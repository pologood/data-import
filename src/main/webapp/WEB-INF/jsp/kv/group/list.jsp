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
					<h1>字典分类列表</h1>
				</div>
				<div class="page-content-area">
					<div class="row">
						<table class="table table-striped table-bordered table-hover"
							role="grid">
							<thead>
								<tr role="row">
									<th>ID</th>
									<th>分类名称</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${data.content}" var="entity">
									<tr role="row">
										<td>${entity.id}</td>
										<td>${entity.name}</td>
										<td>
											<div class="hidden-sm hidden-xs action-buttons">
												<a class=" btn btn-xs btn-success"
													href="/kv/group/edit${suffix}?id=${entity.id}">修改 </a> <a
													class=" deleteWarn btn btn-xs btn-success"
													href="/kv/group/delete${suffix}?id=${entity.id}&page=${data.number}">删除
												</a> <a class="red  btn btn-xs btn-primary"
													href="/kv/entity/add${suffix}?kVGroup.id=${entity.id}">添加字典</a>
												<a class="red  btn btn-xs btn-success"
													href="/kv/entity/list${suffix}?kVGroup.id=${entity.id}">字典列表</a>
												<a class="btn btn-xs btn-success"
													href="/xls/upload${suffix}?group.id=${entity.id}">上传数据</a>
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
