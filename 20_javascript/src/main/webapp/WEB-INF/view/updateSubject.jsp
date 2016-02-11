<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Update Subject">
	<jsp:attribute name="menu_area">
		<ul>
			<li><a href="<c:url value="/students"/>">Students</a></li>
			<li><a class="active" href="<c:url value="/subjects"/>">Subjects</a></li>
			<li><a href="<c:url value="/terms"/>">Terms</a></li>
			<li><a href="<c:url value="/journals"/>">Journal</a></li>
			<ul style="float: right; list-style-type: none;">
				<li><a href="<c:url value="/logout"/>">Logout</a></li>
			</ul>
		</ul>
	</jsp:attribute>
	<jsp:attribute name="content_area">
       <h2>Update Subject</h2>
       <form action="updateSubject" method="post" onsubmit="return validate(this, optionsForUpdateSubject)">
       <input type="hidden" name="subject_id"
				value="<c:out value="${updateSubject.subjectId}"/>">
       <table class="form-table">
       <tr>
					<td>Subject name : </td>
					<td><input type="text" name="updatedSubjectName"
						value="<c:out value="${updateSubject.subjectName}"/>" required>
						<span class="hint">Update subject name in text format</span></td>
				</tr>
       <tr>
					<td>Term Name : </td>
					<td><select name="updatedTermName" size="1" required>
       <c:forEach var="term" items="${terms}">
      <c:choose>
       <c:when test="${term.termId == updateSubject.term.termId}">
       <option selected value="<c:out value="${term.termId}"/>"><c:out
												value="${term.termName}" /></option>
       </c:when>
       <c:otherwise>
       <option value="<c:out value="${term.termId}"/>"><c:out
												value="${term.termName}" /></option>
       </c:otherwise>
       </c:choose>
       </c:forEach>
			</select>
			<span class="hint">Select term from the list</span></td>
				</tr>
		</table>
       <p/><input type="submit" name="update" value="Update">
       </form>         
    </jsp:attribute>
</t:template>