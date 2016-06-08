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
					<h1>添加字典对应</h1>
				</div>
				<div class="page-content-area">
					<div class="row">
						<div class="col-xs-12">
							<form:form name="form" method="post" cssClass="form-horizontal"
								role="form" action="/kv/entity/add${suffix}" id="form"
								modelAttribute="kVEntity">

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">类型：</label>
									<div class="col-sm-5 input-group">
										<input value="${kVEntity.kVGroup.name}" type="text"
											class="form-control" readonly />
										<form:hidden path="kVGroup.id" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">巧达ID：</label>
									<div class="col-sm-5 input-group">
										<form:input path="qdId" id="qdId" placeholder="巧达ID"
											cssClass="form-control" />
									</div>
									<form:errors path="qdId"
										cssClass="label label-xlg label-danger">
									</form:errors>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">巧达名称：</label>
									<div class="col-sm-5 input-group">
										<form:input path="qdName" id="qdName" placeholder="英才名称"
											cssClass="form-control" />
									</div>
									<form:errors path="qdName"
										cssClass="label label-xlg label-danger">
									</form:errors>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">英才ID：</label>
									<div class="col-sm-5 input-group">
										<form:input path="ycId" id="ycId" placeholder="角色名字"
											cssClass="form-control" />
									</div>
									<form:errors path="ycId"
										cssClass="label label-xlg label-danger">
									</form:errors>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">英才名称：</label>
									<div class="col-sm-5 input-group">
										<form:input path="ycName" id="ycName" placeholder="英才名称"
											cssClass="form-control" />
									</div>
									<form:errors path="ycName"
										cssClass="label label-xlg label-danger">
									</form:errors>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">设置为默认：</label>
									<div class="col-sm-5 input-group">
										<label> <input type="checkbox" name="root" id="root"
											class="ace ace-switch ace-switch-4 btn-flat"
											<c:if test="${kVEntity.root==1}">checked="checked"</c:if>>
											<span class="lbl"></span></label>
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