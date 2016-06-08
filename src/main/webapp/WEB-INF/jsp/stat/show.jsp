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
				<div class="page-content-area">
					<div class="row">
						<div class="table-header">统计表</div>
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr role="row">
									<th>时间</th>
									<th>ask总数</th>

									<th>ask未推送</th>
									<th>ask未推送-电话匹配</th>
									<th>ask未推送-不匹配</th>
									<th>ask未推送-拒绝</th>

									<th>ask已推送</th>
									<th>ask已推送-电话匹配</th>
									<th>ask已推送-不匹配</th>
									<th>ask已推送-拒绝</th>

									<th>ask未推送异常</th>
									<th>ask已推送异常</th>

									<th>ask拒绝总数</th>

									<th>简历新增</th>
									<th>简历更新</th>
									
									
									<th>简历新增异常</th>
									<th>简历更新异常</th>
									<th>简历解析失败</th>
									<th>获得联系方式</th>
									<th>获得联系方式异常</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${data}" var="entity">
									<tr role="row">
										<td>${entity.date}</td>
										<td>${entity.ask_all}</td>

										<td>${entity.ask_new}</td>
										<td>${entity.ask_new_m}</td>
										<td>${entity.ask_new_no}</td>
										<td>${entity.ask_new_reject}</td>


										<td>${entity.ask_old}</td>
										<td>${entity.ask_old_m}</td>
										<td>${entity.ask_old_no}</td>
										<td>${entity.ask_old_reject}</td>


										<td>${entity.ask_new_error}</td>
										<td>${entity.ask_old_error}</td>

										<td>${entity.ask_old_reject+entity.ask_new_reject}</td>


										<td>${entity.import_new}</td>
										<td>${entity.import_old}</td>
										<td>${entity.import_new_error}</td>
										<td>${entity.import_old_error}</td>
										<td>${entity.import_jx_error}</td>
										<td>${entity.contract_get}</td>
										<td>${entity.contract_error}</td>
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
</body>
</html>
