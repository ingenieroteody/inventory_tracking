package ph.inv.enums;

public enum SizeEnum {
	XS("Extra Small"),
	S("Small"),
	M("Medium"),
	L("Large"),
	XL("Extract Large"),
	MTO("Customize");
	
	private String value;
	
	SizeEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
