<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">	

<script type="text/javascript">

$(function() {
    $( "#sortable" ).sortable();
    $( "#sortable" ).disableSelection();
    
   
});




function deleteNotice(medSeq) {
	if(confirm("삭제하시겠습니까?")) {
		var param = {
				medSeq	:	medSeq
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/medical/medical_delete_do.go",
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


<style>
.title { display:table; width: 100%; border-collapse: collapse; margin: 0; padding: 0; border: 0; border-spacing:0;}
.subitem { display:table-cell; display:inline-block;  font-weight:bold; padding:4px; border-left: 1px solid #ddd; text-align:center;}
.datasell {display:table-cell;  width:100%; height:200px; text-align:center; border-top:1px solid #ddd; border-bottom:1px solid #ddd; vertical-align:middle; }
</style>

	<ul class="title">
<!-- 		<li class="subitem">마지막 질문 선택</li> -->
		<li class="subitem" >번호</li>
		<li class="subitem" style="width:50px;">sort</li>
		<li class="subitem" style="width:70px;">구분</li>
		<li class="subitem" style="width:70px;">답유형</li>
		<li class="subitem" style="width:650px;">내용</li>
		<li class="subitem" style="width:50px;">관리</li>
	</ul>


	<ul id="sortable" class="title">
		<c:choose>
			<c:when test="${list.size() > 0}">
				<c:forEach var="it" items="${list}">
					<li class="ui-state-default" style="border: 0 !impotant;" >
						<form method="post" name="listForm" id="listForm" onsubmit="return ListEditForm(this); return false;">
						<span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
							<input class="sort"  type="hidden" name="cvSeq" value="${it.cvSeq}">
							<input type="hidden" name="sort" value="${it.sort}">
							<input type="checkbox" name="isLast" value="${it.cvSeq}" style="margin-left:3px;">
							<span style="display:inline-block; width:10px;  text-align:center">${it.cvSeq}</span>
							<span style="display:inline-block; width:55px; text-align:center">${it.sort}</span>
							<span style="display:inline-block; width:70px; text-align:center">${it.askText}</span>
							<span style="display:inline-block; width:70px; text-align:center; margin-right:5px;">${it.valueText}</span>
							<input type="text" name="comment" style="width:630px; margin-right:15px;" class="itext" value="${it.comment}">
							<button type="submit"  class="btn-blue" >수정</button>
							<button type="button" class="btn-red" onclick="deleteNotice(${it.cvSeq});" >삭제</button>
						</form>
					</li>	
				</c:forEach>
			</c:when>
			<c:otherwise>
				<li class="datasell" >조회된 데이터가 없습니다.</li>
			</c:otherwise>
		</c:choose>
	</ul>	
<%-- 	${paging} --%>




	
