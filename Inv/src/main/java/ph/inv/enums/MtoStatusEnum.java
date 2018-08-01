package ph.inv.enums;

public enum MtoStatusEnum {
	ON_GOING("On going"),
	DONE("Done"),
	DELIVERED("Delivered");
	
	private String value;
	
	private MtoStatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
