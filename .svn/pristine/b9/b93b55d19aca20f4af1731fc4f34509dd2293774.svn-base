<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
		
		<table class="list">
			<colgroup>
				<col width="20%">
				<col width="5%">
				<col width="20%">
				<col width="5%">
				<col width="25%">
				<col width="15%">				
			</colgroup>
			<thead>
				<tr>
					<th>Point(100%)</th>
					<th>←</th>
					<th>Money</th>
					<th>→</th>
					<th>현금(70%)</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					<c:forEach var="it" items="${list}">
						<tr>
							<td style="text-align: left; padding-left: 10px;">
								<input type="text" class="itext" class="itext" name="eventName1" value="${it.eventName}">
							</td>
							<td style="text-align: left; padding-left: 10px;">
								<input type="text" class="itext" class="itext" name="point1" value="${it.point}">P
							</td>
							<td style="text-align: left; padding-left: 10px;">
								<input type="text" class="itext" class="itext" name="money1" value="${it.money}">M
							</td>
							<td style="text-align: left; padding-left:10px;">
								<input type="text" class="itext" class="itext" name="times1" value="${it.times}" style="width:40px;">
							</td>
							<td><input type="text" class="itext" class="itext" name="commend1" value="${it.commend}"></td>
							<td>
								<button type="button" class="btn" onclick="pointEditForm(this.form);">수정</button>
								<button class="btn" onclick="deletePoint(${it.pointSeq});">삭제</button>
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
			
			<div style="margin-top:30px;">
				<h2>포인트/Money 전환 설정 등록</h2>
			<form method="post" name="pointForm" onsubmit="return submitForm(this); return false;">
				<input type="hidden" name="pointSeq"  value="0"> 
					<table class="list">
						<tr>
							<td style="text-align: left; padding-left: 10px;">
								<input type="text" class="itext" name="eventName">
							</td>
							<td style="text-align: left; padding-left: 10px;">
								<input type="text" class="itext" name="point">P
							</td>
							<td style="text-align: left; padding-left: 10px;">
								<input type="text" class="itext" name="money">M                                                                        
							</td>
							<td style="text-align: left; padding-left:10px;">
								<select name="period" style="width:80px; margin-right: 10px;" class="select-search">
									<option value="1" >최초</option>
									<option value="2">매번</option>
									<option value="3">건별</option>	
									<option value="4">1일</option>
								</select>회
								<input type="text" class="itext" name="times" style="width:40px; margin-left:10px;">
							</td>
							<td style="text-align: left; padding-left: 10px;">
								<input type="text" class="itext" name="commend">
							</td>
							<td><button class="btn">추가</button>  </td>
						</tr>
					</table>
				</form>		
			</div>
		
<%-- 		${paging} --%>
		
	
	
