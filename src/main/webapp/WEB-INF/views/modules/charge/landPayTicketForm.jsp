<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>国土已缴费管理</title>
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
			var id = $("#inputForm").find("#id").val();
			
			var ticketNo = $("#ticketNo").val();
			
			var aj = $.ajax( {    
			    url:'${ctx}/charge/landPayTicket/ticketNoExists',   
			    data:{
			    	id:id,
			    	ticketNo:ticketNo				    	
			    },    
			    type:'post',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	
			    	if(data == true){
						top.$.jBox.confirm("该国土缴费凭证已使用过，注意不要重复低扣，确认要保存吗？","系统提示",function(v,h,f){
							if(v=="ok"){
								
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
    <sys:message content="${message}"/>
	<legend>国土缴费凭证</legend>
	<matchfee:projectInfoView/><br>
	<form:form id="inputForm" modelAttribute="landPayTicket" action="${ctx}/charge/landPayTicket/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="prjNum"/>
		<div class="control-group">
			<label class="control-label">票据：</label>
			<div class="controls">
				<form:hidden id="path" path="path" htmlEscape="false" maxlength="256" class="input-xlarge"/>
				<sys:ckfinder input="path" type="files" uploadPath="/配套费/国土缴费凭证" selectMultiple="false"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代收费协议：</label>
			<div class="controls">
				<form:hidden id="agencyAgreement" path="agencyAgreement" htmlEscape="false" maxlength="256" class="input-xlarge"/>
				<sys:ckfinder input="agencyAgreement" type="files" uploadPath="/配套费/国土缴费凭证" selectMultiple="false"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">票据号：</label>
			<div class="controls">
			    <!--  
				<form:input path="ticketNo" htmlEscape="false" maxlength="64" class="input-xlarge required" readonly="${not empty landPayTicket.id}"/>
				-->
				<form:input path="ticketNo" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
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
			<label class="control-label">缴费日期：</label>
			<div class="controls">
				<input name="payDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${landPayTicket.payDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
			<shiro:hasPermission name="charge:charge:edit"><input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="onSubmit()"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>