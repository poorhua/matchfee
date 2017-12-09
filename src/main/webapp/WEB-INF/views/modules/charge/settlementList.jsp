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

	</script>
</head>
<body>
	<legend>结算清单</legend>
	<matchfee:chargeViewWithSettleButton charge="${settementList.charge}"></matchfee:chargeViewWithSettleButton><br>
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
</body>
</html>