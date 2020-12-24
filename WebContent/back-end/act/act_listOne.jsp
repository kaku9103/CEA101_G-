<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
   ActVO actVO = (ActVO) request.getAttribute("actVO"); //ActServlet.java(Concroller), �s�Jreq��ActVO����
%>

<html>
<head>
<title>���ʶ��ظ�� - act_event_listOne.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���ʺ������ - ListOneAct.jsp</h3>
		 <h4><a href="act_select_page.jsp"><img src="img/logo.png" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���ʽs��</th>
		<th>���ʶ��ؽs��</th>
		<th>���ʦW��</th>
		<th>���ʪ��A</th>
		<th>���ʳ��W���</th>
		<th>�����|����</th>
		<th>���ʺI����</th>
		<th>���ʮɬq</th>
		<th>�ѥ[�H��</th>
		<th>���ʻ���</th>
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