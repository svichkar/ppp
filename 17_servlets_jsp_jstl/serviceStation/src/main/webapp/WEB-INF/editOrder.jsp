<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="Cars Page">
	<jsp:attribute name="content_area">
		<div align="center">
			<h1>
				<c:out value="Edit existing order" />
			</h1>
		</div>
<table>
			<tr>
				<th class="nav-menu"><h3>Add part to order</h3></th>
				<th class="nav-menu"><h3>Edit general info</h3></th>
				<th class="nav-menu"><h3>Assign worker(s) to order</h3></th>
			<tr>
				<td class="nav-menu"><p>Already added parts</p>
					<form id="partInOrder" action="updatePartOrder" method="post">
						<input hidden="hidden" name="action" value="edit" />
						<table class="nav-menu">
							<c:forEach var="part" items="${parts}">
								<tr>
									<td class="nav-menu"><p>
											<c:out value="${part.part_name }"></c:out>
										</p></td>
									<td class="nav-menu"><input name="partQuant"
										placeholder=" quantity ${part.quntityInOrder }" /></td>
									<td class="nav-menu">
									<input hidden="hidden" name="order_id" value="${order.order_id}" />
									<input hidden="hidden" name="part_id" value="${part.part_id}" />
									<input type="submit" value="update">
									<input hidden="hidden" name="fieldName" value="orderpart" />
									<input type="submit" name="action" formaction="deleteField" value="delete" /></td>
								</tr>
							</c:forEach>
						</table>
					</form>
					<form id="partInOrder" action="updatePartOrder" method="post">
						<input hidden="hidden" name="action" value="add" /><select
							name="part_id"><c:forEach var="part" items="${allParts}">
								<option value="${part.part_id}"><c:out
										value="${part.part_name}"></c:out>	CODE:<c:out
										value="${part.vendor}"></c:out> AMOUNT:<c:out
										value="${part.amount}"></c:out></option>
							</c:forEach>
							</select><input name="partQuant" placeholder="Enter quantity you needed" />
							<input hidden="hidden" name="order_id" value="${order.order_id}" /><br /><br />
							<button type="submit" class="btn btn-primary btn-block btn-large" name="target" value="updateField">Add</button>
					</form></td>
				<td class="nav-menu"><form id="order" action="updateField"
						method="post">
						<table class="data">
							<tr>
								<th># Order</th>
								<td colspan="2"><input name="order_id"
									value="${order.order_id}" hidden="hidden" /><input
									name="order_id" value="${order.order_id}" disabled="disabled" /></td>
							</tr>
							<tr>
								<th>Order Status</th>
								<td><b>Actual: <c:out value="${order.order_status_id}"></c:out></b><br /><select
									name="order_status_id"><c:forEach var="status"
											items="${statuses}">
											<option value="${status.order_status_id}"><c:out
													value="${status.order_status_id}"></c:out></option>
										</c:forEach></select></td>
								<td><ol>
										<c:forEach var="status" items="${statuses}">
											<li value="${status.order_status_id}">- <c:out
													value="${status.order_status_name}"></c:out></li>
										</c:forEach>
									</ol></td>
							</tr>
							<tr>
								<th>Order Info</th>
								<td colspan="2"><textarea rows="5" cols="50"
										name="order_description"><c:out
											value="${order.order_description}" /></textarea></td>
							</tr>
							<tr>
								<th>Order start time</th>
								<td colspan="2"><c:out value="${order.datetime_start}" /></td>
							</tr>
							<tr>
								<th colspan="2">Order finish time</th>
								<td><c:choose>
										<c:when test="${empty order.datetime_finish}">
											<c:out value="Is waiting" />
										</c:when>
										<c:when test="${not empty order.datetime_finish}">
											<c:out value="${order.datetime_finish}" />
										</c:when>
									</c:choose></td>
							</tr>
							<tr>
								<td colspan="3"> <button type="submit"
										class="btn btn-primary btn-block btn-large" name="target"
										value="updateOrder">Update</button></td>
							</tr>
						</table>
					</form></td>
				<td class="nav-menu"><p>Already added workers</p>
					<form id="workerInOrder" action="updateWorkerOrder" method="post">
						<input hidden="hidden" name="action" value="edit" />
						<table class="nav-menu">
							<c:forEach var="worker" items="${workers}">
								<tr>
								<c:choose>
								<c:when test="${worker.status == 'busy'}">
									<td class="nav-menu"><p>
											<c:out value="${worker.fullName }"></c:out>
										</p></td>
									<td class="nav-menu">
									<p>
											<c:out value="${worker.specialization }"></c:out>
										</p></td>
									<td class="nav-menu">
									<p>
											<c:out value="${worker.status }"></c:out>
										</p></td>
										
										<td class="nav-menu"><p>Is work finished</p>
										<select name="isFinished">
										<option value="yes">Yes</option>
										<option value="no">No</option>
										</select>
										</td>
									<td class="nav-menu">
									<input hidden="hidden" name="worker_id" value="${worker.worker_id}" />
									<input hidden="hidden" name="order_id" value="${order.order_id}" />
									<input type="submit" value="update">
									<input hidden="hidden" name="fieldName" value="orderWorker" />
									</td>
									</c:when>
								</c:choose>
								</tr>
							</c:forEach>
						</table>
					</form>
					<form id="orderWorker" action="updateWorkerOrder" method="post">
						<input hidden="hidden" name="action" value="add" />
						<select	name="worker_id" >
							<c:forEach var="worker" items="${allWorkers}">
								<option value="${worker.worker_id}">
								<c:out value="${worker.fullName}"></c:out>	SPEC:<c:out
										value="${worker.specialization}"></c:out> STATUS:<c:out
										value="${worker.status}"></c:out></option>
							</c:forEach>
							</select>
							<br /><br />
							<input hidden="hidden" name="order_id" value="${order.order_id}" />
							<button type="submit" class="btn btn-primary btn-block btn-large" name="target" value="updateField">Add</button>
					</form></td>
			</tr>
		</table>
		<br />
		<br />
		<form action="adminPage" method="get"><div style="width: 50%; margin: 0 auto; text-align: center;">
			<button type="submit" class="btn btn-primary btn-block btn-large"
				name="target" value="car" >Cancel</button></div>
		</form>
	</jsp:attribute></t:mainTemplate>