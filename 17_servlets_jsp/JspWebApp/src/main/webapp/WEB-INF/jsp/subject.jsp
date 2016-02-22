<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Subjects">
<jsp:attribute name="content_area">

<c:choose>
    <c:when test="${message.messageType eq 'ERROR'}">
       <p>
           <h4 style="font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;">
           ${message.messageText}
           </h4>
       </p>
    </c:when>
    <c:otherwise>
        <p>
           <h4 style="font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;color: #15DC13;">
           ${message.messageText}
           </h4>
       </p>
    </c:otherwise>
</c:choose>

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
<form action="subject" method="get">
<td>
<input type="text" name="subjectName" placeholder="enter subject name"/>
</td>
<td>
<input type="text" name="operation" value="search" hidden>
<select name="selectedTerm">
<option value="" selected disabled></option>
<c:forEach items="${terms}" var="t">
    <option value="<c:out value="${t.termName}"/>"><c:out value="${t.termName}"/></option>
</c:forEach>
</select>
</td>
<td>
<input type="submit" value="Search">
</td>
</form>
</tr>

<c:set var="count" value="0" scope="page" />
<c:forEach var="current" items="${subjects}">
<c:set var="count" value="${count + 1}" scope="page"/>
<form method="post" action="subject">
<tr>
<td>${count}</td>
<td>
<input type="text" name="subjectId" value="<c:out value="${current.id}"/>" hidden/>
<input type="text" name="subjectName" value="<c:out value="${current.name}"/>" required/>
</td>
<td>
<select name="selectedTerm">
<c:forEach items="${terms}" var="t">
<c:choose>
    <c:when test="${t.termId == current.termId}">
       <option value="<c:out value="${t.termName}"/>" selected><c:out value="${t.termName}"/></option>
    </c:when>
    <c:otherwise>
        <option value="<c:out value="${t.termName}"/>"><c:out value="${t.termName}"/></option>
    </c:otherwise>
</c:choose>
</c:forEach>
</select>
</td>
<td>
<input type="submit" name="operation" value="update"/>
<input type="submit" name="operation" value="delete"/>
</td>
</tr>
</form>
</c:forEach>

<form method="post" action="subject">
<tr>
<td readOnly></td>
<td>
<input type="text" name="subjectName" placeholder="enter subject name" required/>
</td>
<td>
<select name="selectedTerm" required>
<option value="" selected disabled></option>
<c:forEach items="${terms}" var="t">
    <option value="<c:out value="${t.termName}"/>"><c:out value="${t.termName}"/></option>
</c:forEach>
</select>
</td>
<td>
<input type="submit" name="operation" value="add"/>
</td>
</tr>
</form>

</tbody>
</table>
</div>
</jsp:attribute>
</t:general_template>
