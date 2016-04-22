<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
	<div class="tbl-list" style="width:950px; border-top:1px solid #0782BD;">

		<table style="width:950px;">
			<colgroup>
				<col width="10%">
				<col width="*">
				<col width="20%">
			</colgroup>		
			<thead>
				<tr style="color:#000;">
					<th>번호</th>
					<th>제목</th>
					<th>등록일</th>
				</tr>
			</thead>
			<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					<c:forEach var="it" items="${list}">
						<tr>
							<td><a href="/user/fnq/fnq_view.go?seq=${it.seq}">${it.seq}</a></td>
							<td style="text-align:left; padding-left:10px;"><a href="/user/fnq/fnq_view.go?seq=${it.seq}">${it.title}</a></td>
							<td>${it.regDate.substring(0,16)}</td>
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
	
	</div>
	
	
