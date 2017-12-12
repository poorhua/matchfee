<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算清单</title>
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
		
	    function exportPdf(){

			$("#chargeForm").attr("action","${ctx}/charge/charge/exportSettlementList");
			$("#chargeForm").submit();
	    	
	    }
	    
	    function exportExcel(){

			$("#chargeForm").attr("action","${ctx}/charge/charge/exportSettlementList");
			$("#chargeForm").submit();
	    	
	    }		    
	    
	    function myprint(){
	    	var printpage = document.getElementById("printDiv");
	    	var newstr = printpage.innerHTML; 
	    	var oldstr = document.body.innerHTML; 
	    	document.body.innerHTML =newstr; 
	    	window.print(); 
	    	document.body.innerHTML=oldstr; 
	    	return false; 	    	
	    }

	</script>
	
	<style>
	body{
	  text-align:center;
	  width:100%
	}
	</style>
	
	
</head>
<body>
  <div style="width:210mm; align:center; margin: 0 auto">
    <c:if test="${settementList.charge.status eq '40'}">
    <div style="margin:10px 30px 10px 10px; text-align:right">
    <input id="btnPrint" class="btn btn-primary" type="button" value=" 打 印 " onclick="myprint()"/>&nbsp;&nbsp;&nbsp;
    <input id="btnExportPdf" class="btn btn-primary" type="button" value="导出PDF " onclick="exportPdf()"/>&nbsp;&nbsp;&nbsp;
    <input id="btnExportExcel" class="btn btn-primary" type="button" value="导出Excel" onclick="exportExcel()"/>
    </div>    
    </c:if>
    <div id="printDiv" style="margin:20px 20px 20px 20px">
      <div style="align:center; margin: 0 auto;width:100%">
      <font size="5" color="blue">无锡市城市基础设施配套费结算清单</font>
      </div>
	
	<matchfee:chargeView charge="${settementList.charge}"></matchfee:chargeView><br>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>条目</th>
				<th>建筑面积（平米）</th>
				<th>金额（元）</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		    <tr><td colspan="4" class="tit"><strong>*结算项目</strong></td></tr>
		<c:forEach items="${settementList.projectLicenses}" var="projectLicense">
			<tr>
				<td>
					${projectLicense.name}
				</td>
				<td>
					${projectLicense.totalArea}
				</td>
				<td>
					${projectLicense.totalMoney}
				</td>
				<td>
					${projectLicense.remarks}
				</td>
			</tr>
		</c:forEach>
		    <tr><td colspan="4"><strong>*抵扣项(设计院证明)</strong></td></tr>
		<c:forEach items="${settementList.deductionDocItems}" var="deductionDocItem">
			<tr>
				<td>
					${deductionDocItem.item.name}
				</td>
				<td>
					${deductionDocItem.area}
				</td>
				<td>
					${deductionDocItem.money}
				</td>
				<td>
					${deductionDocItem.remarks}
				</td>
			</tr>
		</c:forEach>	
		    <tr><td colspan="4"><strong>*其他减项</strong></td></tr>
		<c:forEach items="${settementList.projectDeductions}" var="projectDeduction">
			<tr>
				<td>
					${projectDeduction.name}
				</td>
				<td>
					${projectDeduction.area}
				</td>
				<td>
					${projectDeduction.money}
				</td>
				<td>
					${projectDeduction.remarks}
				</td>
			</tr>
		</c:forEach>		
		    <tr><td colspan="4"><strong>*缴费情况</strong></td></tr>
			<tr>
				<td>结算金额（元）</td>
				<td></td>
				<td>${settementList.charge.calMoney}</td>
				<td></td>
			</tr>		    
		<c:forEach items="${settementList.payTickets}" var="payTicket">
			<tr>
				<td>
					缴费
				</td>
				<td></td>
				<td>
					${payTicket.money}
				</td>
				<td>
					${payTicket.remarks}
				</td>
			</tr>
		</c:forEach>	
			<tr>
			<c:choose>
			  <c:when test="${settementList.charge.moneyGap lt 0}">
			  <td>少缴费</td>
			  </c:when>
			  <c:otherwise>
			  <td>多缴费</td>
			  </c:otherwise>
			</c:choose>
				<td></td>
				<td>${settementList.charge.moneyGap}</td>
				<td></td>
			</tr>				
		</tbody>
	</table>    
    </div>
  </div>
</body>
</html>