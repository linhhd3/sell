<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<p>Form San Pham</p>
<c:url value="/them-san-pham" var="url" />
<form:form modelAttribute="product" method="post" action="${url}" enctype="multipart/form-data" >
	<p><spring:message code="product.name"/> </p><form:input path="name"/>
	<p style="color: red"><form:errors path="name"></form:errors></p>
	<p><spring:message code="product.price"/></p><form:input path="price"/>
	<p style="color: red"><form:errors path="price"></form:errors></p>
	<form:input path="file" type="file"/>
	<form:hidden path="id"/>
	
	<p></p><button type="submit">Submit</button>
</form:form>
<hr>
<img src="<c:url value='/static/image/aodai.jpg' />" />
