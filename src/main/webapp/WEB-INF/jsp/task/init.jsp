<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../commons/header.jsp"%>
<link rel="stylesheet" href="/css/jquery-ui.css">
<link rel="stylesheet" href="/css/bootstrap-datetimepicker.min.css">
<%@include file="../top.jsp"%>
</head>
<body class="no-skin">
	<%@include file="../header.jsp"%>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed');
			} catch (e) {
			}
		</script>
		<%@include file="../left.jsp"%>
		<div class="main-content">
			<%@include file="../breadcrumb.jsp"%>
			<div class="page-content">
				<div class="page-header">
					<h1>设置任务时间点</h1>
				</div>
				<div class="page-content-area">
					<div class="row">
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
							<form name="form" method="post" cssClass="form-horizontal"
								role="form" action="init${suffix}" id="form"
								modelAttribute="config">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">时间：</label>
									<div class="col-sm-5 input-group">
										<input name="start" placeholder="时间，整数"
											class="form-control date-picker"
											value="<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">是否开始：</label>

									<div class="col-sm-5 input-group">
										<label> <input type="checkbox" name="run" id="run"
											class="ace ace-switch ace-switch-4 btn-flat" /> <span
											class="lbl"></span></label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">结束任务：</label>

									<div class="col-sm-5 input-group">
										<label> <input type="checkbox" name="stop" id="stop"
											class="ace ace-switch ace-switch-4 btn-flat" /> <span
											class="lbl"></span></label>
									</div>
								</div>

								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-info" type="submit">
											<i class="ace-icon fa fa-check bigger-110"></i> 提交
										</button>
										&nbsp; &nbsp; &nbsp;
										<button class="btn" type="reset">
											<i class="ace-icon fa fa-undo bigger-110"></i> 重置
										</button>
									</div>
								</div>
							</form>
							<!-- /.col -->
						</div>
						<!-- /.row -->
					</div>
				</div>
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->
		<%@include file="../footer.jsp"%>
	</div>
	<!-- /.main-container -->

	<%@include file="../bottom.jsp"%>

	<script src="/js/date-time/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript">
		$('.date-picker').datetimepicker({
			autoclose : true,
			calendarWeeks : false,
			clearBtn : false,
			daysOfWeekDisabled : [],
			forceParse : true,
			format : 'yyyy-mm-dd',
			keyboardNavigation : true,
			language : 'cn',
			minViewMode : 0,
			startView : 2,
			minView : 2,
			todayBtn : false,
			todayHighlight : false
		});
	</script>

</body>
</html>
