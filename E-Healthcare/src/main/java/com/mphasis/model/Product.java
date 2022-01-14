package com.mphasis.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String image;
	private String name;
	private String brand;
	private long qtyAvailable;
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

