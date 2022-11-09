	$(document).ready(function(){
		
		$('#userCid').on("keyup", function() {
			const val = $(this).val().toUpperCase()
			$(this).val(val)
		})
		
		$(document).on("keyup",'#pwuserid', function() {
			const val = $(this).val().toUpperCase()
			$(this).val(val)
		})
		
		$("#sellerjoin").click(function() {
			location.href = "/seller/seller/sellerjoin"
		})
		
		$("#finduserid").click(function(){
			const $infodiv = "#findinfo"
			
			const $div = $("<div>").attr("id", "findinfoval").appendTo($infodiv)
			$("<p>").text("가입시 등록한 이메일을 입력해주세요").appendTo($div)
			$("<input>").attr("id", "email").appendTo($div)
			$("<button>").text("아이디 찾기").attr("id", "findidbtn").appendTo($div)
			
			$("#findmodal").fadeIn();
		})
		
		$(document).on("click", "#closeButton", function(){
			$("#findinfoval").remove();
			$("#findmodal").fadeOut();
		})
		
		$(document).on("click", "#findidbtn", function(){
			$.ajax({
				url : "/consumer/findbyid",
				data : {cEmail : $("#email").val()},
				success : function(data){
					alert(data.result)
					$("#findinfoval").remove();
					$("#findmodal").fadeOut();
				}
			})
		})
	
		$("#finduserpw").click(function(){
			const $infodiv = "#findinfo"
			
			const $div = $("<div>").attr("id", "findinfoval").appendTo($infodiv)
			$("<p>").text("가입시 등록한 아이디 입력해주세요").appendTo($div)
			$("<input>").attr("id", "pwuserid").appendTo($div)
			$("<p>").text("가입시 등록한 이메일을 입력해주세요").appendTo($div)
			$("<input>").attr("id", "pwemail").appendTo($div)
			$("<br>").appendTo($div)
			$("<button>").text("비밀번호 재발급").attr("id", "findpwbtn").appendTo($div)
			
			$("#findmodal").fadeIn();
		})		
		
		$(document).on("click", "#findpwbtn" , function(){
			$.ajax({
				url : "/consumer/changenewpw",
				data : {cId : $("#pwuserid").val(), cEmail : $("#pwemail").val()},
				method : "put",
				success : function(data){
					alert(data.result)
					$("#findinfoval").remove();
					$("#findmodal").fadeOut();
				}
			})
		})
	})	