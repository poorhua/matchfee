<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="deductionDoc" type="org.wxjs.matchfee.modules.charge.entity.DeductionDoc" required="true"%>

<%@ attribute name="withOperation" type="java.lang.String" required="true"%>

	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="20%">抵扣项</th>
				<th width="10%">面积（平米）</th>
				<th width="10%">金额（元）</th>
				<th width="10%">意见书面积（平米）</th>
				<th width="10%">已抵扣面积（平米）</th>
				<th width="10%">剩余面积（平米）</th>
				<th width="20%">备注信息</th>
				<c:if test="${withOperation eq '1' }">
				<shiro:hasPermission name="charge:charge:edit"><th width="10%">操作</th></shiro:hasPermission>
				</c:if>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${deductionDoc.deductionDocItemList}" var="deductionDocItem">
			<tr>
				<td>
					${deductionDocItem.item.name}
				</td>
				<td style="text-align:right">
					<fmt:formatNumber value="${deductionDocItem.area}" pattern="#,##0.00"/>
				</td>
				<td style="text-align:right">
					<fmt:formatNumber value="${deductionDocItem.money}" pattern="#,##0.00"/>
				</td>
				<td style="text-align:right">
					${deductionDocItem.areaInOpinionBook}
				</td>				
				<td style="text-align:right">
					${deductionDocItem.areaDeducted}
				</td>
				<td style="text-align:right">
					${deductionDocItem.areaRemained}
				</td>				
				<td>
					${deductionDocItem.remarks}
				</td>
				<c:if test="${withOperation eq '1' }">
				<shiro:hasPermission name="charge:charge:edit"><td>
    				<a href="${ctx}/charge/deductionDocItem/form?id=${deductionDocItem.id}">修改</a>
					<a href="${ctx}/charge/deductionDocItem/delete?id=${deductionDocItem.id}" onclick="return confirmx('确认要删除该设计院证明项目吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
