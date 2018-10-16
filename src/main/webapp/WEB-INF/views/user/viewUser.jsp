<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>Thong tin nguoi dung</p>
<p>Id: ${user.id }</p>
<p>Ten: ${user.name }</p>
<p>Phone: ${user.phone }</p>

<img src="<c:url value='/static/image/aodai.jpg' />" />