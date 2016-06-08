function Option() {

}
Option.prototype = {
	setTitle : function(text, subtext) {
		this.title = {
			text : text,
			subtext : subtext
		};
	},
	setTooltip : function() {
		this.tooltip = {
			trigger : 'axis'
		};
	},
	setLegend : function(legend) {
		this.legend = {
			data : legend
		};
	},
	setToolbox : function() {
		this.toolbox = {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		};
	},
	setCalculable : function() {
		this.calculable = true;
	},
	setxAxis : function(xAxis) {
		if (!xAxis.type) {
			xAxis.type = "category";
		}
		this.xAxis = xAxis;
	},
	setyAxis : function() {
		this.yAxis = [ {
			type : 'value',
			axisLabel : {
				formatter : '{value}'
			}
		} ];
	},
	setSeries : function(series) {
		var len = series.length;
		for (var i = 0; i < len; i++) {
			var serie = series[i];
			if (!serie.type) {
				serie.type = "line";
			}
		}
		this.series = series;
	},
	simpleBuilder : function(myOption) {
		if (!myOption.title) {
			myOption.title = "无";
		}
		if (!myOption.subTitle) {
			myOption.subTitle = "无";
		}

		this.setTitle(myOption.title, myOption.subTitle);
		this.setTooltip();
		this.setLegend(myOption.legend);
		this.setToolbox();
		this.setCalculable();
		this.setxAxis(myOption.xAxis);
		this.setyAxis();
		this.setSeries(myOption.series);
	}
};
