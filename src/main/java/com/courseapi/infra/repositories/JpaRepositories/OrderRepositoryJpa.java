package com.courseapi.infra.repositories.JpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courseapi.application.repositories.OrderRepository;

@Repository
public interface OrderRepositoryJpa extends JpaRepository<OrderJpa, String>, OrderRepository {

}
