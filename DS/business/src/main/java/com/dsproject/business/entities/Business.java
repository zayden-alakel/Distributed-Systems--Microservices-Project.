package com.dsproject.business.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Business {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}
