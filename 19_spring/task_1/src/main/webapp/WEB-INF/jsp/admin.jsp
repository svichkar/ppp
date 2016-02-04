<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:admin_template title="Admin Page">
    <jsp:attribute name="content_area">

${message}

<div class="divTable">

<table>
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
<form:form action="admin" method="post">
<tr>
<td readonly>${count}</td>
<td>
<input type="text" name="id" value="${current.userId}" hidden/>
<input type="text" name="name" value="${current.firstName}" maxlength="30" pattern="[A-Za-z]{3,30}" required/>
</td>
<td>
<input type="text" name="lastName" value="${current.lastName}" maxlength="30" pattern="[A-Za-z]{3,30}" required/>
</td>
<td>
<input type="text" name="email" value="${current.email}" maxlength="50" pattern="\S+@[a-z]+.[a-z]+" required/>
</td>
<td>
<input type="text" name="login" value="${current.login}" maxlength="20" pattern="[^?-??-???]{3,20}" required/>
</td>
<td>
<input type="text" name="pass" value="${current.userPassword}" maxlength="20" pattern="[^?-??-???]{3,20}" required/>
</td>
<td>
<select name="role">
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
</td>
<td>
<input type="submit" name="operation" value="update"/>
<input type="submit" name="operation" value="delete"/>
</td>
</tr>
</form:form>
</c:forEach>

<tr>
<form:form  action="admin" method="post">
<td readonly></td>
<td><input type="text" name="firstName" pattern="[A-Za-z]{3,30}" required placeholder="first name"></input></td>
<td><input type="text" name="lastName" pattern="[A-Za-z]{3,30}" required placeholder="second name"></input></td>
<td><input type="text" name="email" pattern="\S+@[a-z]+.[a-z]+" required placeholder="e-mail"></input></td>
<td><input type="text" name="login" required placeholder="login"></input></td>
<td><input type="text" name="userPassword" required placeholder="password"></input></td>
<td>
<select name="selectedRole" required>
<option value="" selected disabled></option>
<c:forEach items="${roles}" var="r">
    <option value="${r.roleName}">${r.roleName}</option>
</c:forEach>
</select>
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