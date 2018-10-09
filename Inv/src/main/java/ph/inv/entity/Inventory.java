package ph.inv.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import ph.inv.annotation.Searchable;
import ph.inv.enums.RtwStatusEnum;
import ph.inv.enums.SizeEnum;

@Entity
@Audited
@Table(name="inventory")
public class Inventory extends BaseEntity{

	//@Temporal(TemporalType.DATE)
	@Column(name="date", nullable=false, columnDefinition="VARCHAR(10)")
	@Searchable
	private String date;

	@Column(name="number_code", unique=true, nullable=false, columnDefinition="VARCHAR(10)")
	@Searchable
	private String numberCode;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id", nullable=false)
	@Searchable
	private Product product;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="color_id", nullable=false)
	@Searchable
	private Color color;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", nullable=false)
	//@Searchable
	private Employee employee;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="size_id", nullable=false)
	private SystemCodes size;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id", nullable=false)
	private SystemCodes status;
	
	@Column(name="price", nullable=false)
	private Double price;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="inventory")
	private List<ItemMovement> itemMovements;
	
	@Transient
	private String productName;
	
	@Transient
	private String changeItemName;
	
	public Inventory() {
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getNumberCode() {
		return numberCode;
	}

	public void setNumberCode(String numberCode) {
		this.numberCode = numberCode;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public SystemCodes getSize() {
		return size;
	}

	public void setSize(SystemCodes size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public SystemCodes getStatus() {
		return status;
	}

	public void setStatus(SystemCodes status) {
		this.status = status;
	}

	public String getProductName() {
		if(getId() != 0) return product.getName();
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<ItemMovement> getItemMovements() {
		return itemMovements;
	}

	public void setItemMovements(List<ItemMovement> itemMovements) {
		this.itemMovements = itemMovements;
	}

	public String getChangeItemName() {
		return changeItemName;
	}

	public void setChangeItemName(String changeItemName) {
		this.changeItemName = changeItemName;
	}
	
}
