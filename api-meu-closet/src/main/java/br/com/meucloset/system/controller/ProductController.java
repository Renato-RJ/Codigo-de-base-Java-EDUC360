package br.com.meucloset.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.meucloset.system.model.Product;
import br.com.meucloset.system.service.ProductService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService service;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Product saveProdcut (@RequestBody Product product) {
		return service.createAndSaveProduct(product);
	}
	
	@GetMapping ("/{sku}")
	@ResponseStatus (code = HttpStatus.ACCEPTED)
	public Product searchProductsBySku(@PathVariable String sku){
		return service.searchProduct(sku);
	}
	
	@GetMapping
	@ResponseStatus (code = HttpStatus.ACCEPTED)
	public List<Product> returnAllProducts(){
		return service.listAllProducts();
	}
	
	@PatchMapping ("/{sku}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Product updateProduct(@PathVariable String sku, @RequestParam(name = "value") Double value, 
			@RequestParam(name = "qtd") Integer qtd) {
		return service.updateProductInStock(sku, qtd, value);
	}
	
	@Transactional
	@DeleteMapping("/{sku}")
	@ResponseStatus (code = HttpStatus.ACCEPTED)
	public void deleteProduct(@PathVariable(value = "sku") String sku) {
		service.removeProduct(sku);
	}
	
}
