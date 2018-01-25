<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
	    function toNewPage(){
	       window.location.replace("${ctx}/charge/project/form");
	    }
	</script>
</head>
<body>
    <legend>指定项目</legend>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/charge/project/">项目信息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="project" action="${ctx}/charge/project/listLocalAndRemote" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目编号：</label>
				<form:input path="prjNum" htmlEscape="false" maxlength="32" class="input-large"/>
			</li>
			<li><label>项目名称：</label>
				<form:input path="prjName" htmlEscape="false" maxlength="128" class="input-large"/>
			</li>
		</ul>
		<ul class="ul-form">
			<li><label>建设单位代码：</label>
				<form:input path="buildCorpCode" htmlEscape="false" maxlength="32" class="input-large"/>
			</li>
			<li><label>建设单位名称：</label>
				<form:input path="buildCorpName" htmlEscape="false" maxlength="128" class="input-large"/>
			</li>
		</ul>				
		<ul class="ul-form">
			<li><label>项目地址：</label>
				<form:input path="prjAddress" htmlEscape="false" maxlength="128" class="input-large"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<c:if test="${fns:getUser().isShy}">
			<input id="btnAdd" class="btn btn-primary" type="button" value="手动添加" onclick="toNewPage()"/>
			</c:if>
			</li>
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
				<shiro:hasPermission name="charge:charge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="project">
			<tr>
				<td>${project.prjNum}</td>
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
				<shiro:hasPermission name="charge:charge:edit"><td>
    				<a href="${ctx}/charge/project/form?id=${project.id}&prjNum=${project.prjNum}"><strong> 申报 </strong></a>
				</td></shiro:hasPermission>			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</body>
</html>