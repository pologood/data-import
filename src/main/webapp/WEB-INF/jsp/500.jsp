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
		<%@include file="left.jsp"%>
		<div class="main-content">
			<%@include file="breadcrumb.jsp"%>
			<div class="page-content">

				<div class="page-content-area">500错误了~~</div>
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
