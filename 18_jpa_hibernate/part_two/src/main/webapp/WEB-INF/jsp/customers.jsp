<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
		<table>
		<tr>
				<th>First name</th>
				<th>Last Name</th>
				<th>Phone</th>
				<th>Action</th>
				<th>
				<form action="customerAdd" method="POST">
					<input type="hidden" name="action" value="Add"> 
					<input type="submit" value="Add customer">
				</form>
				</th>
		</tr>
		<c:forEach var="item" items="${customers}">
		<form action="customerEdit" method="POST">
		<tr>
			<td>
				<c:out value="${item.f_name}" />
			</td>
			<td><c:out value="${item.l_name}" /></td>
			<td><c:out value="${item.phone}" /></td>
			<td>
			<select name="action">
				<option name="edit">Edit</option>
				<option name="delete">Delete</option>
			</select>
			</td>
			<td>
				<input type="hidden" name="customer_id" value="${item.customerId}">
				<input type="submit" value="Process">
			</td>
		</tr>
		</form>
		</c:forEach>
		</table>
	</jsp:attribute>
</t:general_template>