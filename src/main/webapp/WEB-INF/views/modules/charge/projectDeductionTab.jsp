<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目抵扣项管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
	    function toNew(){
		       window.location.replace("${ctx}/charge/projectDeduction/form");
	    }		
	</script>
	
	<style type="text/css">
		.docHint {margin: 10px 0 10px 20px;font-size: 16px;font-weight:bold;}
	</style>
</head>
<body>
    <sys:message content="${message}"/>
    <legend>配套费征收</legend>	
    <matchfee:chargeViewWithButtons charge="${charge}"></matchfee:chargeViewWithButtons><br>

	<matchfee:chargeTabController tab="5"></matchfee:chargeTabController>
	<div class="docHint">*其他减项指滨湖区已缴费凭证、防空地下室批文、减免证明等。</div>
	<div style="margin:10px 60px 10px 0;text-align:right">
	    <shiro:hasPermission name="charge:charge:edit">
	    <c:if test="${charge.status lt '20' || (charge.status ge '20' && fns:getUser().isShy)}">
	    <input id="btnAdd" class="btn btn-primary" type="button" value="添加" onclick="toNew()"/>
	    </c:if>
	    </shiro:hasPermission>	  
	</div>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>文件</th>		
				<th>文件编号</th>	
				<th>名称</th>
				<th>文档日期</th>
				<th>面积（平米）</th>
				<th>金额（元）</th>
				<!-- <th>抵扣方式</th>-->
				<th>备注信息</th>
				<shiro:hasPermission name="charge:charge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${charge.projectDeductionList}" var="projectDeduction">
			<tr>
				<td>
				<a href="${projectDeduction.path}" target="_blank">${projectDeduction.filename}</a>					
				</td>			
				<td>
					${projectDeduction.documentNoDisplay}
				</td>				
				<td>
					${projectDeduction.name}
				</td>
				<td>
					<fmt:formatDate value="${projectDeduction.documentDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td style="text-align:right">
					<fmt:formatNumber value="${projectDeduction.area}" pattern="#,##0.00"/>
				</td>
				<td style="text-align:right">
					<fmt:formatNumber value="${projectDeduction.money}" pattern="#,##0.00"/>
				</td>				
				<!-- 
				<td>
					${fns:getDictLabel(projectDeduction.deductionType, 'deduction_type', '')}
				</td>
				 -->
				<td>
					${projectDeduction.remarks}
				</td>
				<shiro:hasPermission name="charge:charge:edit"><td>
				<c:if test="${charge.status lt '20' || (charge.status ge '20' && fns:getUser().isShy)}">
    				<a href="${ctx}/charge/projectDeduction/form?id=${projectDeduction.id}">修改</a>
					<a href="${ctx}/charge/projectDeduction/delete?id=${projectDeduction.id}" onclick="return confirmx('确认要删除该项目抵扣项吗？', this.href)">删除</a>
				</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<matchfee:logListView chargeId="${charge.id}"></matchfee:logListView>
</body>
</html>