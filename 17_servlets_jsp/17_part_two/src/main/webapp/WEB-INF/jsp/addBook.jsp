<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Find book">
    <jsp:attribute name="content_area">
        <form action="addBook" method="post">
            <table>
                <thead>
                    <tr>
                        <th>Book name</th>
                        <th>category</th>
                        <th>cell</th>
                        <th>author</th>
                        <th>quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="text" name="bookName" required></td>
                        <td>
                            <select size="1" name="bookCategory" required="">
                                <option value="" selected disabled>Choose category</option>
                                <c:forEach var="category" items="${categories}">
                                    <option><c:out value="${category.name}"/></option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select size="1" name="bookCell" required="">
                                <option value="" selected disabled>Choose cell</option>
                                <c:forEach var="cell" items="${cells}">
                                    <option><c:out value="${cell.name}"/></option>
                                 </c:forEach>
                             </select>
                        </td>
                        <td>
                            <select size="5" multiple name="bookAuthor" required>
                                <c:forEach var="author" items="${authors}">
                                    <option><c:out value="${author.lastName}"/></option>
                                 </c:forEach>
                             </select>
                        </td>
                        <td><input type="text" name="bookQuantity" value="1" pattern="^[ 0-9]+$" required></td>
                    </tr>
                </tbody>
             </table>
             <input type="submit" value="Add new book">
        </form>
    </jsp:attribute>
    <jsp:attribute name="message_area">
        <c:if test="${not empty param.message}">
            <p align="center"><c:out value="${param.message}"/></p>
        </c:if>
    </jsp:attribute>
</t:template>