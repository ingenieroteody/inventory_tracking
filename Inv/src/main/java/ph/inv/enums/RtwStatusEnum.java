package ph.inv.enums;

public enum RtwStatusEnum {
	IN_STOCK("In Stock"),
	DELIVERED_ATC("Delivered at ATC"),
	DELIVERED_GLO("Delivered at Glorieta"),
	SOLD("Sold");
	
	private String value;
	
	private RtwStatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
