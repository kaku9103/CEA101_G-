<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>

<%
    ActVO actVO = (ActVO) request.getAttribute("actVO"); 
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
		 <h3>新增活動 - addAct.jsp</h3>
		 <h4><a href="act_select_page.jsp"><img src="img/logo.png" width="250" height="100" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/ActServlet" name="form1">
<table>
	<tr>
		<td>活動編號:</td>
		<td><input type="TEXT" name="actNo" size="45" required placeholder="請輸入活動編號"
			 value="<%= (actVO==null)? "" : actVO.getActNo()%>" /></td>
	</tr>
	<tr>
		<td>活動項目編號:</td>
		<td><input type="TEXT" name="actEventNo" size="45" required placeholder="請選擇活動項目編號"
			 value="<%= (actVO==null)? "" :actVO.getActEventNo()%>" /></td>
	</tr>
	<tr>
		<td>活動名稱:</td>
		<td><input type="TEXT" name="actName" size="45" required placeholder="請輸入活動名稱"
			 value="<%= (actVO==null)? "" :actVO.getActName()%>" /></td>
	</tr>
	<tr>
	
		<td>活動狀態:</td>
		<td>
		<input type="TEXT" name="actStatus" size="45" required placeholder="請填入活動狀態為0"
			 value="<%= (actVO==null)? "" :actVO.getActStatus()%>" /></td>
	</tr>
	<tr>
	
		<td>活動報名日期:</td>
		<td>
		<input type="date" name="actRegTime" size="45" required 
			 value="<%= (actVO==null)? "" :actVO.getActRegTime()%>" /></td>
	</tr>
	<tr>
	
		<td>活動截止日期:</td>
		<td>
		<input type="date" name="deadLine" size="45" required 
			 value="<%= (actVO==null)? "" :actVO.getDeadLine()%>" /></td>
	</tr>
	<tr>
	
		<td>活動舉辦日期:</td>
		<td>
		<input type="date" name="actDate" size="45" required 
			 value="<%= (actVO==null)? "" :actVO.getActDate()%>" /></td>
	</tr>
	<tr>
	
		<td>活動時段:</td>
		<td>
		<input type="text" name="actTime" size="45" required  placeholder="活動時段請填入整點數字,如:1600"
			 value="<%= (actVO==null)? "" :actVO.getActTime()%>" /></td>
	</tr>
	<tr>
	
		<td>會員姓名:</td>
		<td>
		<input type="text" name="participant" size="45" required  placeholder="請填入姓名"
			 value="<%= (actVO==null)? "" :actVO.getParticipant()%>" /></td>
	</tr>
	<tr>
	
		<td>活動價格(單人):</td>
		<td>
		<input type="text" name="actPrice" size="45" required  placeholder="請填入數字"
			 value="<%= (actVO==null)? "" :actVO.getActPrice()%>" /></td>
	</tr>

</table>
     <br>
         <input type="hidden" name="action" value="insert">
         <input type="submit" value="送出新增"></FORM>
     </body>
</html>