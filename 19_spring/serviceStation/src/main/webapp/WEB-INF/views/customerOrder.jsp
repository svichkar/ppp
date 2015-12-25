<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Service station</title>

<link href="<c:url value="/resources/style/mainStyle.css" />"
	rel="stylesheet">
<!-- <link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" /> -->
<!-- <link rel="stylesheet" type="text/css" href="style/loginStyle.css"> -->
</head>

<body>
	<header>
		<h1 class="error">
			<c:out value="${message}" />
		</h1>
	</header>

	<table class="data">
		<tr>
			<th># Order</th>
			<th>Order Status</th>
			<th>Order Info</th>
			<th>Car Model</th>
			<th>Car Registration Number</th>
			<th>Car info</th>
			<th>Car owner</th>
			<th>Order start time</th>
			<th>Order finish time</th>
		</tr>
		<c:forEach var="order" items="${orders}">
			<tr>
				<td><c:out value="${order.order_id}" /></td>
				<td><c:out value="${order.order_status.order_status_name}" /></td>
				<td><c:out value="${order.order_description}" /></td>
				<td><c:out value="${order.car.car_model}" /></td>
				<td><c:out value="${order.car.reg_number}" /></td>
				<td><c:out value="${order.car.car_description}" /></td>
				<td><c:out value="${order.car.customer.last_name}"></c:out> <c:out
						value=" ${order.car.customer.first_name}"></c:out></td>
				<td><c:out value="${order.datetime_start}" /></td>
				<td><c:choose>
						<c:when test="${empty order.datetime_finish}">
							<c:out value="Is waiting" />
						</c:when>
						<c:when test="${not empty order.datetime_finish}">
							<c:out value="${order.datetime_finish}" />
						</c:when>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<br/>
	<br/>
	<form action="<c:url value="/logout"></c:url>" method="GET">
		<button type="submit" class="btn btn-primary btn-block btn-large"
			value="Logout">Logout</button>
	</form>
</body>
</html>

