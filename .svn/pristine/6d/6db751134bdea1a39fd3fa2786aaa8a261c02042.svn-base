<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script>


<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(2,1);
	searchList(listForm,1);
});


function searchList(listForm,page) {
	var param = {
		keyword		: 	listForm.keyword.value,
		startDate   :   listForm.startDate.value,
		endDate     :   listForm.endDate.value,
		ready       :	listForm.ready.checked,
		complete    :   listForm.complete.checked,
		page		:	page
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/assay/assay_list.go",
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


</script>

<style>
.btn-analy { margin-left:-450px;}
</style>


</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 분석관리 > 분석 목록
			</header>
		
			<div class="contents-block">
			
				<h1>분석 목록</h1>
				
				<div class="contents-main">

					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool">
									<input type="text" name="startDate" class="itext datepicker" value="${startDate}" placeholder="시작일" style="width:80px;" onchange="startChange(listForm);">
									<input type="text" name="endDate" class="itext datepicker" value="${endDate}" placeholder="종료일" style="width:80px;">
									<input type="text" name="keyword" value="${keyword}" placeholder="아이디 검색" class="itext">
									<label><input type="checkbox" name="ready" value="1" checked="checked">분석대기</label>
									<label><input type="checkbox" name="complete" value="1" >분석완료</label>
									<button type="submit" class="btn">검색</button>
								</div>
								<div class="btn-tools"><button class="btn">엑셀 다운로드</button></div>
							</div>
						</div>

						<div id="contents-list" >
						</div>
					
					</form>
					
				</div>
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>