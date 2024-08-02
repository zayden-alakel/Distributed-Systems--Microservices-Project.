package com.dsproject.customer.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}
