<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<link rel="stylesheet" type="text/css" href="/lib/smarteditor/smart_editor2.css" />
<script type="text/javascript" src="/lib/smarteditor/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
	$(document).ready(function() {
 		aside.setActive(4,3);
	});
	
	function submitForm(frm) {
		
		
		if (frm.title.value == "") {
			alert("버전을 입력해주세요.");
			return false;
		}
		
		if (frm.linkUrl.value == "") {
			alert("URL을 입력해주세요.");
			return false;
		}
		
		var param = {
			seq	: frm.seq.value,
			userId	: frm.userId.value,
			notiType : frm.notiType.value,
			sendPush	: frm.sendPush.value,
			title	: frm.title.value,
			linkUrl : frm.linkUrl.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/update/update_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/admin/update/update.go";
				}
			}
		});

		return false;
	}
	
	
	
	
	function deleteNotice(seq) {
		if(confirm("업그레이드 공지를 삭제하시겠습니까?")) {
			var param = {
				seq	:	seq
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/update/update_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/update/update.go";
					}
				}
			});
		}
		return false;

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
				 ■ 홈 > 게시판관리 > 강제 업데이트
			</header>
		
			<div class="contents-block">
			
				<h1>강제 업데이트</h1>
				
				<div class="contents-edit">

					<form method="post" name="noticeForm" id="noticeEdit" onSubmit="return submitForm(this); return false;">
					<input type="hidden" name="seq" value="${notice.noticeSeq}"/>
					<input type="hidden" name="userId" value="${USER_ID}"/>
					<input type="hidden" name="userName" value="${USER_NAME}"/>
					<input type="hidden" name="ir1_text" value="${notice.contentsText}"/>
					<input type="hidden" name="notiType" value="20"/>
					
						<table class="edit">
						<colgroup>
							<col width="100">
							<col width="*">
						</colgroup>
						<tr>
							<th>OS</th>
							<td class="radio">
								<label><input type="radio" name="sendPush" value="21" ${notice.sendPush == 21 ? 'checked=\"checked\"' : ''}>안드로이드</label>
								<label><input type="radio" name="sendPush" value="22" ${notice.sendPush == 22 ? 'checked=\"checked\"' : ''}>IOS</label>
							</td>
						</tr>
						<tr>
							<th>버전</th>
							<td><input type="text" name="title" class="itext" value="${notice.title}"></td>
						</tr>
						<tr>
							<th>URL</th>
							<td><input type="text" name="linkUrl" class="itext" value="${notice.linkUrl}"></td>
						</tr>
						
						</table>
						
					</div>
					<div class="btn-tools">
						<button type="submit" class="btn" style="width:200px;">저장</button>
						<c:if test="${notice.noticeSeq > 0}">
							<button type="button" class="btn" onclick="deleteNotice(${notice.noticeSeq});">삭제</button>
						</c:if>
						<button type="button" class="btn" onclick="document.location.href='/admin/update/update.go';" >목록으로</button>
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
