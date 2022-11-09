	function iamport(result, user){
		IMP.init('imp35416066');
		IMP.request_pay({
		    pg : 'kakaopay',
		    pay_method : 'card',
		    merchant_uid : 'TEST 주문 ' + new Date().getTime(),
		    name : result.result[0].sstoreName,
		    amount : gettotalprice(result.result),
		    buyer_email : user.cemail,
		    buyer_name : user.cnickname,
		    buyer_tel : user.cphone,
		    buyer_addr : $("#aDeleveryAddress").val() + " " + $("#aDetailAddress").val(),
		}, function(rsp) {
			console.log(rsp);
			// 결제검증
			$.ajax({
	        	type : "POST",
	        	url : "/verifyIamport/" + rsp.imp_uid 
	        }).done(function(data) {
	        	if(rsp.paid_amount == data.response.amount){
		        	orderstart()
	        	} else {
	        		alert("결제 실패");
	        	}
	        });
		});
	}
		
	function printTotalPrice(result){
		const totalPrice = gettotalprice(result)
	
		$('<span>').text("총 가격 : ").appendTo("#ordertotalPrice");
		$('<span>').text(totalPrice + " 원").appendTo("#ordertotalPrice");
	}	
	
	function printList(result){
		
		$('<span>').text(result[0].sstoreName).attr("id", "OrderSstoreName").attr("value", result[0].sstoreNum).appendTo('#orderStoreInfo');
		
		const $list = $('#orderblist');
	
		for(const list of result){
			const $li = $('<li>').attr("class","ordermenulist").appendTo($list);
			$('<span>').text(" 메뉴 : " + list.smenuName).attr("name", "sMenuName").appendTo($li);
			$('<span>').text(" 가격 : " + list.smenuPrice + " 원").attr("name", "sMenuPrice").appendTo($li);
			$('<span>').text(" 수량 : " + list.cmenuCount+ " 개" ).attr("name", "cMenuCount").attr("id", "cMenuCount").attr("value", list.cmenuCount).appendTo($li);
		}
	
	}
	function getHiddenInfo(result) {
		if(result.length > 0){
			$('<input>').attr("type", "hidden").attr("value", result[0].sstoreNum).attr("name", "sStoreNum").attr("id", "sStoreNum").appendTo('#orderdata');
		}
	}
	
	function getPrintAddress() {
		
		$("#aDeleveryAddress").val(sessionStorage.getItem("locationinfo"));
	}
	
	async function getuser() {
		return await $.ajax("/consumer/readconsumer")
	}
	
	function orderstart(){
		const param = {
			aDeleveryAddress : $('#aDeleveryAddress').val(),
			aDetailAddress : $('#aDetailAddress').val(),
			sStoreNum : $('#sStoreNum').val(),
		};
	
		$.ajax({
			url : "/consumer/order/add",
			data : param,
			method : "POST",				
			success : function(data){
				alert(data.message);
				location.href = "/consumer/order/ordersuccess?ordernum=" + data.result.aorderNum;
			}
		});
	}
	
	function gettotalprice(result){
		let totalPrice = 0;
		
		for(let i=0; i<result.length; i++){
			totalPrice = totalPrice + (result[i].cmenuCount * result[i].smenuPrice);
		}
		
		return totalPrice;
	}
	
	async function getlist(){
		const result = await $.ajax({
			url : '/consumer/basket/listread',
		})
		return result;
	}
	
	
$(document).ready(async function(){
		
		const result = await getlist()
		
		const user = await getuser()
		
		printList(result.result);

		printTotalPrice(result.result);
		
		getHiddenInfo(result.result);

		getPrintAddress();
		
	$('#orderbutton').click(function(){
		if($("#aDetailAddress").val()=="") {		
			alert("상세 주소를 입력해주세요")
		} else {
			iamport(result, user)
		}			
	});
		
	$('#orderCansleAndBack').on("click", function() {
		const a = confirm("주문을 취소하고 이전으로 돌아갑니다");
			
		if(a) {
			window.history.back();
		}
	});
		
})