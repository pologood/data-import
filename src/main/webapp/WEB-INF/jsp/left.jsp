<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="sidebar" class="sidebar   responsive">
	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success" id="bt_page_home">
				<i class="ace-icon fa fa-home"></i>
			</button>

			<button class="btn btn-info" id="bt_page_manager">
				<i class="ace-icon fa fa-user"></i>
			</button>

			<button class="btn btn-warning" id="bt_page_members">
				<i class="ace-icon fa fa-bars"></i>
			</button>

			<button class="btn btn-danger" id="bt_page_chart">
				<i class="ace-icon fa fa-shopping-cart"></i>
			</button>
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span> <span class="btn btn-info"></span>

			<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
		</div>
	</div>
	<!-- /.sidebar-shortcuts -->

	<ul class="nav nav-list">
		<c:forEach items="${menus}" var="menu">
			<li><a href="#" class="dropdown-toggle"><i
					class="menu-icon fa fa-null"></i> <span class="menu-text">${menu.name}
				</span><b class="arrow fa fa-angle-down"></b> </a><b class="arrow"></b>
				<ul class="submenu">
					<c:forEach items="${menu.childs}" var="child">
						<c:choose>
							<c:when test="${child.catalog==true}">
								<li><a href="${child.uri}"> <i
										class="menu-icon fa fa-caret-right"></i> <span
										class="menu-text">${child.name}</span></a><b class="arrow"></b></li>
							</c:when>
							<c:otherwise>
								<li class="hide"><a href="${child.uri}"></a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul></li>
		</c:forEach>
	</ul>
	<!-- /.nav-list -->

	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i class="ace-icon fa fa-angle-double-left"
			data-icon1="ace-icon fa fa-angle-double-left"
			data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>

	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed');
		} catch (e) {
		}
		var left_nav_current = '${currentMenu.uri}';
		var left_nav_node = $(".nav-list li ul li a[href='" + left_nav_current
				+ "']");

		left_nav_node.parent().addClass("active ");
		left_nav_node.parent().parent().parent().addClass("active");
	</script>
</div>

