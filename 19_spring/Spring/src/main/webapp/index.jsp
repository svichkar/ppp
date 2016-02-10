<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="<c:url value="/script/validation.js" />"></script>
</head>
    <body>
        <form action="<c:url value="/j_spring_security_check"></c:url>" onsubmit="return validateForm(this,optionsForLoginForm);" method="post"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <table>
                    <tr>
                        <td>Username:</td>
                        <td><input type="text" name="login" title="enter username here"/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password" title="enter password here"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right"><input type="submit" value="Login"/></td>
                    </tr>
                </table>
            </form>
            <div id="hint"></div>
            <c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
                ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
            </c:if>
            <c:out value="${param.msg}"/>
            <div id="errorMsg"></div>
            <script type="text/javascript" src="script/tooltip.js"></script>
    </body>
</html>
