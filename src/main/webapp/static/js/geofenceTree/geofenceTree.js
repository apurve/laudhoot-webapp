function checkParent() {
	$("#checkParent").attr("disabled", "disabled");
	$("#childrenView").html('');
	$.ajax({
		url : getContextPath()+"/geofence/fenceable",
		data : {
			geofence : $('#code').val(),
			parent : $('#parent').val()
			}
	})
	.done(function(data) {
		$("#childrenView").html(data);
	})
	.fail(function() {
		enableCheckParent();
	});
}

function enableCheckParent() {
	$("#checkParent").removeAttr("disabled");
	$('#parent').val('');
}