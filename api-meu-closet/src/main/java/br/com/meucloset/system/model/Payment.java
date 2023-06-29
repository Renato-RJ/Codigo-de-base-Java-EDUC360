package br.com.meucloset.system.model;

import br.com.meucloset.system.enums.EnumPaymentMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private EnumPaymentMethod method;
	private Long identify = 0l;

	public Payment() {

	}

	public Payment(EnumPaymentMethod method) {
		this.method = method;
	}

	public Payment(EnumPaymentMethod method, Long identify) {
		this.method = method;
		this.identify = identify;
	}

	public EnumPaymentMethod getMethod() {
		return method;
	}

	public String getFriendlyMethod() {
		return method.getFriendlyName();
	}

	public Long getIdentify() {
		return identify;
	}
}
