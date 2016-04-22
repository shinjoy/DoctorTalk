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
			oralKind	: frm.oralKind.value,
			oralAmount	: frm.oralAmount.value,
			oralUse	: frm.oralUse.value,
			insulinKind	: frm.insulinKind.value,
			insulinAmount	: frm.insulinAmount.value,
			insulinUse	: frm.insulinUse.value,
			medicalReserve	: frm.medicalReserve.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/user/user_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/admin/user/user_view.go?userId="+frm.userId.value;
				}
			}
		});

		return false;
	}
	
	

</script>

<style>
table.register th {
   vertical-align: top;
   text-align: right;
   padding:10px;
   font-weight: bold;
}

table.register td {
	padding:10px;
    vertical-align: top;
}	
.tbl-list TD{
	border-bottom: 1px solid #ddd;
	padding: 5px;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 회원관리 > 일반회원 수정하기
			</header>
		
			<div class="contents-block">
			
				<h1>일반회원 수정하기</h1>
				
				<div>

					<div>
				
						<form method="post" name="firmForm" onsubmit="return submitForm(this); return false;">
						<input type="hidden" name="userId" value="${user.userId}">
							<table class="register" style="width:1000px;">
								<tr>
									<td>
										<span style="font-weight:bold;">과거력</span>
										<input type="text" class="itext" style="width:500px;" name="haveHistory" value="${userBasic.haveHistory}">   
									</td>
								</tr>
								<tr>
									<td>
										<span style="font-weight:bold;">가족력</span>
										<input type="text" class="itext" style="width:500px;" name="familyHistory" value="${userBasic.familyHistory}" >   
									</td>
								</tr>
								<tr>
									<td>
										<span style="font-weight:bold;">복용력</span>
										<input type="text" class="itext" style="width:500px;" name="drugHistory" value="${userBasic.drugHistory}">   
									</td>
								</tr>
								<tr>
									<td>
										<span style="font-weight:bold;">경구혈당강하제 종류</span>
										<input type="text" class="itext" style="width:500px;" name="oralKind" value="${userBasic.oralKind}">   
									</td>
								</tr>
								<tr>
									<td>
										<span style="font-weight:bold;">경구혈당강하제 양</span>
										<input type="text" class="itext" style="width:500px;" name="oralAmount" value="${userBasic.oralAmount}">   
									</td>
								</tr>
								<tr>
									<td>
										<span style="font-weight:bold;">경구혈당강하제 용법</span>
										<input type="text" class="itext" style="width:500px;" name="oralUse" value="${userBasic.oralUse}">   
									</td>
								</tr>
								<tr>
									<td>
										<span style="font-weight:bold;">인슐린 종류 </span>
										<input type="text" class="itext" style="width:500px;" name="insulinKind" value="${userBasic.insulinKind}">   
									</td>
								</tr>
								<tr>
									<td>
										<span style="font-weight:bold;">인슐린 용량</span>
										<input type="text" class="itext" style="width:500px;" name="insulinAmount" value="${userBasic.insulinAmount}">   
									</td>
								</tr>
								<tr>
									<td>
										<span style="font-weight:bold;">인슐린 용법</span>
										<input type="text" class="itext" style="width:500px;" name="insulinUse" value="${userBasic.insulinUse}">   
									</td>
								</tr>
								<tr>
									<td>
										<span style="font-weight:bold;">병원진료 예약 목록</span>
										<input type="text" class="itext" style="width:500px;"  name="medicalReserve" value="${userBasic.medicalReserve}">   
									</td>
								</tr>
								
							</table>
						</div>
					</div>
				<br><br>
					<div  class="btn-tools" style="width:1000px;">	
						<button type="submit" class="btn-blue" >수정</button>
<%-- 						<button type="button" class="btn" onclick="deleteUser('${user.userId}');">삭제</button> --%>
						<button type="button" class="btn" onclick="document.location.href='/admin/user/user_view.go?userId=${user.userId}';">뒤로가기</button>
					</div>
				</form>
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>