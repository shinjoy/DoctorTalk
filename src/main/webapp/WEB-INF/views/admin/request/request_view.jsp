<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	$(document).ready(function() {
		aside.setActive(1,3);
	});
	
	function approval(companySeq) {
		if(confirm("가맹신청을 승인하시겠습니까?")) {
			var param = {
					companySeq	:	companySeq
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/request/approval_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/request/request.go";
					}
				}
			});
		}
		return false;
	}
	
	function disapproval(companySeq) {
		if(confirm("가맹신청을 불허하시겠습니까?")) {
			var param = {
					companySeq	:	companySeq
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/request/disapproval_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/request/request.go";
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
				 ■ 홈 > 업체관리 > 가맹신청 정보
			</header>
		
			<div class="contents-block">
			
				<h1>가맹신청 정보</h1>
				
				<div class="contents-main">

					<div class="contents-box">
				
						<form method="post" name="requestForm" onsubmit="return submitForm(this); return false;">
						
							<table class="register">
								<tr>
									<th>업체명</th>
									<td>${company.companyName}</td>
								</tr>
								<tr>
									<th>신청자명</th>
									<td>${company.userName}</td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td>${company.phoneNumber}</td>
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
					<button type="button" class="btn" onclick="approval(${company.companySeq});">승인</button>	
					<button type="button" class="btn" onclick="disapproval(${company.companySeq});">불허</button>	
					<button type="button" class="btn" onclick="document.location.href='/admin/request/request.go';">목록</button>
				</div>
					
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>