<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<aside id="nav">

			<ul class="m1">
				<li class="menu" id="menu1">
					<a href="#" onclick="aside.togleSub(1);">
						<dl>
							<dt class="bullet">
								<div class="bullet-image"><img src="/images/doctor_user.png" style="height:20px;"></div>
							</dt>
							<dd class="menu-name">
								<span>회원관리</span>
							</dd>
							<dd class="arrow">
								<img src="/images/arrow_bottom.png">
							</dd>
						</dl>
					</a>
					<ul class="m2" id="u1">
						<li id="menu-sub11">
							<a href="/admin/user/user.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>일반회원</span>
									</dd>
								</dl>
							</a>
						</li>
						<li id="menu-sub12">
							<a href="/admin/user/admin.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>상담원 관리</span>
									</dd>
								</dl>
							</a>
						</li>
						<li id="menu-sub13">
							<a href="/admin/user/user_drop.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>탈퇴회원 관리</span>
									</dd>
								</dl>
							</a>
						</li>
						
						
					</ul>
				</li>
				<li class="menu" id="menu2">
					<a href="#" onclick="aside.togleSub(2);">
						<dl>
							<dt class="bullet">
								<div class="bullet-image"><img src="/images/doctor_service.png"></div>
							</dt>
							<dd class="menu-name">
								<span>서비스 관리</span>
							</dd>
							<dd class="arrow">
								<img src="/images/arrow_bottom.png">
							</dd>
						</dl>
					</a>
					<ul class="m2" id="u2">
						<li id="menu-sub21">
							<a href="/admin/notice/notice.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>공지사항</span>
									</dd>
								</dl>
							</a>
						</li>
						<li id="menu-sub24">
							<a href="/admin/notice/faq.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>QNA 관리</span>
									</dd>
								</dl>
							</a>
						</li> 
						<li id="menu-sub22">
							<a href="/admin/notice/app.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>앱 버전 관리</span>
									</dd>
								</dl>
							</a>
						</li>
					</ul>
				</li>
				<li class="menu" id="menu3">
					<a href="#" onclick="aside.togleSub(3);">
						<dl>
							<dt class="bullet">
								<div class="bullet-image"><img src="/images/doctor_medical.png"></div>
							</dt>
							<dd class="menu-name">
								<span>리커버 - 문진</span>
							</dd>
							<dd class="arrow">
								<img src="/images/arrow_bottom.png">
							</dd>
						</dl>
					</a>
					<ul class="m2" id="u3">
						<li id="menu-sub31">
							<a href="/admin/medical/medical.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>문진</span>
									</dd>
								</dl>
							</a>
						</li>
					</ul>
				</li>
				<li class="menu" id="menu4">
					<a href="#" onclick="aside.togleSub(4);">
						<dl>
							<dt class="bullet">
								<div class="bullet-image"><img src="/images/doctor_index.png"></div>
							</dt>
							<dd class="menu-name">
								<span>리커버 - 지표입력</span>
							</dd>
							<dd class="arrow">
								<img src="/images/arrow_bottom.png">
							</dd>
						</dl>
					</a>
					<ul class="m2" id="u4">
						<li id="menu-sub41">
							<a href="/admin/doctor_index_input/blood.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>혈당</span>
									</dd>
								</dl>
							</a>
						</li>
						<li id="menu-sub42">
							<a href="/admin/doctor_index_input/pressure.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>혈압</span>
									</dd>
								</dl>
							</a>
						</li>
						<li id="menu-sub43">
							<a href="/admin/doctor_index_input/cholesterol.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>콜레스테롤</span>
									</dd>
								</dl>
							</a>
						</li>
						<li id="menu-sub44">
							<a href="/admin/doctor_index_input/weight.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>체중,당화혈색소</span>
									</dd>
								</dl>
							</a>
						</li>						
