<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actorder.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
   ActOrderVO actOrderVO = (ActOrderVO) request.getAttribute("actOrderVO"); //ActServlet.java(Concroller), �s�Jreq��ActVO����
%>

<html>
<head>
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/img/loading.png" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index-back.css" />
    <script src="https://kit.fontawesome.com/dc3c868026.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/backend.css" type="text/css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/backend-search.css" type="text/css" /> 

<title>���ʭq���� - actOrder_listOne.jsp</title>

</head>
		<nav class="navbar-top navbar-light">
			<form class="form-inline">
				<button class="btn btn-outline-success" type="button" 
				onclick="location.href='<%=request.getContextPath()%>/back-end/backend-select_page.jsp'">
				  �^�q��d��
				</button>
			</form>
		</nav>

<div class="table-content" id="content">
    <table class="table table-hover" id="table">
       <thead class="title">
              <tr class="table-primary">
                <th>���ʭq��s��:</th>
                <th>���ʽs��:</th>
                <th>�|���s��:</th>
                <th>�q�檬�A:</th>
                <th>�q����:</th>
                <th>�ѥ[�H��:</th>
                <th>�q���`����:</th>
                <th>�ק�</th>
              </tr>
        </thead>
        <tbody>
              <tr>
                   <td>${actOrderVO.actOdno}</td>
	               <td>${actOrderVO.actNo}</td>
	               <td>${actOrderVO.mbId}</td>
	               <td>${actOrderVO.odStatus}</td>
	               <td>${actOrderVO.odTime}</td>
	               <td>${actOrderVO.ppl}</td>
	               <td>${actOrderVO.totalPrice}</td>
                   
                    <td>
                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actorder/ActOrderServlet" style="margin-bottom: 0px;">
                    <button type="submit" class="btn btn-outline-dark" value="�ק�"> �ק�</button>
                    <input type="hidden" name="actOdno"  value="${actOrderVO.actOdno}">
			        <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
                   </td>
              </tr>
         </tbody>
     </table>
</div>

<script src="js/jquery-3.5.1.min.js"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/index-back.js"></script>
	<script src="js/bootstrap.bundle.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>

</html>