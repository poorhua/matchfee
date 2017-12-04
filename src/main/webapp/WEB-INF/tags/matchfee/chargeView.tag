<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>	

<%@ attribute name="charge" type="org.wxjs.matchfee.modules.charge.entity.Charge" required="true"%>
	
<form:form class="form-horizontal">
	<fieldset>
		<legend>申报信息</legend>
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
				<td class="tit">项目地址：</td>
				<td colspan="3">
					${charge.project.prjAddress}
				</td>
			</tr>			
		</table>
	</fieldset>		
</form:form>
