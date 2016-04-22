<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
	<table class="list">
	<colgroup>
		<col width="10%">
		<col width="10%">
		<col width="10%">
		<col width="*">
		<col width="20%">				
	</colgroup>
	<thead>
		<tr>
			<th>번호</th>
			<th>요청자</th>
			<th>상담원</th>
			<th>시작일</th>
			<th>종료일</th>
			<th>상담시간</th>
			<th>진행여부</th>
			<th>등록일</th>
		</tr>
	</thead>
	<tbody class="rl">
	<c:choose>
		<c:when test="${list.size() > 0}">
			<c:forEach var="it" items="${list}">
				<tr>
					<td>${it.counselSeq}</td>
					<td><a href="/admin/chat/chat_view.go?chatRoomSeq=${it.chatRoomSeq}">${it.userName}</a></td>
					<td><a href="/admin/chat/chat_view.go?chatRoomSeq=${it.chatRoomSeq}">${it.agentName}</a></td>
					<td><a href="/admin/chat/chat_view.go?chatRoomSeq=${it.chatRoomSeq}">${it.startDatetime}</a></td>
					<td>${it.endDatetime}</td>
					<td>${it.counselTime}</td>
					<td>${it.status==0 ? '대기' : it.status==1 ? '상담중' : it.status==2 ? '종료' : ''}</td>
					<td>${it.regDate.substring(0,16)}</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
		<tr>
			<td colspan="9" class="empty-data">조회된 데이터가 없습니다.</td>
		</tr>
		</c:otherwise>
	</c:choose>
	</tbody>
	</table>
		
	${paging}

	
