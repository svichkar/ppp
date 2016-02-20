<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:templateManager title="Update Student" activePage="Students">
	<jsp:attribute name="content_area">
		<h2>Update Student</h2>
		<form action="updateStudent" method="post">
			<input type="hidden" name="student_id"
				value="<c:out value="${updateStudent.studentId}" />">
			<table class="form-table">
				<tr>
					<td>First name : </td>
					<td>
						<input type="text" name="updatedFirstName"
						value="<c:out value="${updateStudent.firstName}" />" required>
					</td>
				</tr>
				<tr>
					<td>Last Name : </td>
					<td>
						<input type="text" name="updatedLastName"
						value="<c:out value="${updateStudent.lastName}" />" required>
					</td>
				</tr>
				<tr>
					<td>Admission Date: </td>
					<td>
						<input type="text" name="updatedAdmissionDate"
						value="<c:out value="${updateStudent.admissionDate}" />" required>
					</td>
				</tr>
				<tr>
					<td>Group Name : </td>
					<td>
						<select name="updatedGroupName" size="1" required>
							<c:forEach var="group" items="${groups}">
								<c:choose>
									<c:when test="${group.groupId == updateStudent.groupId}">
										<option selected value="<c:out value="${group.groupId}" />">
											<c:out value="${group.groupName}" />
										</option>
									</c:when>
									<c:otherwise>
										<option value="<c:out value="${group.groupId}" />">
											<c:out value="${group.groupName}" />
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Status Name : </td>
					<td>
						<select name="updatedStatusName" size="1" required>
							<c:forEach var="status" items="${statuses}">
								<c:choose>
									<c:when test="${status.statusId == updateStudent.statusId}">
										<option selected value="<c:out value="${status.statusId}" />">
											<c:out value="${status.statusName}" />
										</option>
									</c:when>
									<c:otherwise>
										<option value="<c:out value="${status.statusId}" />">
											<c:out value="${status.statusName}" />
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Term Name : </td>
					<td>
						<select name="updatedTermName" size="1" required>
							<c:forEach var="term" items="${terms}">
								<c:choose>
									<c:when test="${term.termId == updateStudent.termId}">
										<option selected value="<c:out value="${term.termId}" />">
										<c:out value="${term.termName}" />
									</option>
									</c:when>
									<c:otherwise>
										<option value="<c:out value="${term.termId}" />">
										<c:out value="${term.termName}" />
									</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
			<br />
			<input type="submit" name="update" value="Update">
		</form>
	</jsp:attribute>
</t:templateManager>