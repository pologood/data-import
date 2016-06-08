<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="commons/header.jsp"%>
<%@include file="top.jsp"%>
</head>
<body class="no-skin">
	<%@include file="header.jsp"%>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>
		<div class="main-content">
			<div class="page-content">
				<div class="page-content-area">
					<div class="row">
						<div class="col-xs-4"></div>
						<div class="col-xs-4 well">
							<c:choose>
								<c:when test="${error!=null}">
									<div class="alert alert-danger">
										<i class="ace-icon fa fa-times"></i> ${error} ！
									</div>
								</c:when>
								<c:otherwise>
									<div class="alert alert-success">
										<i class="ace-icon fa fa-bell"></i> 请输入正确的账号及密码 !
									</div>
								</c:otherwise>
							</c:choose>
							<form:form method="post" class="form-horizontal" action="login"
								id="form" modelAttribute="manager">
								<div class="form-group">
									<label class=" col-sm-3  control-label no-padding-right">登录账号：</label>
									<div class="col-sm-8 input-group">
										<span class="block input-icon input-icon-right"> <form:input
												path="username" id="username" placeholder="登录账号"
												class="form-control" /> <i class="ace-icon fa fa-user"></i>
										</span>
									</div>
								</div>
								<div class="form-group">
									<label class=" col-sm-3  control-label no-padding-right">
										登录密码：</label>
									<div class="col-sm-8 input-group">
										<span class="block input-icon input-icon-right"> <form:password
												path="password" id="password" placeholder="密码"
												class="form-control" /> <i class="ace-icon fa fa-key"></i>
										</span>
									</div>
								</div>
								<br />
								<div class="col-md-offset-2 col-md-10">
									<button class="btn btn-info" type="submit">
										<i class="ace-icon fa fa-check bigger-110"></i> 提交
									</button>
									&nbsp; &nbsp; &nbsp;
									<button class="btn" type="reset">
										<i class="ace-icon fa fa-undo bigger-110"></i> 重置
									</button>
								</div>
							</form:form>
						</div>
						<div class="col-xs-4"></div>
					</div>
					<!-- /.row -->
				</div>

			</div>
			<!-- /.page-content-area -->
		</div>
		<!-- /.page-content -->
	</div>
	<!-- /.main-content -->
	<%@include file="footer.jsp"%>
	</div>
	<!-- /.main-container -->
	<%@include file="bottom.jsp"%>
</body>
</html>
