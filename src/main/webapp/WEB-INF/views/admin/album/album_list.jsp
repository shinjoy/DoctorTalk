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
										<tr >
											<c:choose>
												<c:when test="${it.fileName!=''}">
													<td style="height:50px;">
														<div style="margin-right:1px; vertical-align:top;"><img src="/files/1305920150807132159.jpg" style="width:100%;"></div>
													</td>
												</c:when>
												<c:otherwise >
													<td style="height:50px;">이미지가 등록되지 않았습니다.</td>
												</c:otherwise>
											
											</c:choose>
										</tr>
										<tr>
											<td colspan="2">
												<div class="inner-round-box">
<%-- 													<a href="/admin/assay/assay_view.go?userId=${it.userId}&bbsSeq=${it.bbsSeq}"> --%>
<!--  														<p class="align-right"><span class="ana-ready">분석대기</span></p>  -->
														<br>
														${it.userName}
														<br>
														${it.genderText}|${it.userAge}|${it.area}
														<br>
														${it.regDate.substring(0,16)}
<!-- 													<a> -->
												</div>
											</td>
										</tr>
									</table>								
								</div>
							</li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<li style="height:100px; text-align:center; padding-top:80px; "> 조회된 데이터가 없습니다.</li>
					</c:otherwise>												
				</c:choose>
				
			</ul>
			<div style="clear:both;"></div>
		</div>

		${paging}
		
		
						