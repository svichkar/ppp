<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Login Page">
	<jsp:attribute name="content_area">
       <h2>Students Grade Web Application</h2>
       <form action="main" method="post">
       <p>
				<strong>Login:</strong> 
       <br /><input type="text" maxlength="25" size="40" name="login" />
			</p>
       <p>
				<strong>Password:</strong> 
       <br /><input type="password" maxlength="25" size="40"
					name="password" />
			</p>
       <input type="submit" value="Log in" />
       </form>
       <c:if test="${not empty param.message}">
       <div class="message-info">
				<c:out value="${param.message}" />
			</div>
       </c:if>
    </jsp:attribute>
</t:template>