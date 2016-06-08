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
									<th>简历同步数据</th>
									<th>竞品来源:智联招聘</th>
									<th>竞品来源:前程无忧</th>
									<th>竞品来源:猎聘网</th>
									<th>竞品来源:中华英才</th>
									<th>竞品来源:58同城</th>	
									<th>竞品来源:赶集网</th>	
									<th>竞品来源:拉钩网</th>
									<th>竞品来源:周伯通</th>	
									<th>竞品来源:若邻网</th>			
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${data}" var="entity">
									<tr role="row">
										<td>${entity.date}</td>
										<td>${entity.import_sync}</td>
										<td>${entity.sync_qdsid_1}</td>
										<td>${entity.sync_qdsid_2}</td>
										<td>${entity.sync_qdsid_3}</td>
										<td>${entity.sync_qdsid_4}</td>
										<td>${entity.sync_qdsid_5}</td>
										<td>${entity.sync_qdsid_6}</td>
										<td>${entity.sync_qdsid_7}</td>
										<td>${entity.sync_qdsid_8}</td>
										<td>${entity.sync_qdsid_9}</td>
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
