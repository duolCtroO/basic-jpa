package oort.cloud.shop.entity;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public class CommonEntity {

    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
}
