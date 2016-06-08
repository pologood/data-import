<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>
		<%@include file="../../left.jsp"%>
		<div class="main-content">
			<%@include file="../../breadcrumb.jsp"%>
			<div class="page-content">
				<div class="page-header">
					<h1>字典对应列表</h1>
				</div>
				<div class="page-content-area">
					<div class="row">
						<table class="table table-striped table-bordered">
							<thead>
								<tr role="row">
									<th>巧达ID</th>
									<th>巧达名称</th>
									<th>英才ID</th>
									<th>英才名称</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${data.content}" var="entity">
									<tr role="row">
										<td>${entity.qdId}</td>
										<td>${entity.qdName}</td>
										<td>${entity.ycId}</td>
										<td>${entity.ycName}</td>
										<td>
											<div class="hidden-sm hidden-xs action-buttons">
												<a class="green"
													href="/kv/entity/edit${suffix}?id=${entity.id}"> <i
													class="ace-icon fa fa-pencil bigger-130"></i>
												</a> <a class="red deleteWarn"
													href="/kv/entity/delete${suffix}?id=${entity.id}&page=${data.number}">
													<i class="ace-icon fa fa-trash-o bigger-130"></i>
												</a>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<%@include file="../../commons/slice.jsp"%>
					</div>
				</div>
			</div>
			<!-- /.page-content-area -->
		</div>
		<!-- /.main-content -->
		<%@include file="../../footer.jsp"%>
	</div>
	<!-- /.main-container -->
	<%@include file="../../bottom.jsp"%>

	<script type="text/javascript">
		$(".deleteWarn").click(function() {
			return deleteAlert($(this));
		})
	</script>

</body>
</html>
