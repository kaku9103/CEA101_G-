<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
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

</head>

<body>
	<div class="page-wrapper chiller-theme toggled">
		<a id="show-sidebar" class="btn btn-sm btn-dark" href="#"> <i
			class="fas fa-bars"></i>
		</a>
		<nav id="sidebar" class="sidebar-wrapper">
			<div class="sidebar-content">
				<div class="sidebar-brand">
					<img src="img/logo.png" alt="" />
					<div id="close-sidebar">
						<i class="fas fa-times"></i>
					</div>
				</div>
				<div class="sidebar-header">
					<div class="user-pic">
						<img class="img-responsive img-rounded emp-pic"
							src="img/avatar-2.jpg" alt="User picture" />
					</div>
					<div class="user-info">
						<span class="emp-name">Wayne</span> <span class="emp-title">董事長</span>
						<span class="user-status"> <i class="fa fa-circle"></i> <span>Online</span>
						</span>
					</div>
				</div>
				<!-- sidebar-header  -->
				<div class="sidebar-search">
					<div>
						<div class="input-group">
							<input type="text" class="form-control search-menu"
								placeholder="Search..." />
							<div class="input-group-append">
								<span class="input-group-search"> <i class="fa fa-search"
									aria-hidden="true"></i>
								</span>
							</div>
						</div>
					</div>
				</div>
				<!-- sidebar-search  -->
				<div class="sidebar-menu">
					<ul>
						<li class="header-menu"><span>員工功能</span></li>
						<li><a href="#"> <i class="fa fa-book"></i> <span>個人資料修改</span>
						</a></li>
						<li><a href="#"> <i class="fa fa-calendar"></i> <span>工作排程</span>
								<span class="badge badge-pill badge-danger">3</span>
						</a></li>
						<li class="header-menu"><span>戴蒙度假村系統</span></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="fa fa-tachometer-alt"></i> <span>員工管理系統</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="#">功能 1</a></li>
									<li><a href="#">功能 2</a></li>
									<li><a href="#">功能 3</a></li>
								</ul>
							</div></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="fa fa-calendar"></i> <span>前台房務系統</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="#">功能 1</a></li>
									<li><a href="#">功能 2</a></li>
									<li><a href="#">功能 3</a></li>
								</ul>
							</div></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="fa fa-shopping-cart"></i> <span>會員管理系統</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="#">功能 1</a></li>
									<li><a href="#">功能 2</a></li>
									<li><a href="#">功能 3</a></li>
								</ul>
							</div></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="far fa-gem"></i> <span>餐飲管理系統</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="#">功能 1</a></li>
									<li><a href="#">功能 2</a></li>
									<li><a href="#">功能 3</a></li>
								</ul>
							</div></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="fa fa-chart-line"></i> <span>服務管理系統</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="#">功能 1</a></li>
									<li><a href="#">功能 2</a></li>
									<li><a href="#">功能 3</a></li>
								</ul>
							</div></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="fa fa-globe"></i> <span>活動管理系統</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="#">新增活動</a></li>
									<li><a href="<%=request.getContextPath()%>/back-end/backend-select_page.jsp">查詢活動</a></li>
									<li><a href="#">取消活動</a></li>
								</ul>
							</div></li>
					</ul>
				</div>
				<!-- sidebar-menu--end  -->
			</div>
		</nav>
		<!-- 頁面內容開始-->

		<div class="logo">
			<img src="<%=request.getContextPath()%>/img/logo.png">
		</div>
		<div class="form-title">
			<img src="<%=request.getContextPath()%>/img/loading.png">
			<h2>訂單查詢</h2>
		</div>
		<!-- Example single danger button -->
        <%@ include file="/back-end/actorder/act_order_select_page.jsp"%>

<!-- 頁面內容結束 -->
	
	<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	<script src="<%=request.getContextPath()%>/js/index-back.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
</body>

</html>