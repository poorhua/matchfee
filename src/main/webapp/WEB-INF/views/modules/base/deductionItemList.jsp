<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>抵扣项管理</title>
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
		<li class="active"><a href="${ctx}/base/deductionItem/">抵扣项列表</a></li>
		<shiro:hasPermission name="base:deductionItem:edit"><li><a href="${ctx}/base/deductionItem/form">抵扣项添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="deductionItem" action="${ctx}/base/deductionItem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>代码</th>
				<th>名称</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="base:deductionItem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="deductionItem">
			<tr>
				<td><a href="${ctx}/base/deductionItem/form?id=${deductionItem.id}">
					${deductionItem.id}
				</a></td>
				<td>
					${deductionItem.name}
				</td>
				<td>
					<fmt:formatDate value="${deductionItem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${deductionItem.remarks}
				</td>
				<shiro:hasPermission name="base:deductionItem:edit"><td>
    				<a href="${ctx}/base/deductionItem/form?id=${deductionItem.id}">修改</a>
					<a href="${ctx}/base/deductionItem/delete?id=${deductionItem.id}" onclick="return confirmx('确认要删除该抵扣项吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>