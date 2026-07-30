package com.app.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.request.LoginRequest;
import com.app.dto.response.LoginResponse;
import com.app.entity.Owner;
import com.app.entity.User;
import com.app.repository.OwnerRepository;
import com.app.repository.UserRepository;
import com.app.security.JwtUtil;
import com.app.service.AuthService;
@Service
public class AuthServiceImpl implements AuthService {

	private final OwnerRepository ownerRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	
	public AuthServiceImpl(OwnerRepository ownerRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil) {

			this.ownerRepository = ownerRepository;
			this.userRepository = userRepository;
			this.passwordEncoder = passwordEncoder;
			this.jwtUtil = jwtUtil;
	}


	@Override
	public LoginResponse login(LoginRequest request) {

	    // Check Owner first
	    Owner owner = ownerRepository.findByEmail(request.getEmail()).orElse(null);

	    if (owner != null) {

	        if (!passwordEncoder.matches(request.getPassword(), owner.getPassword())) {
	            throw new RuntimeException("Invalid Password");
	        }

	        String token = jwtUtil.generateToken(owner.getEmail());

	        return new LoginResponse(token, "Owner Login Successful");
	    }

	    // Check User
	    User user = userRepository.findByEmail(request.getEmail())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
	        throw new RuntimeException("Invalid Password");
	    }

	    String token = jwtUtil.generateToken(user.getEmail());

	    return new LoginResponse(token, "User Login Successful");
	}
}