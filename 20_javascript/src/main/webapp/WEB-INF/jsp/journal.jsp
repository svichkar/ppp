<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Journals" opt="javascript/validator/journalOptions.js">
<jsp:attribute name="content_area">

<div>
<p><h4 style="font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;${color}">${message}</h4></p>
</div>

 <div>
 <table>
 <thead>
     <tr>
         <th>#</th>
         <th>Name</th>
         <th>Last Name</th>
         <th>Group</th>
         <th>Subject</th>
         <th>Grade</th>
         <th>Commands</th>
     </tr>
 </thead>
 <tbody>

<c:set var="count" value="0" scope="page" />
<c:forEach var="current" items="${journals}">
<c:set var="count" value="${count + 1}" scope="page"/>
<form:form  method="post" action="journal" onsubmit="return validateForm(this, options)">
<tr onclick="toggleSelected(this)">
<td>${count}</td>
<td>
<input type="text" name="journalId" value="${current.journalId}" hidden/>
<input type="text" name="firstName" value="${current.student.firstName}" readOnly/>
</td>
<td>
<input type="text" name="lastName" value="${current.student.lastName}" readOnly/>
</td>
<td>
<input type="text" name="groupName" value="${current.student.studentGroup.groupName}" readOnly/>
 </td>
<td>
<input type="text" name="subjectName" value="${current.subject.subjectName}" readOnly/>
</td>

<td class="tooltips">
<select name="selectedGrade" onfocus="showTip(this, 'pick up a grade, e.g. <i>good</i>');" onblur="hideTip(this)">
    <c:forEach items="${grades}" var="gr">
    <c:choose>
        <c:when test="${gr.gradeId == current.grade.gradeId}">
           <option value="${gr.gradeName}" selected>${gr.gradeName}</option>
        </c:when>
        <c:otherwise>
            <option value="${gr.gradeName}">${gr.gradeName}</option>
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

<form:form method="post" action="journal" onsubmit="return validateForm(this, options)">
<tr>
<td readOnly></td>

<td class="tooltips">
<input type="text" name="firstName" placeholder="enter student name" onfocus="showTip(this, 'student name, e.g. <i>Ivan</i>');" onblur="hideTip(this)"/>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips">
<input type="text" name="lastName" placeholder="enter student last name" onfocus="showTip(this, 'last name, e.g. <i>Ivanov</i>');" onblur="hideTip(this)"/>
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
<select name="selectedSubject" onfocus="showTip(this, 'pick up a subject, e.g. <i>Physics</i>');" onblur="hideTip(this)">
<option value="" selected disabled>..........</option>
<c:forEach items="${subjects}" var="s">
<option value="${s.subjectName}">${s.subjectName}</option>
</c:forEach>
</select>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips">
<select name="selectedGrade" onfocus="showTip(this, 'pick up a grade, e.g. <i>good</i>');" onblur="hideTip(this)">
<option value="" selected disabled>..........</option>
<c:forEach items="${grades}" var="gr">
<option value="${gr.gradeName}">${gr.gradeName}</option>
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
