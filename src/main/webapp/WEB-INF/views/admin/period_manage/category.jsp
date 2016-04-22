<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>






<script type="text/javascript">

	$(document).ready(function() {
		aside.setActive(8,4);
	});

	
</script>

</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 닥터톡 - 관리목표/주기 > 기간내 미입력
			</header>
		
			<div class="contents-block">
			
				<h1>기간내 미입력</h1>
				
				<div class="contents-main">
				
					<div id="contents-list">
						<table class="list">
						<thead>
							<tr>
								<th>그룹번호</th>
								<th>컨텐츠</th>
								<th>대화갯수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="it" items="${list}">
							<tr>
								<td><a href="/admin/period_manage/no_input.go?group=${it.kcase}&page=1">${it.kcase}</a></td>
								<td style="text-align:left;">
									<a href="/admin/period_manage/no_input.go?group=${it.kcase}&page=1">
									<c:forEach var="c" items="${it.caseList}">
										${c.comment}
									</c:forEach>
									</a>	
								</td>
								<td><a href="/admin/period_manage/no_input.go?group=${it.kcase}&page=1">${it.count}</a></td>
							</tr>
							</c:forEach>
						</tbody>
						</table>
					</div>
						
				</div>
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>