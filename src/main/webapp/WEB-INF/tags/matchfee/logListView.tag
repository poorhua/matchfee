<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="chargeId" type="java.lang.String" required="true"%>

<script type="text/javascript">
<!--
$.ajax({     
    //要用post方式      
    type: "Post",     
    //方法所在页面和方法名      
    url: "${ctx}/base/operationLog/queryApprovelogs?chargeId=${chargeId}",     
    contentType: "application/json; charset=utf-8",     
    dataType: "json",     
    success: function(data) {
    	
    	var html = "";
    	
    	html += "<table id='contentTable' class='table table-striped table-bordered table-condensed'>";
    	html += "<tr>";
    	html += "<td>";
    	
        var obj = eval(data);
        var count = 0;
        $(obj).each(function (index) {
        	var val = obj[index];
        	html += val.content + "<BR>";
        	count++;
        });
    	html += "</td>";
    	html += "</tr>";
    	html += "</table>";
    	
    	document.getElementById("logDisplayTitle").innerHTML = "<strong>日志列表("+count+")</strong>";
        document.getElementById("logDisplayDiv").innerHTML = html;
    },     
    error: function(err) {     
        alert(err);     
    }
});
//-->
</script>

    <div id="logDisplayTitle" style="text-align:left"></div>
	<div id="logDisplayDiv"></div>

