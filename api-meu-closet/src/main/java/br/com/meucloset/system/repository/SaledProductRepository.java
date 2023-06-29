package br.com.meucloset.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.meucloset.system.model.SaledProduct;

public interface SaledProductRepository extends JpaRepository<SaledProduct, Integer> {

}
