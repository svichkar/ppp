<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Home">
<jsp:attribute name="content_area">

<div>
<h2>Hello, ${hello}!</h2>
<table>
<tr>
<td style="text-align: left; background: transparent; border: none; font-size: 13px">
<h3>You are in Student Grade Management system. <b>Here you can:</b>
<ul type="none" style="text-align: left; background: transparent;">
<i>
<li>add/update/delete students, subjects, terms, journal records;</li>
<li>search for subjects by name and term;</li>
<li>search for students by last name and group;</li>
<li>observe grade details for each student by term.</li>
</i>
</ul>
</h3>
</td>
</tr>
</table>
<h4><p>Contact admin if you want to change account information</p><h4>
</div>

</jsp:attribute>
</t:general_template>