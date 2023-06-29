package br.com.meucloset.system.enums;

public enum EnumType {
	NA ("Nacional"),
	IM ("Importada"),
	PP ("Produção própria");

	String friendlyNameType;
	
	EnumType(String name) {
		friendlyNameType = name;
	}

	public String getFriendlyNameType() {
		return friendlyNameType;
	}
	
}
