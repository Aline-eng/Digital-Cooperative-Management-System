package com.farmco.farmco_connect.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmco.farmco_connect.model.Farmer;
import com.farmco.farmco_connect.model.Harvest;
import com.farmco.farmco_connect.model.Payment;
import com.farmco.farmco_connect.model.PaymentStatus;
import com.farmco.farmco_connect.repository.FarmerRepository;
import com.farmco.farmco_connect.repository.HarvestRepository;
import com.farmco.farmco_connect.repository.PaymentRepository;

@Service
public class HarvestService {

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public String saveHarvest(Harvest harvest, String farmerId) {
        UUID parsedFarmerId;
        try {
            parsedFarmerId = UUID.fromString(farmerId);
        } catch (IllegalArgumentException e) {
            return "Invalid farmer id";
        }

        Farmer farmer = farmerRepository.findById(parsedFarmerId).orElse(null);
        if (farmer == null) {
            return "Farmer not found";
        }

        harvest.setFarmer(farmer);
        Harvest savedHarvest = harvestRepository.save(harvest);

        Double totalAmount = savedHarvest.getWeight() * savedHarvest.getPricePerKg();

        Payment payment = new Payment();
        payment.setHarvest(savedHarvest);
        payment.setAmount(totalAmount);
        payment.setStatus(PaymentStatus.PENDING);
        paymentRepository.save(payment);

        return "Harvest saved and payment created successfully";
    }

    public List<Harvest> getHarvestsByFarmer(String farmerId) {
        UUID parsedFarmerId;
        try {
            parsedFarmerId = UUID.fromString(farmerId);
        } catch (IllegalArgumentException e) {
            return null;
        }

        List<Harvest> harvests = harvestRepository.findByFarmerId(parsedFarmerId);

        if (harvests.isEmpty()) {
            return null;
        }

        return harvests;
    }
}
