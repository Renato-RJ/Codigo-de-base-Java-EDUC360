package br.com.meucloset.system.model;

import java.time.LocalDateTime;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToMany
	private Map<String, SaledProduct> products;
	private Double totalValue;
	private String cpf;
	private LocalDateTime data;
	@OneToOne
	private Payment payment;

	public void setCpf(String cpf) {
		if (!cpf.startsWith("C")) {
			cpf = cpf.replace(".", "");
			cpf = cpf.replace("-", "");
			cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
			this.cpf = cpf;
		} else {
			this.cpf = "CPF não informado!";
		}
	}

}
