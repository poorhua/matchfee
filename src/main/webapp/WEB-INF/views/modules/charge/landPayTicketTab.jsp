<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>国土缴费凭证管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
	    function toNewPage(){
		       window.location.replace("${ctx}/charge/landPayTicket/form?prjNum=${charge.project.prjNum}");
	    }		
	</script>
</head>
<body>
    <sys:message content="${message}"/>
    <legend>配套费征收</legend>	
    <matchfee:chargeViewWithButtons charge="${charge}"></matchfee:chargeViewWithButtons><br>

    <matchfee:chargeTabController tab="4"></matchfee:chargeTabController>
	
	<div style="margin:10px 60px 10px 0;text-align:right">
	    <shiro:hasPermission name="charge:charge:edit">
	    <c:if test="${charge.status lt '20' || (charge.status ge '20' && fns:getUser().isShy)}">
	    <input id="btnAdd" class="btn btn-primary" type="button" value="添加" onclick="toNewPage()"/>
	    </c:if>
	    </shiro:hasPermission>	   
	</div>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>票据</th>
				<th>代收费协议</th>		
				<th>票据号</th>	
				<th>名称</th>
				<th>面积（平米）</th>
				<th>金额（元）</th>
				<th>缴费日期</th>
				<th>备注信息</th>
				<shiro:hasPermission name="charge:charge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${charge.landPayTicketList}" var="landPayTicket">
			<tr>
				<td>
				<a href="${landPayTicket.path}" target="_blank">${landPayTicket.filename}</a>				
				</td>
				<td>
				<a href="${landPayTicket.agencyAgreement}" target="_blank">${landPayTicket.agencyAgreementFilename}</a>					
				</td>			
				<td>
					${landPayTicket.ticketNoDisplay}
				</td>				
				<td>
					${landPayTicket.name}
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
				<shiro:hasPermission name="charge:charge:edit"><td>
				<c:if test="${charge.status lt '20' || (charge.status ge '20' && fns:getUser().isShy)}">
    				<a href="${ctx}/charge/landPayTicket/form?id=${landPayTicket.id}">修改</a>
					<a href="${ctx}/charge/landPayTicket/delete?id=${landPayTicket.id}&prjNum=${charge.project.prjNum}" onclick="return confirmx('确认要删除该国土缴费凭证吗？', this.href)">删除</a>
				</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<matchfee:logListView chargeId="${charge.id}"></matchfee:logListView>
</body>
</html>