<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
		<div class="tbl-list">
			<table>
				<tbody class="rl">
				<c:choose>
					<c:when test="${list.size() > 0}">
						<c:forEach var="it" items="${list}">
							<tr>
								<td><a href="/admin/device/modify.go?scrapSeq=${it.scrapSeq}">${it.scrapSeq}</a></td>
								<td><a href="/admin/device/modify.go?scrapSeq=${it.scrapSeq}">${it.phoneNumber}</a></td>
								<td>${it.keyword}</td>
								<td>${it.searchCount}</td>
								<input type="hidden" value="${it.successCount}">
								<input type="hidden" value="${it.failCount}">
								<c:choose>
									<c:when test="${it.successCount+it.failCount==it.searchCount}">
											<td>완료</td>
									</c:when>
									<c:otherwise>
											<td>진행중</td>
									</c:otherwise>
								</c:choose>
								<td>${it.regDate.substring(0,16)}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr style="border-bottom:1px solid #ddd;" >
<!-- 						<td colspan="7" style="height:200px;">조회된 데이터가 없습니다.</td> -->
							<td>홍길동 ㅣ2015-06-15 12:20
								<br>
								저렴하면서도 맛있게 잘 먹었어요. 
							</td>
						</tr>
						<tr>
<!-- 						<td colspan="7" style="height:200px;">조회된 데이터가 없습니다.</td> -->
							<td>홍길동 ㅣ2015-06-15 12:20
								<br>
								저렴하면서도 맛있게 잘 먹었어요. 
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		</div>
		
		${paging}
