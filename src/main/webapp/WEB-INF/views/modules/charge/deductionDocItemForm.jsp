<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>抵扣项目管理</title>
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
		
		function itemChange(){
			var a = document.getElementById("item.id");
			var itemId = a.options[a.selectedIndex].value; 
			var prjNum = ${deductionDocItem.doc.prjNum};
			
	        $.ajax({     
	            //要用post方式      
	            type: "Post",     
	            //方法所在页面和方法名      
	            url: "${ctx}/charge/deductionDocItem/deductionSummary?itemId="+itemId+"&prjNum="+prjNum,     
	            contentType: "application/json; charset=utf-8",     
	            dataType: "json",     
	            success: function(data) {
	            	var areaInOpinionBook = data.areaInOpinionBook;
	            	var areaDeducted = data.areaDeducted;
	            	var areaRemained = data.areaRemained +"平米"
	            	if(areaInOpinionBook == ""){
	            		areaInOpinionBook = "无"
	            	}else{
	            		areaInOpinionBook = areaInOpinionBook +"平米";
	            	}
	            	if(areaDeducted == ""){
	            		areaDeducted = "0平米"
	            	}else{
	            		areaDeducted = areaDeducted +"平米";
	            	}
	            	
	            	//alert("areaInOpinionBook: "+areaInOpinionBook+", areaDeducted: "+areaDeducted);
	            	
	                document.getElementById("deductionItemHint").innerHTML="意见书面积："+areaInOpinionBook
	                +"，已抵扣面积："+areaDeducted  +"，剩余面积："+areaRemained;
	            	//$("#deductionItemHint").innerHTML="意见书面积："+areaInOpinionBook+"，已抵扣面积："+areaDeducted;
	            },     
	            error: function(err) {     
	                alert(err);     
	            }     
	        });
		}
	</script>
</head>
<body>
    <sys:message content="${message}"/>	
	<legend>抵扣项</legend>
	<matchfee:deductionDocView deductionDoc="${deductionDocItem.doc}"></matchfee:deductionDocView><br/>
	<form:form id="inputForm" modelAttribute="deductionDocItem" action="${ctx}/charge/deductionDocItem/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
			
		<input type="hidden" id="doc.id" name="doc.id" value="${deductionDocItem.doc.id}" />
		<input type="hidden" id="doc.charge.id" name="doc.charge.id" value="${deductionDocItem.doc.charge.id}" />
		<input type="hidden" id="doc.prjNum" name="doc.prjNum" value="${deductionDocItem.doc.prjNum}" />
		<div class="control-group">
			<label class="control-label">抵扣项：</label>
			<div class="controls">
				
				<form:select path="item.id" class="input-large required" onchange="itemChange()">
				    <form:option value="" label="请选择"/>
					<form:options items="${fns:getDeductionItems()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>				
				
				<span class="help-inline"><font color="red">*</font> </span>
				<div id="deductionItemHint" name="deductionItemHint" style="color:#00F">${deductionItemHint}</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">面积（平米）：</label>
			<div class="controls">
				<form:input path="area" htmlEscape="false" class="input-xlarge required"
				 onkeyup="this.value=this.value.replace(/[^\d.]/g,'');$('#money').val(Math.round(this.value*${fns:getConfig('matchfee.basis')}*100)/100)"
                 onafterpaste="this.value=this.value.replace(/[^\d.]/g,'')" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">金额（元）：</label>
			<div class="controls">
				<form:input path="money" htmlEscape="false" class="input-xlarge required"
				 onkeyup="this.value=this.value.replace(/[^\d.]/g,'');$('#area').val(Math.round(this.value/${fns:getConfig('matchfee.basis')}*100)/100)"
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