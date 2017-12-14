<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>	

<%@ attribute name="charge" type="org.wxjs.matchfee.modules.charge.entity.Charge" required="true"%>

<legend>配套费征收</legend>		
<form:form id="chargeForm" class="form-horizontal">
    <input id="id" name="id" type="hidden" value="${charge.id}"/>
	<fieldset>
		<div style="text-align:left"><strong>申报信息</strong></div>
		<table class="table-form">

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
				<td class="tit">状态：</td><td>${fns:getDictLabel(charge.status, 'charge_status', '')}</td>
				<td class="tit">结算金额（元）：</td>
				<td>
				    ${charge.calMoney}
				</td>				
			</tr>	
			<c:if test="${charge.status gt '30' }">
			<tr>
				<td class="tit">缴费金额（元）：</td><td>${charge.payMoney}</td>
				<td class="tit">待清算金额（元）：</td>
				<td> ${charge.moneyGapDisplay}
				</td>				
			</tr>	
			</c:if> 			      
			
		</table>
	</fieldset>		
</form:form>
