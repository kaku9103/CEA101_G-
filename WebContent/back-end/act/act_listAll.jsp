<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	ActService actSvc = new ActService();
	List<ActVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>�Ҧ����ʸ�� - listAllAct.jsp</title>
<link rel="icon" type="image/png" href="img/loading.png" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/index-back.css" />
<script src="https://kit.fontawesome.com/dc3c868026.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/backend.css" type="text/css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="../css/backend-search.css" type="text/css" />

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
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table tr {
	width: 100px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	width: 100px;
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>
	<!--  <table id="table-1">
	<tr><td>
		 <h3>�Ҧ����� - act_listAll.jsp</h3>
		 <h4><a href="act_select_page.jsp"><img src="img/logo.png" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>-->

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div class="table-content" id="content">
		<table class="table table-hover" id="table">
			<thead class="title">
				<tr class="table-primary">
					<th>���ʽs��</th>
					<th>���ʶ��ؽs��</th>
					<th>���ʦW��</th>
					<th>���ʪ��A</th>
					<th>���ʳ��W���</th>
					<th>�����|����</th>
					<th>���ʺI����</th>
					<th>���ʮɬq</th>
					<th>�ѥ[��</th>
					<th>���ʻ���</th>
					<th>�ק�</th>
					<th>�R��</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:forEach var="actVO" items="${list}">
						<tr>
							<td>${actVO.actNo}</td>
							<td>${actVO.actEventNo}</td>
							<td>${actVO.actName}</td>
							<td>${actVO.actStatus}</td>
							<td>${actVO.actRegTime}</td>
							<td>${actVO.actDate}</td>
							<td>${actVO.deadLine}</td>
							<td>${actVO.actTime}</td>
							<td>${actVO.participant}</td>
							<td>${actVO.actPrice}</td>

							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/act/ActServlet"
									style="margin-bottom: 0px;">
									<input type="submit" value="�ק�"> <input type="hidden"
										name="actNo" value="${actVO.actNo}"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/act/ActServlet"
									style="margin-bottom: 0px;">
									<input type="submit" value="�R��"> <input type="hidden"
										name="actNo" value="${actVO.actNo}"> <input
										type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>