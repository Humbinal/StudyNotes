/**
 * js
 */
function changeVerifyCode(){
	var img=document.getElementById("imgVerifyCode");
	img.src="/shopping/GetVerifyCode?a="+new Date().getTime();
}