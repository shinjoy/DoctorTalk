<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script>


<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(1,1);
	searchList(1);
});


function searchList(page) {
	var param = {
		page		:	page

		
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/user/level_list.go",
		dataType:"html",
		data:param,
		success:function(msg){
			$("#contents-list").html(msg);
		}
	});
	return false;
}

$(function() {
    $.datepicker.setDefaults( $.datepicker.regional[ "ko" ] );
    $( ".datepicker" ).datepicker(
        {
        	dateFormat: 'yy-mm-dd',
 	       showButtonPanel: true
        }
    );
}); 


function searchCategory(frm) {
	
	if(frm.category.value == "10"){
		$('categoryName').find('option:first').attr('selected', 'selected');
	}
	
	var param = {
		parentSeq		: 	frm.category.value
	};
	
			
	$.ajax({
		type:"POST",
		url:"/admin/catory/catory.go",
		dataType:"json",
		data:param,
		success:function(json){
			var list = json.list;
			var str = '<option value="">== 검색결과 ==</option>';
			
			for (var i=0; i<list.length; i++) {
				str += '<option value="'+list[i].cateSeq+'">'+list[i].categoryName+'</option>';
			}
			$("#categoryName").html(str);
		}
	});
 
	return false;
}

function submitForm(pointForm) {

	if (pointForm.level.value == "") {
		alert("레벨를 입력해주세요.");
		return false;
	}
	if (pointForm.minEx.value == "") {
		alert("최소 경험치를 입력해주세요.");
		return false;
	}
	if (pointForm.maxEx.value == "") {
		alert("최대 경험치를 입력해주세요.");
		return false;
	}
	
	
	var param = {
			levelSeq : pointForm.levelSeq.value,
			level : pointForm.level.value,
			minEx : pointForm.minEx.value,
			maxEx : pointForm.maxEx.value
	};
	
	
	$.ajax({
		type:"POST",
		url:"/admin/user/level_edit_do.go",
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




function submitLevelForm(levelForm) {

	if (levelForm.level.value == "") {
		alert("레벨를 입력해주세요.");
		return false;
	}
	if (levelForm.minEx.value == "") {
		alert("최소 경험치를 입력해주세요.");
		return false;
	}
	if (levelForm.maxEx.value == "") {
		alert("최대 경험치를 입력해주세요.");
		return false;
	}
	
	
	var param = {
			levelSeq : pointForm.levelSeq.value,
			level : levelForm.level.value,
			minEx : levelForm.minEx.value,
			maxEx : levelForm.maxEx.value
	};
	
	
	$.ajax({
		type:"POST",
		url:"/admin/user/level_edit_do.go",
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



</script>

</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 회원관리 > 포인트 설정
			</header>
		
			<div class="contents-block">
			
				<h1>포인트 설정</h1>
				
<!-- 				<div class="contents-main"> -->
						<div class="contents-top">
							<div class="top-tools">
								<div class="tab-bar">
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point.go';"><span>포인트 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point_charge.go';"><span>포인트 충전 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point_money.go';"><span>포인트/Money 전환 설정</span></button>
									<button type="button" class="tab active" onclick="document.location.href='/admin/user/set_level.go';"><span>레벨 정책 설정</span></button>
								</div>		
							</div>
						</div>

						<div id="contents-list">
						</div>

				</div>
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>