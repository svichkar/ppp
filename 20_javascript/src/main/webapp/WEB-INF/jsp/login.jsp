<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:url value="/style/tooltips.css" var="cssTooltip"/>
<c:url value="/j_spring_security_check" var="loginUrl" />
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link href="<c:url value="images/favicon.png"/>" rel="shortcut icon" type="shortcut/ico">
    <script src="<c:url value="javascript/focus.js"/>" type="text/javascript"></script>
    <link href="${cssTooltip}" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" href="<c:url value="style/login-style.css"/>">
</head>
<body>

<div class="login-card">
    <h1>Student Grade Management</h1><br>
<form:form action="${loginUrl}" method="POST">
<table>
<tbody>
<tr>
        <td class="tooltips">
        <input type="text" name="j_username" placeholder="Login" maxlength="50" onfocus="showTip(this, 'user login, e.g. <i>login</i>');" onblur="hideTip(this)"/>
        <div id="message" style="font-size:10;"></div>
        </td>
</tr>
<tr>
        <td class="tooltips">
        <input type="password" name="j_password" placeholder="Password" maxlength="20" onfocus="showTip(this, 'user password, e.g. <i>password</i>');" onblur="hideTip(this)"/>
        <div id="message" style="font-size:10;"></div>
        </td>
</tr>
<tr>
<td>
<input type="submit" name="submit" class="login login-submit" value="Login"/>
</td>
</tr>
</tbody>
</table>
        <c:if test="${not empty param['error']}">
           <h5>Login or Password is not valid. Please try again.</h5>
        </c:if>
</form:form>
</div>
</body>
</html>