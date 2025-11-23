package com.javaweb.service;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionTypeDTO;
import com.javaweb.model.response.TransactionResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ITransactionService {

    TransactionTypeDTO findTransactionById (Long id);
    void addOrUpdateTransactions(TransactionTypeDTO transactionTypeDTO);
    List<TransactionTypeDTO> findByCodeAndCustomerId(String code, Long customerId);
    TransactionResponseDTO loadTransaction(Long id, Long customerId);
}
