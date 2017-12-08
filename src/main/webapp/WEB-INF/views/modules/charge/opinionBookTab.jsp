<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>征收管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
	    function toNewOpinonBook(){
		       window.location.replace("${ctx}/charge/opinionBook/form?prjNum=${charge.project.prjNum}");
	    }
	    
	    function toNewItem(docId){
		   window.location.replace("${ctx}/charge/opinionBookItem/form?doc.id="+docId);
	    }		
	</script>
</head>
<body>
    <sys:message content="${message}"/>
    <matchfee:chargeViewWithButtons charge="${charge}"></matchfee:chargeViewWithButtons><br>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/charge/charge/opinionBookTab">条件意见书</a></li>
		<li><a href="${ctx}/charge/charge/projectLicenseTab">规划许可证</a></li>
		<li><a href="${ctx}/charge/charge/deductionDocTab">设计院证明</a></li>
		<li><a href="${ctx}/charge/charge/projectDeductionTab">其他减项</a></li>
	</ul>

<c:if test="${empty charge.opinionBookList}">
  
	<div style="margin:10px 60px 10px 0;width='100%'">
	   <div align="right">
		    <shiro:hasPermission name="charge:charge:edit">
		    <input id="btnAdd" class="btn btn-primary" type="button" value="添加文件" onclick="toNewOpinonBook()"/>
		    </shiro:hasPermission>	   
	   </div>
	</div>    
    
</c:if>

<c:if test="${not empty charge.opinionBookList}">
	
	<c:forEach items="${charge.opinionBookList}" var="opinionBook">
	
	<legend>条件意见书</legend>
	<div style="margin:10px 60px 10px 0;width='100%'">
	<div align="right">
	<shiro:hasPermission name="charge:charge:edit">
 				<a href="${ctx}/charge/opinionBook/form?id=${opinionBook.id}">修改</a>
		<a href="${ctx}/charge/opinionBook/delete?id=${opinionBook.id}" onclick="return confirmx('确认要删除该条件意见书吗？', this.href)">删除</a>
	</shiro:hasPermission>			
	</div>			
	</div>	
	
	<matchfee:opinionBookView opinionBook="${opinionBook}"></matchfee:opinionBookView>	
	
	<div style="margin:10px 60px 10px 0;width='100%'">
	   <div align="right"><input id="btnAddItem" class="btn btn-primary" type="button" value="添加项目" onclick="toNewItem(${opinionBook.id})"/></div>
	</div>
	
	<matchfee:opinionBookItemView opinionBook="${opinionBook}"></matchfee:opinionBookItemView>	
	
	</c:forEach>	

</c:if>
    
</body>
</html>