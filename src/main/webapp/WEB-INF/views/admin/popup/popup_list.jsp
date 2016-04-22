<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
	<div class="push-list">
		<c:choose>	
		<c:when test="${list.size() > 0}">
			<c:forEach var="it" items="${list}">
				<div class="items" style="margin-left:30px;">
						<ul style="display:table; width:200px; float:left; padding-bottom:20px;">
							<li style="display:table-cell;" >
								<a href="/admin/popup/popup_edit.go?seq=${it.noticeSeq}"> <img src="/files/popup/${it.contentsHtml}" style="max-height:200px;"> </a><br>
								${it.startDate.substring(0,10)}~${it.endDate.substring(0,10)}
							</li>
						</ul>
					</div>
			</c:forEach>
		</c:when>
			<c:otherwise>
					<ul style="text-align:center;"><li>조회된 데이터가 없습니다.</li></ul>
			</c:otherwise>
		</c:choose>
		<br>
		<div style="clear:both;"></div>
	</div>
	${paging}
	
