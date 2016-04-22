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
			mediweek1	: frm.mediweek1.value,
			mediweek2	: frm.mediweek2.value,
			mediweek3	: frm.mediweek3.value,
			mediweek4	: frm.mediweek4.value,
			mediweek5	: frm.mediweek5.value,
			mediweek6	: frm.mediweek6.value,
			mediweek7	: frm.mediweek7.value,
			mediname	: frm.mediname.value,
			meditime	: frm.meditime.value,
			medialert	: frm.medialert.value,
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/user/user_medi.go",
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
					<button type="button" class="tab active" onclick="document.location.href='/admin/user/user_view_medi.go?userId=${user.userId}';"><span>복약정보</span></button>
					<button type="button" class="tab " onclick="document.location.href='/admin/user/user_cv_risk.go?userId=${user.userId}';"><span>CV_RISK</span></button>
					<button type="button" class="tab " onclick="document.location.href='/admin/user/user_view_done.go?userId=${user.userId}';"><span>수행내역</span></button>
					<!-- <button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_hospital.go?userId=${user.userId}';"><span>병원 목록</span></button> -->
					<button type="button" class="tab " onclick="document.location.href='/admin/user/user_view_etc.go?userId=${user.userId}';"><span>기타</span></button>
				</div>				
				
				
				<div class="tbl-list">
					<table class="usertext" style="margin-top:30px; width:100%;">
						<thead>
							<th>약이름</th>
							<th>월</th>
							<th>화</th>
							<th>수</th>
							<th>목</th>
							<th>금</th>
							<th>토</th>
							<th>일</th>
							<th>복약시간</th>								
							<th>복약알림</th>
						</thead>
						<tbody>
						<c:choose>
							<c:when test="${UserMedilist.size() > 0}">
								<c:forEach var="it" items="${UserMedilist}">
									<tr class="center">

										<td>${it.mediname}</td>
										<td>
											<c:if test="${it.mediweek1 == 1 }">
												복용
											</c:if>
										</td>
										<td>
											<c:if test="${it.mediweek2 == 1 }">
											복용
											</c:if>
										</td>
										<td>
											<c:if test="${it.mediweek3 == 1 }">
											복용
											</c:if>
										</td>
										<td>
											<c:if test="${it.mediweek4 == 1 }">
											복용
											</c:if>
										</td>
										<td>
											<c:if test="${it.mediweek5 == 1 }">
											복용
											</c:if>
										</td>
										<td>
											<c:if test="${it.mediweek6 == 1 }">
											복용
											</c:if>
										</td>
										<td>
											<c:if test="${it.mediweek7 == 1 }">
											복용
											</c:if>
										</td>
										<td>${it.meditime}</td>
										<td>${it.alertText}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr><td colspan="10" style="text-align: center;" >조회된 데이터가 없습니다.</td></tr>
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
						<table class="usertext" style="margin-top:30px; width:100%;">
							
							<thead>
								<tr>
							<th>약이름</th>
							<th>월</th>
							<th>화</th>
							<th>수</th>
							<th>목</th>
							<th>금</th>
							<th>토</th>
							<th>일</th>
							<th>복약시간</th>								
							<th>복약알림</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td> <input type="text" class="itext"  name="mediname"></td>
									<td> <input type="text" class="itext" name="mediweek1" style="width:10px;"></td>
									<td> <input type="text" class="itext" name="mediweek2" style="width:10px;"></td>
									<td> <input type="text" class="itext" name="mediweek3"style="width:10px;"></td>
									<td> <input type="text" class="itext" name="mediweek4" style="width:10px;"></td>
									<td> <input type="text" class="itext" name="mediweek5" style="width:10px;"></td>
									<td> <input type="text" class="itext" name="mediweek6" style="width:10px;"></td>
									<td> <input type="text" class="itext" name="mediweek7" style="width:10px;"></td>
									<td> <input type="text" class="itext" name="meditime"></td>
									<td> <input type="text" class="itext" name="medialert"></td>								
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