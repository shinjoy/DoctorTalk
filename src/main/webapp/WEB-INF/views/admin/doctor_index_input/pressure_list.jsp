<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	

<script type="text/javascript">

$(function() {
    $( "#sortable" ).sortable();
    $( "#sortable" ).disableSelection();
});



function deleteNotice(comSeq) {
	if(confirm("삭제하시겠습니까?")) {
		var param = {
				comSeq	:	comSeq
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/doctor_index_input/blood_delete_do.go",
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
.title { display:table; width: 100%; border-collapse: collapse; margin: 0; padding: 0; border: 0; border-spacing: 0;  }
.subitem { display:table-cell; display:inline-block;  font-weight:bold; padding: 4px; border-left: 1px solid #ddd; text-align:center;   }
.datasell {display:table-cell;  width:100%; height:200px; text-align:center; border-top:1px solid #ddd; border-bottom:1px solid #ddd; vertical-align:middle; }
</style>

	<ul class="title">
		<li class="subitem" style="width:50px;">고유코드</li>
		<li class="subitem" style="width:50px;">sort</li>
		<li class="subitem" style="width:40px;">구분</li>
		<li class="subitem" style="width:70px;">답유형</li>
		<li class="subitem" style="width:50px;">질문번호</li>
		<li class="subitem" style="width:50px;">이동번호</li>
		<li class="subitem" style="width:520px;">내용</li>
		<li class="subitem" style="width:30px;">관리</li>
	</ul>

	<ul id="sortable" class="title">
		<c:choose>
			<c:when test="${list.size() > 0}">
				<c:forEach var="it" items="${list}">
					<li class="ui-state-default" >
						<form method="post" name="editForm" id="editForm" onsubmit="return ListEditForm(this); return false;">
						<span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
							<input class="sort" type="hidden" name="comSeq" value="${it.comSeq}">
							<input type="hidden" name="sort" value="${it.sort}">
							<input type="checkbox" name="isLast" class="isLast" value="${it.comSeq}" style="margin-left:-10px;" ${it.isLast == 1 ? 'checked=\"checked\"' : ''}>
							<span style="display:inline-block; width:15px;  text-align:center">${it.comSeq}</span>
							<span style="display:inline-block; width:75px; text-align:center">${it.sort}</span>
							<span style="display:inline-block; width:35px; text-align:center; margin-right:7px;">${it.askText}</span>
							<span style="display:inline-block; width:70px; text-align:center; margin-left:4px; margin-right:15px;">
								<c:choose>
									<c:when test="${it.askind == 1 }">
										<select class="ansType2" name="ansType2" style="width:60px;">
											<option value="0" ${it.ansType == 0 ? 'selected=\"selected\"' : ''}>입력</option>
											<option value="1" ${it.ansType == 1 ? 'selected=\"selected\"' : ''}>선택</option>
											<option value="3" ${it.ansType == 3 ? 'selected=\"selected\"' : ''}>다음질문으로 </option>
											<option value="4" ${it.ansType == 4 ? 'selected=\"selected\"' : ''}>다중선택</option>
										</select>
									</c:when>
									<c:otherwise>
										<input class="ansType2" type="hidden" name="ansType2" value="0" />
									</c:otherwise>
								</c:choose>
							</span>
							<input type="text" class="itext" name="pseq2" style="width:25px; margin-right:15px;" value="${it.pseq}">
							<input type="text" class="itext" name="goseq2" style="width:25px; margin-right:15px;" value="${it.move}">
							<input type="text" name="comment2" style="width:510px; margin-right:20px;" class="itext" value="${it.comment}">
							<button type="submit"  class="btn-blue" >수정</button>
							<button type="button" class="btn-red" onclick="deleteNotice(${it.comSeq});" >삭제</button>
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




	
