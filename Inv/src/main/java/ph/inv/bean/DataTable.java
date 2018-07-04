package ph.inv.bean;

import java.util.List;

public class DataTable<T> {

	private List<List<T>> data;
	
	public DataTable() {
	}
	
	public List<List<T>> getData() {
		return data;
	}

	public void setData(List<List<T>> data) {
		this.data = data;
	}
}
