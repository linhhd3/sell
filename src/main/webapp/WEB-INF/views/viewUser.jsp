<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="<c:url value='/static/css/style.css' />">
<script type="text/javascript" src="<c:url value='/static/js/main.js' />" ></script>
</head>
<body>
<p>Thong tin nguoi dung</p>
<p>Ten: ${user.name }</p>
<p>Pass: ${user.password }</p>
<p>Id: ${user.id }</p>
<p>Gioi tinh: ${user.gender }</p>
<p>Gioi thieu: ${user.about }</p>
<p>Agreement: ${user.acceptAgreement }</p>
<p>So thich</p>
<c:forEach items="${ user.favourites}" var="item">
<p>${item}</p>
</c:forEach>
<p>File: ${user.avatar.getOriginalFilename() }</p>
<img src="<c:url value='/static/image/aodai.jpg' />" />
</body>
</html>