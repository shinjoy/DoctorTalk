<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ include file="/WEB-INF/views/common/header.jsp"  %>     

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회사소개</title>

<script type="text/javascript">

$(document).ready(function() {
	$("#user-menu4 A").css("color","#000");	
});

</script>

<style type="text/css">
.login-title { margin-top:70px; margin-left:30px; font-weight:bold; font-size:20px;}	
.gray-top { width:1000px; padding-bottom:20px; border-bottom:1px solid #ddd;}
.gray-bottom { border-bottom:1px solid #ddd; width:950px; margin-left:30px; }
</style>

</head>
<body>
	
	<div class="login-wrap">
	
		<%@ include file="/WEB-INF/views/home_header.jsp"%> 	
		
		
		<div class="login-content">
	
			<div class="clear" ></div>
		
			<div class="login-content" >
				<div class="inner">
					<div class="login-title">자주묻는질문 > 상세보기</div>
					
					<div class="gray-top"></div>
					
					<div style=" padding-left:30px; padding-top:30px; width:950px;">
						<table style="width:950px;">
						<colgroup>
							<col width="*">
							<col width="15%">
						</colgroup>
						
							<tr style="background-color:#20BBD9; border-top:2px solid #0782BD; height:30px; border-bottom:2px solid #ddd;">
								<td style="padding-left:30px; color:#fff;">${faq.seq}. ${faq.title}</td>
								<td style="color:#fff;">${faq.regDate.substring(0,16)}</td>
							</tr>
<!-- 							<tr> -->
<!-- 								<th><div class="icon"><span class="arrow"></span> 제목<span class="required">*</span></div></th> -->
<!-- 								<td></td> -->
<!-- 							</tr> -->
							<tr>
								<td class="bbs-contents" colspan="2" style="color:#000; padding-left:30px; padding-top:10px;">${faq.questionHtml}</td>
							</tr>
						</table>
						
					</div>
					
				</div>
			</div><!-- End of class="contents" //-->
			
			<div class="btnC" style="padding-left:450px; padding-bottom:10px; padding-top:10px;">
				<button type="button" class="btn" onclick="document.location.href='/user/fnq/fnq.go';" >목록</button>
			</div>		
							
			
		</div>	
					<div class="gray-bottom"></div>	
				<div style="padding-bottom:30px;"></div>
		
		<div class="clear"></div>
		<%@ include file="/WEB-INF/views/home_footer.jsp"  %>  	 		
	
	</div>
</body>
</html>