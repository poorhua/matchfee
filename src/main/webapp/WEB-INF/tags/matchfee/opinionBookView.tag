<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>	

<%@ attribute name="opinionBook" type="org.wxjs.matchfee.modules.charge.entity.OpinionBook" required="true"%>
	
	<form:form class="form-horizontal">
		<fieldset>			
			<table class="table-form">
				<tr>
					<td class="tit" width="15%">文件：</td>
					<td width="35%">	
                     <a href="${opinionBook.path}" target="_blank">${opinionBook.filename}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     <input class="btn btn-default" type="button" value="显示" onclick="$('#opinionBook_${opinionBook.id}').show()"/>	&nbsp;&nbsp;
				     <input class="btn btn-default" type="button" value="隐藏" onclick="$('#opinionBook_${opinionBook.id}').hide()"/>	
					</td>
					<td class="tit" width="15%">名称：</td><td width="35%">${opinionBook.name}</td>
				</tr>
				<tr>
				    <td class="tit">文档编号：</td><td>${opinionBook.documentNo}</td>
					<td class="tit">文档日期：</td><td><fmt:formatDate value="${opinionBook.documentDate}" pattern="yyyy-MM-dd"/></td>
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
	
	<div style="margin:10px 0 10px 0;text-align:center">
		<embed id="opinionBook_${opinionBook.id}" width="800" height="600" src="${opinionBook.path}"  style="display:none"> </embed>
	</div>
