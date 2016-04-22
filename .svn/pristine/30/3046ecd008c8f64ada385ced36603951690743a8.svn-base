<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(1,1);
	searchList(listForm,1);
});

function searchList(listForm,page) {
	var param = {
		keyword		: 	listForm.keyword.value,
		page		:	page
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/member/member_list.go",
		dataType:"html",
		data:param,
		success:function(msg){
			$("#contents-list").html(msg);
		}
	});
	return false;
}

//액셀 다운로드
function excelDownload() {
	var frm = listForm;
	document.location.href = "/admin/member/member_list_excel.go";
}

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
				 ■ 홈 > 회원관리 > 회원목록
			</header>
		
			<div class="contents-block">
			
				<h1>회원 목록</h1>
				
				<div class="contents-main">

					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool">
									<input type="text" name="keyword" value="${keyword}" placeholder="아이디 검색" class="itext">
									<button type="submit" class="btn" onclick="searchList(this.form,1);">검색</button>
								</div>
								<div class="btn-tools"><button class="btn">엑셀 다운로드</button></div>
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