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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/charge/charge/">征收列表</a></li>
		<shiro:hasPermission name="charge:charge:edit"><li><a href="${ctx}/charge/charge/form">征收添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="charge" action="${ctx}/charge/charge/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目代码：</label>
				<form:input path="prjNum" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>项目名称：</label>
				<form:input path="prjName" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>项目地址：</label>
				<form:input path="prjAddress" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>申报时间：</label>
				<input name="reportDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${charge.reportDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>状态：</label>
				<form:input path="status" htmlEscape="false" maxlength="8" class="input-medium"/>
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
				<th>项目代码</th>
				<th>项目名称</th>
				<th>项目地址</th>
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
				<th>备注信息</th>
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
					${charge.prjNum}
				</td>
				<td>
					${charge.prjName}
				</td>
				<td>
					${charge.prjAddress}
				</td>
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
					${charge.remarks}
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