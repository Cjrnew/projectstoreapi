package com.ccostao.projectstoreapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccostao.projectstoreapi.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
