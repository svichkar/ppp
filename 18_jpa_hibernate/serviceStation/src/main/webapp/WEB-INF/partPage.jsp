<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="Parts Page">
	<jsp:attribute name="content_area">
		<div align="center">
		<h1>All Parts</h1>
	</div>
<table class="data">
	<tr>
		<th># Part</th>
		<th>Part name</th>
		<th>Vendor</th>
		<th>Amount</th>
		<th>Action</th>
	</tr>
	<c:forEach var="part" items="${partList}">
		<tr>
			<td><c:out value="${part.part_id}" /></td>
			<td><c:out value="${part.part_name}" /></td>
			<td><c:out value="${part.vendor}" /></td>
			<td><c:out value="${part.amount}" /></td>
			<td>
				<form method="post">
					<input type="hidden" name="part_id" value="${part.part_id}"></input>
					<input hidden="hidden" name="fieldName" value="part"/>
					<input type="submit" name="action" formaction="deleteField" value="Delete"/>
					<input type="submit" name="target" formaction="updateField" value="Edit Part"/>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<form action="addNew" method="get"><div style="width: 50%; margin: 0 auto; text-align: center;">
			<input hidden="hidden" name="homePage" value ="partPage">
			<button type="submit" class="btn btn-primary btn-block btn-large"
				name="target" value="part">Add new part</button></div>
		</form>

		</jsp:attribute>
</t:mainTemplate>
