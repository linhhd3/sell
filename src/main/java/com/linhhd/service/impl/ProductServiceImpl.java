package com.linhhd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linhhd.dao.ProductDao;
import com.linhhd.entity.Product;
import com.linhhd.model.ProductDTO;
import com.linhhd.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao productDao;

	public void addProduct(ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setImgeUrl(productDTO.getImgeUrl());
		productDao.addProduct(product);

	}

	public void updateProduct(ProductDTO productDTO) {
		Product product = productDao.getProductById(productDTO.getId());
		if (product != null) {
			product.setName(productDTO.getName());
			product.setPrice(productDTO.getPrice());
			product.setImgeUrl(productDTO.getImgeUrl());
			productDao.updateProduct(product);
		}

	}

	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		Product product = productDao.getProductById(id);
		if (product != null) {
			productDao.deleteProduct(id);
		}


	}

	public ProductDTO getProductById(int id) {
		Product product = productDao.getProductById(id);
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setImgeUrl(product.getImgeUrl());

		return productDTO;
	}

	public List<ProductDTO> getAllProduct() {
		List<Product> products = productDao.getAllProduct();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Product product : products) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(product.getId());
			productDTO.setName(product.getName());
			productDTO.setPrice(product.getPrice());

			productDTOs.add(productDTO);
		}
		return productDTOs;
	}

}
