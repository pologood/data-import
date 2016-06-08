<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="/js/date-time/jquery-dateFormat.min.js"></script>

<script type="text/javascript">
	$(".format_long_date").each(function() {
		var cur = $(this)
		var val = cur.text();
		if (val.length > 10) {
			cur.text($.format.date(parseInt(val), "yyyy-MM-dd HH:mm:ss"));
		} else {
			cur.text("")
		}
	});
</script>