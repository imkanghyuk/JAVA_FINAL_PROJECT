async function getStoreName(storenum) {
	const result = await $.ajax({
		url : "/storeinfo/storenameget",
		data : {sStoreNum : storenum}
	})
	
	return result.result
}