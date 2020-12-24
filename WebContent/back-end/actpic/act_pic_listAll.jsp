<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actpic.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	ActPicService actPicSvc = new ActPicService();
	List<ActPicVO> list = actPicSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>�Ҧ����ʷӤ���� - listAll_ActPIC.jsp</title>
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

	<!--<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����ʷӤ� - listAll_ActPic.jsp</h3>
		 <h4><a href="act_pic_select_page.jsp"><img src="img/logo.png" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>  -->

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
					<th>���ʷӤ��s��</th>
					<th>���ʶ��ؽs��</th>
					<th>���ʷӤ�</th>
					<th>�ק�</th>
					<th>�R��</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="actPicVO" items="${list}">
					<tr>
						<td>${actPicVO.actPicNo}</td>
						<td>${actPicVO.actEventNo}</td>
						<td><img
							src="<%=request.getContextPath()%>/ActPicReaderServlet?actPicNo=${actPicVO.actPicNo}&action=getOnePic">

						</td>

						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/actpic/ActPicServlet"
								style="margin-bottom: 0px;">
								<input type="submit" value="�ק�"> <input type="hidden"
									name="ActPicNo" value="${actPicVO.actPicNo}"> <input
									type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/actpic/ActPicServlet"
								style="margin-bottom: 0px;">
								<input type="submit" value="�R��"> <input type="hidden"
									name="ActPicNo" value="${actPicVO.actPicNo}"> <input
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