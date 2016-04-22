<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
function noticeCheck(){
	
	
	var frm = pageForm;	
	var userType = frm.userType.value;
	
	
	if (userType == 0 || userType == "" || userType == undefined) {
    	alert("회원전용 메뉴입니다.");
	} else if(userType == 1 || userType == 2 || userType == 3 ) {
    	document.location.href = "/user/assay/assay.go";
	}
	return false;

}
</script>

<style>

.home { background-color:#0782BD; height:50px; width:100%;}

.login {
	width: 1000px;		
	float: left;
	text-align:left;		
	margin-top: 15px;
	margin-bottom: 5px;
	margin-right: 50px;
	height:15px;
}


.login LI{		
	float: left;
	margin-right:30px;	
}

.menu { 
	width:100%; 
	background-color:#20BBD9; 
	height:50px; 		
	float: left;
	text-align:left;		
	margin-bottom: 5px;
	margin-right: 50px;
}

.menu LI { float: left; margin-right:30px; color:#fff; margin-top:15px;}

</style>


<form method="post" name="pageForm">
<input type="hidden" name="userId" value="${USER_ID}">
<input type="hidden" name="userName" value="${USER_NAME}">
<input type="hidden" name="userType" value="${USER_TYPE}">
</form>
		<header class="home">
			<div class="login">
		
				<c:choose>
					<c:when test="${USER_ID=='' || USER_ID==null}">
						<ul>
							<li><a href="/user/introduction/introduction.go" style="color:#fff; margin-left:30px;"> Qraycam</a></li>
				 			<li><a style="color: #fff;"> BiteView</a></li>	
						 	<li><a style="color:#fff; margin-right:550px;"> All in ONE BIO</a></li>
							<li><a href="/login.go" style="color:#fff;">로그인</a></li>	
							<li><a href="/access.go" style="color:#fff;">회원가입</a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul>
							<li><a href="/user/introduction/introduction.go" style="color:#fff; margin-left:30px;"> Qraycam</a></li>
				 			<li><a style="color: #fff;"> BiteView</a></li>	
						 	<li><a style="color:#fff; margin-right:400px;"> All in ONE BIO</a></li>
						</ul>
						<c:choose>
							<c:when test="${USER_TYPE==1}">
								<P style="color:#fff;"> 
								${USER_NAME}님 환영합니다.
								<button class="btn" style="padding: 0px 5px; color:#fff;" onclick="document.location.href='/admin/user/ws.go';">관리자페이지</button>
								<button class="btn" style="padding:0px 5px; color:#fff; " onclick="document.location.href='/logout_do.go';">로그아웃</button>
								</P>
							</c:when>
							<c:when test="${USER_TYPE==2}">
								<P style="color:#fff;">
								${USER_NAME}님 환영합니다.
								<button class="btn" style="padding: 0px 5px; color:#fff;" onclick="document.location.href='/tirebank/order.go';">분석가</button>
								<button class="btn" style="padding:0px 5px; color:#fff; " onclick="document.location.href='/logout_do.go';">로그아웃</button>
								</P>
							</c:when>
							<c:otherwise>
							
								<P style="color:#fff;">
								${USER_NAME}님 환영합니다.
								<button class="btn" style="padding: 0px 5px; color:#fff;" onclick="document.location.href='/user/user_edit_password.go';">비밀번호변경</button>
								<button class="btn" style="padding:0px 5px; color:#fff; " onclick="document.location.href='/logout_do.go';">로그아웃</button></P>								
							</c:otherwise>
						</c:choose>

					</c:otherwise>
				</c:choose>
			</div>
			
		</header>
		
		<div class="menu">
		
		 	<a href="/home/home.go"> <img alt="" src="/images/aray2.png"  style="width:70px; float:left; margin-right:50px; margin-left:30px; margin-top: 10px;"> </a>
		
			<ul id="user-menu">
				<li id="user-menu1"><a href="/user/introduction/introduction.go" style="color: #fff;">서비스소개</a></li>
				<li id="user-menu2"><a href="/user/manual/manual.go" style="color: #fff;">매뉴얼</a></li>
				<li id="user-menu3"><a onclick="noticeCheck()" style="color:#fff; cursor:pointer; color: #fff;">분석내역</a></li>
				<li id="user-menu4"><a href="/user/fnq/fnq.go" style="color: #fff;">자주묻는질문</a></li>
				<li id="user-menu5"><a href="/user/notice/notice.go" style="color: #fff;">공지사항</a></li>
			</ul>
		
		</div>

		<div class="clear"></div>