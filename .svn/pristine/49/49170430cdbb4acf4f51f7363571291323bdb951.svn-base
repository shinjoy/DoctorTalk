<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
$(document).ready(function() {
	aside.setActive(1,1);
});

</script>

</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈
			</header>
		
			<div class="contents-block">
			
				<table>
				<tr>
					<td style="vertical-align:top;">
						<dl class="title-box">
							<dt>
								<div class="tbl">
									<span>공지사항</span>
									<span class="more"><button type="button" class="btn" onclick="document.location.href='/admin/notice/notice.go';">more</button></span>
								</div>
							</dt>
							<dd style="padding:0;">
								<table style="width:400px;" class="list-sm">
								<colgroup>
									<col width="*">
									<col width="20%">				
								</colgroup>
								<c:choose>
									<c:when test="${noticeList.size() > 0}">
										<c:forEach var="it" items="${noticeList}">
											<tr>
												<td style="text-align:left; padding-left:5px;"><a href="/admin/notice/notice_edit.go?seq=${it.seq}">${it.title}</a></td>
												<td>${it.regDate.substring(0,10)}</td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="2" class="empty-data">등록된 공지사항이 없습니다.</td>
										</tr>
									</c:otherwise>
								</c:choose>
								</table>
							</dd>
						</dl>
					</td>
					<td style="vertical-align:top;">
						<dl class="title-box">
							<dt>
								<div class="tbl">
									<span>자주 묻는 질문</span>
									<span class="more"><button type="button" class="btn" onclick="document.location.href='/admin/fnq/fnq.go';">more</button></span>
								</div>
							</dt>
							<dd style="padding:0;">
								<table style="width:400px;" class="list-sm">
								<colgroup>
									<col width="*">
									<col width="20%">				
								</colgroup>
								<c:choose>
									<c:when test="${faqList.size() > 0}">
										<c:forEach var="it" items="${faqList}">
											<tr>
												<td style="text-align:left; padding-left:5px;"><a href="/admin/fnq/fnq_edit.go?seq=${it.seq}">${it.title}</a></td>
												<td>${it.regDate.substring(0,10)}</td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="2" class="empty-data">등록된 질문이 없습니다.</td>
										</tr>
									</c:otherwise>
								</c:choose>
								</table>
							</dd>
						</dl>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<dl class="title-box">
							<dt>
								<div class="tbl">
									<span>분석 요청 현황</span>
									<span class="more"><button type="button" class="btn" onclick="document.location.href='/admin/assay/assay.go';">more</button></span>
								</div>
							</dt>
							<dd>
								<ul>
									<c:choose>	
										<c:when test="${anaList.size() > 0}">
											<c:forEach var="it" items="${anaList}">
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

							</dd>
						</dl>
					</td>
				</tr>
				</table>
			
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>