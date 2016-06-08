<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-footer no-margin-top">
	<ul class="pager pull-left no-margin">
		<li>记录数：${data.numberOfElements}</li>
		<li>第${data.number+1}页</li>
	</ul>
	<ul class="pager pull-right no-margin">
		<c:if test="${!data.first}">
			<li><a href="${queryParam}page=${data.number}">上一页</a></li>
		</c:if>

		<c:if test="${!data.last}">
			<li><a href="${queryParam}page=${data.number+2}">下一页</a></li>
		</c:if>
	</ul>

</div>

