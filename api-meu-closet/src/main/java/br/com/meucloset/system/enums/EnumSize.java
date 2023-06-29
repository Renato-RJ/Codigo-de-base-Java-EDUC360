package br.com.meucloset.system.enums;

public enum EnumSize {
	PQN ("Pequeno"),
	MDO ("Médio"),
	GRD ("Grande"),
	XGD ("Extra Grande"),
	XXG ("Extra Extra Gande");
	
	String friendlySizeName;
	
	EnumSize(String name) {
		friendlySizeName = name;
	}

	public String getFriendlySizeName() {
		return friendlySizeName;
	}

}
