<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(1,2);
});

function userDelete(userId) {
	if ( confirm("회원 계정을 완전히 삭제하시겠습니까?")) {

		var param = {
			userId	:	userId
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/user/admin_delete.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.msg);
				document.location.href = "/admin/user/admin.go";
			}
		});
	}
}

</script>

</head>
<body>
 
<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %> 

<section class="main-cover main-row">
	<section id="main">
		
		 <%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %> 

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 회원관리 > 상담원 관리
			</header>
		
			<div class="contents-block">
			
				<h1>상담원</h1>
				
				<div class="contents-main">

	
					<table class="register" style="width:100%;">
								<colgroup>
									<col width="100">
									<col width="*">
								</colgroup>
								<tr>
									<th>아이디</th>
									<td>${user.userId }</td>
									
								</tr>
								<tr>
									<th>이름</th>
									<td>${user.userName }</td>
								</tr>
								<tr>
									<th>생년월일</th>
									<td>${user.birthday }</td>
								</tr>
								<tr>
									<th>성별</th>
									<td>${user.genderText }</td>
								</tr>
								<tr>
									<th>소속</th>
									<td>${user.belongTo }</td>
								</tr>
								<tr>
									<th>가입일</th>
									<td>${fn:substring(user.regDate,0,10)}</td>
								</tr>
								<tr>
									<th>최근접속일</th>
									<td>${fn:substring(user.lastLogindate,0,10)}</td>
								</tr>
					</table>
						
				
			
				</div>
				
				<div class="btn-tools">
					<button type="button" class="btn-blue" onclick="location.href='/admin/user/admin_edit.go?userId=${user.userId}'">수정</button>
					<button type="button" class="btn-red" onclick="userDelete('${user.userId}');">삭제</button>
					<button type="button" class="btn" onclick="location.href = '/admin/user/admin.go'">목록</button>	
				</div>

			</div>
			
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>


	
		
	
	
