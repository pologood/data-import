<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal fade" id="popover-modal" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="popover-modal-title">地址详细信息</h4>
			</div>
			<div class="modal-body" id="popover-modal-body"></div>
			<p />
		</div>
	</div>
</div>

<script type="text/javascript">
	$.popoverModal = function(data) {
		var html = '<div class="profile-user-info profile-user-info-striped">';
		var content = data.content;
		$(data.model)
				.each(
						function(index) {
							html += '<div class="profile-info-row">';
							var model = data.model[index];
							$(model)
									.each(
											function(i) {
												var m = model[i];
												html += ' <div class="profile-info-name">'
														+ m.label
														+ '</div> <div class="profile-info-value">  <span>'
												if (m.handle) {
													html += m
															.handle(content[m.key])
												} else {
													html += content[m.key]
												}
												html += '  </span></div>'
											});
							html += '</div>';
						});
		html += '</div>';

		$("#popover-modal-title").text(data.title);
		$("#popover-modal-body").html(html);
		$('#popover-modal').modal('show');
	}
</script>

