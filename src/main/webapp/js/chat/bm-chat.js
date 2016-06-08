(function($, undefined) {

	var Chat = function() {
	}
	Tree.prototype = {
		constructor : Tree,
		init : function(config) {
			swfobject.embedSWF(config.swf, config.id, config.width,
					config.height, config.version);
		}
	}

	$.fn.chat = function(config) {
		var chat = new Chat();
		config.id = $(this).attr("id")
		chat.init(config);
		return chat;
	}

	var old = $.fn.chat;
	$.fn.chat.Constructor = Chat;
	$.fn.chat.noConflict = function() {
		$.fn.chat = old;
		return this;
	}

})(window.jQuery);
