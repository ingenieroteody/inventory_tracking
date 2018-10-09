package ph.inv.bean;

import ph.inv.enums.RtwStatusEnum;
import ph.inv.enums.SizeEnum;

public class CurrentInventory {
	
	private Long quantity;
	
	private String itemCode;
	
	private String itemName;
	
	private SizeEnum itemSize;

	private RtwStatusEnum itemStatus;
	
	private String colorName;

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

	public RtwStatusEnum getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(RtwStatusEnum itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
}
