
	$(document).ready(async function(){
		
		$("#userid").on("blur", function(){
			if (idcheck() == false) {
				return;
			}
			const $username = $('#userid').val();
			$.ajax({url : "/consumer/idcheck",
			data : {cId: $username},
			success : function(data){
				$('#idcheck').text(data.message)	
			}
			})
		})
		
		$("#userpw").on("blur", passwordCheck);
		
		$("#cPasswordcheck").on("blur", password2Check);
		
		$("#usernick").on("blur", async function(){
			await nickcheck()
		})
		
		$("#username").on("blur", namecheck);		
		
		$("#userbirth").on("blur", birthcheck);
		
		$("#userphone").on("blur", telcheck);
		
		$("#mailvaluecheck").click(function (){
			mailcheck()
		})
		
		
		$('#joinconsumer').click(async function() {
			const c1 = idcheck()
			const c2 = passwordCheck()
			const c3 = password2Check()
			const c4 = await nickcheck()
			const c5 = namecheck()
			const c6 = birthcheck()
			const c7 = telcheck()
			const c8 = mailcheck()

			if((c1 && c2 && c3 && c4 && c5 && c6 && c7 && c8) == false) {
				alert("작성내용을 확인해주세요")
				return
			}
			const formdata = new FormData($('#formdata')[0]);
			
			const result = await $.ajax({
				url : "/consumer/join",
				data : formdata,
				method : 'post',
			    processData: false,    
			    contentType: false,
			    error : function(data){
			    	console.log(data)
			    }
			})
			
			alert(result.message);
			location.href = "/consumer/consumer/login";
	})
})	

	function idcheck() {
		$('#idcheck').text("");
		
		const pattern = /^[A-Z0-9]{6,10}$/;
		const $username = $("#userid").val().toUpperCase();
		$('#userid').val($username);
		
		const result = pattern.test($username);
		if (result == false) {
			$('#idcheck').text("아이디는 대문자와 숫자 6~10자 입니다").attr("class", "faile")
		}
		return result;
	}
	
	function passwordCheck() { 
		$("#pwcheck").text("");

		const pattern = /^(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$/;
		const $password = $("#userpw").val();
		const result = pattern.test($password);
		if (result == false)
			$("#pwcheck").text("비밀번호는 영숫자와 특수문자를 반드시 하나 포함한 8~10자입니다");
		return result;
	}
	
	function password2Check() { // 입력한 패스워드 확인 체크
		$("#pwcheck2").text("");
		const $password2 = $("#cPasswordcheck").val();
		if ($password2 == "") {
			$("#pwcheck2").text("필수입력입니다");
			return false;
		}
		if ($password2 !== $("#userpw").val()) {
			$("#pwcheck2").text("비밀번호가 일치하지 않습니다");
			return false;
		}
		return true;
	}
	
	async function nickcheck() {
		const $usernick = $('#usernick').val();
		if($usernick == ""){
			$('#nickcheck').text("필수입력 값 입니다")
			return false;
		} 
		else{				
			const data = await $.ajax({url : "/consumer/nicknamecheck",
			data : {cNickname : $usernick}
			})
			$('#nickcheck').text(data.message)
			return data.result
		}
	}
	
	function namecheck() {
		const $username = $('#username').val();
		if($username == ""){
			$('#namecheck').text("필수입력 값 입니다")
			return false
		} else {
			$('#namecheck').text("")
			return true
		}
	}
	
	function birthcheck() {
		const $userbirth = $('#userbirth').val();
		if($userbirth == ""){
			$('#birthcheck').text("필수입력 값 입니다")
			return false
		} else {
			$('#birthcheck').text("")
			return true
		}
	}
	
	function telcheck() {
		const $userphone = $('#userphone').val();
		if($userphone == ""){
			$('#telcheck').text("필수입력 값 입니다")
			return false
		} else {
			$('#telcheck').text("")
			return true
		}
	}
	
	function mailcheck() {
		$("#cEmailval").attr("value", $('#usermail').val() + $("#selectmail").val())
		 
		const $usermail = $('#usermail').val();
		if($usermail == ""){
			$('#mailcheck').text("메일은 필수 입력입니다")
			return false
		} else {
			$('#mailcheck').text("")
			return true
		}
	}