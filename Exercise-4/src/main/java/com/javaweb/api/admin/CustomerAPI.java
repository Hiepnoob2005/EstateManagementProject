package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionTypeDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.TransactionResponseDTO;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "customerAPIOfAdmin")
@RequestMapping("/api/customer")
public class CustomerAPI {
    @Autowired
    public ICustomerService ICustomerService;
    @Autowired
    public ITransactionService ITransactionService;
    @Autowired
    public IUserService IUserService;
    @PostMapping
    public Boolean addOrUpdateCustomer(@RequestBody CustomerDTO customerDTO){
        ICustomerService.addOrUpdateCustomers(customerDTO);
        System.out.print("thêm/sửa thành công");
        return true;
    }
    @DeleteMapping
    public Boolean deleteCustomers(@PathVariable List<Long> ids){
        if (!ids.isEmpty()){
            ICustomerService.deleteCustomers(ids);
            System.out.println("ok");
            return true;
        }
        System.out.println("không có gì để xóa");
        return false;
    }
    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id){
        ResponseDTO result = ICustomerService.listStaffs(id);
        return result;
    }
    @PostMapping("/assignment")
    public Boolean updateAssignmentCustomer (@RequestBody AssignmentCustomerDTO assignmentCustomerDTO){
        IUserService.updateAssignmentCustomer(assignmentCustomerDTO);
        return true;
    }
    @PostMapping("/transaction")
    public Boolean addOrUpdateTransaction (@RequestBody TransactionTypeDTO transactionTypeDTO){
        ITransactionService.addOrUpdateTransactions(transactionTypeDTO);
        System.out.print("ok");
        return true;
    }
    @GetMapping("/{customerId}/transaction/{id}")
    public TransactionResponseDTO transactionResponseDTO (@PathVariable Long customerId, @PathVariable Long id){
        TransactionResponseDTO result = ITransactionService.loadTransaction(id,customerId);
        return result;
    }

}
