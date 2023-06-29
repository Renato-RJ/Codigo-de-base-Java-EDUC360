package br.com.meucloset.system.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meucloset.system.dto.SaleDto;
import br.com.meucloset.system.model.Product;
import br.com.meucloset.system.model.Sale;
import br.com.meucloset.system.model.SaledProduct;
import br.com.meucloset.system.repository.PaymentRepository;
import br.com.meucloset.system.repository.ProductRepository;
import br.com.meucloset.system.repository.SaleRepository;
import br.com.meucloset.system.repository.SaledProductRepository;
import br.com.meucloset.system.util.PersonalUtilities;

@Service
public class SaleService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SaleRepository saleRepository;
	@Autowired
	private SaledProductRepository saledProductRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private PersonalUtilities personalUltilities;

	private static Map<String, SaledProduct> cart = new HashMap<String, SaledProduct>();

	public Map<String, SaledProduct> setQtdAndSaveToCart(String sku, int qtd) {
		Product selectedProduct = productRepository.findBySku(sku);
		SaledProduct sp = new SaledProduct(selectedProduct, qtd);
		sp.setQuantity(qtd);
		if (checkQtdProductToStock(sku, qtd))
			cart.put(sp.getSku(), sp);
		return cart;
	}

	public Map<String, SaledProduct> returnFullCart() {
		return cart;
	}

	public Map<String, SaledProduct> removeProductToCart(String sku) {
		cart.remove(sku);
		return cart;
	}

	public Map<String, SaledProduct> updateQtdProductToCart(String sku, int qtd) {
		SaledProduct sp = cart.get(sku);
		sp.setQuantity(qtd);
		cart.put(sku, sp);
		return cart;
	}

	public Map<String, SaledProduct> clearCart() {
		cart.clear();
		return cart;
	}

	public Sale saveSale(Sale sale) {
		if (!cart.isEmpty()) {
			closeSale(sale);
			saleRepository.save(sale);
			clearCart();
			return saleRepository.findById(sale.getId()).get();
		} else
			return null;
	}

	public SaleDto searchSaleById(Integer id) {
		SaleDto saleDto = new SaleDto(saleRepository.findById(id).get());
		return saleDto;
	}

	public List<SaleDto> showHistoricSales() {
		List<SaleDto> listSaleDto = new ArrayList<>();
		for (Sale sale : saleRepository.findAll()) {
			listSaleDto.add(new SaleDto(sale));
		}
		return listSaleDto;
	}

	public Double showTotalValue() {
		List<Sale> sales = saleRepository.findAll();
		Double total = 0d;
		for (Sale sale : sales) {
			total += sale.getTotalValue();
		}
		return total;
	}
	
	public void deleteSaleById(Integer id) {
		saleRepository.deleteById(id);
	}

	private void closeSale(Sale sale) {
		if (!personalUltilities.validatorCpf(sale.getCpf())) {
			sale.setCpf("CPF não informado!");
		}
		paymentRepository.save(sale.getPayment());
		sale.setTotalValue(setTotalSale());
		saveProductsInDb();
		updateQtdProductToStock();
		sale.setProducts(cart);
		sale.setData(LocalDateTime.now());
	}

	private boolean checkQtdProductToStock(String sku, Integer qtd) {
		Product pStock = productRepository.findBySku(sku);
		return qtd <= pStock.getQuantity();
	}

	private void updateQtdProductToStock() {
		for (String sku : cart.keySet()) {
			Product pStock = productRepository.findBySku(sku);
			pStock.setQuantity(pStock.getQuantity() - cart.get(sku).getQuantity());
			productRepository.save(pStock);
		}
	}

	private double setTotalSale() {
		double total = 0d;
		for (String sku : cart.keySet()) {
			total += cart.get(sku).getQuantity() * cart.get(sku).getValueOfSale();
		}
		return total;
	}

	private void saveProductsInDb() {
		for (String sku : cart.keySet()) {
			saledProductRepository.save(cart.get(sku));
		}
	}

}
