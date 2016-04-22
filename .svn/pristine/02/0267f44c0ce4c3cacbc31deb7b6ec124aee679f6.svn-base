<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
$(document).ready(function() {
	$("#user-menu5 A").css("color","#000");	
	searchList(listForm,1);
});

function searchList(listForm,page) {
	var param = {
		keyword		: 	listForm.keyword.value,
		page		:	page
	};
	
	$.ajax({
		type:"POST",
		url:"/user/notice/notice_list.go",
		dataType:"html",
		data:param,
		success:function(msg){
			$("#contents").html(msg);
		}
	});
	return false;
}

</script>

<style>
.login-title { margin-top:70px; margin-left:30px; font-weight:bold; font-size:20px; color:#000;}	
.gray-top { width:1000px; padding-bottom:20px; border-bottom:1px solid #ddd;}
.tbl-list TH { border-bottom: 1px solid #ddd; text-align:center; padding:10px;}
.tbl-list TD { border-bottom: 1px solid #ddd; text-align:center; padding:3px;}
</style>


</head>
<body >
	
		<div class="login-wrap">
	
		<%@ include file="/WEB-INF/views/home_header.jsp"%> 	
		
		<div class="login-content">
			<div class="inner">
					<div class="login-title">공지사항</div>
				<div class="gray-top"></div>
	
				<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
					<div class="search-sec" >
<!-- 						<div class="search"> -->
<%-- 							<input type="text" name="keyword" value="${keyword}" placeholder="제목/내용 검색" class="fbox wmidium">	 --%>
<!-- 							<button type="button" class="btn" onclick="searchList(this.form,1);">검색</button>				 -->
<!-- 						</div> -->
					</div>
	
					<div id="contents"  style="padding-left:30px; padding-top:30px; background-color:#fff;">
					</div>
				
			</div>
			
		</div>
	
					<div class="search" style="margin-left:400px; margin-top:20px;">
						<input type="text" name="keyword" value="${keyword}" placeholder="제목/내용 검색" class="itext">	
						<button type="button" class="btn" onclick="searchList(this.form,1);">검색</button>				
					</div>
				</form>
	
		<div style="padding-bottom:30px;"></div>
		
	<div class="clear"></div>
		
	<%@ include file="/WEB-INF/views/home_footer.jsp"%> 	     
	</div>

</body>
</html>