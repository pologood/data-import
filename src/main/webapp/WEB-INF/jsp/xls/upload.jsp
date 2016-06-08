<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="../commons/header.jsp"%>
<%@include file="../top.jsp"%>
</head>
<body class="no-skin">
	<%@include file="../header.jsp"%>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>
		<%@include file="../left.jsp"%>
		<div class="main-content">
			<%@include file="../breadcrumb.jsp"%>
			<div class="page-content">
				<div class="page-header">
					<h1>上传字典excell</h1>
				</div>
				<div class="page-content-area">
					<div class="row">
						<div class="col-xs-12">
							<form:form name="form" method="post" cssClass="form-horizontal"
								role="form" action="upload${suffix}"
								enctype="multipart/form-data" modelAttribute="xlsUpload">


								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">所属分类：</label>
									<div class="col-sm-5 input-group">
										<input class="form-control" readonly
											value="${xlsUpload.group.name}" />
										<form:hidden path="group.id" />
									</div>
								</div>


								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1-1"><span class="red">*</span>上传excell：</label>
									<div class="col-sm-5 input-group">
										<input type="file" name="xls" id="xls">
										<form:errors path="xls"
											cssClass="label label-xlg label-danger" />
									</div>
								</div>

								<div class="form-group abtest_node">
									<label class="col-sm-3 control-label no-padding-right">第几个Sheet：</label>
									<div class="col-sm-5 input-group">
										<form:input path="sheet" class="form-control"
											placeholder="第几个Sheet" />
									</div>
									<form:errors path="sheet"
										cssClass="label label-xlg label-danger">
									</form:errors>
								</div>
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-info" type="submit">
											<i class="ace-icon fa fa-check bigger-110"></i> 上传
										</button>
										&nbsp; &nbsp; &nbsp;
										<button class="btn" type="reset">
											<i class="ace-icon fa fa-undo bigger-110"></i> 重置
										</button>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<!-- /.page-content-area -->
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->
		<%@include file="../footer.jsp"%>
	</div>
	<!-- /.main-container -->
	<%@include file="../bottom.jsp"%>

	<script type="text/javascript">
		var upload_status = [ "你还没有选择文件", "选择文件格式不允许" ];
		var upload_up = [ "上传", "修改" ];
		jQuery(function($) {
			$('input[type=file]')
					.ace_file_input(
							{
								no_file : upload_status[0],
								btn_choose : upload_up[0],
								btn_change : upload_up[1],
								droppable : true,
								thumbnail : 'small',
								before_change : function(files, dropped) {
									var whitelist = [ 'xls' ];
									var match = false;
									for (var i = 0; i < files.length; i++) {
										var f = files[i].name;
										var ext = f
												.substring(
														f.lastIndexOf(".") + 1,
														f.length);
										for (var j = 0; j < whitelist.length; j++) {
											if (whitelist[j] == ext) {
												return true;
											}
										}
									}
									alert("文件格式不匹配，格式只能为:" + "<b>" + whitelist
											+ "</b>")
									return match;
								}
							});
		});
	</script>
</body>
</html>
