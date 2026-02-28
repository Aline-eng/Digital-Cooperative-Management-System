package com.farmco.farmco_connect.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmco.farmco_connect.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    Payment findByHarvestId(UUID harvestId);
}
