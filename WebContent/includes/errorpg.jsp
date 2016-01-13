<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.*, java.io.*, java.lang.*" isErrorPage="true" contentType="text/html; charset=utf-8"%>
<%@ page isErrorPage="true" %>
<%@include file="/includes/head.jsp"%>
<title> Search Error</title>
<%@include file="/includes/body.jsp"%>
<h1>Search Error</h1>

<%{
if(exception instanceof java.lang.ClassNotFoundException){
%>
<p><strong>The application is unable to locate the file.</strong></p>
<%
}else if(exception instanceof java.sql.SQLException){
%>
<p>The application is unable to connect to the database.</p>

<%
}else if (exception instanceof java.lang.NullPointerException){
%>
<p> <strong>The application is unable to locate the file.</strong></p>
<%
}else{
%>
 
 <h1><font color="red"> Opps.. An search error occurred. </font></h1>
	<p> Please Make Sure you select a semester to search </p>
<%
}
}
%>

<a href="/01_Class_Search"> Try Again</a>
<%@include file="/includes/foot.jsp"%>
		