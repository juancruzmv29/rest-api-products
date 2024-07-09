package com.restapiproductos.security.jwt;

import com.restapiproductos.models.User;
import com.restapiproductos.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class CustomDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Username", username);

        userDetail = userRepository.findByUsername(username);

        if(!Objects.isNull(userDetail)) {
            return new org.springframework.security.core.userdetails.User(userDetail.getEmail(), userDetail.getNombre(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("El nombre de usuario no ha sido encontrado");
        }
    }

    public User getUserDetail() {
        return userDetail;
    }

}
