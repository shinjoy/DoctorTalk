<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<ul class="contents-list">
		<c:forEach var="dl" items="${noticeList}">
			<a href="/faq_view.go?seq=${dl.noticeSeq}&ctrl=${ctrl}" ${ctrl==1 ? 'target=\"write_frame\"' : '' }">
			<li>
				<div class="contents-info">
					<div class="contents-title-short ellipsis">${dl.noticeTitle}</div>
					<span class="contents-user">${dl.userName}</span>
					<span class="contents-meta round">${dl.regDate.substring(0,16)} 조회수:${dl.viewCount}</span>
				</div>
				<div class="contents-arrow">
					<img src="/images/img_arrow.png">
				</div>
				<div class="clear"></div>
			</li>
			</a>
		</c:forEach>
	</ul>
	
	<div class="temp-more">
		<div class="read-more">
			다음 페이지를 불러오고 있습니다.
		</div>
		${paging}
	</div>
	
