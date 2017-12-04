<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>抵扣项文件管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
    <sys:message content="${message}"/>
    <matchfee:chargeView charge="${charge}"></matchfee:chargeView><br>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/charge/charge/opinionBookTab">条件意见书</a></li>
		<li><a href="${ctx}/charge/charge/projectLicenseTab">工程许可证</a></li>
		<li class="active"><a href="${ctx}/charge/charge/deductionDocTab">设计院证明</a></li>
		<li><a href="${ctx}/charge/charge/projectDeductionTab">其他减项</a></li>
	</ul>    
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>代码</th>
				<th>征收代码</th>
				<th>名称</th>
				<th>项目代码</th>
				<th>文件编号</th>
				<th>文件类型</th>
				<th>保存路径</th>
				<th>文档日期</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="charge:charge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="deductionDoc">
			<tr>
				<td><a href="${ctx}/charge/deductionDoc/form?id=${deductionDoc.id}">
					${deductionDoc.id}
				</a></td>
				<td>
					${deductionDoc.chargeId}
				</td>
				<td>
					${deductionDoc.name}
				</td>
				<td>
					${deductionDoc.prjNum}
				</td>
				<td>
					${deductionDoc.documentNo}
				</td>
				<td>
					${deductionDoc.documentType}
				</td>
				<td>
					${deductionDoc.path}
				</td>
				<td>
					<fmt:formatDate value="${deductionDoc.documentDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${deductionDoc.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${deductionDoc.remarks}
				</td>
				<shiro:hasPermission name="charge:charge:edit"><td>
    				<a href="${ctx}/charge/deductionDoc/form?id=${deductionDoc.id}">修改</a>
					<a href="${ctx}/charge/deductionDoc/delete?id=${deductionDoc.id}" onclick="return confirmx('确认要删除该抵扣项文件吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>