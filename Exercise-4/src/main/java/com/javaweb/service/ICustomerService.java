package com.javaweb.service;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    ResponseDTO listStaffs (Long customerId);
    List<CustomerSearchResponse> findAll (CustomerSearchRequest customerSearchRequest, Pageable pageable);
    void deleteCustomers (List<Long> ids);
    CustomerDTO findCustomerById (Long id);
    int countTotalItem();
    void addOrUpdateCustomers (CustomerDTO customerDTO);
}
