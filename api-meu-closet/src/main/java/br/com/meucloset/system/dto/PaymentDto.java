package br.com.meucloset.system.dto;

import br.com.meucloset.system.enums.EnumPaymentMethod;
import br.com.meucloset.system.model.Payment;
import lombok.Data;

@Data
public class PaymentDto {

	private Integer id;
	private EnumPaymentMethod method;
	private String identify;

	public PaymentDto(Payment payment) {
		this.id = payment.getId();
		this.method = payment.getMethod();
		this.identify = defineIdentify(payment);
	}

	public PaymentDto() {

	}

	public PaymentDto(EnumPaymentMethod method) {
		this.method = method;
	}

	public PaymentDto(EnumPaymentMethod method, Long identify) {
		this.method = method;
		this.identify = defineIdentify(identify);
	}

	public String getMethod() {
		return method.getFriendlyName();
	}

	public String getIdentify() {
		return identify;
	}
	
	public Integer getId() {
		return id;
	}

	private String defineIdentify(Payment payment) {
		String identifyHide = null;
		if (payment.getIdentify() == null || payment.getIdentify() == 0) {
			identifyHide = "SEM IDENTIFICADOR.";
			
		}else {
			identifyHide =  payment.getIdentify().toString().substring(0, 3) + "****"
					+ payment.getIdentify().toString().substring(7);
		}
		return identifyHide;
	}
	
	private String defineIdentify(Long identify) {
		String identifyHide = null;
		if (identify > 0) {
			identifyHide =  identify.toString().substring(0, 3) + "****"
					+ identify.toString().substring(7);
		}
		return identifyHide;
	}
}
