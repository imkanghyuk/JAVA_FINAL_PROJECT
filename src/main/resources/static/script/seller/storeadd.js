		
		async function printcatecode() {
			await $.ajax({
				url : "/seller/category",
				success : function(data){
					const $sel = "#catesel"
					for(let i=0; i<data.result.length; i++){
						$("<option>").text(data.result[i].scategoryName).attr("value", data.result[i].scategoryNum).appendTo($sel)
					}
					$("#categorynum").attr("value", data.result[0].scategoryNum)
				}
			})
		}
		
		async function printlocacode() {
			await $.ajax({
				url : "/seller/location",
				success : function(data){
					const $sel = "#locesel"
					for(let i=0; i<data.result.length; i++){
						$("<option>").text(data.result[i].slocationName).attr("value", data.result[i].slocationCode).appendTo($sel)
						
					}
					$("#locationcode").attr("value", data.result[0].slocationCode)
				}
			})
		}
		
		$(document).ready(async function(){
			await printcatecode()
			await printlocacode()
			
			$("#catesel").click(function() {
				console.log($(this).val())
				$("#categorynum").attr("value", $(this).val())
			})
			
			$("#locationcode").click(function() {
				console.log($(this).val())
				$("#locationcode").attr("value", $(this).val())
			})
			
			$("#mainaddress").click(function() {
				new daum.Postcode({
			        oncomplete: function(data) {
			        	
			        	const locationtext = data.sido + " " + data.sigungu + " " + data.bname
			        	$("#mainaddress").val(locationtext)
			        }
			    }).open();
			})
			
			$("#storeaddbtn").click(function() {
				const time = $("#opentime").val() + " 부터 " + $("#closetime").val() + " 까지" 
				$("#storetime").val(time)
				const address = $("#mainaddress").val() + " " + $("#subaddress").val()
				$("#storeAddress").val(address)
				
				const formdata = new FormData($("#storeform")[0])
				
				$.ajax({
					url : "/seller/storeadd",
					data : formdata,
					method : 'post',
				    processData: false,    
				    contentType: false,
				    success : function(data) {
						console.log(data)
				    	alert(data.message)
				    	location.href="/seller/store/storeread?storenum="+data.result.sstoreNum
				    },
				    error : function(){
				    	alert("가게등록 실패")
				    }
				})
			})
		})	