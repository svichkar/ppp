<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Subjects">
<jsp:attribute name="content_area">

<div>
<p><h4 style="font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;${color}">${message}</h4></p>
<p><h4 style="font-family:'Courier New', Courier, monospace;font-weight:bold;text-align:center;color: black;">
Search results. <a href="subject">Back to Subject List</a></h4></p>
</div>

<div>
<table>
<thead>
    <tr>
        <th>#</th>
        <th>Subjects</th>
        <th>Terms</th>
    </tr>
</thead>
<tbody>
<c:set var="count" value="0" scope="page" />
<c:forEach var="current" items="${subjects}">
<c:set var="count" value="${count + 1}" scope="page"/>
<tr onclick="toggleSelected(this)">
<td>${count}</td>
<td>
<input type="text" value="${current.subjectName}" readOnly/>
</td>
<td>
<input type="text" value="${current.term.termName}" readOnly/>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</jsp:attribute>
</t:general_template>
