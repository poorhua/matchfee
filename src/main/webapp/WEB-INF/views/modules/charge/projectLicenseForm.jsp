<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规划许可证管理</title>
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
		
		function areaOnkeyup(obj){
			obj.value=obj.value.replace(/[^-\d.]/g,'');
			var upArea = 0;
			if($('#upArea').val() != ''){
				upArea = parseFloat($('#upArea').val());
			}
			var downArea = 0;
			if($('#downArea').val() != ''){
				downArea = parseFloat($('#downArea').val());
			}
			$('#totalAreaDisplay').text((upArea + downArea).toFixed(2));
		}
		
	    function onSubmit(){
	    	
	    	var path = $("#path").val();
	    	if(path == ""){
	    		alert("请上传建设工程规划许可证扫描件！");
	    		return;
	    	}

			$("#inputForm").submit();	
			top.$('.jbox-body .jbox-icon').css('top','55px');
	    }		
	</script>
</head>
<body>
    <sys:message content="${message}"/>	
    <legend>建设工程规划许可证</legend>
	<matchfee:chargeView charge="${charge}"></matchfee:chargeView><br/>
	<form:form id="inputForm" modelAttribute="projectLicense" action="${ctx}/charge/projectLicense/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" id="charge.id" name="charge.id" value="${charge.id}">
		<input type="hidden" id="project.prjNum" name="project.prjNum" value="${charge.project.prjNum}">
			
		<div class="control-group">
			<label class="control-label">文件：</label>
			<div class="controls">
				<form:hidden id="path" path="path" htmlEscape="false" maxlength="256" class="input-xlarge required"/>
				<sys:ckfinder input="path" type="files" uploadPath="/配套费/规划许可证" selectMultiple="false"/>
				<span class="help-inline"><font color="red">*</font> &nbsp;&nbsp;&nbsp;如果是多页，请做成一个pdf文件 。</span>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> &nbsp;&nbsp;&nbsp;请按照规划许可证上的建设项目名称填写。</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规划许可证编号：</label>
			<div class="controls">
				<form:input path="documentNo" htmlEscape="false" maxlength="64" class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发证日期：</label>
			<div class="controls">
				<input name="documentDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${projectLicense.documentDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地上面积（平米）：</label>
			<div class="controls">
				<form:input path="upArea" htmlEscape="false" class="input-xlarge required"  
				 onkeyup="areaOnkeyup(this)"
                 onafterpaste="this.value=this.value.replace(/[^-\d.]/g,'')"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地下面积（平米）：</label>
			<div class="controls">
				<form:input path="downArea" htmlEscape="false" class="input-xlarge required"  
				 onkeyup="areaOnkeyup(this)"
                 onafterpaste="this.value=this.value.replace(/[^-\d.]/g,'')"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总面积（平米）：</label>
			<div id ="totalAreaDisplay" class="controls">
			   ${projectLicense.totalAreaDisplay}
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="512" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="charge:charge:edit"><input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="onSubmit()"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>