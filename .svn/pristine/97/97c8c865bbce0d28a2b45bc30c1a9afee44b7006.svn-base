<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<!-- PC 버전 -->
		<div class="pc-view lnb">
			<div class="login-sec">
				<p>환영합니다.</p>
				<p style="margin-bottom:5px;">${USER_NAME}님</p>
				<p style="margin-bottom:5px;"><button class="btn-admin" style="padding: 0px 5px;" onclick="document.location.href='/admin/user_edit_password.go';">비밀번호변경</button></p>
<%-- 				<a href="javascript:popPasswordChange('${USER_ID}');" class="button round orange tiny" style="margin:5px 0;">비밀번호변경</a> --%>
				<p><button class="btn" onclick="document.location.href='/logout_do.go';">로그아웃</button></p>
			</div>
			<nav>
				<ul class="nav">
					<li>
						<p><strong>사용자 관리</strong></p>
						<ul class="sm">
							<li><a id="menu11" href="/admin/user/ws.go">홀세일러</a></li>						
							<li><a id="menu122" href="/admin/user/rt.go">리테일러</a></li>
							<li><a id="menu133" href="/admin/user/gf.go">굿프랜드</a></li>
							<li><a id="menu14" href="/admin/user/tb.go">타이어뱅크 지점</a></li>
						</ul>
					</li>
					<li><p><strong>상품/재고관리</strong></p>
						<ul class="sm">
							<li><a id="menu31" href="/admin/product/product.go">상품내역</a></li>
							<li><a id="menu32" href="/admin/stock/stock.go">타이어뱅크 재고</a></li>
							<li><a id="menu33" href="/admin/excel/excel.go">엑셀자료 업로드</a></li>
							<li><a id="menu34" href="/admin/product/manufacturer.go">제조사 관리</a></li>
						</ul>
					</li>
					<li><p><strong>주문/이송요청관리</strong></p>
						<ul class="sm">
							<li><a id="menu41" href="/admin/order/order_ws.go">홀세일러 주문내역</a></li>
							<li><a id="menu42" href="/admin/order/order_rt.go">리테일러 주문내역</a></li>
							<li><a id="menu43" href="/admin/transfer/transfer_list.go">이송요청내역</a></li>
						</ul>
					</li>
					<li><p><strong>푸시알림</strong></p>
						<ul class="sm">
							<li><a id="menu51" href="/admin/notice/push.go">푸시 알림공지</a></li>
						</ul>
					</li>
					<li><p><strong>사용통계</strong></p>
						<ul class="sm">
							<li><a id="menu36" href="/admin/statistics/statistics_ws.go">홀세일러 통계</a></li>
							<li><a id="menu37" href="/admin/statistics/statistics_rt.go">리테일러 통계</a></li>
							<li><a id="menu38" href="/admin/statistics/statistics_transfer.go">이송요청 통계</a></li>
							<li><a id="menu39" href="/admin/statistics/statistics_order.go">주문상품 통계</a></li>
						</ul>
					</li>			
				</ul>
			</nav>
		</div><!-- End of class="lnb" //-->
				
		<!-- 모바일 버전 -->
		<div id="drop-nav1" class="mobile-view drop-nav">
			<div class="wrap">
				<ul>
					<li><a id="menu11" href="/admin/user/user.go">사용자 목록</a></li>
					<!-- 
					<li><a id="menu12" href="/admin/user/pwinit.go">비밀번호 초기화 이력</a></li>
					<li><a id="menu13" href="/admin/user/terms.go">약관/정책</a></li>
					-->
				</ul>
			</div>
		</div>
		<!-- 
		<div id="drop-nav2" class="mobile-view drop-nav">
			<div class="wrap">
				<ul>
					<li><a id="menu21" href="/admin/bbs/category.go">카테고리 관리</a></li>
					<li><a id="menu22" href="/admin/bbs/header.go">머릿글 관리</a></li>
					<li><a id="menu23" href="/admin/bbs/bbs.go">게시물 관리</a></li>
				</ul>
			</div>
		</div>
		-->
		<div id="drop-nav3" class="mobile-view drop-nav">
			<div class="wrap">
				<ul>
					<li><a id="menu31" href="/admin/notice/notice.go">공지사항</a></li>
					<!-- 
					<li><a id="menu32" href="/admin/notice/popup.go">팝업 공지사항</a></li>
					 -->
					<li><a id="menu33" href="/admin/notice/upgrade.go">업그레이드 공지사항</a></li>
				</ul>
			</div>
		</div>

		