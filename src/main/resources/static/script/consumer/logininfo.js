async function getLogininfo() {
	await $.ajax({
		url: "/consumer/getloginuserinfo",
		success: function(data) {
			const $header = "#headerlogin"
			if(data.result == "비회원"){
				const $div1 = $("<div>").attr("id", "header_div1").appendTo($header)
					$("<button>").text("로그인").attr("id", "login").attr("class","btn btn-light").appendTo($div1);
				const $div2 = $("<div>").attr("id", "header_div2").appendTo($header)
					$("<button>").text("회원가입").attr("id", "join").attr("class","btn btn-light").appendTo($div2);
				
			} else {				
				$("#headerLogin").remove();
				const $div1 = $("<div>").attr("id", "header_div1").appendTo($header)
					$("<span>").text(data.result).attr("id", "mypage").appendTo($div1);
				const $div2 = $("<div>").attr("id", "header_div2").appendTo($header)	
					$("<button>").text("로그아웃").attr("id", "logout").attr("class","btn btn-light").appendTo($div2);
			}
		}
	})

}


$(document).on("click", "#logout", function() {
	$("<form>").attr("id", "logoutform").attr("action", "/consumer/logout").attr("method", "post").appendTo("#header");
	$("#logoutform").submit();	
})

$(document).on("click", "#mypage", function() {

	location.href = "/consumer/consumer/consumerinfo";
})

$(document).on("click", "#login", function() {
	
	location.href = "/consumer/consumer/login";
})	

$(document).on("click", "#join" ,function() {
	location.href = "/consumer/consumer/join"
}) 

$(document).ready(async function(){
	await getLogininfo();
})
