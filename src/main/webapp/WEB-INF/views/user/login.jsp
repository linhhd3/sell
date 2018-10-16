<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value='/login' var="url"/>

<form action="${url }" method="post">
<input name="username" type="text" placeholder="Username" /><br/>
<input name="password" type="password" placeholder="Password" />
<button type="submit">Login</button>
</form>

<img src="<c:url value='/static/image/aodai.jpg' />" />