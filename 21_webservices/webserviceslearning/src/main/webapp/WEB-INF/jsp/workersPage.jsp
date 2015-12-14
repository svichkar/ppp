<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:showTemplate title="Workers">
	<jsp:attribute name="content_area">
		<div align="center">
		<h1>All Workers</h1>
	</div>
	<table align="center" width=70% border=1 cellpadding=8>
		<tr height=40>
			<td width=10%><b>Worker ID</b></td>
			<td width=15%><b>First Name</b></td>
			<td width=15%><b>Last Name</b></td>
			<td width=22%><b>Specialization</b></td>
			<td width=18%><b>Status</b></td>
			<td width=20%><b>Action</b></td>
		</tr>
		<c:forEach var="item" items="${workers}">
			<tr>
				<td><c:out value="${item.workerId}" /></td>
				<td><c:out value="${item.firstName}" /></td>
				<td><c:out value="${item.lastName}" /></td>
				<td><c:out value="${item.workerSpecialization}" /></td>
				<td><c:out value="${item.status}" /></td>
				<td>
					<form method="POST">
						<input type="hidden" name="worker_id" value="${item.workerId}">
						<input type="hidden" name="target" value="Workers" />
						<input type="submit" name="action" formaction="editWorker.do" value="Edit" class="input_edit">
						<input type="submit" name="action" formaction="deleteWorker.do" value="Delete" class="input_edit">
					</form>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan=6>
				<div align="center">
					<form action="addWorker.do" method="GET">
						<input type="submit" value="Add new worker" class="input_add">
					</form>
				</div>
			</td>
		</tr>
	</table>
	</jsp:attribute>
</t:showTemplate>