

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
		
		var target = $('#replyWrapper');
		
		$.ajax({
			url: "/reply/delete",
			type:"post",
			data: deleteReply,
			success:function(){
				$("#replyWrapper").load(location.href + " #replyWrapper");
			},
			error:function(){
				alert("삭제실패!")
			}
		});
	}; 