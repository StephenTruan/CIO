window.onload=function(){
	var form=document.getElementById('form');
	flag=false;  //监听变量用于提交一次表单
	form.onsubmit=function(){
		if (flag) {return;};
		flag=true;
		
		form.submit();
	}
}