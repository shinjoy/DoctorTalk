<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
$(document).ready(function() {
	aside.setActive(1,1);
}); 
	 function deleteMenuImg(menuSeq, photo1) {
			
			if(confirm("메뉴사진을 삭제하시겠습니까?")) {
				var param = {
						menuSeq		:	menuSeq,
						photo1 		:	photo1
				};
				
				$.ajax({
					type:"POST",
					url:"/admin/firm/menuImg_delete_do.go",
					dataType:"json",
					data:param,
					success:function(json){
						alert(json.message);
						if (json.result) {
							document.location.reload();
					
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
					<button type="button" class="tab" onclick="document.location.href='/admin/firm/firm_view.go';"><span>업체사진</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/firm/menu_detail.go';"><span>메뉴</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/firm/review_view.go';"><span>리뷰</span></button>
				</div>			
				
				<form method="post" name="menuForm" enctype="multipart/form-data" action="/admin/firm/menu_edit_do.go">
					<input type="hidden" name="menuSeq" value="${companyMenu.menuSeq}">
					<input type="hidden" name="companySeq" value="${company.companySeq}">
					<input type="hidden" name="photo1" value="${companyMenu.photo1}">
				<table class="request">
				
				<tbody class="rl">
						<tr>
							<th><div class="icon"> 메뉴명</div></th>
							<td><input type="text" class="itext" name="menuName"  value="${companyMenu.menuName}"></td> 
						</tr>
						<tr>
							<th><div class="icon"> 한줄 소개</div></th>
							<td><input type="text" class="itext" name="cover"  value="${companyMenu.cover}"></td>
						</tr>
						
						<tr>
							<th><div class="icon">가격</div></th>
							<td><input type="text" class="itext" name="menuOption1"  value="${companyMenu.menuOption1}"></td>
							<td><input type="text" class="itext" name="menuPrice1"  value="${companyMenu.menuPrice1}"></td>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<td><input type="text" class="itext" name="menuOption2"  value="${companyMenu.menuOption2}"></td>
							<td><input type="text" class="itext" name="menuPrice2"  value="${companyMenu.menuPrice2}"></td>
						</tr>	
						<tr>
							<th>&nbsp;</th>
							<td><input type="text" class="itext" name="menuOption3"  value="${companyMenu.menuOption3}"></td>
							<td><input type="text" class="itext" name="menuPrice3"  value="${companyMenu.menuPrice3}"></td>
						</tr>
						<tr>
							<th><div class="icon">소개글</div></th>
							<td><input type="text" class="itext" name="introduce"  value="${companyMenu.introduce}"></td>
						</tr>
						<tr>
							<th><div class="icon">이용안내</div></th>
							<td><input type="text" class="itext" name="infoUse"  value="${companyMenu.infoUse}"></td>
						</tr>
						<tr>
							<th>소개사진</th>
							<td>
								<c:choose>
									<c:when test="${companyMenu.photo1 == '' || companyMenu.photo1 == null}">
										<input type="file" name="photo1">(200*200픽셀 이하의 이미지만 업로드 시켜주세요.)
									</c:when>
									<c:otherwise>
										<img src="/files/menu/thumb/${companyMenu.photo1}" style="height:100px;">
										<br>
										<button type="button" class="btn tiny" onclick="deleteMenuImg(${companyMenu.menuSeq},${companyMenu.companySeq},'${companyMenu.photo1}');">삭제</button>	
									</c:otherwise>
								</c:choose>
							</td>
						</tr>	
						<tr>
							<th><div class="icon">배송(배달)안내</div></th>
							<td><input type="text" class="itext" name="infoDelivery" value="${companyMenu.infoDelivery}"></td>
						</tr>
										
				</tbody>
				
			</table>
			<button type="submit" class="btn" style="margin-top:10px; margin-left:10px;">메뉴저장</button>
			</form>			
			</div>
		
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>