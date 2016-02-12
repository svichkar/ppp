<%-- 
    Document   : index
    Created on : Jan 26, 2016, Jan 26, 2016 3:13:30 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Home Page">
    <jsp:attribute name="content_area">        
        <c:if test="${empty sessionScope.name}">
            <p>Login to gain more rights</p>
        </c:if>
        <c:if test="${not empty sessionScope.name}">
            <p>Your name is: <c:out value="${sessionScope.name}"/></p>
            <p>Your role is: <c:out value="${sessionScope.role}"/><p>
            </c:if>
        </jsp:attribute>
        <jsp:attribute name="message">        
        </jsp:attribute>
    </t:general_template>
