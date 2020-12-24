<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actevent.model.*"%>

<%
    ActEventVO actEventVO = (ActEventVO) request.getAttribute("actEventVO"); 
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�s�W���ʺ��� - addAct.jsp</title>

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
		 <h3>�s�W���ʶ��� - addAct.jsp</h3>
		 <h4><a href="act_event_select_page.jsp"><img src="img/logo.png" width="250" height="100" border="0">�^����</a></h4>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actevent/ActEventServlet" name="form1">
<table>
	<tr>
		<td>���ʶ��ؽs��:</td>
		<td><input type="TEXT" name="actEventNo" size="45" required placeholder="�п�J���ʶ��ؽs��"
			 value="<%= (actEventVO==null)? "" : actEventVO.getActEventNo()%>" /></td>
	</tr>
	<tr>
		<td>���ʺ����s��:</td>
		<td><input type="TEXT" name="actTypeNo" size="45" required placeholder="�п�ܬ��ʺ����s��"
			 value="<%= (actEventVO==null)? "" :actEventVO.getActEventName()%>" /></td>
	</tr>
	<tr>
		<td>���ʶ��ئW��:</td>
		<td><input type="TEXT" name="actEventName" size="45" required placeholder="�п�J���ʶ��ئW��"
			 value="<%= (actEventVO==null)? "" :actEventVO.getActEventName()%>" /></td>
	</tr>
	<tr>
		<td>���e�ԭz:</td>
		<td><input type="TEXT" name="actInfo" size="45" required placeholder="��²�z���ʤ��e"
			 value="<%= (actEventVO==null)? "" :actEventVO.getActInfo()%>" /></td>
	</tr>

	

</table>
     <br>
         <input type="hidden" name="action" value="insert">
         <input type="submit" value="�e�X�s�W"></FORM>
     </body>
</html>