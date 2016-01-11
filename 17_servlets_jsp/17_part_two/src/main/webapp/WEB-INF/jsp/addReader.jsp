<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Find book">
    <jsp:attribute name="content_area">
        <form action="addReader" method="post">
            <table>
                <thead>
                    <tr>
                        <th>First name</th>
                        <th>Last_name</th>
                        <th>Phone</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="text" name="clientFirstName" required></td>
                        <td><input type="text" name="clientLastName" required></td>
                        <td><input type="text" name="clientPhone"></td>
                        <td><input type="email" name="clientEmail"></td>
                    </tr>
                </tbody>
             </table>
             <input type="submit" value="Add new reader">
                    <c:if test="${not empty clients}">
                        <table border="1">
                             <thead>
                                 <tr><th>clientId</th><th>clientName</th><th>clientPhone</th><th>clientEmail</th></tr>
                             </thead>
                             <tbody>
                                    <c:forEach var="client" items="${clients}">
                                       <tr>
                                           <td><c:out value="${client.clientId}"/></td>
                                           <td><c:out value="${client.lastName} ${client.firstName}"/></td>
                                           <td><c:out value="${client.phone} "/></td>
                                           <td><c:out value="${client.email}"/></td>
                                       </tr>
                                   </c:forEach>
                             </tbody>
                        </table>
                    </c:if>
        </form>
    </jsp:attribute>
    <jsp:attribute name="message_area">
        <c:if test="${not empty param.message}">
            <p align="center"><c:out value="${param.message}"/></p>
        </c:if>
    </jsp:attribute>
</t:template>