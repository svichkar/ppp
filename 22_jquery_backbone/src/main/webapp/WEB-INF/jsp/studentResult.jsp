<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Students">

<jsp:attribute name="content_area">
<div>
<p><h4 style="font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;${color}">${message}</h4></p>
<p><h4 style="font-family:'Courier New', Courier, monospace;font-weight:bold;text-align:center;color: black;">
Search results. <a href="student">Back to Student List</a></h4></p>
</div>

<div>
<table>
<thead>
    <tr>
        <th>#</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Admission Date</th>
        <th>Group</th>
        <th>Term</th>
        <th>Status</th>
    </tr>
</thead>
<tbody>

<c:set var="count" value="0" scope="page" />
<c:forEach var="current" items="${students}">
<c:set var="count" value="${count + 1}" scope="page"/>
<tr onclick="toggleSelected(this)">
<td>${count}</td>
<td>
<input type="text" name="name" value="${current.firstName}" readOnly/>
</td>
<td>
<input type="text" name="lastName" value="${current.lastName}" readOnly/>
</td>
<td>
<input type="date" name="date" value="${current.admissionDate}" style="border: none;font-size: 15;" readOnly/>
</td>
<td>
<input type="text" name="groupName" value="${current.studentGroup.groupName}" readOnly/>
</td>
<td>
<input type="text" name="termName" value="${current.term.termName}" readOnly/>
</td>
<td>
<input type="text" name="statusName" value="${current.status.statusName}" readOnly/>
</td>
</tr>
</c:forEach>

</tbody>
</table>
</div>

</jsp:attribute>
</t:general_template>
