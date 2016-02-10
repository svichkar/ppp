<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Find book">
    <jsp:attribute name="content_area">
        <p><a href="bookManagement">Choose book</a></p>
        <c:if test="${not empty param.bookId}">
            <table border="1" class="table">
                     <thead>
                         <tr><th>Give to client</th><th>bookId</th><th>bookName</th><th>category</th><th>cell</th><th>author</th></tr>
                     </thead>
                     <tbody>
                            <form action="loanManagement" method="post" name="createTicket">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="giveBook" value="true">
                            <c:forEach var="book" items="${books}">
                               <tr>
                                   <td><input type="checkbox" name="bookId" value="${book.bookId}" title="choose the book"></td>
                                   <td><c:out value="${book.bookId}"/></td>
                                   <td><c:out value="${book.bookName} "/></td>
                                   <td><c:out value="${book.category.categoryName}"/></td>
                                   <td><c:out value="${book.cell.cellName}"/></td>
                                   <td>
                                        <c:if test="${not empty book.authors}">
                                            <c:forEach var="author" items="${book.authors}">
                                                <c:out value="${author.authorFullName()} "/><br>
                                            </c:forEach>
                                        </c:if>
                                   </td>
                               </tr>
                           </c:forEach>
                           <tr>
                                <td>Choose Client</td>
                                <td colspan="2">
                                    <select size="1" name="client" required title="choose the client">
                                        <option value="" selected disabled>Choose client</option>>
                                        <c:forEach var="client" items="${clients}">
                                            <option value="${client.clientId}"><c:out value="${client.clientFullName()}"/></option>
                                    </c:forEach>
                             </select>
                                </td>
                                <td colspan="3"><input type="submit" value="Get checked books to client" name="giveTicket"></td>
                           </tr>

                         <form>
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