<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actevent.model.*"%>

<%
    ActEventVO actEventVO = (ActEventVO) request.getAttribute("actEventVO"); 
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增活動種類 - addAct.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>新增活動項目 - addAct.jsp</h3>
		 <h4><a href="act_event_select_page.jsp"><img src="img/logo.png" width="250" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actevent/ActEventServlet" name="form1">
<table>
	<tr>
		<td>活動項目編號:</td>
		<td><input type="TEXT" name="actEventNo" size="45" required placeholder="請輸入活動項目編號"
			 value="<%= (actEventVO==null)? "" : actEventVO.getActEventNo()%>" /></td>
	</tr>
	<tr>
		<td>活動種類編號:</td>
		<td><input type="TEXT" name="actTypeNo" size="45" required placeholder="請選擇活動種類編號"
			 value="<%= (actEventVO==null)? "" :actEventVO.getActEventName()%>" /></td>
	</tr>
	<tr>
		<td>活動項目名稱:</td>
		<td><input type="TEXT" name="actEventName" size="45" required placeholder="請輸入活動項目名稱"
			 value="<%= (actEventVO==null)? "" :actEventVO.getActEventName()%>" /></td>
	</tr>
	<tr>
		<td>內容敘述:</td>
		<td><input type="TEXT" name="actInfo" size="45" required placeholder="請簡述活動內容"
			 value="<%= (actEventVO==null)? "" :actEventVO.getActInfo()%>" /></td>
	</tr>

	

</table>
     <br>
         <input type="hidden" name="action" value="insert">
         <input type="submit" value="送出新增"></FORM>
     </body>
</html>