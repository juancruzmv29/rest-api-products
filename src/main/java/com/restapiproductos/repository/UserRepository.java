package com.restapiproductos.repository;

import com.restapiproductos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
