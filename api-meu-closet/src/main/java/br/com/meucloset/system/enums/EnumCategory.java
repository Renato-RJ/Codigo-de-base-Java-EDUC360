package br.com.meucloset.system.enums;

public enum EnumCategory {
	CA ("Calça"),
	BE ("Bermuda"),
	BS ("Blusa");
	
	String friendlyCategoryName;
	
	EnumCategory(String name) {
		friendlyCategoryName = name;
	}

	public String getFriendlyCategoryName() {
		return friendlyCategoryName;
	}

}
