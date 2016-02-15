<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
		<table>
		<form action="orderPartEdit" name="formForValidation" method="POST">
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
							<option value="${item.partId}" <c:if test="${part.partId eq item.partId}">selected</c:if>><c:out value="${item.partName}" /></option>
						</c:forEach>
					</select>
				</c:if>
				<c:if test="${action == 'Edit'}">
					<c:out value="${part.partName}" />
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
				<c:if test="${action == 'Edit'}">
					<input type="hidden" name="part_id" value="${part.partId}">
				</c:if>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="submit" value="Save">
			</td>
		</tr>
		</form>
		</table>
		<div id="hint" />
</jsp:attribute>
</t:general_template>