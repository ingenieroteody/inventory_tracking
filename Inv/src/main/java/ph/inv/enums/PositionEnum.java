package ph.inv.enums;

public enum PositionEnum {
	SEWER("Sewer"),
	PROMO("Promo girl"),
	CUTTER("Cutter"),
	PATTERN_CUT_AND_SEW("Pattern, cutter and sewer");
	
	private String value;
	
	private PositionEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
