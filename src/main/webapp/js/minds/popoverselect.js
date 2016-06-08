/**
 * @author dashuang.min 一个弹出选择工具
 */

(function($) {
	var old = $.fn.popovermultiselect;
	var cursor;
	var PopoverMultiSelect = function(node, option) {
		this.node = node;
		this.option = option;
		cursor = this;
	}
	PopoverMultiSelect.prototype = {
		constructor : PopoverMultiSelect,
		init : function() {
			this.name = new Date().getTime();
			this.node.attr("data-toggle", "modal");
			this.node.attr("data-target", "#" + this.name);
			this.htmlNode = "node_" + this.name;
			this.node
					.parent()
					.append(
							'<div class="modal fade" id="'
									+ this.name
									+ '"  tabindex="-1" role="dialog" \
					 	 	aria-labelledby="'
									+ this.name
									+ '" aria-hidden="true"> \
					 		<div class="modal-dialog modal-lg"> \
					 			<div class="modal-content"> \
					 				<div class="modal-header"> \
									    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> \
					 					<h4 class="modal-title" id="'
									+ this.name
									+ '">'
									+ this.option.title
									+ '</h4> \
					 				</div> \
					 				<div class="modal-body" id="'
									+ this.htmlNode
									+ '"></div> \
					 			</div> \
					 		</div> \
					 	</div>');
			cursor.option.select = $("#"+cursor.option.selectId).val();
			this.builder(this.option.key);
			this.createHtml(this.option.key);
		},
		bind : function(nodeName) {
			var key = cursor.option.key;
			$(nodeName).bind('click', function() {
				var selectId =  $(this).attr("id");
				cursor.option.select = selectId;
				$("#"+cursor.option.selectId).val(selectId);
				cursor.builder(key);
				cursor.setInput(key);
				$("#" + cursor.name).modal('hide');
				cursor.createHtml(cursor.option.key);
			})
		},
		builder : function(key) {
			var select = cursor.option.select;
			$(cursor.option.data).each(function(index) {
				var otaTypeGroup = this;
				var otaTypes = otaTypeGroup[key.child];
				var change = false;
				$(otaTypes).each(function(index) {
					var otaType = otaTypes[index];
					if (select == otaType[key.childName]) {
						otaType.selected = change = true;
					} else {
						otaType.selected = false;
					}
				});
				otaTypeGroup.selected = change;
			});
		},
		setInput : function(key) {
			var value = "";
			$(this.option.data).each(function(index) {
				var otaTypeGroup = this;
				if (otaTypeGroup.selected) {
					if (value.length > 0)
						value += ",";
					value += otaTypeGroup[key.parentValue] + "(";
					var otaTypes = otaTypeGroup[key.child];
					var count = 0;
					$(otaTypes).each(function(index) {
						var otaType = otaTypes[index];
						if (otaType.selected) {
							value += otaType[key.childValue];
						}

					});
					value += ")";
				}
			});
			this.node.val(value);
		},
		createHtml : function(key) {
			var html = ""
			$("#" + this.htmlNode).html(html);
			$(this.option.data)
					.each(
							function(index) {
								var otaTypeGroup = this;
								html += "<div class='row'><div class='col-xs-12 col-sm-12'><div style='background-color:#e8e8e8;padding-left:5px;padding-right:15px;padding-top:5px;padding-bottom:5px;'><label> <strong>"
										+ otaTypeGroup[key.parentValue]
										+ ((otaTypeGroup.selected) ? "<i class='fa fa-fire'></i>"
												: "")
										+ "</strong></label></div><div class='col-xs-12 col-md-12'>";
								var otaTypes = otaTypeGroup[key.child];
								$(otaTypes)
										.each(
												function(index) {
													var otaType = otaTypes[index];
													html += ' <a class="pop_select btn btn-xs '
															+ ((otaType.selected) ? "btn-danger"
																	: "btn-info")
															+ ' " id='
															+ otaType[key.childName]
															+ '>'
															+ otaType[key.childValue]
															+ '</a> '
												});
								html += '</div></div></div>';
							});
			$("#" + this.htmlNode).html(html);
			this.bind(".pop_select");
		}
	}

	$.fn.popovermultiselect = function(option) {
		var popovermultiselect = new PopoverMultiSelect($(this), option);
		popovermultiselect.init();
	}

	$.fn.popovermultiselect.Constructor = PopoverMultiSelect;
	$.fn.popovermultiselect.noConflict = function() {
		$.fn.popovermultiselect = old;
		return this;
	}

})(window.jQuery)