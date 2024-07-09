package com.restapiproductos.services.Impl;

import com.restapiproductos.repository.UserRepository;
import com.restapiproductos.security.jwt.CustomDetailsService;
import com.restapiproductos.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomDetailsService customDetailsService;

    @Autowired
    private JwtUtil jwtUtil;




}
