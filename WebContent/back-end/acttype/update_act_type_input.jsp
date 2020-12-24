<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.acttype.model.*"%>

<%
     ActTypeVO actTypeVO = (ActTypeVO) request.getAttribute("actTypeVO"); 
    //ActTypeServlet.java(Concroller), �s�Jreq��ActTYpeVO���� (�]�A�������X��ActTYpeVO����, �]�]�A��J��ƿ��~�ɪ�empActTYpeVO����)
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
		 <h4><a href="select_page.jsp"><img src="img/logo.png" width="100" height="32" border="0">�^����</a></h4>
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

<FORM METHOD="post" ACTION="ActTypeServlet" name="form1">
<table>
	
	<tr>
		<td>��ܺ����W��:</td>
		<td><input type="TEXT" name="ActTypeName" size="45"	placeholder="�п�J�s���ʺ����W��"
		value="<%=actTypeVO.getActTypeName()%>" /></td>
	</tr>
	<tr>
		<td>�s�W�����s��:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="ActTypeNo" size="45"	placeholder="�п�J�s���ʺ����s��"
		value="<%=actTypeVO.getActTypeNo()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="empno" value="<%=actTypeVO.getActTypeNo()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>
</html>