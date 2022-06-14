/**
 * Ajax 공통 모듈
 */
function ajax(type, url, data, callback) {
	$.ajax({
		url		: url,
		method	: type,
		data	: data,
		dataTyoe 	: 'json',
		contentType	: false,
		processData	: false,
		success	: function(res, result, xhr) {
			return callback(res, result);
		},

		error	: function(xhr, status, error) {
			console.log('status ' + status)
			console.log('error ' + error)
			console.log('xhr ' + xhr)
		}
	})
}