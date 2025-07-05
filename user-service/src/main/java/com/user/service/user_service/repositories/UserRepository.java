package com.user.service.user_service.repositories;

import com.user.service.user_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA will automatically create the method body for you!
    // For example, you can add:
    // Optional<User> findByEmail(String email);
}
