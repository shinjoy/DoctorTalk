<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	$(document).ready(function() {
		frm = loginForm;
	});
	
	function loginFormCheck(frm) {
		var loginId = frm.loginId.value;
		var loginPw = frm.loginPw.value;

		if (loginId == "") {
			alert("사용자 ID를 입력해주세요.");
			return false;
		}
		if (loginPw == "") {
			alert("비밀번호를 입력해주세요.");
			return false;
		}

		var param = {
			loginId : loginId,
			loginPw : loginPw
		};
		
		$.ajax({
		    type:"POST",
		    url:"/login_do.go",
		    dataType:"json",
		    data:param,
		    success:function(json) {
		        if (json.result) {
		        	document.location.href = "/admin/user/user.go";	
		        } else {
		        	alert(json.message);
		        }
		    },
		    error:function(xhr, status, error) {
		        alert(error);
		    },
		    complete:function(data) {
		    }
		});
		return false;
	}

	
</script>

<style>
	.login-content { width:400px; margin:50px auto 0;}
</style>

</head>
<body>

<section class="main-cover">
	<header id="top-header">
		<aside>
			<div style="height:40px;font-weight:bold;font-size:20px;color:#fff; margin-left:60px; margin-top:10px;">Recover</div>
		</aside>
		<section>
			<div class="head">
			</div>
		</section>
	</header>
</section>

<section class="main-cover main-row">
	<section id="main">
		
		<div class="login-content">

			<dl class="login-box">
				<dt>관리자 로그인</dt>
				<dd>
					<form name="loginForm" method="post" class="log_form" onsubmit="return loginFormCheck(this);return false">
					<input type="hidden" name="userId" value="${USER_ID}">
						<table class="edit">
						<tr>
							<th>&nbsp;</th>
							<td>
								<c:choose>
									<c:when test="${ msg == '' || msg == null }">
										<h4 class="ad_title3">사용자 ID와 비밀번호를 입력해주세요.</h4>
									</c:when>
									<c:otherwise>
										<h4 class="ad_title3">
											<span style="color: #3dd4e1;">${message}</span>
										</h4>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th>아이디</th>
							<td>
								<input type="text" name="loginId" class="itext" style="width: 180px;" placeholder="아이디를 입력하세요">
							</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td>
								<input type="password" name="loginPw" class="itext" style="width: 180px;" placeholder="비밀번호를 입력하세요.">
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>
								<input type="submit" name="go" class="btn" style="width: 190px;" value="LOG IN">
							</td>
						</tr>
						</table>

					</form>

				</dd>
			</dl>
		
			<div class="inner">

						<section id="contentsWrap-login">
							<div class="contents">
								<div class="inner">
									<p class="martok" align="center" style="margin:50px 0;">
										<img alt="" style="height:100px;">
									</p>
									<div class="log-wrap">
	
									</div>
								</div>
							</div>
						</section>

			</div>
			
		</div>
		
		<div style="padding-bottom:30px;"></div>
		
		<div class="clear"></div>
	
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>