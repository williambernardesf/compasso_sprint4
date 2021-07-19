package com.vendaprodutos.model.exception;

import java.text.MessageFormat;

public class ItemIsAlreadyAssignedException extends RuntimeException{
    public ItemIsAlreadyAssignedException(final Long enderecoId, final Long pessoaId){
        super(MessageFormat.format("Item: {0} is already assigned to cart: {1}", enderecoId, pessoaId));
    }
}
