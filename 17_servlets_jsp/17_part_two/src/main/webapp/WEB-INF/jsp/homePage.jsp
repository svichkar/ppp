<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Main page">
    <jsp:attribute name="content_area">
    <img src="webContent/images/background_library.png">
        <p>Welcome</p>
        <p>Your role is: <c:out value="${sessionScope.role}"/><p>
        <p><a href="bookManagement">Find book</a></p>
        <p><a href="/WEB-INF/jsp/addBook.jsp">Add book</a></p>
        <p><a href="/WEB-INF/jsp/addReader.jsp">Add reader</a></p>
        <p><a href="/WEB-INF/jsp/loanBook.jsp">Loan book</a></p>
        <c:if test="${sessionScope.role=='ADMIN'}">
            <p><a href="/WEB-INF/jsp/addUser.jsp">Add user</a></p>
            <p><a href="/WEB-INF/jsp/addCategory.jsp">Add Category</a></p>
        </c:if>
    </jsp:attribute>
    <jsp:attribute name="message_area">
        <c:if test="${not empty param.message}">
            <p align="center"><c:out value="${param.message}"/></p>
        </c:if>
    </jsp:attribute>
</t:template>
