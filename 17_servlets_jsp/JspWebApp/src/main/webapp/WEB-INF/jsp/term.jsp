<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Terms">
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
        <th>Terms</th>
        <th>Commands</th>
    </tr>
</thead>
<tbody>

<c:set var="count" value="0" scope="page" />
<c:forEach var="current" items="${terms}">
<c:set var="count" value="${count + 1}" scope="page"/>
<form method="post" action="term">
<tr>
<td>${count}</td>
<td>
<input type="text" name="termId" value="<c:out value="${current.termId}"/>" hidden/>
<input type="text" name="termName" maxlength="30" value="<c:out value="${current.termName}"/>" required/>
</td>
<td>
<input type="submit" name="operation" value="update"/>
<input type="submit" name="operation" value="delete"/>
</td>
</tr>
</form>
</c:forEach>

<form method="post" action="term">
<tr>
<td readOnly></td>
<td>
<input type="text" name="termName" maxlength="30" placeholder="enter term name" required/>
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