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
	<form:form id="searchForm" modelAttribute="charge" action="${ctx}/charge/charge/settlementlist" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
		    <li>
			    <label>缴费日期 ：</label>
				<input name="dateFrom" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${charge.dateFrom}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<label>到：</label>
				<input name="dateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${charge.dateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>	
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
				<th>缴费时间</th>
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
					<fmt:formatDate value="${charge.reportDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td style="text-align:right">
					<fmt:formatNumber value="${charge.calMoney}" pattern="#,##0.00"/>
				</td>
				<td style="text-align:right">
					<fmt:formatNumber value="${charge.payMoney}" pattern="#,##0.00"/>
				</td>
				<td>
					<fmt:formatDate value="${charge.maxPayDate}" pattern="yyyy-MM-dd"/>
				</td>				
				<td>
					${charge.statusLabel}
				</td>
				<shiro:hasPermission name="charge:charge:edit"><td>
				   <a href="${ctx}/charge/charge/showSettlementList?id=${charge.id}" target="_blank">结算清单</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>