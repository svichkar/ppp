<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true"%>
<%@attribute name="content_area" fragment="true"%>
<%@attribute name="internal_content_area" fragment="true"%>
<%@attribute name="sidebar_area" fragment="true"%>
<c:url value="/style/style.css" var="cssUrl"/>
<!DOCTYPE html>
<html>
<head>
  <title>${title}</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link href="${cssUrl}" type="text/css" rel="stylesheet"/>
  <jsp:invoke fragment="head_area" />
  <p id="header">Your are logged in as: <sec:authentication property="principal.username" /></p>
</head>
<body>
<div id=container>
    <div id="sidebar">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <p><a href="homepage" class="customLink">Home Page</a></p>
            <p><a href="orders" class="customLink">Orders</a></p>
            <p><a href="workers" class="customLink">Workers</a></p>
            <p><a href="cars" class="customLink">Cars</a></p>
            <p><a href="clients" class="customLink">Clients</a></p>
            <jsp:invoke fragment="sidebar_area"/>
        </sec:authorize>
    </div>
    <div id="content">
        <form action="<c:url value="/logout"></c:url>" method="get"/>
                <input type="submit" value="Logout">
        </form>
        <jsp:invoke fragment="content_area" />
        <jsp:invoke fragment="internal_content_area" />
        <c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
            ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
        </c:if>
    </div>
</div>
</body>
</html>