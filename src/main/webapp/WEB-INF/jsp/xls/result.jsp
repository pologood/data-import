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

				<div class="page-content-area">

					<div class="row">
						<div class="col-xs-12">

							<form action="/xls/importData" method="post"
								onsubmit="return confirm('你确定要提交吗')">
								<input type="hidden" name="sheet" value="${xlsUpload.sheet}" />
								<input type="hidden" name="xls" value="${xlsUpload.xls}" /><input
									type="hidden" name="group.id" value="${xlsUpload.group.id}" />
								<button type="submit" class="btn btn-ms btn-warning">导入</button>
							</form>

							<hr />

							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr role="row">
										<th>错误信息</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${result.verifyMessages}" var="message">
										<tr>
											<td>${message}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr role="row">
										<th>值信息</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${result.entitys}" var="entity">
										<tr>
											<td>${entity}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

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
</body>
</html>
