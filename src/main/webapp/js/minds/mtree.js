/**
 * 
 * @author dashuang.min 一个分类的选择jquery工具
 * 
 */

(function($, undefined) {
	var old = $.fn.mtree;
	var Tree = function(element, setting, data) {
		this.element = element;
		this.setting = setting;
		this.name = setting.name ? setting.name : 'tree';
		this.data = data;
	}
	Tree.prototype = {
		constructor : Tree,
		color : [ "widget-color-orange", "widget-color-dark",
				"widget-color-blue", "widget-color-blue3",
				"widget-color-green", "widget-color-red",
				"widget-color-purpel", "widget-color-pink",
				"widget-color-red3", "widget-color-grey" ],
		setSelect : function(selector, flag, init) {
			if (selector instanceof Array) {
				var len = selector.length;
				for (var i = 0; i < len; i++) {
					this.setSelect(selector[i], flag, init);
				}
			} else {
				this.menuSelect(this.data, selector, flag, init);
			}
		},
		menuSelect : function(menu, uri, flag, init) {
			if (menu instanceof Array) {
				var len = menu.length;
				for (var i = 0; i < len; i++) {
					var menuValue = menu[i];
					if (this.menuSelect(menuValue, uri, flag, init)) {
						return;
					} else {
						if (menuValue.menus) {
							if (this.menuSelect(menuValue.menus, uri, flag,
									init)) {
								return;
							}
						}
					}
				}
			} else {
				if (menu.uri == uri) {
					menu.selected = flag;
					this.select(menu, flag);
					if (menu.menus) {
						var menus = menu.menus;
						var len = menus.length;
						for (var i = 0; i < len; i++) {
							var m = menus[i];
							this.menuSelect(m, init ? uri : m.uri, flag);
						}
					}
					return true;
				}
				return false;
			}
		},
		buildTree : function(node) {
			if (arguments.length == 0) {
				var len = this.data.length;
				for (var i = 0; i < len; i++) {
					this.buildTree(this.data[i]);
				}
			} else {
				var nodes = node.menus;
				if (nodes != null && nodes.length > 0) {
					var len = nodes.length;
					for (var i = 0; i < len; i++) {
						nodes[i].parent = node;
					}
				}
			}
		},
		select : function(menu, flag) {
			var parent = menu.parent;
			if (parent != null) {
				if (flag) {
					while (parent != null) {
						parent.selected = flag;
						parent = parent.parent;
					}
				} else {
					var menus = parent.menus;
					for (var i = 0; i < menus.length; i++) {
						if (menus[i].selected)
							return;
					}
					parent.selected = flag;
					this.select(parent, flag);
				}
			}
		},
		createHTML : function() {
			this.element.empty();
			var root = '<class="row">';
			var $tree = this;
			$(this.data)
					.each(
							function(index) {
								root += '<div class="col-sm-12">';
								root += '<div class="widget-box '
										+ $tree.color[index] + '">';
								root += '<div class="widget-header">';
								root += '<div class="checkbox text-left">'
										+ $tree.createCheckBox(this) + '</div>'
								root += '</div><div class="widget-body"><div class="widget-main">'
										+ $tree.child(this.menus)
										+ '</div></div></div></div>';

							});
			root += '</div>';
			this.element.html(root);
			this.element.find('input').bind('change', function() {
				$tree.change(this);
			})
		},
		child : function(menus) {
			var node = '<ul class="list-unstyled">';
			var $tree = this;
			$(menus).each(
					function() {
						node += '<li class="checkbox text-left">'
								+ $tree.createCheckBox(this) + '</li>'
					});
			return node + '</ul>';
		},
		isSelect : function(menu) {
			var value = ' value="' + menu.uri + '" ';
			if (menu.selected) {
				value += 'checked="true"';
			}
			return value;
		},
		change : function(obj) {
			this.setSelect(obj.value, obj.checked, false);
			this.createHTML();
		},
		createCheckBox : function(menu) {
			return '<label><input  name="' + this.name + '" type="checkbox"  '
					+ this.isSelect(menu) + '  class="ace"><span class="lbl"> '
					+ menu.name + '</span></label>';
		}
	}

	$.fn.tree = function(p) {
		this.each(function() {
			var $this = $(this);
			var tree = new Tree($this, p.setting, p.data);
			tree.buildTree();
			if (!p.selector) {
				p.selector = [];
			}
			tree.setSelect(p.selector, true, true);
			tree.createHTML();
		})
	}
	$.fn.tree.Constructor = Tree;
	$.fn.tree.noConflict = function() {
		$.fn.tree = old;
		return this;
	}
})(window.jQuery);