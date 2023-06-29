package br.com.meucloset.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.meucloset.system.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

}
