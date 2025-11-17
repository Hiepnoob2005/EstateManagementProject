package com.javaweb.converter;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionTypeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionConverter {
    @Autowired
    private ModelMapper modelMapper;

    public TransactionEntity transactionEntity (TransactionTypeDTO transactionTypeDTO){
        TransactionEntity transaction = modelMapper.map(transactionTypeDTO, TransactionEntity.class);
        return transaction;
    }
    public TransactionTypeDTO transactionTypeDTO (TransactionEntity transaction){
        TransactionTypeDTO transactionType = modelMapper.map(transaction,TransactionTypeDTO.class);
        return transactionType;
    }
}
