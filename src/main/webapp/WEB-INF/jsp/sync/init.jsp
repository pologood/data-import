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
					<h1>同步程序</h1>
				</div>
				
				<div class="page-content-area">
					<div class="row">
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
							<form name="form" method="post" cssClass="form-horizontal"
								role="form" 
								<c:choose>
									<c:when test="${isStart}">
									action="/sync/stop${suffix}"
									</c:when>
									<c:otherwise>
      								action="/sync/start${suffix}"   
    								</c:otherwise>
								</c:choose>
								 id="form"
								modelAttribute="config">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">线程休息时间(毫秒)：</label>
									<div class="col-sm-5 input-group">
										<input name="intertime" placeholder="线程休息时间"
											class="form-control date-picker"
											value="${intertime}" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">开始位置：</label>
									<div class="col-sm-5 input-group">
										<input name="startIndex" placeholder="开始位置"
											class="form-control date-picker"
											value="${startIndex}" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">每页条数：</label>
									<div class="col-sm-5 input-group">
										<input name="limit" placeholder="每页条数"
											class="form-control date-picker"
											value="${limit}" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">是否开始：</label>

									<div class="col-sm-5 input-group">
										<label> <input type="checkbox" name="run" id="run"
											class="ace ace-switch ace-switch-4 btn-flat" 
											<c:if test="${isStart}">checked="checked"</c:if>
											/> <span
											class="lbl"></span></label>
									</div>
								</div>

								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-info" type="submit" >
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
	<%@include file="../commons/select-js.jsp"%>

</body>
</html>
