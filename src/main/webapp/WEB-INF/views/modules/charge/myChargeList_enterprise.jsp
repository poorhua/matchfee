<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>征收管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {			
			
		});
		
	</script>
</head>
<body>
	<legend>征收列表</legend>	
	
	<div style="margin:10px 60px 10px 0;width='100%'">
	   <div align="right"><input id="btnAdd" class="btn btn-primary" type="button" value="新申报" onclick="location.href='${ctx}/charge/charge/toProjectList'"/></div>
	</div>	
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>代码</th>
				<th>项目编号</th>
				<th>项目名称</th>
				<th>申报人</th>
				<th>申报单位</th>
				<th>申报时间</th>
				<th>结算金额</th>
				<th>缴费金额</th>
				<th>状态</th>
				<shiro:hasPermission name="charge:charge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="charge">
			<tr>
				<td>
					${charge.id}
				</td>
				<td>
					${charge.project.prjNum}
				</td>
				<td>
					${charge.project.prjName}
				</td>
				<td>
					${charge.reportStaff.name}
				</td>
				<td>
					${charge.reportEntity}
				</td>
				<td>
					<fmt:formatDate value="${charge.reportDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${charge.calMoney}
				</td>
				<td>
					${charge.payMoney}
				</td>
				<td>
					${charge.statusLabel}
				</td>
				<shiro:hasPermission name="charge:charge:edit"><td>
    				<a href="${ctx}/charge/charge/opinionBookTab?id=${charge.id}">进入</a>
    				<c:if test="${charge.status eq '00'}">
    				  <a href="${ctx}/charge/charge/delete?id=${charge.id}" onclick="return confirmx('确认要删除该征收吗？', this.href)">删除</a>
    				</c:if>
					<a href="${ctx}/charge/charge/showSettlementList?id=${charge.id}" target="_blank">结算清单</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>