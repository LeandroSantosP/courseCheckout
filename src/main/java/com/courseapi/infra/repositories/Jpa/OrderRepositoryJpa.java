package com.courseapi.infra.repositories.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courseapi.application.repositories.OrderRepository;
import com.courseapi.infra.repositories.Jpa.JpaTableObjScan.OrderJpa;

@Repository
public interface OrderRepositoryJpa extends JpaRepository<OrderJpa, String>, OrderRepository {

}
