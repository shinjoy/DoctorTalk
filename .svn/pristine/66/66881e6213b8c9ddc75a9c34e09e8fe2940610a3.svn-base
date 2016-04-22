<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
$(document).ready(function() {
	aside.setActive(1,1);
});

function searchList(listForm,page) {
	var param = {
		companySeq : pageForm.companySeq.value,
		page		:	page
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/firm/review_view.go",
		dataType:"html",
		data:param,
		success:function(msg){
			$("#contents-list").html(msg);
			
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

</style>
</head>
<body>

<form name="pageForm">
<input type="hidden" name="companySeq" value="${company.companySeq}">
</form>
<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 업체관리 > 업체 상세보기
			</header>
		
			<div class="contents-block">
			
				<h1>업체 상세보기</h1>
				
				<div class="contents-main">

					<div class="contents-box">
				
						<form method="post" name="listForm" onsubmit="return submitForm(this); return false;">
						<input type="hidden" name="seq" value="${analysis.seq}">
							<table class="register">
								<tr>
									<th>업체명</th>
									<td>&nbsp; &nbsp;${company.companyName}</td>
								</tr>
								<tr>
									<th>카테고리</th>
									<td>&nbsp; &nbsp;${company.category1Name} > ${company.category2Name}</td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td>&nbsp; &nbsp;${company.phoneNumber}</td>
								</tr>
								<tr>
									<th>주소지</th>
									<td>&nbsp; &nbsp;${company.sido} ${company.gugun} ${company.address}</td>
								</tr>
								<tr>
									<th>가맹여부</th>
									<td>&nbsp;
									<c:if test="${company.isMember == 0}">일반</c:if>
									<c:if test="${company.isMember == 1}">가맹점</c:if>
									</td>
								</tr>
								<tr>
									<th>적립율</th>
									<td>&nbsp; &nbsp;현금 ${company.saveCash}%ㅣ신용카드 ${company.saveCard}%ㅣ다모인카드 ${company.saveDamoin}%</td>
								</tr>
								<tr>
									<th>배달여부</th>
									<td>&nbsp;
									<c:if test="${company.isDelivery == 0}">불가</c:if>
									<c:if test="${company.isDelivery == 1}">배달 가능</c:if>
									</td>
								</tr>
								<tr>
									<th>영업시간</th>
									<td>&nbsp; &nbsp;${company.openTime} ~ ${company.closeTime}</td>
								</tr>
								<tr>
									<th>휴무일</th>
									<td>&nbsp; &nbsp;${company.holiday}</td>
								</tr>
								<tr>
									<th>업체위치</th>
									<td>&nbsp; &nbsp;위도 : ${company.latitude} 경도: ${company.longitude}</td>
								</tr>
								<tr>
									<th>소개글</th>
									<td>&nbsp; &nbsp;${company.introduce}</td>
								</tr>
							</table>
	
						
						</form>
					</div>

				</div>
				
				<br><br>
				
				<div  class="btn-tools" >
					<!-- <button type="button" class="btn" onclick="submitForm(printcenterForm);">업체 페이지</button> -->		
					<button type="button" class="btn" onclick="deleteNotice(${device.deviceSeq});">정보수정</button>
					<button type="button" class="btn" onclick="document.location.href='/admin/device/device.go';">업체삭제</button>
					<!-- <button type="button" class="btn" onclick="submitForm(printcenterForm);">업체추천</button> -->
					<button type="button" class="btn" onclick="document.location.href='/admin/firm/firm.go';">목록</button>
				</div>
				
				<div class="tab-bar">
					<button type="button" class="tab active" onclick="document.location.href='/admin/firm/firm_view.go?companySeq=${company.companySeq}';"><span>업체사진</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/firm/menu_list.go?companySeq=${company.companySeq}';"><span>메뉴</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/firm/review_view.go?companySeq=${company.companySeq}';"><span>리뷰</span></button>
				</div>			
			
		<div class="tbl-list">
			<table class="request">
				<tbody class="rl">
				<c:choose>
					<c:when test="${list.size() > 0}">
						<c:forEach var="it" items="${list}">
							<tr>
							<td style="text-align: left; padding-left: 10px;"><strong>${it.userName}</strong> | ${it.regDate.substring(0,10)}</td>
						</tr>
						<tr>
							<td style="text-align: left; padding-left: 10px;">${it.contents}</td>
						</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						등록된 리뷰가 없습니다.				
						
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		${paging}
		</div>	
					
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>