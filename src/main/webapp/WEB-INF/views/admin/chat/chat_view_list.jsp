<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
.chat-contents img { max-width:100px; cursor:pointer; }
</style>
		
	<table class="list">
	<colgroup>
		<col width="10%">
		<col width="*">
		<col width="20%">				
	</colgroup>
	<thead>
		<tr>
			<th>이름</th>
			<th>내용</th>
			<th>등록일시</th>
		</tr>
	</thead>
	<tbody class="rl">
	<c:choose>
		<c:when test="${list.size() > 0}">
			<c:forEach var="it" items="${list}">
				<tr>
					<td>${it.sndName}</td>
					<td style="text-align:left;" class="chat-contents">${it.contents}</td>
					<td>${it.regDate}</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
		<tr>
			<td colspan="3" class="empty-data">조회된 데이터가 없습니다.</td>
		</tr>
		</c:otherwise>
	</c:choose>
	</tbody>
	</table>
		
	${paging}

	
