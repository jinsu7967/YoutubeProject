
function Test(rno){
	var reply="";
	reply = $("#reply"+rno).val();
	console.log(reply);
	
	$("#reply"+rno).replaceWith(
	"<textarea name='updateContent' class='reply-input' cols='20' rows='3' text='reply'></textarea>"
	);
	$("#replyBtn"+rno).replaceWith(
	"<button type='submit' name='replyBtn' class='replyUpdateBtn' value='수정하기'>수정하기</button>"
	);
	$("#replyBtn2"+rno).replaceWith(
	"<button type='button' class='replyUpdateBtn' >취소</button>"
	);
	
}