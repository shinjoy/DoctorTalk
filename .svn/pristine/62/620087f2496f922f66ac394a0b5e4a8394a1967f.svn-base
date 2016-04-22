<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
<!--
	function logTabLink(link) {
		var frm = listForm;
		var sponsorId = frm.sponsorId.value;
		var adSeq = frm.adSeq.value;
		var url = link+"?sponsorId="+sponsorId+"&adSeq="+adSeq;
		document.location.href = link+"?sponsorId="+sponsorId+"&adSeq="+adSeq;
	}
//-->
</script>

		<!-- 상단메뉴 -->
		<div class="header">
			<div class="h_container">
			
				<div class="menu">
					<ul class="topMenu">
						<c:choose>
							<c:when test="${ USER_TYPE==1 }">
								<c:choose>
									<c:when test="${ pageMain == 1 }">
										<li><a href="/users.do" class="active"><img src="/images/user.png" alt="">회원관리</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="/users.do"><img src="/images/user.png" alt="">회원관리</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ pageMain == 2 }">
										<li><a href="/ad.do" class="active"><img src="/images/form.png" alt="">광고관리</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="/ad.do" ><img src="/images/form.png" alt="">광고관리</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ pageMain == 3 }">
										<li><a href="/log.do" class="active"><img src="/images/settings.png" alt="">종합통계</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="/log.do" ><img src="/images/settings.png" alt="">종합통계</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ pageMain == 4 }">
										<li><a href="/announce.do" class="active"><img src="/images/notification.png" alt="">게시판</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="/announce.do" ><img src="/images/notification.png" alt="">게시판</a></li>
									</c:otherwise>
								</c:choose>
							</c:when>

							<c:otherwise>
								<c:choose>
									<c:when test="${ pageMain == 2 }">
										<li><a href="/ad.do" class="active"><img src="/images/form.png" alt="">광고관리</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="/ad.do" ><img src="/images/form.png" alt="">광고관리</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ pageMain == 3 }">
										<li><a href="/log.do" class="active"><img src="/images/settings.png" alt="">통계</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="/log.do" ><img src="/images/settings.png" alt="">통계</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ pageMain == 1 }">
										<li><a href="/sponsor_view.do?id=${ USER_ID }" class="active"><img src="/images/user.png" alt="">광고주 정보</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="/sponsor_view.do?id=${ USER_ID }"><img src="/images/user.png" alt="">광고주 정보</a></li>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>

				<img src="/images/admin_mainTitle.png" alt="TTO TTO Manager">

			</div>
		</div>
		<div class="clear"></div>

		<!-- 상단탭 -->
		<div class="tabs">
			<div class="t_container">
			
				<!-- <h1><img src="/images/admin_subTitle.png" alt="management system"></h1> -->
				<div class="tab_links">
					<c:choose>
						<c:when test="${ pageMain == 1 }">
							<ul class="tabNavigation">
								<c:choose>
									<c:when test="${ USER_TYPE==1 }">
										<c:choose>
											<c:when test="${ pageSub == 1 }">
												<li><a href="/users.do" class="active2">사용자관리</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="/users.do" >사용자관리</a></li>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${ pageSub == 2 }">
												<li><a href="/sponsors.do" class="active2">광고주관리</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="/sponsors.do" >광고주관리</a></li>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${ pageSub == 2 }">
												<li><a href="/sponsors.do" class="active2">광고주관리</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="/sponsors.do" >광고주관리</a></li>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</ul>
						</c:when>
						<c:when test="${ pageMain == 2 }">
							<ul class="tabNavigation">
								<c:choose>
									<c:when test="${ pageSub == 1 }">
										<li><a href="/ad.do" class="active2">광고관리</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="/ad.do">광고관리</a></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</c:when>
						<c:when test="${ pageMain == 3 }">
							<ul class="tabNavigation">
								<c:choose>
									<c:when test="${ pageSub == 1 }">
										<li><a href="javascript:logTabLink('/log.do');" class="active2">광고로그</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="javascript:logTabLink('/log.do');" >광고로그</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ pageSub == 2 }">
										<li><a href="javascript:logTabLink('/win.do');" class="active2">당첨내역</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="javascript:logTabLink('/win.do');" >당첨내역</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ pageSub == 3 }">
										<li><a href="javascript:logTabLink('/stGender.do');" class="active2">성별통계</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="javascript:logTabLink('/stGender.do');" >성별통계</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ pageSub == 4 }">
										<li><a href="javascript:logTabLink('/stAge.do');" class="active2">연령통계</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="javascript:logTabLink('/stAge.do');">연령통계</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ pageSub == 5 }">
										<li><a href="javascript:logTabLink('/stArea.do');" class="active2">지역통계</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="javascript:logTabLink('/stArea.do');" >지역통계</a></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</c:when>
						<c:when test="${ pageMain == 4 }">
							<ul class="tabNavigation">
								<c:choose>
									<c:when test="${ pageSub == 1 }">
										<li><a href="/announce.do" class="active2">공지사항</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="/announce.do">공지사항</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${ pageSub == 2 }">
										<li><a href="/fortune.do" class="active2">운세</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="/fortune.do" >운세</a></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</c:when>
					</c:choose>
				</div>
				<div class="admin">
					<span class="name"><%=session.getAttribute("USER_NAME") %></span>
					<a href="/logout_do.do"><img src="/images/btn_logout.png" alt="로그아웃버튼" class="mar_r pointer"></a>
					<a href="#" onclick="onPasswordChangePopClick();"><img src="/images/btn_repass.png" alt="패스워드 변경"  class="pointer"></a>
				</div>

			</div>
		</div>
		<div class="clear"></div>
