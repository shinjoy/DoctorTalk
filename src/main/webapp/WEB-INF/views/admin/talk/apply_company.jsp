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
	//searchList(listForm,1);
});


function searchList(listForm,page) {
	var param = {
		keyword		: 	listForm.keyword.value,
		page		:	page,
		category1   : 	listForm.category.value,
		category2	:	listForm.categoryName.value
		
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/firm/firm_list.go",
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
				 ■ 홈 > 업체관리 > 가맹신청 업체
			</header>
		
			<div class="contents-block">
			
				<h1>가맹신청 업체</h1>
				
				<div class="contents-main">

					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool">
									<select onchange="searchCategory(this.form);" name="category">
										<option value="10">=선택=</option>
										<option value="1">일반음식점</option>
										<option value="2">배달음식점</option>
										<option value="3">유통/서비스</option>
										<option value="4">생활편의</option>									
									</select>
									<select name="categoryName" id="categoryName" style="width:106px;">
										<option value="0">== 검색 ==</option>
									</select>		
									<input type="text" name="keyword" value="${keyword}" placeholder="업체명 검색" class="itext">
									<button type="submit" class="btn">검색</button>
								</div>
								<div class="btn-tools"><button class="btn">엑셀 다운로드</button></div>
							</div>
						</div>

						<div id="contents-list">
							<div style="text-align:center; height:100px; padding-top:80px;">준비중입니다.</div>
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