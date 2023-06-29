package br.com.meucloset.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.meucloset.system.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
