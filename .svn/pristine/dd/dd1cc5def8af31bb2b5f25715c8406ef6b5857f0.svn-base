<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
		<div class="tbl-list" style="width:100%;">
			<ul>
				<c:choose>	
					<c:when test="${list.size() > 0}">
						<c:forEach var="it" items="${list}">
							<li class="imglist">
								<div class="round-box">
									<table>	
										<tr style="height:50px;">
											<c:if test="${it.photo1!=''}">
												<td>
													<div style="margin-right:1px; vertical-align:top;"><img src="/files/imagefile/${it.photo1}" style="width:100%;"></div>
												</td>
											</c:if>
											<c:if test="${it.photo2!=''}">
												<td>
													<div style="margin-left:1px; vertical-align:top;"><img src="/files/imagefile/${it.photo2}" style="width:100%;"></div>
												</td>
											</c:if>
										</tr>
										<tr>
											<td colspan="2">
												<div class="inner-round-box">
												<c:choose>
													<c:when test="${it.frag==0}">
														<a href="/admin/assay/assay_view.go?userId=${it.userId}&seq=${it.seq}">
															<p class="align-right"><span class="ana-ready">분석대기</span></p>
															<br>
															${it.userId}
															<br>
															${it.userAge}세(${it.userName})
															<br>
															${it.regDate.substring(0,16)}
														<a>
													</c:when>
													<c:otherwise>
														<p class="align-right"><span class="ana-finish">분석완료</span></p>
														<br>
														<a href="/admin/assay/assay_view.go?userId=${it.userId}&seq=${it.seq}">${it.userId}<a>
														<br>
														${it.userAge}세(${it.userName})
														<br>
														${it.regDate.substring(0,16)}
													</c:otherwise>		
												</c:choose>
												</div>
											</td>
										</tr>
									</table>									
								</div>
							</li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<li style="height:100px; text-align:center; border-top:1px solid #ddd; border-bottom:1px solid #ddd; padding-top:80px; "> 조회된 데이터가 없습니다.</li>
					</c:otherwise>												
				</c:choose>
				
			</ul>
			<div style="clear:both;"></div>
		</div>

		${paging}
		
		
						