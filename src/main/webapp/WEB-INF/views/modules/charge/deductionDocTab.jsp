<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>抵扣项文件管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
	    function toNewDeductionDoc(){
		       window.location.replace("${ctx}/charge/deductionDoc/form?prjNum=${charge.project.prjNum}");
	    }
	    
	    function toNewItem(docId){
		   window.location.replace("${ctx}/charge/deductionDocItem/form?doc.id="+docId);
	    }		
	</script>
	
	<style type="text/css">
		.docHint {margin: 10px 0 10px 20px;font-size: 16px;font-weight:bold;}
	</style>	
</head>
<body>
	<sys:message content="${message}" />
    <legend>配套费征收</legend>	
	<matchfee:chargeViewWithButtons charge="${charge}"></matchfee:chargeViewWithButtons>
	<br>
	<matchfee:chargeTabController tab="3"></matchfee:chargeTabController>
	
	<div class="docHint">*本期工建配套证明。</div>

	<div style="margin: 10px 60px 10px 0;text-align:right">
		<shiro:hasPermission name="charge:charge:edit">
		<c:if test="${charge.status lt '20' || (charge.status ge '20' && fns:getUser().isShy)}">
			<input id="btnAdd" class="btn btn-primary" type="button"
				value="添加文件" onclick="toNewDeductionDoc()" />
		</c:if>
		</shiro:hasPermission>
	</div>

	<c:forEach items="${charge.deductionDocList}" var="deductionDoc">

		<legend>设计院证明---${deductionDoc.documentNo}</legend>
		<div style="margin: 10px 60px 10px 0;text-align:right">
			<shiro:hasPermission name="charge:charge:edit">
			<c:if test="${charge.status lt '20' || (charge.status ge '20' && fns:getUser().isShy)}">
				<a href="${ctx}/charge/deductionDoc/form?id=${deductionDoc.id}">修改</a>
				<a href="${ctx}/charge/deductionDoc/delete?id=${deductionDoc.id}"
					onclick="return confirmx('确认要删除该设计院证明吗？', this.href)">删除</a>
			</c:if>
			</shiro:hasPermission>
		</div>

		<matchfee:deductionDocView deductionDoc="${deductionDoc}"></matchfee:deductionDocView>
		
	   <c:choose>
	      <c:when test="${charge.status lt '20' || (charge.status ge '20' && fns:getUser().isShy)}">
			<div style="margin: 10px 60px 10px 0;text-align:right">
				<input id="btnAddItem" class="btn btn-primary" type="button"
					value="添加项目" onclick="toNewItem(${deductionDoc.id})" />
			</div>
			
			<matchfee:deductionDocItemView deductionDoc="${deductionDoc}" withOperation="1"></matchfee:deductionDocItemView>
		
  		  </c:when>			   			  	   			  		   			  
  		  <c:otherwise>
            <matchfee:deductionDocItemView deductionDoc="${deductionDoc}" withOperation="0"></matchfee:deductionDocItemView>	  
  		  </c:otherwise>		   			  
	   </c:choose>		
	   
	   <hr style="border:1px dotted #036" />
		
	</c:forEach>
	
	<matchfee:logListView chargeId="${charge.id}"></matchfee:logListView>

</body>
</html>