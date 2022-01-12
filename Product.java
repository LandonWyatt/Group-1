package com.mphasis.model;

public class Product {
	
		private int id;
		private String image;
		private String name;
		private String brand;
		private long qtyAvailable;
		private double price;
		private boolean activate;
		public Product(int id, String image, String name, String brand, long qtyAvailable, double price, boolean activate) {
			super();
			this.id = id;
			this.image = image;
			this.name = name;
			this.brand = brand;
			this.qtyAvailable = qtyAvailable;
			this.price = price;
			this.activate = activate;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
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

