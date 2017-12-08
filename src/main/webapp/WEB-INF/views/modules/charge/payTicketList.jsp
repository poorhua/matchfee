<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>缴费凭证管理</title>
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
		<li class="active"><a href="${ctx}/charge/payTicket/">缴费凭证列表</a></li>
		<shiro:hasPermission name="charge:payTicket:edit"><li><a href="${ctx}/charge/payTicket/form">缴费凭证添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="payTicket" action="${ctx}/charge/payTicket/" method="post" class="breadcrumb form-search">
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
				<th>票据号</th>
				<th>保存路径</th>
				<th>缴费日期</th>
				<shiro:hasPermission name="charge:payTicket:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="payTicket">
			<tr>
				<td><a href="${ctx}/charge/payTicket/form?id=${payTicket.id}">
					${payTicket.name}
				</a></td>
				<td>
					${payTicket.ticketNo}
				</td>
				<td>
					${payTicket.path}
				</td>
				<td>
					<fmt:formatDate value="${payTicket.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="charge:payTicket:edit"><td>
    				<a href="${ctx}/charge/payTicket/form?id=${payTicket.id}">修改</a>
					<a href="${ctx}/charge/payTicket/delete?id=${payTicket.id}" onclick="return confirmx('确认要删除该缴费凭证吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>