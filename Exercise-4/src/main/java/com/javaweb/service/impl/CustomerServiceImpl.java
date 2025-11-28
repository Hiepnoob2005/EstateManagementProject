package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public CustomerConverter customerConverter;
    @Override
    public ResponseDTO listStaffs(Long customerId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1,"STAFF");
//        List<UserEntity> staffAssignment = userRepository.findUsersByCustomer(customerEntity);
//        List<UserEntity> staffAssignment = building.getUserEntities();
        List<UserEntity> staffAssignment = customerEntity.getUserEntities();
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity it : staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if (staffAssignment.contains(it)){
                staffResponseDTO.setChecked("checked");
            }
            else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public List<CustomerSearchResponse> findAll(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        List<CustomerEntity> customerEntities = customerRepository.findAll(customerSearchRequest, pageable);
        List<CustomerSearchResponse> result = new ArrayList<>();
        for (CustomerEntity it : customerEntities){
            CustomerSearchResponse customerSearchResponse = customerConverter.toCustomerSearchResponse(it);
            result.add(customerSearchResponse);
        }
        return result;

    }

    @Override
    public void deleteCustomers(List<Long> ids) {
        for (Long id : ids){
            CustomerEntity customer = customerRepository.findById(id).get();
            customer.setIsActive(0);
            customerRepository.save(customer);
        }
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        CustomerEntity customer = customerRepository.findById(id).get();
        CustomerDTO customerDTO = customerConverter.toCustomerDTO(customer);
        return customerDTO;
    }

    @Override
    public int countTotalItem() {
        return customerRepository.countTotalItem();
    }

    @Override
    public void addOrUpdateCustomers(CustomerDTO customerDTO) {
        CustomerEntity updateOrAddCustomer = customerConverter.toCustomerEntity(customerDTO);
        CustomerEntity customerEntity;
        if (updateOrAddCustomer.getId() != null){
            customerEntity = customerRepository.findById(updateOrAddCustomer.getId()).get();
            updateOrAddCustomer.setUserEntities(customerEntity.getUserEntities()); //setStaff
        }
        updateOrAddCustomer.setIsActive(1);
        customerRepository.save(updateOrAddCustomer);
    }
}
