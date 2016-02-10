<%-- 
    Document   : login
    Created on : Jan 28, 2016, Jan 28, 2016 2:32:08 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Login Page">
    <jsp:attribute name="content_area">
        <h3>Hello, please log in:</h3>
        <form action="login" method=post>
            <p><strong>User Name: </strong>
                <input type="text" name="j_username" size="25"></p>
            <p><strong>Password: </strong>
                <input type="password" size="15" name="j_password"></p>          
            <p><input type="submit" value="Login"></p>
        </form>
    </jsp:attribute>
    <jsp:attribute name="message">
        <c:if test="${not empty requestScope.message}">
            <c:out value="${requestScope.message}"/>
        </c:if>
    </jsp:attribute>
</t:general_template>
