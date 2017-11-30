<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>	

<%@ attribute name="content" type="org.wxjs.matchfee.modules.charge.entity.OpinionBook" required="true" description=""%>

<c:if test="${not empty content}">	
    <input id="btnAdd" class="btn btn-primary" type="button" value="添加文件"/>

	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>文档编号</th>
				<th>文档日期</th>
				<th>链接</th>
				<th>备注信息</th>
				<shiro:hasPermission name="charge:opinionBook:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
				    ${content.name}
				</td>
				<td>
					${content.documentNo}
				</td>				
				<td>
					<fmt:formatDate value="${content.documentDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
				</td>				 
				<td>
					${content.remarks}
				</td>
				<shiro:hasPermission name="charge:opinionBook:edit"><td>
    				<a href="${ctx}/charge/opinionBook/form?id=${content.id}">修改</a>
					<a href="${ctx}/charge/opinionBook/delete?id=${content.id}" onclick="return confirmx('确认要删除该条件意见书吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</tbody>
	</table>	
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>抵扣项代码</th>
				<th>面积（平米）</th>
				<th>金额（元）</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="charge:opinionBookItem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${content.opinionBookItemList}" var="opinionBookItem">
			<tr>
				<td><a href="${ctx}/charge/opinionBookItem/form?id=${opinionBookItem.id}">
					${opinionBookItem.itemId}
				</a></td>
				<td>
					${opinionBookItem.area}
				</td>
				<td>
					${opinionBookItem.money}
				</td>
				<td>
					<fmt:formatDate value="${opinionBookItem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${opinionBookItem.remarks}
				</td>
				<shiro:hasPermission name="charge:opinionBookItem:edit"><td>
    				<a href="${ctx}/charge/opinionBookItem/form?id=${opinionBookItem.id}">修改</a>
					<a href="${ctx}/charge/opinionBookItem/delete?id=${opinionBookItem.id}" onclick="return confirmx('确认要删除该条件意见书项目吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>		

</c:if>
