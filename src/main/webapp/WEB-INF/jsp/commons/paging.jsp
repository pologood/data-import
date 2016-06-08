<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal-footer no-margin-top">
	<ul class="pagination pull-left no-margin">
		<li>总记录：${data.totalElements}</li>
		<li>总页数：${data.totalPages}</li>
	</ul>
	<ul class="pagination pull-right no-margin">
		<li class="prev <c:if test="${data.number<=0}">hide</c:if>"><a
			href="${queryParam}page=${data.number}"> <i
				class="ace-icon fa fa-angle-double-left"></i>
		</a></li>
		<c:forEach begin="${data.startPage}" end="${data.endPage}" var="i">
			<li
				<c:if test="${i==(data.number+1)}"> class="active"
                    </c:if>><a
				href="${queryParam}page=${i}">${i}</a></li>
		</c:forEach>
		<li
			class="next <c:if test="${data.number>=data.totalPages-1}">hide</c:if>"><a
			href="${queryParam}page=${data.number+2}"> <i
				class="ace-icon fa fa-angle-double-right"></i>
		</a></li>
	</ul>
</div>