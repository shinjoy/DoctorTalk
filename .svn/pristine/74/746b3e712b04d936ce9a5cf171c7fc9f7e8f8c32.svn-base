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
						<a href="/admin/request/request_view.go?companySeq=${it.companySeq}">${it.companyName}&nbsp;${it.phoneNumber}&nbsp;
							신청일자 : ${it.regDate.substring(0,10)} | 
							처리상태 : <c:if test="${it.isApproval == 0}">미승인</c:if>
									 <c:if test="${it.isApproval == 1}">승인</c:if>
									 <c:if test="${it.isApproval == 2}">불허</c:if>
						</a></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: left; padding-left: 10px;">
									신청자 : ${it.userName} 
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
		
	
	
