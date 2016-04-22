<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<!-- <script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script> -->


<script type="text/javascript">

$(document).ready(function() {
	$("#user-menu3 A").css("color","#000");	
	searchList(listForm,1);
});

function searchList(listForm,page) {
	var param = {
		startDate   :   listForm.startDate.value,
		endDate     :   listForm.endDate.value,
		ready       :	listForm.ready.checked,
		complete    :   listForm.complete.checked,
		page		:	page
	};
	
	$.ajax({
		type:"POST",
		url:"/user/assay/assay_list.go",
		dataType:"html",
		data:param,
		success:function(msg){
			$("#analy-list").html(msg);
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

.gray-top { width:1000px; padding-bottom:20px; border-bottom:1px solid #ddd;}

.search-sec .sea {
  float: left;
  padding: 5px;
}
.imglist { width:180px; float:left;  padding-top:70px; margin-left:-350px;}

.login-title { margin-top:70px; margin-left:30px; font-weight:bold; font-size:20px; color:#000;}	

.round-box { border-radius:5px; background-color:#20BBD9; padding:5px;}

</style>


</head>
<body>
		<div class="login-wrap">
	
		<%@ include file="/WEB-INF/views/home_header.jsp"%> 	
		
		<div class="login-content">
			<div class="inner">
			
				<div class="login-title">분석 목록</div>
				
				<div class="gray-top"></div>

				<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
					<div class="search-sec" style="padding-left:10px;" >
						<div class="sea">
							<input type="text" name="startDate" class="itext datepicker" value="${startDate}" placeholder="시작일" style="width:80px;" onchange="startChange(listForm);">
							<input type="text" name="endDate" class="itext datepicker" value="${endDate}" placeholder="종료일" style="width:80px;">
							<label><input type="checkbox" name="ready" value="1" checked="checked">분석대기</label>
							<label><input type="checkbox" name="complete" value="1" >분석완료</label>
							<button type="submit" class="btn">검색</button>
						</div>
					</div>
				</form>	
					<div id="analy-list" >
					
					</div>
			</div>
			
		</div>
		
		<div ></div>
		
		
		<div style="padding-bottom:30px;"></div>
		
	<div class="clear"></div>
		
	<%@ include file="/WEB-INF/views/home_footer.jsp"%> 	     
	</div>


</body>
</html>