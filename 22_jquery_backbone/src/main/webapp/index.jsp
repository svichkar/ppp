<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template title="Login Page">
	<jsp:attribute name="content_area">
       <h2>Students Grade Web Application</h2>
       <form action="<c:url value='j_spring_security_check' />" method="post">       
       <p>
				<strong>Login:</strong> 
       <br /><input type="text" maxlength="25" size="40" name="login" />
       <span class="hint">Enter login in text format</span>
			</p>
       <p>
				<strong>Password:</strong> 
       <br /><input type="password" maxlength="25" size="40"
					name="password" />
				<span class="hint">Enter password</span>
			</p>
       <p/><input type="submit" value="Log in" />
       <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
       </form>
       <c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
       <div class="message-info">
				${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
			</div>       
       </c:if>
              <div class="message-info">
				<c:out value="${param.message}" />
			</div>
    </jsp:attribute>
</t:template>
