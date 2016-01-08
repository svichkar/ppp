<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:template title="Library welcome page">
    <jsp:attribute name="content_area">
        <form action="login" method="post">
            <div id="loginForm" align="center">
                <table class="table" border="1" width="30%" cellpadding="3">
                    <thead>
                        <tr>
                            <th colspan="2">Welcome, Please login</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>User name</td>
                            <td><input type="text" name="userName"/></td>
                        </tr>
                        <tr>
                            <td>User password</td>
                            <td><input type="password" name="userPassword"/></td>
                        </tr>
                        <tr>
                            <td><input class="button" type="submit" name="login" value="Login"/></td>
                            <td><input class="button" type="submit" name="registration" value="Registration"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><a href="retrievePassword.jsp">Forgot password?</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </jsp:attribute>
    <jsp:attribute name="message_area">
        <c:if test="${not empty param.message}">
            <p align="center"><c:out value="${param.message}"/></p>
        </c:if>
    </jsp:attribute>
</t:template>
