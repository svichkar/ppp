<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Terms" opt="javascript/validator/termOptions.js">
<jsp:attribute name="content_area">
<div>
<p><h4 style="font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;${color}">${message}</h4></p>
</div>

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
<form:form  method="post" action="term" class="validatedForm" onsubmit="return validateForm(this, options)">
<tr onclick="toggleSelected(this)">
<td>${count}</td>
<td class="tooltips">
<input type="text" name="termId" value="${current.termId}" hidden/>
<input type="text" name="termName" value="${current.termName}" onfocus="showTip(this, 'term name, e.g. <i>first</i>');" onblur="hideTip(this)"/>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>
<td>
<input type="submit" name="update" value="update"/>
<input type="submit" name="delete" value="delete"/>
<div id="message" style="font-size:10;"></div>
</td>
</tr>
</form:form>
</c:forEach>

<form:form  method="post" action="term" class="validatedForm" onsubmit="return validateForm(this, options)">
<tr>
<td readOnly></td>
<td class="tooltips">
<input type="text" name="termName" placeholder="enter term name" onfocus="showTip(this, 'term name, e.g. <i>first</i>');" onblur="hideTip(this)"/>
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