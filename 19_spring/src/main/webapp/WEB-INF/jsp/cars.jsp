<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general title="Cars">
    <jsp:attribute name="internal_content_area">
        <c:if test="${not empty carsSet}">
            <table border="1">
                <caption>Cars</caption>
                    <tr>
                        <th>login</th>
                        <th>client_name</th>
                        <th>brand</th>
                        <th>model</th>
                        <th>VIN</th>
                    </tr>
                    <c:forEach var="car" items="${carsSet}" varStatus="status">
                        <tr>
                            <form action="cars" name="upDel" method="post">
                                <input type="hidden" name="car_id" value="<c:out value="${car.carId}"/>">
                                <input type="hidden" name="client_id" value="<c:out value="${car.client.clientId}"/>">
                                <td><input type="text" name="loginUser" value ="<c:out value="${car.client.user.login}"/>" readonly></td>
                                <td><input type="text" name="client_name" value ="<c:out value="${car.client.getFullName()}"/>" readonly></td>
                                <td><input type="text" name="brand" value ="<c:out value="${car.carType.brand}"/>"></td>
                                <td><input type="text" name="model_name" value ="<c:out value="${car.carType.modelName}"/>"></td>
                                <td><input type="text" name="VIN" value ="<c:out value="${car.serialVIN}"/>"></td>
                                <td><input type="submit" value="edit" name="submitButton"></td>
                                <td><input type="submit" value="delete" name="submitButton"></td>
                            </form>
                        </tr>
                    </c:forEach>
                    </table>
        </c:if>
        <table border="1">
                        <caption>Add car</caption>
                            <tr>
                                <th>client_name</th>
                                <th>brand</th>
                                <th>model</th>
                                <th>VIN</th>
                            </tr>
                    <tr>
                        <form action="cars" name="add" method="post">
                            <td>
                                <select name="clients" required>
                                    <c:forEach var="client" items="${clientsSet}">
                                        <option value="<c:out value="${client.clientId}"/>"><c:out value="${client.firstName} ${client.lastName}"/></option>
                                    </c:forEach>
                                 </select>
                            </td>
                            <td><input type="text" name="brand" required/></td>
                            <td><input type="text" name="model_name" required/></td>
                            <td><input type="text" name="VIN" required/></td>
                            <td><input type="submit" value="add" name="submitButton"></td>
                        </form>
                    </tr>
            </table>
            <c:out value="${msg}"/>
    </jsp:attribute>
</t:general>