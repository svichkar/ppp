<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
		<table>
		<form action="orderPartEdit" method="POST">
		<tr>
			<td>Order ID:</td>
			<td>
				<c:out value="${order_id}" />
			</td>
		</tr>
		<tr>
			<td>Part name:</td>
			<td>
				<c:if test="${action == 'Add'}">
					<select name="part_id">
						<c:forEach var="item" items="${parts}">
							<option value="${item.id}" <c:if test="${part.part_id eq item.id}">selected</c:if>><c:out value="${item.part_name}" /></option>
						</c:forEach>
					</select>
				</c:if>
				<c:if test="${action == 'Edit'}">
					<c:out value="${part.part_name}" />
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Vendor:</td>
			<td>
				<c:out value="${part.vendor}" />
			</td>
		</tr>
		<tr>
			<td>Amount:</td>
			<td>
				<input type="text" name="amount" value="${part.amount}"/>
			</td>
		</tr>
		<tr>
			<td colspan=2 align="right">
				<input type="hidden" name="action" value="Save">
				<input type="hidden" name="order_id" value="${order_id}">
				<input type="hidden" name="part_id" value="${part.part_id}">
				<input type="submit" value="Save">
			</td>
		</tr>
		</form>
		</table>
</jsp:attribute>
</t:general_template>