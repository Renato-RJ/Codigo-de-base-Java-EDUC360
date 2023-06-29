package br.com.meucloset.system.model;

import br.com.meucloset.system.enums.EnumCategory;
import br.com.meucloset.system.enums.EnumColor;
import br.com.meucloset.system.enums.EnumDepartment;
import br.com.meucloset.system.enums.EnumSize;
import br.com.meucloset.system.enums.EnumType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	private String sku;
	private Integer quantity;
	private String description;
	private Double valueOfSale;
	private EnumCategory category;
	private EnumColor color;
	private EnumType type;
	private EnumSize size;
	private EnumDepartment department;

	public void setCategory() {
		this.category = EnumCategory.valueOf(sku.substring(7, 9));
	}

	public String getCategory() {
		return category.getFriendlyCategoryName();
	}

	public void setColor() {
		this.color = EnumColor.valueOf(sku.substring(9, 11));
	}

	public String getColor() {
		return color.getFriendlyNameColor();
	}

	public void setType() {
		this.type = EnumType.valueOf(sku.substring(3, 5));
	}

	public String getType() {
		return type.getFriendlyNameType();
	}

	public void setSize() {
		this.size = EnumSize.valueOf(sku.substring(0, 3));
	}

	public String getSize() {
		return size.getFriendlySizeName();
	}

	public void setDepartment() {
		this.department = EnumDepartment.valueOf(sku.substring(5, 7));
	}

	public String getDepartment() {
		return department.getFriendlyDepartmentName();
	}

}
