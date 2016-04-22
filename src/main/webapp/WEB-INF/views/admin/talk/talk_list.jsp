<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
	<div style="border-top:1px solid #ddd;">	
		<table class="menuedit" style="width:100%">
			<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					<c:forEach var="it" items="${list}">
						<tr>
							<td rowspan="2" style="width:100px;">
								<c:choose>
									<c:when test="${!empty fn:trim(it.bbsContents)}">	
<%-- 									<img src="/files/company/thumb/${it.bbsContents}" style="height:100px;"> --%>
									<a href="/admin/talk/talk_view.go?bbsSeq=${it.bbsSeq}"><img src="/images/1.jpg" style="height:70px;"></a>
									</c:when>
									
									<c:otherwise>
										 등록된 이미지가 없습니다.
									</c:otherwise>
								</c:choose>
								
								</td>
								<td style="text-align:left; padding-left:10px;">
									<a href="/admin/talk/talk_view.go?bbsSeq=${it.bbsSeq}">${it.userName} 
										${it.genderText} | ${it.userAge} | ${it.area}
									 </a>
								</td>
							</tr>
							<tr>
							<td style="text-align:left; padding-left:10px;">
								♥좋아요 ${it.likeCount} ♠댓글 ${it.commentCount} ♣신고 ${it.reportCount} ${it.regDate.substring(0,16)}
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
				<tr>
					<td colspan="7" style="height:200px; text-align:center; margin-top:50px;">조회된 데이터가 없습니다.</td>
				</tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
	</div>		
		${paging}
		
	
	
