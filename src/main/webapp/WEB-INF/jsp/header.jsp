<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="navbar" class="navbar navbar-default">
	<script type="text/javascript">
		try {
			ace.settings.check('navbar', 'fixed')
		} catch (e) {
		}
	</script>

	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left">
			<a href="/index" class="navbar-brand"> <small> <img
					src="/img/logo.gif" height="22" /> ${systemName}
			</small>
			</a>
		</div>
		<c:if test="${login_manager!=null}">
			<div class="navbar-buttons navbar-header pull-right">
				<ul class="nav ace-nav">
					<li class="light-blue"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <i
							class="ace-icon fa fa-user"></i> <span>欢迎你：${login_manager.username}</span>
							<i class="ace-icon fa fa-caret-down"></i>
					</a>
						<ul
							class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li><a href="/logout"> <i
									class="ace-icon fa fa-power-off"></i> 退出登录
							</a></li>
						</ul></li>
				</ul>
			</div>
		</c:if>
	</div>
</div>