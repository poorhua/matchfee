<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程许可证管理</title>
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
		<li class="active"><a href="${ctx}/charge/projectLicense/">工程许可证列表</a></li>
		<shiro:hasPermission name="charge:charge:edit"><li><a href="${ctx}/charge/projectLicense/form">工程许可证添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="projectLicense" action="${ctx}/charge/projectLicense/" method="post" class="breadcrumb form-search">
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
				<th>文件编号</th>
				<th>保存路径</th>
				<th>文档日期</th>
				<th>地上面积（平米）</th>
				<th>地下面积（平米）</th>
				<th>备注信息</th>
				<shiro:hasPermission name="charge:charge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="projectLicense">
			<tr>
				<td><a href="${ctx}/charge/projectLicense/form?id=${projectLicense.id}">
					${projectLicense.name}
				</a></td>
				<td>
					${projectLicense.documentNo}
				</td>
				<td>
					<input type="hidden" id="path${projectLicense.id}" name="path${projectLicense.id}" value="${projectLicense.path}">
					<sys:ckfinder input="path${projectLicense.id}" type="files" uploadPath="/charge/projectLicense" selectMultiple="false" readonly="true"/>
				</td>
				<td>
					<fmt:formatDate value="${projectLicense.documentDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${projectLicense.upArea}
				</td>
				<td>
					${projectLicense.downArea}
				</td>
				<td>
					<fmt:formatDate value="${projectLicense.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${projectLicense.remarks}
				</td>
				<shiro:hasPermission name="charge:charge:edit"><td>
    				<a href="${ctx}/charge/projectLicense/form?id=${projectLicense.id}">修改</a>
					<a href="${ctx}/charge/projectLicense/delete?id=${projectLicense.id}" onclick="return confirmx('确认要删除该工程许可证吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>