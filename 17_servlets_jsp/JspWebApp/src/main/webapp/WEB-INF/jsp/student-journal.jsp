<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Student Grade Details">
<jsp:attribute name="content_area">

 ${error}

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
 <form method="get" action="<c:url value="/journal/student"/>">
 <input type="text" name="id" value="${id}" hidden/>
 <select name="term">
 <option value="" selected disabled>select term</option>
 <c:forEach items="${terms}" var="t">
     <option value="${t.termName}">${t.termName}</option>
 </c:forEach>
 </select>
 <input type="submit" name="operation" value="show"/>
 </form>
 </tr>
<c:set var="count" value="0" scope="page" />
<c:forEach var="current" items="${journals}">
<c:set var="count" value="${count + 1}" scope="page"/>
<tr>
<td>${count}</td>
<td>${current.subject}</td>
<td>${current.grade}</td>
</tr>
</c:forEach>

<tr>
<td readOnly></td>
<td>AVERAGE SCORE:</td>
<td>${score}</td>
</tr>

</tbody>
</table>
</div>
</jsp:attribute>
</t:general_template>
