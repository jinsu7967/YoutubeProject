
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
        url: "/search",
        type: "post",
        data: SearchData.keyword,
		contentType: "application/json",
		dataType: "json",
        success: function(content){
			console.log(content)
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

/* 재생 목록에 추가 */

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

/* 재생목록 삭제 */

function playlistDelete(id){
	
	
	var playlistDelete = {
		playlist_num: $("#playlist"+ id).val() 
	}
	
	console.log(playlistDelete);
	
	$.ajax({
			url: "/playlist-delete",
			type:"post",
			data: playlistDelete,
			success:function(){
				alert("삭제 성공")
				$("#playlistReload").load(location.href + " #playlistReload");
			},
			error:function(){
				alert("삭제 실패")
				
			}
		});
}

/* 모달로 값 넘기기 */

function PlaylistUpdateForm(id){
 	$("#playlistNum").val(id)

	console.log(id);
}

/* 정보 수정 모달로 값 넘기기 */
function ContentUpdateForm(id){
	$("#contentUpdateNum").val(id)
	
	console.log(id);
}

/* 재생 목록 수정 ajax */

function PlaylistUpdate(){
	var playlistUpdate = {
		playlist_num: $("#playlistNum").val(),
		playlist_name: $("#playlistName").val()
	}
	
	$.ajax({
		url: "/playlist-update",
		type:"post",
		data: playlistUpdate,
		success:function(){
			alert("변경 성공")
			$("#playlistReload").load(location.href + " #playlistReload");
		},
		error:function(){
			alert("변경 실패")
		}
	});
}

/* 재생목록 조회 */

function Myplaylist(id){
	var playlistData = {
		playlist_name: $("#playlist_name"+ id).val(),
		email: $("#user_email"+ id).val()
	}
	
	console.log(playlistData);
	
	$.ajax({
		url: "/myplaylist",
		type: "post",
		data: JSON.stringify(playlistData),
		contentType: "application/json",
		dataType: "json",
		success:function(data){
			console.log(data)
			
			$('#playlistShow').empty();
			
			if(data.length<=0){
				alert("재생목록이 없습니다.")
				
			}
			
			
			if(data.length >=1){
				
				data.forEach(function(data){
					str='<div class="container-fluid mb-0">'
					str+='<table class="table">'
					str+='<thead class="thead-light">'
					str+='<tr>'
					str+='<th scope="col" class="col-3">컨텐츠 제목</th>'
					str+='<th scope="col" class="col-3">작성자</th>'
					str+='<th scope="col" class="col-3">조회수</th>'
					str+='<th scope="col" class="col-3">설정</th>'
					str+='</tr>'
					str+='</thead>'
					str+='<tbody>'
					str+='<tr>'
					str+='<td><a class="text-primary" href="/mypage/content-detail?contentNum='+data.content_num+'">'+data.content_name+'</a></td>'
					str+='<td>'+data.content_writer+'</td>'
					str+='<td>'+data.content_count+"회"+'</td>'
					str+='<input type="hidden" value="'+data.content_num+'" id="myPlaylistContent'+data.content_num+'">'
					str+='<td><button type="button" class="border-0 bg-transparent" id="'+data.content_num+'" onclick="MyplaylistConDelete(this.id)"><i class="bi bi-x-square"></i></button></td>'
					str+='</tr>'
					str+='</tbody>'
					str+='</div>'
					$('#playlistShow').append(str);
				})
			}
			
			
		},
		error:function(){
			alert("변경 실패")
		}
	});
}

/*나의 재생목록 컨텐츠 제거*/

function MyplaylistConDelete(id){
	var myplaylistContentDel= {
		content_num: $("#myPlaylistContent"+ id).val()
	}
	console.log(myplaylistContentDel)
	
	$.ajax({
		url: "/myplaylistcontent-delete",
		type: "post",
		data: myplaylistContentDel,
		success:function(){
			alert("삭제 완료")
			$("#close").click();
		},
		error:function(){
			alert("삭제 실패")
		}
	});
}

/* 컨텐츠 수정 */

function ContentUpdate(){

	var contentUpdate={
		content_num: $("#contentUpdateNum").val(),
		content_name: $("#contentUpdateName").val(),
		content_date: $("#now_update_date").val()
	}
	
	console.log(contentUpdate);
	
	$.ajax({
	 	url: "/mypage/update",
		type: "post",
		data: contentUpdate,
		success:function(){
			alert("수정 성공")
		},
		error:function(){
			alert("수정 실패")
		}
	})
	
	
}


