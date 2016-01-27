<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Students">
    <jsp:attribute name="content_area">

${message}
${error}
${errorSearch}

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
        <th>Commands</th>
        <th>Details</th>
    </tr>
</thead>
<tbody>

<tr>
<td readOnly></td>
<td readOnly></td>
<form action="student" method="get">
<td>
<input type="text" name="lastName" placeholder="enter last name"/>
</td>
<td readOnly></td>
<td>
<input type="text" name="operation" value="search" hidden>
<select name="group">
<option value="" selected disabled></option>
<c:forEach items="${groups}" var="g">
    <option value="${g.groupName}">${g.groupName}</option>
</c:forEach>
</select>
</td>
<td readOnly></td>
<td readOnly></td>
<td>
<input type="submit" value="Search">
</td>
<td readOnly></td>
</form>
</tr>

<c:set var="count" value="0" scope="page" />
<c:forEach var="current" items="${students}">
<c:set var="count" value="${count + 1}" scope="page"/>
<form method="post" action="student">
<tr>
<td>${count}</td>
<td>
<input type="text" name="id" value="${current.id}" hidden/>
<input type="text" name="name" value="${current.name}" required/>
</td>
<td>
<input type="text" name="lastName" value="${current.lastName}" required/>
</td>
<td>
<input type="date" name="date" value="${current.date}" style="background: #E5E5DB;border: none;font-size: 15;" required/>
</td>
<td>
<select name="group">
<c:forEach items="${groups}" var="g">
<c:choose>
    <c:when test="${g.groupId == current.groupId}">
       <option value="${g.groupName}" selected>${g.groupName}</option>
    </c:when>
    <c:otherwise>
        <option value="${g.groupName}">${g.groupName}</option>
    </c:otherwise>
</c:choose>
</c:forEach>
</select>
</td>
<td>
<select name="term">
<c:forEach items="${terms}" var="t">
<c:choose>
    <c:when test="${t.termId == current.termId}">
       <option value="${t.termName}" selected>${t.termName}</option>
    </c:when>
    <c:otherwise>
        <option value="${t.termName}">${t.termName}</option>
    </c:otherwise>
</c:choose>
</c:forEach>
</select>
</td>
<td>
<select name="status">
<c:forEach items="${statusList}" var="status">
<c:choose>
    <c:when test="${status.statusId == current.statusId}">
       <option value="${status.statusName}" selected>${status.statusName}</option>
    </c:when>
    <c:otherwise>
        <option value="${status.statusName}">${status.statusName}</option>
    </c:otherwise>
</c:choose>
</c:forEach>
</select>
</td>
<td>
<input type="submit" name="operation" value="update"/>
<input type="submit" name="operation" value="delete"/>
</td>
</form>

<form method="get" action="<c:url value="/journal/student"/>">
<td>
<input type="text" name="id" value="${current.id}" hidden/>
<input type="submit" name="operation" value="details"/>
</td>
</form>

</tr>
</c:forEach>

<form method="post" action="student">
<tr>
<td readOnly></td>
<td>
<input type="text" name="name" placeholder="enter first name" required/>
</td>
<td>
<input type="text" name="lastName" placeholder="enter last name" required/>
</td>
<td>
<input type="date" name="date" style="background: #E5E5DB;border: none;font-size: 15;" required/>
</td>
<td>
<select name="group" required>
<option value="" selected disabled></option>
<c:forEach items="${groups}" var="g">
    <option value="${g.groupName}">${g.groupName}</option>
</c:forEach>
</select>
</td>
<td>
<select name="term" required>
<option value="" selected disabled></option>
<c:forEach items="${terms}" var="t">
    <option value="${t.termName}">${t.termName}</option>
</c:forEach>
</select>
</td>
<td>
<select name="status" required>
<option value="" selected disabled></option>
<c:forEach items="${statusList}" var="status">
    <option value="${status.statusName}">${status.statusName}</option>
</c:forEach>
</select>
</td>
<td>
<input type="submit" name="operation" value="add"/>
</td>
<td readOnly></td>
</tr>
</form>

</tbody>
</table>
</div>

</jsp:attribute>
</t:general_template>
