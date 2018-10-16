<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<security:authorize access="isAuthenticated()">
	<security:authentication property="principal" var="user"/>
	Welcome, ${user.username}
	<a href="<c:url value='/dang-xuat'/>">Thoat</a><br/>
</security:authorize>


<a href="<c:url value='/admin/them-khach-hang'/>">Them khach hang</a><br/>
<a href="<c:url value='/danh-sach-san-pham'/>">DS san pham</a><br/>
<a href="<c:url value='/them-san-pham'/>">Them San Pham</a><br/>
<a href="<c:url value='/admin/danh-sach-khach-hang'/>">DS Khach Hang</a><br/>
<a href="<c:url value='/xem-gio-hang'/>">Xem Gio Hang</a><br/>

</a>