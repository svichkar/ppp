<%-- 
    Document   : add_car_model
    Created on : Feb 4, 2016, Feb 4, 2016 12:51:21 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Add Employee Category">
    <jsp:attribute name="content_area">
        <form action="add-employee-category" method="post">
            <table>
                <tbody>
                    <tr><td>Category Name: </td><td><input type="text" size="40" name="new-category-name"></td></tr>                  
                    <tr><td colspan="2"><input type="submit" name="status" value="create"></td></tr>
                </tbody>
            </table>
        </form>
    </jsp:attribute>
    <jsp:attribute name="message">
        <c:if test="${not empty requestScope.message}">
            <c:out value="${requestScope.message}"/>
        </c:if>
    </jsp:attribute>
</t:general_template>
