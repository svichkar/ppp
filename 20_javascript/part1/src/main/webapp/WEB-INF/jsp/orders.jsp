<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
	<table id="main_table">
		<tr>
				<th>Order id</th>
				<th>Status</th>
				<th>Car</th>
				<th>Vin</th>
				<th>Description</th>
				<th>Time started</th>
				<th>Time finished</th>
				<th>Action</th>
				<th>
				<form action="orderAdd" method="POST">
					<input type="hidden" name="action" value="Add"> 
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="submit" value="Add order">
				</form>
				</th>
		</tr>
		<c:forEach var="item" items="${oiwcs}">
		<form action="orderEdit" method="POST">
		<tr>
			<td><c:out value="${item.id}" /></td>
			<td><c:out value="${item.order_status_name}" /></td>
			<td><c:out value="${item.model}" /></td>
			<td><c:out value="${item.vin}" /></td>
			<td><c:out value="${item.description}" /></td>
			<td><c:out value="${item.datetime_start}" /></td>
			<td><c:out value="${item.datetime_end}" /></td>
			<td>
			<select name="action">
				<option name="edit">Edit</option>
				<option name="delete">Delete</option>
			</select>
			</td>
			<td>
				<input type="hidden" name="order_id" value="${item.id}">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="submit" value="Process">
			</td>
		</tr>
		</form>
		</c:forEach>
		</table>
	
	</jsp:attribute>
</t:general_template>