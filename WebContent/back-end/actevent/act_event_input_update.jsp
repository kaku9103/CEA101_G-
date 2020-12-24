<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actevent.model.*"%>

<%
     ActEventVO actEventVO = (ActEventVO) request.getAttribute("actEventVO"); 
    //ActEventServlet.java(Concroller), �s�Jreq��ActEvenVO���� (�]�A�������X��ActEventVO����, �]�]�A��J��ƿ��~�ɪ�empActTYpeVO����)
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
		 <h3>���ʺ����ק� - update_act_type_input.jsp</h3>
		 <h4><a href="act_event_select_page.jsp"><img src="img/logo.png" width="100" height="32" border="0">�^����</a></h4>
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

<FORM METHOD="post" ACTION="ActEventServlet" name="form1">
<table>
	
	<tr>
		<td>�s�W���ʶ��ؽs��:</td>
		<td><input type="TEXT" name="actEventNo" size="45"	placeholder="�п�J�s���ʶ��ؽs��"
		value="<%=actEventVO.getActEventNo()%>" /></td>
	</tr>
	<tr>
		<td>���ʺ����s��:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="actTypeNo" size="45"	placeholder="�п�J���ʺ����s��"
		value="<%=actEventVO.getActTypeNo()%>" /></td>
	</tr>
	<tr>
		<td>���ʶ��ئW��:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="actEventName" size="45"	placeholder="�п�J���ʶ��ئW��"
		value="<%=actEventVO.getActEventName()%>" /></td>
	</tr>
	<tr>
		<td>���ʱԭz:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="actInfo" size="45"	placeholder="�п�J���ʱԭz"
		value="<%=actEventVO.getActInfo()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="empno" value="<%=actEventVO.getActEventNo()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>
</html>