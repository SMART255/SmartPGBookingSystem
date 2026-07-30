package com.app.serviceImpl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.request.RegisterUserRequest;
import com.app.dto.response.UserResponse;
import com.app.entity.User;
import com.app.enums.Role;
import com.app.enums.Status;
import com.app.exception.ResourceAlreadyExistsException;
import com.app.repository.UserRepository;
import com.app.service.UserService;

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

        System.out.println("========== USER REGISTRATION ==========");

        try {

            System.out.println("Step 1 : Checking Email");

            if (userRepository.existsByEmail(request.getEmail())) {
                throw new ResourceAlreadyExistsException("Email already exists");
            }

            System.out.println("Step 2 : Checking Phone");

            if (userRepository.existsByPhone(request.getPhone())) {
                throw new ResourceAlreadyExistsException("Phone already exists");
            }

            System.out.println("Step 3 : Creating User");

            User user = new User();

            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());

            // Encrypt Password
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            user.setPhone(request.getPhone());
            user.setGender(request.getGender());
            user.setAddress(request.getAddress());

            // Default Role & Status
            user.setRole(Role.USER);
            user.setStatus(Status.ACTIVE);

            System.out.println("Step 4 : Saving User");

            User savedUser = userRepository.save(user);

            System.out.println("Step 5 : User Saved Successfully");

            return mapToResponse(savedUser);

        } catch (Exception e) {

            System.out.println("USER REGISTRATION FAILED");
            e.printStackTrace();

            throw e;
        }
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public UserResponse updateUser(Long id,
                                   RegisterUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {

            throw new ResourceAlreadyExistsException("Email already exists");
        }

        if (!user.getPhone().equals(request.getPhone())
                && userRepository.existsByPhone(request.getPhone())) {

            throw new ResourceAlreadyExistsException("Phone already exists");
        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setGender(request.getGender());
        user.setAddress(request.getAddress());

        if (request.getPassword() != null &&
                !request.getPassword().isBlank()) {

            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        User updatedUser = userRepository.save(user);

        return mapToResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    private UserResponse mapToResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setGender(user.getGender());
        response.setAddress(user.getAddress());
        response.setStatus(user.getStatus());

        return response;
    }
}