<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bj58.chr.auth.Menu"%>
<div class="breadcrumbs" id="breadcrumbs">
	<script type="text/javascript">
		try {
			ace.settings.check('breadcrumbs', 'fixed')
		} catch (e) {
		}
	</script>
	<ul class="breadcrumb">
		<li><i class="ace-icon fa fa-home home-icon"></i> <a
			href="/index.do">控制面板</a></li>
		<%
			Menu gmenu = (Menu) request.getAttribute("currentMenu");
			StringBuilder breadcrumb = new StringBuilder(100);
			while (gmenu != null) {
				StringBuilder builder = new StringBuilder();
				builder.append("<li><a>").append(gmenu.getName()).append("</a></li>");
				if (breadcrumb != null) {
					builder.append(breadcrumb);
				}
				breadcrumb = builder;
				gmenu = gmenu.getParent();
			}
		%>
		<%=breadcrumb%>
	</ul>
	<!-- 	<div class="nav-search" id="nav-search"> -->
	<!-- 		<form class="form-search"> -->
	<!-- 			<span class="input-icon"> <input type="text" -->
	<!-- 				placeholder="查询 ..." class="nav-search-input" id="nav-search-input" -->
	<!-- 				autocomplete="off" /> <i -->
	<!-- 				class="ace-icon fa fa-search nav-search-icon"></i> -->
	<!-- 			</span> -->
	<!-- 		</form> -->
	<!-- 	</div> -->
	<!-- /.nav-search -->
</div>

