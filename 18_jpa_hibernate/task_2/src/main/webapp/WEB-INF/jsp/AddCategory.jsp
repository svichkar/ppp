
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="add category">
<jsp:attribute name="content_area">
<h2>Add category</h2>
<div class = "submit">
		<form id="addcategory" action="addcategory" method="post">
			<label for="catname">Reader First Name</label>
				<input class = "submit" type="text" name="categoryname" placeholder="Category name"
				required />
				
				<input class = "submit" type=submit value="create category" name="button">
			
		</form>
	</div>
	<c:if test="${not empty status}">
		<c:choose>
		<c:when test="${status == 'true'}">
		<br /> <font color="green">category was created</font>
		</c:when>
		<c:otherwise>
		<br /> <font color="red">category already exists</font>
		</c:otherwise>
		</c:choose>
		</c:if>
											
		</jsp:attribute>
</t:general_template>