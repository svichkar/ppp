<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Students" opt="javascript/validator/studentOptions.js">
    <jsp:attribute name="content_area">

<div>
<p><h4 style="font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;${color}">${message}</h4></p>
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
        <th>Commands</th>
        <th>Details</th>
    </tr>
</thead>
<tbody>

<tr>
<td readOnly></td>
<td readOnly></td>
<form:form action="student" method="get">

<td class="tooltips">
<input type="text" name="lastName" placeholder="enter last name" onfocus="showTip(this, 'last name, e.g. <i>Ivanov</i>');" onblur="hideTip(this)"/>
<div id="message" style="font-size:10;"></div>
</td>

<td readOnly></td>

<td class="tooltips">
<input type="text" name="operation" value="search" hidden>
<select name="group" onfocus="showTip(this, 'pick up a group, e.g. <i>group 1</i>');" onblur="hideTip(this)">
<option value="" selected disabled>..........</option>
<c:forEach items="${groups}" var="g">
    <option value="${g.groupName}">${g.groupName}</option>
</c:forEach>
</select>
<div id="message" style="font-size:10;"></div>
</td>

<td readOnly></td>
<td readOnly></td>
<td>
<input type="submit" value="search">
</td>
<td readOnly></td>
</form:form>
</tr>

<c:set var="count" value="0" scope="page" />
<c:forEach var="current" items="${students}">
<c:set var="count" value="${count + 1}" scope="page"/>
<form:form method="post" action="student" class="validatedForm" onsubmit="return validateForm(this, options)">
<tr onclick="toggleSelected(this)">
<td>${count}</td>

<td class="tooltips">
<input type="text" name="studentId" value="${current.studentId}" hidden/>
<input type="text" name="firstName" value="${current.firstName}" onfocus="showTip(this, 'student name, e.g. <i>Ivan</i>');" onblur="hideTip(this)"/>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips">
<input type="text" name="lastName" value="${current.lastName}" onfocus="showTip(this, 'last name, e.g. <i>Ivanov</i>');" onblur="hideTip(this)"/>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips">
<input type="date" name="date" value="${current.admissionDate}" style="border: none;font-size: 15;" onfocus="showTip(this, 'pick up a date, e.g. <i>01-FEB-2016</i>');" onblur="hideTip(this)"/>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips">
<select name="selectedGroup" onfocus="showTip(this, 'pick up a group, e.g. <i>group 1</i>');" onblur="hideTip(this)">
<c:forEach items="${groups}" var="g">
<c:choose>
    <c:when test="${g.groupId == current.studentGroup.groupId}">
       <option value="${g.groupName}" selected>${g.groupName}</option>
    </c:when>
    <c:otherwise>
        <option value="${g.groupName}">${g.groupName}</option>
    </c:otherwise>
</c:choose>
</c:forEach>
</select>
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

<td class="tooltips">
<select name="selectedStatus" onfocus="showTip(this, 'pick up a status, e.g. <i>active</i>');" onblur="hideTip(this)">
<c:forEach items="${statusList}" var="status">
<c:choose>
    <c:when test="${status.statusId == current.status.statusId}">
       <option value="${status.statusName}" selected>${status.statusName}</option>
    </c:when>
    <c:otherwise>
        <option value="${status.statusName}">${status.statusName}</option>
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
</form:form>

<form:form method="get" action="student">
<td>
<input type="text" name="studentId" value="${current.studentId}" hidden/>
<input type="submit" name="show" value="details"/>
</td>
</form:form>
</tr>
</c:forEach>

<form:form method="post" action="student" onsubmit="return validateForm(this, options)">
<tr>
<td readOnly></td>

<td class="tooltips">
<input type="text" name="firstName" placeholder="enter first name" onfocus="showTip(this, 'student name, e.g. <i>Ivan</i>');" onblur="hideTip(this)"/>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips">
<input type="text" name="lastName" placeholder="enter last name" onfocus="showTip(this, 'last name, e.g. <i>Ivanov</i>');" onblur="hideTip(this)"/>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips">
<input type="date" name="date" style="background: #E5E5DB;border: none;font-size: 15;" onfocus="showTip(this, 'pick up a date, e.g. <i>01-FEB-2016</i>');" onblur="hideTip(this)"/>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips">
<select name="selectedGroup" onfocus="showTip(this, 'pick up a group, e.g. <i>group 1</i>');" onblur="hideTip(this)">
<option value="" selected disabled>..........</option>
<c:forEach items="${groups}" var="g">
    <option value="${g.groupName}">${g.groupName}</option>
</c:forEach>
</select>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips">
<select name="selectedTerm" onfocus="showTip(this, 'pick up a term, e.g. <i>first</i>');" onblur="hideTip(this)">
<option value="" selected disabled>..........</option>
<c:forEach items="${terms}" var="t">
    <option value="${t.termName}">${t.termName}</option>
</c:forEach>
</select>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips">
<select name="selectedStatus" onfocus="showTip(this, 'pick up a status, e.g. <i>active</i>');" onblur="hideTip(this)">
<option value="" selected disabled>..........</option>
<c:forEach items="${statusList}" var="status">
    <option value="${status.statusName}">${status.statusName}</option>
</c:forEach>
</select>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td>
<input type="submit" name="add" value="add"/>
</td>
<td readOnly></td>
</tr>
</form:form>

</tbody>
</table>
</div>

</jsp:attribute>
</t:general_template>
