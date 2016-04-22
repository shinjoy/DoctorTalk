<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
		<table class="list">
		<colgroup>
			<col width="10%">
			<col width="20%">
			<col width="10%">
			<col width="20%">
			<col width="10%">
			<col width="10%">
		</colgroup>
			<thead>
				<th>이름</th>
				<th>아이디</th>
				<th>생년월일</th>
				<th>질환</th>
				<th>가입경로</th>
				<th>가입일</th>
			</thead>
		
			<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					<c:forEach var="it" items="${list}">
					<tr>
						<td><a href="/admin/user/user_view.go?userId=${it.userId}">${it.userName}</a></td>
						<td style="text-align:left; padding-left:5px;"><a href="/admin/user/user_view.go?userId=${it.userId}">${it.userId}</a></td>
						<td>${it.birthday}</td>
						<td>
							<c:if test="${it.blood == 'blood'}">혈당</c:if>
							<c:if test="${it.press == 'press'}">고혈압</c:if>
							<c:if test="${it.col == 'col'}">콜레스테롤</c:if>
							<c:if test="${it.heiwieght == 'heiwieght'}">비만</c:if>
						</td>																
						<td>
							<c:choose>
								<c:when test="${it.loginNaver==1}">네이버</c:when>
								<c:when test="${it.loginKakao==1}">카카오</c:when>
								<c:when test="${(it.aimmedId==null || it.aimmedId=='') == false}">에임메드</c:when>
								<c:otherwise>리커버</c:otherwise>
							</c:choose>
						</td>
						<td>${it.regDate.substring(0,16)}</td>
					</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan="7" style="height:200px;">조회된 데이터가 없습니다.</td>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
			
		${paging}
		
	
	
