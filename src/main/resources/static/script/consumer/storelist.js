	function  getstorecategory(){
		return location.search.substring(6)
	}
	
	async function getstorelistprint(categorynum) {
		const locationcode = await getLoctionCode();
		const param = {sCategoryNum : categorynum, sLocation : locationcode}
		
		const data = await $.ajax({
			url : "/storeinfo/readstorelist",
			data : param
		});

 		const search = location.href.indexOf('?')
		const searchval = location.href.substring(search)

		
		const list = data.result
		console.log(list)
		if(list.length == 0){

			$("<h1>").text("준비중 입니다.").appendTo('#storelist');
			
		}
		const url ="https://iciasmartframe2.s3.ap-northeast-2.amazonaws.com/upload/seller/storelogo/"
		for(const store of list){
			const $div = $("<div>").attr("class", "list_store").attr("value", store.sstoreNum).appendTo('#storelist')
			const $divlogo = $("<div>").attr("class", "list_store_logo").appendTo($div)
				$("<img>").attr("src",url+store.sstoreLogo).appendTo($divlogo)
			const $divlnfo = $("<div>").attr("class", "list_store_lnfo").appendTo($div)
				//const a = $("<a>").attr("href", "/main/storeview"+searchval+"&store="+ store.sstoreNum).appendTo($div);
				const $1 = $("<div>").attr("class","store_name_div").appendTo($divlnfo);
					$("<p>").text(store.sstoreName).appendTo($1)
				const $2 = $("<div>").attr("class","store_min_div").appendTo($divlnfo);
					$("<p>").text("최소주문금액 "+store.sminDelevery + " 원").appendTo($2)
				const $3 = $("<div>").attr("class","store_review_div").appendTo($divlnfo);
					$("<span>").text("리뷰 "+store.sreviewAvg + " 점").appendTo($3)
					$("<span>").text("( "+store.sreviewCount + " 개 )").appendTo($3)
		}
		
		return searchval;
	}

 $(document).ready(async function(){
	 const categorynum = getstorecategory()
	 
	 const searchval = await getstorelistprint(categorynum)
	 
	 $(document).on("click", ".list_store", function(){
		 location.href = "/main/storeview"+ searchval + "&store="+ $(this).attr("value")
	 })
 })