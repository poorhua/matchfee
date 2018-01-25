<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>征收管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	    
	    function toNew(){
		   window.location.replace("${ctx}/charge/payTicket/form?charge.id="+${charge.id});
	    }		
	</script>
</head>
<body>
    <sys:message content="${message}"/>
    <legend>配套费征收</legend>	
    <c:if test="${fns:getUser().isShy}"><legend>缴费确认</legend></c:if>
    <c:if test="${!fns:getUser().isShy}"><legend>上传缴费凭证</legend></c:if>
    <matchfee:chargeViewWithButtons charge="${charge}"></matchfee:chargeViewWithButtons><br>

	<div style="margin:10px 60px 10px 0;text-align:right">
	    <shiro:hasPermission name="charge:charge:edit">
	    <c:if test="${charge.status lt '20' || (charge.status ge '20' && fns:getUser().isShy)}">
	    <input id="btnAdd" class="btn btn-primary" type="button" value="添加缴费凭证" onclick="toNew()"/>
	    </c:if>
	    </shiro:hasPermission>	   
	</div>

<c:if test="${not empty charge.payTicketList}">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>保存路径</th>
			    <th>金额（元）</th>
				<th>票据号</th>
				<th>缴费日期</th>
				<shiro:hasPermission name="charge:charge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
	
		<c:forEach items="${charge.payTicketList}" var="payTicket">
			<tr>
				<td>
				<a href="${payTicket.path}" target="_blank">${payTicket.filename}</a>				
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
				<shiro:hasPermission name="charge:charge:edit"><td>
				<c:if test="${charge.status lt '20' || (charge.status ge '20' && fns:getUser().isShy)}">
    				<a href="${ctx}/charge/payTicket/form?id=${payTicket.id}">修改</a>
					<a href="${ctx}/charge/payTicket/delete?id=${payTicket.id}" onclick="return confirmx('确认要删除该缴费凭证吗？', this.href)">删除</a>
				</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>	
		</tbody>
	</table>
</c:if>

<matchfee:logListView chargeId="${charge.id}"></matchfee:logListView>
    
</body>
</html>