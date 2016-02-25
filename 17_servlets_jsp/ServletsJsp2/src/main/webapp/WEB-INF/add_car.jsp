<%-- 
    Document   : create_user
    Created on : Feb 1, 2016, Feb 1, 2016 4:59:06 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Add Car">
    <jsp:attribute name="content_area">
        <form action="add-car" method="post">
            <table>
                <tbody>
                    <tr><td>Serial ID: </td><td><input type="text" size="40" name="new-sid"></td></tr>                   
                    <tr><td>Car Model: </td><td>
                            <select name="new-car-model">
                                <c:forEach var="carType" items="${requestScope.carTypes}">
                                    <option value="${carType.carTypeId}">${carType.brand} ${carType.model}</option>
                                </c:forEach>
                            </select>
                        </td></tr>
                    <tr><td>Owner: </td><td>
                            <select name="new-client">
                                <c:forEach var="client" items="${requestScope.clients}">
                                    <option value="${client.clientId}">${client.firstName} ${client.lastName}</option>
                                </c:forEach>
                            </select>
                        </td></tr>
                    <tr><td colspan="2"><button type="submit" name="status" value="create">create</button></td></tr>
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
