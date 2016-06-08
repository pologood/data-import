<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					<h1>角色列表</h1>
				</div>
				<div class="page-content-area">
					<div class="row">
						<div class="table-header">配置列表</div>
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr role="row">
									<th>名称</th>
									<th>值</th>
									<th>说明</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${data.content}" var="entity">
									<tr role="row">
										<td>${entity.name}</td>
										<td>${entity.value}</td>
										<td>${entity.note}</td>
										<td>
											<div class="hidden-sm hidden-xs action-buttons">
												<a class="btn btn-xs btn-warning"
													href="/config/edit${suffix}?id=${entity.id}"> 编辑 </a> <a
													class="btn btn-xs btn-success deleteWarn"
													href="/config/delete${suffix}?id=${entity.id}&page=${data.number}">
													删除</a>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- /.page-content-area -->
		</div>
		<!-- /.main-content -->
		<%@include file="../footer.jsp"%>
	</div>
	<!-- /.main-container -->
	<%@include file="../bottom.jsp"%>

	<script type="text/javascript">
		$(".deleteWarn").click(function() {
			return deleteAlert($(this));
		})
	</script>

</body>
</html>
