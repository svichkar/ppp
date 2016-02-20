<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:templateManager title="Update Journal" activePage="Journals">
	<jsp:attribute name="content_area">
		<h2>Update Journal</h2>
		<form action="updateJournal" method="post">
			<input type="hidden" name="journal_id"
				value="<c:out value="${updateJournal.journalId}" />">
			<table class="form-table">
				<tr>
					<td>Student : </td>
					<td>
						<select name="updatedStudent" size="1" required>
							<c:forEach var="student" items="${students}">
								<c:choose>
									<c:when test="${student.studentId == updateJournal.studentId}">
										<option selected
											value="<c:out value="${student.studentId}" />">
											<c:out value="${student.studentNameInGroup}" />
										</option>
									</c:when>
									<c:otherwise>
										<option value="<c:out value="${student.studentId}" />">
											<c:out value="${student.studentNameInGroup}" />
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Subject : </td>
					<td>
						<select name="updatedSubject" size="1" required>
							<c:forEach var="subject" items="${subjects}">
								<c:choose>
									<c:when test="${subject.subjectId == updateJournal.subjectId}">
										<option selected
											value="<c:out value="${subject.subjectId}" />">
											<c:out value="${subject.subjectName}" />
										</option>
									</c:when>
									<c:otherwise>
										<option value="<c:out value="${subject.subjectId}" />">
											<c:out value="${subject.subjectName}" />
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Grade : </td>
					<td>
						<select name="updatedGrade" size="1" required>
							<c:forEach var="grade" items="${grades}">
								<c:choose>
									<c:when test="${grade.gradeId == updateJournal.gradeId}">
										<option selected value="<c:out value="${grade.gradeId}" />">
										<c:out value="${grade.gradeName}" />
									</option>
									</c:when>
									<c:otherwise>
										<option value="<c:out value="${grade.gradeId}" />">
										<c:out value="${grade.gradeName}" />
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