package com.javaweb.controller.admin;

import com.javaweb.enums.Status;
import com.javaweb.enums.TransactionType;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionTypeDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {
//    @PostMapping(value = "/lien-he/")
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/admin/customer-list")
    public ModelAndView customerList (@ModelAttribute CustomerSearchRequest customerSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("modelSearch", customerSearchRequest);
        mav.addObject("listStaffs",userService.getStaffs());
        if (SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            customerSearchRequest.setStaffId(staffId);
            mav.addObject("customerList", customerService.findAll(customerSearchRequest, PageRequest.of(customerSearchRequest.getPage() - 1, customerSearchRequest.getMaxPageItems() )));
        }
        else {
            mav.addObject("customerList", customerService.findAll(customerSearchRequest, PageRequest.of(customerSearchRequest.getPage() - 1, customerSearchRequest.getMaxPageItems() )));
        }
        List<CustomerSearchResponse> res = customerService.findAll(customerSearchRequest, PageRequest.of(customerSearchRequest.getPage() - 1, customerSearchRequest.getMaxPageItems()));
        CustomerSearchResponse customerSearchResponse = new CustomerSearchResponse();
        DisplayTagUtils.of(request, customerSearchResponse);
        customerSearchResponse.setListResult(res);
        customerSearchResponse.setTotalItems(customerService.countTotalItem());
        mav.addObject("customerList", customerSearchResponse);
        return mav;
    }
    @GetMapping(value = "/admin/customer-edit")
    public ModelAndView customerEdit (@ModelAttribute("customerEdit") CustomerDTO customerDTO, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("statuss", Status.statusType());
        return mav;
    }
    @GetMapping(value = "/admin/customer-edit-{id}")
    public ModelAndView customerEdit (@PathVariable("id") Long id, HttpServletRequest request){
        ModelAndView mav  = new ModelAndView("admin/customer/edit");
        mav.addObject("statuss",Status.statusType());
        CustomerDTO customerDTO = customerService.findCustomerById(id);
        mav.addObject("customerEdit",customerDTO);
        mav.addObject("transactionType", TransactionType.transactionType());
        List<TransactionTypeDTO> transactionListCSKH = transactionService.findByCodeAndCustomerId("CSKH",id);
        mav.addObject("transactionListCSKH", transactionListCSKH);
        List<TransactionTypeDTO> transactionListDDX = transactionService.findByCodeAndCustomerId("DDX",id);
        mav.addObject("transactionListDDX",transactionListDDX);
        return mav;
    }
}
