package br.com.meucloset.system.enums;

public enum EnumPaymentMethod {
	C_CREDITO ("Cartão de crédito."),
	C_DEBITO ("Cartão de débito."),
	DINHEIRO ("Dinheiro"),
	PIX ("PIX");
	
	String friendlyName;

	EnumPaymentMethod(String name) {
		this.friendlyName = name;
	}

	public String getFriendlyName() {
		return friendlyName;
	}	
}
