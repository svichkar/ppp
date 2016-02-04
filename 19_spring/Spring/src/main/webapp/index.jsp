<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <body>

        <form action="j_spring_security_check" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <table>
                    <tr>
                        <td>Username:</td>
                        <td><input type="text" name="login" title="not empty"/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password" /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right"><input type="submit" value="Login"/></td>
                    </tr>
                </table>
            </form>
            <c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
                ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
            </c:if>
            <c:out value="${param.msg}"/>
            <script type="text/javascript" src="script/tooltip.js"></script>
    </body>
</html>
