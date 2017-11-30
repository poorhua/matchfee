<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>征收管理</title>
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

    <matchfee:projectInfoView content="${project}"/>

	<form:form id="searchForm" modelAttribute="charge" action="${ctx}/charge/charge/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label></label>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="添加申报"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>代码</th>
				<th>申报人</th>
				<th>申报单位</th>
				<th>申报时间</th>
				<th>测算人</th>
				<th>测算时间</th>
				<th>审核人</th>
				<th>审核时间</th>
				<th>确认人</th>
				<th>确认时间</th>
				<th>付款凭证保存路径</th>
				<th>测算金额</th>
				<th>付款金额</th>
				<th>状态</th>
				<shiro:hasPermission name="charge:charge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="charge">
			<tr>
				<td><a href="${ctx}/charge/charge/form?id=${charge.id}">
					${charge.id}
				</a></td>
				<td>
					${charge.reportStaff}
				</td>
				<td>
					${charge.reportEntity}
				</td>
				<td>
					<fmt:formatDate value="${charge.reportDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${charge.calStaff}
				</td>
				<td>
					<fmt:formatDate value="${charge.calDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${charge.approveStaff}
				</td>
				<td>
					<fmt:formatDate value="${charge.approveDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${charge.confirmStaff}
				</td>
				<td>
					<fmt:formatDate value="${charge.confirmDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${charge.payTicketPath}
				</td>
				<td>
					${charge.calMoney}
				</td>
				<td>
					${charge.payMoney}
				</td>
				<td>
					${charge.status}
				</td>
				<shiro:hasPermission name="charge:charge:edit"><td>
    				<a href="${ctx}/charge/charge/form?id=${charge.id}">修改</a>
					<a href="${ctx}/charge/charge/delete?id=${charge.id}" onclick="return confirmx('确认要删除该征收吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>