package com.yeji.couponservice.repository.entity;


import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public class UpdatedEntity extends CreatedEntity {

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
