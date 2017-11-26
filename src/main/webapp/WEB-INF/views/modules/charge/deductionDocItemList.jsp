<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>抵扣项目管理</title>
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
		<li class="active"><a href="${ctx}/charge/deductionDocItem/">抵扣项目列表</a></li>
		<shiro:hasPermission name="charge:deductionDocItem:edit"><li><a href="${ctx}/charge/deductionDocItem/form">抵扣项目添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="deductionDocItem" action="${ctx}/charge/deductionDocItem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>文档代码：</label>
				<form:input path="docId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>抵扣项代码：</label>
				<form:input path="itemId" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>文档代码</th>
				<th>抵扣项代码</th>
				<th>面积（平米）</th>
				<th>金额（元）</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="charge:deductionDocItem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="deductionDocItem">
			<tr>
				<td><a href="${ctx}/charge/deductionDocItem/form?id=${deductionDocItem.id}">
					${deductionDocItem.id}
				</a></td>
				<td>
					${deductionDocItem.docId}
				</td>
				<td>
					${deductionDocItem.itemId}
				</td>
				<td>
					${deductionDocItem.area}
				</td>
				<td>
					${deductionDocItem.money}
				</td>
				<td>
					<fmt:formatDate value="${deductionDocItem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${deductionDocItem.remarks}
				</td>
				<shiro:hasPermission name="charge:deductionDocItem:edit"><td>
    				<a href="${ctx}/charge/deductionDocItem/form?id=${deductionDocItem.id}">修改</a>
					<a href="${ctx}/charge/deductionDocItem/delete?id=${deductionDocItem.id}" onclick="return confirmx('确认要删除该抵扣项目吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>