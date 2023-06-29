package br.com.meucloset.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.meucloset.system.model.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
	Product findBySku(String sku);
	void deleteBySku(String sku);

}
