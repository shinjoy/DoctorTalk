<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
	<h1><img src="/images/logo_top.png" alt="SMART iDEA"></h1>
	<nav>
		<ul>
			<c:choose>
				<c:when test="${ tab == 1 }">
					<li><a href="/main.do" class="active tab">Home</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/main.do" class="tab">Home</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${ tab == 2 }">
					<li><a href="/store.do" class="active tab">스토어</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/store.do" class="tab">스토어</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${ tab == 3 }">
					<li><a href="/event.do" class="active tab">이벤트</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/event.do" class="tab">이벤트</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="javascript:openHotdealPop();" class="hotdeal_btn">HOT딜</a></li>
		</ul>
	</nav>		
</header>

