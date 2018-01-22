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
			//$("#searchForm").submit();
        	return false;
        }	
		
	    function querySubmit(){
	    	
	    	var c1 = document.getElementById("project.prjNum").value;
	    	var c2 = document.getElementById("project.prjName").value;
	    	var c3 = document.getElementById("project.buildCorpCode").value;
	    	var c4 = document.getElementById("project.buildCorpName").value;
	    	var c5 = document.getElementById("project.projectLicense").value;
	    	
	    	if((c1+c2+c3+c4+c5)==""){
	    		alert("请输入查询条件！");
	    		return;
	    	}else{
	    		$("#searchForm").submit();
	    	}				
	    }		
	</script>
</head>
<body>
	<legend>征收记录查询</legend>
	<form:form id="searchForm" modelAttribute="charge" action="${ctx}/report/report/search4CommonUser" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>	
		<c:if test="${fns:getUser().isZfUser}">
			<ul class="ul-form">
				<li><label>项目编号：</label>
				    <form:input path="project.prjNum" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				</li>
				<li><label>项目名称：</label>
					<form:input path="project.prjName" htmlEscape="false" maxlength="128" class="input-xlarge required"/>			
				</li>				
			</ul>	
			<ul class="ul-form">
				<li><label>建设单位代码：</label>
				    <form:input path="project.buildCorpCode" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				</li>
				<li><label>建设单位名称：</label>
					<form:input path="project.buildCorpName" htmlEscape="false" maxlength="128" class="input-xlarge required"/>				
				</li>				
			</ul>	
			<ul class="ul-form">
				<li><label>规划许可证编号：</label>
				    <form:input path="project.projectLicense" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				</li>
				<li><label>状态 ：</label>
				<form:select path="status" class="input-large required">
				    <form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('charge_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>			
				</li>				
			</ul>				
		</c:if>			
		<ul class="ul-form">	
			<li>		
			    <label>申报日期 ：</label>
				<input name="dateFrom" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${charge.dateFrom}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<label>到：</label>
				<input name="dateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${charge.dateTo}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>				
			</li>			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="querySubmit()"/>
			</li>
			<li class="clearfix"></li>
		</ul>		
	</form:form>	
	
	<c:if test="${fn:length(page.list) > 10}">
	    <div style="margin: 10px 0 10px 20px;font-size: 16px;font-weight:bold;">查询结果超过10条，请缩小查询范围。</div>
	</c:if>
	<c:if test="${fn:length(page.list) <= 10}">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目代码</th>
				<th>项目名称</th>
				<th>项目地址</th>
				<th>申报人</th>
				<th>申报单位</th>
				<th>申报时间</th>
				<th>测算金额</th>
				<th>付款金额</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="charge">
			<tr>
				<td>
					${charge.project.prjNum}
				</td>
				<td>
					${charge.project.prjName}
				</td>
				<td>
					${charge.project.prjAddress}
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
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</c:if>
</body>
</html>