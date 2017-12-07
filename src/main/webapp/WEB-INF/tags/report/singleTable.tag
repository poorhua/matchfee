<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ include file="/WEB-INF/views/include/charttable.jsp"%>

<%@ attribute name="gridData" type="java.lang.String" required="true" description="grid data"%>
<%@ attribute name="gridColModel" type="java.lang.String" required="true" description="grid ColModel"%>

			
		<script type="text/javascript">

/*table*/
/*
var mydata = [
       { id: "1", invdate: "2007-10-01", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00" },
       { id: "9", invdate: "2007-09-01", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00" }
];
*/
var mydata = ${gridData};     

$(document).ready(function () {
    $("#jqGrid").jqGrid({
        datatype: "local",
		data: mydata,
        height: 400,
		width: 1250,
		/*
        colModel: [
            { label: 'Inv No', name: 'id', width: 75, key:true },
            { label: 'Notes', name: 'note', width: 150 }
        ],
        */
        colModel: ${gridColModel},               
        viewrecords: true, // show the current page, data rang and total records on the toolbar
		loadonce:true,
		sortable:false,
        rowNum: 50,
        caption: "",
        pager: "#jqGridPager"   
    });
});
		</script>

<table wifth="80%" align="center">
  <tr>
  	<td>
  <table id="jqGrid" align="center"></table>
  <div id="jqGridPager" style="align:center"></div>		
  	</td>
  </tr>	
</table>

