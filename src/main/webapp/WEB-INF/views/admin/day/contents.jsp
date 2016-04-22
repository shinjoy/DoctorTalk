<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>






<script type="text/javascript">

	$(function() {
	    $( "#sortable" ).sortable();
	    $( "#sortable" ).disableSelection();
	});
	
	$(document).ready(function() {
 		aside.setActive(9,1);
		searchList1(listForm,1,listForm.group.value);
	});

	function searchList1(frm,page,group) {
		var param = {
			page		:	page,
			group : group
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/day/contents_list.go",
			dataType:"html",
			data:param,
			success:function(msg){
				$("#contents-list").html(msg);
			}
		});
		return false;
	}
	
	//질문 추가
	function addToday(frm) {
		
		var ansType = $("select[class=ansType]").val(); 
		var goSeq =  frm.goSeq.value;
		if (frm.comment.value == "") {
			alert("내용을 입력해주세요.");
			return false;
		}
		
		var param = {
			daygroup  : frm.daygroup.value,
 			askind	: frm.askind.value,
 			goseq : goSeq,
			comment	: frm.comment.value,
			ansType	: ansType,
			maxsort : frm.maxsort.value,
			genderType : frm.genderType.value
		};
		console.log(param);
		$.ajax({
			type:"POST",
			url:"/admin/day/contents_add.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					searchList1(frm,1,frm.daygroup.value);
				}
			}
		});

		return false;
	}
	
	//답변추가
	function addanToday(frm) {
		
		var ansType = $("select[class=ansType]").val(); 
		var goseq = $("#goseq").val(); 
		var pseq = $("#pseq").val(); 
		if (frm.comment1.value == "") {
			alert("내용을 입력해주세요.");
			return false;
		}
		
		
		var param = {
			daygroup  : frm.daygroup.value,
 			askind	: frm.askind.value,
			comment	: frm.comment1.value,
			maxsort : frm.maxsort.value,
			goseq	: goseq,
			pseq	: pseq,
			genderType : frm.genderType.value
		};
		console.log(param);
		$.ajax({
			type:"POST",
			url:"/admin/day/contents_add.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					searchList1(frm,1,frm.daygroup.value);
				}
			}
		});

		return false;
	}

	function checkQuestion() {
		
		 var Count = $(".sort").length;
		 var arr = new Array();
		 var arr2 = new Array();
		 var arrSeq = "" 
		 $.each($(".sort"), function( index, obj ) {
				if($(".isLast").eq(index).is(":checked")){
					arr2.push($(obj).val());
				}else{
					arr.push($(obj).val());
				}
		            
		});
		arrSeq = arr.join(",");
		comSeq = arr2.join(",");
		
		var param = {
				comSeq	: comSeq,
				arrSeq : arrSeq
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/day/contents_isLast.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				
			}
		});
		
		return false;
	}

	
	//순서정렬
	function viewArray(){
		
		var sortCount = $(".sort").length;
		var arr = new Array();
		var arrSeq = "" ; 
		
		$.each($(".sort"), function( index, obj ) {
			arr.push($(obj).val());
		});
		
		arrSeq = arr.join(",");


		var param = {
				arrSeq  : arrSeq
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/day/contents_sort_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				
			}
		});

		return false;
		


	}
	//세팅
	function questType(frm){
		
		var askind = frm.askind.value;
		var kind = document.getElementById("kind")

		
		if(frm.askind.value == 1){
			
			kind.style.display = "block";
			kind2.style.display = "none";
		
		}
		if(frm.askind.value==2){
			
			kind.style.display = "none";
			kind2.style.display = "block";
	
		}
		
	}
	//수정
	function editgo(frm) {

		if (frm.comment.value == "") {
			alert("내용을 입력해주세요.");
			return false;
		}
		

		
		var param = {
				daySeq  : frm.daySeq.value,
				comment	: frm.comment.value,
				ansType : frm.ansType.value,
				goseq : frm.goseq.value,
				pseq : frm.pseq.value,
				
			};
			
		$.ajax({
			type:"POST",
			url:"/admin/day/contents_list_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					searchList1(frm,1,frm.daygroup.value);
				}
			}
		});

		return false;
	} 
	//삭제
	function deleteday(daySeq,daygroup,frm) {
		
		var param = {
			daySeq  : daySeq,
			daygroup  : daygroup
 			
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/day/contents_list_delete.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					searchList1(frm,1,frm.daygroup.value);
				}
			}
		});

		return false;
	}


	//컨텐츠그룹 삭제
	function deleteContentsGroup(daygroup) {
		if (confirm("컨텐츠 그룹을 삭제하시겠습니까?\n삭제된 데이터는 복구할 수 없습니다.")) {
			var param = {
				daygroup  : daygroup
			};
			console.log(param);
			$.ajax({
				type:"POST",
				url:"/admin/day/contents_group_delete.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/day/category.go";
					}
				}
			});
		}

		return false;
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
				 ■ 홈 > 닥터톡 - 컨텐츠관리 > 1일컨텐츠
			</header>
		
			<div class="contents-block">
			
				<h1>1일컨텐츠</h1>
				
				<div class="contents-main">
				
					 <form method="post" name="listForm" id="listForm" >
					 <input type="hidden" name="group" value="${group}">
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool">
									컨텐츠 그룹 : 
									<select name="daygroup" onchange="searchList1(this.form,1,this.value);">
										<c:forEach items="${daygroup }" var="it">
										 <option value="${it.daygroup }" ${it.daygroup == group ? 'selected=\"selected\"' : '' }>${it.daygroup  }</option>
										</c:forEach>
									</select>
								</div>
							</div>		
						</div>
					</form>	
						
					<div id="contents-list">
					</div>
					

				</div>

				<div style="text-align:center;  padding:10px; border: 1px solid #ddd; width: 1000px;">
					<button type="button" class="btn-red" onclick="deleteContentsGroup(${group});">컨텐츠 제거</button>
				</div>
			</div>

		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>