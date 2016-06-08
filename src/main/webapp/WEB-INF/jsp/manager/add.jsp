<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../commons/header.jsp"%>
<%@include file="../commons/select-css.jsp"%>
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
					<h1>添加用户</h1>
				</div>
				<div class="page-content-area">
					<div class="row">
						<div class="col-xs-12">
							<form:form name="form" method="post" class="form-horizontal"
								role="form" action="add${suffix}" id="form"
								modelAttribute="manager">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1">用户名字：</label>
									<div class="col-sm-5 input-group">
										<form:input path="name" id="name" placeholder="用户名字"
											class="form-control" />
									</div>
									<form:errors path="name"
										cssClass="label label-xlg label-danger">
									</form:errors>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1">登录名称：</label>

									<div class="col-sm-5 input-group">
										<form:input path="username" id="username" placeholder="登录名称"
											class="form-control" />
									</div>
									<form:errors path="username"
										cssClass="label label-xlg label-danger">
									</form:errors>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1">登录密码：</label>

									<div class="col-sm-5 input-group">
										<form:password path="password" id="password"
											placeholder="登录密码" class="form-control" />
									</div>
									<form:errors path="password"
										cssClass="label label-xlg label-danger">
									</form:errors>
								</div>
								<c:if test="${login_manager.root==true}">
									<div class="form-group" id="super">
										<label class="col-sm-3 control-label no-padding-right">是否超级用户：</label>

										<div class="col-sm-5 input-group">
											<label> <input type="checkbox" name="root" id="root"
												class="ace ace-switch ace-switch-4 btn-flat"
												<c:if test="${manager.root==true}">checked="checked"</c:if>>
												<span class="lbl"></span></label>
										</div>
									</div>
								</c:if>
								<div class="form-group" id="roles">
									<label class="col-sm-3 control-label no-padding-right">用户角色：</label>

									<div class="col-sm-5 input-group">
										<select class="chosen-select" name="role.id"
											data-placeholder="请选择一个角色">
											<option />
											<c:forEach items="${roles}" var="role">
												<option
													<c:if test="${manager.role.id==role.id}">selected='selected'</c:if>
													value="${role.id}">${role.name}</option>
											</c:forEach>
										</select>
										<form:errors path="role"
											cssClass="label label-xlg label-danger">
										</form:errors>
									</div>

								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">备注：</label>

									<div class="col-sm-5 input-group">
										<form:textarea path="note"
											class="autosize-transition form-control" />
									</div>
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
									<form:hidden path="id" />
								</div>
							</form:form>
						</div>
					</div>
					<!-- /.col -->
					<!-- /.row -->

				</div>
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->
		<%@include file="../footer.jsp"%>
	</div>
	<!-- /.main-container -->

	<%@include file="../bottom.jsp"%>
	<%@include file="../commons/select-js.jsp"%>

	<script type="text/javascript">
		jQuery(function($) {
			$('.chosen-select').chosen({
				allow_single_deselect : true
			});

			$(window).off('resize.chosen').on('resize.chosen', function() {
				$('.chosen-select').each(function() {
					var $this = $(this);
					$this.next().css({
						'width' : $this.parent().width()
					});
				})
			}).trigger('resize.chosen');

			$("#root").bind("click", function(e) {
				checkBoxSetvalue();
			});

			function checkBoxSetvalue() {
				if ($("#root")[0].checked) {
					$("#roles").hide();
				} else {
					$("#roles").show();
				}
			}
			checkBoxSetvalue();

		});
	</script>

</body>
</html>
