<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actorder.model.*"%>

<%
    ActOrderVO actOrderVO = (ActOrderVO) request.getAttribute("actOrderVO"); 
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�s�W���ʭq�� - addAct.jsp</title>

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
		 <h3>�s�W���ʭq�� - Act_order_add.jsp</h3>
		 <h4><a href="act_order_select_page.jsp"><img src="img/logo.png" width="250" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actorder/ActOrderServlet" name="form1">
<table>
	<tr>
		<td>���ʭq��s��:</td>
		<td><input type="TEXT" name="actOdno" size="45" required placeholder="�п�J���ʭq��s��"
			 value="<%= (actOrderVO==null)? "" : actOrderVO.getActOdno()%>" /></td>
	</tr>
	<tr>
		<td>���ʽs��:</td>
		<td><input type="TEXT" name="actNo" size="45" required placeholder="�п�J���ʽs��"
			 value="<%= (actOrderVO==null)? "" :actOrderVO.getActNo()%>" /></td>
	</tr>
	<tr>
	
		<td>�|���s��:</td>
		<td>
		<input type="TEXT" name="mbId" size="45" required placeholder="�ж�J�|���s��"
			 value="<%= (actOrderVO==null)? "" :actOrderVO.getMbId()%>" /></td>
	</tr>
	<tr>
	
		<td>�q����:</td>
		<td>
		<input type="date" name="odTime" size="45" required 
			 value="<%= (actOrderVO==null)? "" :actOrderVO.getOdTime()%>" /></td>
	</tr>
	<tr>
		<td>���ʪ��A:</td>
		<td><input type="TEXT" name="odStatus" size="45" required placeholder="�п�ܬ��ʪ��A"
			 value="<%= (actOrderVO==null)? "" :actOrderVO.getOdStatus()%>" /></td>
	</tr>
	<tr>
	
		<td>�ѥ[�H��:</td>
		<td>
		<input type="text" name="ppl" size="45" required 
			 value="<%= (actOrderVO==null)? "" :actOrderVO.getPpl()%>" /></td>
	</tr>
	<tr>
	
		<td>�q���`����:</td>
		<td>
		<input type="text" name="totalPrice" size="45" required 
			 value="<%= (actOrderVO==null)? "" :actOrderVO.getTotalPrice()%>" /></td>
	</tr>
	
	

</table>
     <br>
         <input type="hidden" name="action" value="insert">
         <input type="submit" value="�e�X�s�W"></FORM>
     </body>
</html>