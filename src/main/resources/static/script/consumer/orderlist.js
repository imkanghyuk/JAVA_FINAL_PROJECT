	$(document).ready(async function () {
		
	const now = await $.ajax({
		url : "/consumer/order/inglist",
		success : async function(data){
			if(data.result.length != 0){
				console.log(data)
				const $li = $('#noworderlist')
				
				for(let i=0; i< data.result.length; i++){
					const $lidiv = $("<div>").attr("class","nowlistdiv").appendTo($li)
					const storename = await getStoreName(data.result[i].sstoreNum)			
					$("<span>").text(storename).appendTo($lidiv);
					
					const a = $("<div>").attr("class","noworderlistdiv").attr("value",i).appendTo($lidiv)
					$("<span>").text(data.result[i].aorderDate + " ").appendTo(a);
					$("<span>").text(data.result[i].aorderStatus).appendTo(a);
					
					if(data.result[i].readDetail.length == 0){
						return
					} else if(data.result[i].readDetail.length == 1){
						$("<p>").text("주문 내역 : " + data.result[i].readDetail[0].smenuName).appendTo(a); 
					} else {
						$("<p>").text("주문 내역 : " + data.result[i].readDetail[0].smenuName + " 외 " + (parseInt(data.result[i].readDetail.length) -1) + "개" ).appendTo(a);
					}
				}
			}
		}		
	})	
		
	const before = await $.ajax({
		url : "/consumer/order/beforelist",
		success : async function(data){
			console.log(data)
			if(data.result.length != 0){
				
				const $li = $('#beforeorderlist')
				
				for(let i=0; i<data.result.length; i++){
					const $lidiv = $("<div>").attr("class","beforelistdiv").appendTo($li)
					const storename = await getStoreName(data.result[i].sstoreNum)
					$("<span>").text(storename).appendTo($lidiv);
					
					const a = $("<div>").attr("class","orderlistdiv").attr("value",i).appendTo($lidiv)
					$("<span>").text(data.result[i].aorderDate + " ").appendTo(a);
					$("<span>").text(data.result[i].aorderStatus).appendTo(a);
					$("<p>").text("총 주문 금액 : " + data.result[i].atotalPrice).appendTo(a);
					
					if(data.result[i].detailList.length == 1){
						$("<span>").text("주문 내역 : " + data.result[i].detailList[0].smenuName).appendTo(a); 
					} else if(data.result[i].detailList.length > 1){
						$("<span>").text("주문 내역 : " + data.result[i].detailList[0].smenuName + " 외 " + (data.result[i].detailList.length -1) + " 개" ).appendTo(a);
					}
					
					if(printreivewbutton(data.result[i].aorderDate) < 3 && !await reviewsize(data.result[i].aorderNum)){
						$("<button>").text("리뷰 작성").attr("value", data.result[i].aorderNum).attr("value2", data.result[i].sstoreNum).attr("class", "reviewadd").appendTo(a);
					} else {
						
					}
				}
			}	
		},
		error : function(data){
			console.log(data.responseJSON)
		}})
		
	
	async function reviewsize(ordernum) {
		const result = await $.ajax({
			url : "/consumer/review/existence",
			data : {aOrderNum : ordernum}
		})
		return result.result
	}

	function printreivewbutton(date){
		const date1 = new Date(date)
		const date2 = new Date()
		const result = Math.ceil((date2.getTime()-date1.getTime()) / (1000*60*60*24))-1
		return result
	}
	
	$(document).on("click", ".orderlistdiv", async function() {
		const num = $(this).attr("value")
		const list = $("#orderdetaillist")			

		const $div = $("<div>").attr("class","modaldiv").appendTo(list)
			
		$("<div>").text("총 금액 : " + before.result[num].atotalPrice).appendTo($div)
			
		const storename = await getStoreName(before.result[num].sstoreNum)
			$("<div>").text("배송지 : " + before.result[num].adeleveryAddress + " " + before.result[num].adetailAddress).appendTo($div)
		for(let i=0; i < before.result[num].detailList.length; i++){	
			$("<div>").text("메뉴 이름 : " + before.result[num].detailList[i].smenuName).appendTo($div)
			$("<div>").text("수량 : " + before.result[num].detailList[i].acount).appendTo($div)
		}		
		$("#ordermodal").fadeIn();
	})
	
	$(document).on("click", ".noworderlistdiv", async function(e) {
		const num = $(this).attr("value")
		const list = $("#orderdetaillist")			
		
		const $div = $("<div>").attr("class","modaldiv").appendTo(list)

		$("<div>").text("총 금액 : " + now.result[num].atotalPrice).appendTo($div)
		
		const storename = await getStoreName(now.result[num].sstoreNum)
			$("<div>").text("배송지 : " + now.result[num].adeleveryAddress + " " + now.result[num].adetailAddress).appendTo($div)
		for(let i=0; i < now.result[num].readDetail.length; i++){	
			$("<div>").text("메뉴 이름 : " + now.result[num].readDetail[i].smenuName).appendTo($div)
			$("<div>").text("수량 : " + now.result[num].readDetail[i].acount).appendTo($div)
		}
		$("#ordermodal").fadeIn();
			
	})
		
	$(document).on("click", "#closeButton", function(e){
		e.stopPropagation();
		$(".modaldiv").remove();
		$(".modal").fadeOut();
	})
	
	$(document).on("click", ".reviewadd", function(e){
		e.stopPropagation();
		$("#ordernumber").attr("value" ,$(this).attr("value"))
		$("#storenumber").attr("value" ,$(this).attr("value2"))
		$("#reviewmodal").fadeIn();
	})
	
	
	$('.starRev span').click(function(){
		$(this).parent().children('span').removeClass('on');
		$(this).addClass('on').prevAll('span').addClass('on');
		
		$("#starpoint").text($(this).attr("value"))
		$("#score").attr("value", $(this).attr("value"))
		return false;
	});
	
	$(document).on("click", "#reviewinsert", function() {
		const form = new FormData($("#reviewform")[0]) 
		$.ajax({
			url : "/consumer/review/save",
			data : form,
			method : 'post',
			processData: false,    
			contentType: false,
			success : function() {
				location.href = location.href
			}
		})
	})
	
	
		
})