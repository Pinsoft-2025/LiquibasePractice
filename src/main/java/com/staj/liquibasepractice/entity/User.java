package com.staj.liquibasepractice.entity;

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
@Table(name = "\"User\"") // PostgreSQL'de case-sensitive yapmak için çift tırnak!
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"") // Case-sensitive sütun
    private Long id;

    @Column(name = "\"Email\"", unique = true, nullable = false)
    private String email;

    @Column(name = "\"Username\"", nullable = false)
    private String username;

    @Column(name = "\"Password\"", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "\"Role_Id\"", nullable = false)
    private Role role;
}
