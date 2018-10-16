<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>Thong tin nguoi dung</p>
<p>Id: ${product.id }</p>
<p>Ten: ${product.name }</p>
<p>Gia: ${product.price }</p>
<p><img  src="<c:url value='/files/${product.imgeUrl }' />" /></p>

<img src="<c:url value='/static/image/aodai.jpg' />" />