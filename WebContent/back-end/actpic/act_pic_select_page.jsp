<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>CEA101G1-ACT</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>���ʷӤ�</h3><h4>( ACT_PIC )</h4></td></tr>
</table>

<p>This is the Home page for ACT_PIC</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  
  <li><a href='act_pic_listAll.jsp?action=getAll'> List</a> all ActPic    <h4>(getFromSession).</h4> <br><br><br></li>
  
   <jsp:useBean id="ActPicSvc" scope="page" class="com.actpic.model.ActPicService" />
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actpic/ActPicServlet" >
       <b>��ܷӤ��s��:</b>
       <select size="1" name="actPicNo">
         <c:forEach var="actPicVO" items="${ActPicSvc.all}" > 
          <option value="${actPicVO.actPicNo}">${actPicVO.actPicNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
   <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actpic/ActPicServlet" >
       <b>��ܬ��ʶ��ؽs��:</b>
       <select size="1" name="actPicNo">
         <c:forEach var="actPicVO" items="${ActPicSvc.all}" > 
          <option value="${actPicVO.actPicNo}">${actPicVO.actEventNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  

</ul>
<h3>���ʺ����޲z</h3>

<ul>
  <li><a href='act_pic_add.jsp'>Add</a> �s�W���ʺ���.</li>
</ul>

<script>    
   function fun1(){
      with(document.form1){
         if (empno.value=="") 
             alert("�п�J���ʺ����s��!");
         else if (isNaN(empno.value)) 
             alert("�s���榡�����T!");
         else
             submit();
      }
   }
</script>

</body>
</html>