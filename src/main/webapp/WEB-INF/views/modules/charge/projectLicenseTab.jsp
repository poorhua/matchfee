<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规划许可证管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
	    function toNewPage(){
		       window.location.replace("${ctx}/charge/projectLicense/form");
	    }		
	</script>
</head>
<body>
    <sys:message content="${message}"/>
    <legend>配套费征收</legend>	
    <matchfee:chargeViewWithButtons charge="${charge}"></matchfee:chargeViewWithButtons><br>
	
	<matchfee:chargeTabController tab="1"></matchfee:chargeTabController>
	
	<div style="margin:10px 60px 10px 0;text-align:right">
	    <shiro:hasPermission name="charge:charge:edit">	      
	    
	    <c:if test="${charge.status lt '20' || (charge.status ge '20' && fns:getUser().isShy)}">		 
	    <input id="btnAdd" class="btn btn-primary" type="button" value="添加" onclick="toNewPage()"/>  			    
	    </c:if>	    
	    
	    </shiro:hasPermission>	   
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
                <th>文件</th>		
                <th>规划许可证编号</th>	
				<th>项目名称</th>
				<th>发证日期</th>
				<th>地上面积（平米）</th>
				<th>地下面积（平米）</th>
				<th>总面积（平米）</th>
				<shiro:hasPermission name="charge:charge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${charge.projectLicenseList}" var="projectLicense">
			<tr>
				<td>	
				<a href="${projectLicense.path}" target="_blank">${projectLicense.filename}</a>				
				</td>
				<td>
					${projectLicense.documentNoDisplay}
				</td>							
				<td>
					${projectLicense.name}
				</td>
				<td>
					<fmt:formatDate value="${projectLicense.documentDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td style="text-align:right">
					<fmt:formatNumber value="${projectLicense.upArea}" pattern="#,##0.00"/>
				</td>
				<td style="text-align:right">
					<fmt:formatNumber value="${projectLicense.downArea}" pattern="#,##0.00"/>
				</td>				
				<td style="text-align:right">
					<fmt:formatNumber value="${projectLicense.totalAreaDisplay}" pattern="#,##0.00"/>
				</td>								
				<shiro:hasPermission name="charge:charge:edit"><td>	
		   		  
			    <c:if test="${charge.status lt '20' || (charge.status ge '20' && fns:getUser().isShy)}">		 
    				<a href="${ctx}/charge/projectLicense/form?id=${projectLicense.id}">修改</a>
					<a href="${ctx}/charge/projectLicense/delete?id=${projectLicense.id}" onclick="return confirmx('确认要删除该规划许可证吗？', this.href)">删除</a> 			    
			    </c:if>		   		  			

				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<matchfee:logListView chargeId="${charge.id}"></matchfee:logListView>
</body>
</html>