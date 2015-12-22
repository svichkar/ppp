<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="Worker Page">
	<jsp:attribute name="content_area">
		<div align="center">
			<h1>
				<c:out value="Edit existing worker" />
			</h1>
		</div>
		<form id="worker" action="updateWorker" method="post">
				<input hidden="hidden" name="worker_id" value="${worker.worker_id}" />
				<input hidden="hidden" name="user_id" value="${worker.user.user_id}" />
				<input hidden="hidden" name="action" value="edit" />
<table>
				<tr>
					<th class="nav-menu" colspan="2"><h3>Edit general info</h3></th>
				</tr>
				<tr>
					<td class="nav-menu"><b>worker ID</b></td>
					<td class="nav-menu"><input disabled="disabled" value="${worker.worker_id}" />
					</td>
				</tr>
				<tr>
					<td class="nav-menu"><b>Last Name</b></td>
					<td class="nav-menu"><input name="last_name" value="${worker.last_name}" /></td>
				</tr>
				<tr>
					<td class="nav-menu"><b>First name</b></td>
					<td class="nav-menu"><input name="first_name" value="${worker.first_name}" /></td>
				</tr>
				<tr>
					<td class="nav-menu"><p>Specialization</p></td>	
					<td class="nav-menu" colspan="2">
					<select name="specialization_id" form="worker">
					<c:forEach var="spec" items="${specs}">
					<c:choose>
					<c:when test="${spec.specialization_id == worker.specialization.specialization_id}">
					<option value="${spec.specialization_id }" selected="selected"><c:out value="${spec.specialization_name }"></c:out>
					</c:when>
					<c:otherwise>
						<option value="${spec.specialization_id }"><c:out value="${spec.specialization_name }"></c:out>
					</c:otherwise>
					</c:choose>
					</c:forEach>
					</select>
				</tr>
				<tr>
					<td class="nav-menu"><b>User login</b></td>
					<td class="nav-menu"><input name="user_login" value="${worker.user.user_login}" /></td>
				</tr>
				<tr>
					<td class="nav-menu"><b>User password</b></td>
					<td class="nav-menu"><input name="user_password" value="${worker.user.user_password}" /></td>
				</tr>
				<tr>
				<td class="nav-menu" colspan="2">
				<button type="submit" class="btn btn-primary btn-block btn-large" name="target" value="updateWorker">Update</button>
				</td>
				</tr>
			</table>
						<input hidden="hidden" name="action" value="edit" />
		</form>
		<br />
		<br />
		<form action="adminPage" method="get">
			<div style="width: 50%; margin: 0 auto; text-align: center;">
			<button type="submit" class="btn btn-primary btn-block btn-large"
					name="target" value="worker">Cancel</button>
			</div>
		</form>
	</jsp:attribute></t:mainTemplate>