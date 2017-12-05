<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="deductionDoc" type="org.wxjs.matchfee.modules.charge.entity.DeductionDoc" required="true"%>

	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>抵扣项</th>
				<th>面积（平米）</th>
				<th>金额（元）</th>
				<th>备注信息</th>
				<shiro:hasPermission name="charge:charge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${deductionDoc.deductionDocItemList}" var="deductionDocItem">
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
				<shiro:hasPermission name="charge:charge:edit"><td>
    				<a href="${ctx}/charge/deductionDocItem/form?id=${deductionDocItem.id}">修改</a>
					<a href="${ctx}/charge/deductionDocItem/delete?id=${deductionDocItem.id}" onclick="return confirmx('确认要删除该设计院证明项目吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
