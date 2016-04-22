<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>	

<script type="text/javascript">

$(function() {
    $( "#sortable" ).sortable();
    $( "#sortable" ).disableSelection();
    
   
});




function deleteNotice(preSeq) {
	if(confirm("삭제하시겠습니까?")) {
		var param = {
				preSeq	:	preSeq
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/period_manage/period_delete_do.go",
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
		<li class="subitem" style="width:50px;">고유코드</li>
		<li class="subitem" style="width:30px;">sort</li>
		<li class="subitem" style="width:70px;">질병이름</li>
		<li class="subitem" style="width:710px;">내용</li>
		<li class="subitem" style="width:30px;">관리</li>
	</ul>


	<ul id="sortable" class="title">
		<c:choose>
			<c:when test="${list.size() > 0}">
				<c:forEach var="it" items="${list}">
					<li class="ui-state-default" style="border: 0 !impotant;" >
						<form method="post" name="listForm" id="listForm" onsubmit="return ListEditForm(this); return false;">
						<span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
							<input class="sort"  type="hidden" name="preSeq" value="${it.preSeq}">
							<input type="hidden" name="sort" value="${it.sort}">
						
							<span style="display:inline-block; width:15px;  text-align:center">${it.preSeq}</span>
							<span style="display:inline-block; width:65px; text-align:center">${it.sort}</span>
							<span style="display:inline-block; width:65px; text-align:center; margin-left:-10px;">
								<c:if test="${it.diseaseId == 'blood'}">혈당</c:if>
								<c:if test="${it.diseaseId == 'press'}">혈압</c:if>
								<c:if test="${it.diseaseId == 'col'}">콜레스테롤</c:if>
								<c:if test="${it.diseaseId == 'weight'}">비만</c:if>
							</span>
							<input type="text" name="comment2" style="width:700px; margin-left:10px; margin-right:20px;" class="itext" value="${it.comment}">
							<button type="submit"  class="btn-blue" >수정</button>
							<button type="button" class="btn-red" onclick="deleteNotice(${it.preSeq});" >삭제</button>
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




	
