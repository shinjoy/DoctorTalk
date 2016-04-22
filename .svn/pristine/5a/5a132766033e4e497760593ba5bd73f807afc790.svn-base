<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
	<div class="push-list">
		<c:choose>	
		<c:when test="${list.size() > 0}">
			<c:forEach var="it" items="${list}">
				<div class="items" style="margin-left:30px;">
						<ul style="display:table; width:200px; float:left; padding-bottom:20px;">
							<li style="display:table-cell;" >
								<a href="/admin/push/push_edit.go?seq=${it.pushSeq}"> <img src="/files/imagefile/${it.contentsHtml}"> </a>
							</li>
							<li style="display:table-cell; float:left; vertical-align:top; ">
								<ul style="width:200px;">
									<li>${it.title}</li>
									<li>${it.linkUrl}</li>
									<li>${it.startDate.substring(0,10)}~${it.endDate.substring(0,10)}</li>
									<li>${it.sido}&nbsp;${it.gugun}</li>
									<li>${it.countPush}</li>
									<li>${it.countView}</li>
									<li>${it.countClick}</li>
								</ul>
							</li>
						</ul>

					</div>
			</c:forEach>
		</c:when>
			<c:otherwise>
					<ul style="text-align:center;"><li>조회된 데이터가 없습니다.</li></ul>
			</c:otherwise>
		</c:choose>
		<br>
		<div style="clear:both;"></div>
	</div>
	
		${paging}
		
	
	
