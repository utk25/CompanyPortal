$(document).ready(() => {

	$.ajax("/userInfo", {
		type: 'get',
		data: {},
		success: function() {
            window.location.href = "/userInfo";
		},
		error: function(xhr, textStatus, errorThrown) {
			$("#form").show();
		}
	});



    $("#login").submit((e) => {
    	e.preventDefault();
    	$.ajax("/authenticate", {
    		type: 'post',
    		data: $("#login").serialize(),
    		success: function(res) {
                console.log(res);
    			Cookies.set('authToken', res.authToken)
                window.location.href = "/userInfo";
    		},
    		error: function(xhr, textStatus, errorThrown) {
    			$("#errorMessage").show();
    		}
    	});
    });

});