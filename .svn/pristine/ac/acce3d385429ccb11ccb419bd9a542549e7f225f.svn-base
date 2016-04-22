<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
		
		<table class="list">

			<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					<c:forEach var="it" items="${list}">
					<tr>
						<td rowspan="2">
						<c:choose>
								<c:when test="${!empty fn:trim(it.photo1)}">	
								<img src="/files/company/thumb/${it.photo1}" style="height:100px;">
								</c:when>
								<c:otherwise>
									 등록된 이미지가 없습니다.
								</c:otherwise>
						</c:choose>
						</td>
						<td style="text-align: left; padding-left: 10px;">
						<a href="/admin/business/business_view.go?companySeq=${it.companySeq}">${it.companyName}&nbsp;${it.phoneNumber}&nbsp;
							ID : ${it.userId}  
							
						</a></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: left; padding-left: 10px;">
									대표자 : ${it.userName}   ${it.sido} &nbsp; ${it.gugun}
						</td>
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
		
	
	
