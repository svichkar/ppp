<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general title="Workers">
    <jsp:attribute name="internal_content_area">
        <c:if test="${not empty employeeSet}">
            <table border="1">
            <caption>Workers</caption>
            <tr>
                <th>worker_id</th>
                <th>first_name</th>
                <th>last_name</th>
                <th>category</th>
            </tr>
            <c:forEach var="employee" items="${employeeSet}" varStatus="status">
                <tr>
                    <form action="workers" name="upDel" method="post">
                        <td><input type="text" name="worker_id" value="<c:out value="${employee.employeeId}"/>"
                                   readonly></td>
                        <td><input type="text" name="first_name" value="<c:out value="${employee.firstName}"/>"></td>
                        <td><input type="text" name="last_name" value="<c:out value="${employee.lastName}"/>"></td>
                        <td><select name="category" required>
                            <option selected value="<c:out value="${employee.employeeCategory.employeeCategoryId}"/>">
                                <c:out value="${employee.employeeCategory.employeeCategoryName}"/></option>
                            <c:forEach var="employeeCategory" items="${employeeCategorySet}">
                                <c:if test="${employeeCategory.employeeCategoryName != employee.employeeCategory.employeeCategoryName}">
                                    <option value="<c:out value="${employeeCategory.employeeCategoryId}"/>"><c:out
                                            value="${employeeCategory.employeeCategoryName}"/></option>
                                </c:if>
                            </c:forEach>
                        </select></td>
                        <td><input type="submit" value="edit" name="submitButton"></td>
                        <td><input type="submit" value="delete" name="submitButton"></td>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </tr>
            </c:forEach>
        </c:if>
                    </table>
                    <table border="1">
                        <caption>Add worker</caption>
                        <tr>
                            <th>first_name</th>
                            <th>last_name</th>
                            <th>category</th>
                        </tr>
                        <tr>
                            <form action="workers" name="add" method="post">
                                <td><input type="text" name="first_name" required/></td>
                                <td><input type="text" name="last_name" required/></td>
                                <td><select name="category" required>
                                    <c:forEach var="employeeCategory" items="${employeeCategorySet}">
                                        <option value="<c:out value="${employeeCategory.employeeCategoryId}"/>"><c:out
                                                value="${employeeCategory.employeeCategoryName}"/></option>
                                    </c:forEach>
                                </select></td>
                                <td><input type="submit" value="add" name="submitButton"></td>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                        </tr>
                    </table>
            <c:out value="${msg}"/>
    </jsp:attribute>
</t:general>