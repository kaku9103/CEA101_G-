<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>CEA101G1-ACT</title>
<title>Diamond Resort 後台管理</title>
<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/img/loading.png" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index-back.css" />
<script src="https://kit.fontawesome.com/dc3c868026.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backend.css" type="text/css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backend-select_page.css" type="text/css" /> 

</head>
<body bgcolor='white'>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	

	<ul id="search-table">
		<nav class="navbar-top navbar-light">
			<form class="form-inline">
				<button class="btn btn-outline-success" type="button" 
				onclick="location.href='<%=request.getContextPath()%>/back-end/backend-index.jsp?action=getAll'">
				  查詢全部訂單
				</button>
				<button class="btn btn-outline-success" type="button" 
				onclick="location.href='<%=request.getContextPath()%>/back-end/backend-add.jsp'">
				  新增訂單
				</button>
				<button class="btn btn-outline-success" type="button" 
				onclick="location.href='<%=request.getContextPath()%>/back-end/backend-add.jsp'">
				  新增照片
				</button>
			</form>
		</nav>
		
		<jsp:useBean id="ActOrderSvc" scope="page"
			class="com.actorder.model.ActOrderService" />
		<li id="font">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actorder/ActOrderServlet">
				<div class="input-group mb-3">
					<h5>訂單編號:</h5>
					<input type="hidden" name="action" class="form-control"
						value="getOne_For_Display"> <input type="text"
						name="actOdno" class="form-control" placeholder="訂單編號"
						aria-label="活動訂單編號" aria-describedby="button-addon2">
					<div class="input-group-append">
						<input type="hidden" name="action">
						<button class="btn btn-outline-secondary" type="submit"
							id="button-addon2">送出</button>
					</div>
				</div>
			</FORM>
		</li>
		<li id="font">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actorder/ActOrderServlet">
				<div class="input-group mb-3">
					<h5>會員編號:</h5>
					<select class="custom-select" id="inputGroupSelect02"
						name="actOdno">
						<c:forEach var="actOrderVO" items="${ActOrderSvc.all}">
							<option value="${actOrderVO.actOdno}">${actOrderVO.mbId}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_For_Display">
					<button class="btn btn-outline-secondary" type="submit"
						id="button-addon2">送出</button>
				</div>
			</FORM>
		</li>
		<li id="font">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actorder/ActOrderServlet">
				<div class="input-group mb-3">
					<h5>活動編號:</h5>
					<select class="custom-select" id="inputGroupSelect02"
						name="actOdno">
						<c:forEach var="actOrderVO" items="${ActOrderSvc.all}">
							<option value="${actOrderVO.actOdno}">${actOrderVO.actNo}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_For_Display">
					<button class="btn btn-outline-secondary" type="submit"
						id="button-addon2">送出</button>
				</div>
			</FORM>
		</li>

	</ul>

	<!-- 頁面內容結束 -->
	</div>
	<script src="js/jquery-3.5.1.min.js"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/index-back.js"></script>

</body>

</html>