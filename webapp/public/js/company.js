$(document).ready(() => {

    const authToken = Cookies.get('authToken');
    
    setInterval(function() {
    	console.log("pinged");
        $.ajax("/ping", {
			type: 'post',
			data: {},
			success: function() {
	            
			},
			error: function() {
	            window.location.href = "/login";
			}
	    });
    }, 10000);
});
