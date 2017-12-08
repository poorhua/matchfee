<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>	

<form:form class="form-horizontal">
	<fieldset>
		<strong>项目信息</strong>
		<table class="table-form">
			<tr>
				<td class="tit">项目编号：</td><td>${sessionScope.project.prjNum}</td>
				<td class="tit">项目名称：</td><td>${sessionScope.project.prjName}</td>
			</tr>
			<tr>
				<td class="tit">建设单位代码：</td><td>${sessionScope.project.buildCorpCode}</td>
				<td class="tit">建设单位名称：</td><td>${sessionScope.project.buildCorpName}</td>
			</tr>
			<tr>
				<td class="tit">项目地址：</td>
				<td colspan="3">
					${sessionScope.project.prjAddress}
				</td>
			</tr>
		</table>
	</fieldset>		
</form:form>

