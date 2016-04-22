<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	
	$(document).ready(function() {
// 		aside.setActive(3,1);
 		aside.setActive(1,4);
	});

	/* 저장 */
	function submitFormCategory(frm) {

		var param = {
				cateSeq : frm.cateSeq.value,
				parentSeq : frm.parentSeq.value,
				lowCategory : frm.lowCategory.value
		};
		//console.log(param);
		$.ajax({
			type:"POST",
			url:"/admin/category/category_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json) {
				alert(json.message);
				if(json.result) {
					document.location.reload();
				}
			}
		});
	}


	function deleteCategory(cateSeq,parentSeq) {
		if(confirm("하위 카테고리가 있으면 함께 삭제됩니다. 카테고리를 삭제하시겠습니까?")) {
			var param = {
					parentSeq	:	parentSeq
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/category/category_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.reload();
					}
				}
			});
		}
		return false;

	}
	
	function deleteParentCategory(cateSeq) {
		if(confirm("하위 카테고리를 삭제하시겠습니까?")) {
			var param = {
					cateSeq	:	cateSeq
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/category/parent_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.reload();
					}
				}
			});
		}
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
				 ■ 홈 > 카테고리
			</header>
		
			<div class="contents-block">
			
				<h1>카테고리</h1>
				
				<div>
					<form method="post" name="printcenterForm" onsubmit="return submitForm(this); return false;">
				
						<table>	
								<form method="post" name="parentseqForm">
									<tr>
										<td>일반음식점</td>	
										<td><button type="button" class="btn" onclick="deleteCategory(0,1);">삭제</button></td>
									</tr>
								</form>
									<c:choose>
											<c:when test="${general.size() > 0}">
												<c:forEach var="it" items="${general}">
													<form method="post" name="seqForm">
														<tr>
															<input type="hidden" name="cateSeq" value="${it.cateSeq}">
															<input type="hidden" name="parentSeq" value="${it.parentSeq}">
															<td><input type="text" class="itext" name="lowCategory" value="${it.categoryName}"></td>
															<td><button type="button" class="btn" onclick="submitFormCategory(this.form);" >수정</button></td>			
															<td><button type="button" class="btn" onclick="deleteParentCategory(${it.cateSeq});">삭제</button></td>																						
														</tr>
													</form>				
												</c:forEach>
												<form method="post" name="seqForm">
													<tr>									
														<input type="hidden" name="cateSeq" value="0">
														<input type="hidden" name="parentSeq" value="1">
														<td><input type="text" class="itext" name="lowCategory" value=""></td>
														<td><button type="button" class="btn" onclick="submitFormCategory(this.form);">추가</button></td>		
													</tr>
												</form>
											</c:when>	
											<c:otherwise>
												<form method="post" name="seqForm">
													<tr>									
														<input type="hidden" name="cateSeq" value="0">
														<input type="hidden" name="parentSeq" value="1">
														<td><input type="text" class="itext" name="lowCategory" value="${it.categoryName}"></td>
														<td><button type="button" class="btn" onclick="submitFormCategory(this.form);">추가</button></td>		
													</tr>	
												</form>	
											</c:otherwise>
									</c:choose>
								</form>	
								
								<form method="post" name="parentseqForm">	
									<tr>
										<td>배달음식점</td>	
										<td><button type="button" class="btn" onclick="deleteCategory(0,2);">삭제</button></td>
									</tr>
								</form>
								<form method="post" name="seqForm">
									<c:choose>
											<c:when test="${deliver.size() > 0}">
												<c:forEach var="it" items="${deliver}">
													<form method="post" name="seqForm">
														<tr>
															<input type="hidden" name="cateSeq" value="${it.cateSeq}">
															<input type="hidden" name="parentSeq" value="${it.parentSeq}">
															<td><input type="text" class="itext" name="lowCategory" value="${it.categoryName}"></td>
															<td><button type="button" class="btn" onclick="submitFormCategory(this.form);" >수정</button></td>			
															<td><button type="button" class="btn" onclick="deleteParentCategory(${it.cateSeq});">삭제</button></td>																						
														</tr>
													</form>				
												</c:forEach>
												<form method="post" name="seqForm">
													<tr>									
														<input type="hidden" name="cateSeq" value="0">
														<input type="hidden" name="parentSeq" value="2">
														<td><input type="text" class="itext" name="lowCategory" value=""></td>
														<td><button type="button" class="btn" onclick="submitFormCategory(this.form);">추가</button></td>		
													</tr>
												</form>
											</c:when>	
											<c:otherwise>
												<form method="post" name="seqForm">
													<tr>									
														<input type="hidden" name="cateSeq" value="0">
														<input type="hidden" name="parentSeq" value="2">
														<td><input type="text" class="itext" name="lowCategory" value="${it.categoryName}"></td>
														<td><button type="button" class="btn" onclick="submitFormCategory(this.form);">추가</button></td>		
													</tr>	
												</form>	
											</c:otherwise>
									</c:choose>
								</form>	
								
								<form method="post" name="parentseqForm">
									<tr>
										<input type="hidden" name="cateSeq" value="3">
										<td>유통/서비스</td>	
										<td><button type="button" class="btn" onclick="deleteCategory(0,3);">삭제</button></td>
									</tr>
								</form>	
								<form method="post" name="seqForm">
									<c:choose>
											<c:when test="${circulation.size() > 0}">
												<c:forEach var="it" items="${circulation}">
													<form method="post" name="seqForm">
														<tr>
															<input type="hidden" name="cateSeq" value="${it.cateSeq}">
															<input type="hidden" name="parentSeq" value="${it.parentSeq}">
															<td><input type="text" class="itext" name="lowCategory" value="${it.categoryName}"></td>
															<td><button type="button" class="btn" onclick="submitFormCategory(this.form);" >수정</button></td>			
															<td><button type="button" class="btn" onclick="deleteParentCategory(${it.cateSeq});">삭제</button></td>																						
														</tr>
													</form>				
												</c:forEach>
												<form method="post" name="seqForm">
													<tr>									
														<input type="hidden" name="cateSeq" value="0">
														<input type="hidden" name="parentSeq" value="3">
														<td><input type="text" class="itext" name="lowCategory" value=""></td>
														<td><button type="button" class="btn" onclick="submitFormCategory(this.form);">추가</button></td>		
													</tr>
												</form>
											</c:when>	
											<c:otherwise>
												<form method="post" name="seqForm">
													<tr>									
														<input type="hidden" name="cateSeq" value="0">
														<input type="hidden" name="parentSeq" value="3">
														<td><input type="text" class="itext" name="lowCategory" value="${it.categoryName}"></td>
														<td><button type="button" class="btn" onclick="submitFormCategory(this.form);">추가</button></td>		
													</tr>	
												</form>	
											</c:otherwise>
									</c:choose>
								</form>	
								
								<form method="post" name="parentseqForm">
									<tr>
										<input type="hidden" name="cateSeq" value="4">
										<td>생활편의</td>
										<td><button type="button" class="btn" onclick="deleteCategory(0,4);">삭제</button></td>
									</tr>
								</form>
								<form method="post" name="seqForm">
									<c:choose>
											<c:when test="${life.size() > 0}">
												<c:forEach var="it" items="${life}">
													<form method="post" name="seqForm">
														<tr>
															<input type="hidden" name="cateSeq" value="${it.cateSeq}">
															<input type="hidden" name="parentSeq" value="${it.parentSeq}">
															<td><input type="text" class="itext" name="lowCategory" value="${it.categoryName}"></td>
															<td><button type="button" class="btn" onclick="submitFormCategory(this.form);" >수정</button></td>			
															<td><button type="button" class="btn" onclick="deleteParentCategory(${it.cateSeq});">삭제</button></td>																						
														</tr>
													</form>				
												</c:forEach>
												<form method="post" name="seqForm">
													<tr>									
														<input type="hidden" name="cateSeq" value="0">
														<input type="hidden" name="parentSeq" value="4">
														<td><input type="text" class="itext" name="lowCategory" value=""></td>
														<td><button type="button" class="btn" onclick="submitFormCategory(this.form);">추가</button></td>		
													</tr>
												</form>
											</c:when>	
											<c:otherwise>
												<form method="post" name="seqForm">
													<tr>									
														<input type="hidden" name="cateSeq" value="0">
														<input type="hidden" name="parentSeq" value="4">
														<td><input type="text" class="itext" name="lowCategory" value="${it.categoryName}"></td>
														<td><button type="button" class="btn" onclick="submitFormCategory(this.form);">추가</button></td>		
													</tr>	
												</form>	
											</c:otherwise>
									</c:choose>
								</form>	
			
						</table>	
					</form>	
				</div>

				</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>