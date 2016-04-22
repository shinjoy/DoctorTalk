<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header5.jsp"  %>
	
<script type="text/javascript">
$(document).ready(function() {

});
</script>
<style>
	#pop-box { padding:5px;}
	.pop-tool { position:relative; height:100px; text-align:center; }
	.pop-tool .user-photo { width:60px; height:60px; margin:10px auto; }
	.pop-tool .close-btn { position:absolute; top:0; right:0; }
	table.info { width:100%; border:1px solid #aaa; }
	table.info th { vertical-align:top; text-align:right; padding:5px; font-weight:normal; border-bottom:1px solid #ccc; background-color:#eee; }
	table.info td { vertical-align:top; padding:5px; border-bottom:1px solid #ccc; }
	table.info td.data { color:#000; }
</style>

</head>

<body>

	<div class="pop-tool">
		<div class="detail-btn">
		</div>
		<div class="user-photo">
			<div class="photo-big-chat" style="background-image:url('${user.fileName=='' ? '/images/user_default.png' : user.fileName }');"></div>
			<button type="button" onclick="windowOpen('http://recover.bestist.net/pc/blood_view.go?userId=${user.userId}', 'recover_detail', 1060, 700, 'yes', 'no')" class="btn" style="margin-top:3px;">상세정보</button>
		</div>
		<div class="close-btn"><!-- <img src="/images/lay_close.png" class="btn-close" onclick="pop.close();"> --></div>
	</div>
	

	<table class="info">
	<col width="30%">
	<col width="30%">
	<col width="20%">
	<col width="20%">
	<tr>
		<th>성명</th>
		<td colspan="3">${user.userName}</td>
	</tr>
	<tr>
		<th>성별</th>
		<td>${user.genderText}</td>
		<th>나이</th>
		<td>${user.userAge}세</td>
	</tr>
	<tr>
		<th>보유질환</th>
		<td colspan="3">
			${basic.blood=='blood' ? '당뇨병' : ''}
			${basic.press=='press' ? '고혈압' : ''}
			${basic.col=='col' ? '고지혈증' : ''}
			${basic.heiwieght=='heiwieght' ? '비만' : ''}
		</td>
	</tr>
	<!-- 
	<tr>
		<th>제휴사</th>
		<td></td>
		<th>서비스등급</th>
		<td></td>
	</tr>
	 -->
	<tr>
		<th>휴대폰번호</th>
		<td colspan="3">${user.phoneNumber}</td>
	</tr>
	<tr>
		<th>E-mail</th>
		<td colspan="3">${user.userId}</td>
	</tr>
	</table>
	
</body>

</html>