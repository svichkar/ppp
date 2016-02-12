<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:common_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
        <form action="j_spring_security_check" method="POST">
        <table align="center">
        	<tr>
					<td>Login:</td>
					<td><input type="text" name="login" value="your name" /></td>
				</tr>
        	<tr>
					<td>Password:</td>
					<td><input type="password" name="password" /></td>
				</tr>
        	<tr>
					<td><input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /></td>
					<td><input type="submit" value="Sign in"></td>
				</tr>
         </table> 
        </form>
        <div id="hint" /> 
    </jsp:attribute>
</t:common_template>