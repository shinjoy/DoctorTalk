<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	<c:set var="i" value="1"></c:set>
	<ul class="contents-list">
		<c:forEach var="dl" items="${list}">
			<li onclick="document.location.href='/m/notice/notice_view.go?seq=${dl.noticeSeq}';">
				<div class="bullet color-bullet${i}"></div>
				<div class="contents-info">
					<div class="contents-title-short ellipsis">${dl.title}</div>
					<div class="contents">
						<c:choose>
							<c:when test="${fn:length(dl.contentsText) > 100}">${dl.contentsText.substring(0,100)}...</c:when>
							<c:otherwise>${dl.contentsText}</c:otherwise>
						</c:choose>
					</div>
					<span class="contents-meta">${dl.regDate.substring(0,10)}</span>
				</div>
			</li>
			<c:set var="i" value="${i+1}"></c:set>
		</c:forEach>
	</ul>

	<!-- 	
	<div class="temp-more">
		<div class="read-more">
			다음 페이지를 불러오고 있습니다.
		</div>
	</div>
	 -->
	${paging}
	
