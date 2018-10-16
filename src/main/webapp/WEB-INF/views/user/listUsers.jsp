<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<h2>Danh sach nguoi dung</h2>
	<hr />
	<table>
		<tr>
			<th>ID</th>
			<th>TEN</th>
			<th>So Dien Thoai</th>
			<th>Chon</th>
		</tr>

		<c:forEach items="${users }" var="user">
			<tr>
				<td>${user.id }</td>
				<td>${user.name }</td>
				<td>${user.phone }</td>
				<td><a href="<c:url value='/admin/chi-tiet-khach-hang/${user.id }'/>" >Chi Tiet</a>
				<a href="<c:url value='/admin/xoa-khach-hang/${user.id }'/>" >Xoa</a>
				<a href="<c:url value='/admin/sua-khach-hang/${user.id }'/>" >Sua</a>
				</td>
			</tr>
		</c:forEach>

	</table>
	
	<img src="<c:url value='/static/image/aodai.jpg' />" />

