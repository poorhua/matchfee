<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<c:if test="${not empty chargeList}">
    <div style="text-align:left"><strong>征收列表</strong></div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<th>申报日期</th>
				<th>结算金额</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${chargeList}" var="charge">
			<tr>
				<td>
					${charge.id}
				</td>
				<td>
					<fmt:formatDate value="${charge.reportDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${charge.calMoney}
				</td>
				<td>
					${fns:getDictLabel(charge.status, 'charge_status_', '')}
				</td>		
				<td>	
					<a href="${ctx}/record/record/applyInfo?id=${applyRecord.id}" target="_blank">查看</a> 
				</td>	
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</c:if>

