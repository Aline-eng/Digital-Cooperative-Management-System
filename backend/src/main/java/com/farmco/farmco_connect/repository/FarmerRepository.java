package com.farmco.farmco_connect.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmco.farmco_connect.model.Farmer;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, UUID> {

    Boolean existsByNationalId(String nationalId);
}
