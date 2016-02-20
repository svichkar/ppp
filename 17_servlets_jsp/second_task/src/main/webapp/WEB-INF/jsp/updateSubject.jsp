<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:templateManager title="Update Subject" activePage="Subjects">
	<jsp:attribute name="content_area">
		<h2>Update Subject</h2>
		<form action="updateSubject" method="post">
			<input type="hidden" name="subject_id"
				value="<c:out value="${updateSubject.subjectId}" />">
			<table class="form-table">
				<tr>
					<td>First name : </td>
					<td>
						<input type="text" name="updatedSubjectName"
						value="<c:out value="${updateSubject.subjectName}" />" required>
					</td>
				</tr>
				<tr>
					<td>Term Name : </td>
					<td>
						<select name="updatedTermName" size="1" required>
							<c:forEach var="term" items="${terms}">
								<c:choose>
									<c:when test="${term.termId == updateSubject.termId}">
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