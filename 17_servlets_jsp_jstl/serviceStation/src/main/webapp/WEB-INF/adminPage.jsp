<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="Admin Page">
	<jsp:attribute name="content_area">
		<div align="center">
		<h1>All Orders</h1>
	</div>
<header><h1 class="error"><c:out value="${message}" /></h1></header>

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
		<th>Action</th>
	</tr>
	<c:forEach var="order" items="${orders}">
		<tr>
			<td><c:out value="${order.orderId}"/></td>
			<td><c:out value="${order.orderStatus}" /></td>
			<td><c:out value="${order.oredrInfo}" /></td>
			<td><c:out value="${order.carModel}" /></td>
			<td><c:out value="${order.carRegNumber}" /></td>
			<td><c:out value="${order.carDescription}" /></td>
			<td><c:out value="${order.customerFullName}"></c:out></td>
			<td><c:out value="${order.orderStartTime}" /></td>
			<td><c:choose>
					<c:when test="${empty order.orderFinishTime}">
						<c:out value="Is waiting" />
					</c:when>
					<c:when test="${not empty order.orderFinishTime}">
						<c:out value="${order.orderFinishTime}" />
					</c:when>
				</c:choose></td>
			<td>
				<form method="post">
					<input type="hidden" name="order_id" value="${order.orderId}"/>
					<input type="hidden" name="order_id" value="${order.oredrInfo}"/>
					<input hidden="hidden" name="fieldName" value="order"/>
					<input type="submit" name="action" formaction="deleteField" value="Delete"/>
					<input type="submit" name="target" formaction="updateField" value="Edit order"/>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<form action="addNew" method="get"><div style="width: 50%; margin: 0 auto; text-align: center;">
			<button type="submit" class="btn btn-primary btn-block btn-large"
				name="target" value="order">Add new order</button></div>
		</form>
		</jsp:attribute>
</t:mainTemplate>
