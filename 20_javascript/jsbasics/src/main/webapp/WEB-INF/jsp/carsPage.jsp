<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:showTemplate title="Cars">
	<jsp:attribute name="content_area">
		<div align="center">
		<h1>All Cars</h1>
	</div>
	<table align="center" width=70% border=1 cellpadding=8>
		<tr height=40>
			<td width=5%><b>Car ID</b></td>
			<td width=20%><b>Car Model</b></td>
			<td width=15%><b>Car VIN</b></td>
			<td width=25%><b>Car Description</b></td>
			<td width=15%><b>Customer Name</b></td>
			<td width=20%><b>Action</b></td>
		</tr>
		<c:forEach var="item" items="${cars}">
			<tr>
				<td><c:out value="${item.carId}" /></td>
				<td><c:out value="${item.carModel}" /></td>
				<td><c:out value="${item.carVin}" /></td>
				<td><c:out value="${item.carDescription}" /></td>
				<td><c:out value="${item.customerName}" /></td>
				<td>
					<form method="POST">
						<input type="hidden" name="car_id" value="${item.carId}">
						<input type="hidden" name="target" value="Cars" />
						<input type="submit" name="action" formaction="<c:url value="/admin/editCar.do"></c:url>" value="Edit" class="input_edit">
						<input type="submit" name="action" formaction="<c:url value="/admin/deleteCar.do"></c:url>" value="Delete" class="input_edit">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan=6>
				<div align="center">
					<form action="<c:url value="/admin/addCar.do"></c:url>" method="GET">
						<input type="submit" value="Add new car" class="input_add">
					</form>
				</div>
			</td>
		</tr>
	</table>
	</jsp:attribute>
</t:showTemplate>