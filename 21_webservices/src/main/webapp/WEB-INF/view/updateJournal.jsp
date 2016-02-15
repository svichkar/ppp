<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Update Journal">
	<jsp:attribute name="menu_area">
		<ul>
			<li><a href="<c:url value="/students"/>">Students</a></li>
			<li><a href="<c:url value="/subjects"/>">Subjects</a></li>
			<li><a href="<c:url value="/terms"/>">Terms</a></li>
			<li><a class="active" href="<c:url value="/journals"/>">Journal</a></li>
			<ul style="float: right; list-style-type: none;">
				<li><a href="<c:url value="/logout"/>">Logout</a></li>
			</ul>
		</ul>
	</jsp:attribute>
	<jsp:attribute name="content_area">
       <h2>Update Journal</h2>
       <form action="updateJournal" method="post" onsubmit="return validate(this, optionsForUpdateJournal)">
       <input type="hidden" name="journal_id"
				value="<c:out value="${updateJournal.journalId}"/>">
       <table class="form-table">
       <tr><td>Student : </td>
       <td><select name="updatedStudent" size="1" required>
       <c:forEach var="student" items="${students}">
       <c:choose>
       <c:when test="${student.studentId == updateJournal.student.studentId}">
       <option selected value="<c:out value="${student.studentId}"/>"><c:out value="${student.firstName} ${student.lastName}, ${student.group.groupName}" /></option>
       </c:when>
       <c:otherwise>
       <option value="<c:out value="${student.studentId}"/>"><c:out value="${student.firstName} ${student.lastName}, ${student.group.groupName}" /></option>
       </c:otherwise>
       </c:choose>
       </c:forEach>
       </select>
       <span class="hint">Select student from the list</span></td></tr>
        <tr><td>Subject : </td>
       <td><select name="updatedSubject" size="1" required>
       <c:forEach var="subject" items="${subjects}">
       <c:choose>
       <c:when test="${subject.subjectId == updateJournal.subject.subjectId}">
       <option selected value="<c:out value="${subject.subjectId}"/>"><c:out value="${subject.subjectName}" /></option>
       </c:when>
       <c:otherwise>
       <option value="<c:out value="${subject.subjectId}"/>"><c:out value="${subject.subjectName}" /></option>
       </c:otherwise>
       </c:choose>
       </c:forEach>
       </select>
       <span class="hint">Select subject from the list</span></td></tr>
       <tr><td>Grade : </td>
       <td><select name="updatedGrade" size="1" required>
       <c:forEach var="grade" items="${grades}">
       <c:choose>
       <c:when test="${grade.gradeId == updateJournal.grade.gradeId}">
       <option selected value="<c:out value="${grade.gradeId}"/>"><c:out value="${grade.gradeName}" /></option>
       </c:when>
       <c:otherwise>
       <option value="<c:out value="${grade.gradeId}"/>"><c:out value="${grade.gradeName}" /></option>
       </c:otherwise>
       </c:choose>
       </c:forEach>
       </select>
       <span class="hint">Select grade from the list</span></td></tr>
		</table>
       <p/><input type="submit" name="update" value="Update">
       </form>         
    </jsp:attribute>
</t:template>