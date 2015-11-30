<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>User Page</title>
</head>
<body bgcolor="#F0FFFF">
	<div align="center">
		<h1>User's Orders</h1>
	</div>
	<table align="center" width=50% border=1 cellpadding=8>
		<tr height=40>
			<td width=5%><b>Order ID</b></td>
			<td width=10%><b>Order Status</b></td>
			<td width=20%><b>Order Description</b></td>
			<td width=10%><b>Car VIN</b></td>
			<td width=10%><b>Car Model</b></td>
			<td width=15%><b>Car Description</b></td>
			<td width=15%><b>Time Started</b></td>
			<td width=15%><b>Time Finished</b></td>
		</tr>
		<c:forEach var="item" items="${orders}">
			<tr>
				<td><c:out value="${item.orderId}" /></td>
				<td><c:out value="${item.orderStatus}" /></td>
				<td><c:out value="${item.description}" /></td>
				<td><c:out value="${item.carVin}" /></td>
				<td><c:out value="${item.carModel}" /></td>
				<td><c:out value="${item.carDescription}" /></td>
				<td><c:out value="${item.timestampStart}" /></td>
				<td><c:out value="${item.timestampFinish}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>