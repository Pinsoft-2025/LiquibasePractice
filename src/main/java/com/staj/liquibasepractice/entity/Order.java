package com.staj.liquibasepractice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "\"price\"")
    private float price;

    @Column(name = "\"quantity\"")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "\"user_id\"", nullable = false)
    @JsonBackReference //dto gelene kadar geçici çözüm
    private User user;

}
