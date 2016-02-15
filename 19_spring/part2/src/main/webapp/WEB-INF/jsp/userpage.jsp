<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:common_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
			<form action="<c:url value="/logout" />" method="POST" class="bye">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <input type="submit" value="Logout"
				name="signoff">
		</form>
	<table>
		<tr>
			<th>Order_id</th>
			<th>Car</th>
			<th>Vin</th>
			<th>Status</th>
			<th>Description</th>
		</tr>
		<c:forEach var="item" items="${oiwcs}">
			<tr>
				<td><c:out value="${item.id}" /></td>
				<td><c:out value="${item.model}" /></td>
				<td><c:out value="${item.vin}" /></td>
				<td><c:out value="${item.orderStatusName}" /></td>
				<td><c:out value="${item.description}" /></td>
			</tr>
		</c:forEach>
	</table>
	</jsp:attribute>
</t:common_template>