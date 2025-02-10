package com.ragheb.registration.service.impl;

import com.ragheb.registration.dto.UserDTO;
import com.ragheb.registration.entity.User;
import com.ragheb.registration.repository.UserRepository;
import com.ragheb.registration.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationImpl implements UserRegistrationService {

    private final UserRepository userRepository;

    @Override
    public void register(UserDTO userDTO) {
        User user = User.builder()
                .email(userDTO.getEmail())
                .mobile(userDTO.getMobile())
                .dateOfBirth(userDTO.getDateOfBirth())
                .city(userDTO.getCity())
                .build();
                this.userRepository.save(user);

//        User savedUser =
//        return userDTO.builder()
//                .email(savedUser.getEmail())
//                .mobile(savedUser.getMobile())
//                .dateOfBirth(savedUser.getDateOfBirth())
//                .city(savedUser.getCity())
//                .build();
    }
}
