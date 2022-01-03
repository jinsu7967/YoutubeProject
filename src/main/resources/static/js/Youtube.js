
function Test(rno){
	var reply="";
	reply = document.getElementById('reply'+rno).value;
	console.log(reply);
	
	$("#reply"+rno).replaceWith(
	"<textarea name='updateContent' class='reply-input' cols='20' rows='3' text='reply'></textarea>"
	);
	$("#replyBtn"+rno).replaceWith(
	"<button type='submit' name='replyBtn' class='replyUpdateBtn' value='수정하기'>수정하기</button>"
	);
	$("#replyBtn2"+rno).replaceWith(
	"<button type='button' class='replyUpdateBtn'><a href='/mypage'>취소</a></button>"
	);
	
}