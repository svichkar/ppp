<%-- 
    Document   : administration
    Created on : Jan 28, 2016, Jan 28, 2016 5:41:08 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Cars">
    <jsp:attribute name="content_area">
        <form method='post'>
            <table border='1'>
                <tr>
                    <th><button formaction='add-car' formmethod='get'>add</button></th>
                    <th>Car ID</th>
                    <th>Serial ID</th> 
                    <th>Car Model</th>
                    <th>Owner</th>
                </tr>
                <c:forEach var="carBean" items="${requestScope.carBeans}">
                    <tr>
                        <c:choose>
                            <c:when test="${param.edit eq carBean.carId}">
                                <td>
                                    <button formaction='cars' formmethod='post' name='action' value='edit'>ok</button>
                                    <button formaction='cars' formmethod='get' name='action' value='cancel' >cancel</button>
                                </td>   
                                <td><input type='hidden' name='car-id' value='${carBean.carId}'/><c:out value="${carBean.carId}"/></td>
                                <td><input type='text' size='20' name='new-sid' value="${carBean.serialId}"/></td> 
                                <td>
                                    <select name="new-car-type-id">
                                        <c:forEach var="carType" items="${requestScope.carTypes}">
                                            <option ${carType.carTypeId eq carBean.carType.carTypeId?"selected":""} value="${carType.carTypeId}">${carType.brand} ${carType.model}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="new-client-id">
                                        <c:forEach var="clientBean" items="${requestScope.clientBeans}">
                                            <option ${clientBean.clientId eq carBean.clientBean.clientId?"selected":""} value="${clientBean.clientId}">${clientBean.firstName} ${clientBean.lastName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </c:when>
                            <c:when test="${param.delete eq carBean.carId}">
                                <td>
                                    <button formaction='cars' formmethod='post' name='action' value='delete' >ok</button>
                                    <button formaction='cars' formmethod='get' name='action' value='cancel"/>' >cancel</button>
                                </td>
                                <td><input type='hidden' name='car-id' value='${carBean.carId}'/><c:out value="${carBean.carId}"/></td>
                                <td><c:out value="${carBean.serialId}"/></td> 
                                <td><c:out value="${carBean.carType.brand} ${carBean.carType.model}"/></td>
                                <td><c:out value="${carBean.clientBean.firstName} ${carBean.clientBean.lastName}"/></td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <button formaction='cars' formmethod='get' name='edit' value='<c:out value="${carBean.carId}"/>' >edit</button>
                                    <button formaction='cars' formmethod='get' name='delete' value='<c:out value="${carBean.carId}"/>' >delete</button>
                                </td>
                                <td><c:out value="${carBean.carId}"/></td>
                                <td><c:out value="${carBean.serialId}"/></td> 
                                <td><c:out value="${carBean.carType.brand} ${carBean.carType.model}"/></td>
                                <td><c:out value="${carBean.clientBean.firstName} ${carBean.clientBean.lastName}"/></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>                 
                </c:forEach>
            </table>
        </form>
    </jsp:attribute>
    <jsp:attribute name="message">
        <c:if test="${not empty requestScope.message}">
            <c:out value="${requestScope.message}"/>
        </c:if>
    </jsp:attribute>
</t:general_template>