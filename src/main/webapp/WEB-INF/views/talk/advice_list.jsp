<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
	<div>	
		<table class="list" style="float:left;">
			<thead>
				<th>회원명</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>제휴사</th>
				<th>서비스등급</th>
			</thead>
		
		
			<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					<c:forEach var="it" items="${list}">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
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
		
	
	
