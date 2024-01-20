package com.yeji.couponservice.adapter.out.persistence;


import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

@MappedSuperclass
public class CreatedEntity {

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;
}
