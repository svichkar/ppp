<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Subjects">
<jsp:attribute name="content_area">

<p><h4 style="font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;">${error}</h4></p>
<p><h4 style="font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;">${errorSearch}</h4></p>
<p><h4 style="font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color:#15DC13;">${message}</h4></p>

<div>
<table>
<thead>
    <tr>
        <th>#</th>
        <th>Subjects</th>
        <th>Terms</th>
        <th>Commands</th>
    </tr>
</thead>
<tbody>

<tr>
<td readOnly></td>
<form:form  action="subject" method="get">
<td>
<input type="text" name="subjectName" placeholder="enter subject name"/>
</td>
<td>
<input type="text" name="operation" value="search" hidden>
<select name="selectedTerm">
<option value="" selected disabled></option>
<c:forEach items="${terms}" var="t">
    <option value="${t.termName}">${t.termName}</option>
</c:forEach>
</select>
</td>
<td>
<input type="submit" value="Search">
</td>
</form:form>
</tr>

<c:set var="count" value="0" scope="page" />
<c:forEach var="current" items="${subjects}">
<c:set var="count" value="${count + 1}" scope="page"/>
<form:form method="post" modelAttribute="subjectForm" action="subject">
<tr>
<td>${count}</td>
<td>
<input type="text" name="subjectId" value="${current.subjectId}" hidden/>
<input type="text" name="subjectName" value="${current.subjectName}" required/>
</td>
<td>
<form:select  path="term.termName">
<form:option value="${current.term.termName}"/>
<form:options items="${terms}" itemValue="termId" itemLabel="termName"/>
</form:select>
</td>
<td>
<input type="submit" name="update" value="update"/>
<input type="submit" name="delete" value="delete"/>
</td>
</tr>
</form:form>
</c:forEach>

<form:form method="post" modelAttribute="subjectForm" action="subject">
<tr>
<td readOnly></td>
<td>
<input type="text" name="subjectName" placeholder="enter subject name" required/>
</td>
<td>
<select name="selectedTerm" required>
<option value="" selected disabled></option>
<c:forEach items="${terms}" var="t">
    <option value="${t.termName}">${t.termName}</option>
</c:forEach>
</select>
</td>
<td>
<input type="submit" name="add" value="add"/>
</td>
</tr>
</form:form>

</tbody>
</table>
</div>
</jsp:attribute>
</t:general_template>
