<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ taglib prefix="report" tagdir="/WEB-INF/tags/report"%>
<html>
<head>
<meta charset="utf-8">
<title>dashboard</title>
<script type="text/javascript" src="/matchfee/static/chart/Chart.js"></script>
<link rel="stylesheet" href="/matchfee/static/chart/dashboard.css" />
</head>
<body>

	<div id="prat1">
		<fieldset id="fieldset1">
			<legend>账号信息</legend>
			<table id="customers" align="center">
				<tr>
					<td>用户名：</td>
					<td>${user.name }
				</tr>

				<tr>
					<td>用户邮箱：</td>
					<td>${user.email }</td>
				</tr>
				<tr>
					<td>最后登录日期：</td>
					<td><fmt:formatDate value="${user.loginDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td>用户工号：</td>
					<td>${user.no }</td>
				</tr>
				<c:if test="${user.isQyUser}">
				<tr>
					<td>建设单位代码：</td>
					<td>${user.project.buildCorpCode}</td>
				</tr>
				<tr>
					<td>建设单位名称：</td>
					<td>${user.project.buildCorpName}</td>
				</tr>								
				</c:if>
			</table>
		</fieldset>
	</div>
	<div id="prat2">
		<fieldset id="fieldset2">
			<legend>征收汇总</legend>
			<table id="customers2" align="center">

			</table>
		</fieldset>
	</div>
	<div id="prat3">
		<fieldset id="fieldset3">
			<legend>申报数按月统计</legend>
				<canvas id="myChart1"></canvas>
		</fieldset>
	</div>
	<div id="prat4">
		<fieldset id="fieldset4">
			<legend>征收金额按月统计</legend>
				<canvas id="myChart2"></canvas>
		</fieldset>
	</div>

	<script type="text/javascript">
		var bufferDeclare = ${declareData};
		var labelDeclare = [];
		var dataDeclare = [];
		for ( var i in bufferDeclare) {
			dataDeclare.push(bufferDeclare[i].report);
			labelDeclare.push(bufferDeclare[i].month);
		}
		var declare = {
			labels : labelDeclare,
			datasets : [ {
				data : dataDeclare,
				backgroundColor : [ "#FF6384", "#FF6384", "#FF6384", "#FF6384",
						"#FF6384", "#FF6384", "#FF6384", "#FF6384", "#FF6384",
						"#FF6384", "#FF6384", "#FF6384" ],
				label : '申报数量'
			} ]
		};

		var ctx1 = document.getElementById("myChart1").getContext("2d");
		var myBarChart = new Chart(ctx1, {
			type : 'bar',
			data : declare,
		});

		var bufferChargeMoney = ${chargeMoneyData};
		var labelChargeMoney = [];
		var dataChargeMoney = [];
		for ( var i in bufferChargeMoney) {
			dataChargeMoney.push(bufferChargeMoney[i].report);
			labelChargeMoney.push(bufferChargeMoney[i].month);
		}
		var chargeMoney = {
			labels : labelChargeMoney,
			datasets : [ {
				data : dataChargeMoney,
				backgroundColor : [ "#36A2EB", "#36A2EB", "#36A2EB", "#36A2EB",
						"#36A2EB", "#36A2EB", "#36A2EB", "#36A2EB", "#36A2EB",
						"#36A2EB", "#36A2EB", "#36A2EB" ],
				label : '征收总金额'
			} ]
		};
		var ctx2 = document.getElementById("myChart2").getContext("2d");
		var myBarChart = new Chart(ctx2, {
			type : 'bar',
			data : chargeMoney,
		});

		var bufferChargeStatus = ${chargeStatusData};
		var trStr = '';
		for ( var i in bufferChargeStatus) {
			trStr += '<tr><td>' + bufferChargeStatus[i].status + '：</td><td>'
					+ bufferChargeStatus[i].count + '个<td></tr>';
		}
		document.getElementById("customers2").innerHTML = trStr;
	</script>
</body>
</html>