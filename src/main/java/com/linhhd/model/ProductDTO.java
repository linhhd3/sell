package com.linhhd.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * The persistent class for the product database table.
 * 
 */
public class ProductDTO {

	private int id;

	private String name;
	
	private String imgeUrl;

	private int price;
	
	private MultipartFile file;

	public ProductDTO() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImgeUrl() {
		return imgeUrl;
	}

	public void setImgeUrl(String imgeUrl) {
		this.imgeUrl = imgeUrl;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	

	
}