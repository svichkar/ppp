<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
		<table>
		<form action="workerEdit" name="formForValidation" method="POST">
		<tr>
			<td>First name:</td>
			<td>
				<input type="text" name="f_name" value="${worker.fname}" />
			</td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td>
				<input type="text" name="l_name" value="${worker.lname}" />
			</td>
		</tr>
		<tr>
			<td>Specification:</td>
			<td>
				<select name="spec_id">
					<c:forEach var="item" items="${specifications}">
						<option value="${item.specId}"
										<c:if test="${item.specId eq worker.spec.specId}">selected</c:if>>
										<c:out value="${item.specName}"/>
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>Status:</td>
			<td>
				<select name="status_id">
					<c:forEach var="item" items="${statuses}">
						<option value="${item.statusId}"
										<c:if test="${item.statusId eq worker.status.statusId}">selected</c:if>>
										<c:out value="${item.statusName}"/>
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan=2 align="right">
				<input type="hidden" name="action" value="Save">
				<input type="hidden" name="worker_id" value="${worker.workerId}">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="submit" value="Save">
			</td>
		</tr>
		</form>
		</table>
		<div id="hint" />
	</jsp:attribute>
</t:general_template>