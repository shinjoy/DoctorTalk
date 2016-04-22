<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<html>
<head>

<script type="text/javascript">
function onPasswordChange(frm) {
	if (frm.oldPassword.value == "") {
		alert("기존 비밀번호를 입력해주세요.");
		return false;
	}
	if (frm.newPassword.value == "") {
		alert("새 비밀번호를 입력해주세요.");
		return false;
	}
	if (frm.confirmPassword.value == "") {
		alert("새 비밀번호 확인을 입력해주세요.");
		return false;
	}
	if (frm.confirmPassword.value != frm.newPassword.value) {
		alert("비밀번호 확인이 일치하지 않습니다.")
		return false;
	}
	if (frm.isCorrectPw.value < 1) {
		alert("안전한 비밀번호를 입력해주세요.");
		return false;
	}
	
	frm.action = "/admin/user_edit_password_do.go";
	frm.submit();

	return false;
}


 
 
function passwordSafeCheck(frm, obj) {
	var userPw = obj.value;
	var points = userPw.length;
	var safeRate = "";
	var has_letter = new RegExp("[az]");
	var has_caps = new RegExp("[AZ]");
	var has_numbers = new RegExp("[0-9]");
	var has_symbols = new RegExp("\\W");

	if(has_letter.test(userPw)) { points += 3; }
	if(has_caps.test(userPw)) { points += 3; }
	if(has_numbers.test(userPw)) { points += 3; }
	if(has_symbols.test(userPw)) { points += 3; }
	
	
	if( points >= 16 ) {
		safeRate = "STRONG";
		$("#pwCheckResult").css("color","#48f");
		$("#pwCheckResult").html("안전한 비밀번호입니다.");
		frm.isCorrectPw.value = "1";
	} else if( points >= 12 ) {
		safeRate = "MEDIUM";
		$("#pwCheckResult").css("color","#0da");
		$("#pwCheckResult").html("사용할 수 있는 비밀번호입니다.");
		frm.isCorrectPw.value = "1";
	} else if( points >= 9 ) {
		safeRate = "WEEK";
		$("#pwCheckResult").css("color","#cb0");
		$("#pwCheckResult").html("사용할 수 있지만 취약한 비밀번호입니다.");
		frm.isCorrectPw.value = "1";
	} else {
		safeRate = "VERYWEEK";
		$("#pwCheckResult").css("color","#f84");
		$("#pwCheckResult").html("사용할 수 없는 비밀번호입니다.");
		frm.isCorrectPw.value = "0";
	}
	return safeRate;
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
				 ■ 홈 > 비밀번호 변경
			</header>
		
			<div class="contents-block">
			
				<h1>비밀번호 변경</h1>
				
				<div class="contents-main">
				
					<form method="post" name="listForm" id="listForm" onsubmit="return onPasswordChange(this); return false;" style="width:1000px;">
						<input type="hidden" name="isCorrectPw">
						<input type="hidden" name="userId" value="${USER_ID}">
						<div class="tbl-edit" >
							<table class="edit" style="margin:20px;">
							<colgroup>
								<col width="12%">
								<col width="*">
							</colgroup>
							<tr>
								<th>기존 비밀번호</div></th>
								<td><input type="password" name="oldPassword" class="itext"  style="width:140px;"></td>
							</tr>
							<tr>
								<th>새 비밀번호 </div></th>
								<td>
									<input type="password" name="newPassword" class="itext"  style="width:140px;" onkeydown="passwordSafeCheck(this.form, this);">
									<span id="pwCheckResult" class="orange"></span>
								</td>
							</tr>
							<tr>
								<th>새 비밀번호 확인 </div></th>
								<td><input type="password" name="confirmPassword" class="itext"  style="width:140px;"></td>
							
							</tr>
							<tr>
								<td colspan="2">	
									<button type="submit" class="btn" style="margin:5px 5px 5px 70px;">비밀번호 변경</button>
									<button type="button" class="btn" style="margin:5px;" onclick="document.location.href='/admin/member/member.go';" >취소</a></button>
								</td>
							</tr>
							</table>

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