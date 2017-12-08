<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>	

<%@ attribute name="charge" type="org.wxjs.matchfee.modules.charge.entity.Charge" required="true"%>

	<script type="text/javascript">
		
	    function reportSubmit(){
			$("#chargeForm").attr("action","${ctx}/charge/charge/reportSubmit");
			$("#chargeForm").submit();		       
	    }
	    
	    function calculatePass(){
			$("#chargeForm").attr("action","${ctx}/charge/charge/calculatePass");
			$("#chargeForm").submit();		       
	    }	
	    
	    function calculateReject(){
	    	
	    	var memo = $("#calMemo").val();
	    	if(memo.equals("")){
	    		alert("退回操作，请填写审批意见！");
	    		return;
	    	}
	    	
			top.$.jBox.confirm("确认要退回吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#chargeForm").attr("action","${ctx}/charge/charge/calculateReject");
					$("#chargeForm").submit();	
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
	    }
	    
	    function approvePass(){
			$("#chargeForm").attr("action","${ctx}/charge/charge/approvePass");
			$("#chargeForm").submit();
	    }	
	    
	    function approveReject(){
	    	var memo = $("#approveMemo").val();
	    	if(memo.equals("")){
	    		alert("退回操作，请填写审批意见！");
	    		return;
	    	}	    	
			top.$.jBox.confirm("确认要退回吗？","系统提示",function(v,h,f){
				if(v=="ok"){	    	
					$("#chargeForm").attr("action","${ctx}/charge/charge/approveReject");
					$("#chargeForm").submit();	
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');

	    }
	    
	    function confirmSubmit(){

			top.$.jBox.confirm("确定要确认缴费吗？","系统提示",function(v,h,f){
				if(v=="ok"){	    	
					$("#chargeForm").attr("action","${ctx}/charge/charge/confirm");
					$("#chargeForm").submit();	
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
	    	
	    }
	    
	    function exportPdf(){

			$("#chargeForm").attr("action","${ctx}/charge/charge/exportSettlementList");
			$("#chargeForm").submit();
	    	
	    }
	</script>
	
<form:form id="chargeForm" class="form-horizontal">
    <input id="id" name="id" type="hidden" value="${charge.id}"/>
	<fieldset>
		<strong>申报信息</strong>
		<table class="table-form">
		  <tr>
		    <td width="85%">
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
					<td class="tit">项目地址：</td>
					<td>
						${charge.project.prjAddress}
					</td>
					<td class="tit">状态：</td><td>${fns:getDictLabel(charge.status, 'charge_status', '')}</td>
				</tr>		      
		      </table>
		    </td>
		    <td width="15%" align="center">
			    <shiro:hasPermission name="charge:charge:edit">
		          <c:choose>
		   			<c:when test="${charge.status eq '00' || charge.status eq '05'}">
		   				<input id="btnYes" class="btn btn-primary" type="button" value="提交" onclick="reportSubmit()"/>
		   			</c:when>
		   			<c:when test="${charge.status eq '10'}">
		   				<table>
		   				  <tr>
		   				    <td>审批意见：<br>
		   				      <textarea id="calMemo" name="calMemo" rows="3" cols="20"></textarea>    
		   				    </td>
		   				  </tr>
		   				  <tr>
		   				    <td align="center">
		   				      <input id="btnYes" class="btn btn-primary" type="button" value=" 批 准 "  onclick="calculatePass()"/>
		   				      <input id="btnNo" class="btn btn-warning" type="button" value=" 退 回 " onclick="calculateReject()"/>
		   				    </td>
		   				  </tr>
		   				</table>
		   			</c:when>	
		   			<c:when test="${charge.status eq '20'}">
		   				<table>
		   				  <tr>
		   				    <td>审批意见：<br>
		   				      <textarea id="approveMemo" name="approveMemo" rows="3" cols="20"></textarea>    
		   				    </td>
		   				  </tr>
		   				  <tr>
		   				    <td align="center">
		   				      <input id="btnYes" class="btn btn-primary" type="button" value=" 批 准 "  onclick="approvePass()"/>
		   				      <input id="btnNo" class="btn btn-warning" type="button" value=" 退 回 " onclick="approveReject()"/>
		   				    </td>
		   				  </tr>
		   				</table>
		   			</c:when>	
		   			<c:when test="${charge.status eq '30'}">
		   				<input id="btnYes" class="btn btn-primary" type="button" value="确认缴费" onclick="confirmSubmit()"/>
		   			</c:when>	
		   			<c:when test="${charge.status eq '40'}">
		   				<input id="btnYes" class="btn btn-primary" type="button" value="导出结算清单" onclick="exportPdf()"/>
		   			</c:when>		   				   				   				   					   					   			
		   			<c:otherwise>
		   			
		   			</c:otherwise>
		   		  </c:choose>
			    </shiro:hasPermission>	    
		    </td>
		  </tr>
			
		</table>
	</fieldset>		
</form:form>
