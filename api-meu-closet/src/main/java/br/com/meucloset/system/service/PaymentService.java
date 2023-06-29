package br.com.meucloset.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meucloset.system.model.Payment;
import br.com.meucloset.system.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository repository;
	
	public Payment savePayment(Payment payment) {
		return repository.save(payment);
	}
}
