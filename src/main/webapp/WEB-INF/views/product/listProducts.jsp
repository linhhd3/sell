<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<h2>Danh sach San Pham</h2>
	<hr />
	
	<table>
		<tr>
			<th>ID</th>
			<th>TEN</th>
			<th>Gia</th>
			<th>Chon</th>
		</tr>

		<c:forEach items="${products }" var="product">
			<tr>
				<td>${product.id }</td>
				<td>${product.name }</td>
				<td>${product.price }</td>
				<td><a href="<c:url value='/chi-tiet-san-pham/${product.id }'/>" >Chi Tiet</a>
				<a href="<c:url value='/xoa-san-pham/${product.id }'/>" >Xoa</a>
				<a href="<c:url value='/sua-san-pham/${product.id }'/>" >Sua</a>
				<a href="<c:url value='/them-gio-hang/${product.id }'/>" >Add to Cart</a>
				</td>
			</tr>
		</c:forEach>

	</table>
	
	<img src="<c:url value='/static/image/aodai.jpg' />" />

