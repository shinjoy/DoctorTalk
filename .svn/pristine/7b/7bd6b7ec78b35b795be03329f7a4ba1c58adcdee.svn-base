<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script>


<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(1,3);
	searchList(1);
});


function searchList(page) {
	var param = {
		page		:	page
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/user/point_list.go",
		dataType:"html",
		data:param,
		success:function(msg){
			$("#contents-list").html(msg);
		}
	});
	return false;
}

function submitForm(pointForm) {

	if (pointForm.eventName.value == "") {
		alert("이벤트 이름을 입력해주세요.");
		return false;
	}
	if (pointForm.point.value == "") {
		alert("포인트를 입력해주세요.");
		return false;
	}
	if (pointForm.money.value == "") {
		alert("F-Money를 입력해주세요.");
		return false;
	}
	
	if (pointForm.times.value == "") {
		alert("횟수를 선택해주세요.");
		return false;
	}
	
	var param = {
			pointSeq : pointForm.pointSeq.value,
			eventName : pointForm.eventName.value,
			point : pointForm.point.value,
			money : pointForm.money.value,
			period : pointForm.period.value,
			times : pointForm.times.value,
			commend : pointForm.commend.value
			
	};
	
	
	$.ajax({
		type:"POST",
		url:"/admin/user/point_edit_do.go",
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


function pointEditForm(frm) {

	if (frm.eventName1.value == "") {
		alert("이벤트 이름을 입력해주세요.");
		return false;
	}
	if (frm.point1.value == "") {
		alert("포인트를 입력해주세요.");
		return false;
	}
	if (frm.money1.value == "") {
		alert("F-Money를 입력해주세요.");
		return false;
	}
	
	if (frm.times1.value == "") {
		alert("횟수를 선택해주세요.");
		return false;
	}
	
	var param = {
			pointSeq : frm.pointSeq.value,
			eventName : frm.eventName1.value,
			point : frm.point1.value,
			money : frm.money1.value,
			period : frm.period1.value,
			times : frm.times1.value,
			commend : frm.commend1.value
	};
	
	
	$.ajax({
		type:"POST",
		url:"/admin/user/point_edit_do.go",
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
									<button type="button" class="tab active" onclick="document.location.href='/admin/user/set_point.go';"><span>포인트 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point_charge.go';"><span>포인트 충전 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_point_money.go';"><span>포인트/Money 전환 설정</span></button>
									<button type="button" class="tab" onclick="document.location.href='/admin/user/set_level.go';"><span>레벨 정책 설정</span></button>
								</div>		
							</div>
						</div>

						<div id="contents-list">
						</div>

				</div>
<!-- 			</div> -->
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>