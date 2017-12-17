<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>抵扣项文件管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<legend>设计院证明</legend>
	<matchfee:projectInfoView/><br>
	<form:form id="inputForm" modelAttribute="deductionDoc" action="${ctx}/charge/deductionDoc/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="prjNum"/>
		<input type="hidden" id="charge.id" name="charge.id" value="${chargeId}">
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">文件：</label>
			<div class="controls">
				<form:hidden id="path" path="path" htmlEscape="false" maxlength="256" class="input-xlarge"/>
				<sys:ckfinder input="path" type="files" uploadPath="/配套费/设计院证明" selectMultiple="false"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件编号：</label>
			<div class="controls">
				<form:input path="documentNo" htmlEscape="false" maxlength="64" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文档日期：</label>
			<div class="controls">
				<input name="documentDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${deductionDoc.documentDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="64" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="charge:charge:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>