<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
$(document).ready(function() {
	aside.setActive(2,1);
});
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
				 ■ 홈 > 업체관리 > 영업점 상세보기
			</header>
		
			<div class="contents-block">
			
				<h1>영업점 상세보기</h1>
				
				<div class="contents-main">

					<div class="contents-box">
				
						<form method="post" name="printcenterForm" enctype="multipart/form-data" onsubmit="return submitForm(this); return false;">
						<input type="hidden" name="seq" value="${analysis.seq}">
							<table class="register">
								<tr>
									<th>업체명</th>
									<td>${company.companyName}</td>
								</tr>
								<tr>
									<th>대표자명</th>
									<td>${company.userName}</td>
								</tr>
								<tr>
									<th>ID</th>
									<td>${company.userId}</td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td>${company.phoneNumber}</td>
								</tr>
								<tr>
									<th>지역설정</th>
									<td>${company.sido} ${company.gugun}</td>
								</tr>
								<tr>
									<th>신청일자</th>
									<td>${company.regDate.substring(0,10)}</td>
								</tr>
							</table>
	
						
						</form>
					</div>

				</div>
				
				<br><br>
				
				<div  class="btn-tools" >
					<!-- <button type="button" class="btn" onclick="submitForm(printcenterForm);">업체 수정/삭제</button> -->		
					<button type="button" class="btn" onclick="document.location.href='/admin/business/business.go';">목록</button>
				</div>
					
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>