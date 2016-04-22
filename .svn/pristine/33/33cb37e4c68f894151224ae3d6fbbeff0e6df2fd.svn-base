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
		
		if(confirm("해당사진을 삭제하시겠습니까?") == true) {
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
						document.location.href = "/admin/firm/firm_view.go?companySeq="+${company.companySeq}
					}
				}
			});
		}
		return;

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
					<button type="button" class="tab" onclick="document.location.href='/admin/firm/menu_list.go?companySeq=${company.companySeq}';"><span>메뉴</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/firm/review_view.go?companySeq=${company.companySeq}';"><span>리뷰</span></button>
				</div>			
			<form method="post" name="listForm" id="listForm" enctype="multipart/form-data" action="/admin/firm/firmImg_add_do.go">
				<input type="hidden" name="companySeq" value="${company.companySeq}">
								
				<div class="tbl-list">
					<table>
						<tbody class="rl">
								
							<tr>
								<td>
								<c:choose>
								<c:when test="${!empty fn:trim(company.photo1)}">	
								<img src="/files/company/thumb/${company.photo1}" style="height:50px;">&nbsp; &nbsp;
								<button class="btn tiny" onclick="deleteCompanyImg(${company.companySeq}, '${company.photo1}');">파일삭제</button>
								</c:when>
								<c:otherwise>
									<strong>메인사진 추가</strong>&nbsp; &nbsp;<input type="file" name="photo1"><br><br>
								</c:otherwise>
								</c:choose>
								</td>
							</tr>
														
							<tr>
								<td>
								<c:choose>
								<c:when test="${!empty fn:trim(company.photo2)}">	
								<img src="/files/company/thumb/${company.photo2}" style="height:50px;">&nbsp; &nbsp;
								<button class="btn tiny" onclick="deleteCompanyImg(${company.companySeq}, '${company.photo2}');">파일삭제</button>
								</c:when>
								<c:otherwise>
									2번사진 추가&nbsp; &nbsp; <input type="file" name="photo2"><br><br>
								</c:otherwise>
								</c:choose>
								</td>
							</tr>
							
							<tr>
								<td>
								<c:choose>
								<c:when test="${!empty fn:trim(company.photo3)}">	
								<img src="/files/company/thumb/${company.photo3}" style="height:50px;">&nbsp; &nbsp;
								<button class="btn tiny" onclick="deleteCompanyImg(${company.companySeq}, '${company.photo3}');">파일삭제</button>
								</c:when>
								<c:otherwise>
									3번사진 추가&nbsp; &nbsp; <input type="file" name="photo3"><br><br>
								</c:otherwise>
								</c:choose>
								</td>
							</tr>
							
							<tr>
								<td>
								<c:choose>
								<c:when test="${!empty fn:trim(company.photo4)}">	
								<img src="/files/company/thumb/${company.photo4}" style="height:50px;">&nbsp; &nbsp;
								<button class="btn tiny" onclick="deleteCompanyImg(${company.companySeq}, '${company.photo4}');">파일삭제</button>
								</c:when>
								<c:otherwise>
									4번사진 추가&nbsp; &nbsp; <input type="file" name="photo4"><br><br>
								</c:otherwise>
								</c:choose>
								</td>
							</tr>
							
							<tr>
								<td>
								<c:choose>
								<c:when test="${!empty fn:trim(company.photo5)}">	
								<img src="/files/company/thumb/${company.photo5}" style="height:50px;">&nbsp; &nbsp;
								<button class="btn tiny" onclick="deleteCompanyImg(${company.companySeq}, '${company.photo5}');">파일삭제</button>
								</c:when>
								<c:otherwise>
									5번사진 추가&nbsp; &nbsp; <input type="file" name="photo5"><br><br>
								</c:otherwise>
								</c:choose>
								</td>
							</tr>
			
							<tr>
							<td><button type="submit" class="btn">이미지 등록</button></td>
							</tr>
				</tbody>
				
			</table>
		
		</div>
		</form>			
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>