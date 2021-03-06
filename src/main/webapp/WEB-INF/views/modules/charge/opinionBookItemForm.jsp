<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>条件意见书项目管理</title>
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
    <legend>抵扣项</legend>
	<matchfee:opinionBookView opinionBook="${opinionBookItem.doc}"></matchfee:opinionBookView><br/>
	<form:form id="inputForm" modelAttribute="opinionBookItem" action="${ctx}/charge/opinionBookItem/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<form:hidden path="doc.id" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
		<div class="control-group">
			<label class="control-label">抵扣项：</label>
			<div class="controls">		
				
				<form:select path="item.id" class="input-large required" disabled="${not empty opinionBookItem.id}">
				    <form:option value="" label="请选择"/>
					<form:options items="${fns:getDeductionItems()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>						
				
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">面积（平米）：</label>
			<div class="controls">
				<form:input path="area" htmlEscape="false" class="input-xlarge required"
				 onkeyup="this.value=this.value.replace(/[^\d.]/g,'');$('#money').val(Math.round(this.value*105*100)/100)"
                 onafterpaste="this.value=this.value.replace(/[^\d.]/g,'')" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">金额（元）：</label>
			<div class="controls">
				<form:input path="money" htmlEscape="false" class="input-xlarge required"
				 onkeyup="this.value=this.value.replace(/[^\d.]/g,'');$('#area').val(Math.round(this.value/105*100)/100)"
				 onafterpaste="this.value=this.value.replace(/[^\d.]/g,'')" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="512" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="charge:charge:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>