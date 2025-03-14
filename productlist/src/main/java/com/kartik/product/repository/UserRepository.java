package com.kartik.product.repository;

import com.kartik.product.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.boot.beanvalidation.IntegrationException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
