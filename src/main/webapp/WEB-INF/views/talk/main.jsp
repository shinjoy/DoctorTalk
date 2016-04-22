<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/talk/header5.jsp"  %>

<script>

chat.setSocket("http://218.234.17.221:15010");



</script>

</head>
<body style="overflow-y:hidden;">

<div class="wrap">
<aside class="frame-left">
	<iframe name="aside" src="/talk/talk.go"></iframe>
</aside>

<div class="frame-line"></div>

<article class="frame-right">
	<iframe name="contents" src="/talk/talk_list.go"></iframe>
</article>
</div>

</body>
</html>
