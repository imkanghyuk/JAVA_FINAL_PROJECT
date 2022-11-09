	function passwordCheck() { 
		$("#pwcheck").text("");

		const pattern = /^(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$/;
		const $password = $("#newpassword").val();
		const result = pattern.test($password);
		if (result == false)
			$("#pwcheck").text("비밀번호는 영숫자와 특수문자를 반드시 하나 포함한 8~10자입니다");
		return result;
	}

	$(document).ready(function(){
		const imgurl = "https://iciasmartframe2.s3.ap-northeast-2.amazonaws.com/upload/consumer/profile/";
		$.ajax({
			url : "/consumer/readconsumer",
			success : function(data){
				const result = data.result;
				if(result.cprofileImg != "" && result.cprofileImg != null){
					$("#myprofile").attr("src",imgurl + result.cprofileImg)
				}	
				$("#mynickname").text(result.cnickname)
				$("#myemail").text(result.cemail)
				
			}
		})
		
		$("#updateimgbutton").click(function(){
			
			const formdata = new FormData($("#updateimgform")[0]);
			
			$.ajax({
				url : '/consumer/update',
				data : formdata,
				method : 'put',
			    processData: false,    
			    contentType: false,
				success : function(data){
					$("#myprofile").attr("src",imgurl + data.result.cprofileImg)
				}
			})
		})
		
		
		$(document).on("click", "#updatepw", function(){
			
			if($("#newpwdiv").attr("value")=="show"){
				
				$("#updatepw").text("창닫기")
				
				const $div = $("<div>").attr("id", "pwformdiv").appendTo("#newpwdiv");
				const $form = $("<form>").attr("id","updatepwform").appendTo($div);
				$("<input>").attr("name","cPassword").attr("type","password").attr("id", "newpassword").attr("placeholder","변경할 비밀번호를 입력하세요").appendTo($form)
				$("<p>").attr("id", "pwcheck").appendTo($form)
				$("<button>").attr("id","pwupdatebutton").attr("class","btn btn-danger").text("변경하기").appendTo($div)
				
				$("#newpwdiv").attr("value","hide");
			}
			
			else if($("#newpwdiv").attr("value")=="hide"){
				$("#pwformdiv").remove();
				$("#newpwdiv").attr("value","show");
				$("#updatepw").text("비밀번호 변경")
			}
		})
		
		$(document).on("click", "#pwupdatebutton", function(e){
			e.stopPropagation();
			if(passwordCheck()){
				const formdata = $("#updatepwform").serialize();
				
				$.ajax({
					url : '/consumer/update',
					data : formdata,
					method : 'put',
					success : function(data){
						alert("비밀번호 변경 완료")
					}
				})
			} else {
				alert("비밀번호를 확인해 주세요")
			}
		})
		
		$(document).on("click", "#updatednickname", function(){
			
			if($("#newnickdiv").attr("value")=="show"){
			
				$("#updatednickname").text("창닫기")
				
				const $div = $("<div>").attr("id", "nickformdiv").appendTo("#newnickdiv");
				const $form = $("<form>").attr("id","updatenickform").appendTo($div);
				$("<input>").attr("name","cNickname").attr("id", "newnickname").attr("placeholder","변경할 닉네임을 입력하세요").appendTo($form)
				$("<button>").attr("id","nickupdatebutton").attr("class","btn btn-danger").text("변경하기").appendTo($div)
				
				$("#newnickdiv").attr("value","hide");
				
			}
			else if($("#newnickdiv").attr("value")=="hide"){
				$("#nickformdiv").remove();
				$("#newnickdiv").attr("value","show");
				$("#updatednickname").text("변경하기")
			}
		})
		
		$(document).on("click", "#nickupdatebutton", function(e){
			e.stopPropagation();
			const formdata = $("#updatenickform").serialize();
			
			$.ajax({
				url : '/consumer/update',
				data : formdata,
				method : 'put',
				success : function(data){
					$("#mynickname").text(data.result.cnickname)
				}
			})
		})
		
		$(document).on("click", "#updateemail", function(){
			
			if($("#newmaildiv").attr("value")=="show"){
				
				$("#updateemail").text("창닫기")
				
				const $div = $("<div>").attr("id", "mailformdiv").appendTo("#newmaildiv");
				const $form = $("<form>").attr("id","updatemailform").appendTo($div);
				$("<input>").attr("name","cEmail").attr("id","newemail").attr("placeholder","변경할 이메일을 입력하세요").appendTo($form)
				$("<button>").attr("id","mailupdatebutton").attr("class","btn btn-danger").text("변경하기").appendTo($div)
				
				$("#newmaildiv").attr("value","hide");
				
			}
			else if($("#newmaildiv").attr("value")=="hide"){
				$("#mailformdiv").remove();
				$("#newmaildiv").attr("value","show");
				$("#updateemail").text("변경하기")
			}
		})
		
		$(document).on("click", "#mailupdatebutton", function(e){
			e.stopPropagation();
			const formdata = $("#updatemailform").serialize();
			
			$.ajax({
				url : '/consumer/update',
				data : formdata,
				method : 'put',
				success : function(data){
					$("#myemail").text(data.result.cemail)
				}
			})
		})
		
		$(document).on("blur", "#newpassword", function(){ 
			if(passwordCheck()) {
				$("#pwcheck").text("");
			}	
		})
	})