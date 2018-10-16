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
<p>Thong tin file</p>
<c:forEach items="${files }" var="file">
<p>File: ${file.getOriginalFilename() }</p>
</c:forEach>
<img src="<c:url value='/static/image/aodai.jpg' />" />
</body>
</html>