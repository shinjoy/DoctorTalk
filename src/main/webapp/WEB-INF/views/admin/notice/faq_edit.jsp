<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	$(document).ready(function() {
 		aside.setActive(2,4);
	});

	$(function() {
		$.datepicker.setDefaults( $.datepicker.regional[ "ko" ] );
		$( ".datepicker" ).datepicker(
			{
			showButtonPanel: true
			}
		);
	});	

	
	function submitForm(frm) {

		if (frm.title.value == "") {
			alert("제목을 입력해주세요.");
			return false;
		}
		
		if (frm.cateKind[0].checked == false && frm.cateKind[1].checked == false && frm.cateKind[2].checked == false && frm.cateKind[3].checked == false && frm.cateKind[4].checked == false) {
		    alert("카테고리를 선택해주세요.");
		    return false;
		}
		
		var param = {
			qnaSeq	: frm.qnaSeq.value,
			userId	: frm.userId.value,
			cateKind	: frm.cateKind.value,
			title	: frm.title.value,
			ir1	: frm.ir1.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/notice/faq_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/admin/notice/faq.go";
				}
			}
		});

		
		//frm.action = "/admin/notice/notice_edit_do.go";
		//frm.submit();
		return false;
	}
	
	function deleteNotice(qnaSeq) {
		if(confirm("도움말을 삭제하시겠습니까?")) {
			var param = {
					qnaSeq	:	qnaSeq
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/notice/faq_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/notice/faq.go";
					}
				}
			});
		}
		return false;

	}
	
</script>

</head>
<style>
.device { font-weight: bold; font-size:25px; color:#464242; padding-bottom:30px;}	
</style>

<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 서비스관리 > QNA 관리
 			</header>
		
			<div class="contents-block">
			
				<h1>QNA 관리</h1>
				
				<div class="contents-edit">

					<form method="post" name="noticeForm" id="noticeEdit" onSubmit="return submitForm(this); return false;">
					<input type="hidden" name="qnaSeq" value="${qna.qnaSeq}"/>
					<input type="hidden" name="userId" value="${USER_ID}"/>
					<input type="hidden" name="userName" value="${USER_NAME}"/>
					<input type="hidden" name="ir1_text" value="${qna.contentsText}"/>
						<table class="edit">
						<colgroup>
							<col width="100">
							<col width="*">
						</colgroup>
						<tr>
							<th>제목</th>
							<td><input type="text" name="title" class="itext" style="width:590px;" value="${qna.title}"></td>
						</tr>
<!-- 						<tr> -->
<!-- 							<th>게시기간</th> -->
<!-- 							<td> -->
<%-- 								<input type="text" name="startDate" class="itext datepicker" style="width:150px;" value="${notice.startDate.length() >= 10 ? notice.startDate.substring(0,10) : notice.startDate}"> ~ --%>
<%-- 								<input type="text" name="endDate" class="itext datepicker" style="width:150px;" value="${notice.endDate.length() >= 10 ? notice.endDate.substring(0,10) : notice.endDate}"> --%>
<!-- 							</td> -->
<!-- 						</tr> -->
						<tr>
							<th>카테고리</th>
							<td class="radio">
								<label><input type="radio" name="cateKind" value="1" ${qna.cateKind == 1 ? 'checked=\"checked\"' : ''}>혈압</label>
								<label><input type="radio" name="cateKind" value="2" ${qna.cateKind == 2 ? 'checked=\"checked\"' : ''}>혈당</label>
								<label><input type="radio" name="cateKind" value="3" ${qna.cateKind == 3 ? 'checked=\"checked\"' : ''}>콜레스테롤/당화혈색소</label>
								<label><input type="radio" name="cateKind" value="4" ${qna.cateKind == 4 ? 'checked=\"checked\"' : ''}>복약관리</label>
								<label><input type="radio" name="cateKind" value="5" ${qna.cateKind == 5 ? 'checked=\"checked\"' : ''}>문진정보</label>
							</td>
						</tr>
						<tr>
							<th style="vertical-align: top;">내용</th>
							<td><textarea name="ir1" id="ir1" style=" width:600px; height:500px;">${qna.contentsHtml}</textarea></td>
						</tr>
						</table>
						
					</div>
					<div class="btn-tools">
						<button type="submit" class="btn-blue" style="width:200px;">저장</button>
						<c:if test="${qna.qnaSeq > 0}">
							<button type="button" class="btn-red" onclick="deleteNotice(${qna.qnaSeq});">삭제</button>
						</c:if>
						<button type="button" class="btn" onclick="document.location.href='/admin/notice/faq.go';" >목록으로</button>
					</div>
					</form>
				</div>
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>
