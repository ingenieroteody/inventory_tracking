package ph.inv.bean;

import ph.inv.enums.SizeEnum;
import ph.inv.enums.StatusEnum;

public class CurrentInventory {
	
	private Long quantity;
	
	private String itemCode;
	
	private String itemName;
	
	private SizeEnum itemSize;

	private StatusEnum itemStatus;
	
	private String colorName;

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public SizeEnum getItemSize() {
		return itemSize;
	}

	public void setItemSize(SizeEnum itemSize) {
		this.itemSize = itemSize;
	}

	public StatusEnum getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(StatusEnum itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	
}
