<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>	

<%@ attribute name="opinionBook" type="org.wxjs.matchfee.modules.charge.entity.OpinionBook" required="true"%>
	
	<form:form class="form-horizontal">
		<fieldset>			
			<table class="table-form">
				<tr>
					<td class="tit">文档编号：</td><td>${opinionBook.documentNo}</td>
					<td class="tit">名称：</td><td>${opinionBook.name}</td>
				</tr>
				<tr>
					<td class="tit">文档日期：</td><td><fmt:formatDate value="${opinionBook.documentDate}" pattern="yyyy-MM-dd"/></td>
					<td class="tit">连接：</td><td>
				<input type="hidden" id="path" name="path" value="${opinionBook.path}">
				<sys:ckfinder input="path" type="files" uploadPath="/charge/opinionBook" selectMultiple="false" readonly="true"/>					
					</td>
				</tr>
				<tr>
					<td class="tit">备注信息：</td>
					<td>
						${opinionBook.remarks}
					</td>
					<td class="tit"></td>
					<td>					
					</td>
				</tr>
			</table>
		</fieldset>		
	</form:form>
