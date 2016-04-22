<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(1,1);
		searchCategory(firmForm);
		searchArea(firmForm);
	});
	
	
	function submitForm(frm) {
		
		/* if (frm.userName.value=="") {
			alert("이름을 입력해주세요.");
			return false;
		}
		if (frm.userId.value == "") {
			alert("ID (이메일)을 입력하세요.");
			return false;
		}
		if (validEmail(frm.userId.value) == false) {
			 return false;
		}
		if (frm.phone1.value=="" || frm.phone2.value=="" || frm.phone3.value=="") {
			alert("연락처를 정확하게 입력해주세요.");
			return false;
		}
		if (frm.address1.value=="" || frm.address2.value=="") {
			alert("주소를 입력해주세요.");
			return false;
		}
		if (frm.bCode.value=="") {
			alert("지점 번호를 입력해주세요.");
			return false;
		} */
		var param = {
	
				companySeq 	:	frm.companySeq.value,
				companyName	:	frm.companyName.value,
				category1	:	frm.category.value,
				category2	:	frm.categoryName.value,
				sido		:	frm.areaSido.value,
				gugun		:	frm.areaGugun.value,
				address		:	frm.address.value,
				phoneNumber	:	frm.phone1.value+"-" + frm.phone2.value +"-"+ frm.phone3.value,
				introduce	:	frm.introduce.value,
				isMember	: 	frm.isMember.value,
				saveCash	:	frm.saveCash.value,
				saveCard	:	frm.saveCard.value,
				saveDamoin	:	frm.saveDamoin.value,
				isDelivery	:	frm.isDelivery.value,
				openTime	:	frm.openTime.value,
				closeTime	:	frm.closeTime.value,
				holiday		:	frm.holiday.value,
				isMenu		:	frm.isMenu.value,
				latitude	:	frm.latitude.value,
				longitude	:	frm.longitude.value
		};
		

		$.ajax({
			type:"POST",
			url:"/admin/firm/firm_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json) {
				alert(json.message);
				if(json.result) {
					document.location.href = "/admin/firm/firm.go";
				}
			}
		});
		return false;
	}
	
	
	function phoneCheck(phone){
		
	    var re = /^[0-9]+$/;
	    
	    if(re.test(phone) == false) {
	           alert("전화번호는 숫자만 가능합니다.");
	           return false;
	    }else{
	    	
	    	return true;
	    }
		
		
	}

	function searchCategory(frm) {
	
		if(frm.category.value == "10"){
			$('categoryName').find('option:first').attr('selected', 'selected');
		}
		
		var param = {
			parentSeq		: 	frm.category.value
		};
		
				
		$.ajax({
			type:"POST",
			url:"/admin/catory/catory.go",
			dataType:"json",
			data:param,
			success:function(json){
				var list = json.list;
				var str = '<option value="">== 검색결과 ==</option>';
				
				for (var i=0; i<list.length; i++) {
					var selected = "";
					if (list[i].cateSeq == pageForm.category2.value) {
						selected = 'selected="selected"';
					}
					str += '<option value="'+list[i].cateSeq+'" '+ selected +'>'+list[i].categoryName+'</option>';
				}
				$("#categoryName").html(str);
			}
		});
	 
		return false;
	}
	
	
	
	function searchArea(frm) {
		
		if(frm.areaSido.value == "0"){
			$('areaSido').find('option:first').attr('selected', 'selected');
		}
		
		var param = {
				areaSido		: 	frm.areaSido.value
		};
		
				
		$.ajax({
			type:"POST",
			url:"/admin/area/search.go",
			dataType:"json",
			data:param,
			success:function(json){
				var list = json.list;
				var str = '<option value="">== 검색결과 ==</option>';
				
				for (var i=0; i<list.length; i++) {
					var selected = "";
					if (list[i].gugun == pageForm.gugun2.value) {
						selected = 'selected="selected"';
					}
					str += '<option value="'+list[i].gugun+'" '+ selected +'>'+list[i].gugun+'</option>';
				}
				$("#areaGugun").html(str);
			}
		});
	 
		return false;
	}
	
	
	
</script>

<style>
table.register th {
   vertical-align: top;
   text-align: right;
   padding:10px;
   font-weight: bold;
}

table.register td {
	padding:10px;
    vertical-align: top;
}	

</style>
</head>
<body>

