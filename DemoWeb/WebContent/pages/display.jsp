<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/table.css" />
<title>Display</title>
</head>
<body>

<h3>Select Product Table Result : XXX row(s) selected</h3>

<c:if test="${not empty select}">
<table>
	<thead>
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Price</th>
		<th>Make</th>
		<th>Expire</th>
	</tr>
	</thead>
	<tbody>
<c:forEach var="element" items="${select}">
	<tr>
		<td>${element.id}</td>
		<td>${element.name}</td>
		<td>${element.price}</td>
		<td>${element.make}</td>
		<td>${element.expire}</td>
	</tr>
</c:forEach>
	</tbody>
</table>
</c:if>
<h3><a href="product.jsp">Product Table</a></h3>
</body>
</html>