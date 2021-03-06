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
			haveHistory	: frm.haveHistory.value,
			familyHistory	: frm.familyHistory.value,
			drugHistory : frm.drugHistory.value,
			oralKind	: frm.oralKind.value,
			oralAmount	: frm.oralAmount.value,
			oralUse	: frm.oralUse.value,
			insulinKind	: frm.insulinKind.value,
			insulinAmount	: frm.insulinAmount.value,
			insulinUse	: frm.insulinUse.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/user/user_edit_do.go",
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
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_medi.go?userId=${user.userId}';"><span>복약정보</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_cv_risk.go?userId=${user.userId}';"><span>CV_RISK</span></button>
					<button type="button" class="tab " onclick="document.location.href='/admin/user/user_view_done.go?userId=${user.userId}';"><span>수행내역</span></button>
					<button type="button" class="tab active" onclick="document.location.href='/admin/user/user_view_hospital.go?userId=${user.userId}';"><span>병원 목록</span></button>
					<button type="button" class="tab " onclick="document.location.href='/admin/user/user_view_etc.go?userId=${user.userId}';"><span>기타</span></button>
				</div>
								
				<div class="tbl-list">
<!-- 				<table class="register" style="margin-top:10px;"> -->
<!-- 						<thead> -->
<!-- 							<th>아침공복 혈당</th> -->
<!-- 							<th>식후 혈당</th> -->
<!-- 							<th>취침전 혈당</th> -->
<!-- 							<th>당화혈색소</th> -->
<!-- 							<th>혈압</th> -->
<!-- 							<th>맥박</th> -->
<!-- 							<th>콜레스테롤</th> -->
<!-- 							<th>저밀도 콜레스테롤</th> -->
<!-- 							<th>고밀도 콜레스테롤</th> -->
<!-- 							<th>중성지방</th> -->
<!-- 							<th>BMI</th> -->
<!-- 							<th>등록일자</th> -->
<!-- 						</thead> -->
<!-- 						<tbody> -->
<!-- 							<tr> -->
<%-- 								<td style="text-align:left; padding-left:10px;">${Usergoal.goalsMblood}-${Usergoal.goalbMblood}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${Usergoal.goalEblood}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${Usergoal.goalSblood}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${Usergoal.goalHba}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${Usergoal.goalsPre}-${Usergoal.goalbPre}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${Usergoal.goalPul}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${Usergoal.goalCol}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${Usergoal.goalLdl}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${Usergoal.goalHdl}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${Usergoal.goalTg}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${Usergoal.goalsBmi}-${Usergoal.goalbBmi}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${fn:substring(Usergoal.regDate,0,16)}</td> --%>
<!-- 							</tr> -->
<!-- 						</tbody> -->
<!-- 					</table> -->
				
				
<!-- 					<table class="usertext" style="margin-top:30px; width:100%;"> -->
<!-- 						<thead> -->
<!-- 							<th>과거력</th> -->
<!-- 							<th>가족력</th> -->
<!-- 							<th>복용력</th> -->
<!-- 							<th>경구혈당강하제 종류</th> -->
<!-- 							<th>경구혈당강하제 용량</th> -->
<!-- 							<th>경구혈당강하제 용법</th> -->
<!-- 							<th>인슐린 종류</th> -->
<!-- 							<th>인슐린 용량</th> -->
<!-- 							<th>인슐린 용법</th> -->
<!-- 						</thead> -->
<!-- 						<tbody> -->
<%-- 						<c:choose> --%>
<%-- 							<c:when test="${UserGoallist.size() > 0}"> --%>
<%-- 								<c:forEach var="it" items="${UserGoallist}"> --%>
<!-- 									<tr> -->
<%-- 								<td style="text-align:left; padding-left:10px;">${it.goalsMblood}-${it.goalbMblood}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${it.goalEblood}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${it.goalSblood}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${it.goalHba}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${it.goalsPre}-${it.goalbPre}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${it.goalPul}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${it.goalCol}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${it.goalLdl}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${it.goalHdl}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${it.goalTg}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${it.goalsBmi}-${it.goalbBmi}</td> --%>
<%-- 								<td style="text-align:left; padding-left:10px;">${fn:substring(it.regDate,0,16)}</td> --%>
<!-- 									</tr> -->
<%-- 								</c:forEach> --%>
<%-- 							</c:when> --%>
<%-- 							<c:otherwise> --%>
<!-- 								<tr><td colspan="12" style="text-align: center;" >조회된 데이터가 없습니다.</td></tr> -->
<%-- 							</c:otherwise> --%>
<%-- 						</c:choose> --%>
<!-- 						</tbody> -->
<!-- 					</table> -->
<%-- 				${paging} --%>



				</div>
				
				<div>
					<form method="post" name="firmForm" onsubmit="return submitForm(this); return false;">
						<input type="hidden" name="userId" value="${user.userId}">
						<table class="usertext" style="margin-top:30px; width:100%;">
							<thead>
								<tr>
									<th>경구혈당강하제 종류</th>
									<td> <input type="text" class="itext" style="width:200px;" name="haveHistory" value="${userBasic.haveHistory}">  </td>
									<th>경구혈당강하제 용량</th>
									<td> <input type="text" class="itext" style="width:200px;" name="familyHistory" value="${userBasic.familyHistory}" >  </td>
									<th>경구혈당강하제 용법</th>
									<td> <input type="text" class="itext" style="width:200px;" name="drugHistory" value="${userBasic.drugHistory}">   </td>
								</tr>
								<tr>
									<th>인슐린 종류</th>
									<td> <input type="text" class="itext" style="width:200px;" name="insulinKind" value="${userBasic.insulinKind}"> </td>
									<th>인슐린 용량</th>
									<td> <input type="text" class="itext" style="width:200px;" name="insulinAmount" value="${userBasic.insulinAmount}">   </td>
									<th>인슐린 용법</th>
									<td> <input type="text" class="itext" style="width:200px;" name="insulinUse" value="${userBasic.insulinUse}"> </td>
								</tr>
								<tr>
									<th>과거력</th>
									<td colspan="5"> <input type="text" class="itext" style="width:850px;" name="oralKind" value="${userBasic.oralKind}">    </td>
								</tr>
								<tr>
									<th>가족력</th>
									<td colspan="5"> <input type="text" class="itext" style="width:850px;" name="oralAmount" value="${userBasic.oralAmount}"> </td>
								</tr>
								<tr>
									<th>복용력</th>
									<td colspan="5"> <input type="text" class="itext" style="width:850px;" name="oralUse" value="${userBasic.oralUse}"> </td>
								</tr>
							</thead>
						</table>
						<div  class="btn-tools" >	
							<button type="submit" class="btn">등록하기</button>
						</div>
					</form>
				</div>
						
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>