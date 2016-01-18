<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general title="Workers">
    <jsp:attribute name="internal_content_area">
        <c:if test="${not empty employeeList}">
            <table border="1">
                <caption>Workers</caption>
                    <tr>
                        <th>worker_id</th>
                        <th>first_name</th>
                        <th>last_name</th>
                        <th>category</th>
                    </tr>
                    <c:forEach var="employee" items="${employeeList}" varStatus="status">
                        <tr>
                            <form action="workers" name="upDel" method="post">
                                <input type="hidden" name="login" value="<c:out value="${param.login}"/>">
                                <td><input type="text" name="worker_id" value ="<c:out value="${employee.employeeId}"/>" readonly></td>
                                <td><input type="text" name="first_name" value ="<c:out value="${employee.firstName}"/>"></td>
                                <td><input type="text" name="last_name" value ="<c:out value="${employee.lastName}"/>"></td>
                                <td><select name="category" required>
                                        <option selected value="<c:out value="${employee.employeeCategoryId}"/>"><c:out value="${employeeCategoryNameList[status.index]}"/></option>
                                            <c:forEach var="employeeCategory" items="${employeeCategoryList}">
                                                <c:if test="${employeeCategory.name != employeeCategoryNameList[status.index]}">
                                                    <option value="<c:out value="${employeeCategory.employeeCategoryId}"/>"><c:out value="${employeeCategory.name}"/></option>
                                                </c:if>
                                            </c:forEach>
                                    </select></td>
                                <td><input type="submit" value="edit" name="edit"></td>
                                <td><input type="submit" value="delete" name="delete"></td>
                            </form>
                        </tr>
                    </c:forEach>
                    <tr>
                        <form action="workers" name="add" method="post">
                            <input type="hidden" name="login" value="<c:out value="${param.login}"/>">
                            <td></td>
                            <td><input type="text" name="first_name"/></td>
                            <td><input type="text" name="last_name"/></td>
                            <td><select name="category" required>
                                    <c:forEach var="employeeCategory" items="${employeeCategoryList}">
                                        <option value="<c:out value="${employeeCategory.employeeCategoryId}"/>"><c:out value="${employeeCategory.name}"/></option>
                                    </c:forEach>
                                </select></td>
                            <td><input type="submit" value="add" name="add"></td>
                        </form>
                    </tr>
            </table>
       </c:if>
    </jsp:attribute>
</t:general>