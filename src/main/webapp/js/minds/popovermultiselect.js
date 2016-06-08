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
			this.builder(this.option.key);
			this.createHtml(this.option.key);
		},
		select : function(value, checked, level) {
			var key = this.option.key;
			$(this.option.data)
					.each(
							function(index) {
								var otaTypeGroup = this;
								if (typeof (level) == "undefined") {
									otaTypeGroup.selected = checked;
									cursor
											.setSelect(key, otaTypeGroup,
													checked);
								} else {
									if (level) {
										if (otaTypeGroup[key.parentName] == value) {
											otaTypeGroup.selected = checked;
											cursor.setSelect(key, otaTypeGroup,
													checked);
										}
									} else {
										var otaTypes = otaTypeGroup[key.child];
										$(otaTypes)
												.each(
														function(index) {
															var otaType = otaTypes[index];
															if (otaType[key.childName] == value) {
																otaType.selected = checked;
																cursor
																		.setSelect(
																				key,
																				otaTypeGroup,
																				'setSelect');
															}
														});
									}
								}
							});
			this.createHtml(key);
		},
		bind : function(nodeName, name, level) {
			var cursor = this;
			$("#" + nodeName + " input[name='" + name + "']").bind(
					'click',
					function() {
						cursor.select($(this).val(), $(this).is(":checked"),
								level);
					})
		},
		setSelect : function(key, otaTypeGroup, checked) {
			var otaTypes = otaTypeGroup[key.child];
			if (checked == 'setSelect') {
				var haveSelected = false;
				$(otaTypes).each(function(index) {
					var otaType = otaTypes[index];
					if (otaType.selected) {
						haveSelected = true;
						return;
					}
				});
				otaTypeGroup.selected = haveSelected;
			} else {
				$(otaTypes).each(function(index) {
					var otaType = otaTypes[index];
					otaType.selected = checked;
				});
			}
		},
		builder : function(key) {
			var select = this.option.select;
			var size = select.length;
			$(this.option.data).each(function(index) {
				var otaTypeGroup = this;
				var otaTypes = otaTypeGroup[key.child];
				$(otaTypes).each(function(index) {
					var otaType = otaTypes[index];
					for (var i = 0; i < size; i++) {
						if (select[i] == otaType.uri) {
							otaType.selected = true;
							otaTypeGroup.selected = true;
						}
					}
				});
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
							count++;
						}

					});
					value += count + ")";
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
								html += "<div class='row'><div class='col-xs-12 col-sm-12'><div style='background-color:#e8e8e8;padding-left:5px;padding-right:5px;padding-top:5px;padding-bottom:5px;'><label><input type='checkbox' "
										+ (otaTypeGroup.selected ? 'checked'
												: '')
										+ " class='ace' name='"
										+ key.parentCheckboxName
										+ "' value='"
										+ otaTypeGroup[key.parentName]
										+ "'  /><span class='lbl'> <strong>"
										+ otaTypeGroup[key.parentValue]
										+ "</strong> </span></label></div><div class='col-xs-12 col-md-12'>";
								var otaTypes = otaTypeGroup[key.child];
								$(otaTypes)
										.each(
												function(index) {
													var otaType = otaTypes[index];
													html += '<div class="checkbox inline">\
													<label><input name="'
															+ key.childCheckboxName
															+ '" type="checkbox" '
															+ (otaType.selected ? "checked"
																	: "")
															+ ' class="ace"  value="'
															+ otaType[key.childName]
															+ '"   >\
														<span class="lbl"> '
															+ otaType[key.childValue]
															+ '</span>\
													</label>\
												</div>';
												});
								html += '</div></div></div>';
							});
			$("#" + this.htmlNode).html(html);
			this.bind(this.name, key.parentCheckboxName, true);
			this.bind(this.name, key.childCheckboxName, false);
			this.setInput(key);
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