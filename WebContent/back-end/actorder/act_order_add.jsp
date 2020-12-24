<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actorder.model.*"%>

<%
    ActOrderVO actOrderVO = (ActOrderVO) request.getAttribute("actOrderVO"); 
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增活動訂單 - addAct.jsp</title>

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
		 <h3>新增活動訂單 - Act_order_add.jsp</h3>
		 <h4><a href="act_order_select_page.jsp"><img src="img/logo.png" width="250" height="100" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actorder/ActOrderServlet" name="form1">
<table>
	<tr>
		<td>活動訂單編號:</td>
		<td><input type="TEXT" name="actOdno" size="45" required placeholder="請輸入活動訂單編號"
			 value="<%= (actOrderVO==null)? "" : actOrderVO.getActOdno()%>" /></td>
	</tr>
	<tr>
		<td>活動編號:</td>
		<td><input type="TEXT" name="actNo" size="45" required placeholder="請輸入活動編號"
			 value="<%= (actOrderVO==null)? "" :actOrderVO.getActNo()%>" /></td>
	</tr>
	<tr>
	
		<td>會員編號:</td>
		<td>
		<input type="TEXT" name="mbId" size="45" required placeholder="請填入會員編號"
			 value="<%= (actOrderVO==null)? "" :actOrderVO.getMbId()%>" /></td>
	</tr>
	<tr>
	
		<td>訂單日期:</td>
		<td>
		<input type="date" name="odTime" size="45" required 
			 value="<%= (actOrderVO==null)? "" :actOrderVO.getOdTime()%>" /></td>
	</tr>
	<tr>
		<td>活動狀態:</td>
		<td><input type="TEXT" name="odStatus" size="45" required placeholder="請選擇活動狀態"
			 value="<%= (actOrderVO==null)? "" :actOrderVO.getOdStatus()%>" /></td>
	</tr>
	<tr>
	
		<td>參加人數:</td>
		<td>
		<input type="text" name="ppl" size="45" required 
			 value="<%= (actOrderVO==null)? "" :actOrderVO.getPpl()%>" /></td>
	</tr>
	<tr>
	
		<td>訂單總價格:</td>
		<td>
		<input type="text" name="totalPrice" size="45" required 
			 value="<%= (actOrderVO==null)? "" :actOrderVO.getTotalPrice()%>" /></td>
	</tr>
	
	

</table>
     <br>
         <input type="hidden" name="action" value="insert">
         <input type="submit" value="送出新增"></FORM>
     </body>
</html>