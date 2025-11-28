package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.Status;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    @Autowired
    private ModelMapper modelMapper;
    public CustomerSearchResponse toCustomerSearchResponse(CustomerEntity customer) {
        CustomerSearchResponse customerSearchResponse = modelMapper.map(customer, CustomerSearchResponse.class);
        customerSearchResponse.setFullName(customer.getFullName());
        customerSearchResponse.setPhone(customer.getPhone());
        if (customer.getStatus() != ""){
            String statusKey = customer.getStatus();
            String statusValue = Status.statusType().get(statusKey);
            customerSearchResponse.setStatus(statusValue);
        }
        return customerSearchResponse;
    }
    public CustomerEntity toCustomerEntity (CustomerDTO customerDTO ){
        CustomerEntity customer = modelMapper.map(customerDTO, CustomerEntity.class);
        customer.setFullName(customerDTO.getFullname());
        customer.setPhone(customerDTO.getCustomerPhone());
        customer.setCompanyName(customerDTO.getCompanyName());
        if (customerDTO.getStatus() == "" || customerDTO.getStatus() == null){
            customer.setStatus("CHUA_XL");
        }
        return customer;
    }
    public CustomerDTO toCustomerDTO (CustomerEntity customerEntity){
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        customerDTO.setFullname(customerEntity.getFullName());
        customerDTO.setCompanyName(customerEntity.getCompanyName());
        customerDTO.setCustomerPhone(customerEntity.getPhone());
        return customerDTO;
    }
}
