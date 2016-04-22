<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

			<div class="contents-box">
					<form method="post" name="firmForm" onsubmit="return submitForm(this); return false;">
					<input type="hidden" name="userId" value="${user.userId}">
						<table class="register" style="width:1000px;">
							<colgroup>
								<col width="10%">
								<col width="20%">
								<col width="10%">
								<col width="20%">
								<col width="10%">
								<col width="30%">
							</colgroup>
							<tr>
								<th>아이디</th>
								<td>${user.userId}</td>
								<th>키(cm)</th>
								<td>${userBasic.height}</td>
								<th>질환</th>
								<td>
									<c:if test="${userBasic.blood == 'blood'}">당뇨</c:if>
									<c:if test="${userBasic.press == 'press'}">고혈압</c:if>
									<c:if test="${userBasic.col == 'col'}">콜레스테롤</c:if>
									<c:if test="${userBasic.heiwieght == 'heiwieght'}">비만</c:if>
								</td>
							</tr>
							<tr>									
								<th>생년월일</th>
								<td>${user.birthday}</td>
								<th>체중(Kg)</th>
								<td>${userBasic.weight}</td>
								<th>흡연유무</th>
								<td>${userBasic.smokeText}</td>
							</tr>
							<tr>
								<th>성별</th>
								<td>${userBasic.genderText}</td>
								<th>허리둘레(cm)</th>
								<td>${userBasic.waist}</td>
								<th>등록일자</th>
								<td>${userBasic.regDate.substring(0,16)}</td>
							</tr>
						</table>
						
<!-- 						<div style="margin-top:30px;"><h2>●복약정보</h2></div> -->
<!-- 						<table class="register" style="width:1000px;"> -->
<!-- 							<thead> -->
<!-- 								<th style="text-align:center;">약이름</th> -->
<!-- 								<th style="text-align:center;">복약시간</th> -->
<!-- 								<th style="text-align:center;">복약 알림</th> -->
<!-- 							</thead> -->
<!-- 							<tbody class="rl"> -->
<!-- 								<tr> -->
<%-- 									<td>${userMedi.mediname}</td> --%>
<%-- 									<td>${userMedi.meditime}</td> --%>
<%-- 									<td>${userMedi.alertText}</td> --%>
<!-- 								</tr> -->
<!-- 							</tbody> -->
<!-- 						</table> -->
					</form>
				</div>
