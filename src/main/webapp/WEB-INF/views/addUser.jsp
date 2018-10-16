<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="<c:url value='/static/css/style.css' />">
<script type="text/javascript" src="<c:url value='/static/js/main.js' />" ></script>
</head>
<body>
<p>Form nguoi dung</p>
<c:url value="/them-user" var="url" />
<form:form modelAttribute="user" method="post" action="${url}" enctype="multipart/form-data">
	<p>Ten: </p><form:input path="name"/>
	<p style="color: red"><form:errors path="name"></form:errors></p>
	<p>Pass: </p><form:password path="password"/>
	<p style="color: red"><form:errors path="password"></form:errors></p>
	<form:hidden path="id"/>
	<p>So thich: </p><form:checkboxes items="${list }" path="favourites"/>
	<p>Gioi tinh</p><form:select path="gender">
		<form:option value="Nam">Nam</form:option>
		<form:option value="Nu">Nu</form:option>
	</form:select>
	<p>Gioi thieu</p><form:textarea path="about"/>
	<p></p><form:radiobutton path="acceptAgreement" value="true" label="Dong y"/><br/>
	<form:input path="avatar" type="file"/>
	<p></p><button type="submit">Submit</button>
</form:form>
<hr>
<img src="<c:url value='/static/image/aodai.jpg' />" />
</body>
</html>