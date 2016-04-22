<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div>
<form method="post" name="levelForm" onsubmit="return false;">
	<table class="list">
		<colgroup>
			<col width="25%">
			<col width="15%">
			<col width="20%">
			<col width="15%">			
		</colgroup>
		<thead>
			<tr>
				<th>레벨</th>
				<th>최소 경험치</th>
				<th>최대 경험치</th>
				<th>관리</th>
			</tr>
		</thead>
		<tbody class="rl">
		<c:choose>
			<c:when test="${list.size() > 0}">
				<c:forEach var="it" items="${list}">
					<input type="hidden" name="levelSeq"  value="${it.levelSeq}"> 
					<tr>
						<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="level" value="${it.level}">레벨</td>
						<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="minEx" value="${it.minEx}">M</td>
						<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="maxEx" value="${it.maxEx}">M</td>
						<td>
							<button type="button" class="btn" onclick="submitLevelForm(this.form);">수정</button>
							<button class="btn" onclick="deletePoint(${it.levelSeq});">삭제</button>
						</td>							
					</tr>
			
			</c:forEach>
			</c:when>
			<c:otherwise>
			<tr>
				<td colspan="7" style="height:200px;">조회된 데이터가 없습니다.</td>
			</tr>
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>
</form>	
	</div>		
	<div style="margin-top:30px;">
		<h2>레벨 정책 설정 등록</h2>
		<form method="post" name="pointForm" onsubmit="return submitForm(this); return false;">
			<input type="hidden" name="levelSeq"  value="0"> 
			<table class="list">
				<tr>
					<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="level" >레벨</td>
					<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="minEx" >M</td>
					<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="maxEx">M</td>
					<td><button class="btn">추가</button></td>	
				</tr>
			</table>
		</form>		
	</div>
		
	
	
