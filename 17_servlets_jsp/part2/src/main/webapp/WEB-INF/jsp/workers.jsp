<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
		<table>
		<tr>
				<th>First name</th>
				<th>Last name</th>
				<th>Specification</th>
				<th>Status</th>
				<th>Action</th>
				<th>
				<form action="workerAdd" method="POST">
					<input type="hidden" name="action" value="Add"> 
					<input type="submit" value="Add worker">
				</form>
				</th>
		</tr>
		<c:forEach var="item" items="${workers}">
		<form action="workerEdit" method="POST">
		<tr>
			<td><c:out value="${item.f_name}" /></td>
			<td><c:out value="${item.l_name}" /></td>
			<td><c:out value="${item.spec_name}" /></td>
			<td><c:out value="${item.status_name}" /></td>
			<td>
			<select name="action">
				<option name="edit">Edit</option>
				<option name="delete">Delete</option>
			</select>
			</td>
			<td>
				<input type="hidden" name="worker_id" value="${item.id}">
				<input type="submit" value="Process">
			</td>
		</tr>
		</form>
		</c:forEach>
		</table>
	</jsp:attribute>
</t:general_template>