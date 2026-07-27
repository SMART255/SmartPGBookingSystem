package com.app.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.request.RegisterUserRequest;
import com.app.dto.response.UserResponse;
import com.app.entity.User;
import com.app.enums.Role;
import com.app.enums.Status;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import com.app.exception.ResourceAlreadyExistsException;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserResponse register(RegisterUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new ResourceAlreadyExistsException("Phone already exists");
        }

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setPhone(request.getPhone());

        user.setGender(request.getGender());

        user.setAddress(request.getAddress());

        user.setRole(Role.USER);

        user.setStatus(Status.ACTIVE);

        user.setCreatedAt(LocalDateTime.now());

        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        UserResponse response = new UserResponse();

        response.setId(savedUser.getId());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setEmail(savedUser.getEmail());
        response.setPhone(savedUser.getPhone());
        response.setGender(savedUser.getGender());
        response.setAddress(savedUser.getAddress());
        response.setRole(savedUser.getRole());
        response.setStatus(savedUser.getStatus());

        return response;
    }
  

}