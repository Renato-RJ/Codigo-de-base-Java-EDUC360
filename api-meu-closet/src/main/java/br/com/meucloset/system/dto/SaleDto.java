package br.com.meucloset.system.dto;

import java.time.LocalDateTime;
import java.util.Map;

import br.com.meucloset.system.model.Sale;
import br.com.meucloset.system.model.SaledProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDto {

	private Integer id;
	private String cpf;
	private Double totalValue;
	private LocalDateTime data;
	private PaymentDto payment;
	private Map<String, SaledProduct> products;

	public SaleDto(Sale sale) {
		this.id = sale.getId();
		this.products = sale.getProducts();
		this.totalValue = sale.getTotalValue();
		this.cpf = defineCpf(sale);
		this.data = sale.getData();
		this.payment = new PaymentDto(sale.getPayment());
	}

	private String defineCpf(Sale sale) {
		if (!sale.getCpf().startsWith("C")) {
			return sale.getCpf().substring(0, 3) + ".***.**" + sale.getCpf().substring(10);
		} else {
			return "CPF não informado!";
		}
	}

}
