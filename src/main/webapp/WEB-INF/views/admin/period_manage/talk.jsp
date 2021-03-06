<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>


<script type="text/javascript">

	$(document).ready(function() {
 		aside.setActive(8,1);
		searchList(listForm,1);
	});

	function searchList(listForm,page) {
		var param = {
			page		:	page
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/period_manage/talk_list.go",
			dataType:"html",
			data:param,
			success:function(msg){
				$("#contents-list").html(msg);
			}
		});
		return false;
	}
	
	function addListForm(frm) {
		
		if (frm.comment.value == "") {
			alert("내용을 입력해주세요.");
			return false;
		}
		
		var param = {
			medSeq  : frm.medSeq.value,
 			askind	: frm.askind.value,
			comment	: frm.comment.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/medical/medical_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.reload();
				}
			}
		});

		return false;
	}
	
	function answerListForm(frm) {
		
		if (frm.comment1.value == "") {
			alert("내용을 입력해주세요.");
			return false;
		}
		
		var param = {
			medSeq  : frm.medSeq.value,
 			askind	: frm.askind.value,
 			value	: frm.value.value,
			comment	: frm.comment1.value
			
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/medical/medical_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.reload();
				}
			}
		});

		return false;
	}
	
	function ListEditForm(frm) {
		
		if (frm.comment.value == "") {
			alert("내용을 입력해주세요.");
			return false;
		}
		
		var param = {
			medSeq  : frm.medSeq.value,
			comment	: frm.comment.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/medical/medical_edit_table_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.reload();
				}
			}
		});

		return false;
	}
	
	

	function checkQuestion() {
		
		var isLastCheck = $('input:checkbox[name="isLast"]:checked ').val();
		
		var param = {
			medSeq	: isLastCheck
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/medical/medical_checkQuest_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.reload();
				}
			}
		});
		
		return false;
	}
	
	function viewArray(){
		
		var sortCount = $(".sort").length;
		var arr = new Array();
		var arrSeq = "" ; 
		
		$.each($(".sort"), function( index, obj ) {
			arr.push($(obj).val());
		});
		
		arrSeq = arr.join(",");
/*
		for (var i=0; i<arr.length; i++) {
			arrSeq  += arr[i] + ",";
		}
*/	

		var param = {
				sortList  : arrSeq
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/medical/sort_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.reload();
				}
			}
		});

		return false;
		
// 		for(var i=0; i<arr.length; i++){
// 			alert(arr[i]);	
// 		}
	}
	
	function questType(frm){
		
		var askind = frm.askind.value;
		var guide = document.getElementById("guide");
		
// 		if(frm.askind.value == 1){

// 			if(guide.style.display == "block"){
// 				answer.style.display ="none";

// 				if(guide.style.display =="none"){
// 					guide.style.display == "block"
// 				}
// 			}else if(answer.style.display == "block"){
// 				guide.style.display ="block";
// 				answer.style.display ="none";
// 			}
// 		}
		
		if(frm.askind.value == 1){
			if(guide.style.display == "none"){
				guide.style.display ="block";
				answer.style.display ="none";
			}else if(guide.style.display != "block"){
				guide.style.display ="block";
			}	
		}
		
		if(frm.askind.value==2){
			if(answer.style.display == "none"){
				answer.style.display ="block";
				guide.style.display ="none";
			}else{
				answer.style.display ="none";
			}	
		}
		
	}
	
</script>

</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 닥터톡 - 관리목표/주기 > 관리목표 대화
			</header>
		
			<div class="contents-block">
			
				<h1>관리목표 대화</h1>
				
				<div class="contents-main">
				
					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<div class="contents-top">
							<div class="top-tools">
							</div>
						</div>
					</form>		
						
						<div id="contents-list">
						</div>
						
						<div style="text-align:right; margin-top:10px; margin-right:10px;">
							<button type="button"  class="btn" onclick="checkQuestion()">질문 체크</button>
							<button type="button"  class="btn" onclick="viewArray();">정렬 저장</button>
						</div>
						
						<div style="margin-top:20px;">
							<h2 style="margin-left:10px;">추가하기</h2>
							<form method="post" name="addForm" id="addForm" >
								<input type="hidden" name="medSeq"  value="0">
								<div style="float:left;  margin-left:10px;">
									<select class="askind" name="askind" id="askind" onchange="questType(this.form)">
<!-- 										<option value="1">안내문</option> -->
										<option value="1">질문</option>
										<option value="2">답안</option>
									</select> 
								</div>
								
								<div class="guide" id="guide" style="display:block; margin-bottom:10px;" >
									 <input type="text" class="itext"  name="comment" style="width:600px;  margin-left:10px;">
									 <button class="btn" type="button" onclick="addListForm(this.form)">등록</button>
								</div>
								 
								 <div class="answer" id="answer" style="display:none; margin-left:10px; margin-bottom:20px;" >
									<select name="value" id="value" style="  margin-left:10px;" >
										<option value="0">입력</option>
										<option value="1">선택</option>	
									</select> 
								 	 <input type="text" class="itext"  name="comment1" style="width:600px;">
 	 <!-- 						 	  goto <input type="text" class="itext"  name="move" > -->
									 <button class="btn" type="button" onclick="answerListForm(this.form)" >등록</button>
								 </div>
							</form>
						</div>
				</div>
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>