package com.mphasis.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Table
@Entity(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String image;
	@Size(min = 1, max = 40, message = "Name must be 1-40 characters")
	private String name;
	@Size(min = 1, max = 40, message = "Brand must be 1-40 characters")
	private String brand;
	@Size(min = 1, max = 40, message = "Description must be 1-40 characters")
	private String description;
	@Range(min = 1, message = "Quantity available cannot be less than 1")
	private long qtyAvailable;
	@Range(min = 0, message = "Price cannot be less than 0.00")
	private double price;
	private boolean activate;
	
	public Product() {
		this.image = "/images/products/" + this.id + ".jpg";
		this.activate = false;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public long getQtyAvailable() {
		return qtyAvailable;
	}
	
	public void setQtyAvailable(long qtyAvailable) {
		this.qtyAvailable = qtyAvailable;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean isActivate() {
		return activate;
	}
	
	public void setActivate(boolean activate) {
		this.activate = activate;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", image=" + image + ", name=" + name + ", brand=" + brand + ", qtyAvailable="
				+ qtyAvailable + ", price=" + price + ", activate=" + activate + "]";
	}
	
}