<form name="pageForm">
<input type="hidden" name="category2" value="${company.category2}">
<input type="hidden" name="gugun2" value="${company.gugun}">
</form>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 업체관리 > 업체등록
			</header>
		
			<div class="contents-block">
			<form method="post" name="firmForm" onSubmit="return submitForm(this); return false;">
					<input type="hidden" name="companySeq" value="${company.companySeq}">
				<h1>업체 등록</h1>
				
				<div class="contents-main">

					<div class="contents-box">
				
						
							<table class="register">
						<tr>
							<th><div class="icon">업체명</div></th>
							<td><input type="text" class="itext" name="companyName" value="${company.companyName}"></td>
						</tr>

						<tr>
							<th><div class="icon"> 카테고리</div></th>
							<td>
							<select onchange="searchCategory(this.form);" name="category">
								<option value="10">=선택=</option>
								<option value="1" ${company.category1 == '1' ? 'selected=\"selected\"' : ''}>일반음식점</option>
								<option value="2" ${company.category1 == '2' ? 'selected=\"selected\"' : ''}>배달음식점</option>
								<option value="3" ${company.category1 == '3' ? 'selected=\"selected\"' : ''}>유통/서비스</option>
								<option value="4" ${company.category1 == '4' ? 'selected=\"selected\"' : ''}>생활편의</option>									
							</select>
					
							<select id="categoryName" name="categoryName">
								<option value="0">== 검색 ==</option>
							</select>
							</td>
						</tr>
												
						<tr>
							<th><div class="icon">전화번호</div></th>
							<td colspan="3">
							<c:set var="phoneNumber" value="${fn:split(company.phoneNumber, '-')}"/>
							 <c:forEach var="s1" items="${phoneNumber}" varStatus="s">
						    <c:if test="${s.count==1}"><c:set var="phoneNumber1" value="${s1}"/></c:if>  
						    <c:if test="${s.count==2}"><c:set var="phoneNumber2" value="${s1}"/></c:if>  
						    <c:if test="${s.count==3}"><c:set var="phoneNumber3" value="${s1}"/></c:if>            
						    </c:forEach> 
								<input type="text" class="itext" name="phone1" value="${phoneNumber1}"> -
								<input type="text" class="itext" name="phone2" value="${phoneNumber2}"> -
								<input type="text" class="itext" name="phone3" value="${phoneNumber3}">
							</td>

						</tr>
						<tr>
							<th><div class="icon"> 주소지</div></th>
							<td>
							<select onchange="searchArea(this.form);" name="areaSido">
								<option value="0">=선택=</option>
								<c:forEach items="${location}" var="it" >
									<option value="${it.area_sido}" ${it.area_sido == company.sido ? 'selected=\"selected\"' : ''} >${it.area_sido}</option>								
								</c:forEach>		
							</select>
					
							<select id="areaGugun" name="areaGugun">
								<option value="">== 검색 ==</option>
							</select>
							<input type="text" class="itext" name="address" value="${company.address}" ></td>
						</tr>
						<tr>
							<th><div class="icon"> 소개글</div></th>
							<td><input type="text" class="itext" name=introduce  value="${company.introduce}"></td>
						</tr>
						<tr>
							<th><div class="icon"> 가맹여부</div></th>
							<td>
								<input type="radio" name="isMember" value="1">가맹
								<input type="radio" name="isMember" value="0" checked>일반
							</td>
						</tr>
						<tr>
							<th><div class="icon"> 적립율</div></th>
							<td> 현금 <input type="text" class="itext" name="saveCash"  value="${company.saveCash}" style="margin-left:10px;"></td>
							<td> 신용카드 <input type="text" class="itext" name="saveCard"  value="${company.saveCard}" style="margin-left:10px;"></td>
							<td> 다모인카드 <input type="text" class="itext" name="saveDamoin"  value="${company.saveDamoin}" style="margin-left:10px;"></td>
						</tr>
						<tr>
							<th><div class="icon"> 배달여부</div></th>
							<td>
								<input type="radio" name="isDelivery" value="1">배달가능
								<input type="radio" name="isDelivery" value="0" checked>불가
							</td>
						</tr>
						<tr>
							<th><div class="icon"> 영업시간</div></th>
							<td>
							<input type="text" class="itext" name="openTime" value="${company.openTime}"> ~ 
							<input type="text" class="itext" name="closeTime" value="${company.closeTime}">
							</td>
						</tr>
						<tr>
							<th><div class="icon"> 휴무일</div></th>
							<td><input type="text" class="itext" name="holiday" value="${company.holiday}"></td>
						</tr>
						<tr>
							<th><div class="icon"> 메뉴여부</div></th>
							<td>
								<input type="radio" name="isMenu" value="1" <c:if test="${company.isMenu eq 1}">checked="checked"</c:if> >메뉴
								<input type="radio" name="isMenu" value="2" <c:if test="${company.isMenu eq 2}">checked="checked"</c:if>>상품
								<input type="radio" name="isMenu" value="0" <c:if test="${company.isMenu eq 0}">checked="checked"</c:if>>사용안함
							</td>
						</tr>
						<tr>
							<th><div class="icon"> 업체위치</div></th>
							<td> 위도<input type="text" class="itext" name="latitude"  value="${company.latitude}" style="margin-left:10px;"></td>
							<td> 경도<input type="text" class="itext" name="longitude"  value="${company.longitude}" style="margin-left:10px;"></td>
						</tr>
						</table>	
					
					</div>
			
				</div>
				
				<div class="btn-tools">
					<button type="submit" class="btn">업체 등록</button>
					<button type="button" class="btn" onclick="document.location.href='/admin/firm/firm.go';">목록</button>
				</div>
				</form>	
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>