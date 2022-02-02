console.log("this is colsole");


function toggleSidebar(){

	if($(".sidebar").is(":visible")){
		$(".sidebar").css("display","none");
		$(".content").css("margin-left","0%");
	}else{
		$(".sidebar").css("display","block");
		$(".content").css("margin-left","20%");
	}
	
};

function orderSuccess(){

swal("Good job!", "Successfully order waste !!", "success");

}


function api( ){
	
	
	
 fetch('https://api.thingspeak.com/channels/1370131/feeds.json?results=1')
.then(res => res.json())
.then(data => {
	let val = data.feeds[0].field1
	console.log(val)
	if(val==1)
	$("#water").css("height","260px");
	else
	$("#water").css("height","50px");

})
 



}