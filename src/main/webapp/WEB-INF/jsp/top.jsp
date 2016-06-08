<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/font-awesome.min.css" />

<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet" href="/css/google.css?family=Open+Sans:400,300" />

<!-- ace styles -->
<link rel="stylesheet" href="/css/ace.min.css" id="main-ace-style" />
<!--[if lte IE 9]>
			<link rel="stylesheet" href="/css/ace-part2.min.css" />
		<![endif]-->
<link rel="stylesheet" href="/css/ace-skins.min.css" />
<link rel="stylesheet" href="/css/ace-rtl.min.css" />

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="/js/ace-extra.min.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="/js/html5shiv.min.js"></script>
		<script src="/js/respond.min.js"></script>
		<![endif]-->


<!-- basic scripts -->

<!--[if !IE]> -->
<script src="/js/jquery/jquery-2.1.1.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="/js/jquery/jquery-1.11.1.min.js"></script>
<![endif]-->

<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery
			|| document.write("<script src='/js/jquery.min.js'>"
					+ "<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script src="/js/bootbox.js"></script>



<script type="text/javascript">
	window.alert = function(title, callback) {
		bootbox.dialog({
			title : '警告',
			message : "<span class='bigger-110'>" + title + "</span>",
			buttons : {
				"success" : {
					"label" : "确定",
					"className" : "btn-sm btn-success",
					"callback" : function() {
						callback();
					}
				}
			}
		});
	}

	function ajaxResult(data, f) {
		if (data.code == 1) {
			f(data.retMsg);
		} else {
			if (data.code == -1) {
				alert("服务器返回异常")
			} else {
				alert("访问失败,你没有权限或者登录已经注销");
			}
		}
	}

	function deleteAlert(o) {
		var message = $(o).attr("title");
		if (!message) {
			message = "你确定要删除这条数据吗? , 删除之后数据不能恢复";
		}

		bootbox.confirm({
			title : '提示',
			message : message,
			buttons : {
				confirm : {
					label : "确定",
					className : "btn-primary btn-sm",
				},
				cancel : {
					label : "取消",
					className : "btn-sm",
				}
			},
			callback : function(result) {
				if (result == 1) {
					window.location.href = o.attr("href");
				}
			}
		});
		return false;
	}

	$(function() {
		$("a.deleteWarn").bind("click", function() {
			return deleteAlert($(this));
		})

		$("input.noInput").bind("keydown", function() {
			$(this).val('');
			return false;
		})

	})
</script>

<style type="text/css">
.search-div-height {
	height: 40px;
}

.rl-form_main {
	width: 400px;
	margin: auto;
}

.rl-form .input-group {
	margin-bottom: 30px;
}

.rl-form .input-group {
	margin-bottom: 20px;
}

.rl-form  .login-header {
	padding-bottom: 9px;
	margin: 1px 0 20px;
	border-bottom: 1px solid #ddd;
}
</style>