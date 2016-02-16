<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="View Journal">
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
       <h2>Journal</h2>
        <table class="centered-table" border="1">
            <thead>
                <tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Admission Date</th>
					<th>Group</th>
					<th>Status</th>
					<th>Term</th>
				</tr>
            </thead>
            <tbody>            
            <tr>
                    <td><c:out value="${student.firstName}" /></td>
                    <td><c:out value="${student.lastName}" /></td>
                    <td><c:out
							value="${student.admissionDate}" /></td>
                    <td><c:out value="${student.group.groupName}" /></td>
                    <td><c:out value="${student.status.statusName}" /></td>
                    <td><c:out value="${student.term.termName}" /></td>
                </tr>
            </tbody>
        </table>        
         <br />
		<form action="journal" method="post" onsubmit="return validate(this, optionsForViewJournal)">
         <input type="hidden" name="student_id"
				value="<c:out value="${student.studentId}"/>">
       <table class="form-table">
       <tr>
						<td>Term Name : </td>
						<td><select name="term_id" size="1" required>
       <option selected disabled>--Select term--</option>
       <!--  c:forEach var="term" items="${terms}"-->
       <option value="<c:out value="${term.termId}"/>"><c:out
										value="${term.termName}" /></option>
       <!-- /c:forEach-->
			</select>
			<span class="hint">Select term from the list</span></td>
					</tr>                          
		</table>
	    <p/><input type="submit" name="openJournal" value="View Journal">
       </form>
       <table class="centered-table" border="1">
            <thead>
                <tr>
                	<th>Term</th>
					<th>Subject</th>
					<th>Grade</th>
					<th>GPA</th>
				</tr>
            </thead>
            <tbody>
            <c:forEach var="journal" items="${journals}">
            <tr>
            		<td><c:out value="${journal.subject.term.termName}" /></td>
                    <td><c:out
								value="${journal.subject.subjectName}" /></td>
                    <td><c:out value="${journal.grade.gradeName}" /></td>                    
                    <td><c:out value="${gpas.gradeName}" /></td>                  
                </tr>
            </c:forEach>
            </tbody>
        </table>             
    </jsp:attribute>
</t:template>