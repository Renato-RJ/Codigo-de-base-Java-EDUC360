package br.com.meucloset.system.enums;

public enum EnumColor {
	BC ("Branco"),
	AZ ("Azul"),
	VD ("Verde"),
	AM ("Amarelo"),
	LA ("Laranja"),
	VR ("Vermelho"),
	PR ("Preto"),
	ES ("Estampado");
	
	String friendlyNameColor;

	EnumColor(String name) {
		friendlyNameColor = name;
	}

	public String getFriendlyNameColor() {
		return friendlyNameColor;
	}

}
