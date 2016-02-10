<%-- 
    Document   : create_user
    Created on : Feb 1, 2016, Feb 1, 2016 4:59:06 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Create User">
    <jsp:attribute name="content_area">
        <form action="add-client" method="post">
            <table>
                <tbody><tr><td>First Name: </td><td><input type="text" size="40" name="new-fname"></td></tr>
                    <tr><td>Last Name: </td><td><input type="text" size="40" name="new-lname"></td></tr>
                    <tr><td>Login: </td><td><input type="text" size="40" name="new-login"></td></tr>
                    <tr><td>Password: </td><td><input type="password" size="40" name="new-password"></td></tr>
                    <tr>
                        <td><input type="hidden" name="new-role" value="${requestScope.webRole.webRoleId}">Role: </td>
                        <td>user</td>
                    </tr>
                    <tr><td colspan="2"><input type="submit" name="status" value="create"></td></tr>
                </tbody></table>
        </form>
    </jsp:attribute>
    <jsp:attribute name="message">
        <c:if test="${not empty requestScope.message}">
            <c:out value="${requestScope.message}"/>
        </c:if>
    </jsp:attribute>
</t:general_template>
