<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

	<style type="text/css">
		.tabText {font-size: 18px;font-weight:bold;}
	</style>

<%@ attribute name="tab" type="java.lang.String" required="true"%>


	<ul class="nav nav-tabs tabText">
		<li <c:if test="${empty tab || tab eq '1'}">class="active"</c:if> ><a href="${ctx}/charge/charge/projectLicenseTab">规划许可证</a></li>
		<li <c:if test="${tab eq '2'}">class="active"</c:if>><a href="${ctx}/charge/charge/opinionBookTab">条件意见书</a></li>
		<li <c:if test="${tab eq '3'}">class="active"</c:if>><a href="${ctx}/charge/charge/deductionDocTab">设计院证明</a></li> 
		<li <c:if test="${tab eq '4'}">class="active"</c:if>><a href="${ctx}/charge/charge/landPayTicketTab">国土缴费凭证</a></li>
		<li <c:if test="${tab eq '5'}">class="active"</c:if>><a href="${ctx}/charge/charge/projectDeductionTab">其他减项</a></li>
	</ul>