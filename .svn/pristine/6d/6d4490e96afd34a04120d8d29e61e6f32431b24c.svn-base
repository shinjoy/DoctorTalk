<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
		<table class="list">
		<thead>
			<tr>
				<th>이메일</th>
				<th>이름</th>
				<th>등록일자</th>
				<th>관리</th>
			</tr>
		</thead>
		<tbody class="rl">
		<c:choose>
			<c:when test="${list.size() > 0}">
				<c:forEach var="it" items="${list}">
					<tr>
						<td style="text-align:left; padding-left:15px;">${it.userId}</td>
						<td>${it.userName}</td>
						<td>${it.regDate.substring(0,16)}</td>
						<td><button type="button" class="btn tiny" onclick="deleteAnalyst('${it.userId}');">삭제</button></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="7" style="height:200px;">조회된 데이터가 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
		</tbody>
		</table>
		${paging}
			
	
	
	
	

