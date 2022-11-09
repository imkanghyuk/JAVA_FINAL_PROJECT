
	$(document).ready(async function(){
		
		const result = await $.ajax({
			url : "/consumer/readconsumer",
			success : function(data){
				const dataval = data.result;
				$("#mylevelinfo").text(dataval.clevel)
				
			},error(data){
				if(data != null) {
					location.href="http://localhost:8087/main/home"
				}
			}
		})
		
		$(document).on("click", "#myorderclick", function() {
			$('#sectionpart2val').load('/consumer/order/orderlist');
		})

		$(document).on("click", "#mypageclick", function() {
			$('#sectionpart2val').load('/consumer/consumer/mypage');
			
		})
		
		$(document).on("click", "#myreviewclick", function() {
			$('#sectionpart2val').load('/consumer/review/reviewlist');
			
		})
		
		$(document).on("click", "#myiddeleteclick", function(e) {
			$("#deletemodal").fadeIn();			
		})
		
		$(document).on("click", "#closeButton", function(){
			$("#deletemodal").fadeOut();
		})
		
		$(document).on("click", "#byebyeInfo", function(){
			
			$("#logout").click();
			
			const param = {
				cId : $("#deleteID").val(),
				cPassword : $("#deletePW").val()
			}
			
			$.ajax({
				url : "/consumer/delete",
				data : param,
				method : "delete"
			})	
			
		})
		
	})	