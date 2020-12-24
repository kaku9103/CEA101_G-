<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actevent.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	ActEventService actEventSvc = new ActEventService();
	List<ActEventVO> list = actEventSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>�Ҧ����ʶ��ظ�� - listAllActEvent.jsp</title>
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

</head>
<body bgcolor='white'>

	<!-- <table id="table-1">
	<tr><td>
		 <h3>�Ҧ����ʺ��� - listAll_ActType.jsp</h3>
		 <h4><a href="act_event_select_page.jsp"><img src="img/logo.png" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table> -->

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
					<th>���ʶ��ؽs��</th>
					<th>���ʺ����s��</th>
					<th>���ʶ��ئW��</th>
					<th>���ʰT��</th>
					<th>�ק�</th>
					<th>�R��</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:forEach var="actEventVO" items="${list}">
						<tr>
							<td>${actEventVO.actEventNo}</td>
							<td>${actEventVO.actTypeNo}</td>
							<td>${actEventVO.actEventName}</td>
							<td>${actEventVO.actInfo}</td>

							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actevent/ActEventServlet" style="margin-bottom: 0px;">
									<button type="submit" class="btn btn-outline-dark" value="�ק�"> �ק�</button> 
									<input type="hidden"name="actEventNo" value="${actEventVO.actEventNo}"> 
									<input type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actevent/ActEventServlet"
									style="margin-bottom: 0px;">
									<button type="submit" class="btn btn-outline-dark" value="�R��"> �R��</button> 
									<input type="hidden"name="actEventNo" value="${actEventVO.actEventNo}"> 
									<input type="hidden" name="action" value="delete">
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