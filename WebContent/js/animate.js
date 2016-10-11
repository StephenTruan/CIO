window.onload=function(){
	
	var box=document.getElementById('box');
	var aside=document.getElementById('help');
	var control=document.getElementById('control');
	var back=document.getElementById('back');
	//获取页面高度，让遮盖层回去时能不被看见，如果要用getStyle在IE下，获得的不是百分比，比如100% 获得的是100，别的浏览时将100%换算成px
	
	var flag=parseInt(-document.documentElement.clientHeight);
	//缩放界面保证排版不错乱(页面box的高)
	box.style.height=document.documentElement.clientHeight +'px';	
	aside.style.top=-document.documentElement.clientHeight+'px';
	window.onresize=function(){
		flag=parseInt(-document.documentElement.clientHeight);
		aside.style.top=flag+'px';
		box.style.height=document.documentElement.clientHeight +'px';	
	}
	control.onclick=function(){
		animate(aside,'top',0,10);
	}
	back.onclick=function(){
		animate(aside,'top',flag,10);
	}

	//下拉动画函数  元素，方向 ，结果 ，整个页面刷新次数
	function animate(obj,direction,num,time){
		//现在的值
		var now=parseInt(getStyle(obj,direction));
		//速度
		var speed=num>flag?parseInt(Math.abs(now/time)) : parseInt(flag/time);
		start();
		function start(){
			var timer=setTimeout(function(){
				obj.style[direction]=now+speed+'px';
				now=parseInt(obj.style[direction]);
				if(now >= 0  ){
					obj.style[direction]=0;
					clearTimeout(timer);
				}
				else if( now <= flag ){
					obj.style[direction]=flag+'px'; 
					clearTimeout(timer);
				}
				else{
					start();
				}
			},10)
			
		}
	}
	
	function getStyle(obj,attr){
	if(obj.currentStyle){
		return obj.currentStyle[attr];
	}
	else{
		return getComputedStyle(obj,false)[attr];
	}
}
}