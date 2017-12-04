<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目抵扣项管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/charge/projectDeduction/">项目抵扣项列表</a></li>
		<shiro:hasPermission name="charge:charge:edit"><li><a href="${ctx}/charge/projectDeduction/form">项目抵扣项添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="projectDeduction" action="${ctx}/charge/projectDeduction/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
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
		<c:forEach items="${page.list}" var="projectDeduction">
			<tr>
				<td><a href="${ctx}/charge/projectDeduction/form?id=${projectDeduction.id}">
					${projectDeduction.name}
				</a></td>
				<td>
					${projectDeduction.documentNo}
				</td>
				<td>
					${projectDeduction.path}
				</td>
				<td>
					<fmt:formatDate value="${projectDeduction.documentDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${projectDeduction.area}
				</td>
				<td>
					${projectDeduction.money}
				</td>
				<td>
					${projectDeduction.deductionType}
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
	<div class="pagination">${page}</div>
</body>
</html>