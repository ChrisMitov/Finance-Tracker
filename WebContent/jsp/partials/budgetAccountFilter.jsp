<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div id="chartdiv" style="width: 100%; height: 500px; font-size: 11px;"></div>

<script type="text/javascript">
	$.get("./budget-account").success(function(data) {
		var chart = AmCharts.makeChart("chartdiv", {
			"type" : "funnel",
			"theme" : "light",
			"dataProvider" : data,
			"balloon" : {
				"fixedPosition" : true
			},
			"valueField" : "value",
			"titleField" : "title",
			"marginRight" : 240,
			"marginLeft" : 50,
			"startX" : -500,
			"rotate" : true,
			"labelPosition" : "right",
			"balloonText" : "[[title]]: [[value]]n[[description]]",
			"export" : {
				"enabled" : true
			}
		});
	});
</script>