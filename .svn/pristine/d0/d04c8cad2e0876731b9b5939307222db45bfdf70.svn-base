<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>






<script type="text/javascript">

	$(document).ready(function() {
 		aside.setActive(9,1);
	});

	function searchList(frm,page) {
		document.location.href = "/admin/day/category.go?page="+page;
	}
	
	//그룹추가
	function addgroup(num,frm) {
		
		var check1 =$("input:radio[name=genderType]").is(":checked") ;
		if(check1==false){
			alert("그룹유형을 선택해주세요");
			return false;
		}
		var param = {
			nextgroup : num,
			genderType : frm.genderType.value
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/day/addcontents.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/admin/day/contents.go?group="+(num+1)+"&page=1";
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
				 ■ 홈 > 닥터톡 - 컨텐츠관리 > 1일컨텐츠
			</header>
		
			<div class="contents-block">
			
				<h1>1일컨텐츠</h1>
				
				<div class="contents-main">
				
					 <form method="post" name="listForm" id="listForm" >
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool">
									<span style="padding-left: 10px;">
									<input type="radio" name="genderType" value="0">공통</input>
									<input type="radio" name="genderType" value="1">여자만</input>
									<button class="btn" type="button" onclick="addgroup(${maxGroup},this.form)">신규등록</button>
									</span>
								</div>
							</div>		
						</div>
					</form>	
										
					<div id="contents-list">
					
						<form method="post" name="listForm" id="listForm" >

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
									<td><a href="/admin/day/contents.go?group=${it.daygroup}&page=1">${it.daygroup}</a></td>
									<td style="text-align:left;">
										<a href="/admin/day/contents.go?group=${it.daygroup}&page=1">
										<c:forEach var="c" items="${it.dayList}">
											${c.comment}
										</c:forEach>
										</a>	
									</td>
									<td><a href="/admin/day/contents.go?group=${it.daygroup}&page=1">${it.count}</a></td>
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