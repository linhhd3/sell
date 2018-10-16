<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<h2>Danh sach Gio Hang</h2>
	<hr />
	
	<table>
		<tr>
			<th>Ten SP</th>
			<th>Gia</th>
			<th>So Luong</th>
			<th>Chon</th>
		</tr>

		<c:forEach items="${order.itemDTOs }" var="orderDTO">
			<tr>
				<td>${orderDTO.productDTO.name }</td>
				<td>${orderDTO.productDTO.price }</td>
				<td>${orderDTO.number }</td>
				<td>
				<a href="<c:url value='/xoa-gio-hang/${orderDTO.productDTO.id }'/>" >Xoa</a>
				</td>
			</tr>
		</c:forEach>

	</table>
	
	<img src="<c:url value='/static/image/aodai.jpg' />" />

