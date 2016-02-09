<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
		<table id="main_table">
		<tr>
				<th>Model</th>
				<th>Vin</th>
				<th>Description</th>
				<th>Customer</th>
				<th>Action</th>
				<th>
				<form action="carAdd" method="POST">
					<input type="hidden" name="action" value="Add"> 
					<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					<input type="submit" value="Add car">
				</form>
				</th>
		</tr>
		<c:forEach var="item" items="${carcustomers}">
		<form action="carEdit" method="POST">
		<tr>
			<td><c:out value="${item.model}" /></td>
			<td><c:out value="${item.vin}" /></td>
			<td><c:out value="${item.description}" /></td>
			<td><c:out value="${item.f_name} ${item.l_name}" /></td>
			<td>
			<select name="action">
				<option name="edit">Edit</option>
				<option name="delete">Delete</option>
			</select>
			</td>
			<td>
				<input type="hidden" name="car_id" value="${item.id}">
				<input type="hidden" name="customer_id" value="${item.customer_id}">
				<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
				<input type="submit" value="Process">
			</td>
		</tr>
		</form>
		</c:forEach>
		</table>
	</jsp:attribute>
</t:general_template>