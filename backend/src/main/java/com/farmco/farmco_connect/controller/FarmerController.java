package com.farmco.farmco_connect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmco.farmco_connect.model.Farmer;
import com.farmco.farmco_connect.service.FarmerService;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveFarmer(@RequestBody Farmer farmer, @RequestParam String locationId) {
        String response = farmerService.registerFarmer(farmer, locationId);

        if (response.equals("Farmer registered successfully")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllFarmers() {
        List<Farmer> farmers = farmerService.getAllFarmers();

        if (farmers == null) {
            return new ResponseEntity<>("No farmers found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(farmers, HttpStatus.OK);
    }
}
