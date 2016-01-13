<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="includes/head.jsp" %>
<link REL="icon" href="http://www.baruch.cuny.edu/favicon.ico">
<title>Schedule Results</title>
<%@ include file="/includes/body.jsp"%>
<div id="searchResults">
	<p>Search results are based on the following keywords:</p>
	<table id="criteria"
		summary="This table contains the search criteria. Results are listed in next table.">
		<tr>
			<td><strong>Semester</strong>: <c:out value="${semester}" /></td>
			<td><strong>Days</strong>: <c:out value="${week}" /></td>
		</tr>
		<tr>
			<td><strong>Department</strong>: <c:out value="${department}" /></td>
			<td><strong>Time</strong>:Time</td>
		</tr>
		<tr>
			<td><strong>Discipline</strong>: <c:out value="${disc}" /></td>
			<td><strong>Course number</strong>: <c:out value="${courseNum}" /></td>
		</tr>
		<tr>
			<td><strong>Division</strong>: <c:out value="${grad_div}" /></td>
			<td><strong>Instructor</strong>:<c:out value="${instructor}" /></td>
		</tr>
	</table>
	<div>
		<font color="red"> <br> <b>The schedule was LAST&nbsp;
				updated at 9:00am on Nov 27th, 2006.</b> <br>Due to the dynamic
			nature of the registration process, not all courses listed as open
			will have space for new registrant. <br> <br>
		</font>
	</div>
</div>
<table id="results" class="displayt"
	summary="This table contains the search results for schedule of classes.">
	<caption>Schedule of Classes Search Results</caption>
	<thead>
		<tr>
			<th scope="col">Course</th>
			<th scope="col">Code</th>
			<th scope="col">Section</th>
			<th scope="col">Day &amp; Time</th>
			<th scope="col">Dates</th>
			<th scope="col">Bldg &amp; Rm</th>
			<th scope="col">Instructor</th>
			<th scope="col">Seats Avail</th>
			<th scope="col">Comments</th>
		</tr>
	</thead>
	<tbody>

		<!-- Discipline -->
		<!-- Re formatted the table rows -->
		<c:forEach items="${courseList}" var="course">
			<tr>
				<td><a href="CourseDetailsServlet?semester=${semester}&dis=${course.disc}&title=${course.title}
				&num=${course.crsNum}&code=${course.code}&section=${course.section}&dept=${course.dept}&division=${grad_div}
				&startDate=${course.startDate}&endDate=${course.endDate}&seats=${course.seatsAvailable}&creditHours=${course.creditHours}
				&description=${course.description}&preReq=${course.preReq}&comments=${course.comments}">
				<c:out value="${course.disc}"/> <c:out value="${course.crsNum}"/></a></td>
				<td><c:out value="${course.code}"/></td>
				<td><c:out value="${course.section}"/></td>
				<td><c:out value="${course.meetingDays}"/> <c:out value="${course.startTime}"/>-<c:out value="${course.endTime}"/> <c:out value="${course.amPM}"/></td>
				<td><c:out value="${course.startDate}"/> - <c:out value="${course.endDate}"/></td>
				<td><c:out value="${course.building}"/><c:out value="${course.room}"/></td>
				<td><c:out value="${course.instructor}"/></td>
				<td><c:out value="${course.seatsAvailable}"/></td>
				<td>
					<c:set var="comment" value="${course.comments}"/>
					<c:if test="${fn:length(comment) ge 30}">
						${fn:substring(comment, 0, 75)}...Click course for more details
					</c:if></td>
			</tr>
		</c:forEach>

	</tbody>
</table>
<%@include file="/includes/foot.jsp"%>