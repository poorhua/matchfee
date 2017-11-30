<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息管理</title>
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
		
	    function toNewPage(){
	       window.location.replace("${ctx}/charge/project/form");
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/charge/project/">项目信息列表</a></li>
		<shiro:hasPermission name="charge:project:edit"><li><a href="${ctx}/charge/project/form">项目信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="project" action="${ctx}/charge/project/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目编号：</label>
				<form:input path="prjNum" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>项目名称：</label>
				<form:input path="prjName" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>建设单位代码：</label>
				<form:input path="buildCorpCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>建设单位名称：</label>
				<form:input path="buildCorpName" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>项目地址：</label>
				<form:input path="prjAddress" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id="btnAdd" class="btn btn-primary" type="button" value="手动添加" onclick="toNewPage()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目编号</th>
				<th>项目名称</th>
				<th>建设单位代码</th>
				<th>建设单位名称</th>
				<th>项目地址</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="charge:project:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="project">
			<tr>
				<td><a href="${ctx}/charge/project/form?id=${project.id}">
					${project.prjNum}
				</a></td>
				<td>
					${project.prjName}
				</td>
				<td>
					${project.buildCorpCode}
				</td>				
				<td>
					${project.buildCorpName}
				</td>

				<td>
					${project.prjAddress}
				</td>
				<td>
					<fmt:formatDate value="${project.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${project.remarks}
				</td>
				<shiro:hasPermission name="charge:project:edit"><td>
    				<a href="${ctx}/charge/project/form?id=${project.id}"><strong> 修改 </strong></a>
    				<a href="${ctx}/charge/opinionBook/tab?prjNum=${project.prjNum}"><strong> 申报 </strong></a>
				</td></shiro:hasPermission>			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>