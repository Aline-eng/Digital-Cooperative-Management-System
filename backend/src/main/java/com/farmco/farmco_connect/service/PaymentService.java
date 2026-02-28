package com.farmco.farmco_connect.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmco.farmco_connect.model.Payment;
import com.farmco.farmco_connect.model.PaymentStatus;
import com.farmco.farmco_connect.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public String withdrawPayment(String harvestId) {
        UUID parsedHarvestId;
        try {
            parsedHarvestId = UUID.fromString(harvestId);
        } catch (IllegalArgumentException e) {
            return "Invalid harvest id";
        }

        Payment payment = paymentRepository.findByHarvestId(parsedHarvestId);

        if (payment == null) {
            return "Payment not found for this harvest";
        }

        if (payment.getStatus() == PaymentStatus.PAID) {
            return "Payment already withdrawn";
        }

        payment.setStatus(PaymentStatus.PAID);
        paymentRepository.save(payment);
        return "Payment withdrawn successfully";
    }
}
