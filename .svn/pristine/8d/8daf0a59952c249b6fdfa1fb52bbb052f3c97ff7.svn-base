<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>


	

<script type="text/javascript">


	$(document).ready(function() {
		aside.setActive(9,4);
		/* 폼 ajax전송 : http://malsup.com/jquery/form/#ajaxForm */
		var options = {
			//target :		'#user-join-result',
			beforeSubmit :	formCheck,
			success :		formSuccess
		};
		$('#magazinesubEdit').ajaxForm(options);
	});

	function formCheck(formData, jqForm, options) {
		var frm = magazinesubEdit;
		if (frm.pageTitle.value == "") {
			alert("제목을 입력해주세요.");
			return false;
		}
		if (frm.pageContents.value == "") {
			alert("내용을 입력해주세요.");
			return false;
		}
		
		return true; 
	}
	function formSuccess(responseText, statusText, xhr, $form) {
		//alert('status: ' + statusText + '\n\nresponseText: \n' + responseText );
		var json = JSON.parse(responseText);
		try {
			alert(json.message);
			if (json.result) {
				document.location.href = "/admin/contents/contents_edit.go?mSeq="+json.mSeq;
			}
		} catch (e) {
            alert(json.message); 
		}
	}	
	
	function deleteFile(pSeq, fileName) {
		if(confirm("이미지파일을 삭제하시겠습니까?")) {
			var param = {
				pSeq	:	pSeq,
				fileName	: fileName
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/contents/subcontents_file_delete.go",
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

</head>
<style>
.device { font-weight: bold; font-size:25px; color:#464242; padding-bottom:30px;}	

.title { display:table; width: 100%; border-collapse: collapse; margin: 0; padding: 0; border: 0; border-spacing:0;}
.subitem { display:table-cell; display:inline-block;  font-weight:bold; padding:4px; border-left: 1px solid #ddd; text-align:center;}
.datasell {display:table-cell;  width:100%; height:200px; text-align:center; border-top:1px solid #ddd; border-bottom:1px solid #ddd; vertical-align:middle; }

</style>

<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 컨텐츠 관리 > 건강매거진 관리
			</header>
		
			<div class="contents-block">
			
				<h1>건강매거진 관리</h1>
				
				<div class="contents-edit">

					<form method="post" name="magazinesubForm" id="magazinesubEdit" action="/admin/contents/subcontents_edit_do.go" enctype="multipart/form-data">
					<input type="hidden" name="pSeq" value="${mp.pSeq}"/>
					<input type="hidden" name="mSeq" value="${mSeq}"/>
					<input type="hidden" name="userId" value="${USER_ID}"/>
					<input type="hidden" name="userName" value="${USER_NAME}"/>
						<table class="edit">
						<colgroup>
							<col width="100">
							<col width="*">
						</colgroup>
						<tr>
							<th>제목</th>
							<td><input type="text" name="pageTitle" class="itext" style="width:370px;" value="${mp.pageTitle}"></td>
							
							<td rowspan="5">내용<br><textarea name="pageContents" style="width:370px;height:200px; padding:5px;">${mp.pageContents}</textarea></td>
						</tr>
						<tr>
							<th>페이지번호</th>
							<td><input type="hidden" name="page" class="itext" style="width:370px;" value="${page}">${page}</td>
						</tr>
						<tr>
							<th>이미지</th>
							<td>
								<c:choose>
									<c:when test="${mp.pageFilename == '' || mp.pageFilename == null}">
										<input type="file" name="pageFilename">
									</c:when>
									<c:otherwise>
										<img src="/files/submagazine/${mp.pageFilename}" style="max-height:100px">
										<button type="button" class="btn" onclick="deleteFile(${mp.pSeq},'${mp.pageFilename}')">이미지 삭제</button>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						</table>
						
					</div>
					<div class="btn-tools">
						<button type="submit" class="btn" style="width:200px;">저장</button>
						
						<button type="button" class="btn" onclick="document.location.href='/admin/contents/contents_edit.go?mSeq=${mSeq}';" >목록으로</button>
					</div>
					</form>
									
				</div>
			
			</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>
