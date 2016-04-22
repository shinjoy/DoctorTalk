<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header5.jsp"  %>
<script>
/**
 * 본문 이미지 크기 조정

$(window).load(function(){
	//alert($(window).width());
	// 이미지 크기조정
	$("img").each(function() {   
		if ($(this).width() > $(window).width())
		{
			//alert($(this).width()+","+$(window).width()); // this 는 img 객체를 의미
			$(this).width($(window).width());
		}
		$(this).width(100);

	}); 
	// iframe에 YouTube가 있을 경우
	$("iframe").each(function() {   
		if ($(this).width() > $(window).width())
		{
			//alert($(this).width()+","+$("#view .contents_area").width()); // this 는 img 객체를 의미
			$(this).width($(window).width());
		}
	}); 
});
 */
</script>
<style>
<!--
	.contents_area img {
		width:100%;
	}
-->
</style>
</head>
<body>
	
	<div class="contents_area"  style="padding:10px;">
		${campaign.contentInfo }
	</div>

</body>
</html>