<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:showTemplate title="Customers">
	<jsp:attribute name="content_area">
		<div align="center">
		<h1>All Customers</h1>
	</div>
	<table align="center" width=70% border=1 cellpadding=8>
		<tr height=40>
			<td width=10%><b>Customer ID</b></td>
			<td width=25%><b>First Name</b></td>
			<td width=25%><b>Last Name</b></td>
			<td width=20%><b>Phone</b></td>
			<td width=20%><b>Action</b></td>
		</tr>
		<c:forEach var="item" items="${customers}">
			<tr>
				<td><c:out value="${item.customerId}" /></td>
				<td><c:out value="${item.firstName}" /></td>
				<td><c:out value="${item.lastName}" /></td>
				<td><c:out value="${item.phone}" /></td>
				<td>
					<form method="POST">
						<input type="hidden" name="customer_id" value="${item.customerId}">
						<input type="hidden" name="target" value="Customers" />
						<input type="submit" name="action" formaction="<c:url value="/admin/editCustomer.do"></c:url>" value="Edit" class="input_edit">
						<input type="submit" name="action" formaction="<c:url value="/admin/deleteCustomer.do"></c:url>" value="Delete" class="input_edit">
					</form>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan=5>
				<div align="center">
					<form action="<c:url value="/admin/addCustomer.do"></c:url>" method="GET">
						<input type="submit" value="Add new Customer" class="input_add">
					</form>
				</div>
			</td>
		</tr>
	</table>
	</jsp:attribute>
</t:showTemplate>