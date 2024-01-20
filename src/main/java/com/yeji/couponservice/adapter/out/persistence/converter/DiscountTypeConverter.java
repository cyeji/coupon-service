package com.yeji.couponservice.adapter.out.persistence.converter;

import com.yeji.couponservice.adapter.out.persistence.enums.DiscountType;
import jakarta.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class DiscountTypeConverter implements AttributeConverter<DiscountType, String> {

    @Override
    public String convertToDatabaseColumn(DiscountType discountType) {
        if (discountType == null) return null;
        return discountType.name();
    }

    @Override
    public DiscountType convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        return Arrays.stream(DiscountType.values())
                     .filter(discount -> discount.name()
                                                 .equals(dbData))
                     .findAny()
                     .orElseThrow(() -> new NoSuchElementException());
    }
}
