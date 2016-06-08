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

						<form name="form" method="get" cssClass="form-horizontal"
							role="form" action="coid${suffix}" id="form">
							<input name="cvId" type="text" value="${cvId}" />
							<button class="btn btn-sm btn-info" type="submit">提交</button>
						</form>

						<h4 class="header">合作ID</h4>
						<well> <c:forEach items="${data}" var="entity">
							${entity}
							<br />
						</c:forEach> </well>
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
