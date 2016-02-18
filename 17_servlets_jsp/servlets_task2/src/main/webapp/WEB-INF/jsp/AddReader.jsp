
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="add reader">
	<jsp:attribute name="content_area">
<h2>Add Reader</h2>
		<form id="addcategory" action="addreader" method="post">			
				<input type="text" name="readerfirstname" placeholder="Reader first name" required/>
				<input type="text" name="readerlastname" placeholder="Reader last name" required/>
				<input type="text" name="email" placeholder="email" required/>
				<input type=submit value="add reader" name="button">			
		</form>		
		</jsp:attribute>
</t:general_template>