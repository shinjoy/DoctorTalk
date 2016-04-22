<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script>
<link rel="stylesheet" media="screen" type="text/css" href="css/colorpicker.css" />
<script type="text/javascript" src="js/colorpicker.js"></script>


<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(4,1);
	searchList(listForm,1);
});



function searchList(listForm,page) {
	var param = {
// 		 
		page		:	page
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/push/push_list.go",
		dataType:"html",
		data:param,
		success:function(msg){
			$("#contents-list").html(msg);
		}
	});
	return false;
}



</script>

<style>
.push-listdiv.items {
	width: 500px;
	float: left;
}

.push-list div img {
	float: left;
	height: 150px;
	width: 150px;
	margin-right:10px;
}
</style>


</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 공지사항 > 푸시광고 관리
			</header>
		
			<div class="contents-block">
			
				<h1>푸시광고 관리</h1>
				
				<div class="contents-main">

					<form method="post" name="listForm" id="listForm" enctype="multipart/form-data" onsubmit="return searchList(this,1); return false;">
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool">		
<%-- 									<input type="text" name="keyword" value="${keyword}" placeholder="업체명 검색" class="itext"> --%>
<!-- 									<button type="submit" class="btn">검색</button> -->
								</div>
								<div class="btn-tools"><button type="button" class="btn" onclick="document.location.href='/admin/push/push_edit.go?seq=0'">푸시 광고 등록</button></div>
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