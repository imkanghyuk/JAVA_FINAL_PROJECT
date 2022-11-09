	async function getOrderNum() {
		const ordernum = location.search.substring(10);
		
		const check = await $.ajax({
			url : "/consumer/order/numcheck",
			data : {aOrderNum : ordernum}
		})
		
		if(check.result == false || ordernum == null || ordernum <0 || ordernum == "undefinded" || ordernum == ""){
			alert("잘못된 접근입니다"); 
			return location.href = "/main/home";
		}
			
		return ordernum; 
	}
	
	function printOrderlist(result){
		const $list = $("#orderlist");		
		const ordermenu = result[0].readDetail;
		
		$("<p>").attr("id", "status").appendTo($list);
		$("<span>").text("주문상태 : ").appendTo($("#status"));
		$("<span>").text(result[0].aorderStatus).appendTo($("#status"));		
		$("<p>").attr("id", "orderdate").appendTo($list);
		$("<span>").text("주문일시 : ").appendTo($("#orderdate"));
		$("<span>").text(result[0].aorderDate).appendTo($("#orderdate"));
		$("<p>").attr("id", "address").appendTo($list);
		$("<span>").text("주소 : ").appendTo($("#address"));
		$("<span>").text(result[0].adeleveryAddress + " " + result[0].adetailAddress).appendTo($("#address"));
		$("<p>").attr("id", "totalprice").appendTo($list);
		$("<span>").text("결재 금액 : ").appendTo($("#totalprice"));
		$("<span>").text(result[0].atotalPrice + " 원").appendTo($("#totalprice"));
		
		
		$("<p>").attr("id", "menu").appendTo($list);
		$("<p>").text("주문메뉴").appendTo($("#menu"));
		for(const menu of ordermenu){
			$("<p>").text(menu.smenuName).appendTo($("#menu"));
		}
	}

	$(document).ready(async function(){
			
		const ordernum = await getOrderNum();
		
		const result = await $.ajax({
			url : "/consumer/order/nowlist",
			data : {aOrderNum : ordernum}
		})
		
		printOrderlist(result.result);
		
		console.log(result);
		
	})
	