function publish(){
	var flag = document.getElementById("flag").value;
	if(flag!=0){
		window.alert("对不起，您已发表过意见!");
		return false;
	}
}