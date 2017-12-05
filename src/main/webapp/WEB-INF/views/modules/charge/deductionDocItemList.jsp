<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>抵扣项目管理</title>
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
					${deductionDocItem.doc}
				</td>
				<td>
					${deductionDocItem.item}
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