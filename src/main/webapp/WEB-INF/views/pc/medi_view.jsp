<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(1,1);
	});
	

	function searchList(searForm,page) {
		
		document.location.href = "/pc/medi_view.go?userId="+searForm.seaId.value+"&page="+page;

		return false;
	}	
	
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
			medialert	: frm.medialert.value
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

<section class="main-cover main-row">
	<section id="main">

		<section id="contents">

			<div class="contents-block">
			
				<h1>${user.userName}</h1>
				
				<%@ include file="/WEB-INF/views/admin/user/user_info.jsp"  %>

				<br><br>
				<div  class="btn-tools" >	
				</div>
				
				<div class="tab-bar">
					<button type="button" class="tab " onclick="document.location.href='/pc/blood_view.go?userId=${user.userId}';"><span>혈당</span></button>
					<button type="button" class="tab" onclick="document.location.href='/pc/pressure_view.go?userId=${user.userId}';"><span>혈압</span></button>
					<button type="button" class="tab" onclick="document.location.href='/pc/weight_view.go?userId=${user.userId}';"><span>체중</span></button>
					<button type="button" class="tab " onclick="document.location.href='/pc/cholesterol_view.go?userId=${user.userId}';"><span>콜레스테롤</span></button>
					<button type="button" class="tab " onclick="document.location.href='/pc/hemoglobin_view.go?userId=${user.userId}';"><span>당화혈색소</span></button>
					<button type="button" class="tab " onclick="document.location.href='/pc/goal_view.go?userId=${user.userId}';"><span>관리목표</span></button>
					<button type="button" class="tab" onclick="document.location.href='/pc/week_view.go?userId=${user.userId}';"><span>관리주기</span></button>
					<button type="button" class="tab active" onclick="document.location.href='/pc/medi_view.go?userId=${user.userId}';"><span>복약정보</span></button>
					<button type="button" class="tab " onclick="document.location.href='/pc/cv_risk_view.go?userId=${user.userId}';"><span>CV_RISK</span></button>
					<button type="button" class="tab " onclick="document.location.href='/pc/hospital_view.go?userId=${user.userId}';"><span>병원 목록</span></button>	
				</div>				
				
				
				<div class="tbl-list">
					<form method="post" name="searForm" onsubmit="return searchList(this); return false;">
						<input type="hidden" name="seaId" value="${user.userId}">
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
											<tr>
		
												<td style="text-align:left; padding-left:10px;">${it.mediname}</td>
												<td style="text-align:left; padding-left:10px;">
													<c:if test="${it.mediweek1 == 1 }">
														복용
													</c:if>
												</td>
												<td style="text-align:left; padding-left:10px;">
													<c:if test="${it.mediweek2 == 1 }">
													복용
													</c:if>
												</td>
												<td style="text-align:left; padding-left:10px;">
													<c:if test="${it.mediweek3 == 1 }">
													복용
													</c:if>
												</td>
												<td style="text-align:left; padding-left:10px;">
													<c:if test="${it.mediweek4 == 1 }">
													복용
													</c:if>
												</td>
												<td style="text-align:left; padding-left:10px;">
													<c:if test="${it.mediweek5 == 1 }">
													복용
													</c:if>
												</td>
												<td style="text-align:left; padding-left:10px;">
													<c:if test="${it.mediweek6 == 1 }">
													복용
													</c:if>
												</td>
												<td style="text-align:left; padding-left:10px;">
													<c:if test="${it.mediweek7 == 1 }">
													복용
													</c:if>
												</td>
												<td style="text-align:left; padding-left:10px;">${it.meditime}</td>
												<td style="text-align:left; padding-left:10px;">${it.alertText}</td>
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
					</form>	
				</div>	
				
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
					
			</div>
		</section>
	</section>
</section>

</body>
</html>