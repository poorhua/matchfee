<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ taglib prefix="report" tagdir="/WEB-INF/tags/report" %>

<html>
<head>
	<title>征收统计报表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {		
		});
	</script>
</head>
<body>

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="100%" height="30" class="title" align="left"><strong>${reportData.title}</strong><br>
    </td> 
  </tr>
  <tr>
    <td>
	<form:form id="searchForm" modelAttribute="reportParam" action="${ctx}/report/report/query" method="post" class="breadcrumb form-search">
        <form:hidden path="reportType"/>
		<ul class="ul-form">
			<li>				
			    <label>月份 ：</label>
				<input name="dateFrom" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportParam.dateFrom}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
				<label>到：</label>
				<input name="dateTo" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportParam.dateTo}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
			</li>			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value=" 查 询 "/>
			
			</li>
			<li class="clearfix"></li>
		</ul>		
	</form:form>    
    
    </td>
  </tr>  
  <tr>
    <td>
	<c:choose>
	  <c:when test="${reportData.chartType=='pieReport'}">
	    <report:pieChartAndTable gridColModel="${reportData.tableData.jsonColModel}" gridData="${reportData.tableData.jsonData}" 
	    chartSeries="${reportData.chartData.jsonSeries}" chartTitle="${reportData.chartData.title}">
	    </report:pieChartAndTable>
	  </c:when>
	  <c:otherwise>
	    <report:columnChartAndTable gridColModel="${reportData.tableData.jsonColModel}" chartSeries="${reportData.chartData.jsonSeries}" 
	    chartyTitle="${reportData.chartData.yTitle}" chartCategories="${reportData.chartData.jsonCategories}" gridData="${reportData.tableData.jsonData}" 
	    chartTitle="${reportData.chartData.title}" chartSubTitle="${reportData.chartData.subTitle}" chartyAxisUnit="${reportData.chartData.yAxisUnit}">	
	    </report:columnChartAndTable>			  
	  </c:otherwise>
	</c:choose>
		
    </td>
  </tr> 
</table>


</body>
</html>