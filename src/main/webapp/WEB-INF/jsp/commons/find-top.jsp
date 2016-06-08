<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="search-div-height">
	<div class="col-xs-2 pull-left">
		<div class="input-group input-group-sm">
			<span class="input-group-addon">用户ID ：</span>
			<form:input maxlength="12" id="id" path="id" cssClass="form-control"
				placeholder="用户ID" />
		</div>
	</div>
	<div class="col-xs-2 pull-left">
		<div class="input-group input-group-sm">
			<span class="input-group-addon">用户名 ：</span>
			<form:input id="userName" path="userName" cssClass="form-control"
				placeholder="登录账号" />
		</div>
	</div>
	<div class="col-xs-2 pull-left">
		<div class="input-group input-group-sm">
			<span class="input-group-addon">电子邮件 ：</span>
			<form:input id="email" path="email" cssClass="form-control"
				placeholder="电子邮件" />
		</div>
	</div>
	<div class="col-xs-2 pull-left">
		<div class="input-group input-group-sm">
			<span class="input-group-addon">电话 ：</span>
			<form:input id="mobile" path="mobile" cssClass="form-control"
				placeholder="电话号码" />
		</div>
	</div>

	<div class="col-xs-1 pull-left">
		<div class="input-group input-group-sm">
			<button class="btn btn-info" type="submit" name="formSubmit"
				id="formSubmit" value="formSubmit">
				<i class="ace-icon fa fa-check bigger-110"></i> 查询
			</button>
		</div>
	</div>
</div>