<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
 <%@ include file="/WEB-INF/views/common/header.jsp"  %>    
<html>
<head>


<script type="text/javascript">
function formCheck(frm) {
	
	var userId = frm.userId.value;
	var userPw = frm.userPw.value;
	var userPwConfirm = frm.userPwConfirm.value;
	var userName = frm.userName.value;
	var age = frm.age.value;
	var gender = frm.gender.value;
	


	if (frm.userId.value == "") {
		alert("ID (이메일)을 입력하세요.");
		return false;
	}
	if (validEmail(frm.userId.value) == false) {
		 return false;
	}
	if (frm.userPw.value == "") {
		alert("비밀번호를 입력하세요.");
		return false;
	}
	if (frm.userPw.value.length < 6) {
		alert("비밀번호는 6자 이상을 입력해야 합니다.");
		return false;
	}
	if (frm.userPwConfirm.value == "") {
		alert("비밀번호 확인을 입력하세요.");
		return false;
	}
	if (frm.userPw.value != frm.userPwConfirm.value) {
		alert("비밀번호와 확인이 일치하지 않습니다.");
		return false;
	}
	if (frm.userName.value == "") {
		alert("이름을 입력하세요.");
		return false;
	}
	if (frm.age.value == "") {
		alert("나이를 입력하세요.");
		return false;
	}
	

	for(var i=0; i<gender.length; i++) 
	{
	    if(gender[i].checked == true) {
	        gender = 1;
	    }
	}
	if (gender == 0) {
	    alert("성별을 선택해 주세요.");
	    
	    return false;
	}


	var param = {

		userId : userId,
		userPw : userPw,
		userName    : userName,
		age : age,
		gender : gender
		
		
	
	};

	$.ajax({
			type:"POST",
			url:"/join_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/login.go";
				}
			}
		});

	
	return false;
}

/**
 * 이메일만 입력가능합니다.
 * @param val
 * @returns {Boolean}
 */
function validEmail(email) {
	var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;   
	  
	if(regex.test(email) === false) {  
	    alert("잘못된 이메일 형식입니다.");  
	    return false;  
	} else {  
		return true;
	}  
}
 
 
function ageCheck(age){
	
    var re = /^[0-9]+$/;
    
    if(re.test(age) == false) {
           alert("나이는 숫자만 가능합니다.");
           return false;
    }else{
    	
    	return true;
    }
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



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회사소개</title>

<style type="text/css">
.login-content { padding-top:50px;}
.log_form { padding: 10px;}
.login-title { margin-top:70px; margin-left:30px; font-weight:bold; font-size:20px;}
.gray { width:1000px; padding-bottom:20px; border-bottom:1px solid #ddd;}
.blue { border-top:2px solid #0683BB; width:950px; margin-left:30px; margin-top:50px;}
.gray-bottom { border-bottom:1px solid #ddd; width:950px; margin-left:30px; }
.fbox { 
	font-size:12px; 
	color: #000; 
	line-height:18px; 
	height:13px; 
	padding: 4px 2px;  
	width:65px; 
	border:1px solid #ddd; 
/* 	border-radius:13px;  */
/* 	background-color: #464242;  */
	text-align: left; 
	line-height: 12px;
	
}
</style>

</head>
<body>
	
	<div class="login-wrap">
	
			<%@ include file="/WEB-INF/views/home_header.jsp"%> 	
			
				<div class="login-title">회원 가입</div>
				
						<div class="gray"></div>
<!-- 			<div> <button>로그인</button>  </div> -->
			
			
			<div class="blue"></div>
				

		<div class="login-content">
		
			<div><img src="/images/aray1.png" style="height:50px; margin-left:400px; margin-top:-15px; margin-bottom:20px;"></div>
				
		<table style="margin-left:300px;">
			<tr>
			<td>
			
			<form name="loginCheck" method="post" class="log_form" onsubmit="return false">
	
				<div class="log-wrap-join">
					<table>
	
						<tr>
							<th>아이디</th>
							<td style="padding:10px;">
								<input type="text" name="userId" class="itext" style="width: 180px;" placeholder="아이디를 입력하세요" >
							</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td style="padding: 10px;">
								<input type="password" name="userPw" class="itext" style="width: 180px;" placeholder="비밀번호를 입력하세요"  onkeydown="passwordSafeCheck(this.form, this);">
								<span id="pwCheckResult" class="orange"></span>
							</td>
						</tr>
						
						<tr>
							<th>비밀번호 확인</th>
							<td style="padding: 10px;">
								<input type="password" name="userPwConfirm" class="itext" style="width: 180px;" placeholder="비밀번호를 확인하세요">
							</td>
						</tr>
						
						<tr>
							<th>이름</th>
							<td style="padding: 10px;">
								<input type="text" name="userName" class="itext" style="width: 180px;" placeholder="이름을 입력하세요">
							</td>
						</tr>
						
						<tr>
							<th>나이</th>
							<td style="padding: 10px;">
								<input type="text" name="age" class="itext" style="width: 180px;" placeholder="나이를 입력하세요"> (태어난 년도를 입력해주세요. ex)1992)
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<P style="padding:10px 10px 20px 10px;">
									<label style="cursor:pointer;" >
									<input type="checkbox" name="use" style="text-align: center;">메일링 가입 (메일링 가입이 체크되지 않으면 단체메일 발송시 메일을 받지 않습니다.)</label>
								</P>
							</td>
						</tr>
						<tr>
							<th>성별</th>
							<td>
								<label><input type="radio" name="gender" value="1" ${notice.gender == 1 ? 'checked=\"checked\"' : ''}>남자</label>
								<label><input type="radio" name="gender" value="2" ${notice.gender == 2 ? 'checked=\"checked\"' : ''}>여자</label>
							</td>
						</tr>
						
						
						<tr>
							<td>&nbsp;</td>
							<td>
								<input type="button" name="go" class="btn" style="width:180px; margin-left:10px; text-align: center; margin-top:30px;" value="회원가입" onclick="formCheck(this.form)">
							</td>
						</tr>
	
						
					</table>
				</div>
				
				
				
			</form>
			</td>
			</tr>
			</table>
		</div>
			
					<div class="gray-bottom"></div>	
			
			<div style="padding-bottom:30px;"></div>
		
			<div class="clear"></div>
		
	<%@ include file="/WEB-INF/views/home_footer.jsp"%> 	

	</div>
</body>
</html>