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
</head>
<body>
    <sys:message content="${message}"/>
    <matchfee:chargeViewWithButtons charge="${charge}"></matchfee:chargeViewWithButtons><br>

	<ul class="nav nav-tabs">
		<li><a href="${ctx}/charge/charge/opinionBookTab">条件意见书</a></li>
		<li><a href="${ctx}/charge/charge/projectLicenseTab">工程许可证</a></li>
		<li><a href="${ctx}/charge/charge/deductionDocTab">设计院证明</a></li>
		<li class="active"><a href="${ctx}/charge/charge/projectDeductionTab">其他减项</a></li>
	</ul>
	<div style="margin:10px 60px 10px 0;width='100%'">
	   <div align="right">
		    <shiro:hasPermission name="charge:charge:edit">
		    <input id="btnAdd" class="btn btn-primary" type="button" value="添加" onclick="toNew()"/>
		    </shiro:hasPermission>	   
	   </div>
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>文件编号</th>
				<th>保存路径</th>
				<th>文档日期</th>
				<th>面积（平米）</th>
				<th>金额（元）</th>
				<th>抵扣方式</th>
				<th>备注信息</th>
				<shiro:hasPermission name="charge:charge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${charge.projectDeductionList}" var="projectDeduction">
			<tr>
				<td>
					${projectDeduction.name}
				</td>
				<td>
					${projectDeduction.documentNo}
				</td>
				<td>
				<input type="hidden" id="path${projectDeduction.id}" name="path${projectDeduction.id}" value="${projectDeduction.path}">
				<sys:ckfinder input="path${projectDeduction.id}" type="files" uploadPath="/charge/projectDeduction" selectMultiple="false" readonly="true"/>					
				</td>
				<td>
					<fmt:formatDate value="${projectDeduction.documentDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${projectDeduction.area}
				</td>
				<td>
					${projectDeduction.money}
				</td>
				<td>
					${fns:getDictLabel(projectDeduction.deductionType, 'deduction_type', '')}
				</td>
				<td>
					${projectDeduction.remarks}
				</td>
				<shiro:hasPermission name="charge:charge:edit"><td>
    				<a href="${ctx}/charge/projectDeduction/form?id=${projectDeduction.id}">修改</a>
					<a href="${ctx}/charge/projectDeduction/delete?id=${projectDeduction.id}" onclick="return confirmx('确认要删除该项目抵扣项吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>