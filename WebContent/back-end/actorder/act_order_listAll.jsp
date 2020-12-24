<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actorder.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ActOrderService actOrderSvc = new ActOrderService();
    List<ActOrderVO> list = actOrderSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<html>
<head>
<title>所有活動訂單 - act_order_listAll.jsp</title>
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/img/loading.png" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index-back.css" />
    <script src="https://kit.fontawesome.com/dc3c868026.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/backend.css" type="text/css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/backend-search.css" type="text/css" /> 

</head>
<body bgcolor='white'>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="table-content" id="content">
    <table class="table table-hover" id="table">
       <thead class="title">
              <tr class="table-primary">
                <th>活動訂單編號:</th>
                <th>活動編號:</th>
                <th>會員編號:</th>
                <th>訂單狀態:</th>
                <th>訂單日期:</th>
                <th>參加人數:</th>
                <th>訂單總價格:</th>
                <th>修改</th>
              </tr>
        </thead>
        <tbody>
              <tr>
              <c:forEach var="actOrderVO" items="${list}">
              
                   <td>${actOrderVO.actOdno}</td>
	               <td>${actOrderVO.actNo}</td>
	               <td>${actOrderVO.mbId}</td>
	               <td>${actOrderVO.odStatus}</td>
	               <td>${actOrderVO.odTime}</td>
	               <td>${actOrderVO.ppl}</td>
	               <td>${actOrderVO.totalPrice}</td>
                   
                    <td>
                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actorder/ActOrderServlet" style="margin-bottom: 0px;">
                    <button type="submit" class="btn btn-outline-dark" value="修改"> 修改</button>
                    <input type="hidden" name="actOdno"  value="${actOrderVO.actOdno}">
			        <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
                   </td>
              </tr>
              </c:forEach>
         </tbody>
     </table>
     <nav id="pagination-1" aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>
	</nav>
     
</div>


</body>
</html>