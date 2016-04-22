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
			goalsMblood	: frm.goalsMblood.value,
			goalbMblood	: frm.goalbMblood.value,
			goalEblood	: frm.goalEblood.value,
			goalSblood	: frm.goalSblood.value,
			goalHba	: frm.goalHba.value,
			goalsPre	: frm.goalsPre.value,
			goalbPre	: frm.goalbPre.value,
			goalPul	: frm.goalPul.value,
			goalCol	: frm.goalCol.value,
			goalLdl	: frm.goalLdl.value,
			goalHdl	: frm.goalHdl.value,
			goalTg	: frm.goalTg.value,
			goalsBmi	: frm.goalsBmi.value,
			goalbBmi	: frm.goalbBmi.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/user/user_goal.go",
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
					<button type="button" class="tab active" onclick="document.location.href='/admin/user/user_view_goal.go?userId=${user.userId}';"><span>관리목표</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_week.go?userId=${user.userId}';"><span>관리주기</span></button>
					<button type="button" class="tab " onclick="document.location.href='/admin/user/user_view_medi.go?userId=${user.userId}';"><span>복약정보</span></button>
					<button type="button" class="tab " onclick="document.location.href='/admin/user/user_cv_risk.go?userId=${user.userId}';"><span>CV_RISK</span></button>
					<button type="button" class="tab " onclick="document.location.href='/admin/user/user_view_done.go?userId=${user.userId}';"><span>수행내역</span></button>
					<!-- <button type="button" class="tab" onclick="document.location.href='/admin/user/user_view_hospital.go?userId=${user.userId}';"><span>병원 목록</span></button> -->
					<button type="button" class="tab " onclick="document.location.href='/admin/user/user_view_etc.go?userId=${user.userId}';"><span>기타</span></button>
				</div>
								
				<div class="tbl-list">
				<!-- 
				<table class="register" style="margin-top:10px;">
						<thead>
							<th>아침공복 혈당</th>
							<th>식후 혈당</th>
							<th>취침전 혈당</th>
							<th>당화혈색소</th>
							<th>혈압</th>
							<th>맥박</th>
							<th>콜레스테롤</th>
							<th>저밀도 콜레스테롤</th>
							<th>고밀도 콜레스테롤</th>
							<th>중성지방</th>
							<th>BMI</th>
							<th>등록일자</th>
						</thead>
						<tbody>
							<tr>
								<td style="text-align:left; padding-left:10px;">${Usergoal.goalsMblood}-${Usergoal.goalbMblood}</td>
								<td style="text-align:left; padding-left:10px;">${Usergoal.goalEblood}</td>
								<td style="text-align:left; padding-left:10px;">${Usergoal.goalSblood}</td>
								<td style="text-align:left; padding-left:10px;">${Usergoal.goalHba}</td>
								<td style="text-align:left; padding-left:10px;">${Usergoal.goalsPre}-${Usergoal.goalbPre}</td>
								<td style="text-align:left; padding-left:10px;">${Usergoal.goalPul}</td>
								<td style="text-align:left; padding-left:10px;">${Usergoal.goalCol}</td>
								<td style="text-align:left; padding-left:10px;">${Usergoal.goalLdl}</td>
								<td style="text-align:left; padding-left:10px;">${Usergoal.goalHdl}</td>
								<td style="text-align:left; padding-left:10px;">${Usergoal.goalTg}</td>
								<td style="text-align:left; padding-left:10px;">${Usergoal.goalsBmi}-${Usergoal.goalbBmi}</td>
								<td style="text-align:left; padding-left:10px;">${fn:substring(Usergoal.regDate,0,16)}</td>
							</tr>
						</tbody>
					</table>
					 -->
				
					
					<table class="usertext" style="margin-top:30px; width:100%;">
						<thead>
							<tr class="center">
								<c:if test="${userBasic.blood =='blood'}">
									<th>아침공복<br>혈당</th>
									<th>식후<br>혈당</th>
									<th>취침전<br>혈당</th>
									<th>당화혈<br>색소</th>
								</c:if>
								<c:if test="${userBasic.press =='press'}">
									<th>수축기<br>혈압</th>
									<th>이완기<br>혈압</th>
									<th>맥박</th>
								</c:if>
								<c:if test="${userBasic.col =='col'}">
									<th>총콜레스테롤</th>
									<th>LDL<br>콜레스테롤</th>
									<th>HDL<br>콜레스테롤</th>
									<th>중성지방</th>
								</c:if>
								<c:if test="${userBasic.heiwieght =='heiwieght'}">
									<th>BMI</th>
								</c:if>
								<!--  -->
								<th>등록일자</th>
							</tr>
						</thead>
						<tbody>
						<c:choose>
							<c:when test="${UserGoallist.size() > 0}">
								<c:forEach var="it" items="${UserGoallist}">
								<tr class="center">
									<c:if test="${userBasic.blood =='blood'}">
										<td>${it.goalsMblood}-${it.goalbMblood}</td>
										<td>${it.goalEblood}미만</td>
										<td>${it.goalSblood}미만</td>
										<td>${it.goalHba}</td>
									</c:if>
									<c:if test="${userBasic.press =='press'}">
										<td>${it.goalbPre}미만</td>
										<td>${it.goalsPre}미만</td>
										<td>${it.goalPul}</td>
									</c:if>
									<c:if test="${userBasic.col =='col'}">
										<td>${it.goalCol}미만</td>
										<td>${it.goalLdl}</td>
										<td>${it.goalHdl}</td>
										<td>${it.goalTg}</td>
									</c:if>
									<c:if test="${userBasic.heiwieght =='heiwieght'}">
										<td>${it.goalsBmi}-${it.goalbBmi}</td>
									</c:if>
									<td>${fn:substring(it.regDate,0,16)}</td>
								</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr><td colspan="12" style="text-align: center;" >조회된 데이터가 없습니다.</td></tr>
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
									<c:if test="${userBasic.blood =='blood'}">
										<th>아침공복<br>혈당</th>
										<th>식후<br>혈당</th>
										<th>취침전<br>혈당</th>
										<th>당화혈<br>색소</th>
									</c:if>
									<c:if test="${userBasic.press =='press'}">
										<th>수축기<br>혈압</th>
										<th>이완기<br>혈압</th>
										<th>맥박</th>
									</c:if>
									<c:if test="${userBasic.col =='col'}">
										<th>총콜레스테롤</th>
										<th>저밀도<br>콜레스테롤</th>
										<th>고밀도<br>콜레스테롤</th>
										<th>중성지방</th>
									</c:if>
									<c:if test="${userBasic.heiwieght =='heiwieght'}">
										<th>BMI</th>
									</c:if>
								</tr>
							</thead>
							<tbody>
								<tr>
									<c:if test="${userBasic.blood =='blood'}">
										<td> <input type="text" class="itext"  name="goalsMblood" style="width:30px;">-<input type="text" class="itext"  name="goalbMblood" style="width:30px;"></td>
										<td> <input type="text" class="itext" name="goalEblood" style="width:30px;"></td>
										<td> <input type="text" class="itext" name="goalSblood" style="width:30px;"></td>
										<td> <input type="text" class="itext" name="goalHba" style="width:30px;"></td>
									</c:if>
									<c:if test="${userBasic.press =='press'}">
										<td> <input type="text" class="itext" name="goalbPre" style="width:30px;"></td>
										<td> <input type="text" class="itext" name="goalsPre" style="width:30px;"></td>
										<td> <input type="text" class="itext" name="goalPul" style="width:30px;"></td>
									</c:if>
									<c:if test="${userBasic.col =='col'}">
										<td> <input type="text" class="itext" name="goalCol" style="width:30px;"></td>
										<td> <input type="text" class="itext" name="goalLdl" style="width:30px;"></td>
										<td> <input type="text" class="itext" name="goalHdl" style="width:30px;"></td>
										<td> <input type="text" class="itext" name="goalTg" style="width:30px;"></td>
									</c:if>
									<c:if test="${userBasic.heiwieght =='heiwieght'}">
										<td> <input type="text" class="itext" name="goalsBmi" style="width:30px;">- <input type="text" class="itext" name="goalbBmi" style="width:30px;"></td>
									</c:if>
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