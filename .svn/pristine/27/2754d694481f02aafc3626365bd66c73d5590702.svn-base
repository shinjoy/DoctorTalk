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
			height	: frm.height.value,
			weightNum	: frm.weightNum.value,
			bbmi	: frm.bbmi.value,
			tbw	: frm.tbw.value,
			muscle	: frm.muscle.value,
			bmd	: frm.bmd.value,
			equip	: frm.equip.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/user/user_weight.go",
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

				<div  class="btn-tools" style="margin-top:0;">	
<%-- 					<button type="button" class="btn-blue" onclick="document.location.href='/admin/user/user_edit.go?userId=${user.userId}';">수정</button> --%>
						<c:if test="${user.status == 4}">
							<button type="button" class="btn-red" onclick="deleteNotice(${user.userId});">삭제</button>
						</c:if>
					<button type="button" class="btn" onclick="document.location.href='/admin/user/user.go';">목록</button>
				</div>
				
				<div class="tab-bar">
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view.go?userId=${user.userId}';"><span>혈당</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_pressure.go?userId=${user.userId}';"><span>혈압</span></button>
					<button type="button" class="tab active" onclick="document.location.href='/admin/user/user_view_weight.go?userId=${user.userId}';"><span>체중</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_cholesterol.go?userId=${user.userId}';"><span>콜레스테롤</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_hemoglobin.go?userId=${user.userId}';"><span>당화혈색소</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_goal.go?userId=${user.userId}';"><span>관리목표</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_week.go?userId=${user.userId}';"><span>관리주기</span></button>
					<button type="button" class="tab " onclick="document.location.href='/admin/user/user_view_medi.go?userId=${user.userId}';"><span>복약정보</span></button>
					<button type="button" class="tab " onclick="document.location.href='/admin/user/user_cv_risk.go?userId=${user.userId}';"><span>CV_RISK</span></button>
					<button type="button" class="tab " onclick="document.location.href='/admin/user/user_view_done.go?userId=${user.userId}';"><span>수행내역</span></button>
					<!-- <button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_hospital.go?userId=${user.userId}';"><span>병원 목록</span></button> -->
					<button type="button" class="tab " onclick="document.location.href='/admin/user/user_view_etc.go?userId=${user.userId}';"><span>기타</span></button>
				</div>				
				
				
				<div class="tbl-list">
					<table class="register" style="margin-top:10px;">
							<thead>
								<tr class="center">
									<th>해당년월</th>
									<th>체중 월 평균</th>
									<th>BMI 월 평균</th>
								</tr>
							</thead>
							<tbody>
								<tr class="center">
									<td style="padding-left:10px; background-color:#f8f8f8;">${ym}</td>
									<td>${weightavg}</td>
									<td><fmt:formatNumber value="${bmiavg}" pattern=".0"></fmt:formatNumber></td>
								</tr>
							</tbody>
					</table>
				
					<br>
					<iframe name="graph" src="/m/grape_do.go?userId=${user.userId}&diseaseId=weight&speriod=${before}&eperiod=${today}" style="border:0; width:990px; height:280px;"></iframe>
					
					<table class="usertext" style="margin-top:30px; width:100%;">
						<thead>
							<th>체중</th>
							<th>BMI</th>								
							<th>체지방</th>
							<th>체수분</th>
							<th>근육</th>							
							<th>골량</th>
							<th>장비 사용여부</th>
							<th>등록일자</th>
						</thead>
						<tbody>
						<c:choose>
							<c:when test="${UserWeightlist.size() > 0}">
								<c:forEach var="it" items="${UserWeightlist}">
									<tr class="center">
										<td>${it.weightNum}</td>
										<td><fmt:formatNumber value="${it.bmi}" pattern=".0"></fmt:formatNumber></td>
										<td>${it.bbmi}</td>
										<td>${it.tbw }</td>
										<td>${it.muscle }</td>
										<td>${it.bmd }</td>	
										<td>${it.equipText}</td>									
										<td>${it.regDate.substring(0,16) }</td>


									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr><td colspan="7" style="text-align: center;" >조회된 데이터가 없습니다.</td></tr>
							</c:otherwise>
						</c:choose>
						</tbody>
					</table>
				${paging}
				</div>	
				
				<!-- 
				<div>
					<h2>사용자 정보 추가하기</h2>
					<form method="post" name="firmForm" onsubmit="return submitForm(this); return false;">
						<input type="hidden" name="userId" value="${user.userId}">
						<input type="hidden" name="height" value="${userBasic.height}">
						<table class="usertext" style="margin-top:30px; width:100%;">
							
							<thead>
								<tr>
									<th>체중</th>								
									<th>체지방</th>
									<th>체수분</th>
									<th>근육</th>							
									<th>골량</th>
									<th>장비 사용여부</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td> <input type="text" class="itext"  name="weightNum"></td>
									<td> <input type="text" class="itext" name="bbmi"></td>
									<td> <input type="text" class="itext" name="tbw"></td>
									<td> <input type="text" class="itext" name="muscle"></td>
									<td> <input type="text" class="itext" name="bmd"></td>
									<td> <input type="text" class="itext" name="equip"></td>
								</tr>
							</tbody>
						</table>
						<div  class="btn-tools" >	
							<button type="submit" class="btn">추가하기</button>
						</div>
					</form>
				</div>
				 -->
					
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>