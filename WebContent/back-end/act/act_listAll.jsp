<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ActService actSvc = new ActService();
	List<ActVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有活動資料 - listAllAct.jsp</title>
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
		 <h3>所有活動 - act_listAll.jsp</h3>
		 <h4><a href="act_select_page.jsp"><img src="img/logo.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>-->

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
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
					<th>活動編號</th>
					<th>活動項目編號</th>
					<th>活動名稱</th>
					<th>活動狀態</th>
					<th>活動報名日期</th>
					<th>活動舉辦日期</th>
					<th>活動截止日期</th>
					<th>活動時段</th>
					<th>參加者</th>
					<th>活動價格</th>
					<th>修改</th>
					<th>刪除</th>
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
									<input type="submit" value="修改"> <input type="hidden"
										name="actNo" value="${actVO.actNo}"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/act/ActServlet"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除"> <input type="hidden"
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