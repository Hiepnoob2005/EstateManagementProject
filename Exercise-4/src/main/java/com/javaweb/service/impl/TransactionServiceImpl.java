package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionTypeDTO;
import com.javaweb.model.response.TransactionResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TransactionServiceImpl implements ITransactionService {
    @Autowired
    public TransactionRepository transactionRepository;
    @Autowired
    public TransactionConverter transactionConverter;
    @Autowired
    public CustomerRepository customerRepository;
    @Override
    public TransactionTypeDTO findTransactionById(Long id) {
        TransactionEntity transactionEntity = transactionRepository.findById(id).get();
        return transactionConverter.transactionTypeDTO(transactionEntity);
    }

    @Override
    public void addOrUpdateTransactions(TransactionTypeDTO transactionTypeDTO) {
        TransactionEntity addOrUpdate = transactionConverter.transactionEntity(transactionTypeDTO);
        TransactionEntity transactionEntity;
        if (addOrUpdate.getId() != null) {
            transactionEntity = transactionRepository.findById(addOrUpdate.getId()).get();
            addOrUpdate.setCustomer(transactionEntity.getCustomer());
            addOrUpdate.setCreatedBy(transactionEntity.getCreatedBy());
            addOrUpdate.setCreatedDate(transactionEntity.getCreatedDate());
        }
        else {
            CustomerEntity customer = customerRepository.findById(transactionTypeDTO.getCustomerId()).get();
            addOrUpdate.setCustomer(customer);
        }
        transactionRepository.save(addOrUpdate);
    }

    @Override
    public List<TransactionTypeDTO> findByCodeAndCustomerId(String code, Long customerId) {
        List<TransactionEntity> transactionEntities = transactionRepository.findAllByCustomerIdAndCode(customerId,code);
        List<TransactionTypeDTO> transactionTypeDTOS = new ArrayList<>();
        for (TransactionEntity transaction : transactionEntities){
            TransactionTypeDTO transactionTypeDTO = transactionConverter.transactionTypeDTO(transaction);
            transactionTypeDTOS.add(transactionTypeDTO);
        }
        return transactionTypeDTOS;
    }

    @Override
    public TransactionResponseDTO loadTransaction(Long id, Long customerId) {
        TransactionEntity transactionEntity = transactionRepository.findByIdAndCustomerId(id,customerId);
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setNote(transactionEntity.getNote());
        return transactionResponseDTO;
    }
}
