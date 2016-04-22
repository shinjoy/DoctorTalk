<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
$(document).ready(function() {
	aside.setActive(1,1);
});	
	
	
	function deleteCompany(companySeq) {
		if(confirm("업체를 삭제하시겠습니까?")) {
			var param = {
					companySeq	:	companySeq
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/firm/firm_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/firm/firm.go";
					}
				}
			});
		}
		return false;

	}
	
	function deleteCompanyImg(companySeq, photo) {
		
		if(confirm("해당사진을 삭제하시겠습니까?")) {
			var param = {
					companySeq	:	companySeq,
					photo 		:	photo
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/firm/firmImg_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/firm/firm.go";
					}
				}
			});
		}
		return false;

	}
	
	
	function deleteMenuImg(menuSeq, photo1) {
		
		if(confirm("해당메뉴사진을 삭제하시겠습니까?")) {
			var param = {
					menuSeq		:	menuSeq,
					photo1		:	photo1
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/firm/menuImg_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/firm/menu_list.go";
					}
				}
			});
		}
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
				 ■ 홈 > 업체관리 > 업체 상세보기
			</header>
		
			<div class="contents-block">
			
				<h1>업체 상세보기</h1>
				
				<div class="contents-main">

					<div class="contents-box">
				
						<form method="post" name="firmForm" onsubmit="return submitForm(this); return false;">
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
					<button type="button" class="btn" onclick="document.location.href='/admin/firm/firm_edit.go?companySeq=${company.companySeq}';">정보수정</button>
					<button type="button" class="btn" onclick="deleteCompany(${company.companySeq});">업체삭제</button>
					<!-- <button type="button" class="btn" onclick="submitForm(printcenterForm);">업체추천</button> -->
					<button type="button" class="btn" onclick="document.location.href='/admin/firm/firm.go';">목록</button>
				</div>
				
				<div class="tab-bar">
					<button type="button" class="tab active" onclick="document.location.href='/admin/firm/firm_view.go?companySeq=${company.companySeq}';"><span>업체사진</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/firm/menu_list.go?companySeq=${company.companySeq}';"><span>메뉴</span>(${count})</button>
					<button type="button" class="tab" onclick="document.location.href='/admin/firm/review_view.go?companySeq=${company.companySeq}';"><span>리뷰</span></button>
				</div>			
			
				<div class="tbl-list">
			<table class="request">
				<tbody class="rl">
				<c:choose>
					<c:when test="${list.size() > 0}">
						<c:forEach var="it" items="${list}">
							<tr>
							<th><div class="icon"> 메뉴명</div></th>
							<td><a href="/admin/firm/menu_detail.go?companySeq=${it.companySeq}&menuSeq=${it.menuSeq}" >${it.menuName}</a></td>
						</tr>
						<tr>
							<th><div class="icon"> 한줄 소개</div></th>
							<td>${it.cover}</td>
						</tr>
						
						<tr>
							<th><div class="icon">${it.menuOption1}  </div></th>
							<td>${it.menuPrice1}</td>
						</tr>
						<tr>
							<th><div class="icon">${it.menuOption2}  </div></th>
							<td>${it.menuPrice2}</td>
						</tr>
						<tr>
							<th><div class="icon">${it.menuOption3}  </div></th>
							<td>${it.menuPrice3}</td>
						</tr>
						
						<tr>
							<th>소개사진</th>
							<td>
							<c:choose>
								<c:when test="${!empty fn:trim(it.photo1)}">	
								<img src="/files/menu/thumb/${it.photo1}" style="height:100px;">
								</c:when>
								<c:otherwise>
									 등록된 이미지가 없습니다.
								</c:otherwise>
							</c:choose>
							</td>
						</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						등록된 메뉴가 없습니다.				
						
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		
		</div>			
	
		<div>
			<button class="btn" style="margin-top:10px; margin-left:10px;" onclick="document.location.href='/admin/firm/menu_edit.go?companySeq=${company.companySeq}';" >메뉴추가</button>
			<!-- <button class="btn" style="margin-top:10px; margin-left:10px;" onclick="document.location.href='/admin/firm/menu_edit.go';" >메뉴삭제</button>
			<button class="btn" style="margin-top:10px; margin-left:10px;" onclick="document.location.href='/admin/firm/menu_view.go';" >메뉴목록</button> -->
		</div>	
					
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>