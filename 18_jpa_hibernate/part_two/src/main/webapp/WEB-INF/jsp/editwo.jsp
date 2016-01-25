<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
		<table>
		<form action="orderWorkerEdit" method="POST">
		<tr>
			<td>Order ID:</td>
			<td>
				<c:out value="${order_id}" />
			</td>
		</tr>
		<tr>
			<td>Worker name:</td>
			<td>
				<c:if test="${action == 'Add'}">
					<select name="worker_id">
						<c:forEach var="item" items="${workers}">
							<option value="${item.workerId}" <c:if test="${worker.workerId eq item.workerId}">selected</c:if>><c:out value="${item.f_name} ${item.l_name}" /></option>
						</c:forEach>
					</select>
				</c:if>
				<c:if test="${action == 'Edit'}">
					<c:out value="${worker.f_name} ${worker.l_name}" />
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Completed:</td>
			<td>
				<select name="completed">
					<option value="false"
								<c:if test="${worker.completed == 'false'}">selected</c:if>>N</option>
					<option value="true"
								<c:if test="${worker.completed == 'true'}">selected</c:if>>Y</option>
				</select>
			</td>
		</tr>
		
		
		<tr>
			<td colspan=2 align="right">
				<input type="hidden" name="action" value="Save">
				<input type="hidden" name="order_id" value="${order_id}">
				<input type="hidden" name="worker_id" value="${worker.workerId}">
				<input type="submit" value="Save">
			</td>
		</tr>
		</form>
		</table>
</jsp:attribute>
</t:general_template>