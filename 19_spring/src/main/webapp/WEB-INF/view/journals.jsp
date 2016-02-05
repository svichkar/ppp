<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Journal">
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
	<div class="forms">
       <h2>Journal</h2>
       <h3>Add Record</h3>
       <form action="journals" method="post">
       <table class="form-table">
       <tr>
       <td>Student : </td>
       <td><select name="newStudent" size="1" required>
       <option selected disabled>--Select student--</option>
       <c:forEach var="student" items="${students}">
       <option value="<c:out value="${student.studentId}"/>"><c:out
										value="${student.firstName} ${student.lastName}, ${student.group.groupName}" /></option>
       </c:forEach>
       </select>
       </td>
       </tr>
       <tr>
       <td>Subject : </td>
       <td><select name="newSubjectName" size="1" required>
       <option selected disabled>--Select subject--</option>
       <c:forEach var="subject" items="${subjects}">
       <option value="<c:out value="${subject.subjectId}"/>"><c:out
										value="${subject.subjectName}" /></option>
       </c:forEach>
       </select>
       </td>
       </tr>
       <tr>
       <td>Grade : </td>
       <td><select name="newGradeName" size="1" required>
       <option selected disabled>--Select grade--</option>
       <c:forEach var="grade" items="${grades}">
       <option value="<c:out value="${grade.gradeId}"/>"><c:out
										value="${grade.gradeName}" /></option>
       </c:forEach>
       </select>
       </td>
       </tr>
       </table>
       <br /><input type="submit" name="add" value="Add">
       </form> 
       </div>
       <c:if test="${not empty message}">
       <div class="message-info" align="center">
				<c:out value="${message}" />
			</div>
       </c:if>    
       <h3>Journal records</h3>       
        <table class="centered-table" border="1">
            <thead>
                <tr>
					<th>Journal ID</th>
					<th>Student</th>
					<th>Subject</th>
					<th>Grade</th>		
					<th>Action</th>					
				</tr>
            </thead>
            <tbody>
            <c:forEach var="journal" items="${journals}">
                <tr>
                	<td><c:out value="${journal.journalId}" /></td>
                    <td><c:out
								value="${journal.student.firstName} ${journal.student.lastName}, ${journal.student.group.groupName}" /></td>
                    <td><c:out
								value="${journal.subject.subjectName}" /></td>
                    <td><c:out value="${journal.grade.gradeName}" /></td>                    
                    <td><div  class="action"><form action="updateJournal" method="get">
                    <input type="hidden" name="journal_id"
									value="<c:out value="${journal.journalId}"/>">
                    <input type="submit" name="edit" value="Edit">
							</form></div>
					<div  class="action">
					<form action="journals" method="post">
                    <input type="hidden" name="journal_id"
									value="<c:out value="${journal.journalId}"/>">
                    <input type="submit" name="delete" value="Delete">
                    </form></div></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>                            
    </jsp:attribute>
</t:template>