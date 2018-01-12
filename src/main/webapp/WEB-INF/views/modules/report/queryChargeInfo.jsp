<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>配套费征收</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});		
	</script>
	<style type="text/css">
		.docTypeLabel {margin: 10px 0 10px 20px;color: blue;font-size: 18px;font-weight:bold;}
	</style>
</head>
<body>
    <sys:message content="${message}"/>
    <legend>配套费征收</legend>	
    <matchfee:chargeViewWithSettleButton charge="${charge}"></matchfee:chargeViewWithSettleButton><br>
    
    <div class="docTypeLabel">*规划许可证</div>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
                <th>文件</th>			
				<th>名称</th>
				<th>文件编号</th>
				<th>文档日期</th>
				<th>地上面积（平米）</th>
				<th>地下面积（平米）</th>
				<th>总面积（平米）</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${charge.projectLicenseList}" var="projectLicense">
			<tr>
				<td>
				<input type="hidden" id="pathlicense${projectLicense.id}" name="pathlicense${projectLicense.id}" value="${projectLicense.path}">
				<sys:ckfinder input="pathlicense${projectLicense.id}" type="files" uploadPath="/配套费/规划许可证" selectMultiple="false" readonly="true"/>		
				
				<!--  
				<embed width="800" height="600" src="${projectLicense.path}"> </embed> 
				-->
							
				</td>			
				<td>
					${projectLicense.name}
				</td>
				<td>
					${projectLicense.documentNo}
				</td>

				<td>
					<fmt:formatDate value="${projectLicense.documentDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${projectLicense.upArea}
				</td>
				<td>
					${projectLicense.downArea}
				</td>
				<td>
					${projectLicense.totalAreaDisplay}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<div class="docTypeLabel">*条件意见书</div>

<c:if test="${not empty charge.opinionBookList}">
	
	<c:forEach items="${charge.opinionBookList}" var="opinionBook">
	
	<matchfee:opinionBookView opinionBook="${opinionBook}"></matchfee:opinionBookView>
	
	<matchfee:opinionBookItemView opinionBook="${opinionBook}" withOperation="0"></matchfee:opinionBookItemView>	
	
	</c:forEach>	

</c:if>

   <div class="docTypeLabel">*设计院证明</div>
	<c:forEach items="${charge.deductionDocList}" var="deductionDoc">

		<legend>设计院证明---${deductionDoc.documentNo}</legend>

		<matchfee:deductionDocView deductionDoc="${deductionDoc}"></matchfee:deductionDocView>
		
		<matchfee:deductionDocItemView deductionDoc="${deductionDoc}" withOperation="0"></matchfee:deductionDocItemView>
		<hr style="border:1px dotted #036" />
		
	</c:forEach>
	
	<div class="docTypeLabel">*国土缴费凭证</div>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>票据</th>
				<th>代收费协议</th>			
				<th>名称</th>
				<th>票据号</th>
				<th>面积（平米）</th>
				<th>金额（元）</th>
				<th>缴费日期</th>
				<th>备注信息</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${charge.landPayTicketList}" var="landPayTicket">
			<tr>
				<td>
				<input type="hidden" id="pathlpt${landPayTicket.id}" name="pathlpt${landPayTicket.id}" value="${landPayTicket.path}">
				<sys:ckfinder input="pathlpt${landPayTicket.id}" type="files" uploadPath="/配套费/国土缴费凭证" selectMultiple="false" readonly="true"/>						
				</td>
				<td>
				<input type="hidden" id="aapath${landPayTicket.id}" name="aapath${landPayTicket.id}" value="${landPayTicket.agencyAgreement}">
				<sys:ckfinder input="aapath${landPayTicket.id}" type="files" uploadPath="/配套费/国土缴费凭证" selectMultiple="false" readonly="true"/>						
				</td>			
				<td>
					${landPayTicket.name}
				</td>
				<td>
					${landPayTicket.ticketNo}
				</td>
				<td>
					${landPayTicket.area}
				</td>
				<td>
					${landPayTicket.money}
				</td>
				<td>
					<fmt:formatDate value="${landPayTicket.payDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${landPayTicket.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>	
	
	<div class="docTypeLabel">*其他减项</div>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>文件</th>			
				<th>名称</th>
				<th>文件编号</th>
				<th>文档日期</th>
				<th>面积（平米）</th>
				<th>金额（元）</th>
				<!-- <th>抵扣方式</th>-->
				<th>备注信息</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${charge.projectDeductionList}" var="projectDeduction">
			<tr>
				<td>
				<input type="hidden" id="pathpd${projectDeduction.id}" name="pathpd${projectDeduction.id}" value="${projectDeduction.path}">
				<sys:ckfinder input="pathpd${projectDeduction.id}" type="files" uploadPath="/配套费/减项证明" selectMultiple="false" readonly="true"/>	
								
				</td>			
				<td>
					${projectDeduction.name}
				</td>
				<td>
					${projectDeduction.documentNo}
				</td>
				<td>
					<fmt:formatDate value="${projectDeduction.documentDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${projectDeduction.area}
				</td>
				<td>
					${projectDeduction.money}
				</td>
				<!-- 
				<td>
					${fns:getDictLabel(projectDeduction.deductionType, 'deduction_type', '')}
				</td>
				-->
				<td>
					${projectDeduction.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>	
	
	<div class="docTypeLabel">*本期缴费凭证</div>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>凭证</th>
			    <th>金额（元）</th>
				<th>票据号</th>
				<th>缴费日期</th>
			</tr>
		</thead>
		<tbody>
	
		<c:forEach items="${charge.payTicketList}" var="payTicket">
			<tr>
				<td>
				<input type="hidden" id="pathpt${payTicket.id}" name="pathpt${payTicket.id}" value="${payTicket.path}">
				<sys:ckfinder input="pathpt${payTicket.id}" type="files" uploadPath="/配套费/缴费票据" selectMultiple="false" readonly="true"/>					
				</td>
				<td>
					${payTicket.money}
				</td>				
				<td>
					${payTicket.ticketNo}
				</td>
				<td>
					<fmt:formatDate value="${payTicket.payDate}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</c:forEach>	
		</tbody>
	</table>
	
	<matchfee:logListView chargeId="${charge.id}"></matchfee:logListView>
</body>
</html>