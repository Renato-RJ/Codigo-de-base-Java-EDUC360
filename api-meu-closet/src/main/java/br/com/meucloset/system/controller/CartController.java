package br.com.meucloset.system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.meucloset.system.model.SaledProduct;
import br.com.meucloset.system.service.SaleService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	SaleService service;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Map<String, SaledProduct> addToTheCart(@RequestParam(name = "sku") String sku,
			@RequestParam(name = "qtd") Integer qtd) {
		return service.setQtdAndSaveToCart(sku, qtd);
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Map<String, SaledProduct> showCart() {
		return service.returnFullCart();
	}

	@PatchMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Map<String, SaledProduct> updateProductOfCart(@RequestParam(value = "sku") String sku,
			@RequestParam(value = "qtd") int qtd) {
		return service.updateQtdProductToCart(sku, qtd);
	}

	@PatchMapping("/{sku}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Map<String, SaledProduct> removeProductOfCart(@PathVariable String sku) {
		return service.removeProductToCart(sku);
	}

	@Transactional
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Map<String, SaledProduct> clearCart() {
		return service.clearCart();
	}

}
