<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>抵扣项文件管理</title>
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
		<li class="active"><a href="${ctx}/charge/deductionDoc/">抵扣项文件列表</a></li>
		<shiro:hasPermission name="charge:charge:edit"><li><a href="${ctx}/charge/deductionDoc/form">抵扣项文件添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="deductionDoc" action="${ctx}/charge/deductionDoc/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>项目代码：</label>
				<form:input path="prjNum" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>项目代码</th>
				<th>文件编号</th>
				<th>文件类型</th>
				<th>保存路径</th>
				<th>文档日期</th>
				<th>备注信息</th>
				<shiro:hasPermission name="charge:charge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="deductionDoc">
			<tr>
				<td><a href="${ctx}/charge/deductionDoc/form?id=${deductionDoc.id}">
					${deductionDoc.name}
				</a></td>
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
	<div class="pagination">${page}</div>
</body>
</html>