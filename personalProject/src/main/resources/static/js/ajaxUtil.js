/**
 * Ajax 공통 모듈
 */
AjaxUtil = {
	ajax : function(type, url, data, callback) {
		$.ajax({
			url		: url,
			method	: type,
			data	: data,
			dataTyoe 	: 'json',
			contentType	: false,
			processData	: false,
			success	: function(res, result, xhr) {
				if(xhr.status == '200') {
					return callback(res, xhr);
				}
			},
			error	: function(xhr, status, error) {
				//if(xhr.responseText != null) alert(JSON.parse(request.responseText).reason);
				console.log('status ' , status)
				console.log('error ' , error)
				console.log('xhr ' , xhr)
			}
		});
	}
}
