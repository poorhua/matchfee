<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>征收管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
   <c:forEach items="${list}" var="opinionBook">
      <matchfee:opinionBookView content="${opinionBook}"></matchfee:opinionBookView>
   </c:forEach>
</body>
</html>