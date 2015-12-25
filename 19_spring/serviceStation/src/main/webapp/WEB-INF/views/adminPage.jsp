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
			<td><c:out value="${order.order_id}"/></td>
			<td><c:out value="${order.order_status.order_status_name}" /></td>
			<td><c:out value="${order.order_description}" /></td>
			<td><c:out value="${order.car.car_model}" /></td>
			<td><c:out value="${order.car.reg_number}" /></td>
			<td><c:out value="${order.car.car_description}" /></td>
			<td><c:out value="${order.car.customer.last_name}"></c:out> <c:out value=" ${order.car.customer.first_name}"></c:out></td>
			<td><c:out value="${order.datetime_start}" /></td>
			<td><c:choose>
					<c:when test="${empty order.datetime_finish}">
						<c:out value="Is waiting" />
					</c:when>
					<c:when test="${not empty order.datetime_finish}">
						<c:out value="${order.datetime_finish}" />
					</c:when>
				</c:choose></td>
			<td>
				<form method="post">
					<input type="hidden" name="order_id" value="${order.order_id}"/>
					<%-- <input type="hidden" name="order_id" value="${order.oredrInfo}"/> --%>
					<input hidden="hidden" name="fieldName" value="order"/>
					<input type="submit" name="action" formaction="<c:url value="/admin/deleteOrder"></c:url>" value="Delete"/>
					<input type="submit" name="target" formaction="<c:url value="/admin/updateOrder"></c:url>" value="Edit order"/>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<form action="<c:url value="/admin/addNewOrder"></c:url>" method="get"><div style="width: 50%; margin: 0 auto; text-align: center;">
			<button type="submit" class="btn btn-primary btn-block btn-large">Add new order</button></div>
		</form>
		</jsp:attribute>
</t:mainTemplate>
