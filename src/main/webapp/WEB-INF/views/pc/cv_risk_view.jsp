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
		
		document.location.href = "/pc/cv_risk_view.go?userId="+searForm.seaId.value+"&page="+page;

		return false;
	}
	
	
	
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
					<button type="button" class="tab" onclick="document.location.href='/pc/hemoglobin_view.go?userId=${user.userId}';"><span>당화혈색소</span></button>
					<button type="button" class="tab" onclick="document.location.href='/pc/goal_view.go?userId=${user.userId}';"><span>관리목표</span></button>
					<button type="button" class="tab" onclick="document.location.href='/pc/week_view.go?userId=${user.userId}';"><span>관리주기</span></button>
					<button type="button" class="tab " onclick="document.location.href='/pc/medi_view.go?userId=${user.userId}';"><span>복약정보</span></button>
					<button type="button" class="tab active" onclick="document.location.href='/pc/cv_risk_view.go?userId=${user.userId}';"><span>CV_RISK</span></button>
					<button type="button" class="tab " onclick="document.location.href='/pc/hospital_view.go?userId=${user.userId}';"><span>병원 목록</span></button>
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
				
				
					<form method="post" name="searForm" onsubmit="return searchList(this); return false;">
						<input type="hidden" name="seaId" value="${user.userId}">	
							<table class="usertext" style="margin-top:30px; width:100%;">
								<thead>
									<tr>
										<th>cv수치</th>
										<th>콜레스테롤</th>
										<th>HDL 콜레스테롤</th>
										<th>이완기</th>
										<th>수축기</th>
										<th>흡연여부</th>
										<th>10년후 나이</th>
										<th>등록일자</th>
									</tr>
								</thead>
								<tbody>
								<c:choose>
									<c:when test="${UserCvrisklist.size() > 0}">
										<c:forEach var="it" items="${UserCvrisklist}">
											<tr>
												<td>${it.cvNum}</td>
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
					</form>	
				</div>
			</div>
		</section>
	</section>
</section>

</body>
</html>