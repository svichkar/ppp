<%-- 
    Document   : create_user
    Created on : Feb 1, 2016, Feb 1, 2016 4:59:06 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Add Assignment">
    <jsp:attribute name="content_area">
        <form action="add-assignment" method="post">
            <table>
                <tbody>                
                    <tr>
                        <td>Order: </td>
                        <td><input type="hidden" name="car-order-id" value="${carOrderBean.carOrderId}">id:[${carOrderBean.carOrderId}] ${carOrderBean.carBean.carType.brand}-${carOrderBean.carBean.carType.model} SID:[${carOrderBean.carBean.serialId}] date:&nbsp;<fmt:formatDate type="both"  value="${carOrderBean.startDate}"/></td>
                    </tr>
                    <tr>
                        <td>Assignee: </td>
                        <td>
                            <select name="employee-id">
                                <c:forEach var="employeeBean" items="${requestScope.employeeBeans}">
                                    <option value="${employeeBean.employeeId}">${employeeBean.firstName} ${employeeBean.lastName} - ${employeeBean.employeeCategory.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="status" value="create">
                        </td>
                    </tr>
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
