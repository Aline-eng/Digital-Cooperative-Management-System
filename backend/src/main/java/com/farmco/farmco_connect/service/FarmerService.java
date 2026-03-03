package com.farmco.farmco_connect.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.farmco.farmco_connect.model.Farmer;
import com.farmco.farmco_connect.model.Location;
import com.farmco.farmco_connect.repository.FarmerRepository;
import com.farmco.farmco_connect.repository.LocationRepository;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private LocationRepository locationRepository;

    public String registerFarmer(Farmer farmer, String locationId) {
        if (farmerRepository.existsByNationalId(farmer.getNationalId())) {
            return "Farmer with that National ID already exists";
        }

        UUID parsedLocationId;
        try {
            parsedLocationId = UUID.fromString(locationId);
        } catch (IllegalArgumentException e) {
            return "Invalid location id";
        }

        Location location = locationRepository.findById(parsedLocationId).orElse(null);
        if (location == null) {
            return "Location not found";
        }

        farmer.setLocation(location);
        farmerRepository.save(farmer);
        return "Farmer registered successfully";
    }

    public List<Farmer> getAllFarmers() {
        List<Farmer> farmers = farmerRepository.findAll();

        if (farmers.isEmpty()) {
            return null;
        }

        return farmers;
    }

    public Page<Farmer> getFarmersPaginatedAndSorted(int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = Sort.Direction.fromOptionalString(direction).orElse(Sort.Direction.ASC);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        return farmerRepository.findAll(pageable);
    }
}
