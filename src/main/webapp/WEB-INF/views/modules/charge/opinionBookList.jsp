<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>OpinonBook管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
		
    <input id="btnAdd" class="btn btn-primary" type="button" value="添加文件"/>

	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>文档编号</th>
				<th>文档日期</th>
				<th>链接</th>
				<th>备注信息</th>
				<shiro:hasPermission name="charge:opinionBook:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="opinionBook">
			<tr>
				<td><a href="${ctx}/charge/opinionBook/form?id=${opinionBook.id}">
					${opinionBook.name}
				</a></td>
				<td>
					${opinionBook.documentNo}
				</td>				
				<td>
					<fmt:formatDate value="${opinionBook.documentDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
				</td>				
				<td>
					${opinionBook.remarks}
				</td>
				<shiro:hasPermission name="charge:opinionBook:edit"><td>
    				<a href="${ctx}/charge/opinionBook/form?id=${opinionBook.id}">修改</a>
					<a href="${ctx}/charge/opinionBook/delete?id=${opinionBook.id}" onclick="return confirmx('确认要删除该OpinonBook吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>