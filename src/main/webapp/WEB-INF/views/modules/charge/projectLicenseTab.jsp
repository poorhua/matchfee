<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规划许可证管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
	    function toNewPage(){
		       window.location.replace("${ctx}/charge/projectLicense/form");
	    }		
	</script>
</head>
<body>
    <sys:message content="${message}"/>
    <matchfee:chargeViewWithButtons charge="${charge}"></matchfee:chargeViewWithButtons><br>
	
	<matchfee:chargeTabController tab="1"></matchfee:chargeTabController>
	
	<div style="margin:10px 60px 10px 0;width='100%'">
	   <div align="right">
		    <shiro:hasPermission name="charge:charge:edit">
		    <input id="btnAdd" class="btn btn-primary" type="button" value="添加" onclick="toNewPage()"/>
		    </shiro:hasPermission>	   
	   </div>
	</div>
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
				<shiro:hasPermission name="charge:charge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${charge.projectLicenseList}" var="projectLicense">
			<tr>
				<td>
				<input type="hidden" id="path${projectLicense.id}" name="path${projectLicense.id}" value="${projectLicense.path}">
				<sys:ckfinder input="path${projectLicense.id}" type="files" uploadPath="/配套费/规划许可证" selectMultiple="false" readonly="true"/>					
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
				<shiro:hasPermission name="charge:charge:edit"><td>
    				<a href="${ctx}/charge/projectLicense/form?id=${projectLicense.id}">修改</a>
					<a href="${ctx}/charge/projectLicense/delete?id=${projectLicense.id}" onclick="return confirmx('确认要删除该规划许可证吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<matchfee:logListView chargeId="${charge.id}"></matchfee:logListView>
</body>
</html>