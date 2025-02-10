package com.ragheb.registration.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @Column(unique = true, nullable = false)
    private String email;

    private LocalDate dateOfBirth;

    @Column(unique = true)
    private String mobile;
    private String city;
}
