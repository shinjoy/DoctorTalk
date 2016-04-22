<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
		<table class="list">

			<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					<c:forEach var="it" items="${list}">
						<tr>
							<td style="text-align:left; padding-left:15px;">${it.userId}</td>
							<td>${it.userName}</td>
							<td>${it.userAge}</td>
							<td>${it.genderText}</td>
							<td>${it.regDate.substring(0,16)}</td>
	
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
				<tr>
	<!-- 							<td colspan="7" style="height:200px;">조회된 데이터가 없습니다.</td> -->
					<td rowspan="2"><img src="/images/star.png"></td>
					<td style="text-align:left; padding-left:10px;">
						<a href="/admin/firm/firm_view.go">맛있는집&nbsp;02-123-1234&nbsp;[가맹]현금:5% | 신용카드:5% | 다모인카드:10%</a>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:left; padding-left:10px;">배달가능 ㅣ 메뉴 6개ㅣ 서울 동작구 사당동 13길 10 </td>
				</tr>
								<tr>
	<!-- 							<td colspan="7" style="height:200px;">조회된 데이터가 없습니다.</td> -->
					<td rowspan="2"><img src="/images/star.png"></td>
					<td style="text-align:left; padding-left:10px;">
						<a href="/admin/firm/firm_view.go">맛있는집&nbsp;02-123-1234&nbsp;[가맹]현금:5% | 신용카드:5% | 다모인카드:10%</a>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:left; padding-left:10px;">배달가능 ㅣ 메뉴 6개ㅣ 서울 동작구 사당동 13길 10 </td>
				</tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
			
		${paging}
		
	
	
