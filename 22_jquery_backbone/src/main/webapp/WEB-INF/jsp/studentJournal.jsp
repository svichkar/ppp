<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:url value="/student/journal" var="linkDetails"/>
<t:general_template title="Student Grade Details">
<jsp:attribute name="content_area">

<div>
<p><h4 style="font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;${color}">${message}</h4></p>
<p>
<h3>Detailed grade statistic for <b>${student.firstName} ${student.lastName}</b>. <a href="<c:url value="/student"/>">Back to Students List</a></h3>
</p>
</div>

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
<form:form method="get" action="${linkDetails}">
 <td colspan="3" class="tooltips">
 <input type="text" name="studentId" value="${student.studentId}" hidden/>
 <select name="term" style="font-style: italic;" onfocus="showTip(this, 'pick up a term, e.g. <i>first</i>');" onblur="hideTip(this)">
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
<div id="message" style="font-size:10;"></div>
</td>

</tr>
<tr>
<td colspan="3">
<input type="submit" name="operation" value="show"/>
</form:form>
</td>
</tr>

<c:set var="count" value="0" scope="page" />
<c:forEach var="current" items="${journals}">
<c:set var="count" value="${count + 1}" scope="page"/>
<tr onclick="toggleSelected(this)">
<td>${count}</td>
<td>${current.subject.subjectName}</td>
<td>${current.grade.gradeName}</td>
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
