<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Find book">
    <jsp:attribute name="content_area">
        <form action="bookManagement" method="post">
             <p><input type="radio" checked name="searchCriteria" value="all">Find all<p>
             <p><input type="radio" name="searchCriteria" value="name">By name<p>
             <p><input type="radio" name="searchCriteria" value="author">By author<p>
             <p><input type="radio" name="searchCriteria" value="category">By category<p>
             <p><input type="text" name="searchWord"></p>
             <p><input type="submit" value="Search"></p>
        </form>
            <c:if test="${not empty books}">
                <table border="1">
                     <thead>
                         <tr><th></th><th>bookId</th><th>bookName</th><th>category</th><th>cell</th><th>author</th><th>ticket id if book in rent</th></tr>
                     </thead>
                     <tbody>
                            <form action="loanManagement" method="post" name="giveBook">
                                         <input type="submit" value="Get checked books to client" name="giveBook">
                            <c:forEach var="book" items="${books}">
                               <tr>
                                   <td>
                                        <c:if test="${empty book.getCurrentOpenTicket()}">
                                            <input type="checkbox" name="bookId" value="${book.bookId}">
                                        </c:if>
                                   </td>
                                   <td><c:out value="${book.bookId}"/></td>
                                   <td><c:out value="${book.bookName} "/></td>
                                   <td><c:out value="${book.category.categoryName}"/></td>
                                   <td><c:out value="${book.cell.cellName}"/></td>
                                   <td>
                                            <c:forEach var="author" items="${book.authors}">
                                                <c:out value="${author.authorFullName()} "/><br>
                                            </c:forEach>
                                   </td>
                                   <td>
                                        <c:if test="${not empty book.getCurrentOpenTicket()}">
                                            <c:out value="${book.getCurrentOpenTicket().ticketId}"/>
                                        </c:if>
                                   </td>
                               </tr>
                           </c:forEach>
                         </form>
                     </tbody>
                </table>
            </c:if>
    </jsp:attribute>
    <jsp:attribute name="message_area">
        <c:if test="${not empty msg}">
            <p align="center"><c:out value="${msg}"/></p>
        </c:if>
    </jsp:attribute>
</t:template>