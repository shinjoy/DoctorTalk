<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="css/smart_editor2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// 상단 메뉴 설정
		$("#menu2").addClass("active");
	});
	
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "ir1",
	    sSkinURI: "/SmartEditor2Skin.html",
	    fCreator: "createSEditor2"
	});

	function onNoticeEdit(frm) {
		oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
		frm.action = "/notice_edit_do.go";
		frm.submit();
		return false;
	}


	function onImageDelete(seq, fileName) {
		if (confirm("파일을 삭제하시겠습니까?\n파일을 삭제하면 수정 화면이 초기화됩니다.")) {
			document.location.href = "/delete_notice_file_do.go?seq="+seq+"&fileName="+fileName;
			/*
			$.ajax({
				type:"POST",
				url:"/delete_notice_file_do.go",
				dataType:"html",
				data:"seq="+seq+"&fileName="+fileName,
				success:function(msg){
					BB_popupLayerEx(popTitle, msg);
				}
			});
			*/
		}
	}
	
</script>

</head>
<body>

<header>
	<div class="wrap">
		<div class="logo">
			봉쥬르봉제
		</div>
		<div class="right-side">
			반갑습니다. 
			<a href="/logout.go" class="bb round green tiny"><span>로그아웃</span></a>
		</div>
	</div>
</header>

<nav>
	<div class="wrap">
		<ul>
			<li onClick="document.location.href='/web/user_manage.jsp';"><span>회원관리</span></li>
			<li onClick="document.location.href='/web/group_manage.jsp';"><span>모임관리</span></li>
			<li class="active" onClick="document.location.href='/web/notice_manage.jsp';"><span>공지사항</span></li>
			<li onClick="document.location.href='/web/statistics_manage.jsp';"><span>통계관리</span></li>
		</ul>
	</div>
</nav>


<article>
	<div class="wrap">

		<div class="write-form">
			
			<!-- 힐링센터 등록/수정 -->
			<form method="post" name="noticeForm" id="noticeEdit" enctype="multipart/form-data" onSubmit="return onNoticeEdit(this); return false;" >
			<input type="hidden" name="noticeSeq" value="${notice.noticeSeq}"/>
			<input type="hidden" name="userId" value="${loginUser.userId}"/>
			<input type="hidden" name="userName" value="${loginUser.userName}"/>
				<div class="inputForm" style="width:100%;" >
					<dl>
						<dt style="margin:0 0 3px 0;">제목 : </dt>
						<dd style="width:850px;"><input type="text" name="noticeTitle" class="bb" style="width:590px;" value="${notice.noticeTitle}"></dd>
						<dt style="margin:5px 0 3px 0;">내용 : </dt>
						<dd style="width:850px;">
							<textarea name="ir1" id="ir1" rows="10" cols="100" style="width:610px; height:412px; display:none;">${notice.noticeContents}</textarea>
						</dd>
					</dl>
					<div class="clear"></div>
				</div>
				<div class="toolbox">
					<button type="submit" class="bb round big orange" style="margin:5px; width:200px;" >저장</button>
					<button type="button" class="bb round big gray" style="margin:5px;" onclick="history.back(-1);">취소</button>
				</div>
			</form>
							
		</div>
	</div>
</article>

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
