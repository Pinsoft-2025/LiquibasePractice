package com.staj.liquibasepractice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"user\"") // PostgreSQL'de case-sensitive yapmak için çift tırnak!
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"id\"") // Case-sensitive sütun
    private Long id;

    @Column(name = "\"email\"", unique = true, nullable = false)
    private String email;

    @Column(name = "\"username\"", nullable = false)
    private String username;

    @Column(name = "\"password\"", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "\"role_Id\"", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference //dto gelene kadar geçici çözüm
    private List<Order> orders = new ArrayList<Order>();
}
