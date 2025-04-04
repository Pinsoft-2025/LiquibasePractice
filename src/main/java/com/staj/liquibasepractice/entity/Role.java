package com.staj.liquibasepractice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"role\"") // Case-sensitive yapmak için çift tırnak!
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"id\"") // Case-sensitive sütun
    private Long id;

    @Column(name = "\"name\"", nullable = false, unique = true)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
