package com.example.vuebackend.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.example.vuebackend.repository.dto.BookStatus;

@Converter(autoApply = true)
public class BookStatusConverter implements AttributeConverter<BookStatus, Integer>{

    @Override
    public Integer convertToDatabaseColumn(BookStatus attribute) {
        // TODO Auto-generated method stub
        return attribute.getCode();
    }

    @Override
    public BookStatus convertToEntityAttribute(Integer dbData) {
        // TODO Auto-generated method stub
        return dbData != null ?new BookStatus(dbData) : null;
    }
    
}
