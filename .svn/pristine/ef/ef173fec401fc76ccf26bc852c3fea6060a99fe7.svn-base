<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
 <%@ include file="/WEB-INF/views/common/header.jsp"  %>    
<html>
<head>


<script type="text/javascript">
function formCheck(frm) {

	var chk1=document.loginCheck.use.checked;
	var chk2=document.loginCheck.personal.checked;
	
	if(chk1==""){		
		alert('이용 약관에 동의해 주세요');		
		return false;
	}

	if(chk2==""){
		alert('개인정보 취급방침 약관에 동의해 주세요');		
		return false;
	}
	
	frm.action = "/join.go";
	frm.submit();
	
	return false;
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>약관 동의</title>

<style type="text/css">
.login-content { padding-top:50px; }
.log_form { padding: 10px;}
.login-title { margin-top:70px; margin-left:30px; font-weight:bold; font-size:20px;}
.gray { width:1000px; padding-bottom:20px; border-bottom:1px solid #ddd;}
.blue { border-top:2px solid #0683BB; width:950px; margin-left:30px; margin-top:50px;}
.gray-bottom { border-bottom:1px solid #ddd; width:950px; margin-left:30px; }
</style>

</head>
<body class="">
	
	<div class="login-wrap">
	
		<%@ include file="/WEB-INF/views/home_header.jsp"%> 	
	
			<div class="login-title">약관 동의</div>
		 		
		<div class="gray"></div>
<!-- 			<div> <button>로그인</button>  </div> -->
			
			
			<div class="blue"></div>	
		<div class="login-content">
		
			<div><img src="/images/aray1.png" style="height:50px; margin-left:450px; margin-top:-15px;"></div>

		<table style="margin-left:15px;">
			<tr>	
			<td>
				
			<form name="loginCheck" method="post" class="log_form" onsubmit="return false">
			
				<div class="log-wrap-join">
				
					<table style="padding:10px;">
						<tr>
							<td>&nbsp;</td>
							<td>이용약관</td>
						</tr>
						<tr>
							<td >&nbsp;</td>
							<td style="padding: 0px;">
								<div style="border:1px solid #999; width:950px; height:100px;  overflow-y:scroll; background-color:#fff;"></div>
						
							<P>
							<label style="cursor:pointer;"><input type="checkbox" name="use" style="text-align: center;">이용약관 동의</label>
							</P>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>개인정보 취급방침</td>
						</tr>
						<tr>
							<td >&nbsp;</td>
							<td style="padding: 0px;">
								<div style="border:1px solid #999; width:950px; height:100px; padding:0; overflow-y:scroll; background-color:#fff;"></div>
								
								<P>
								<label style="cursor:pointer;"><input type="checkbox" name="personal" style="text-align: center;">개인정보 취급방침 동의</label>
								</P>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>
								<input type="button" name="go" class="btn" style="width:180px; margin-top:10px; margin-left:430px; text-align: center;" value="다음" onclick="formCheck(this.form)">
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