<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:showTemplate title="Parts">
	<jsp:attribute name="content_area">
		<div align="center">
		<h1>All Parts</h1>
	</div>
	<table align="center" width=70%  border=1 cellpadding=8>
		<tr height=40>
			<td width=10%><b>Part ID</b></td>
			<td width=30%><b>Part Name</b></td>
			<td width=20%><b>Vendor Name</b></td>
			<td width=20%><b>Amount</b></td>
			<td width=20%><b>Action</b></td>
		</tr>
		<c:forEach var="item" items="${parts}">
			<tr>
				<td><c:out value="${item.partId}" /></td>
				<td><c:out value="${item.partName}" /></td>
				<td><c:out value="${item.vendor}" /></td>
				<td><c:out value="${item.amount}" /></td>
				<td>
					<form method="POST">
						<input type="hidden" name="part_id" value="${item.partId}">
						<input type="hidden" name="target" value="Parts" />
						<input type="submit" name="action" formaction="<c:url value="/admin/editPart.do"></c:url>" value="Edit" class="input_edit">
						<input type="submit" name="action" formaction="<c:url value="/admin/deletePart.do"></c:url>" value="Delete" class="input_edit">
					</form>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan=5>
				<div align="center">
					<form action="<c:url value="/admin/addPart.do"></c:url>" method="GET">
						<input type="submit" value="Add new part" class="input_add">
					</form>
				</div>
			</td>
		</tr>
	</table>
	</jsp:attribute>
</t:showTemplate>