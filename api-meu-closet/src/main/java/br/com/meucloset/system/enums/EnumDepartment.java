package br.com.meucloset.system.enums;

public enum EnumDepartment {
	IN ("Infantil"),
	FE ("Feminino"),
	MA ("Masculino");
	
	String friendlyDepartmentName;

	EnumDepartment(String name) {
		friendlyDepartmentName = name;
	}

	public String getFriendlyDepartmentName() {
		return friendlyDepartmentName;
	}

}
