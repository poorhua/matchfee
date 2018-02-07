<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>	

<%@ attribute name="charge" type="org.wxjs.matchfee.modules.charge.entity.Charge" required="true"%>

	<script type="text/javascript">
	    
	    function showSettlementList(){
	    	
	    	window.open('${ctx}/charge/charge/showSettlementList?id=${charge.id}','_blank')
	    	
	    }
	</script>

<form:form id="chargeForm" class="form-horizontal">
    <input id="id" name="id" type="hidden" value="${charge.id}"/>
	<fieldset>
		<div style="text-align:left"><strong>申报信息</strong></div>
		<table class="table-form">
		  <tr>
		    <td width="80%">
		      <table width="100%">
				<tr>
					<td class="tit">申报代码：</td><td>${charge.id}</td>
					<td class="tit">申报日期：</td><td><fmt:formatDate value="${charge.reportDate}" pattern="yyyy-MM-dd"/></td>
				</tr>		
				<tr>
					<td class="tit">项目编号：</td><td>${charge.project.prjNum}</td>
					<td class="tit">项目名称：</td><td>${charge.project.prjName}</td>
				</tr>
				<tr>
					<td class="tit">建设单位代码：</td><td>${charge.project.buildCorpCode}</td>
					<td class="tit">建设单位名称：</td><td>${charge.project.buildCorpName}</td>
				</tr>
				<tr>
					<td class="tit">联系人：</td><td>${charge.project.contact}</td>
					<td class="tit">联系电话：</td><td>${charge.project.mobile}</td>
				</tr>				
				<tr>
					<td class="tit">状态：</td><td>${charge.statusLabel}</td>
					<td class="tit">结算金额（元）：</td>
					<td style="text-align:right">
					    <fmt:formatNumber value="${charge.calMoney}" pattern="#,##0.00"/>
					</td>				
				</tr>	
				<c:if test="${charge.status gt '30' }">
				<tr>
					<td class="tit">缴费金额（元）：</td>
					<td style="text-align:right"><fmt:formatNumber value="${charge.payMoney}" pattern="#,##0.00"/></td>
					<td class="tit">待清算金额（元）：</td>
					<td style="text-align:right"><fmt:formatNumber value="${charge.moneyGapDisplay}" pattern="#,##0.00"/>
					</td>				
				</tr>	
				</c:if> 		      
		      </table>
		    </td>
		    <td width="20%" align="center">	   				   				   				   					   					   			
		   		<input id="btnSettle" class="btn btn-primary" type="button" value="结算清单" onclick="showSettlementList()"/>    
		    </td>
		  </tr>
			
		</table>
	</fieldset>		
</form:form>
