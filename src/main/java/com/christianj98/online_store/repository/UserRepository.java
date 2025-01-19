package com.christianj98.online_store.repository;

import com.christianj98.online_store.entity.CustomUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CustomUserDetails, Long> {

    Optional<CustomUserDetails> findByUsername(String username);

    @Query(
            value = "SELECT COUNT(*) " +
                    "FROM users u " +
                    "WHERE u.username = :email",
            nativeQuery = true
    )
    Integer getEmailCount(@Param("email") String email);
    Optional<CustomUserDetails> findByEmailVerificationToken(String emailVerificationToken);
}
