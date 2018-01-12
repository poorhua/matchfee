<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>	

<%@ attribute name="deductionDoc" type="org.wxjs.matchfee.modules.charge.entity.DeductionDoc" required="true"%>
	
	<form:form class="form-horizontal">
		<fieldset>			
			<table class="table-form">
				<tr>
					<td class="tit" width="15%">文件：</td>
					<td width="35%">
                     <a href="${deductionDoc.path}" target="_blank">${deductionDoc.filename}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     <input class="btn btn-default" type="button" value="显示" onclick="$('#deductionDoc_${deductionDoc.id}').show()"/>&nbsp;&nbsp;	
				     <input class="btn btn-default" type="button" value="隐藏" onclick="$('#deductionDoc_${deductionDoc.id}').hide()"/>									
					</td>
					<td class="tit" width="15%">名称：</td><td width="35%">${deductionDoc.name}</td>
				</tr>
				<tr>
				    <td class="tit">文件编号：</td><td>${deductionDoc.documentNo}</td>
					<td class="tit">文档日期：</td><td><fmt:formatDate value="${deductionDoc.documentDate}" pattern="yyyy-MM-dd"/></td>

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

	<div style="margin:10px 0 10px 0;text-align:center">
		<embed id="deductionDoc_${deductionDoc.id}" width="800" height="600" src="${deductionDoc.path}"  style="display:none"> </embed>
	</div>