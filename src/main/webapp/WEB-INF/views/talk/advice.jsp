<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script>


<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(3,2);
	searchList(listForm,1);
});


function searchList(listForm,page) {
	var param = {
		page		:	page
		
	};
	
	$.ajax({
		type:"POST",
		url:"/talk/advice_list.go",
		dataType:"html",
		data:param,
		success:function(msg){
			$("#contents-list").html(msg);
		}
	});
	return false;
}


function searchArea(frm) {
	
	if(frm.areaSido.value == "0"){
		$('areaSido').find('option:first').attr('selected', 'selected');
	}
	
	return false;
}

</script>

<style>
table.menuedit th {
   vertical-align: top;
   text-align :left;
   padding:5px;
   font-weight: bold;
}

table.menuedit td {
	padding:5px;
	text-align :left;
    vertical-align: top;
    padding: 3px;
    border-bottom: 1px solid #ddd;
}	

</style>


</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/talk/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 토크관리 > 토크목록
			</header>
		
			<div class="contents-block">
			
				<h1>토크목록</h1>
				
<!-- 				<div> -->

<!-- 					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;"> -->
<!-- 						<div class="contents-top" style="width:50%; float:left;"> -->
<!-- 						</div> -->

<!-- 						<div id="contents-list" style="width:50%; float:left;"> -->
<!-- 						</div> -->
						
							
					
<!-- 					</form> -->
					
<!-- 				</div> -->
				
				<div class="contents-main" style="float:left; width:40%;">

					<div class="contents-box">
				
						<form method="post" name="firmForm" onsubmit="return submitForm(this); return false;">
						<input type="hidden" name="userId" value="${user.userId}">
							<table class="register">
								<tr>
									<td colspan="8">
										<img src="/images/1305920150807132159.jpg" style="height:100px;">	
<%-- 										<img src="/files/company/thumb/${it.photo1}" style="height:100px;"> --%>
									</td>
								</tr>
								<tr>									
									<td>홍길동</td>		
								</tr>
								
								<tr>
									<td>${user.genderText}|${user.userAge}|${user.area}</td>
								</tr>
								<tr>
									<td>휴대폰 번호: 010-0000-0000</td>
								</tr>
								<tr>
									<td>F-MONEY : ${user.income}</td>
								</tr>
								<tr>
		
									<td>최근 접속 : ${it.lastLogindate.substring(0,10)}&nbsp;&nbsp;&nbsp;등록일자 : ${it.regDate.substring(0,10)}</td>
								</tr>
								<tr>
					
									<td> 소개 글 : 하느님이 보우하사 우리나라만세</td>
								</tr>
							</table>
						</form>
					</div>
					
					
					<div style="margin-top:30px;">	
						
						<table class="list">
							
							<thead>
								<th colspan="3">상담이력</th>						
							</thead>
							
							<thead>
								<th>상담일자</th>
								<th>상담사</th>
								<th>진행시간</th>
							</thead>
						
						
							<tbody class="rl">
							<c:choose>
								<c:when test="${list.size() > 0}">
									<c:forEach var="it" items="${list}">
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</c:forEach>
								</c:when>
								<c:otherwise>
								<tr>
									<td colspan="7" style="height:200px; text-align:center; margin-top:50px;">조회된 데이터가 없습니다.</td>
								</tr>
								</c:otherwise>
							</c:choose>
							</tbody>
						</table>
					</div>		
					${paging}
				</div>	
				
				<div style="float:left; margin-left:50px; width:55%; height:600px; border:1px solid #ddd; ">
					
					<div>
						<table class="list" style="border-top: 0 !important;">
							<thead>
								<th>
									<select name="age" id="age" style="width:106px;" class="select-search">
										<option value="0">=대분류=</option>
										<option value="1">10대</option>
										<option value="2">20대</option>	
										<option value="3">30대</option>
										<option value="4">40대</option>	
										<option value="5">50대이상</option>	
									</select>
								</th>
								<th>
									<select name="age" id="age" style="width:106px;" class="select-search">
										<option value="0">=중분류=</option>
										<option value="1">10대</option>
										<option value="2">20대</option>	
										<option value="3">30대</option>
										<option value="4">40대</option>	
										<option value="5">50대이상</option>	
									</select>
								</th>
								<th>스크립트</th>
								<th>참고자료</th>
								<th>저장</th>
								<th>상담종료</th>								
							</thead>
							
						</table>
					</div>
					
					<div style="clear:both;"></div>
					
					
					<div style="width:10px; background-color:#fff;">
					
					
					
					
					</div>
					
					
				</div>

			</div>

		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>