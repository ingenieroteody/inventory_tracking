package ph.inv.util;

import java.util.List;

public class QueryTable {

	private String parentClass;
	
	private String tableName;
	
	private List<String> columnName;
	
	private List<String> columnType;
	
	private List<QueryTable> nestedTables;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<String> getColumnName() {
		return columnName;
	}

	public void setColumnName(List<String> columnName) {
		this.columnName = columnName;
	}

	public List<String> getColumnType() {
		return columnType;
	}

	public void setColumnType(List<String> columnType) {
		this.columnType = columnType;
	}

	public List<QueryTable> getNestedTables() {
		return nestedTables;
	}

	public void setNestedTables(List<QueryTable> nestedTables) {
		this.nestedTables = nestedTables;
	}

	public String getParentClass() {
		return parentClass;
	}

	public void setParentClass(String parentClass) {
		this.parentClass = parentClass;
	}
}
