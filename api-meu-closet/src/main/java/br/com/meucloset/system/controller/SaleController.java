package br.com.meucloset.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.meucloset.system.dto.SaleDto;
import br.com.meucloset.system.model.Sale;
import br.com.meucloset.system.service.SaleService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/sale")
public class SaleController {
	
	@Autowired
	SaleService service;
	
	@PostMapping
	@ResponseStatus (code = HttpStatus.CREATED)
	public Sale closeSale(@RequestBody Sale sale) {
		return service.saveSale(sale);
	}
	
	@GetMapping
	@ResponseStatus (code = HttpStatus.ACCEPTED)
	public List<SaleDto> showAllSales(){
		return service.showHistoricSales();
		}
	
	@GetMapping("/{id}")
	@ResponseStatus (code = HttpStatus.ACCEPTED)
	public SaleDto searchSaleById(@PathVariable Integer id){
		return service.searchSaleById(id);
		}
	
	@GetMapping("/total")
	@ResponseStatus (code = HttpStatus.ACCEPTED)
	public Double showTotalMoneyOfAllSales() {
		return service.showTotalValue();
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	@ResponseStatus (code = HttpStatus.ACCEPTED)
	public void deleteSaleById(@PathVariable Integer id) {
		service.deleteSaleById(id);
	}

}
