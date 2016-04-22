<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(1,2);
	searchList(listForm,1);
});

function searchList(listForm,page) {
	var param = {
		keyword		: 	listForm.keyword.value,
		page		:	page
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/member/analyst_list.go",
		dataType:"html",
		data:param,
		success:function(msg){
			$("#contents-list").html(msg);
		}
	});
	return false;
}

function deleteAnalyst(userId) {
	if(confirm("분석가를 삭제하시겠습니까?")) {
		var param = {
				userId	:	userId
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/member/analyst_delete_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.reload();
				} else {
					alert(json.message);
				}
			}
		});
	}
	return false;

}

function popAnalysistAdd(userId) {
	pop.openPage("/admin/member/analyst_edit.go?userId="+userId);
}
/* 저장 */
function formCheck(frm) {
	
	if (frm.id.value == "") {
		alert("ID (이메일)을 입력하세요.");
		return false;
	}
	if (validEmail(frm.id.value) == false) {
		 return false;
	}	
	if (frm.name.value == "") {
		alert("이름을 입력하세요.");
		return false;
	}
	if (frm.pw.value == "") {
		alert("비밀번호를 입력하세요.");
		return false;
	}
	if (frm.pw.value.length < 6) {
		alert("비밀번호는 6자 이상을 입력해야 합니다.");
		return false;
	}

	var param = {
		id :frm.id.value,
		name : frm.name.value,
		pw : frm.pw.value,
	};
	
	
	$.ajax({
		type:"POST",
		url:"/admin/member/analyst_do.go",
		dataType:"json",
		data:param,
		success:function(json) {
			alert(json.message);
			if(json.result) {
				document.location.reload();
			}
		}
	});
}


function validEmail(email) {
	var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;   
	  
	if(regex.test(email) === false) {  
	    alert("잘못된 이메일 형식입니다.");  
	    return false;  
	} else {  
		return true;
	}  
}
 
 
function Check(phone){
	
    var re = /^[0-9]+$/;
    
    if(re.test(phone) == false) {
           alert("비밀번호는 숫자만 가능합니다.");
           return false;
    }else{
    	
    	return true;
    }
	
	
}

//액셀 다운로드
function excelDownload() {
	var frm = listForm;
	document.location.href = "/admin/member/analyst_list_excel.go";
}


</script>

<style>
.device { font-weight: bold; font-size:25px; color:#464242;  }	
.guard { border-bottom: 3px solid #535b60; padding-top:15px; width: 1000px;}
.search-sec .down { float: left; padding-left:680px; text-align: right; }	
</style>


</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 회원관리 > 분석가목록
			</header>
		
			<div class="contents-block">
			
				<h1>분석가 관리</h1>
				
				<div class="contents-main">
	
					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool">
									<input type="text" name="keyword" value="${keyword}" placeholder="아이디 검색" class="itext">
									<button type="submit" class="btn" >검색</button>
								</div>
								<div class="btn-tools">
									<button class="btn" onclick="popAnalysistAdd('');">분석가 등록</button>
									<button class="btn">엑셀 다운로드</button>
								</div>
							</div>
						</div>
	
						<div id="contents-list">
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