<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
		<table>
		<form action="partEdit" method="POST">
		<tr>
			<td>Part name:</td>
			<td>
				<input type="text" name="part_name" value="${part.part_name}" />
			</td>
		</tr>
		<tr>
			<td>Vendor:</td>
			<td>
				<input type="text" name="vendor" value="${part.vendor}" />
			</td>
		</tr>
		<tr>
			<td>Amount:</td>
			<td>
				<input type="text" name="amount" value="${part.amount}" />
			</td>
		</tr>
		<tr>
			<td colspan=2 align="right">
				<input type="hidden" name="action" value="Save">
				<input type="hidden" name="part_id" value="${part.id}">
				<input type="submit" value="Save">
			</td>
		</tr>
		</form>
		</table>
	</jsp:attribute>
</t:general_template>