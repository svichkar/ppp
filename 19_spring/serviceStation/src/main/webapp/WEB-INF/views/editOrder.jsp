<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="Orders Page">
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
						<table class="nav-menu">
							<c:forEach var="oPart" items="${parts}">
								<tr>
					<form id="partInOrder"
									action="<c:url value="/admin/editPartInOrder"></c:url>"
									method="post">
									
								<td class="nav-menu"><p>
											<c:out value="${oPart.part.part_name }"></c:out>
										</p></td>
									<td class="nav-menu"><input name="partQuant"
										placeholder=" quantity ${oPart.amount }" /></td>
									<td class="nav-menu">
									<input hidden="hidden" name="order_id"
										value="${oPart.order.order_id}" />
									<input hidden="hidden" name="part_id"
										value="${oPart.part.part_id}" />
									<input type="submit" value="update">
							<input type="submit"
										formaction="<c:url value="/admin/deletePartInOrder"></c:url>"
										value="delete" />
											</td>
					</form>
								</tr>
							</c:forEach>
						</table>
					<form id="partInOrder"
						action="<c:url value="/admin/addPartToOrder"></c:url>"
						method="post">
						<select name="part_id"><c:forEach var="part"
								items="${allParts}">
								<option value="${part.part_id}"><c:out
										value="${part.part_name}"></c:out>	CODE:<c:out
										value="${part.vendor}"></c:out> AMOUNT:<c:out
										value="${part.amount}"></c:out></option>
							</c:forEach>
							</select><br /><i>Enter quantity you needed</i><br /><input
							name="partQuant" placeholder="Enter quantity you needed" />
							<input hidden="hidden" name="order_id" value="${order.order_id}" /><br /><br />
							<button type="submit" class="btn btn-primary btn-block btn-large">Add</button>
					</form></td>
				<td class="nav-menu"><form id="order"
						action="<c:url value="/admin/updateOrderGeneralInfo"></c:url>"
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
								<td colspan="2"><b>Actual: </b>
								<select name="order_status_id" form="order">
					<c:forEach var="status" items="${statuses}">
					<c:choose>
					<c:when
													test="${order.order_status.order_status_id == status.order_status_id}">
					<option value="${status.order_status_id}" selected="selected"><c:out
															value="${status.order_status_name}"></c:out>
												
												</c:when>
					<c:otherwise>
						<option value="${status.order_status_id }"><c:out
															value="${status.order_status_name }"></c:out>
												
												</c:otherwise>
					</c:choose>
					</c:forEach>
					</select>
					</td>
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
										class="btn btn-primary btn-block btn-large">Update</button></td>
							</tr>
						</table>
					</form></td>
				<td class="nav-menu"><p>Already added workers</p>
						<table class="nav-menu">
							<c:forEach var="oWorker" items="${workers}">
								<tr>
					<form id="workerInOrder" action="<c:url value="/admin/updateWorkerOrder"></c:url>"
									method="post">
								<c:choose>
								<c:when
											test="${oWorker.worker.worker_status.worker_status_name == 'busy'}">
									<td class="nav-menu"><p>
											<c:out value="${oWorker.worker.last_name }"></c:out>
													<c:out value=" ${oWorker.worker.first_name }"></c:out>
										</p></td>
									<td class="nav-menu">
									<p>
											<c:out
														value="${oWorker.worker.specialization.specialization_name }"></c:out>
										</p>
											</td>
									<td class="nav-menu">
									<p>
											<c:out
														value="${worker.worker.worker_status.worker_status_name}"></c:out>
										</p>
											</td>
										
										<td class="nav-menu"><p>Is work finished</p>
										<select name="isFinished">
										<option value="yes">Yes</option>
										<option value="no">No</option>
										</select>
										</td>
									<td class="nav-menu">
									<input hidden="hidden" name="worker_id"
												value="${oWorker.worker.worker_id}" />
									<input hidden="hidden" name="order_id"
												value="${oWorker.order.order_id}" />
									<input type="submit" value="update">
									</td>
									</c:when>
								</c:choose>
					</form>
								</tr>
							</c:forEach>
						</table>
					<form id="orderWorker" action="<c:url value="/admin/addWorkerOrder"></c:url>" method="post">
						<select name="worker_id">
							<c:forEach var="worker" items="${allWorkers}">
								<option value="${worker.worker_id}">
								<c:out value="${worker.last_name}"></c:out> <c:out
										value="${worker.first_name}"></c:out>	SPEC:<c:out
										value="${worker.specialization.specialization_name}"></c:out> STATUS:<c:out
										value="${worker.worker_status.worker_status_name}"></c:out></option>
							</c:forEach>
							</select>
							<br /><br />
							<input hidden="hidden" name="order_id" value="${order.order_id}" />
							<button type="submit" class="btn btn-primary btn-block btn-large">Add</button>
					</form></td>
			</tr>
		</table>
		<br />
		<br />
		<form action="<c:url value="/admin/adminPage"></c:url>" method="get">
			<div style="width: 50%; margin: 0 auto; text-align: center;">
			<button type="submit" class="btn btn-primary btn-block btn-large">Cancel</button>
			</div>
		</form>
	</jsp:attribute></t:mainTemplate>