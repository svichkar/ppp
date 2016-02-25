<%-- 
    Document   : general_template
    Created on : Jan 27, 2016, Jan 27, 2016 5:04:22 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true"%>
<%@attribute name="content_area" fragment="true"%>
<%@attribute name="sidebar_area" fragment="true"%>
<%@attribute name="message" fragment="true"%>
<c:url value="/style/style.css" var="cssUrl"/>
<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta name="description" content="car service" />
        <meta name="keywords" content="car, service, car service" />
        <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
        <link href="${cssUrl}" type="text/css" rel="stylesheet"/>
        <jsp:invoke fragment="head_area" />
    </head>

    <body>
        <div id="main">
            <div id="header">
                <div id="logo">
                    <div id="logo_text">
                        <!-- class="logo_colour", allows you to change the colour of the text -->
                        <h1><a href="/">Servlet <span id="logo_colour">JSP</span> 2</a></h1>
                    </div>
                </div>
                <div id="menubar">
                    <ul id="menu"> 
                        <c:if test="${not empty sessionScope.name}">
                            <li  <c:if test="${title=='Home Page'}"> class="selected"</c:if>><a href="/homepage">Home</a></li>
                                <c:if test="${sessionScope.role ne 'user'}">
                                <li  <c:if test="${title=='Cars'}"> class="selected"</c:if>><a href="/cars">Cars</a></li>
                                <li  <c:if test="${title=='Clients'}"> class="selected"</c:if>><a href="/clients">Clients</a></li>
                                <li  <c:if test="${title=='Car Models'}"> class="selected"</c:if>><a href="/car-models">Car Models</a></li>
                                <li  <c:if test="${title=='Car Orders'}"> class="selected"</c:if>><a href="/car-orders">Orders</a></li>
                                    <c:if test="${sessionScope.role ne 'employee'}">
                                    <li  <c:if test="${title=='Employees'}"> class="selected"</c:if>><a href="/employees">Employees</a></li>
                                    <li  <c:if test="${title=='Employee Categories'}"> class="selected"</c:if>><a href="/employee-categories">Employee Categories</a></li>
                                    </c:if>
                                </c:if>
                            <ul id="right-menu"> 
                                <c:if test="${sessionScope.role eq 'admin'}">
                                    <li  <c:if test="${title=='Administration'}"> class="selected"</c:if>><a href="/administration">Administration</a></li>
                                    </c:if>
                                <li><a href="/logout">Logout</a></li>
                            </ul> 
                        </c:if>
                    </ul>
                </div>
                <div class="message">
                    <jsp:invoke fragment="message"/>
                </div>
            </div>
            <div id="site_content">
                <c:if test="${not empty sessionScope.name}">
                    <h3>Welcome <c:out value="${sessionScope.name}"/></h3>
                </c:if>
                <div id="content">
                    <jsp:invoke fragment="content_area" />
                </div>
            </div>
            <div id="footer">
                <p>Copyright &copy; 2016 | All Rights Reserved</p>
            </div>
        </div>
    </body>
</html>