<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="opinionBook" type="org.wxjs.matchfee.modules.charge.entity.OpinionBook" required="true"%>

<%@ attribute name="withOperation" type="java.lang.String" required="true"%>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>抵扣项</th>
				<th>面积（平米）</th>
				<th>金额（元）</th>
				<th>备注信息</th>
				<c:if test="${withOperation eq '1' }">
				<shiro:hasPermission name="charge:charge:edit"><th>操作</th></shiro:hasPermission>
				</c:if>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${opinionBook.opinionBookItemList}" var="opinionBookItem">
			<tr>
				<td>
					${opinionBookItem.item.name}
				</td>
				<td>
					${opinionBookItem.area}
				</td>
				<td>
					${opinionBookItem.money}
				</td>
				<td>
					${opinionBookItem.remarks}
				</td>
				<c:if test="${withOperation eq '1' }">
				<shiro:hasPermission name="charge:charge:edit"><td>
    				<a href="${ctx}/charge/opinionBookItem/form?id=${opinionBookItem.id}">修改</a>
					<a href="${ctx}/charge/opinionBookItem/delete?id=${opinionBookItem.id}" onclick="return confirmx('确认要删除该条件意见书项目吗？', this.href)">删除</a>
				</td></shiro:hasPermission>				
				</c:if>

			</tr>
		</c:forEach>
		</tbody>
	</table>
