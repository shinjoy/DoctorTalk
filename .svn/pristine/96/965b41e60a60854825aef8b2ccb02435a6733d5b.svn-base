<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<ul class="contents-list">
		<c:forEach var="dl" items="${list}">
			<li onclick="readContents(${dl.noticeSeq},this);">
				<div class="contents-arrow">
					<img src="/images/bullet_arrow_right.png" class="arrow" style="width:30px;">
				</div>
				<div class="contents-info">
					<div class="contents-title-short ellipsis">${dl.title}</div>
					<span class="contents-meta round">${dl.regDate.substring(0,16)}</span>
				</div>
				<div class="clear"></div>
				<div class="contents">
				</div>
				<div class="clear"></div>
			</li>
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
	
