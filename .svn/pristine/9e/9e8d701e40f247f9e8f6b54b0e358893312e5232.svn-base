<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header5.jsp"  %>

	<link rel="stylesheet" type="text/css" href="/css/bb-admin-mobile.css" />
	
<script type="text/javascript">
$(document).ready(function() {
	// 힐링센터 검색
	searchList(1);
	
	// 하단까지 스크롤 되면 자동 더보기 실행
	$("#list-view").scroll( function() {
	    var offsetHeight = $(this).prop('offsetHeight');
	    var scrollTop= $(this).scrollTop();
	    var scrollHeight= $(this).prop('scrollHeight');
	    
	    
	    if ((offsetHeight + scrollTop) >= scrollHeight){
	    	var frm = listForm;
	    	var i = frm.currentPage.length;
	    	var currentPage;
	    	var maxPage;
	    	if (i == undefined) {
		    	currentPage = frm.currentPage.value;
		    	maxPage = frm.maxPage.value;
	    	} else {
		    	currentPage = frm.currentPage[i-1].value;
		    	maxPage = frm.maxPage[i-1].value;
	    	}
			if (currentPage < maxPage) {
				frm.page.value = parseInt(currentPage) + 1;
				searchList();
			} else {
				$(".read-more").html("더이상 자료가 없습니다.");
			}
	    }
	});
});

// 검색
function searchList(p) {
	var frm = listForm;
	var params = "";
	var page = frm.page.value;
	var keyword = frm.keyword.value;
	var ctrl = frm.ctrl.value;
	if (p>0) {
		page = p;
	}

	params = "page="+page+"&keyword="+keyword+"&ctrl="+ctrl;
	console.log("params : ",params);
	
	$.ajax({
		type:"POST",
		url:"/m/notice/notice_list.go",
		dataType:"html",
		data:params,
		success:function(msg){
			$(".temp-more").html("");
			$("#list-view").append(msg);
			var maxPage = frm.maxPage.value;
			if (maxPage == 1) {
				$(".temp-more").html("");
			}
		}
	});
}
function readContents(seq, obj) {
	
	$(".open .contents").html("");
	$(".open").animate({height:55},100,null);
	$(".open .arrow").attr("src","/images/bullet_arrow_right.png");

	if ($(obj).hasClass("open")) {
		//$(".open").removeClass("open");
		$(obj).removeClass("open");

		$(obj).children(".contents").html("");
		$(obj).animate({height:55},100,null);
		$(obj).children(".arrow").attr("src","/images/bullet_arrow_right.png");

	} else {
		$(".open").removeClass("open");

		var params = {
			seq : seq
		}
		
		$.ajax({
			type:"POST",
			url:"/m/notice/notice_view.go",
			dataType:"html",
			data:params,
			success:function(msg){
				$(obj).children(".contents").html(msg);
				var h = $(obj).children(".contents").innerHeight()+60;
				if (h < 380) { h=380; }
				$(obj).animate({height:h},200,function() {
					$('html,body').animate({scrollTop:$(obj).offset().top}, 500);
				});
				$(obj).children(".contents-arrow").html("<img src=\"/images/bullet_arrow_down.png\" class=\"arrow\" style=\"width:30px;\">");
				$(obj).addClass("open");
				//$(obj).children(".contents-arrow .arrow").rotate({animateTo:-90});
			}
		});
	}
}
</script>

</head>

<body class="white-background">

	<form metohd="post" name="listForm" style="height:100%;">
	<input type="hidden" name="page" value="${page}">
	<input type="hidden" name="keyword" value="${keyword}">
	<input type="hidden" name="ctrl" value="${ctrl}">

		<div id="list-view" style="height:100%;">
		</div>
		
	</form>

</body>

</html>