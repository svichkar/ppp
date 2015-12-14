<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:showTemplate title="Orders">
	<jsp:attribute name="content_area">
		<div align="center">
		<h1>All Orders</h1>
	</div>
	<table align="center" width=80% border=1 cellpadding=8>
		<tr height=40>
			<td width=5%><b>Order ID</b></td>
			<td width=8%><b>Order Status</b></td>
			<td width=17%><b>Order Description</b></td>
			<td width=9><b>Car VIN</b></td>
			<td width=8%><b>Car Model</b></td>
			<td width=13%><b>Car Description</b></td>
			<td width=15%><b>Time Started</b></td>
			<td width=15%><b>Time Finished</b></td>
			<td width=10%><b>Action</b></td>
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
				<td>
					<form method="POST">
						<input type="hidden" name="order_id" value="${item.orderId}">
						<input type="hidden" name="target" value="Orders" />
						<input type="submit" name="action" formaction="editOrder.do" value="Edit" class="input_edit">
						<input type="submit" name="action" formaction="deleteOrder.do" value="Delete" class="input_edit">
					</form>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan=9>
				<div align="center">
					<form action="addOrder.do" method="GET">
						<input type="submit" value="Add new order" class="input_add">
					</form>
				</div>
			</td>
		</tr>
	</table>
	</jsp:attribute>
</t:showTemplate>