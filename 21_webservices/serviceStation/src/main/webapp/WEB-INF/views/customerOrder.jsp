<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>Service station</title>
		<link href="<c:url value="/resources/style/mainStyle.css" />" rel="stylesheet">
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
					<td>
						<c:out value="${order.orderId}" />
					</td>
					<td>
						<c:out value="${order.orderStatus.orderStatusName}" />
					</td>
					<td>
						<c:out value="${order.orderDescription}" />
					</td>
					<td>
						<c:out value="${order.car.carModel}" />
					</td>
					<td>
						<c:out value="${order.car.regNumber}" />
					</td>
					<td>
						<c:out value="${order.car.carDescription}" />
					</td>
					<td>
						<c:out value="${order.car.customer.lastName}"></c:out> 
						<c:out value=" ${order.car.customer.firstName}"></c:out>
					</td>
					<td>
						<c:out value="${order.datetimeStart}" />
					</td>
					<td>
						<c:choose>
							<c:when test="${empty order.datetimeFinish}">
								<c:out value="Is waiting" />
							</c:when>
							<c:when test="${not empty order.datetimeFinish}">
								<c:out value="${order.datetimeFinish}" />
							</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<br/>
		<br/>
		<br/>
		<form action="<c:url value="/logout"></c:url>" method="GET">
			<button type="submit" class="btn btn-primary btn-block btn-large" value="Logout">Logout</button>
		</form>
	</body>
</html>

