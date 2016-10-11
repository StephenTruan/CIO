window.onload=function(){
	var body=document.body;
	var flag=false;
	var box=document.getElementById('box');
	var txt=document.getElementById('txt');
	var sub=document.getElementById('sub');
	var other=document.getElementById('other');
	var form=document.getElementById('form');
	var reg=/^\s*$/;
	//是否为中文
	var m = /[\u4e00-\u9fa5]/;
	var chine=[];
	box.style.width=document.documentElement.clientWidth*3/4 + 'px';
	box.style.height=document.documentElement.clientHeight*3/4 + 'px';
	box.style.top=document.documentElement.clientHeight*1/(4*2) +'px';
	box.style.left=document.documentElement.clientWidth*1/(4*2) +'px';
	txt.style.width=box.clientWidth*3/4+'px';
	txt.style.height=box.clientHeight*3/4+'px';
	window.onresize=function(){
		box.style.width=document.documentElement.clientWidth*3/4 + 'px';
		box.style.height=document.documentElement.clientHeight*3/4 + 'px';
		box.style.top=document.documentElement.clientHeight*1/(4*2) +'px';
		box.style.left=document.documentElement.clientWidth*1/(4*2) +'px';
		txt.style.width=box.clientWidth*3/4+'px';
		txt.style.height=box.clientHeight*3/4+'px';
	}

	form.onsubmit=function(e){
		var eve=window.event || e;
		//把除汉字外的其他字符 删掉，来验证是否为10个汉字
		if(flag) return;
		for(var i=0;i<txt.value.length; i++){
			
			if(m.test(txt.value[i])){
				chine.push(txt.value[i]);
			}
		}
		if(reg.test(txt.value)){
			preDef(eve);
			alert('不能为空');
			
		}
		else if(chine.length>10){
				flag=true;
				return true;
			}
			else{
				preDef(eve);
				alert('不能少于10个汉字');
			}
		}
		function preDef(evt){
			if(evt.preventDefault){
				evt.preventDefault();
			}else{
				evt.returnValue=false;
			}
		}

		body.style.background='url(image/indexBg.png)';
}