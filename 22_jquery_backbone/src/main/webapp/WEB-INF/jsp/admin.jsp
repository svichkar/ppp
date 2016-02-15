<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:admin_template title="Admin Page">
<jsp:attribute name="content_area">

<div>
<p><h4 style="font-family:'Courier New', Courier, monospace;font-weight:100;text-align:center;${color}">${message}</h4></p>
</div>

<div class="divTable">

<table >
<thead>
<th>#</th>
<th>First Name</th>
<th>Last Name</th>
<th>E-mail</th>
<th>Login</th>
<th>Password</th>
<th>Role</th>
<th>Commands</th>
</thead>

<tbody>

<c:set var="count" value="0" scope="page" />
<c:forEach var="current" items="${users}">
<c:set var="count" value="${count + 1}" scope="page"/>
<form:form action="admin" method="post" onsubmit="return validateForm(this, options)">
<tr onclick="toggleSelected(this)">
<td readonly>${count}</td>
<td class="tooltips">
<input type="text" name="userId" value="${current.userId}" hidden/>
<input type="text" name="firstName" value="${current.firstName}" onfocus="showTip(this, 'user name, e.g. <i>Ivan</i>');" onblur="hideTip(this)"/>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>
<td class="tooltips">
<input type="text" name="lastName" value="${current.lastName}" onfocus="showTip(this, 'user surname, e.g. <i>Ivanov</i>');" onblur="hideTip(this)"/>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>
<td class="tooltips">
<input type="text" name="email" value="${current.email}" onfocus="showTip(this, 'user e-mail, e.g. <i>email@mail.ua</i>');" onblur="hideTip(this)"/>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>
<td class="tooltips">
<input type="text" name="login" value="${current.login}" onfocus="showTip(this, 'user login, e.g. <i>myLogin</i>');" onblur="hideTip(this)"/>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>
<td class="tooltips">
<input type="text" name="userPassword" value="${current.userPassword}" onfocus="showTip(this, 'user password, e.g. <i>password</i>');" onblur="hideTip(this)"/>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>
<td class="tooltips">
<select name="selectedRole" onfocus="showTip(this, 'pick up a user role, e.g. <i>user</i>');" onblur="hideTip(this)">
<c:forEach items="${roles}" var="r">
<c:choose>
    <c:when test="${r.roleId == current.role.roleId}">
       <option value="${r.roleName}" selected>${r.roleName}</option>
    </c:when>
    <c:otherwise>
        <option value="${r.roleName}">${r.roleName}</option>
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

<tr>
<form:form  action="admin" method="post" onsubmit="return validateForm(this, options)">
<td readonly></td>

<td class="tooltips">
<input type="text" name="firstName" onfocus="showTip(this, 'user name, e.g. <i>Ivan</i>');" onblur="hideTip(this)" placeholder="first name">
</input>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips"><input type="text" name="lastName" onfocus="showTip(this, 'user surname, e.g. <i>Ivanov</i>');" onblur="hideTip(this)" placeholder="last name">
</input>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips"><input type="text" name="email" onfocus="showTip(this, 'user e-mail, e.g. <i>email@mail.ua</i>');" onblur="hideTip(this)" placeholder="e-mail">
</input>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips">
<input type="text" name="login" onfocus="showTip(this, 'user login, e.g. <i>myLogin</i>');" onblur="hideTip(this)" placeholder="login">
</input>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips">
<input type="text" name="userPassword" onfocus="showTip(this, 'user password, e.g. <i>password</i>');" onblur="hideTip(this)" placeholder="password">
</input>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td class="tooltips">
<select name="selectedRole" onfocus="showTip(this, 'pick up a user role, e.g. <i>user</i>');" onblur="hideTip(this)">
<option value="" selected disabled>..........</option>
<c:forEach items="${roles}" var="r">
    <option value="${r.roleName}">${r.roleName}</option>
</c:forEach>
</select>
<span name="error" style="visibility:hidden" class="errorValidation"></span>
<div id="message" style="font-size:10;"></div>
</td>

<td>
<input type="submit" name="add" value="add""/>
</td>
</form:form>
</tr>

</tbody>
</table>
</div>

</jsp:attribute>
</t:admin_template>