<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Edit Term">
	<jsp:attribute name="content_area">
	<h1>Edit Term</h1>
		<h2>Term:</h2>
		<form action="editTerm.do" method="post">
		<b>Alias:</b><br>
		<input type="text" name="alias" value="${term.alias}"><br>	
		<input type="hidden" name="aliasOld" value="${term.alias}"><br>	
		<p>
				<input type="submit" value="Save">
			</p>
		</form>	
</jsp:attribute>
</t:generalManager>