<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="container-fluid">
	<div class="row text-center" style="overflow: hidden;">
		<div class="col-sm-3"
			style="float: none !important; display: inline-block;">
			<label class="text-left">Angle:</label> <input class="chart-input"
				data-property="angle" type="range" min="0" max="60" value="30"
				step="1" />
		</div>

		<div class="col-sm-3"
			style="float: none !important; display: inline-block;">
			<label class="text-left">Depth:</label> <input class="chart-input"
				data-property="depth3D" type="range" min="1" max="25" value="10"
				step="1" />
		</div>
		<div class="col-sm-3"
			style="float: none !important; display: inline-block;">
			<label class="text-left">Inner-Radius:</label> <input
				class="chart-input" data-property="innerRadius" type="range" min="0"
				max="80" value="0" step="1" />
		</div>
	</div>
</div>

<script type="text/javascript">
			$
					.get("./budgetChart")
					.success(
							function(data) {
								var chart = AmCharts
										.makeChart(
												"chartdiv",
												{
													"type" : "pie",
													"theme" : "light",
													"dataProvider" : data,
													"valueField" : "sum",
													"titleField" : "title",
													"outlineAlpha" : 0.4,
													"depth3D" : 15,
													"balloonText" : "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
													"angle" : 30,
													"export" : {
														"enabled" : true
													}
												});
								jQuery('.chart-input').off().on(
										'input change',
										function() {
											var property = jQuery(this).data(
													'property');
											var target = chart;
											var value = Number(this.value);
											chart.startDuration = 0;

											if (property == 'innerRadius') {
												value += "%";
											}

											target[property] = value;
											chart.validateNow();
										});
							});
		</script>