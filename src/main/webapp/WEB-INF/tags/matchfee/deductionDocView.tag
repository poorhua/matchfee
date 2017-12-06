<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>	

<%@ attribute name="deductionDoc" type="org.wxjs.matchfee.modules.charge.entity.DeductionDoc" required="true"%>
	
	<form:form class="form-horizontal">
		<fieldset>			
			<table class="table-form">
				<tr>
					<td class="tit">文件编号：</td><td>${deductionDoc.documentNo}</td>
					<td class="tit">名称：</td><td>${deductionDoc.name}</td>
				</tr>
				<tr>
					<td class="tit">文档日期：</td><td><fmt:formatDate value="${deductionDoc.documentDate}" pattern="yyyy-MM-dd"/></td>
					<td class="tit">链接：</td><td>
				<input type="hidden" id="path${deductionDoc.id}" name="path${deductionDoc.id}" value="${deductionDoc.path}">
				<sys:ckfinder input="path${deductionDoc.id}" type="files" uploadPath="/charge/deductionDoc" selectMultiple="false" readonly="true"/>					
					</td>
				</tr>
				<tr>
					<td class="tit">备注信息：</td>
					<td>
						${deductionDoc.remarks}
					</td>
					<td class="tit"></td>
					<td>					
					</td>
				</tr>
			</table>
		</fieldset>		
	</form:form>
