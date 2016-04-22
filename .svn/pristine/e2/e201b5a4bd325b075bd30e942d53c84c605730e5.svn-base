<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(1,1);
	});
	
	function submitForm(frm) {
		
		
		
		var param = {
			userId : frm.userId.value,
			bloodNum	: frm.bloodNum.value,
			bloodTime	: frm.bloodTime.value,
			equip	: frm.equip.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/user/user_blood.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.reload();
				}
			}
		});

		return false;
	}
	
	function deleteNotice(userId) {
		if(confirm("사용자를 삭제하시겠습니까?")) {
			var param = {
					userId	:	userId
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/user/user_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/user/user_drop.go";
					}
				}
			});
		}
		return false;

	}
	
	function deleteNotice(userId) {
		if(confirm("사용자를 삭제하시겠습니까?")) {
			var param = {
					userId	:	userId
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/user/user_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/user/user_drop.go";
					}
				}
			});
		}
		return false;

	}

</script>

<style>


</style>
</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 회원관리 > 일반회원 상세보기
			</header>
		
			<div class="contents-block">
			
				<h1>${user.userName}</h1>
				
				<%@ include file="/WEB-INF/views/admin/user/user_info.jsp"  %>

				<div class="btn-tools" style="margin-top:0;">	
<%-- 					<button type="button" class="btn-blue" onclick="document.location.href='/admin/user/user_edit.go?userId=${user.userId}';">수정</button> --%>
						<c:if test="${user.status == 4}">
							<button type="button" class="btn-red" onclick="deleteNotice(${user.userId});">삭제</button>
						</c:if>
					<button type="button" class="btn" onclick="document.location.href='/admin/user/user.go';">목록</button>
				</div>
				
				<div class="tab-bar">
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view.go?userId=${user.userId}';"><span>혈당</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_pressure.go?userId=${user.userId}';"><span>혈압</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_weight.go?userId=${user.userId}';"><span>체중</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_cholesterol.go?userId=${user.userId}';"><span>콜레스테롤</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_hemoglobin.go?userId=${user.userId}';"><span>당화혈색소</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_goal.go?userId=${user.userId}';"><span>관리목표</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_week.go?userId=${user.userId}';"><span>관리주기</span></button>
					<button type="button" class="tab " onclick="document.location.href='/admin/user/user_view_medi.go?userId=${user.userId}';"><span>복약정보</span></button>
					<button type="button" class="tab active" onclick="document.location.href='/admin/user/user_cv_risk.go?userId=${user.userId}';"><span>CV_RISK</span></button>
					<button type="button" class="tab " onclick="document.location.href='/admin/user/user_view_done.go?userId=${user.userId}';"><span>수행내역</span></button>
					<!-- <button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_hospital.go?userId=${user.userId}';"><span>병원 목록</span></button> -->
					<button type="button" class="tab " onclick="document.location.href='/admin/user/user_view_etc.go?userId=${user.userId}';"><span>기타</span></button>
				</div>				
				
				<div class="tbl-list">
<!-- 				<table class="register" style="margin-top:10px;"> -->
<!-- 						<thead> -->
<!-- 							<th>공복 월 평균</th> -->
<!-- 							<th>식후 월 평균</th> -->
<!-- 							<th>취침전 월 평균</th> -->
<!-- 						</thead> -->
<!-- 						<tbody> -->
<!-- 							<tr> -->
<%-- 								<td style="text-align:left; padding-left:10px;">${gongavg}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${eatavg}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${sleepavg}</td> --%>
<!-- 							</tr> -->
<!-- 						</tbody> -->
<!-- 					</table> -->
				
				
					<table class="usertext" style="margin-top:30px; width:100%;">
						<thead>
							<tr>
								<th>CV_RISK</th>
								<th>총콜레스테롤</th>
								<th>HDL 콜레스테롤</th>
								<th>수축기 혈압</th>
								<th>이완기 혈압</th>
								<th>흡연여부</th>
								<th>10년후 나이</th>
								<th>등록일자</th>
							</tr>
						</thead>
						<tbody>
						<c:choose>
							<c:when test="${UserCvrisklist.size() > 0}">
								<c:forEach var="it" items="${UserCvrisklist}">
									<tr class="center">
										<td>${it.cvNum}%</td>
										<td>${it.col}</td>
										<td>${it.hdl}</td>
										<td>${it.splessure}</td>
										<td>${it.dplessure}</td>
										<td>${it.smokeText}</td>
										<td>${it.userTage}</td>
										<td>${fn:substring(it.regDate,0,16)}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr><td colspan="8" style="text-align: center;" >조회된 데이터가 없습니다.</td></tr>
							</c:otherwise>
						</c:choose>
						</tbody>
					</table>
				${paging}
				</div>
				
<!-- 				<div> -->
<!-- 					<h2>사용자 정보 추가하기</h2> -->
					
<!-- 					<form method="post" name="firmForm" onsubmit="return submitForm(this); return false;"> -->
<%-- 						<input type="hidden" name="userId" value="${user.userId}"> --%>
<!-- 						<table class="usertext" style="margin-top:30px; width:100%;"> -->
							
<!-- 							<thead> -->
<!-- 								<tr> -->
<!-- 									<th >혈당</th> -->
<!-- 									<th >복약시간</th> -->
<!-- 									<th> 장비사용여부</th> -->
<!-- 								</tr> -->
<!-- 							</thead> -->
<!-- 							<tbody> -->
<!-- 								<tr> -->
<!-- 									<td> <input type="text" class="itext"  name="bloodNum"></td> -->
<!-- 									<td> <input type="text" class="itext" name="bloodTime"></td> -->
<!-- 									<td> <input type="text" class="itext" name="equip"></td> -->
<!-- 								</tr> -->
<!-- 							</tbody> -->
<!-- 						</table> -->
<!-- 						<div  class="btn-tools" >	 -->
<!-- 							<button type="submit" class="btn">추가하기</button> -->
<!-- 						</div> -->
<!-- 					</form> -->
<!-- 				</div> -->
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>