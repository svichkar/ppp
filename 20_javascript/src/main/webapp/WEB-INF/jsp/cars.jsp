<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
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
                        <form action="cars" name="upDel" onsubmit="return validateForm(this,optionsForCarForm);"
                              method="post">
                            <input type="hidden" name="car_id" value="<c:out value="${car.carId}"/>">
                            <input type="hidden" name="client_id" value="<c:out value="${car.client.clientId}"/>">
                            <td><input title="This is user login. READ ONLY" type="text" name="loginUser"
                                       value="<c:out value="${car.client.user.login}"/>" readonly></td>
                            <td><input title="This is client full name. READ ONLY" type="text" name="client_name"
                                       value="<c:out value="${car.client.getFullName()}"/>" readonly></td>
                            <td><input title="This is brand of car" type="text" name="brand"
                                       value="<c:out value="${car.carType.brand}"/>"></td>
                            <td><input title="This is model name of car" type="text" name="model_name"
                                       value="<c:out value="${car.carType.modelName}"/>"></td>
                            <td><input title="This is serial VIN number" type="text" name="VIN"
                                       value="<c:out value="${car.serialVIN}"/>"></td>
                            <td><input type="submit" value="edit" name="submitButton"></td>
                            <td><input type="submit" value="delete" name="submitButton"></td>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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
                <form action="cars" onsubmit="return validateForm(this,optionsForCarForm);" name="add" method="post">
                    <td>
                        <select title="Select client here" name="clients" required>
                            <c:forEach var="client" items="${clientsSet}">
                                <option value="<c:out value="${client.clientId}"/>"><c:out
                                        value="${client.firstName} ${client.lastName}"/></option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input title="This is brand of car" type="text" name="brand" /></td>
                    <td><input title="This is model name of car" type="text" name="model_name" /></td>
                    <td><input title="This is serial VIN number" type="text" name="VIN" /></td>
                    <td><input type="submit" value="add" name="submitButton"></td>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </tr>
        </table>
            <c:out value="${msg}"/>
    </jsp:attribute>
</t:general>