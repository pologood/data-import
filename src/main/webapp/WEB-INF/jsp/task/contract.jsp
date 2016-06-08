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


				<div class="well">db : ${dbIndex}-- id:${id}</div>

				<form name="form" method="post" cssClass="form-horizontal"
					action="contract${suffix}">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right">数据库：</label>
						<div class="col-sm-5 input-group">
							<input name="db" id="db" placeholder="db" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right">频率：</label>
						<div class="col-sm-5 input-group">
							<input name="step" id="step" placeholder="step"
								cssClass="form-control" value="0" />
						</div>
					</div>
					<div class="clearfix form-actions">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn btn-info" type="submit">
								<i class="ace-icon fa fa-check bigger-110"></i> 提交
							</button>
						</div>
					</div>
				</form>
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->
		<%@include file="../footer.jsp"%>
	</div>
	<!-- /.main-container -->

	<%@include file="../bottom.jsp"%>


</body>
</html>
