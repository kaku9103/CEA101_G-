<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
   ActVO actVO = (ActVO) request.getAttribute("actVO"); //ActServlet.java(Concroller), 存入req的ActVO物件
%>

<html>
<head>
<title>活動項目資料 - act_event_listOne.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
  width:100px;
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>活動種類資料 - ListOneAct.jsp</h3>
		 <h4><a href="act_select_page.jsp"><img src="img/logo.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>活動編號</th>
		<th>活動項目編號</th>
		<th>活動名稱</th>
		<th>活動狀態</th>
		<th>活動報名日期</th>
		<th>活動舉辦日期</th>
		<th>活動截止日期</th>
		<th>活動時段</th>
		<th>參加人數</th>
		<th>活動價格</th>
	</tr>
	
	
	<tr>
	     <td>${actVO.actNo}</td>
	     <td>${actVO.actEventNo}</td>
	     <td>${actVO.actName}</td>
	     <td>${actVO.actStatus}</td>
	     <td>${actVO.actRegTime}</td>
	     <td>${actVO.actDate}</td>
	     <td>${actVO.deadLine}</td>
	     <td>${actVO.actTime}</td>
	     <td>${actVO.participant}</td>
	     <td>${actVO.actPrice}</td>
	</tr>
	
</table>

</body>
</html>