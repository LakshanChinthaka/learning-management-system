package com.chinthaka.learningmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id", length = 20)
    private Long addressId;

    @Column(name = "city", length = 100, nullable = false)
    private String city;

    @Column(name = "districts", length = 100, nullable = false)
    private String districts ;

    @Column(name = "date_modify_time", columnDefinition="DATETIME DEFAULT NOW()")
    private LocalDateTime dateOfModify;

}
