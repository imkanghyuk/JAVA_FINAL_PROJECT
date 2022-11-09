function saveCoords(latitude, longitude) {

	localStorage.setItem('latitude', JSON.stringify(latitude));
	localStorage.setItem('longitude', JSON.stringify(longitude));
}

function success({ coords, timestamp }) {

	const latitude = coords.latitude;   // 위도
	const longitude = coords.longitude; // 경도

	saveCoords(latitude, longitude)

}

function getUserLocation() {
	if (!navigator.geolocation) {
		throw "위치 정보가 지원되지 않습니다.";
	}
	navigator.geolocation.getCurrentPosition(success);
}

async function getStringLocation() {
	const latitude = localStorage.getItem("latitude");
	const longitude = localStorage.getItem("longitude");

	const result =
		await $.ajax({
			method: "GET",
			url: "http://dapi.kakao.com/v2/local/geo/coord2regioncode.JSON?x=" + longitude + "&y=" + latitude,
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Authorization", "KakaoAK " + "31c700d7fcde4a5b78984aba17a7d42e");
			}
		});

	return result.documents;
}

async function getLoctionCode() {

 	return sessionStorage.getItem("locationcode")
}

function prinntLocationinfo(){
	$("#nowlocation").attr("value", sessionStorage.getItem("locationinfo"));
}
