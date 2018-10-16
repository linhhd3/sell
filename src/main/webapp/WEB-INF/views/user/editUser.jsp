<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<p>Form nguoi dung</p>
<c:url value="/admin/sua-khach-hang" var="url" />
<form:form modelAttribute="user" method="post" action="${url}">
	<form:hidden path="id"/>
	<p>Ten: </p><form:input path="name"/>
	<p style="color: red"><form:errors path="name"></form:errors></p>
	<p>Phone: </p><form:input path="phone"/>
	<p style="color: red"><form:errors path="phone"></form:errors></p>
	
	<p></p><button type="submit">Submit</button>
</form:form>
<hr>
<img src="<c:url value='/static/image/aodai.jpg' />" />