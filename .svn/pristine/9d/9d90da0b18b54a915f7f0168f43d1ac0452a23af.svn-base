<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
		<table class="list">

			<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					<c:forEach var="it" items="${list}">
					<tr>
<!-- 							<td colspan="7" style="height:200px;">조회된 데이터가 없습니다.</td> -->
						<td rowspan="4"><img src="/files/imagefile/${it.contentsHtml}"></td>
					</tr>
					<tr>
						<td style="padding-left:10px; text-align:left;"><a href="/admin/banner/banner_edit.go?seq=${it.bannerSeq}">${it.linkUrl}</a></td>
					</tr>
					<tr>
						<td style="padding-left:10px; text-align:left;">${it.sido}&nbsp;${it.gugun}</td>
					</tr>
					<tr>
						<td style="padding-left:10px; text-align:left;">${it.startDate.substring(0,10)}~${it.endDate.substring(0,10)} | ${it.countView} | ${it.countClick}</td>
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
		
	
	
