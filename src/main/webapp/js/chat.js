/**
 * @author dashuang.min 一个聊天室程序
 */

(function($) {
	var old = $.fn.bmChat;
	var cur = {};
	var userListArray = {};
	var user = {};
	var count = 0;
	var countLength = 30;
	var scroll = true;
	var messageInput = {};
	var userlistNode = {};
	var messageNode = {};
	var messageContainer = {};
	var preTime = 0;
	var BmChat = function(node, option) {
		this.node = node;
		this.option = option;
		cur = this;
		messageInput = $('#' + this.option.messageInput);
		userlistNode = $("#" + this.option.userlistNode);
		userlistCount = $("#" + this.option.userlistCount);
		messageNode = $("#" + this.option.messageList);
		messageContainer = $("#messageContainer");
	};

	BmChat.prototype = {
		constructor : BmChat,
		init : function() {
			var option = this.option;
			var swfVersionStr = "11.1.0";
			var xiSwfUrlStr = "playerProductInstall.swf";
			var flashvars = {
				rtmp : option.rtmp,
				user : option.user,
				manager : true
			};
			var params = {
				rtmp : option.rtmp,
				user : option.user,
				manager : true
			};
			params.quality = "high";
			params.bgcolor = "#FF0000";
			params.allowscriptaccess = "sameDomain";
			params.allowfullscreen = "true";
			var attributes = {};
			attributes.id = option.name;
			attributes.name = option.name;
			attributes.align = "middle";
			swfobject.embedSWF(option.swf, option.name, "10", "10",
					swfVersionStr, xiSwfUrlStr, flashvars, params, attributes);
		},
		swfObject : function() {
			if (navigator.appName.indexOf("Microsoft") != -1) {
				return window[this.option.name];
			} else {
				return document[this.option.name];
			}
		},
		logs : function(o) {
			for ( var p in o) {
				this.log(p);
			}
		},
		log : function(json) {
			console.log(json);
		},
		alert : function(json) {
			console.log(json);
		},
		asCalljs : function(obj) {
			eval("this." + obj['method'])(obj['json']);
		},
		open : function() {
			cur.callAs('ManagerFunction', 'close');
		},
		close : function() {
			cur.callAs('ManagerFunction', 'open');
		},
		setUser : function(json) {
			user = eval('(' + json + ')');
			var messageSend = $('#' + cur.option.messageSend);
			messageSend.click(function(val) {
				var val = messageInput.val();
				if (val != '') {
					cur.setMessage(val);
					messageInput.val('');
				}
			});
			$(document).keydown(function(event) {
				if (event.keyCode == 13) {
					var val = messageInput.val();
					if (val != '') {
						cur.setMessage(val);
						messageInput.val('');
					}
				}
			});
			messageInput.attr("disabled", false);
			messageSend.attr("disabled", false);

			var config = cur.option.config;
			var limitText = 50;
			if (config && config.limitText) {
				limitText = config.limitText > 3 ? config.limitText : limitText;
			}
			messageInput.attr("maxLength", limitText);
			cur.nodeInit();
		},
		nodeInit : function() {
			$("#c_scroll_bt").click(
					function() {
						if (scroll) {
							$("#c_scroll_bt i").removeClass(
									"fa-arrow-circle-down").addClass(
									"fa-arrow-circle-right");
							scroll = false;
						} else {
							$("#c_scroll_bt i").removeClass(
									"fa-arrow-circle-right").addClass(
									"fa-arrow-circle-down");
							scroll = true;
						}
					}).attr("disabled", false);

			$("#c_clear_bt").click(function() {
				cur.clear(count);
			}).attr("disabled", false);

			$("#c_photo_bt").click(function() {
				cur.uploader();
			}).attr("disabled", false);

			$("#c_face_bt")
					.attr("disabled", false)
					.popover(
							{
								animation : true,
								template : '<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content" id="faceSelect"></div></div>',
								title : '请选择表情',
								placement : 'top',
								trigger : 'click',
								content : function() {
									return cur.face();
								},
								html : true
							});

			$('#c_face_bt').on(
					'shown.bs.popover',
					function() {
						$("#faceSelect img").click(
								function() {
									messageInput.val(messageInput.val() + "{"
											+ this.title + "}");
									$('#c_face_bt').popover('hide');
								});
					});
		},
		userlist : function(json) {
			var html = "";
			userlistNode.html(html);
			$(json).each(function() {
				userListArray[this.id] = this;
				html += cur.option.userListTemplate(this);
			});
			userlistNode.html(html);
			userlistCount.text(json.length);
		},
		setMessage : function(json) {
			if (cur.limitFrequency()) {
				var message = {};
				message.content = json;
				cur.callAs("ChatFunction", "broadcastMsg", message);
			} else {
				cur.alert("你发送消息过于频繁");
			}
		},
		notice : function(json) {
			var message = {};
			message.content = json;
			cur.callAs("ChatFunction", "notice", message);
		},
		uploader : function() {
			cur.callAs("", "upload", "");
		},
		callAs : function(cls, method, json) {
			var command = {};
			command.classMethod = cls;
			command.method = method;
			command.json = json;
			cur.swfObject().jsCallas(command);
		},
		alert : function(content) {
			cur.appendText('<h5 class="red chat_message_tag">' + content
					+ '</h5>');
		},
		getMessage : function(json) {
			json = eval('(' + json + ')');
			if (json.senderId) {
				var admin;
				if (json.senderId == user.id) {
					admin = user.classify;
					json.nikename = "我";
				} else {
					var tu = userListArray[json.senderId];
					admin = tu.classify;
					json.nikename = userListArray[json.senderId].nickname;
				}
				json.admin = (admin == 100) ? true : false;
				var content = json.content;
				var begin = content.indexOf("{");
				while (begin >= 0) {
					var end = content.indexOf("}", begin);
					if (end > 0) {
						var f1 = content.substring(0, begin);
						var f2 = content.substring(end + 1, content.length);
						var name = content.substring(begin + 1, end);
						content = f1 + "<img src='/face/" + phiz[name] + "' />"
								+ f2;
						begin = content.indexOf("{", end);
					} else {
						begin = content.indexOf("{", begin);
					}
				}
				json.content = content;
				cur.appendText(cur.option.messageTemplate(json));
			} else {
				json.nikename = "公告";
				cur.appendText(cur.option.noticeTemplate(json));
			}
		},
		appendText : function(content) {
			messageNode.append(content);
			count++;
			if (count > countLength) {
				cur.clear(10);
			}
			if (scroll) {
				messageContainer.stop().animate({
					scrollTop : messageNode.height()
				}, 1000);
			}
		},
		clear : function(depth) {
			var nodes = $(".chat_message_tag");
			for (var i = 0; i < depth; i++) {
				var obj = nodes.get(i);
				if (obj) {
					obj.remove();
				}
			}
			count = 0;
		},
		limitFrequency : function() {
			var nowTime = new Date().getTime() / 1000;
			var config = cur.option.config;
			var limitFrequency = 3;
			if (config && config.limitFrequency) {
				limitFrequency = config.limitFrequency > 3 ? config.limitFrequency
						: limitFrequency;
			}
			if (nowTime - preTime > limitFrequency) {
				preTime = nowTime;
				return true;
			}
			return false;
		},
		callback : function(json) {
			if (cur.option.callback) {
				cur.appendText(cur.option.callback(json));
			}
		},
		face : function() {
			var faces = [ "", "", "" ];
			for ( var p in phiz) {
				var pref = p.substring(0, 2);
				var str = "<a class='face_img'><img alt='" + p + "' title='"
						+ p + "' src='/face/" + phiz[p] + "' /></a>";
				if (pref == 'df') {
					faces[0] += str;
				} else if (pref == 'lt') {
					faces[1] += str;
				} else if (pref == 'lx') {
					faces[2] += str;
				}
			}
			var content = '<div class="tabbable tabs-below"> \
				<div class="tab-content"> \
				<div id="face_0" class="tab-pane in active"> '
					+ faces[0]
					+ '</div> \
				<div id="face_1" class="tab-pane"> '
					+ faces[1]
					+ '</div> \
				<div id="face_2" class="tab-pane"> '
					+ faces[2]
					+ '</div>\
			</div>\
			<ul class="nav nav-tabs">\
				<li class="active">\
					<a data-toggle="tab" href="#face_0">常用</a>\
				</li>\
				<li>\
					<a data-toggle="tab" href="#face_1">测试</a>\
				</li>\
				<li>\
					<a data-toggle="tab" href="#face_2">给的</a>\
				</li>\
			  </ul>\
		   </div>';
			return content;
		}
	};

	$.fn.bmChat = function(option) {
		var bmChat = new BmChat($(this), option);
		bmChat.init();
		return bmChat;
	};

	$.fn.bmChat.Constructor = BmChat;
	$.fn.bmChat.noConflict = function() {
		$.fn.bmChat = old;
		return this;
	};

})(window.jQuery);