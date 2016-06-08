<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../commons/header.jsp"%>
<%@include file="../../top.jsp"%>
</head>
<body class="no-skin">
	<%@include file="../../header.jsp"%>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed');
			} catch (e) {
			}
		</script>
		<%@include file="../../left.jsp"%>
		<div class="main-content">
			<%@include file="../../breadcrumb.jsp"%>
			<div class="page-content">
				<div class="page-header">
					<h1>添加字典分类</h1>
				</div>
				<div class="page-content-area">
					<div class="row">
						<div class="col-xs-12">
							<form:form name="form" method="post" cssClass="form-horizontal"
								role="form" action="/kv/group/add${suffix}" id="form"
								modelAttribute="kVGroup">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1">分类名称：</label>
									<div class="col-sm-5 input-group">
										<form:input path="name" id="name" placeholder="角色名字"
											cssClass="form-control" />
									</div>
									<form:errors path="name"
										cssClass="label label-xlg label-danger">
									</form:errors>
								</div>
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-info" type="submit">
											<i class="ace-icon fa fa-check bigger-110"></i> 提交
										</button>
										<form:hidden path="id" />
										&nbsp; &nbsp; &nbsp;
										<button class="btn" type="reset">
											<i class="ace-icon fa fa-undo bigger-110"></i> 重置
										</button>
									</div>
								</div>
							</form:form>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->

				</div>
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->
		<%@include file="../../footer.jsp"%>
	</div>
	<!-- /.main-container -->
	<%@include file="../../bottom.jsp"%>