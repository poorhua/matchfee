<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目抵扣项管理</title>
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
		
		function onSubmit(){
			var documentNo = $("#documentNo").val();
			
			if(documentNo == "" || documentNo == "无"){
				$("#inputForm").submit();
				return;
			}
			
			var aj = $.ajax( {    
			    url:'${ctx}/charge/projectDeduction/documentNoExists?documentNo='+documentNo,   
			    data:{
			    	//documentNo:$("#documentNo").val()
			    },    
			    type:'post',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	
			    	if(data == true){
						top.$.jBox.confirm("该文件编号已使用过，注意不要重复抵扣，确认要保存吗？","系统提示",function(v,h,f){
							if(v=="ok"){
								$("#documentNo").val(documentNo+"_duplicate");
								
								$("#inputForm").submit();	
							}
						},{buttonsFocus:1});				    		
			    	}else{
			    		$("#inputForm").submit();	
			    	}
		        
			     },    
			     error : function() {   
			          alert("获取数据异常！");    
			     }    
			});	
			
		} 
	</script>
</head>
<body>
    <legend>其他减项</legend>
	<matchfee:chargeView charge="${charge}"></matchfee:chargeView><br/>
	<form:form id="inputForm" modelAttribute="projectDeduction" action="${ctx}/charge/projectDeduction/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" id="charge.id" name="charge.id" value="${charge.id}">
		<input type="hidden" id="project.prjNum" name="project.prjNum" value="${charge.project.prjNum}">
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">文件：</label>
			<div class="controls">
				<form:hidden id="path" path="path" htmlEscape="false" maxlength="256" class="input-xlarge"/>
				<sys:ckfinder input="path" type="files" uploadPath="/配套费/减项证明" selectMultiple="false"/>
				<span class="help-inline"><font color="red">*</font> &nbsp;&nbsp;&nbsp;如果是多页，请做成一个pdf文件 。</span>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">文件编号：</label>
			<div class="controls">
			<form:input path="documentNo" htmlEscape="false" maxlength="64" class="input-xlarge" readonly="${not empty projectDeduction.id}"/>
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
			<label class="control-label">文档日期：</label>
			<div class="controls">
				<input name="documentDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${projectDeduction.documentDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
		<!--  
		<div class="control-group">
			<label class="control-label">抵扣方式：</label>
			<div class="controls">
				<form:select path="deductionType" class="input-large required">
					<form:options items="${fns:getDictList('deduction_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		-->
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