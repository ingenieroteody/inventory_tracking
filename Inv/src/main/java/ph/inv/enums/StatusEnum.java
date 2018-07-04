package ph.inv.enums;

public enum StatusEnum {
	IN_STOCK("In Stock"),
	DELIVERED_ATC("Delivered at ATC"),
	DELIVERED_GLO("Delivered at Glorieta"),
	SOLD("Sold");
	
	private String value;
	
	private StatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
