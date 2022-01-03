
function Test(rno){
	var reply="";
	reply = $("#reply"+rno).val();
	
	$("#reply"+rno).html(
	"<textarea name='replyUpdate'></textarea>"
	);
	$("#replyBtn"+rno).html(
	"<button name='replyBtn' text='수정하기' value='수정하기'></button>"
	);
	$("#replyBtn2"+rno).hide();
	
}