<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Student Grade Details">
<jsp:attribute name="content_area">

${pageTitle}
${message}

 <div>
 <table>
 <thead>
     <tr>
         <th>#</th>
         <th>Subject</th>
         <th>Grade</th>
     </tr>
 </thead>
 <tbody>
 <tr>
 <td colspan="3">
 <form method="get" action="<c:url value="/journal/student"/>">
 <input type="text" name="id" value="${id}" hidden/>
 <select name="term" style="font-style: italic;">
 <option value="" disabled style="font-style: italic;font-size: 12;">select term for details</option>
 <c:forEach items="${terms}" var="t">
 <c:choose>
     <c:when test="${t.termName eq selectedTerm.termName}">
        <option style="font-style: normal; font-size: 16;" value="${t.termName}" selected>${t.termName}</option>
     </c:when>
     <c:otherwise>
         <option style="font-style: normal; font-size: 16;" value="${t.termName}">${t.termName}</option>
     </c:otherwise>
 </c:choose>

 </c:forEach>
 </select>
 <input type="submit" name="operation" value="show"/>
 </form>
</td>
</tr>
<c:set var="count" value="0" scope="page" />
<c:forEach var="current" items="${journals}">
<c:set var="count" value="${count + 1}" scope="page"/>
<tr>
<td>${count}</td>
<td>${current.subject}</td>
<td>${current.grade}</td>
</c:forEach>
</tr>

<tr>
<td  colspan="2"><b>AVERAGE SCORE:</b></td>
<td><b>${score}</b></td>
</tr>

</tbody>
</table>
</div>
</jsp:attribute>
</t:general_template>
