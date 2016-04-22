<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/header5.jsp"  %>

<script type="text/javascript">
window.fbAsyncInit = function() {
    FB.init({
      appId      : '748163835248059',
      xfbml      : true,
      version    : 'v2.0'
    });
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/ko_KR/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));


</script>

<style type="text/css">
.maga td { padding:10px;} 
</style>

</head>

<body class="magazine">
	
	<div class="magazine-page"  >
		<div class="back"><a href="javascript:history.back(-1);"><img src="/images/bullet_arrow_left.png"></a></div>
		<c:choose>
			<c:when test="${sub.pageFilename!=''}">
				<div class="image"><img src="/files/submagazine/${sub.pageFilename}"></div>
				
				<p class="title">${sub.pageTitle }</p>
				<p class="contents">
					<% pageContext.setAttribute("LF", "\n"); %>
					${fn:replace(sub.pageContents, LF,'<br>')}
				</p>
			</c:when>
			<c:otherwise>
				<div style="height:60px;"></div>
				
				<p class="title">${sub.pageTitle }</p>
				<p class="contents">
					<% pageContext.setAttribute("LF", "\n"); %>
					${fn:replace(sub.pageContents, LF,'<br>')}
				</p>
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>