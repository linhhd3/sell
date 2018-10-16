package com.linhhd.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.linhhd.model.OrderDTO;
import com.linhhd.model.OrderItemDTO;
import com.linhhd.model.ProductDTO;
import com.linhhd.service.ProductService;

@Controller
public class OrderController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/them-gio-hang/{productId}", method=RequestMethod.GET)
	public String addToCart(HttpServletRequest request,
			@PathVariable(value="productId") int productId,
			HttpSession session){
		ProductDTO productDTO = productService.getProductById(productId);
		if(session.getAttribute("cart") == null){
			OrderDTO orderDTO = new OrderDTO();
			OrderItemDTO orderItemDTO = new OrderItemDTO();
			orderItemDTO.setNumber(1);
			orderItemDTO.setProductDTO(productDTO);
			
			List<OrderItemDTO> oderItemDTOs = new ArrayList<OrderItemDTO>();
			oderItemDTOs.add(orderItemDTO);
			orderDTO.setItemDTOs(oderItemDTOs);
			
			session.setAttribute("cart", orderDTO);
			request.setAttribute("order", orderDTO);
		} else {
			OrderDTO orderDTO = (OrderDTO) session.getAttribute("cart");
			List<OrderItemDTO> oderItemDTOs = orderDTO.getItemDTOs();
			boolean flag = false;
			for(OrderItemDTO orderItemDTO : oderItemDTOs){
				if(orderItemDTO.getProductDTO().getId() == productDTO.getId()){
					orderItemDTO.setNumber(orderItemDTO.getNumber() + 1);
					flag = true;
				}
			}
			if(!flag){
				OrderItemDTO orderItemDTO = new OrderItemDTO();
				orderItemDTO.setNumber(1);
				orderItemDTO.setProductDTO(productDTO);
				oderItemDTOs.add(orderItemDTO);
			}
			
			session.setAttribute("cart", orderDTO);	
			request.setAttribute("order", orderDTO);
		}
		
		return "cart";
	}
	
	@RequestMapping(value="/xem-gio-hang", method=RequestMethod.GET)
	public String viewCart(HttpServletRequest request,
			HttpSession session){
		if(session.getAttribute("cart") != null){
			OrderDTO orderDTO = (OrderDTO) session.getAttribute("cart");
			request.setAttribute("order", orderDTO);
		}
		
		return "cart";
	}
	
	@RequestMapping(value="/xoa-gio-hang/{productId}", method=RequestMethod.GET)
	public String deleteCart(HttpServletRequest request,
			@PathVariable(value="productId") int productId,
			HttpSession session){
		if(session.getAttribute("cart") != null){
			OrderDTO orderDTO = (OrderDTO) session.getAttribute("cart");
			List<OrderItemDTO> oderItemDTOs = orderDTO.getItemDTOs();
			
			Iterator<OrderItemDTO> iterator = oderItemDTOs.iterator();
			while(iterator.hasNext()){
				if(iterator.next().getProductDTO().getId() == productId){
					iterator.remove();
				}
			}
			
			if (oderItemDTOs.isEmpty()){
				session.removeAttribute("cart");
			}
			
			request.setAttribute("order", orderDTO);
		}
		
		return "cart";
	}
}
