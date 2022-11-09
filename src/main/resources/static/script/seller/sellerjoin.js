
	$(document).ready(async function(){
		
		$("#sellerid").on("blur", function(){
			if (idcheck() == false) {
				return;
			}
			const $username = $('#sellerid').val();
			$.ajax({url : "/seller/idcheck",
			data : {sId: $username},
			success : function(data){
				$('#selleridcheck').text(data.result)	
			}
			})
		})
		
		$("#sellerpw").on("blur", passwordCheck);
		
		$("#sellerPasswordcheck").on("blur", password2Check);
		
		$("#sellerBuis").on("blur", async function(){
			await sellerBuischeck()
		})
		
		$("#sellername").on("blur", namecheck);		
		
		$("#sellerbirth").on("blur", birthcheck);
		
		$("#sellerphone").on("blur", telcheck);
		
		$("#sellermailvaluecheck").click(function (){
			mailcheck()
		})
		
		
		$('#joinseller').click(async function() {
			const c1 = idcheck()
			const c2 = passwordCheck()
			const c3 = password2Check()
			const c4 = await sellerBuischeck()
			const c5 = namecheck()
			const c6 = birthcheck()
			const c7 = telcheck()
			const c8 = mailcheck()
			
			if((c1 && c2 && c3 && c4 && c5 && c6 && c7 && c8) == false) {
				alert("작성내용을 확인해주세요")
				return
			} else {
				
			const param = $('#sellerformdata').serialize();
			const result = await $.ajax({
				url : "/seller/sellerjoin",
				data : param,
				method : 'post',
				success : function(data){
					alert(data.message);
					location.href = "/seller/seller/login";		
				},
			    error : function(data){
			    	console.log(data)
			    	alert("회원가입 실패");
			    }
			})
			
			}
			 
	})
})	

	function idcheck() {
		$('#selleridcheck').text("");
		
		const pattern = /^[A-Z0-9]{6,10}$/;
		const $username = $("#sellerid").val().toUpperCase();
		$('#sellerid').val($username);
		
		const result = pattern.test($username);
		if (result == false) {
			$('#selleridcheck').text("아이디는 대문자와 숫자 6~10자 입니다").attr("class", "faile")
		}
		return result;
	}
	
	function passwordCheck() { 
		$("#sellerpwcheck").text("");

		const pattern = /^(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$/;
		const $password = $("#sellerpw").val();
		const result = pattern.test($password);
		if (result == false)
			$("#sellerpwcheck").text("비밀번호는 영숫자와 특수문자를 반드시 하나 포함한 8~10자입니다");
		return result;
	}
	
	function password2Check() { // 입력한 패스워드 확인 체크
		$("#sellerpwcheck2").text("");
		const $password2 = $("#sellerPasswordcheck").val();
		if ($password2 == "") {
			$("#sellerpwcheck2").text("필수입력입니다");
			return false;
		}
		if ($password2 !== $("#sellerpw").val()) {
			$("#sellerpwcheck2").text("비밀번호가 일치하지 않습니다");
			return false;
		}
		return true;
	}
	
	async function sellerBuischeck() {
		const $sellerBuis = $('#sellerBuis').val();
		if($sellerBuis == ""){
			$('#sellerBuischeck').text("필수입력 값 입니다")
			return false;
		} 
		else{				
			const data = await $.ajax({url : "/seller/sellerBuischeck",
			data : {sBuisnessNum : $sellerBuis}
			})
			$('#sellerBuischeck').text(data.message)
			return data.result
		}
	}
	
	function namecheck() {
		const $username = $('#sellername').val();
		if($username == ""){
			$('#sellernamecheck').text("필수입력 값 입니다")
			return false
		} else {
			$('#sellernamecheck').text("")
			return true
		}
	}
	
	function birthcheck() {
		const $userbirth = $('#sellerbirth').val();
		if($userbirth == ""){
			$('#sellerbirthcheck').text("필수입력 값 입니다")
			return false
		} else {
			$('#sellerbirthcheck').text("")
			return true
		}
	}
	
	function telcheck() {
		const $userphone = $('#sellerphone').val();
		if($userphone == ""){
			$('#sellertelcheck').text("필수입력 값 입니다")
			return false
		} else {
			$('#sellertelcheck').text("")
			return true
		}
	}
	
	function mailcheck() {
		$("#sEmailval").attr("value", $('#sellermail').val() + $("#selectsellermail").val())
		 
		const $usermail = $('#sellermail').val();
		if($usermail == ""){
			$('#sellermailcheck').text("메일은 필수 입력입니다")
			return false
		} else {
			$('#sellermailcheck').text("")
			return true
		}
	}