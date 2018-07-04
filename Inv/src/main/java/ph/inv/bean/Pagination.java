package ph.inv.bean;
import java.util.List;

/**
 * Bootgrid is no longer in use
 * @author TIngeniero
 *
 * @param <T>
 */
@Deprecated
public class Pagination<T> {

	private int current;
	
	private int rowCount;
	
	private List<T> rows;

	private int total;

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		
		int page = (current - 1) * (rowCount);
		int indexEnd = (rowCount * current);//+(current-1)
		int pageTo = indexEnd;
		
		if((rows.size() - indexEnd) < 0) 
			pageTo = indexEnd - Math.abs(rows.size() - indexEnd);
		
		
		//System.out.println("IndexStart: " + page + " IndexEnd: " + pageTo + " RowCount: " + rowCount + " IF: " + (rows.size() - indexEnd));
		
		this.total = rows.size();
		this.rows = rows.subList(page, pageTo);
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
