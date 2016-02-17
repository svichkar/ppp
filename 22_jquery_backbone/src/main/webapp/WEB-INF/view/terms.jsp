<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Terms">
	<jsp:attribute name="menu_area">
		<ul>
			<li><a href="<c:url value="/students"/>">Students</a></li>
			<li><a href="<c:url value="/subjects"/>">Subjects</a></li>
			<li><a class="active" href="<c:url value="/terms"/>">Terms</a></li>
			<li><a href="<c:url value="/journals"/>">Journal</a></li>
			<ul style="float: right; list-style-type: none;">
				<li><a href="<c:url value="/logout"/>">Logout</a></li>
			</ul>
		</ul>
	</jsp:attribute>
	<jsp:attribute name="content_area">
	<div class="forms">
       <h2>Terms</h2>
       <h3>Add Term</h3>
       <form action="terms" method="post" onsubmit="return validate(this, optionsForAddTerm)">
       <table class="form-table">
       <tr>
					<td>Term Name : </td>
					<td><input type="text" name="newTermName" required>
					<span class="hint">Enter term name in text format</span>
					</td>
				</tr>
       </table>
       <p/><input type="submit" name="add" value="Add">
       </form>
       <a href="<c:url value="/backboneTerms"/>">Backbone Terms</a> 
       </div>
       <c:if test="${not empty message}">
       <div class="message-info" align="center">
				<c:out value="${message}" />
			</div>
       </c:if>             
       <h3>List of terms</h3>       
        <table class="centered-table" border="1">
            <thead>
                <tr>
					<th>Term ID</th>
					<th>Term Name</th>
					<th>Action</th>				
				</tr>
            </thead>
            <tbody>
            <c:forEach var="term" items="${terms}">
                <tr>
                    <td><c:out value="${term.termId}" /></td>
                    <td><c:out value="${term.termName}" /></td>                    
                    <td><div  class="action"><form action="updateTerm" method="get">
                    <input type="hidden" name="term_id"
									value="<c:out value="${term.termId}"/>">
                    <input type="submit" name="edit" value="Edit">
							</form></div>
					<div  class="action"><form action="terms" method="post">
                    <input type="hidden" name="term_id"
									value="<c:out value="${term.termId}"/>">
                    <input type="submit" name="delete" value="Delete">
                    </form></div></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>                            
    </jsp:attribute>
</t:template>