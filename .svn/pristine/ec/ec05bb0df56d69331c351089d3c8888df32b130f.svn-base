<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(2,1);
	});
	
	function submitForm(frm) {
			
		var param = {
			bbsSeq	: frm.bbsSeq.value,
			reportCount :frm.reportCount.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/talk/talk_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/admin/talk/talk.go";
				}
			}
		});

		return false;
	}
	
	function blindFormDeal(frm) {
		
		var param = {
			bbsSeq	: frm.bbsSeq.value,
			blindCount :frm.blindCount.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/talk/talk_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/admin/talk/talk.go";
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
	padding:2px;
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
				 ■ 홈 > 토크관리 > 토크 상세보기
			</header>
		
			<div class="contents-block">
			
				<h1>토크 상세보기</h1>
				
				<div class="contents-main">

					<div class="contents-box">
				
						<form method="post" name="firmForm" onsubmit="return submitForm(this); return false;">
						<input type="hidden" name="bbsSeq" value="${bbs.bbsSeq}">
							<table class="register">
								<tr>
									<td rowspan="6">
										<img src="/images/1305920150807132159.jpg" style="height:100px;">	
<%-- 										<img src="/files/thumb/${it.photo1}" style="height:100px;"> --%>
									</td>
									<td>
										${bbs.userId}
										<br>
										등록일자 : ${bbs.regDate.substring(0,10)}
										<br>
										♥좋아요 : ${bbs.likeCount} | ♠댓글 : ${bbs.commentCount} | ♣신고 : ${bbs.reportCount} 
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td></td>
								</tr>
<!-- 								<tr> -->
<%-- 									<td>포인트 : ${user.point}</td> --%>
<!-- 								</tr> -->
<!-- 								<tr> -->
<%-- 									<td>F-MONEY : ${user.income}</td> --%>
<!-- 								</tr> -->
								<tr>
									<td>하느님이 보우하사 우리나라만세</td>
								</tr>
							</table>
						</form>
					</div>

				</div>
				<br><br>
				<div class="btn-tools" style="width:300px; margin:0 auto;" >	
					<form method="post" name="reportForm" onsubmit="return false;" style="float:left; margin-right:10px;">
						<input type="hidden" name="bbsSeq" value="${bbs.bbsSeq}">
						<input type="hidden" name="reportCount" value="0">
						<button type="button" class="btn" onclick="submitForm(this.form);">신고 해제 처리</button>
					</form>
					<form method="post" name="blindForm" onsubmit="return false;" style="float:left; margin-right:-20px;">
						<input type="hidden" name="bbsSeq" value="${bbs.bbsSeq}">
						<input type="hidden" name="blindCount" value="1">
						<button type="button" class="btn" onclick="blindFormDeal(this.form);"> 블라인드 처리</button>
						
					</form>
					<button type="button" class="btn" onclick="document.location.href='/admin/user/user_edit.go?userId=${user.userId}';">주제</button>
					<button type="button" class="btn"  onclick="document.location.href='/admin/talk/talk.go';">목록</button>
				</div>
				
				<div class="tab-bar">
					<button type="button" class="tab" onclick="document.location.href='/admin/talk/talk_view.go?bbsSeq?${bbs.bbsSeq}';"><span>댓글</span></button>
				</div>				
				
				<div class="tbl-list">
					<table class="request" style="width:100%">
						<tbody class="rl" style="width:100%">
						<c:choose>
							<c:when test="${list.size() > 0}">
								<c:forEach var="it" items="${list}">
									<tr>
										<td rowspan="3">
										<img src="/images/1305920150807132159.jpg" style="height:70px;">	
				<%-- 								<img src="/files/company/thumb/${it.photo1}" style="height:100px;"> --%>
										</td>
										<td style="text-align:left; padding-left:10px;">
											${it.guestId}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;등록일자:${it.regDate.substring(0,16)} 
										</td>
									</tr>
									<tr>
										<td style="text-align:left; padding-left:10px;">
										${it.contents}
										</td>
									</tr>				
<!-- 									<tr> -->
<!-- 										<td style="text-align:left; padding-left:10px;"> -->
<!-- 										 하느님이 보우하사 우리나라만세 -->
<!-- 										</td> -->
<!-- 									</tr>	 -->
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr><td colspan="7" style="height:200px; text-align: center;">조회된 데이터가 없습니다.</td></tr>
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