<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="Workers Page">
	<jsp:attribute name="content_area">
		<div align="center">
		<h1>All Workers</h1>
	</div>
<table class="data">
	<tr>
		<th># Worker</th>
		<th>Full name</th>
		<th>Specialization</th>
		<th>Status</th>
		<th>Login</th>
		<th>Password</th>
		<th>Action</th>
	</tr>
	<c:forEach var="worker" items="${workerList}">
		<tr>
			<td><c:out value="${worker.workerId}" /></td>
			<td><c:out value="${worker.lastName}" />
						<c:out value=" ${worker.firstName}" /></td>
			<td><c:out value="${worker.specialization.specializationName}" /></td>
			<td><c:out value="${worker.workerStatus.workerStatusName}" /></td>
			<td><c:out value="${worker.user.userLogin}" /></td>
			<td><c:out value="${worker.user.userPassword}" /></td>
			<td>
				<form method="post">
					<input type="hidden" name="worker_id" value="${worker.workerId}"></input>
					<input type="submit" name="action"
								formaction="<c:url value="/admin/deleteWorker"></c:url>"
								value="Delete" />
					<input type="submit" name="target"
								formaction="<c:url value="/admin/updateExistingWorker"></c:url>"
								value="Edit Worker" />
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<form action="<c:url value="/admin/addNewWorker"></c:url>" method="get">
			<div style="width: 50%; margin: 0 auto; text-align: center;">
			<input hidden="hidden" name="homePage" value="workerPage">
			<button type="submit" class="btn btn-primary btn-block btn-large">Add new worker</button>
			</div>
		</form>


		</jsp:attribute>
</t:mainTemplate>
