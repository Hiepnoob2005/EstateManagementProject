package com.javaweb.service;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionTypeDTO;

import java.util.List;

public interface ITransactionService {

    TransactionTypeDTO findTransactionById (Long id);
    void addOrUpdateTransactions(TransactionTypeDTO transactionTypeDTO);
    List<TransactionTypeDTO> findByCodeAndCustomerId(String code, Long customerId);
}
