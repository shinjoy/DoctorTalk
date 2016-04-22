<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
		<div class="tbl-list">
			<table class="request">
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
						<tr>
							<th><div class="icon"> 메뉴명</div></th>
							<td>후라이드</td>
						</tr>
						<tr>
							<th><div class="icon"> 한줄 소개</div></th>
							<td>바삭바삭 치킨</td>
						</tr>
						
						<tr>
							<th><div class="icon">가격</div></th>
							<td>16,000</td>
						</tr>
						<tr>
							<th><div class="icon">소개글</div></th>
							<td>친절한 사장님</td>
						</tr>
						<tr>
							<th><div class="icon">이용안내</div></th>
							<td>16:00~25:00</td>
						</tr>
						<tr>
							<th>소개사진</th>
							<td>	
								<img src="/images/1.jpg"  style="height:100px;">
<%-- 								<img src="/files/imagefile/${analysis.photo3}" style="max-width:100%;"> --%>
								<br>
								<button type="button" class="btn tiny" onclick="deleteImg('${analysis.seq}','${analysis.photo3}');">삭제</button>	
<!-- 								<img src="/images/1.jpg"  style="height:100px;"> -->
<!-- 								<br> -->
<%-- 								<button type="button" class="btn tiny" onclick="deleteImg('${analysis.seq}','${analysis.photo3}');">삭제</button>	 --%>
<!-- 								<img src="/images/1.jpg"  style="height:100px;"> -->
<!-- 								<br> -->
<%-- 								<button type="button" class="btn tiny" onclick="deleteImg('${analysis.seq}','${analysis.photo3}');">삭제</button>	 --%>
							</td>
						</tr>	
						<tr>
							<th><div class="icon">배송(배달)안내</div></th>
							<td>배송 가능</td>
						</tr>					
						
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		
		</div>			
	
		<div>
			<button class="btn" style="margin-top:10px; margin-left:10px;" onclick="document.location.href='/admin/firm/menu_edit.go';" >메뉴수정</button>
			<button class="btn" style="margin-top:10px; margin-left:10px;" onclick="document.location.href='/admin/firm/menu_edit.go';" >메뉴삭제</button>
			<button class="btn" style="margin-top:10px; margin-left:10px;" onclick="document.location.href='/admin/firm/menu_view.go';" >메뉴목록</button>
		</div>						
				