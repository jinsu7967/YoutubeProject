
/* 댓글쓰기 (Reply Write) */

function replyWrite(){

	/*현재 시간 불러오기  */
    document.getElementById('now_date').valueAsDate = new Date();

    var writeReply = {
        contentNum : $("#content_num").val(),
        replyWriter : $("#replyWriter").val(),
        replyContent : $("#replyContent").val(),
        replyDate : $("#now_date").val()
    }

    $.ajax({
        url: "/reply/write",
        type: "post",
        data: writeReply,
        success:function(){
            $("#replyWrapper").load(location.href + " #replyWrapper");
        },
        error:function(){
			
        }
    })
};

/* 댓글 수정(Reply Modify) */

function replyModify(id){				

		var updateReply = {
			rno : $("#rno" + id).val(),
			content : $("#content" + id).val(),
			content_num : $("#content_num" + id).val()
		}
		
		$.ajax({
			url: "/reply/update",
			type:"post",
			data: updateReply,
			success:function(){
				$("div.multi-collapse-" + updateReply.rno).removeClass('show');
				$("p.multi-collapse-" + updateReply.rno).addClass('show');
				$("p.multi-collapse-" + updateReply.rno).html(updateReply.content);
			},
			error:function(){
				alert("Error!!!");
			}
		})
		
};

/* 댓글 삭제(Reply Delete) */

function replyDelete(id){

		var deleteReply = {
				rno: $("#rno" + id).val(),
				content_num: $("#content_num" + id).val()
		}
		
		
		
		$.ajax({
			url: "/reply/delete",
			type:"post",
			data: deleteReply,
			success:function(){
				$("#replyWrapper").load(location.href + " #replyWrapper");
				alert("삭제 성공")
			},
			error:function(){
				alert("삭제 실패")
			}
		});
}; 

/* 검색 기능 */

function searchContent(){

	var SearchData = {
			keyword: $("#search-input").val()
		}
	
	console.log("keyword는"+SearchData.keyword);
	
	$.ajax({
        url: "/search-test",
        type: "post",
        data: SearchData.keyword,
		contentType: "application/json",
		dataType: "json",
        success: function(content){

			$('#contentList').empty();
			
			if(content.length >=1){
				content.forEach(function(content){
					str= '<div class="player col pr-3">';
					str+= '<div class="thumb-wrap">';
					str+= '<a href="/mypage/content-detail/'+content.content_num+'">';
					str+= '<img class="play-content" src="../static/upload/'+content.file_name+'">';
					str+= '</a>';
					str+= '</div>';
					str+= '<div class="row row-cols-2 no-gutters align-items-center py-3">';
					str+= '<div class="play-mark col-auto ">';
					str+= '<img src="../static/upload/'+content.file_name+'">';
					str+= '</div>';
					str+= '<div class="play-info col-auto pl-3">';
					str+= '<div class="play-info-title">';
					str+= '<h5 class="mb-0">';
					str+= '<b>'+content.content_name+'</b>';
					str+= '</h5>';
					str+= '</div>';
					str+= '<div class="play-info-writer">';
					str+= '<p class="mb-0">'+content.content_writer+'</p>';
					str+= '<p class="mb-0">'+content.content_count+"회"+'</P>';
					str+= '</div>';
					str+= '</div>';
					str+= '</div>';
					str+= '</div>';
					$('#contentList').append(str);
				})
			}
			
			if(content.length==0){
			 	alert("검색내용 없음")
				location.href="/";
			}
        },
        error:function(){
			alert("검색 실패")
        }
    })
}

/* 재생 목록에 추가*/

function PlayListAdd(){
	
	var playlistData = {
	
	playlist_name: $("#playlist option:selected").val(),
	content_num: $("#contentNum").val(),
	email: $("#user").val()
	
	
	}
	console.log(playlistData);
	
	$.ajax({
			url: "/playlist-add",
			type:"post",
			data: playlistData,
			success:function(){
				alert("재생목록에 추가 되었습니다.")
			},
			error:function(){
				alert("재생목록에 추가 되지 않았습니다.")
			}
		});
}


