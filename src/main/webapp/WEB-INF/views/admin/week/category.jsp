<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>






<script type="text/javascript">

	$(document).ready(function() {
		aside.setActive(9,2);
	});

	function searchList(frm,page) {
		document.location.href = "/admin/week/category.go?diseaseId="+frm.diseaseId.value+"&page="+page;
	}
	
	//그룹추가
	function addgroup(num,frm) {
		var param = {
			nextgroup : num,
			diseaseId : frm.diseaseId.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/week/addcontents.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/admin/week/contents.go?group="+(num+1)+"&diseaseId="+frm.diseaseId.value+"&page=1";
				}
			}
		});

		return false;
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
				 ■ 홈 > 닥터톡 - 컨텐츠관리 > 1주컨텐츠
			</header>
		
			<div class="contents-block">
			
				<h1>1주컨텐츠</h1>
				
				<div class="contents-main">
				
										
					<div id="contents-list">
					
						<form method="post" name="listForm" id="listForm" >
						<input type="hidden" name="diseaseId" value="${diseaseId}">

							<div class="contents-top">
							 	<div class="top-tools">
								 	<div class="search-tool" style="float:left;">
										<button class="${diseaseId =='blood' ? 'btn-blue' : 'btn' }" type="button" onclick="this.form.diseaseId.value='blood';searchList(this.form,1);">당뇨병</button>
										<button class="${diseaseId =='press' ? 'btn-blue' : 'btn' }" type="button" onclick="this.form.diseaseId.value='press';searchList(this.form,1)">고혈압</button>
										<button class="${diseaseId =='col' ? 'btn-blue' : 'btn' }" type="button" onclick="this.form.diseaseId.value='col';searchList(this.form,1)">고지혈증</button>
										<button class="${diseaseId =='weight' ? 'btn-blue' : 'btn' }" type="button" onclick="this.form.diseaseId.value='weight';searchList(this.form,1)">비만</button>
										<button style="margin-left:10px;" class="btn-green" type="button" onclick="addgroup(${maxGroup}, this.form);">신규등록</button>
									</div>
								</div>
							</div>
	
							<table class="list">
							<colgroup>
								<col width="70"></col>
								<col width="60"></col>
								<col width="*"></col>
								<col width="60"></col>
							</colgroup>
							<thead>
								<tr>
									<th>그룹번호</th>
									<th>질환</th>
									<th>컨텐츠</th>
									<th>대화갯수</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="it" items="${list}">
								<tr>
									<td><a href="/admin/week/contents.go?group=${it.weekgroup}&diseaseId=${it.diseaseId}&page=1">${it.weekgroup}</a></td>
									<td><a href="/admin/week/contents.go?group=${it.weekgroup}&diseaseId=${it.diseaseId}&page=1">
										${it.diseaseId=='blood' ? '당뇨병' : it.diseaseId=='press' ? '고혈압' : it.diseaseId=='col' ? '고지혈증' : it.diseaseId=='weight' ? '비만' : ''}
									</a></td>
									<td style="text-align:left;">
										<a href="/admin/week/contents.go?group=${it.weekgroup}&diseaseId=${it.diseaseId}&page=1">
										<c:forEach var="c" items="${it.weekList}">
											${c.comment}
										</c:forEach>
										</a>	
									</td>
									<td><a href="/admin/week/contents.go?group=${it.weekgroup}&diseaseId=${it.diseaseId}&page=1">${it.count}</a></td>
								</tr>
								</c:forEach>
							</tbody>
							</table>
							
							${paging }
							
						</form>

					</div>
						
				</div>
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>