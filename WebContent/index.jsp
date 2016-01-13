<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ page errorPage="includes/errorpg.jsp" %> 

<%@include file="/includes/head.jsp" %>
<title>Schedule Search</title>
<%@include file="/includes/body.jsp"%>


<form action="SearchServlet" method="post">
	<input type="hidden" name="action" value="doSearch" />
	<div align="center">
		<p>Enter the semester, discipline, course number, professor's name
			and/or days/time you wish to search.</p>
		<table id="search"
			summary="This table contains search options for the schedule of classes.">
			<caption>
				Schedule of Classes Search&nbsp;
			</caption>
			<tbody>
				<tr>
					<th><label for="semester">Semester:</label></th>
					<td><select id="semester" name="semester">
							<option>Semester</option>
							<c:forEach items="${sessions}" var="session">
								<option>${session.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th><label for="discipline">Discipline:</label></th>
					<td><select id="discipline" name="discipline">
							<option>Select All</option>
							<c:forEach items="${disciplines}" var="discipline">
								<option>${discipline.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
				<th>Division</th>
					<td><label for="undergraduate">Undergraduate </label> 
					<input  type="checkbox" id="undergraduate" value="U" name="gradStatus"
						checked> <br> <label for="graduate">Graduate</label>
						<input type="checkbox" id="gradaute" value="G" name="gradStatus"
						checked></td>
				</tr>
				<tr>
					<th><label for="number">Course number:</label></th>
					<td><input id="number" size="10" name="number" maxlength="5"
						type="text"></td>
				</tr>
				<tr>
					<th><label for="days">Days:</label></th>
					<td><select id="days" name="week">
							<option>Select All</option>
							<c:forEach items="${meetingDays}" var="days">
								<option>${days.meetingDays}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th><label for="time">Time:</label></th>
					<td><select id="time" name="time_a_b">
							<option value="Select All">Select All</option>
							<option value="before">before</option>
							<option value="after">after</option>
							<option value="around">around</option>
						</select> 
					<select name="time">
							<option value="0">Select All</option>
							<option value="7">7:00am</option>
							<option value="8">8:00am</option>
							<option value="9">9:00am</option>
							<option value="10">10:00am</option>
							<option value="11">11:00am</option>
							<option value="12">12:00pm</option>
							<option value="13">1:00pm</option>
							<option value="14">2:00pm</option>
							<option value="15">3:00pm</option>
							<option value="16">4:00pm</option>
							<option value="17">5:00pm</option>
							<option value="18">6:00pm</option>
							<option value="19">7:00pm</option>
							<option value="20">8:00pm</option>
							<option value="21">9:00pm</option>
					</select></td>
				</tr>
				<tr>
					<th><label for="instructor">Instructor:</label></th>
					<td><input id="instructor" size="30" name="prof" type="text"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<p align="center">
		<input value="Start Search" type="submit">
	</p>

</form>

<%@include file="/includes/foot.jsp"%>