<!-- 						<li id="menu-sub45"> -->
<!-- 							<a href="/admin/doctor_index_input/splenopathy.go"> -->
<!-- 								<dl> -->
<!-- 									<dt class="bullet"> -->
<!-- 										<div class="bullet-image"><img src="/images/arrow_right.png"></div> -->
<!-- 									</dt> -->
<!-- 									<dd class="menu-name"> -->
<!-- 										<span>비질환 대화관리</span> -->
<!-- 									</dd> -->
<!-- 								</dl> -->
<!-- 							</a> -->
<!-- 						</li> -->
					</ul>
				</li>
<!-- 				<li class="menu" id="menu5"> -->
<!-- 					<a href="#" onclick="aside.togleSub(5);"> -->
<!-- 						<dl> -->
<!-- 							<dt class="bullet"> -->
<!-- 								<div class="bullet-image"><img src="/images/banner.png"></div> -->
<!-- 							</dt> -->
<!-- 							<dd class="menu-name"> -->
<!-- 								<span>닥터톡 - 지표관리</span> -->
<!-- 							</dd> -->
<!-- 							<dd class="arrow"> -->
<!-- 								<img src="/images/arrow_bottom.png"> -->
<!-- 							</dd> -->
<!-- 						</dl> -->
<!-- 					</a> -->
<!-- 					<ul class="m2" id="u5"> -->
<!-- 						<li id="menu-sub51"> -->
<!-- 							<a href="/admin/doctor_index_manage/blood.go"> -->
<!-- 								<dl> -->
<!-- 									<dt class="bullet"> -->
<!-- 										<div class="bullet-image"><img src="/images/arrow_right.png"></div> -->
<!-- 									</dt> -->
<!-- 									<dd class="menu-name"> -->
<!-- 										<span>혈당</span> -->
<!-- 									</dd> -->
<!-- 								</dl> -->
<!-- 							</a> -->
<!-- 						</li> -->
<!-- 						<li id="menu-sub52"> -->
<!-- 							<a href="/admin/doctor_index_manage/pressure.go"> -->
<!-- 								<dl> -->
<!-- 									<dt class="bullet"> -->
<!-- 										<div class="bullet-image"><img src="/images/arrow_right.png"></div> -->
<!-- 									</dt> -->
<!-- 									<dd class="menu-name"> -->
<!-- 										<span>혈압</span> -->
<!-- 									</dd> -->
<!-- 								</dl> -->
<!-- 							</a> -->
<!-- 						</li> -->
<!-- 						<li id="menu-sub53"> -->
<!-- 							<a href="/admin/doctor_index_manage/weight.go"> -->
<!-- 								<dl> -->
<!-- 									<dt class="bullet"> -->
<!-- 										<div class="bullet-image"><img src="/images/arrow_right.png"></div> -->
<!-- 									</dt> -->
<!-- 									<dd class="menu-name"> -->
<!-- 										<span>체중,당화혈색소</span> -->
<!-- 									</dd> -->
<!-- 								</dl> -->
<!-- 							</a> -->
<!-- 						</li> -->
<!-- 						<li id="menu-sub54"> -->
<!-- 							<a href="/admin/doctor_index_manage/cholesterol.go"> -->
<!-- 								<dl> -->
<!-- 									<dt class="bullet"> -->
<!-- 										<div class="bullet-image"><img src="/images/arrow_right.png"></div> -->
<!-- 									</dt> -->
<!-- 									<dd class="menu-name"> -->
<!-- 										<span>콜레스테롤</span> -->
<!-- 									</dd> -->
<!-- 								</dl> -->
<!-- 							</a> -->
<!-- 						</li> -->

<!-- 					</ul> -->
<!-- 				</li> -->
				<li class="menu" id="menu6">
					<a href="#" onclick="aside.togleSub(6);">
						<dl>
							<dt class="bullet">
								<div class="bullet-image"><img src="/images/doctor_analysis.png"></div>
							</dt>
							<dd class="menu-name">
								<span>리커버 - 분석</span>
							</dd>
							<dd class="arrow">
								<img src="/images/arrow_bottom.png">
							</dd>
						</dl>
					</a>
					<ul class="m2" id="u6">
						<li id="menu-sub61">
							<a href="/admin/analysis/empty_morning.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>관리 분석</span>
									</dd>
								</dl>
							</a>
						</li>
