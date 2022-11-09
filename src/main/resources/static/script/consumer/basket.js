function printBasketList(result) {
	if (result.length == 0) {
		return
	}

	const $list = $("<ul>").attr("id", "blist").appendTo('#BasketList');

	for (const list of result) {
		const $li = $('<li>').attr("class","basket_menulist").appendTo($list);
			const $1 = $("<div>").attr("class","li_content").appendTo($li)
				$('<span>').text(" 가격 : " + list.smenuPrice).attr("name", "sMenuPrice").appendTo($1);
				$('<span>').text(" 메뉴 : " + list.smenuName).attr("name", "sMenuName").appendTo($1);
			const $2 = $("<div>").attr("class","li_button").appendTo($li)
				$('<button>').text("-").attr("class", "countminus").addClass("btn btn-danger").addClass("btn-sm").attr("type", "submit").attr("value1", list.smenuCode).attr("value2", false).appendTo($2);
				$('<span>').text(" 수량 : " + list.cmenuCount).attr("name", "cMenuCount").attr("id", "cMenuCount").attr("value", list.cmenuCount).appendTo($2);
				$('<button>').text("+").attr("class", "countplus").addClass("btn btn-danger").addClass("btn-sm").attr("type", "submit").attr("value1", list.smenuCode).attr("value2", true).appendTo($2);
			const $3 = $("<div>").attr("class","li_del_button").appendTo($li)
			$('<button>').text("삭제").attr("class", "deletebutton").addClass("btn btn-danger").addClass("btn-sm").attr("type", "submit").attr("value", list.cbasketNum).appendTo($3);
	}

}

function printBasketTotalPrice(result) {

	let totalPrice = 0;
	const $view = $("<div>").attr("id", "totalview").appendTo("#totalPrice");

	for (let i = 0; i < result.length; i++) {
		totalPrice = totalPrice + (result[i].cmenuCount * result[i].smenuPrice);
	}

	$('<span>').text("총 가격 : ").appendTo($view);
	$('<span>').text(totalPrice).attr("id", "orderPrice").attr("value", totalPrice).appendTo($view);
}

function printStoreName(result) {
	if(result == null){
		return ;
	}
	else if (result.length == 0) {
		$("#BasketStoreName").remove()
		return $('<span>').attr("id", "BasketStoreName").appendTo('#BasketStore');
	}
	$("#BasketStoreName").remove()
	$('<span>').text(result[0].sstoreName).attr("id", "BasketStoreName").attr("value",result[0].sstoreNum).appendTo('#BasketStore');

}

	$(document).ready(async function() {
				
		await $.ajax({
			url : '/consumer/basket/listread',
			success : function(result) {
				if(result.result != "비로그인"){
					printStoreName(result.result)
					printBasketList(result.result);
					printBasketTotalPrice(result.result);
				} else {
					
				}
			}
		})
		
		$(document).on("click", ".deletebutton", function(){
			const value = $(this).attr('value');
			
			const param = {cBasketNum : value};
			
			$.ajax({
				url : "/consumer/basket/delete",
				data : param,
				method : "delete",
				success : function(data) {
					alert(data.message);
					$("#blist").remove();
					$("#totalview").remove();
					if(data.result !=null){
						printStoreName(data.result)
						printBasketList(data.result);
						printBasketTotalPrice(data.result);
					}
				}			
			});
		});

		$(document).on("click" , ".countminus", async function(){	
			
			const code = $(this).attr('value1');
			const status = $(this).attr('value2');
			const count = $(this).siblings('span#cMenuCount').attr("value");

			const param = {sMenuCode : code, countVal :status};
			
			if(count == 1){
				alert("수량을 감소할 수 없습니다.");
			}
			
			else{
			await $.ajax({
				url : "/consumer/basket/countupdate",
				data : param,
				method : "patch",
				success : function(data) {
					alert(data.message);
					$("#blist").remove();
					$("#totalview").remove();
					if(data.result !=null){	
						printBasketList(data.result);
						printBasketTotalPrice(data.result);
					}
				}
			})
			}
		});
		
		
		$(document).on("click", ".countplus", async function(){
			
			const code = $(this).attr('value1');
			const status = $(this).attr('value2');
			const count = $(this).siblings('span#cMenuCount').attr("value");

			const param = {sMenuCode : code, countVal :status};
			

			await $.ajax({
					url : "/consumer/basket/countupdate",
					data : param,
					method : "patch",
					success : function(data) {
						alert(data.message);
						$("#blist").remove();
						$("#totalview").remove();
						printBasketList(data.result);
						printBasketTotalPrice(data.result);
					}
				})
			
		});
		
		
		$("#orderadd").click(async function(){
			
			const checkPrice = await $.ajax({
				url : "/consumer/basket/pricecheck",
				data : {
					sStoreNum : $("#BasketStoreName").attr("value"),
					totalPrice : $("#orderPrice").attr("value")
					}				
			});
			
			if(checkPrice.result==false){
				alert("최소 주문 금액 미달");
			}
			else if(checkPrice.result=="상품없음"){
				alert("장바구니에 상품이 없습니다.");	
			}
			else{				
				location.href = "/consumer/order/corder";
			}
		})
			
})