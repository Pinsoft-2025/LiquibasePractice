package com.staj.liquibasepractice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private float price;
    private String explanation;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference //dto gelene kadar geçici çözüm
    private Category category;
}
