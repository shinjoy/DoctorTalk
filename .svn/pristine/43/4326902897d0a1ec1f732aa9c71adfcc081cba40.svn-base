<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<link rel="stylesheet" type="text/css" href="/lib/smarteditor/smart_editor2.css" />
<script type="text/javascript" src="/lib/smarteditor/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
	$(document).ready(function() {
		aside.setActive(3,1);
	});

	$(function() {
		$.datepicker.setDefaults( $.datepicker.regional[ "ko" ] );
		$( ".datepicker" ).datepicker(
			{
			showButtonPanel: true
			}
		);
	});	

	var oEditors = [];

	
	function submitForm(frm) {
		
		var notiType = frm.notiType.value;
		for(var i=0; i<notiType.length; i++) // 일반적인 배열의 루프이지요?
		{
		    if(notiType[i].checked == true) { // 하나만 체크되면 OK이므로
		    	notiType = 1;
		    }
		}
		if (notiType == 0) {
		    alert("유형을 선택해주세요.");
		    return false;
		} 
		
		
		if (frm.title.value == "") {
			alert("제목을 입력해주세요.");
			return false;
		}
		
		oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
		getPlainText(frm);
		
		var param = {
			seq	: frm.seq.value,
			userId	: frm.userId.value,
			notiType	: frm.notiType.value,
			title	: frm.title.value,
			ir1	: frm.ir1.value,
			ir1_text	: frm.ir1_text.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/fnq/fnq_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/admin/fnq/fnq.go";
				}
			}
		});

		
		//frm.action = "/admin/notice/notice_edit_do.go";
		//frm.submit();
		return false;
	}
	// html 태그제거
	function fRemoveHtmlTag(string) { 
		string = string.replace("<br>","\r\n");
		var objReplace = new RegExp(); 
		objReplace = /[<][^>]*[>]/gi; 
		return string.replace(objReplace, ""); 
	}
	function getPlainText(frm) { 
		var html = oEditors.getById["ir1"].getIR(); 
		frm.ir1_text.value=fRemoveHtmlTag(html);
	}
	
	function deleteNotice(seq) {
		if(confirm("게시물을 삭제하시겠습니까?")) {
			var param = {
				seq	:	seq
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/fnq/fnq_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/fnq/fnq.go";
					}
				}
			});
		}
		return false;

	}
	
</script>

</head>


<body class="admin">

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 공지사항 > 공지사항 등록
			</header>
		
			<div class="contents-block">
			
				<h1>공지사항 등록</h1>
				
				<div class="contents-edit">
					
					<form method="post" name="noticeForm" id="noticeEdit" enctype="multipart/form-data" onSubmit="return submitForm(this); return false;" style="width:1000px;">
					<input type="hidden" name="seq" value="${fnq.seq}"/>
					<input type="hidden" name="userId" value="${USER_ID}"/>
					<input type="hidden" name="userName" value="${USER_NAME}"/>
					<input type="hidden" name="ir1_text" value="${fnq.questionText}"/>
					
						<table class="edit">
						<colgroup>
							<col width="100">
							<col width="*">
						</colgroup>
						<tr>
							<th>제목</th>
							<td>
								<div class="entitle">
									<input type="text" name="title" class="itext"  value="${fnq.title}" style="width:99%;">
								</div>
							</td>
						</tr>
						<tr>
							<th>유형</th>
							<td>
								<div class="title-content radio">
									<label><input type="radio" name="notiType" value="0" ${notice.notiType == 0 ? 'checked=\"checked\"' : ''}>일반공지</label>
									<label><input type="radio" name="notiType" value="1" ${notice.notiType == 1 ? 'checked=\"checked\"' : ''}>특별공지</label>
								</div>
							</td>
						</tr>
						<tr>
							<th>게시</th>
							<td>
								<div class="title-content radio">
									<label><input type="radio" name="visible" value="1" ${notice.visible == 1 ? 'checked=\"checked\"' : ''}>게시</label> 
									<label><input type="radio" name="visible" value="0" ${notice.visible == 0 ? 'checked=\"checked\"' : ''}>숨김</label>
								</div>	
							</td>
						</tr>
						<tr>
							<th style="vertical-align:top;"> 내용</th>
							<td><textarea name="ir1" id="ir1" style="display:none;">${fnq.questionHtml}</textarea></td>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<td>
								<div class="btnC">
									<button type="submit" class="btn green" >저장</button>
									<button type="button" class="btn red" onclick="deleteNotice(${fnq.seq});">삭제</button>
									<button type="button" class="btn herb" onclick="document.location.href='/admin/fnq/fnq.go';" >목록으로</button>
								</div>
							</td>
						</tr>
						</table>
							
					</form>
				</div>
			</div>
		</section>

	</section>
</section>
<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

<script type="text/javascript">

// 추가 글꼴 목록
//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];

nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "ir1",
	sSkinURI: "/SmartEditor2Skin.html",	
	htParams : {
		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
		fOnBeforeUnload : function(){
			//alert("완료!");
		}
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
});


function pasteHTML() {
	var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
	oEditors.getById["ir1"].exec("PASTE_HTML", [sHTML]);
}

function showHTML() {
	var sHTML = oEditors.getById["ir1"].getIR();
	alert(sHTML);
}

function submitContents(elClickedObj) {
	oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
	
	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
	try {
		elClickedObj.form.submit();
	} catch(e) {}
}

function setDefaultFont() {
	var sDefaultFont = '궁서';
	var nFontSize = 24;
	oEditors.getById["ir1"].setDefaultFont(sDefaultFont, nFontSize);
}


</script>

</body>
</html>
