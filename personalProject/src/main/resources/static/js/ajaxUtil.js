/**
 * Ajax 공통 모듈
 */
AjaxUtil = {
	get : function( url, data, callback) {
		$.ajax({
			url		: url,
			method	: 'get',
			data	: data,
			contentType:'application/json;charset=utf-8',
			dataTyoe 	: 'json',
			success	: function(res, result, xhr) {
				if(xhr.status == '200') {
					return callback(res, xhr);
				}
			},
			error	: function(xhr, status, error) {
				AjaxUtil.error(xhr, status, error);
			}
		});
	},


	post : function(url, data, callback) {
		$.ajax({
			url		: url,
			method	: 'post',
			data	: data,
			contentType:'application/json;charset=utf-8',
			dataTyoe 	: 'json',
			success	: function(res, result, xhr) {
				if(xhr.status == '200' || xhr.status == '201') {
					return callback(res, xhr);
				} else {
					AjaxUtil.error(xhr, status, error);
				}
			},
			error	: function(xhr, status, error) {
				AjaxUtil.error(xhr, status, error);
			}
		});
	},


	put : function(url, data, callback) {
		$.ajax({
			url		: url,
			method	: 'put',
			data	: data,
			contentType:'application/json;charset=utf-8',
			dataTyoe 	: 'json',
			success	: function(res, result, xhr) {
				if(xhr.status == '200') {
					return callback(res, xhr);
				} else {
					AjaxUtil.error(xhr, status, error);
				}
			},
			error	: function(xhr, status, error) {
				AjaxUtil.error(xhr, status, error);
			}
		});
	},

	delete : function(url, data, callback) {
		$.ajax({
			url		: url,
			method	: 'delete',
			data	: data,
			contentType:'application/json;charset=utf-8',
			dataTyoe 	: 'json',
			success	: function(res, result, xhr) {
				if(xhr.status == '200') {
					return callback(res, xhr);
				} else {
					AjaxUtil.error(xhr, status, error);
				}
			},
			error	: function(xhr, status, error) {
				AjaxUtil.error(xhr, status, error);
			}
		});
	},


	file : function(type, url, data, callback) {
		$.ajax({
			url		: url,
			method	: type,
			data	: data,
			contentType	: false,
			processData	: false,
			success	: function(res, result, xhr) {
				if(xhr.status == '200') {
					return callback(res, xhr);
				} else {
					AjaxUtil.error(xhr, status, error);
				}
			},
			error	: function(xhr, status, error) {
				AjaxUtil.error(xhr, status, error);
			}
		});
	},


	error : function(xhr, status, error) {
		if (xhr.status === 0) {
                alert('Not connect.\n Verify Network.');
        }

        else if (xhr.status == 400) {
        	alert('Server understood the request, but request content was invalid. [400]');
        }

        else if (xhr.status == 401) {
        	alert('Unauthorized access. [401]');
        }

        else if (xhr.status == 403) {
        	alert('Forbidden resource can not be accessed. [403]');
        }

        else if (xhr.status == 404) {
            alert('Requested page not found. [404]');
        }

        else if (xhr.status == 500) {
            alert('Internal server error. [500]');
        }

        else if (xhr.status == 503) {
        	alert('Service unavailable. [503]');
        }

        else {
            alert('Uncaught Error.n' + xhr.responseText);
        }
	}
}
