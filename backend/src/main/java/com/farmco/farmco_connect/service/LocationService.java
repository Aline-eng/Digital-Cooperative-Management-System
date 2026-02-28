package com.farmco.farmco_connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmco.farmco_connect.model.Location;
import com.farmco.farmco_connect.repository.LocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public String saveLocation(Location location) {
        locationRepository.save(location);
        return "Location saved successfully";
    }

    public List<Location> getAllLocations() {
        List<Location> locations = locationRepository.findAll();

        if (locations.isEmpty()) {
            return null;
        }

        return locations;
    }
}
