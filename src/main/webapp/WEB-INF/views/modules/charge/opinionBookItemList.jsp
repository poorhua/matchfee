<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>条件意见书项目管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>抵扣项代码</th>
				<th>面积（平米）</th>
				<th>金额（元）</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="charge:opinionBookItem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="opinionBookItem">
			<tr>
				<td><a href="${ctx}/charge/opinionBookItem/form?id=${opinionBookItem.id}">
					${opinionBookItem.itemId}
				</a></td>
				<td>
					${opinionBookItem.area}
				</td>
				<td>
					${opinionBookItem.money}
				</td>
				<td>
					<fmt:formatDate value="${opinionBookItem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${opinionBookItem.remarks}
				</td>
				<shiro:hasPermission name="charge:opinionBookItem:edit"><td>
    				<a href="${ctx}/charge/opinionBookItem/form?id=${opinionBookItem.id}">修改</a>
					<a href="${ctx}/charge/opinionBookItem/delete?id=${opinionBookItem.id}" onclick="return confirmx('确认要删除该条件意见书项目吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>