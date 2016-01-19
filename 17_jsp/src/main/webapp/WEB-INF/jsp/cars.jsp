<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general title="Cars">
    <jsp:attribute name="internal_content_area">
        <c:if test="${not empty carsBeanList}">
            <table border="1">
                <caption>Cars</caption>
                    <tr>
                        <th>login</th>
                        <th>client_name</th>
                        <th>brand</th>
                        <th>model</th>
                        <th>VIN</th>
                    </tr>
                    <c:forEach var="car" items="${carsBeanList}" varStatus="status">
                        <tr>
                            <form action="cars" name="upDel" method="post">
                                <input type="hidden" name="login" value="<c:out value="${param.login}"/>">
                                <input type="hidden" name="car_id" value="<c:out value="${car.carId}"/>">
                                <input type="hidden" name="client_id" value="<c:out value="${car.clientId}"/>">
                                <td><input type="text" name="loginUser" value ="<c:out value="${car.login}"/>" readonly></td>
                                <td><input type="text" name="client_name" value ="<c:out value="${car.clientFSName}"/>" readonly></td>
                                <td><input type="text" name="brand" value ="<c:out value="${car.carBrand}"/>"></td>
                                <td><input type="text" name="model_name" value ="<c:out value="${car.carModel}"/>"></td>
                                <td><input type="text" name="VIN" value ="<c:out value="${car.carVIN}"/>"></td>
                                <td><input type="submit" value="edit" name="edit"></td>
                                <td><input type="submit" value="delete" name="delete"></td>
                            </form>
                        </tr>
                    </c:forEach>
                    <tr>
                        <form action="cars" name="add" method="post">
                            <td></td>
                            <td>
                                <select name="clients" required>
                                    <c:forEach var="client" items="${clientsList}">
                                        <option value="<c:out value="${client.clientId}"/>"><c:out value="${client.firstName} ${client.lastName}"/></option>
                                    </c:forEach>
                                 </select>
                            </td>
                            <td><input type="text" name="brand" required/></td>
                            <td><input type="text" name="model_name" required/></td>
                            <td><input type="text" name="VIN" required/></td>
                            <td><input type="submit" value="add" name="add"></td>
                        </form>
                    </tr>
            </table>
            <c:out value="${param.message}"/>
       </c:if>
    </jsp:attribute>
</t:general>