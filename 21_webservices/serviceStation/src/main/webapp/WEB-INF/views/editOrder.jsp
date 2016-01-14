<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="Edit Orders Page">
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
			</tr>
			<tr>
			<td class="nav-menu"><p>Already added parts</p>
				<c:forEach var="oPart" items="${parts}">
					<form id="partInOrder" method="post">
						<div style=" width:100%; height:1px; clear:both;">
							<div class="div-line" style="width:30%; word-wrap: normal;">
								<c:out value="${oPart.part.partName }"></c:out>
							</div>
							<div class="div-line" style="width:40%;">
								<input name="partQuant"	placeholder=" quantity ${oPart.amount }" />
							</div>
							<div class="div-line" style="width:10%;">
								<input hidden="hidden" name="order_id" value="${oPart.order.orderId}" />
								<input hidden="hidden" name="part_id" value="${oPart.part.partId}" />
								<input type="submit" formaction="<c:url value="/admin/editPartInOrder"></c:url>" value="update">
								<input type="submit" formaction="<c:url value="/admin/deletePartInOrder"></c:url>" value="delete" />
							</div>
						</div>
							<br/>
							<br/>
		 			</form>
				</c:forEach>

				<form id="partInOrder" action="<c:url value="/admin/addPartToOrder"></c:url>" method="post">
					<select name="part_id">
						<c:forEach var="part" items="${allParts}">
							<option value="${part.partId}">
								<c:out value="${part.partName}"></c:out>	
								CODE:<c:out	value="${part.vendor}"></c:out> 
								AMOUNT:<c:out value="${part.amount}"></c:out>
							</option>
						</c:forEach>
					</select>
					<br />
					<i>Enter quantity you needed</i>
					<br />
					<input name="partQuant" placeholder="Enter quantity you needed" />
					<input hidden="hidden" name="order_id" value="${order.orderId}" />
					<br /><br />
					<button type="submit" class="btn btn-primary btn-block btn-large">Add</button>
				</form>
			</td>
			
			<td class="nav-menu">
				<form id="order" action="<c:url value="/admin/updateOrderGeneralInfo"></c:url>" method="post">
					<table class="data">
						<tr>
							<th># Order</th>
							<td colspan="2">
								<%-- <input name="order_id" value="${order.orderId}" hidden="hidden" /> --%>
								<input name="order_id" value="${order.orderId}" disabled="disabled" />
							</td>
						</tr>
						<tr>
							<th>Order Status</th>
							<td colspan="2">
								<b>Actual: </b>
								<select name="order_status_id" form="order">
									<c:forEach var="status" items="${statuses}">
										<c:choose>
											<c:when test="${order.orderStatus.orderStatusId == status.orderStatusId}">
												<option value="${status.orderStatusId}" selected="selected">
													<c:out value="${status.orderStatusName}"></c:out>
												</option>
											</c:when>
											<c:otherwise>
												<option value="${status.orderStatusId }">
													<c:out	value="${status.orderStatusName }"></c:out>
												</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th>Order Info</th>
							<td colspan="2">
								<textarea rows="5" cols="50" name="order_description">
									<c:out value="${order.orderDescription}" />
								</textarea>
							</td>
						</tr>

						<tr>
							<th>Car in work</th>
							<td colspan="2">
								<c:out value="MODEL: ${order.car.carModel} " />
								<br/>
								<c:out value="REG NUMBER ${order.car.regNumber}" />
							</td>
						</tr>
						
						<tr>
						<th>Order start time</th>
						<td colspan="2"><c:out value="${order.datetimeStart}" /></td>
						</tr>
						<tr>
						<th colspan="2">Order finish time</th>
						<td><c:choose>
						<c:when test="${empty order.datetimeFinish}">
						<c:out value="Is waiting" />
						</c:when>
						<c:when test="${not empty order.datetimeFinish}">
						<c:out value="${order.datetimeFinish}" />
						</c:when>
						</c:choose></td>
						</tr>
						<tr>
						<td colspan="3"> <button type="submit"
						class="btn btn-primary btn-block btn-large">Update</button></td>
						</tr>
					</table>
				</form>
			</td>
			
			<td class="nav-menu"><p>Already added workers</p>
			<table class="nav-menu">
			<c:forEach var="oWorker" items="${workers}">
			<tr>
			<form id="workerInOrder" action="<c:url value="/admin/updateWorkerOrder"></c:url>"
			method="post">
			<c:choose>
			<c:when
						test="${oWorker.worker.workerStatus.workerStatusName == 'busy'}">
			<td class="nav-menu"><p>
			<c:out value="${oWorker.worker.lastName }"></c:out>
			<c:out value=" ${oWorker.worker.firstName }"></c:out>
			</p></td>
			<td class="nav-menu">
			<p>
			<c:out value="${oWorker.worker.specialization.specializationName }"></c:out>
			</p>
			</td>
			<td class="nav-menu">
			<p>
			<c:out value="${worker.worker.workerStatus.workerStatusName}"></c:out>
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
						value="${oWorker.worker.workerId}" />
			<input hidden="hidden" name="order_id"	value="${oWorker.order.orderId}" />
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
			<option value="${worker.workerId}">
			<c:out value="${worker.lastName}"></c:out> <c:out
			value="${worker.firstName}"></c:out>	SPEC:<c:out
			value="${worker.specialization.specializationName}"></c:out> STATUS:<c:out
			value="${worker.workerStatus.workerStatusName}"></c:out></option>
			</c:forEach>
			</select>
			<br /><br />
			<input hidden="hidden" name="order_id" value="${order.orderId}" />
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
	</jsp:attribute>
</t:mainTemplate>