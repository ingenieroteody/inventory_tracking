package ph.inv.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import ph.inv.annotation.Searchable;
import ph.inv.enums.MtoStatusEnum;
import ph.inv.enums.RtwStatusEnum;
import ph.inv.enums.SizeEnum;

@Entity
@Audited
@Table(name="mto")
public class Mto extends BaseEntity {

	@Column(name="order_date", nullable=false, columnDefinition="VARCHAR(10)")
	private String orderDate;
	
	@Column(name="pickup_date", nullable=false, columnDefinition="VARCHAR(10)")
	private String pickupDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", nullable=false)
	private Employee employee;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", length=25, nullable=false)
	private MtoStatusEnum status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="color_id", nullable=false)
	@Searchable
	private Color color;
	
	@Enumerated(EnumType.STRING)
	@Column(name="size", length=25, nullable=false)
	private SizeEnum size;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id", nullable=false)
	private Product product;
	
	@Column(name="price")
	private BigDecimal price;

	@Transient
	private String productName;
	
	public Mto() {
		this.status = MtoStatusEnum.ON_GOING;
	}
	
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public MtoStatusEnum getStatus() {
		return status;
	}

	public void setStatus(MtoStatusEnum status) {
		this.status = status;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public SizeEnum getSize() {
		return size;
	}

	public void setSize(SizeEnum size) {
		this.size = size;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String getProductName() {
		if(getId() != 0) return product.getName();
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
