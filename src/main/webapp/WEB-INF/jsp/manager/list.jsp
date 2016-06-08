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
	<%@include file="../commons/detail.jsp"%>
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
					<h1>用户列表</h1>
				</div>
				<div class="page-content-area">
					<div class="row">

						<div class="col-xs-11">
							<div class="table-header">用户列表</div>
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr role="row">
										<th>名称</th>
										<th>登陆名</th>
										<th>角色</th>
										<th>说明</th>
										<th>添加时间</th>
										<th rowspan="1" colspan="1">操作</th>
										<th class="hide"></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${data.content}" var="entity">
										<tr role="row" class="data_model">
											<td>${entity.name}</td>
											<td>${entity.username}</td>
											<td><c:if test="${entity.root==true}">超级用户</c:if>
												${entity.role.name}</td>
											<td>${entity.note}</td>
											<td><fmt:formatDate value="${entity.createTime}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>
												<div class="hidden-sm hidden-xs action-buttons">
													<a class="green"
														href="/chr-page-manager/admin/edit${suffix}?id=${entity.id}">
														<i class="ace-icon fa fa-pencil bigger-130"></i>
													</a> <a class="red deleteWarn"
														href="/chr-page-manager/admin/delete${suffix}?id=${entity.id}">
														<i class="ace-icon fa fa-trash-o bigger-130"></i>
													</a>
												</div>
											</td>

											<td class="hide">

												<div class="profile-user-info profile-user-info-striped">
													<div class="profile-info-row">
														<div class="profile-info-name">名称</div>
														<div class="profile-info-value">
															<span class="editable">${entity.name}</span>
														</div>
														<div class="profile-info-name">用户名</div>
														<div class="profile-info-value">
															<span class="editable">${entity.username}</span>
														</div>
													</div>
													<div class="profile-info-row">
														<div class="profile-info-name">超级用户</div>
														<div class="profile-info-value">
															<span class="editable">${entity.root}</span>
														</div>
														<div class="profile-info-name">添加时间</div>
														<div class="profile-info-value">
															<span class="editable"><fmt:formatDate
																	value="${entity.createTime}"
																	pattern="yyyy-MM-dd HH:mm:ss" /></span>
														</div>
													</div>
													<div class="profile-info-row">
														<div class="profile-info-name">角色名</div>
														<div class="profile-info-value">
															<span class="editable">${entity.role.name}</span>
														</div>
														<div class="profile-info-name">备注</div>
														<div class="profile-info-value">
															<span class="editable">${entity.note}</span>
														</div>
													</div>
												</div>
												<div class="profile-user-info profile-user-info-striped"
													style="border-top: 0px">
													<div class="profile-info-row">
														<div class="profile-info-name">权限列表</div>
														<div class="profile-info-value">
															<ol>
															</ol>

														</div>
													</div>
												</div>
												<p>&nbsp;</p>
												<p>&nbsp;</p>
												<p>&nbsp;</p>
												<p>&nbsp;</p>
												<p>&nbsp;</p>
												<p>&nbsp;</p>
												<p>&nbsp;</p>
												<p>&nbsp;</p>
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
			$(".data_model").dblclick(function(e) {
				$(".modal-body").html($(this).find(".hide").html());
				$('#myModal').modal('show');
			})
		</script>
</body>
</html>
