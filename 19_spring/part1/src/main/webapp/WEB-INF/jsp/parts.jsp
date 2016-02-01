<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
		<table>
		<tr>
				<th>Part name</th>
				<th>Vendor</th>
				<th>Amount</th>
				<th>Action</th>
				<th>
				<form action="partAdd" method="POST">
					<input type="hidden" name="action" value="Add"> 
					<input type="submit" value="Add part">
				</form>
				</th>
		</tr>
		<c:forEach var="item" items="${parts}">
		<form action="partEdit" method="POST">
		<tr>
			<td><c:out value="${item.part_name}" /></td>
			<td><c:out value="${item.vendor}" /></td>
			<td><c:out value="${item.amount}" /></td>
			<td>
			<select name="action">
				<option name="edit">Edit</option>
				<option name="delete">Delete</option>
			</select>
			</td>
			<td>
				<input type="hidden" name="part_id" value="${item.partId}">
				<input type="submit" value="Process">
			</td>
		</tr>
		</form>
		</c:forEach>
		</table>
	</jsp:attribute>
</t:general_template>