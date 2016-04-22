<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
	<table class="list">
	<colgroup>
		<col width="10%">
		<col width="*">
		<col width="20%">				
	</colgroup>
	<thead>
		<tr>
			<th>번호</th>
			<th>버전</th>
			<th>등록일</th>
		</tr>
	</thead>
	<tbody class="rl">
	<c:choose>
		<c:when test="${list.size() > 0}">
			<c:forEach var="it" items="${list}">
				<tr>
					<td><a href="/admin/update/update_edit.go?seq=${it.noticeSeq}">${it.noticeSeq}</a></td>
					<td style="text-align:left; padding-left:5px;"><a href="/admin/update/update_edit.go?seq=${it.noticeSeq}">${it.title}</a></td>
					<td>${it.regDate.substring(0,16)}</td>

				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="7" class="empty-data">조회된 데이터가 없습니다.</td>
			</tr>
		</c:otherwise>
	</c:choose>
	</tbody>
	</table>
		
	${paging}

	
