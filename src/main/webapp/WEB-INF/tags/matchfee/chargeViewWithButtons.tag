<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>	

<%@ attribute name="charge" type="org.wxjs.matchfee.modules.charge.entity.Charge" required="true"%>

	<script type="text/javascript">
		
	    function reportSubmit(){
	       
			top.$.jBox.confirm("确认要提交吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#chargeForm").attr("action","${ctx}/charge/charge/reportSubmit");
					$("#chargeForm").submit();	
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');				
	    }
	    
	    function calculatePass(){
		
			top.$.jBox.confirm("确认要通过吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#chargeForm").attr("action","${ctx}/charge/charge/calculatePass");
					$("#chargeForm").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');				
	    }	
	    
	    function calculateReject(){
	    	
	    	var memo = $("#calMemo").val();
	    	if(memo == ""){
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
			
			top.$.jBox.confirm("确认要批准吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#chargeForm").attr("action","${ctx}/charge/charge/approvePass");
					$("#chargeForm").submit();	
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');			
	    }	
	    
	    function approveReject(){
	    	var memo = $("#approveMemo").val();
	    	if(memo == ""){
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
	    
	    function uploadPass(){
	    	
	    	var isEmptyList = ${empty charge.payTicketList};
	    	
	    	
	    	if(isEmptyList){
	    		alert("请先上传缴费凭证！");
	    		return;
	    	}
	    	
	    	var hint = "确认要提交吗？";	    	
			
			top.$.jBox.confirm(hint,"系统提示",function(v,h,f){
				if(v=="ok"){
					$("#chargeForm").attr("action","${ctx}/charge/charge/uploadPass");
					$("#chargeForm").submit();	
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');			
	    }	
	    
	    function uploadReject(){
	    	var memo = $("#approveMemo").val();
	    	if(memo == ""){
	    		alert("退回操作，请填写审批意见！");
	    		return;
	    	}	    	
			top.$.jBox.confirm("确认要退回吗？","系统提示",function(v,h,f){
				if(v=="ok"){	    	
					$("#chargeForm").attr("action","${ctx}/charge/charge/uploadReject");
					$("#chargeForm").submit();	
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');

	    }	    
	    
	    function confirmPass(){
	    	
	    	var isEmptyList = ${empty charge.payTicketList};
	    	
	    	
	    	if(isEmptyList){
	    		alert("请先上传缴费凭证！");
	    		return;
	    	}
	    	
	    	var hint = "确定要确认缴费吗？";

			top.$.jBox.confirm(hint,"系统提示",function(v,h,f){
				if(v=="ok"){	    	
					$("#chargeForm").attr("action","${ctx}/charge/charge/confirmPass");
					$("#chargeForm").submit();	
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
	    	
	    }
	    
	    function confirmReject(){
	    	var memo = $("#approveMemo").val();
	    	if(memo == ""){
	    		alert("退回操作，请填写审批意见！");
	    		return;
	    	}	    	
			top.$.jBox.confirm("确认要退回吗？","系统提示",function(v,h,f){
				if(v=="ok"){	    	
					$("#chargeForm").attr("action","${ctx}/charge/charge/confirmReject");
					$("#chargeForm").submit();	
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');

	    }
	    
	    function showSettlementList(){
	    	
	    	window.open('${ctx}/charge/charge/showSettlementList?id=${charge.id}','_blank')
	    	
	    }
	</script>

<form:form id="chargeForm" class="form-horizontal">
    <input id="id" name="id" type="hidden" value="${charge.id}"/>
	<fieldset>
		<div style="text-align:left"><strong>申报信息</strong></div>
		<table class="table-form">
		  <tr>
		    <td width="70%">
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
					<td class="tit">联系人：</td><td>${charge.project.contact}</td>
					<td class="tit">联系电话：</td><td>${charge.project.mobile}</td>
				</tr>				
				<tr>
					<td class="tit">状态：</td><td>${charge.statusLabel}</td>
					<td class="tit">结算金额（元）：</td>
					<td style="text-align:right">
					    <fmt:formatNumber value="${charge.calMoney}" pattern="#,###.00"/>
					</td>				
				</tr>	
				<c:if test="${charge.status gt '30' }">
				<tr>
					<td class="tit">缴费金额（元）：</td>
					<td style="text-align:right"><fmt:formatNumber value="${charge.payMoney}" pattern="#,###.00"/></td>
					<td class="tit">待清算金额（元）：</td>
					<td style="text-align:right"><fmt:formatNumber value="${charge.moneyGapDisplay}" pattern="#,###.00"/>
					</td>				
				</tr>	
				</c:if> 		      
		      </table>
		    </td>
		    <td width="30%" align="center">
			    <shiro:hasPermission name="charge:charge:edit">
		          <c:choose>
		   			<c:when test="${charge.status eq '00' || charge.status eq '05'}">
		   				<input id="btnYes" class="btn btn-primary" type="button" value=" 提 交 " onclick="reportSubmit()"/>
		   				<input id="btnSettle" class="btn btn-primary" type="button" value="预览结算清单" onclick="showSettlementList()"/>
		   			</c:when>
		   			<c:when test="${charge.status eq '10' && fns:getUser().isYwy}">
		   				<table width="100%">
		   				  <tr>
		   				    <td>意见：<br>
		   				      <textarea id="calMemo" name="calMemo" rows="3" cols="40"></textarea>    
		   				    </td>
		   				  </tr>
		   				  <tr>
		   				    <td align="center">
		   				      <input id="btnYes" class="btn btn-primary" type="button" value="测算通过 "  onclick="calculatePass()"/>
		   				      <input id="btnNo" class="btn btn-warning" type="button" value=" 退 回 " onclick="calculateReject()"/>
		   				      <input id="btnSettle" class="btn btn-primary" type="button" value="预览结算清单" onclick="showSettlementList()"/>
		   				    </td>
		   				  </tr>
		   				</table>
		   			</c:when>	
		   			<c:when test="${charge.status eq '20' && fns:getUser().isShy}">
		   				<table width="100%">
		   				  <tr>
		   				    <td>意见：<br>
		   				      <textarea id="approveMemo" name="approveMemo" rows="3" cols="40"></textarea>    
		   				    </td>
		   				  </tr>
		   				  <tr>
		   				    <td align="center">
		   				      <input id="btnYes" class="btn btn-primary" type="button" value="审核通过"  onclick="approvePass()"/>
		   				      <input id="btnNo" class="btn btn-warning" type="button" value=" 退 回 " onclick="approveReject()"/>
		   				      <input id="btnSettle" class="btn btn-primary" type="button" value="预览结算清单" onclick="showSettlementList()"/>
		   				    </td>
		   				  </tr>
		   				</table>
		   			</c:when>	
		   			<c:when test="${charge.status eq '30' && fns:getUser().isYwy}">
		   				<table width="100%">
		   				  <tr>
		   				    <td>意见：<br>
		   				      <textarea id="approveMemo" name="approveMemo" rows="3" cols="40"></textarea>    
		   				    </td>
		   				  </tr>
		   				  <tr>
		   				    <td align="center">
		   				      <input id="btnYes" class="btn btn-primary" type="button" value="提交凭证" onclick="uploadPass()"/>
		   				      <input id="btnNo" class="btn btn-warning" type="button" value=" 退 回 " onclick="uploadReject()"/>
		   				      <input id="btnSettle" class="btn btn-primary" type="button" value="预览结算清单" onclick="showSettlementList()"/>
		   				    </td>
		   				  </tr>
		   				</table>		   			    
		   			    
		   			</c:when>		   			
		   			<c:when test="${charge.status eq '35' && fns:getUser().isShy}">
		   				<table width="100%">
		   				  <tr>
		   				    <td>意见：<br>
		   				      <textarea id="approveMemo" name="approveMemo" rows="3" cols="40"></textarea>    
		   				    </td>
		   				  </tr>
		   				  <tr>
		   				    <td align="center">
		   				      <input id="btnYes" class="btn btn-primary" type="button" value="确认缴费" onclick="confirmPass()"/>
		   				      <input id="btnNo" class="btn btn-warning" type="button" value=" 退 回 " onclick="confirmReject()"/>
		   				      <input id="btnSettle" class="btn btn-primary" type="button" value="预览结算清单" onclick="showSettlementList()"/>
		   				    </td>
		   				  </tr>
		   				</table>		   			    
		   			    
		   			</c:when>		   				   				   				   					   					   			
		   			<c:otherwise>
		   			    <input id="btnSettle" class="btn btn-primary" type="button" value="预览结算清单" onclick="showSettlementList()"/>
		   			</c:otherwise>
		   		  </c:choose>
		   		  
			    </shiro:hasPermission>	    
			    
		    </td>
		  </tr>
			
		</table>
	</fieldset>		
</form:form>
