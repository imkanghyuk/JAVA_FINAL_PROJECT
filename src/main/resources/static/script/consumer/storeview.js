	function getStoreNum(){
		const search = location.search.indexOf('&')
		return location.search.substring((search)+7)
	}
	
	async function printmenugroup(storenum, LocationCode){
		
		const menugrouplist = await $.ajax({
			url : "/storeinfo/readstoreinfo",
			data : {sStoreNum : storenum, sLocation : LocationCode},
			success : function(data){
				console.log(data)
				const mglresult = data.result
				
				const mglist = $('#menugrouplist')
				
				
				for(const mgl of mglresult){
					const $li = $("<li>").text(mgl.sgroupName).attr("value",mgl.sgroupNum).attr("class", "menugroup").attr("value2","show").appendTo(mglist);
					$('<div>').attr("class", "menulist").appendTo($li)
				}		
			}
		})
		
	}

	function count(value) {
		
		let count = parseInt($('#modalcount').text());
		let price = parseInt($('#modalprice').text());
		
		if(value == "plus") {
			count = count+1;

		if(count >= 30){
			alert("수량을 확인해 주세요")
		}
		
		$('#modalcount').text(count);
		$('#modaltotalprice').text(count * price)
		}
	
		if(value == "minus") {
			count = count-1;
		
			if(count < 1){
				count = 1;
			}
		
		$('#modalcount').text(count);
		$('#modaltotalprice').text(count * price)
		}
		
	}
	
	
	$(document).ready(async function(){
		const storenum = getStoreNum()
		const LocationCode = await getLoctionCode();
		
		await printmenugroup(storenum, LocationCode)
		
		await $.ajax({
			url : "/storeinfo/readcategory",
			success : function(data){
				const $calist = "#catelist";
				
				for(let i=0; i<data.result.length; i++) {
					const catelist = $("<div>").attr("class", "catelist_div").attr("value", data.result[i].scategoryNum).appendTo($calist)
					$("<span>").text(data.result[i].scategoryName).appendTo(catelist)
				}
			}
		})
		
				
		$(document).on("click",".menugroup", async function(e){			
			//e.stopPropagation()
			if($(this).attr("value2")=="show") {
				
				const groupnum = $(this).attr("value")
				
				const result = await $.ajax({
					url : "/storeinfo/readstoremenu",
					data : {sGroupNum : groupnum, sLocation : LocationCode}
				})
				console.log(result)
				const menulist = result.result
				
				const $group = $(this).children();
				const url ="https://iciasmartframe2.s3.ap-northeast-2.amazonaws.com/upload/seller/menuimg/"
				for(const ml of menulist){
					const $li = $("<div>").attr("value1", ml.sgroupNum).attr("value2",ml.smenuCode).attr("class","listli").appendTo($group)
					const $1 = $("<div>").attr("class", "c_menu_img").appendTo($li)
						$("<img>").attr("src",url + ml.smenuImg).attr("class","menu_img").appendTo($1);
					const $2 = $("<div>").attr("class", "c_menu_content").appendTo($li)
						$("<span>").text(ml.smenuName).appendTo($2);
						$("<span>").text("가격 : " + ml.smenuPrice).appendTo($2);
				}
				
				$(this).attr("value2", "hide");
				
			} else {
				$(this).children().children().remove();
				
				$(this).attr("value2", "show");
			};
			
			
		})
		
	$(document).on("click", ".listli", async function(e){		
		
		e.stopPropagation();
		
		const groupnum = $(this).attr("value1");
		const menucode = $(this).attr("value2");
		
		const result = await $.ajax({
			url : "/storeinfo/menudetail",
			data : {sGroupNum : groupnum, sMenuCode : menucode ,sLocation : LocationCode}
		})
		const url = "https://iciasmartframe2.s3.ap-northeast-2.amazonaws.com/upload/seller/menuimg/"
		const menudetail = result.result
		$("#sStoreNum").attr("value", menudetail.sstoreNum);
		$("#sGroupNum").attr("value", menudetail.sgroupNum);
		$("#sMenuCode").attr("value", menudetail.smenuCode);
		
		$("#modalimg").attr("src", url+menudetail.smenuImg);
		$("#modalname").text(menudetail.smenuName);
		$("#modalinfo").text(menudetail.smenuInfo);
		$("#modalprice").text(menudetail.smenuPrice);
		$("#modalcount").text(1);
		$("#modaltotalprice").text(menudetail.smenuPrice * $("#modalcount").text());
		
		$(".modal").fadeIn();
	})
	
	$(document).on("click", "#closeButton", function(){
		$(".modal").fadeOut();
	})
	
	$(document).on("click", "#countminus", function(){
		count($("#countminus").attr("value"));
	})

	
	$(document).on("click", "#countplus", function(){
		count($("#countplus").attr("value"));
	})	
	
	$(document).on("click", "#tobasket", async function(){
		
		const param = {sMenuCode : $('#sMenuCode').val(), sGroupNum : $('#sGroupNum').val(), sStoreNum : $('#sStoreNum').val(), cMenuCount :$('#modalcount').text()}
		
		await $.ajax({
			url : "/consumer/basket/add",
			data : param,
			method : "post",
			success : function(data){
				alert(data.message);
				/*
				if(data.message =="로그인이 필요합니다"){
					location.href = "/consumer/login"
				} */				
				$("#blist").remove();
				$("#totalview").remove();
				if(data.result != null){
					printStoreName(data.result)
					printBasketList(data.result);
					printBasketTotalPrice(data.result);
					$(".modal").fadeOut();
				} else {
					
			}},
			error : async function(data){
				const result = data.responseJSON;
				console.log(result)
				alert("로그인이 필요합니다");
				location.href = "/consumer/consumer/login"
			}
		})
	})
	
	
	$(document).on("click", ".catelist_div", function(){
		location.href = "http://localhost:8087/main/storelist?cate=" + $(this).attr("value")
	})
	
	$("#store_menu_get").click(function(){
		location.href=location.href
	})
		
	$("#store_review_get").click(function(){
		$("#store_menu_group").remove()
		$("<div>").attr("id","store_menu_group").appendTo("#section_1")
		$.ajax({
			url : "/storeinfo/store/reviewget",
			data : {sStoreNum : storenum},
			success : function(data){
				console.log(data)
				if(data.result.length == 0) {
					const $1 = $("<div>").attr("id", "review_result").appendTo("#store_menu_group")
					$("<h1>").text("가게 작성 리뷰가 없습니다.").appendTo($1)
				} else {

					const url = "https://iciasmartframe2.s3.ap-northeast-2.amazonaws.com/upload/consumer/review/"
					
					for(let i=0; i<data.result.length; i++){
					const $1 = $("<div>").attr("class", "review_result").appendTo("#store_menu_group")
						const $rimg = $("<div>").attr("class", "r_img_div").appendTo($1);
							$("<img>").attr("src",url+data.result[i].areviewImg).appendTo($rimg)
						const $infod = $("<div>").attr("class", "r_info_div").appendTo($1);
							const $r1 = $("<div>").attr("class", "r_date_div").appendTo($infod);
								$("<p>").text(data.result[i].awriteDate).appendTo($r1)
							const $r2 = $("<div>").attr("class", "r_nick_div").appendTo($infod);
								$("<p>").text(data.result[i].anickname).appendTo($r2)
								let star = "★"
								if(data.result[i].ascore == "2"){
									star = "★★"
								} else if (data.result[i].ascore == "3"){
									star = "★★★"
								} else if (data.result[i].ascore == "4"){
									star = "★★★★"
								} else if (data.result[i].ascore == "5"){
									star = "★★★★★"
								} else {
									star = "★"
								}
								
								$("<p>").text(star).attr("class", "star_point").appendTo($r2)
							const $r3 = $("<div>").attr("class", "r_content_div").appendTo($infod);
								$("<p>").text(data.result[i].areview).appendTo($r3)
					}		
					 
				}
			}
		})
	})
		
	$("#store_info_get").click(function(){
		$("#store_menu_group").remove()
		$("<div>").attr("id","store_menu_group").appendTo("#section_1")
		$.ajax({
			url : "/storeinfo/store/infoimgget",
			data : {sStoreNum : storenum},
			success : function(data){
				console.log(data)
				const url = "https://iciasmartframe2.s3.ap-northeast-2.amazonaws.com/upload/seller/storeintro/"

				const $title = $("<div>").attr("id","intro_title").appendTo("#store_menu_group")
					$("<p>").text("가게 이미지").appendTo($title)
				const $img = $("<div>").attr("id","intro_img").appendTo("#store_menu_group")
					if(data.result.sstoreImg.length != 0){
						$("<img>").attr("src",url+data.result.sstoreImg[0].sinfoImg).appendTo($img)
						$("<img>").attr("src",url+data.result.sstoreImg[1].sinfoImg).appendTo($img)
						$("<img>").attr("src",url+data.result.sstoreImg[2].sinfoImg).appendTo($img)
					} else {
						$("<img>").attr("src",url+"default.JPG").appendTo($img)
						$("<img>").attr("src",url+"default.JPG").appendTo($img)
						$("<img>").attr("src",url+"default.JPG").appendTo($img)
					}
				const $div = $("<div>").attr("id","intro_content").appendTo("#store_menu_group")
					const $1 = $("<div>").attr("id","intro_sstoreIntro").appendTo($div)
						$("<p>").text("가게 소개글").appendTo($1)
						$("<p>").text(data.result.sstoreIntro).appendTo($1)
				const $div2 = $("<div>").attr("id","intro_etc").appendTo("#store_menu_group")	
					const $2 = $("<div>").attr("id","intro_sstoreAddress").appendTo($div2)
						$("<p>").text("가게 주소").appendTo($2)
						$("<p>").text(data.result.sstoreAddress).appendTo($2)
					const $3 = $("<div>").attr("id","intro_sminDeleVery").appendTo($div2)
						$("<p>").text("최소 주문 금액").appendTo($3)
						$("<p>").text(data.result.sminDeleVery + " 원").appendTo($3)
					const $4 = $("<div>").attr("id","intro_sstoreTime").appendTo($div2)
						$("<p>").text("영업 시간").appendTo($4)
						$("<p>").text(data.result.sstoreTime).appendTo($4)
			}
		})
	})	
	
	})
