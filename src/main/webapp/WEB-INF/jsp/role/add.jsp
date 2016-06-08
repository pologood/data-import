<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../commons/header.jsp"%>
<%@include file="../top.jsp"%>
<%@include file="../commons/select-css.jsp"%>
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
					<h1>添加管理员角色</h1>
				</div>
				<div class="page-content-area">
					<div class="row">
						<div class="col-xs-12">
							<form:form name="form" method="post" cssClass="form-horizontal"
								role="form" action="add${suffix}" id="form"
								modelAttribute="role">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1">角色名称：</label>
									<div class="col-sm-5 input-group">
										<form:input path="name" id="name" placeholder="角色名字"
											cssClass="col-xs-12" />
									</div>
									<form:errors path="name"
										cssClass="label label-xlg label-danger">
									</form:errors>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1-1">权限设置：</label>
									<div class="col-sm-5 input-group">
										<input id="menus" class="form-control" placeholder="请选择权限" />
										<form:errors path="menus"
											cssClass="label label-xlg label-danger">
										</form:errors>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1-1">角色说明：</label>

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
						</div>
						<!-- /.col -->
					</div>
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
	<script type="text/javascript" src="/js/minds/popovermultiselect.js"></script>
	<script type="text/javascript">
		jQuery(function($) {
			var menus = ${jsonMenu};
			$("#menus").popovermultiselect({
				title : '选择用户权限',
				data : menus,
				select :${selector},
				key : {
					parentName : 'uri',
					parentValue : 'name',
					child : 'childs',
					childName : 'uri',
					childValue : 'name',
					parentCheckboxName : 'menus',
					childCheckboxName : 'menus'
				}
			});
		});
	</script>
</body>
</html>
