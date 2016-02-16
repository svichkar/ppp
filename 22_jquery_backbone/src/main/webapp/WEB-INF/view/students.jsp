<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Students">
	<jsp:attribute name="menu_area">
		<ul>
			<li><a class="active" href="<c:url value="/students"/>">Students</a></li>
			<li><a href="<c:url value="/subjects"/>">Subjects</a></li>
			<li><a href="<c:url value="/terms"/>">Terms</a></li>
			<li><a href="<c:url value="/journals"/>">Journal</a></li>
			<ul style="float: right; list-style-type: none;">
				<li><a href="<c:url value="/logout"/>">Logout</a></li>
			</ul>
		</ul>
	</jsp:attribute>
	<jsp:attribute name="content_area">	
	<div class="forms">
	<h2>Students</h2>
       <h3>Add Student</h3>
       <form action="students" method="post" onsubmit="return validate(this, optionsForAddStudent)">
       <table class="form-table">
       <tr>
					<td>First Name : </td>
					<td><input type="text" name="newFirstName" required>
					<span class="hint">Enter the first name in text format</span>
					</td>
				</tr>
       <tr>
					<td>Last Name : </td>
					<td><input type="text" name="newLastName" required>
					<span class="hint">Enter the last name in text format</span></td>
				</tr>
       <tr>
					<td>Admission Date: </td>
					<td><input type="text" name="newAdmissionDate" required>
					<span class="hint">Enter admission date in format YYYY-MM-DD</span></td>
				</tr>
       <tr>
					<td>Group Name : </td>
					<td><select name="newGroupName" size="1" required>
       <option selected disabled>--Select group--</option>
       <c:forEach var="group" items="${groups}">
       <option value="<c:out value="${group.groupId}"/>"><c:out
											value="${group.groupName}" /></option>
       </c:forEach>       
			</select>
			<span class="hint">Select group from the list</span></td>
				</tr>                                  
       <tr>
					<td>Status Name : </td>
					<td><select name="newStatusName" size="1" required>
       <option selected disabled>--Select status--</option>
       <c:forEach var="status" items="${statuses}">
       <option value="<c:out value="${status.statusId}"/>"><c:out
											value="${status.statusName}" /></option>
       </c:forEach>
			</select>
			<span class="hint">Select status from the list</span></td>
				</tr>
       <tr>
					<td>Term Name : </td>
					<td><select name="newTermName" size="1" required>
       <option selected disabled>--Select term--</option>
       <c:forEach var="term" items="${terms}">
       <option value="<c:out value="${term.termId}"/>"><c:out
											value="${term.termName}" /></option>
       </c:forEach>
			</select>
			<span class="hint">Select term from the list</span></td>
				</tr>       
       </table>
       <p/><input type="submit" name="add" value="Add">       
       </form> 
       <h3>Search Student</h3>
       <form action="students" method="post" onsubmit="return validate(this, optionsForSearchStudent)">
       <table class="form-table">
       <tr>
					<td>Last Name : </td>
					<td><input type="text" name="searchLastName">
					<span class="hint">Enter the last name for search</span></td>
				</tr>
       <tr>
					<td>Group Name : </td>
					<td><select name="searchGroupId" size="1">
       <option selected disabled>--Select group--</option>
       <c:forEach var="group" items="${groups}">
       <option value="<c:out value="${group.groupId}"/>"><c:out
											value="${group.groupName}" /></option>
       </c:forEach>
			</select>
			<span class="hint">Select group from the list</span></td>
				</tr>                          
		</table>
	    <p/><input type="submit" name="search" value="Search">
       </form>
       </div>
       <c:if test="${not empty message}">
       <div class="message-info" align="center">
				<c:out value="${message}" />
			</div>
       </c:if>      
       <h3>List of students</h3>       
        <table class="centered-table" border="1">
            <thead>
                <tr>
					<th>Student ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Admission Date</th>
					<th>Group</th>
					<th>Status</th>
					<th>Term</th>
					<th>Action</th>
				</tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td><c:out value="${student.studentId}" /></td>
                    <td><c:out value="${student.firstName}" /></td>
                    <td><c:out value="${student.lastName}" /></td>
                    <td><c:out value="${student.admissionDate}" /></td>
                    <td><c:out value="${student.group.groupName}" /></td>
                    <td><c:out value="${student.status.statusName}" /></td>
                    <td><c:out value="${student.term.termName}" /></td>
                    <td><div class="action">
								<form action="updateStudent" method="get">
                    <input type="hidden" name="student_id"
										value="<c:out value="${student.studentId}"/>">
					<input type="submit" name="edit" value="Edit">
							</form>
							</div>
					<div class="action">		
					<form action="students" method="post">
                    <input type="hidden" name="student_id"
										value="<c:out value="${student.studentId}"/>">
					 <input type="submit" name="delete" value="Delete">
                    </form>
							</div>
                    <div class="action">
                    <form action="journal" method="get">
                    <input type="hidden" name="student_id"
										value="<c:out value="${student.studentId}"/>">
                    <input type="submit" name="viewJournal"
										value="View Journal">
                    </form>
							</div></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>                         
    </jsp:attribute>
</t:template>