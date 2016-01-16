<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Find book">
    <jsp:attribute name="content_area">
        <form action="categoryManagement" method="post">
             Category name : <input type="text" name="categoryName" required>
             <input type="submit" value="Add new category">
        </form>
        <c:if test="${not empty categories}">
                        <table border="1">
                             <thead>
                                 <tr><th>categoryId</th><th>categoryName</th></tr>
                             </thead>
                             <tbody>
                                    <c:forEach var="category" items="${categories}">
                                       <tr>
                                           <td><c:out value="${category.categoryId}"/></td>
                                           <td><c:out value="${category.categoryName} "/></td>
                                       </tr>
                                   </c:forEach>
                             </tbody>
                        </table>
                    </c:if>
    </jsp:attribute>
    <jsp:attribute name="message_area">
        <c:if test="${not empty param.message}">
            <p align="center"><c:out value="${param.message}"/></p>
        </c:if>
    </jsp:attribute>
</t:template>