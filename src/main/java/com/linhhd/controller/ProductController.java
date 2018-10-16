package com.linhhd.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.linhhd.model.ProductDTO;
import com.linhhd.service.ProductService;
import com.linhhd.service.UserService;
import com.linhhd.validator.UserValidator;

@Controller
public class ProductController {
	
	private static Logger logger = Logger.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	@Autowired
	UserValidator validator;
	
	@RequestMapping(value="/danh-sach-san-pham", method=RequestMethod.GET)
	public String getAllProduct(HttpServletRequest request){
		List<ProductDTO> products = productService.getAllProduct();
		request.setAttribute("products", products);
		
		return "listProducts";
	}
	
	@RequestMapping(value="/chi-tiet-san-pham/{productId}", method=RequestMethod.GET)
	public String viewProduct(HttpServletRequest request,
			@PathVariable(value="productId")int productId){
		
		ProductDTO product = productService.getProductById(productId);
		request.setAttribute("product", product);
		
		return "viewProduct";
	}
	
	@RequestMapping(value="/them-san-pham", method=RequestMethod.GET)
	public String addProduct(HttpServletRequest request){
		
		request.setAttribute("product", new ProductDTO());
		
		return "addProduct";
	}
	
	@RequestMapping(value="/them-san-pham", method=RequestMethod.POST)
	public String addProduct(HttpServletRequest request, 
			@ModelAttribute(value="product") ProductDTO productDTO){
		
		MultipartFile multipartFile = productDTO.getFile();
		try {
			File file = new File("C:/Uploads/" + multipartFile.getOriginalFilename());
			FileOutputStream fileOutputStream;
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(multipartFile.getBytes());
			fileOutputStream.close();
			productDTO.setImgeUrl(multipartFile.getOriginalFilename());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		productService.addProduct(productDTO);
		
		return "redirect:/danh-sach-san-pham";
	}
	
	@RequestMapping(value="/xoa-san-pham/{productId}", method=RequestMethod.GET)
	public String deleteProduct(HttpServletRequest request, 
			@PathVariable(value="productId") int productId){
		productService.deleteProduct(productId);
		
		return "redirect:/danh-sach-san-pham";
	}
	
	@RequestMapping(value="/sua-san-pham/{productId}", method=RequestMethod.GET)
	public String editProduct(HttpServletRequest request, 
			@PathVariable(value="productId") int productId){
		ProductDTO productDTO = productService.getProductById(productId);
		request.setAttribute("product", productDTO);
		
		return "editProduct";
	}
	
	@RequestMapping(value="/sua-san-pham", method=RequestMethod.POST)
	public String editProduct(HttpServletRequest request, 
			@ModelAttribute(value="product") ProductDTO productDTO){
		
		MultipartFile multipartFile = productDTO.getFile();
		try {
			File file = new File("C:/Uploads/" + multipartFile.getOriginalFilename());
			FileOutputStream fileOutputStream;
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(multipartFile.getBytes());
			fileOutputStream.close();
			productDTO.setImgeUrl(multipartFile.getOriginalFilename());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		productService.updateProduct(productDTO);
		
		return "redirect:/danh-sach-san-pham";
	}
}