<!-- 						<li id="menu-sub62"> -->
<!-- 							<a href="/admin/analysis/after_dinner.go"> -->
<!-- 								<dl> -->
<!-- 									<dt class="bullet"> -->
<!-- 										<div class="bullet-image"><img src="/images/arrow_right.png"></div> -->
<!-- 									</dt> -->
<!-- 									<dd class="menu-name"> -->
<!-- 										<span>식후혈당</span> -->
<!-- 									</dd> -->
<!-- 								</dl> -->
<!-- 							</a> -->
<!-- 						</li> -->
<!-- 						<li id="menu-sub63"> -->
<!-- 							<a href="/admin/analysis/sleep_blood.go"> -->
<!-- 								<dl> -->
<!-- 									<dt class="bullet"> -->
<!-- 										<div class="bullet-image"><img src="/images/arrow_right.png"></div> -->
<!-- 									</dt> -->
<!-- 									<dd class="menu-name"> -->
<!-- 										<span>취침전 혈당</span> -->
<!-- 									</dd> -->
<!-- 								</dl> -->
<!-- 							</a> -->
<!-- 						</li> -->
<!-- 						<li id="menu-sub64"> -->
<!-- 							<a href="/admin/analysis/hemoglobin.go"> -->
<!-- 								<dl> -->
<!-- 									<dt class="bullet"> -->
<!-- 										<div class="bullet-image"><img src="/images/arrow_right.png"></div> -->
<!-- 									</dt> -->
<!-- 									<dd class="menu-name"> -->
<!-- 										<span>당화혈색소</span> -->
<!-- 									</dd> -->
<!-- 								</dl> -->
<!-- 							</a> -->
<!-- 						</li> -->
<!-- 						<li id="menu-sub65"> -->
<!-- 							<a href="/admin/analysis/pressure.go"> -->
<!-- 								<dl> -->
<!-- 									<dt class="bullet"> -->
<!-- 										<div class="bullet-image"><img src="/images/arrow_right.png"></div> -->
<!-- 									</dt> -->
<!-- 									<dd class="menu-name"> -->
<!-- 										<span>혈압</span> -->
<!-- 									</dd> -->
<!-- 								</dl> -->
<!-- 							</a> -->
<!-- 						</li> -->
<!-- 						<li id="menu-sub66"> -->
<!-- 							<a href="/admin/analysis/cholesterol.go"> -->
<!-- 								<dl> -->
<!-- 									<dt class="bullet"> -->
<!-- 										<div class="bullet-image"><img src="/images/arrow_right.png"></div> -->
<!-- 									</dt> -->
<!-- 									<dd class="menu-name"> -->
<!-- 										<span>콜레스테롤</span> -->
<!-- 									</dd> -->
<!-- 								</dl> -->
<!-- 							</a> -->
<!-- 						</li> -->
<!-- 						<li id="menu-sub67"> -->
<!-- 							<a href="/admin/analysis/weight.go"> -->
<!-- 								<dl> -->
<!-- 									<dt class="bullet"> -->
<!-- 										<div class="bullet-image"><img src="/images/arrow_right.png"></div> -->
<!-- 									</dt> -->
<!-- 									<dd class="menu-name"> -->
<!-- 										<span>체중</span> -->
<!-- 									</dd> -->
<!-- 								</dl> -->
<!-- 							</a> -->
<!-- 						</li> -->
					</ul>
				</li>
				
				<li class="menu" id="menu7">
					<a href="#" onclick="aside.togleSub(7);">
						<dl>
							<dt class="bullet">
								<div class="bullet-image"><img src="/images/cv_risk.png"></div>
							</dt>
							<dd class="menu-name">
								<span>리커버 - CV Risk</span>
							</dd>
							<dd class="arrow">
								<img src="/images/arrow_bottom.png">
							</dd>
						</dl>
					</a>
					<ul class="m2" id="u7">
						<li id="menu-sub71">
							<a href="/admin/doctor_cv_risk/cv_risk.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>CV Risk</span>
									</dd>
								</dl>
							</a>
						</li>
					</ul>
				</li>
				<li class="menu" id="menu8">
					<a href="#" onclick="aside.togleSub(8);">
						<dl>
							<dt class="bullet">
								<div class="bullet-image"><img src="/images/doctor_period.png"></div>
							</dt>
							<dd class="menu-name">
								<span>관리목표/주기</span>
							</dd>
							<dd class="arrow">
								<img src="/images/arrow_bottom.png">
							</dd>
						</dl>
					</a>
					<ul class="m2" id="u8">
