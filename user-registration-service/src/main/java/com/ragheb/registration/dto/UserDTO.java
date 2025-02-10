package com.ragheb.registration.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
    private String email;
    private LocalDate dateOfBirth;
    private String mobile;
    private String city;
}
