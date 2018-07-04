package ph.inv.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import ph.inv.annotation.Searchable;
import ph.inv.enums.SizeEnum;
import ph.inv.enums.StatusEnum;

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
	
	@Enumerated(EnumType.STRING)
	@Column(name="size", length=25, nullable=false)
	private SizeEnum size;
	
	@Column(name="quantity", nullable=false)
	private Integer quantity;

	@Enumerated(EnumType.STRING)
	@Column(name="status", length=25, nullable=false)
	private StatusEnum status;
	
	@Transient
	private String productName;
	
	public Inventory() {
		this.status = StatusEnum.IN_STOCK;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public SizeEnum getSize() {
		return size;
	}

	public void setSize(SizeEnum size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public String getProductName() {
		if(getId() != 0) return product.getName();
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
