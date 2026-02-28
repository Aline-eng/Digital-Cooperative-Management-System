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

import com.farmco.farmco_connect.model.Harvest;
import com.farmco.farmco_connect.service.HarvestService;

@RestController
@RequestMapping("/api/harvests")
public class HarvestController {

    @Autowired
    private HarvestService harvestService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveHarvest(@RequestBody Harvest harvest, @RequestParam String farmerId) {
        String response = harvestService.saveHarvest(harvest, farmerId);

        if (response.equals("Harvest saved and payment created successfully")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @GetMapping(value = "/byFarmer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHarvestByFarmer(@RequestParam String farmerId) {
        List<Harvest> harvests = harvestService.getHarvestsByFarmer(farmerId);

        if (harvests == null) {
            return new ResponseEntity<>("No harvest records found for this farmer", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(harvests, HttpStatus.OK);
    }
}
