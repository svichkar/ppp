<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
		<table class	="first">
				<tr>
					<th>Part name</th>
					<th>Vendor</th>
					<th>Amount</th>
					<th>Action</th>
					<th>
						<form action="orderPartAdd" method="POST">
							<input type="hidden" name="action" value="Add">
							<input type="hidden" name="order_id" value="${oiw.id}">
							<input type="submit" value="Add part">
						</form>
					</th>
				</tr>
					<c:forEach var="part" items="${po}">
						<form action="orderPartEdit" method="POST">
							<tr>
								<td>
									<c:out value="${part.part_name}" />
								</td>
								<td>
									<c:out value="${part.vendor}" />
								</td>
								<td>
									<c:out value="${part.amount}" />
								</td>
								<td>
									<select name="action">
										<option name="edit">Edit</option>
										<option name="delete">Delete</option>
									</select>
								</td>
								<td>
									<input type="hidden" name="order_id" value="${part.id}"> 
									<input type="hidden" name="part_id" value="${part.part_id}">
									<input type="submit" value="Process">
								</td>
							</tr>
						</form>
					</c:forEach>
		</table>
		<table class="second">
		<form action="orderEdit" method="POST">
		<tr>
			<td>Order id</td>
			<td>
				<c:out value="${oiw.id}" />
			</td>
		</tr>
		<tr>
			<td>Model:</td>
			<td>
				<select name="car_id">
					<c:forEach var="car" items="${cars}">
						<option value="${car.id}"
									<c:if test="${car.id eq oiw.car_id}">selected</c:if>>
										<c:out value="${car.model}" />
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>Status:</td>
			<td>
				<select name="order_status_id">
				<c:forEach var="status" items="${os}">
						<option value="${status.id}"
									<c:if test="${status.id eq oiw.order_status_id}">selected</c:if>>
										<c:out value="${status.order_status_name}" />
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>Description:</td>
			<td>
				<input type="text" name="description" value="${oiw.description}" />
			</td>
		</tr>
		<tr>
			<td>Start time:</td>
			<td>
				<input type="datetime" name="datetime_start" value="${oiw.datetime_start}" />
			</td>
		</tr>
		<tr>
			<td>Stop time:</td>
			<td>
				<input type="datetime" name="datetime_end" 	value="${oiw.datetime_end}" />
			</td>
		</tr>
		<tr>
			<td colspan=2 align="right">
				<input type="hidden" name="action" value="Save">
				<input type="hidden" name="order_id" value="${oiw.id}">
				<input type="submit" value="Save">
			</td>
		</tr>
		</form>
		</table>
		<table class="third">
			<tr>
					<th>Worker name</th>
					<th>Completed</th>
					<th>Action</th>
					<th>
						<form action="orderWorkerAdd" method="POST">
							<input type="hidden" name="action" value="Add"> 
							<input type="hidden" name="order_id" value="${oiw.id}">
							<input type="submit" value="Add worker">
						</form>
					</th>
				</tr>
					<c:forEach var="worker" items="${ow}">
						<form action="orderWorkerEdit" method="POST">
							<tr>
								<td>
									<c:out value="${worker.f_name} ${worker.l_name}" />
								</td>
								<td>
									<c:if test="${worker.completed == 'false'}"><c:out value="N" /></c:if>
									<c:if test="${worker.completed == 'true'}"><c:out value="Y" /></c:if>
								</td>
								<td>
									<select name="action">
										<option name="edit">Edit</option>
										<option name="delete">Delete</option>
									</select>
								</td>
								<td>
									<input type="hidden" name="worker_id" value="${worker.worker_id}"> 
									<input type="hidden" name="order_id" value="${worker.id}">
									<input type="submit" value="Process">
								</td>
							</tr>
						</form>
					</c:forEach>
		</table>
	</jsp:attribute>
</t:general_template>