<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
 		aside.setActive(1,5);
		searchList(listForm,1);
	});

	function searchList(listForm,page) {
		var param = {
			keyword		: 	listForm.keyword.value,
			page		:	page
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/statistics/statistics_list.go",
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
.device { font-weight: bold; font-size:25px; color:#464242; }	
.guard { border-bottom: 3px solid #535b60; padding-top:15px; width: 1000px;}	
.search-sec .down { float: left; padding-left:680px; text-align: right; }
</style>


</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 업체 통계
			</header>
		
			<div class="contents-block">
			
				<h1> 업체 통계</h1>
				
				<div class="contents-main">
				
					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool">
									<input type="text" name="startDate" class="itext datepicker" value="${startDate}" placeholder="시작일" style="width:80px;" onchange="startChange(listForm);">
									<input type="text" name="endDate" class="itext datepicker" value="${endDate}" placeholder="종료일" style="width:80px;">
									<input type="text" name="keyword" value="${keyword}" placeholder="제목/내용 검색" class="itext">
									<button type="submit" class="btn" >검색</button>
								</div>

							</div>
						</div>
						
						<div id="contents-list">
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