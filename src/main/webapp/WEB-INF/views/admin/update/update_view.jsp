<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<link rel="stylesheet" type="text/css" href="/lib/smarteditor/smart_editor2.css" />
<script type="text/javascript" src="/lib/smarteditor/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
	$(document).ready(function() {
 		aside.setActive(3,3);
	});
	
	function deleteNotice(seq) {
		if(confirm("게시물을 삭제하시겠습니까?")) {
			var param = {
				seq	:	seq
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/notice/notice_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/notice/notice.go";
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
				 ■ 홈 > 게시판관리 > 강제 업데이트
			</header>
		
			<div class="contents-block">
			
				<h1>강제 업데이트</h1>
				
				<div class="contents-edit">

					<form method="post" name="noticeForm" id="noticeEdit" enctype="multipart/form-data" onSubmit="return submitForm(this); return false;">
					<input type="hidden" name="seq" value="${notice.seq}"/>
					<input type="hidden" name="userId" value="${USER_ID}"/>
					<input type="hidden" name="userName" value="${USER_NAME}"/>
					<input type="hidden" name="ir1_text" value="${notice.contentsText}"/>
					
						<table class="edit">
						<colgroup>
							<col width="100">
							<col width="*">
						</colgroup>
						<tr>
							<th>OS</th>
							<td class="radio">
								<label><input type="radio" name="notiType" value="0" ${notice.notiType == 0 ? 'checked=\"checked\"' : ''}>안드로이드</label>
								<label><input type="radio" name="notiType" value="1" ${notice.notiType == 1 ? 'checked=\"checked\"' : ''}>IOS</label>
							</td>
						</tr>
						<tr>
							<th>버전</th>
							<td>1.5</td>
						</tr>
						</table>
						
					</div>
					<div class="btn-tools">
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
