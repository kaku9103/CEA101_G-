<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>

<%
     ActVO actVO = (ActVO) request.getAttribute("actVO"); 
    //ActServlet.java(Concroller), �s�Jreq��ActVO���� (�]�A�������X��ActEventVO����, �]�]�A��J��ƿ��~�ɪ�empActTYpeVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���ʺ����ק� - update_act_type_input.jsp</title>

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
		 <h3>���ʭק� - update_act_input.jsp</h3>
		 <h4><a href="act_select_page.jsp"><img src="img/logo.png" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/ActServlet" name="form1">
<table>
	
	<tr>
		<td>�s�W���ʽs��:</td>
		<td><input type="TEXT" name="actNo" size="45"	placeholder="�п�J�s���ʶ��ؽs��"
		value="<%=actVO.getActNo()%>" /></td>
	</tr>
	<tr>
		<td>���ʶ��ؽs��:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="actEventNo" size="45"	placeholder="�п�J���ʺ����s��"
		value="<%=actVO.getActEventNo()%>" /></td>
	</tr>
	<tr>
		<td>���ʦW��:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="actName" size="45"	placeholder="�п�J���ʶ��ئW��"
		value="<%=actVO.getActName()%>" /></td>
	</tr>
	<tr>
		<td>���ʪ��A:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="actStatus" size="45"	placeholder="�п�J���ʱԭz"
		value="<%=actVO.getActStatus()%>" /></td>
	</tr>
	<tr>
	
		<td>���ʳ��W���:</td>
		<td>
		<input type="date" name="actRegTime" size="45" required 
			 value="<%=actVO.getActRegTime()%>" /></td>
	</tr>
	<tr>
	
		<td>���ʺI����:</td>
		<td>
		<input type="date" name="deadLine" size="45" required 
			 value="actVO.getDeadLine()%>" /></td>
	</tr>
	<tr>
	
		<td>�����|����:</td>
		<td>
		<input type="date" name="actDate" size="45" required 
			 value="<%=actVO.getActDate()%>" /></td>
	</tr>
	<tr>
	
		<td>���ʮɬq:</td>
		<td>
		<input type="text" name="actTime" size="45" required  placeholder="���ʮɬq�ж�J���I�Ʀr,�p:1600"
			 value="<%=actVO.getActTime()%>" /></td>
	</tr>
	<tr>
	
		<td>�|���m�W:</td>
		<td>
		<input type="text" name="participant" size="45" required  placeholder="�ж�J�m�W"
			 value="<%=actVO.getParticipant()%>" /></td>
	</tr>
	<tr>
	
		<td>���ʻ���(��H):</td>
		<td>
		<input type="text" name="actPrice" size="45" required  placeholder="�ж�J�Ʀr"
			 value="<%=actVO.getActPrice()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="empno" value="<%=actVO.getActNo()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>
</html>