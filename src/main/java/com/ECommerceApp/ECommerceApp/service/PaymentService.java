package com.ECommerceApp.ECommerceApp.service;

import com.ECommerceApp.ECommerceApp.model.Order;
import com.ECommerceApp.ECommerceApp.model.Payment;
import com.ECommerceApp.ECommerceApp.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class PaymentService {
    private final PaymentRepository PaymentRepository;
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository PaymentRepository, PaymentRepository paymentRepository) {
        this.PaymentRepository = PaymentRepository;
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(Float price) {
        Payment payment = new Payment();
        payment.setPaymentDate(Date.valueOf(LocalDate.now()));
        payment.setAmount(price);
        payment.setPaymentMethod("Card");
        paymentRepository.save(payment);
        return payment;
    }
}
