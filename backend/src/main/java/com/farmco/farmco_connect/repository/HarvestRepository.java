package com.farmco.farmco_connect.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmco.farmco_connect.model.Harvest;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, UUID> {

    List<Harvest> findByFarmerId(UUID farmerId);
}
