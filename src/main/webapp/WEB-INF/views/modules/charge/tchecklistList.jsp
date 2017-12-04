<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算清单信息管理</title>
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
		<li class="active"><a href="${ctx}/charge/tchecklist/">结算清单信息列表</a></li>
		<shiro:hasPermission name="charge:tchecklist:edit"><li><a href="${ctx}/charge/tchecklist/form">结算清单信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tchecklist" action="${ctx}/charge/tchecklist/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>公司代码：</label>
				<form:input path="companyname" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目名称</th>
				<th>公司代码</th>
				<th>建筑面积</th>
				<th>应缴费用</th>
				<th>减免费用</th>
				<th>已缴费用</th>
				<th>结欠费用</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="charge:tchecklist:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tchecklist">
			<tr>
				<td><a href="${ctx}/charge/tchecklist/form?id=${tchecklist.id}">
					${tchecklist.projectname}
				</a></td>
				<td>
					${tchecklist.companyname}
				</td>
				<td>
					${tchecklist.area}
				</td>
				<td>
					${tchecklist.assessment}
				</td>
				<td>
					${tchecklist.derate}
				</td>
				<td>
					${tchecklist.pay}
				</td>
				<td>
					${tchecklist.balancedue}
				</td>
				<td>
					<fmt:formatDate value="${tchecklist.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${tchecklist.remarks}
				</td>
				<shiro:hasPermission name="charge:tchecklist:edit"><td>
    				<a href="${ctx}/charge/tchecklist/form?id=${tchecklist.id}">修改</a>
					<a href="${ctx}/charge/tchecklist/delete?id=${tchecklist.id}" onclick="return confirmx('确认要删除该结算清单信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>