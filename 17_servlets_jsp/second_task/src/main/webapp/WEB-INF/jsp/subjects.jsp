<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:templateManager title="Subjects" activePage="Subjects">
	<jsp:attribute name="content_area">
		<div class="forms">
			<h2>Subjects</h2>
			<h3>Add Subject</h3>
			<form action="subjects" method="post">
				<table class="form-table">
					<tr>
						<td>Subject Name : </td>
						<td>
							<input type="text" name="newSubjectName" required>
						</td>
					</tr>
					<tr>
						<td>Term Name : </td>
						<td>
							<select name="newTermName" size="1" required>
								<option selected disabled>--Select term--</option>
								<c:forEach var="term" items="${terms}">
									<option value="<c:out value="${term.termId}" />">
										<c:out value="${term.termName}" />
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<br />
				<input type="submit" name="add" value="Add">
			</form>
			<h3>Search Subject</h3>
			<form action="subjects" method="post">
				<table class="form-table">
					<tr>
						<td>Subject Name : </td>
						<td>
							<input type="text" name="searchSubjectName">
						</td>
					</tr>
					<tr>
						<td>Term Name : </td>
						<td>
							<select name="searchTermId" size="1">
								<option selected disabled>--Select term--</option>
								<c:forEach var="term" items="${terms}">
									<option value="<c:out value="${term.termId}" />">
										<c:out value="${term.termName}" />
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<br />
				<input type="submit" name="search" value="Search">
			</form>
		</div>
		<c:if test="${not empty param.message}">
			<div class="message-info" align="center">
				<c:out value="${param.message}" />
			</div>
		</c:if>
		<h3>List of subjects</h3>
		<table class="centered-table" border="1">
			<thead>
				<tr>
					<th>Subject ID</th>
					<th>Subject Name</th>
					<th>Term Name</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="subject" items="${subjects}">
					<tr>
						<td>
							<c:out value="${subject.subjectId}" />
						</td>
						<td>
							<c:out value="${subject.subjectName}" />
						</td>
						<td>
							<c:out value="${subject.termName}" />
						</td>
						<td>
							<div class="action">
								<form action="updateSubject" method="get">
									<input type="hidden" name="subject_id"
										value="<c:out value="${subject.subjectId}" />">
									<input type="submit" name="edit" value="Edit">
								</form>
							</div>
							<div class="action">
								<form action="subjects" method="post">
									<input type="hidden" name="subject_id"
										value="<c:out value="${subject.subjectId}" />">
									<input type="submit" name="delete" value="Delete">
								</form>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</jsp:attribute>
</t:templateManager>