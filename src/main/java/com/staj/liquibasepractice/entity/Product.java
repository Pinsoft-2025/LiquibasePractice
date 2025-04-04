package com.staj.liquibasepractice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"product\"")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"name\"", nullable = false)
    private String name;

    @Column(name = "\"price\"", nullable = false)
    private float price;

    @Column(name = "\"explanation\"")
    private String explanation;

    @Column(name = "\"base64_image\"", columnDefinition = "TEXT")
    private String base64Image;

    @ManyToOne
    @JoinColumn(name = "\"category_id\"", nullable = false)
    @JsonBackReference //dto gelene kadar geçici çözüm
    private Category category;

    @ManyToOne
    @JoinColumn(name = "\"order_id\"", nullable = false)
    @JsonBackReference
    private Order order;
}
