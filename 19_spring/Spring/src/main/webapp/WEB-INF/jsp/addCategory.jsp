<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Find book">
    <jsp:attribute name="content_area">
        <form action="<c:url value="/categoryManagement"></c:url>" onsubmit="return validateForm(this,optionsForAddCategoryForm);" method="POST"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
             Category name : <input type="text" name="categoryName" required title="enter categoryName here">
             <input type="submit" value="Add new category">
        </form>
        <c:if test="${not empty categories}">
                        <table border="1" class="table">
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
        <c:if test="${not empty msg}">
            <p align="center"><c:out value="${msg}"/></p>
        </c:if>
        <div id="errorMsg"></div>
    </jsp:attribute>
</t:template>