<!-- 						<li id="menu-sub81"> -->
<!-- 							<a href="/admin/period_manage/talk.go"> -->
<!-- 								<dl> -->
<!-- 									<dt class="bullet"> -->
<!-- 										<div class="bullet-image"><img src="/images/arrow_right.png"></div> -->
<!-- 									</dt> -->
<!-- 									<dd class="menu-name"> -->
<!-- 										<span>관리목표 대화</span> -->
<!-- 									</dd> -->
<!-- 								</dl> -->
<!-- 							</a> -->
<!-- 						</li> -->
						<li id="menu-sub82">
							<a href="/admin/period_manage/period.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>관리주기 안내</span>
									</dd>
								</dl>
							</a>
						</li>
<!-- 						<li id="menu-sub83"> -->
<!-- 							<a href="/admin/period_manage/period_blood.go"> -->
<!-- 								<dl> -->
<!-- 									<dt class="bullet"> -->
<!-- 										<div class="bullet-image"><img src="/images/arrow_right.png"></div> -->
<!-- 									</dt> -->
<!-- 									<dd class="menu-name"> -->
<!-- 										<span>혈당 관리주기 변경</span> -->
<!-- 									</dd> -->
<!-- 								</dl> -->
<!-- 							</a> -->
<!-- 						</li> -->
						<li id="menu-sub84">
							<a href="/admin/period_manage/category.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>기간내 미입력</span>
									</dd>
								</dl>
							</a>
						</li>
						<li id="menu-sub85">
							<a href="/admin/notice/manage_index.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>관리목표설정</span>
									</dd>
								</dl>
							</a>
						</li> 

					</ul>
				</li>
				
								
			<li class="menu" id="menu9">
					<a href="#" onclick="aside.togleSub(9);">
						<dl>
							<dt class="bullet">
								<div class="bullet-image"><img src="/images/content.png"></div>
							</dt>
							<dd class="menu-name">
								<span>컨텐츠 관리</span>
							</dd>
							<dd class="arrow">
								<img src="/images/arrow_bottom.png">
							</dd>
						</dl>
					</a>
					<ul class="m2" id="u9">
						<li id="menu-sub91">
							<a href="/admin/day/category.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>1일주기 컨텐츠</span>
									</dd>
								</dl>
							</a>
						</li>
						<li id="menu-sub92">
							<a href="/admin/week/category.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>1주주기 컨텐츠</span>
									</dd>
								</dl>
							</a>
						</li>
						<!-- 
						<li id="menu-sub93">
							<a href="/admin/month/contents.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>1개월주기 컨텐츠</span>
									</dd>
								</dl>
							</a>
						</li>
						 -->
						<li id="menu-sub94">
							<a href="/admin/contents/contents.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>건강 매거진</span>
									</dd>
								</dl>
							</a>
						</li>

					</ul>
				</li>
						
				<li class="menu" id="menu10">
					<a href="#" onclick="aside.togleSub(10);">
						<dl>
							<dt class="bullet">
								<div class="bullet-image"><img src="/images/content.png"></div>
							</dt>
							<dd class="menu-name">
								<span>채팅 관리</span>
							</dd>
							<dd class="arrow">
								<img src="/images/arrow_bottom.png">
							</dd>
						</dl>
					</a>
					<ul class="m2" id="u10">
						<li id="menu-sub101">
							<a href="/admin/chat/chat.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>채팅 관리</span>
									</dd>
								</dl>
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</aside>
