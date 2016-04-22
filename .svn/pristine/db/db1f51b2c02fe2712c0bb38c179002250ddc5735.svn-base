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
	var page = frm.page.value;
	var keyword = frm.keyword.value;
	var ctrl = frm.ctrl.value;
	var category = frm.category.value;
	if (p>0) {
		page = p;
	}
	
	var params = {
		page : page,
		keyword : keyword,
		ctrl : ctrl,
		category : category
		
	}
	
	console.log("params : ",params);
	
	$.ajax({
		type:"POST",
		url:"/m/faq/faq_list.go",
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
	

	
// 	alert($(obj).parent().children(".faq-contents").height());
// 		$('#container').css({'height':Cheight+'px'});
// 	var maxHeight = $(".faq-contents").height();
// 	var heightc = $("P").height();
// 	 var objSet   = $(".faq-contents").height();
// 	  var objTarHeight= document.getElementByClass(contents).offsetHeight;




	//$(obj).parent().find(".faq-contents").css("display","block");
	 //var h = $(obj).parent().find(".faq-contents").innerHeight();
	 var Cheight = $(window).height();
	 var h = $(obj).parent().find("p").innerHeight();
	
	if ($(obj).parent().children(".faq-contents").css("display") == "block") {
		
		
		$(obj).parent().children(".faq-contents").animate(500, function() {
			$(obj).parent().children(".faq-contents").css("display","none");
		});
		$(obj).children(".title-cell").removeClass("active");
		$(obj).parent().children(".faq-contents").removeClass("active");
		$(obj).children(".title-re").removeClass("active") 
		$(obj).children(".title-re").children(".arrow").attr("src","/images/bullet_arrow_right.png");

	} else {
		$(obj).parent().children(".faq-contents").css("display","block");
		$(obj).parent().children(".faq-contents").animate(500);
		$(obj).children(".title-cell").addClass("active");
		$(obj).children(".title-re").addClass("active") 
		$(obj).children(".title-re").children(".arrow").attr("src","");
		$(obj).parent().children(".faq-contents").addClass("active");
		
	} 
	
	
	/*
	$(".open .faq-contents").html("");
	$(".open").animate({height:25},100,null);
// 	$(".open .arrow").attr("src","/images/bullet_arrow_right.png");

	if ($(obj).hasClass("open")) {
		$(".open").removeClass("open");
		$(obj).removeClass("open");

		$(obj).children(".faq-contents").html("");
		$(obj).animate({height:25},100,null);

	} else {
		$(".open").removeClass("open");

		var params = {
			seq : seq
		}
		
		$.ajax({
			type:"POST",
			url:"/m/faq/faq_view.go",
			dataType:"html",
			data:params,
			success:function(msg){
				$(obj).children(".faq-contents").html(msg);
				var h = $(obj).children(".faq-contents").innerHeight()+60;
				if (h < 380) { h=380; }
				$(obj).animate({height:h},200,function() {
					$('html,body').animate({scrollTop:$(obj).offset().top}, 500);
				});
				$(obj).children(".title-cell").addClass("active");
				$(obj).children(".title-cell").html("<style =\background-color:#87b231;\> ");
// 				$(obj).children(".contents-info").addClass("active");
// 				$(obj).children(".contents-title-faq").html("<style =\background-color:#87b231;\> ");
// 				$(obj).children(".faq-contents").html("<style =\background-color:#0f2334;\> ");
				$(obj).addClass("open");
			}
		});
	}
	*/
}

</script>

</head>

<body class="faq">

	<form method  ="post" name="listForm" style="height:100%;">
	<input type="hidden" name="page" value="${page}">
	<input type="hidden" name="keyword" value="${keyword}">
	<input type="hidden" name="ctrl" value="${ctrl}">
	<input type="hidden" name="category" value="${category}">

	
		<div id="list-view" style="height:100%;">
		

		</div>
		
	</form>

</body>

</html>