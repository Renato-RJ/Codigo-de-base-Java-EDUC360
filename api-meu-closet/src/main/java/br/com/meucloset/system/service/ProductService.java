package br.com.meucloset.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meucloset.system.model.Product;
import br.com.meucloset.system.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product createAndSaveProduct(Product product) {
		if (searchProduct(product.getSku()) == null) {
			try {
				product.setCategory();
				product.setColor();
				product.setDepartment();
				product.setSize();
				product.setType();
			} catch (Exception e) {
				return null;
			}
			return productRepository.save(product);
		} else {
			return updateProductInStock(product.getSku(), product.getQuantity(), product.getValueOfSale());
		}
	}

	public Product updateProductInStock(String sku, Integer qtd, Double vlr) {
		Product product = productRepository.findBySku(sku);
		if (qtd != null)
			product.setQuantity(product.getQuantity() + qtd);
		if (vlr >= 0)
			product.setValueOfSale(vlr);
		return productRepository.save(product);
	}

	public void removeProduct(String sku) {
		productRepository.deleteBySku(sku);
	}

	public Product searchProduct(String sku) {
		return productRepository.findBySku(sku);
	}

	public List<Product> listAllProducts() {
		return productRepository.findAll();
	}
}
