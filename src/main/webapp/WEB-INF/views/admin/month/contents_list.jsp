<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script type="text/javascript">
	
$(function() {
    $( "#sortable" ).sortable();
    $( "#sortable" ).disableSelection();
});

	
</script>
<style>
.title { display:table; width: 100%; border-collapse: collapse; margin: 0; padding: 0; border: 0; border-spacing: 0;  }
.subitem { display:table-cell; display:inline-block;  font-weight:bold; padding: 4px; border-left: 1px solid #ddd; text-align:center; border-top:1px solid #ddd;    }
.datasell {display:table-cell;  width:1000px; height:200px; text-align:center; border-top:1px solid #ddd; border-bottom:1px solid #ddd; vertical-align:middle; }
</style>
</head>
<body>



 	<ul class="title">
		<li class="subitem" style="width:60px;">고유코드</li>
		<li class="subitem" style="width:40px;">sort</li>
		<li class="subitem" style="width:40px;">구분</li>
		<li class="subitem" style="width:70px;">답유형</li>
		<li class="subitem" style="width:50px;">질문번호</li>
		<li class="subitem" style="width:50px;">이동번호</li>
		<li class="subitem" style="width:540px;">내용</li>
		<li class="subitem" style="width:75px;">관리</li>
	</ul>

	<ul id="sortable">
		<c:choose>
			<c:when test="${list.size() > 0}">
				<c:forEach var="it" items="${list}">
					
					<li class="ui-state-default">
						<form method="post" name="editForm" id="editForm" onsubmit="return editgo(this); return false;">
						<span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
							<input type="hidden" name="month" value="${nowmonth}"/>
							<input class="sort" type="hidden" name="monthSeq" value="${it.monthSeq}"/>
							<input type="hidden" name="sort" value="${it.sort}"/>
							<input type="checkbox" name="isLast" class="isLast" value="${it.monthSeq}" style="margin-left:-5px;"  ${it.isLast == 1 ? 'checked=\"checked\"' : ''}> 
							<span style="display:inline-block; width:10px; text-align:center">${it.monthSeq}</span>
							<span style="display:inline-block; width:80px; text-align:center">${it.sort}</span>
							<span style="display:inline-block; width:25px; text-align:center; margin-right:20px;">${it.askText }</span>
							<span style="display:inline-block; width:60px; text-align:center; margin-right:15px;">
								<c:choose>
									<c:when test="${it.askind == 1 }">
										<select name="ansType" style="width:60px;">
											<option value="0" ${it.ansType == 0 ? 'selected=\"selected\"' : ''}>입력</option>
											<option value="1" ${it.ansType == 1 ? 'selected=\"selected\"' : ''}>선택</option>
											<option value="3" ${it.ansType == 3 ? 'selected=\"selected\"' : ''}>다음질문으로 </option>
											<option value="4" ${it.ansType == 4 ? 'selected=\"selected\"' : ''}>다중선택</option>
										</select>
									</c:when>
									<c:otherwise>
										<input type="hidden" name="ansType" value="0"/>
									</c:otherwise>
								</c:choose>
							</span>
							<input type="text" name="pseq" class="itext" style="width:30px; margin-right:10px;" value="${it.pseq }">
							<input type="text" name="goseq" class="itext"  style="width:30px; margin-right:10px;" value="${it.move }">
							<input type="text" name="comment" style="width:530px; margin-right:7px;" class="itext" value="${it.comment}">
							<button type="submit"  class="btn-blue" >수정</button>
							<button type="button" class="btn-red" onclick="deletemonth(${it.monthSeq},${it.month },this.form);" >삭제</button>
						</form>
					</li>	
				</c:forEach>
			</c:when>
			<c:otherwise>
				<li class="datasell" colspan="8">조회된 데이터가 없습니다.</li>
			</c:otherwise>
		</c:choose>
	</ul>

	<div style="text-align: right; margin-top: 10px; margin-right: 10px;">
		<button type="button" class="btn" onclick="checkQuestion()">질문 체크</button>
		<button type="button" class="btn" onclick="viewArray();">정렬 저장</button>
	</div>

	<div style="margin-top: 20px;">
		<h2 style="margin-left: 10px;">추가하기</h2>
		<form method="post" onsubmit="return addToweek(this); return false;">
			
			<input type="hidden" name="maxsort" value="${list.size()}"/>
			<input type="hidden" name="month" id ="month" value="${nowmonth}"/>
			
			<div style="float: left; margin-left: 10px;">
				<select class="askind" name="askind" id="askind" onchange="questType(this.form)">

					<option value="1">질문</option>
					<option value="2">답변</option>
				</select>
			</div>
			<div class="kind" id="kind" style="display: block; margin-left: 10px; margin-bottom: 20px;">
			
				<select name="ansType" class="ansType" style="margin-left: 10px;">
					<option value="0" selected="selected">입력</option>
					<option value="1">선택</option>
					<option value="3">다음질문</option>
					<option value="4">다중선택</option>
				</select>
				<input type="hidden" name="pseq" value="0"/> 
				
				<input type="text" class="itext" name="goSeq" placeholder="이동번호" style="width: 50px;">
				<input type="text" class="itext" name="comment" style="width: 600px;">
				<button class="btn" type="button" onclick="addTomonth(this.form);">등록</button>

			</div>

			<div class="kind2" id="kind2" style="display: none; margin-left: 10px; margin-bottom: 20px;">
				<input type="text" class="itext" name="pseq" id="pseq" placeholder="질문번호" style="width: 50px;"> 
				<input type="text" class="itext" name="goseq" id="goseq" placeholder="이동번호" style="width: 50px;">
				<input type="hidden" name="ansType" value="0"/> 
				<input type="text" class="itext" name="comment1" style="width: 600px;">
				<button class="btn" type="button" onclick="addanTomonth(this.form);">등록</button>
			
			</div>

			
			
		</form>
	</div>








</body>
</html> 