<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actpic.model.*"%>

<%
    ActPicVO actPicVO = (ActPicVO) request.getAttribute("actPicVO"); 
    //ActPicServlet.java(Concroller), �s�Jreq��ActPicVO���� (�]�A�������X��ActPicVO����, �]�]�A��J��ƿ��~�ɪ�ActPicVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���ʷӤ��ק� - act_pic_input_update.jsp</title>

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
		 <h3>���ʷӤ��ק� - act_pic_input_update.jsp</h3>
		 <h4><a href="/act_pic_select_page.jsp"><img src="img/logo.png" width="100" height="32" border="0">�^����</a></h4>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actpic/ActPicServlet" name="form1" enctype="multipart/form-data">
<table>
	
	<tr>
		<td>�s�W���ʷӤ��s��:</td>
		<td><input type="TEXT" name="ActPicNo" size="45"	placeholder="�п�J�s���ʷӤ��s��"
		value="<%=actPicVO.getActPicNo()%>" /></td>
	</tr>
	<tr>
		<td>���ʶ��ؽs��:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="ActEventNo" size="45"	placeholder="�п�J���ʶ��ؽs��"
		value="<%=actPicVO.getActEventNo()%>" /></td>
	</tr>
	<tr>
		<td>���ʷӤ��W��:<font color=red><b>*</b></font></td>
		<td><input type="file" name="ActPic" size="45"
		value="<%=actPicVO.getActPic()%>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="empno" value="<%=actPicVO.getActPicNo()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>
</html>