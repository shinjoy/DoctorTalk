<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(1,1);
		searchList();
	});
	
	function searchList(page) {
		
		var param = {
				page	: page
		};
		
		
		$.ajax({
			type:"POST",
			url:"/admin/firm/menu_list.go",
			dataType:"html",
			data:param,
			success:function(msg){
				$("#img-contents").html(msg);
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
				
						<form method="post" name="printcenterForm" enctype="multipart/form-data" onsubmit="return submitForm(this); return false;">
						<input type="hidden" name="seq" value="${analysis.seq}">
							<table class="register">
								<tr>
									<th>업체명</th>
									<td>맛있는집</td>
								</tr>
								<tr>
									<th>카테고리</th>
									<td>일반음식점>한식/분식</td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td>02-123-1234</td>
								</tr>
								<tr>
									<th>주소지</th>
									<td>서울시 동작구 사당동 13길 10</td>
								</tr>
								<tr>
									<th>가맹여부</th>
									<td>가맹</td>
								</tr>
								<tr>
									<th>적립율</th>
									<td>현금 5%ㅣ신용카드 5%ㅣ다모인카드 10%</td>
								</tr>
								<tr>
									<th>배달여부</th>
									<td>배달가능</td>
								</tr>
								<tr>
									<th>영업시간</th>
									<td>09:00~24:00</td>
								</tr>
								<tr>
									<th>휴무일</th>
									<td>매주 세번째 일요일</td>
								</tr>
								<tr>
									<th>업체위치</th>
									<td>위도 : 36.8273236 경도: 127.12395125</td>
								</tr>
								<tr>
									<th>소개글</th>
									<td>맛있는집</td>
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
					<button type="button" class="tab active" onclick="document.location.href='/admin/firm/firm_view.go';"><span>업체사진</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/firm/menu_detail.go';"><span>메뉴</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/firm/review_view.go';"><span>리뷰</span></button>
				</div>			
			
				<div id="img-contents">
				</div>	
					
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>