<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>征收管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {			
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/report/report/taxProtectExport");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});	
			
			$("#btnSubmit").click(function(){
				$("#searchForm").attr("action","${ctx}/report/report/taxProtect");
				$("#searchForm").submit();
			});				
		});
	</script>
</head>
<body>
    
	<legend>税收保障</legend>
	<form:form id="searchForm" modelAttribute="reportParam" action="${ctx}/report/report/taxProtect" method="post" class="breadcrumb form-search">			
		<ul class="ul-form">	
			<li>		
			    <label>月份 ：</label>
			    <input name="reportType" type="hidden" value="ChargeGist">
				<input name="dateFrom" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportParam.dateFrom}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>			
			</li>			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
			<input id="btnExport" class="btn btn-primary" type="button" value=" 导 出 "/>
			</li>
			<li class="clearfix"></li>
		</ul>		
	</form:form>	
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>项目名称</th>
				<th>项目位置</th>
				<th>建设单位</th>
				<th>所属级次</th>
				<th>建筑面积</th>
				<th>规划许可证</th>
				<th>施工许可证编号</th>
				<th>征收金额</th>
				<th>征收金额的计算依据</th>
				<th>缴款时间</th>									
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="entity" varStatus="status">
			<tr>
				<td>${ status.index + 1}</td>
				<td>
					${entity.project.prjName}
				</td>
				<td>
					${entity.project.prjAddress}
				</td>
				<td>
					${entity.project.buildCorpName}
				</td>
				<td>
					${entity.areaLevel}
				</td>
				<td style="text-align:right">
					${entity.constructArea}
				</td>
				<td>
					${entity.projectLicense}
				</td>	
				<td>
					${entity.constructLicenseNo}
				</td>
				<td style="text-align:right">
					${entity.chargeMoney}
				</td>
				<td>
					${entity.chargeGist}
				</td>										
				<td>
					<fmt:formatDate value="${entity.maxPayDate}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>