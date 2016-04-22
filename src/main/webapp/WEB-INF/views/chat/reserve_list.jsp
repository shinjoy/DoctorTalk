<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header5.jsp"  %>
	
<script type="text/javascript">
$(document).ready(function() {
	timer.startTimer();
});

//채팅화면 오픈 요청
function openChat(chatRoomSeq) {
	//window.location = "jscall://callChatRoom?"+id;
	window.location = "jscall://enterChatRoom?"+chatRoomSeq;
}

function refresh() {
	document.location.reload();
}

</script>

<style>
.timer {
	position:fixed; top:10px; right:10px; 
	background-image:url("/images/reload.png"); background-position:center; background-repeat:no-repeat;
	width:40px; height:30px; color:#fff; border-radius:20px; font-size:14px; font-weight:bold; padding-top:10px; text-align:center; cursor:pointer;
}
</style>
</head>

<body>

	<div class="timer" id="timer" onclick="refresh();"></div>
	
	<ul>
		<c:choose>
			<c:when test="${list.size() > 0}">
				<c:forEach var="it" items="${list}">
					<li onclick="openChat('${it.chatRoomSeq}')">
						<table class="chat-list">
						<tr>
							<td class="photo-cell"><div class="profile" style="background-image:url('${it.fileName=='' ? '/images/user_default.png' : it.fileName}');"></div></td>
							<td class="underline">
								<span class="bold">${it.userName}</span> (${it.genderText} | ${it.userAge}세)<br>
								<span class="gray">${it.blood==1 ? '혈당' : '' } ${it.press==1 ? '혈압' : '' } ${it.col==1 ? '콜레스테롤' : '' } ${it.heiwieght==1 ? '체중' : '' }</span>
								<c:if test="${it.isNow == 0}">
									<span style="display:inline-block;padding-left:10px;"><img src="/images/icon_clock.png" style="vertical-align:middle;"> ${it.counselDate } ${it.counselTime }</span>
								</c:if>
							</td>
						</tr>
						</table>
					</li>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<li style="text-align:center; padding-top:50px;">상담 대기자가 없습니다.</li>
			</c:otherwise>
		</c:choose>
	</ul>

</body>

</html>