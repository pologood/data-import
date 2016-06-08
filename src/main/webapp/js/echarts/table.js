(function($, undefined) {
	var Table = function Table(c) {
		this.c = c;
	}

	Table.prototype = {
		constructor : Table,
		show : function() {
			var html = "<table>";
			html += this.createTop();
			html += this.createHead();
			html += this.createBody();
			html += "</table>";
			alert(html)
			this.element.html(html);
		},
		createTop : function() {
			return "";
		},
		createHead : function() {
			var legend = this.c.legend;
			var html = "<tr>";
			$.each(legend, function(index) {
				html += "<td>" + legend[index] + "</td>"
			});
			return html + "</tr>"
		},
		createBody : function() {
			return "";
		}
	}

	$.fn.table = function(c) {
		var table = new Table(c);
		table.show();
	}
	$.fn.table.Constructor = Table;
})(window.jQuery);