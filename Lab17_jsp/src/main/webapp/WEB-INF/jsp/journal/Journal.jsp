<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Journal">
	<jsp:attribute name="content_area">
	<h1>Terms</h1>
	<table style="width: 60%" border="1">
		<tr>
			<th width=25%>First Name</th>
			<th width=25%>Last Name</th>
			<th width=20%>Student Group</th>
			<th width=20%>Subject</th>
            <th width=10%>Grade</th>
		</tr>
		<c:forEach var="record" items="${journal}">
			<tr>
				<td>${record.firstName}</td>
				<td>${record.lastName}</td>
				<td>${record.studentGroup}</td>
                <td>${record.subject}</td>
                <td>${record.rate}</td>
			</tr>
		</c:forEach>
	</table>
	</jsp:attribute>
</t:generalManager>