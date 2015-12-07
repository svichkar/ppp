<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Subjects">
	<jsp:attribute name="content_area">
<h1>Subjects</h1>
<h2>Search</h2>
<b>Search strategy:</b>
		<br>
<form action="Subjects.do" method="post">
  <input type="radio" name="searchType" value="subject" checked> Subject Name
  <input type="radio" name="searchType" value="term"> Term Name
  <input type="text" name="searchQuery">
  <input type="submit" value="Search" style="width: 100px;">
</form> 
<h2>Subjects</h2>
<table style="width: 50%" border="1">
		<tr>
			<th width=15%>Subject Id</th>
			<th width=30%>Subject Name</th>
			<th width=30%>Term Name</th>
			<th width=15%>Action</th>
		</tr>
		<c:forEach var="subject" items="${subjects}">
			<tr>
				<td>${subject.subjectId}</td>
				<td>${subject.name}</td>	
				<td>${subject.term.alias}</td>				
				<td>
					<form action="editSubject.do" method="get">							
						<input type=submit name="edit" value="Edit"
								style="width: 100%; height: 50%;">
						<input type="hidden" name="subjectId" value="${subject.subjectId}">
					</form>
					<form action="deleteSubject.do" method="post">							
						<input type=submit name="delete" value="Delete"
								style="width: 100%; height: 50%;">
						<input type="hidden" name="subjectId" value="${subject.subjectId}"> 
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<form action="addNewSubject.do" method="get">
	<input type=submit name="create" value="Add new" style="width: 100px;">
	</form>
</jsp:attribute>
</t:generalManager>