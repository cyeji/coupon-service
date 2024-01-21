package com.yeji.couponservice.adapter.out.persistence.converter;

import com.yeji.couponservice.domain.enums.CouponType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.NoSuchElementException;

@Converter
public class CouponTypeConverter implements AttributeConverter<CouponType, String> {


    @Override
    public String convertToDatabaseColumn(CouponType couponType) {
        if (couponType == null) return null;
        return couponType.name();
    }

    @Override
    public CouponType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Arrays.stream(CouponType.values())
                     .filter(coupon -> coupon.name()
                                             .equals(dbData))
                     .findAny()
                     .orElseThrow(() -> new NoSuchElementException());
    }
}
