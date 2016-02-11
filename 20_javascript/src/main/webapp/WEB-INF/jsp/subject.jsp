<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Subjects" opt="javascript/validator/subjectOptions.js">
<jsp:attribute name="content_area">

<div>
<p><h4 style="font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;${color}">${message}</h4></p>
</div>

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
<form:form action="subject" method="get">
<td class="tooltips">
<input type="text" name="subjectName" placeholder="enter subject name" onfocus="showTip(this, 'subject name, e.g. <i>Physics</i>');" onblur="hideTip(this)"/>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips">
<input type="text" name="operation" value="search" hidden>
<select name="selectedTerm" onfocus="showTip(this, 'pick up a term, e.g. <i>first</i>');" onblur="hideTip(this)">
<option value="" selected disabled>................</option>
<c:forEach items="${terms}" var="t">
    <option value="${t.termName}">${t.termName}</option>
</c:forEach>
</select>
<div id="message" style="font-size:10;"></div>
</td>

<td>
<input type="submit" value="search">
</td>
</form:form>
</tr>

<c:set var="count" value="0" scope="page" />
<c:forEach var="current" items="${subjects}">
<c:set var="count" value="${count + 1}" scope="page"/>
<form:form method="post" action="subject" onsubmit="return validateForm(this, options)">
<tr onclick="toggleSelected(this)">
<td>${count}</td>

<td class="tooltips">
<input type="text" name="subjectId" value="${current.subjectId}" hidden/>
<input type="text" name="subjectName" value="${current.subjectName}" onfocus="showTip(this, 'subject name, e.g. <i>Physics</i>');" onblur="hideTip(this)"/>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips">
<select name="selectedTerm" onfocus="showTip(this, 'pick up a term, e.g. <i>first</i>');" onblur="hideTip(this)">
<c:forEach items="${terms}" var="t">
<c:choose>
    <c:when test="${t.termId == current.term.termId}">
       <option value="${t.termName}" selected>${t.termName}</option>
    </c:when>
    <c:otherwise>
        <option value="${t.termName}">${t.termName}</option>
    </c:otherwise>
</c:choose>
</c:forEach>
</select>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td>
<input type="submit" name="update" value="update"/>
<input type="submit" name="delete" value="delete"/>
</td>
</tr>
</form:form>
</c:forEach>

<form:form method="post" action="subject" onsubmit="return validateForm(this, options)">
<tr>
<td readOnly></td>

<td class="tooltips">
<input type="text" name="subjectName" placeholder="enter subject name" onfocus="showTip(this, 'subject name, e.g. <i>Physics</i>');" onblur="hideTip(this)"/>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips">
<select name="selectedTerm" onfocus="showTip(this, 'pick up a term, e.g. <i>first</i>');" onblur="hideTip(this)">
<option value="" selected disabled>................</option>
<c:forEach items="${terms}" var="t">
    <option value="${t.termName}">${t.termName}</option>
</c:forEach>
</select>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
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
