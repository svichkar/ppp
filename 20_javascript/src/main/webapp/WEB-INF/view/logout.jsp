<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Logout Page">
	<jsp:attribute name="content_area">
       <h2>Good Bye!</h2>
       <p>You are successfully logged out! Please log in back to application to continue work.</p>
       <p>
			<a href="<c:url value="/index.jsp"/>">Login Page</a>
		</p>
    </jsp:attribute>
</t:template>