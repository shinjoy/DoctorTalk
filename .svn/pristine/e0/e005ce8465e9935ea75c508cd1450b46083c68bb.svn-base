<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(3,1);
		searchList(listForm,1);
	});

</script>

<style>
.device { font-weight: bold; font-size:25px; color:#464242; padding-bottom:30px;}	
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 게시판관리 > 공지사항 상세
			</header>
		
			<div class="contents-block">
			
				<h1>공지사항 상세</h1>
				
				<div class="contents-main">

					<div class="tbl-edit">
						<table>
						<col width="15%">
						<col width="*">
							<tr>
								<td>${notice.seq}</td>
								<td>${notice.title}</td>
								<td>${notice.regDate.substring(0,16)}</td>
							</tr>
<!-- 							<tr> -->
<!-- 								<th><div class="icon"><span class="arrow"></span> 제목<span class="required">*</span></div></th> -->
<!-- 								<td></td> -->
<!-- 							</tr> -->
							<tr>
								<th>내용</th>
								<td class="bbs-contents">${notice.contentsText}</td>
							</tr>
						</table>
						
					</div>
					<div class="btnC">
						<button type="button" class="btn" onclick="document.location.href='/recruit/recruit.go';" >목록</button>
					</div>
					
				</div>
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>