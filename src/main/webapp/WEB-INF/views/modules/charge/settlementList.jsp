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

			$("#chargeForm").attr("action","${ctx}/charge/charge/exportPDFSettlementList");
			$("#chargeForm").submit();
	    	
	    }
	    
	    function exportExcel(){

			$("#chargeForm").attr("action","${ctx}/charge/charge/exportExcelSettlementList");
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
    <c:if test="${settementList.charge.status ge '30'}">
    <div style="margin:10px 30px 10px 10px; text-align:right">
    <input id="btnPrint" class="btn btn-primary" type="button" value=" 打 印 " onclick="myprint()"/>&nbsp;&nbsp;&nbsp;
    <input id="btnExportPdf" class="btn btn-primary" type="button" value="导出PDF " onclick="exportPdf()"/>&nbsp;&nbsp;&nbsp;
    <!--  
    <input id="btnExportExcel" class="btn btn-primary" type="button" value="导出Excel" onclick="exportExcel()"/>
    -->
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
				<th style="text-align:center;width:26%">条目</th>
				<th style="text-align:center;width:12%">面积（平米）</th>
				<th style="text-align:center;width:12%">金额（元）</th>
				<th style="text-align:center;width:25%">说明</th>
				<th style="text-align:center;width:25%">备注</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="tit">
					<strong>*上期待清算金额（元）</strong>
				</td>
				<td>
				</td>
				<td style="text-align:right">
					${settementList.charge.previousRemainDisplay}
				</td>
				<td></td>
				<td></td>
			</tr>		    
		    <tr><td colspan="5" class="tit"><strong>*结算项目</strong></td></tr>
		<c:forEach items="${settementList.projectLicenses}" var="projectLicense">
			<tr>
				<td>
					${projectLicense.name}
				</td>
				<td style="text-align:right">
					<fmt:formatNumber value="${projectLicense.totalAreaDisplay}" pattern="#,###.00"/>
				</td>
				<td style="text-align:right">
					<fmt:formatNumber value="${projectLicense.totalMoneyDisplay}" pattern="#,###.00"/>
				</td>
				<td>
					${projectLicense.description}
				</td>
				<td>${projectLicense.remarks}</td>
			</tr>
		</c:forEach>
		    <tr><td colspan="5"><strong>*抵扣项(设计院证明)</strong></td></tr>
		<c:forEach items="${settementList.deductionDocItems}" var="deductionDocItem">
			<tr>
				<td>
					${deductionDocItem.item.name}
				</td>
				<td style="text-align:right">
					<fmt:formatNumber value="${deductionDocItem.area}" pattern="#,###.00"/>
				</td>
				<td style="text-align:right">
					<fmt:formatNumber value="${deductionDocItem.money}" pattern="#,###.00"/>
				</td>
				<td>
					${deductionDocItem.description}
				</td>				
				<td>
					${deductionDocItem.remarks}
				</td>
			</tr>
		</c:forEach>	
		    <tr><td colspan="5"><strong>*国土已缴费</strong></td></tr>
		<c:forEach items="${settementList.landPayTickets}" var="landPayTicket">
			<tr>
				<td>
					${landPayTicket.name}
				</td>
				<td>
					
				</td>
				<td style="text-align:right">
					 <fmt:formatNumber value="${landPayTicket.money}" pattern="#,###.00"/>
				</td>
				<td>
					${landPayTicket.description}
				</td>				
				<td>
					${landPayTicket.remarks}
				</td>
			</tr>
		</c:forEach>		
		    <tr><td colspan="5"><strong>*其他减项</strong></td></tr>
		<c:forEach items="${settementList.projectDeductions}" var="projectDeduction">
			<tr>
				<td>
					${projectDeduction.name}
				</td>
				<td style="text-align:right">
					<fmt:formatNumber value="${projectDeduction.area}" pattern="#,###.00"/>
				</td>
				<td style="text-align:right">
					<fmt:formatNumber value="${projectDeduction.money}" pattern="#,###.00"/>
				</td>
				<td>
					${projectDeduction.description}
				</td>				
				<td>
					${projectDeduction.remarks}
				</td>
			</tr>
		</c:forEach>		
		    <tr><td colspan="5"><strong>*缴费情况</strong></td></tr>
			<tr>
				<td style="text-align:right">结算金额（元）</td>
				<td></td>
				<td style="text-align:right">
				<fmt:formatNumber value="${settementList.charge.calMoney}" pattern="#,###.00"/>
				</td>
				<td></td>
				<td></td>
			</tr>		    
		<c:forEach items="${settementList.payTickets}" var="payTicket">
			<tr>
				<td>
					缴费
				</td>
				<td></td>
				<td style="text-align:right">
					
					<fmt:formatNumber value="${payTicket.money}" pattern="#,###.00"/>
				</td>
				<td style="text-align:right">
					${payTicket.description}
				</td>				
				<td>
					${payTicket.remarks}
				</td>
			</tr>
		</c:forEach>	
			<tr>
			<!--  
			<c:choose>
			  <c:when test="${settementList.charge.moneyGap lt 0}">
			  <td>少缴费</td>
			  </c:when>
			  <c:otherwise>
			  <td>多缴费</td>
			  </c:otherwise>
			</c:choose>
			-->
			  <td>待清算金额</td>
				<td></td>
				<td style="text-align:right">
				<fmt:formatNumber value="${settementList.charge.moneyGapDisplay}" pattern="#,###.00"/>
				</td>
				<td></td>
				<td></td>
			</tr>				
		</tbody>
	</table>    
    </div>
  </div>
</body>
</html>