package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.Status;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerConverter {
    @Autowired
    private ModelMapper modelMapper;
    public CustomerSearchResponse toCustomerSearchResponse(CustomerEntity customer) {
        CustomerSearchResponse customerSearchResponse = modelMapper.map(customer, CustomerSearchResponse.class);
        customerSearchResponse.setName(customer.getFullName());
        if (customer.getStatus() != ""){
            String statusKey = customer.getStatus();
            String statusValue = Status.statusType().get(statusKey);
            customerSearchResponse.setStatus(statusValue);
        }
        return customerSearchResponse;
    }
    public CustomerEntity toCustomerEntity (CustomerDTO customerDTO ){
        CustomerEntity customer = modelMapper.map(customerDTO, CustomerEntity.class);
        customer.setFullName(customerDTO.getName());
        customer.setPhone(customerDTO.getCustomerPhone());
        if (customerDTO.getStatus() == "" || customerDTO.getStatus() == null){
            customer.setStatus("CHUA_XL");
        }
        return customer;
    }
    public CustomerDTO toCustomerDTO (CustomerEntity customerEntity){
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        customerDTO.setName(customerEntity.getFullName());
        customerDTO.setCustomerPhone(customerEntity.getPhone());
        return customerDTO;
    }
}
