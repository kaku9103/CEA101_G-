<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actorder.model.*"%>

<%
     ActOrderVO actOrderVO = (ActOrderVO) request.getAttribute("actOrderVO"); 
    //ActServlet.java(Concroller), 存入req的ActVO物件 (包括幫忙取出的ActEventVO物件, 也包括輸入資料錯誤時的empActTYpeVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>活動訂單修改 - act_order_update.jsp</title>

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
		 <h3>活動訂單修改 - act_order_update.jsp</h3>
		 <h4><a href="act_order_select_page.jsp"><img src="img/logo.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

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
		<td>活動訂單:</td>
		<td><input type="TEXT" name="actOdno" size="45"	onfocus="blur()" 
		value="<%=actOrderVO.getActOdno()%>" /></td>
	</tr>
	<tr>
		<td>活動狀態:</td>
		<td><input type="TEXT" name="odStatus" size="45"	placeholder="請輸入0、1、2"
		value="<%=actOrderVO.getOdStatus()%>" /></td>
	</tr>
	<tr>
		<td>活動編號:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="actNo" size="45"	placeholder="請輸入活動編號"
		value="<%=actOrderVO.getActNo()%>" /></td>
	</tr>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td> 
		<td><input type="TEXT" name="mbId" size="45"	onfocus="blur()"
		value="<%=actOrderVO.getMbId()%>" /></td>
	</tr>
	<tr>
		<td>訂單日期:<font color=red><b>*</b></font></td> 
		<td><input type="Date" name="odTime" size="45"	
		value="<%=actOrderVO.getOdTime()%>" /></td>
	</tr>
	<tr>
		<td>參加人數:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="ppl" size="45"	placeholder="請輸入參加人數"
		value="<%=actOrderVO.getPpl()%>" /></td>
	</tr>
	<tr>
		<td>訂單總價格:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="totalPrice" size="45"	placeholder="請修改金額"
		value="<%=actOrderVO.getTotalPrice()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="actOdno" value="<%=actOrderVO.getActOdno()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</html>