<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general title="Clients">
    <jsp:attribute name="internal_content_area">
        <c:if test="${not empty ucSet}">
            <table border="1">
                <caption>Clients</caption>
                    <tr>
                        <th>first_name</th>
                        <th>last_name</th>
                        <th>user_login</th>
                        <th>user_password</th>
                        <th>user_role</th>
                    </tr>
                    <c:forEach var="uc" items="${ucSet}" varStatus="status">
                        <tr>
                            <form action="clients" name="upDel" method="post" onsubmit="return validateForm(this,optionsForClientForm);">
                                <input type="hidden" name="client_id" value ="<c:out value="${uc.clientId}"/>">
                                <input type="hidden" name="user_id" value ="<c:out value="${uc.user.userId}"/>">
                                <td><input title="This is client first name" type="text" name="first_name" value ="<c:out value="${uc.firstName}"/>"></td>
                                <td><input title="This is client last name" type="text" name="last_name" value ="<c:out value="${uc.lastName}"/>"></td>
                                <td><input title="This is client-user login" type="text" name="user_login" value ="<c:out value="${uc.user.login}"/>" readonly></td>
                                <td><input title="This is client-user password" type="text" name="user_password" value ="<c:out value="${uc.user.password}"/>"></td>
                                <td><select title="This is client-user role" name="roles" required>
                                        <option selected value="<c:out value="${uc.user.role.roleId}"/>"><c:out value="${uc.user.role.roleName}"/></option>
                                            <c:forEach var="role" items="${roleSet}">
                                                <c:if test="${role.roleName != uc.user.role.roleName}">
                                                    <option value="<c:out value="${role.roleId}"/>"><c:out value="${role.roleName}"/></option>
                                                </c:if>
                                            </c:forEach>
                                    </select></td>
                                <td><input type="submit" value="edit" name="submitButton"></td>
                                <td><input type="submit" value="delete" name="submitButton"></td>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                        </tr>
                    </c:forEach>
            </table>
        </c:if>
            <table border="1">
                        <caption>Add client</caption>
                            <tr>
                                <th>first_name</th>
                                <th>last_name</th>
                                <th>user_login</th>
                                <th>user_password</th>
                                <th>user_role</th>
                            </tr>
                    <tr>
                        <form action="clients" name="add" method="post" onsubmit="return validateForm(this,optionsForClientForm);">
                            <td><input title="This is client first name" type="text" name="first_name" /></td>
                            <td><input title="This is client last name" type="text" name="last_name" /></td>
                            <td><input title="This is client-user login" type="text" name="user_login" /></td>
                            <td><input title="This is client-user password" type="text" name="user_password" /></td>
                            <td><select title="This is client-user role" name="roles" required>
                                    <c:forEach var="role" items="${roleSet}">
                                        <option value="<c:out value="${role.roleId}"/>"><c:out value="${role.roleName}"/></option>
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