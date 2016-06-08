<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../commons/header.jsp"%>
<%@include file="../top.jsp"%>
<style type="text/css">
#template, #abtest {
	height: 200px;
	border: 1px solid #CCC;
}
</style>
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
					<h1>添加规则</h1>
				</div>
				<div class="page-content-area">
					<div class="row">
						<div class="col-xs-12">
							<form:form name="form" method="post" cssClass="form-horizontal"
								role="form" action="add${suffix}" id="form"
								modelAttribute="rule">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">名称：</label>
									<div class="col-sm-5 input-group">
										<form:input path="name" id="name" placeholder="名称"
											cssClass="form-control" />
									</div>
									<form:errors path="name"
										cssClass="label label-xlg label-danger">
									</form:errors>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">名称：</label>
									<div class="col-sm-5 input-group">
										<form:textarea path="template" id="template" placeholder="值"
											cssClass="form-control" />
									</div>
									<form:errors path="template"
										cssClass="label label-xlg label-danger">
									</form:errors>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">开启测试：</label>
									<div class="col-sm-5 input-group">
										<label> <input type="checkbox" name="test" id="test"
											class="ace ace-switch ace-switch-4 btn-flat"
											<c:if test="${rule.test==true}">checked="checked"</c:if>>
											<span class="lbl"></span></label>
									</div>
								</div>

								<div class="form-group abtest_node">
									<label class="col-sm-3 control-label no-padding-right">测试模板：</label>
									<div class="col-sm-5 input-group">
										<form:textarea path="abtest"
											class="autosize-transition form-control" placeholder="测试模板" />
									</div>
									<form:errors path="abtest"
										cssClass="label label-xlg label-danger">
									</form:errors>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">说明：</label>
									<div class="col-sm-5 input-group">
										<form:textarea path="note"
											class="autosize-transition form-control" placeholder="用户说明" />
									</div>
									<form:errors path="note"
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

	<script>
		function setSelect() {
			if ($("#test")[0].checked) {
				$(".abtest_node").show(2);
			} else {
				$(".abtest_node").hide(2);
			}
		}
		$("#test").change(function() {
			setSelect();
		});
		setSelect();
	</script>
</body>
</html>
