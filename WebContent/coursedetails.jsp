<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ include file="/includes/head.jsp" %>
<title>Course Details</title>
<%@ include file="/includes/body.jsp" %>

	<table id="details" summary="This table contains details about each course.">
		<caption>Schedule of Classes Course Details</caption>
		<tr>
			<th scope="row">Semester:</th>
			<td><c:out value="${semester}"/></td>
		</tr>
		<tr>
			<th scope="row">Course - Title:</th>
			<td><c:out value="${disc}"/></td>
		</tr>
		<tr>
			<th scope="row">Code:</th>
			<td><c:out value="${code}"/></td>
		</tr>
		<tr>
			<th scope="row">Section:</th>
			<td><c:out value="${section}"/></td>
		</tr>
		<tr>
			<th scope="row">Department:</th>
			<td><c:out value="${dept}"/></td>
		</tr>
		<tr>
			<th scope="row">Division:</th>
			<td><c:out value="${division}"/></td>
		</tr>
		<tr>
			<th scope="row">Dates:</th>
			<td><c:out value="${dates}"/></td>
		</tr>
		<tr>
			<th scope="row">Seats Available:</th>
			<td><c:out value="${seats}"/></td>	
		</tr>
		<c:set var="count" value="${0}" scope="page"/>
		<c:forEach items="${courseMeetings}" var="course">
		<tr>
			<c:set var="count" value="${count + 1}" scope="page"/>
			<th scope="row">Meeting <c:out value="${count}"/> - Day &amp; Time, Building &amp; Room,
				Instructor:</th>
				<td>
				<c:out value="${course.meetingDays}" ></c:out>,
				<c:out value="${course.startTime}" ></c:out>-
				<c:out value="${course.endTime}" ></c:out>,
				<c:out value="${course.building}" ></c:out>,
				<c:out value="${course.room}" ></c:out>,
				<c:out value="${course.instructor}" ></c:out>
				</td>
			<!-- <td>TTH, 8:40am-10:20am, 23 511, Crane</td> -->
		</tr>
		</c:forEach>
		<!-- <tr>
			<th scope="row">Meeting 2 - Day &amp; Time, Building &amp; Room,
				Instructor:</th>
			<td>F, 10:45am-11:35am, 23 5SO, Tucker</td> 
		</tr> -->
		<tr>
			<th scope="row">Credit Hours:</th>
			<td><c:out value="${creditHours}" ></c:out></td>
		</tr>
		<tr>
			<th scope="row">Description:</th>
			 <td><c:out value="${description}"></c:out></td> 
		</tr>
		<tr>
			<th scope="row">Course Comments:</th>
			<td><c:out value="${comments}"></c:out></td>
		</tr>
		<tr>
			<th scope="row">Pre-requisite:</th>
			<td><c:out value="${preReq}"></c:out></td>
		</tr>
	</table>


<%@include file="/includes/foot.jsp"%>

