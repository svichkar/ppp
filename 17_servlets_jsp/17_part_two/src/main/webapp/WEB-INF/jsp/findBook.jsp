<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Find book">
    <jsp:attribute name="sidebar_area">
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
    <jsp:attribute name="content_area">
        <c:choose>
            <c:when test="${not empty books}">
                <table border="1">
                           <thead>
                               <tr><th>bookId</th><th>bookName</th><th>category</th><th>cell</th><th>author</th><th>ticket id if in rent</th></tr>
                           </thead>
                           <tbody>
                           <c:forEach var="book" items="${books}">
                               <tr>
                                   <td><c:out value="${book.book.bookId}"/></td>
                                   <td><c:out value="${book.book.name} "/></td>
                                   <td><c:out value="${book.category.name}"/></td>
                                   <td><c:out value="${book.cell.name}"/></td>
                                   <td>
                                        <c:if test="${not empty book.authors}">
                                            <c:forEach var="author" items="${book.authors}">
                                                <c:out value="${author.firstName} "/><c:out value="${author.lastName}"/><br>
                                            </c:forEach>
                                        </c:if>
                                   </td>
                                   <td>
                                        <c:if test="${not empty book.ticket}">
                                            <c:out value="${book.ticket.ticketId}"/>
                                        </c:if>
                                   </td>
                               </tr>
                           </c:forEach>
                           </tbody>
                       </table>
            </c:when>
            <c:otherwise>
                <form action="bookManagement" method="post">
                            <p><input type="radio" checked name="searchCriteria" value="all">Find all<p>
                            <p><input type="radio" name="searchCriteria" value="name">By name<p>
                            <p><input type="radio" name="searchCriteria" value="author">By author<p>
                            <p><input type="radio" name="searchCriteria" value="category">By category<p>
                            <p><input type="text" name="searchWord"></p>
                            <p><input type="submit" value="Search"></p>
                        </form>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
    <jsp:attribute name="message_area">
        <c:if test="${not empty param.message}">
            <p align="center"><c:out value="${param.message}"/></p>
        </c:if>
    </jsp:attribute>
</t:template>