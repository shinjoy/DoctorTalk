<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> -->
<!--[if IE 6]><html lang="ko" class="no-js old ie6"><![endif]-->
<!--[if IE 7]><html lang="ko" class="no-js old ie7"><![endif]-->
<!--[if IE 8]><html lang="ko" class="no-js old ie8"><![endif]-->
<!--[if IE 9]><html lang="ko" class="no-js ie9"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html lang="ko" class="no-js modern"><!--<![endif]-->

<% request.setCharacterEncoding("utf-8"); %>
<%
 response.setHeader("Cache-Control","no-cache"); 
 response.setHeader("Pragma","no-cache"); 
 response.setDateHeader("Expires",0); 
%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta http-equiv="Content-Script-Type" content="text/javascript" />
	<meta http-equiv="Content-Style-Type" content="text/css" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>Doctortalk</title>
	
	<link rel="stylesheet" type="text/css" href="/css/bb-1.0.2.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-form-1.0.2.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-popup-1.0.1.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-popup-2.0.1.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-tab-1.0.1.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-table-1.0.2.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-admin-1.0.3.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-calendar-1.0.1.css" />

	<script type="text/javascript" src="/lib/bestist/big-button-0.0.1.js"></script>
	<script type="text/javascript" src="/lib/bestist/big-button-form-0.0.1.js"></script>
	<script type="text/javascript" src="/lib/bestist/big-button-jquery-0.0.1.js"></script>
	<script type="text/javascript" src="/lib/bestist/jquery.leanModal.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js" type="text/javascript"></script>
	
	<!-- 달력 레이어 -->
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/lib/jquery/jquery.ui.datepicker-ko.js"></script>

	<!-- DIV 스크롤 -->
	<script type="text/javascript" src="/lib/iscroll/ender.js"></script>
	<script type="text/javascript" src="/lib/iscroll/iscroll.js"></script>
	<script type="text/javascript" src="/lib/iscroll/iscroll-lite.js"></script>

	<script type="text/javascript" src="/lib/jquery/jquery.form.js"></script>


<style>
	body { padding:20px; background-color:#fff; }
	h1 {font-weight:bold;font-size:14px;}
	ol.my-form li {
		list-style:decimal;
		font-weight:bold;
		font-size:16px;
		margin-left:30px;
	}
	.div-title { clear:both; border-bottom:1px solid #000; font-size:16px; font-weight:bold; margin-top:20px; color:#f40; padding-top:30px; }
	.api { min-width:1100px; clear:both; margin-top:20px; }
	.api dt { width:200px; }
	.api .in { float:left; }
	.api .out { float:left; width:600px; }
	.api .in dt { clear:left; float:left; }
	.api .in dd { float:left; }
	.result-view { border:1px solid #aaa; padding:10px; clear:both; word-break:break-all; }
	.real { color:#f40; min-height:26px; }
	input { width:264px !important; }
</style>

<script>

	$(document).ready(function() {
		/* 폼 ajax전송 : http://malsup.com/jquery/form/#ajaxForm */
		var options = {
			//target :		'#user-join-result',
			beforeSubmit :	formCheck,
			success :		formSuccess
		};
		$('#user-login').ajaxForm(options);
		$('#user-join').ajaxForm(options);
		$('#aimmed-check').ajaxForm(options);
		$('#check-id').ajaxForm(options);
		$('#check-phone').ajaxForm(options);
		$('#search-Id').ajaxForm(options);
		$('#search-Pw').ajaxForm(options);
		$('#new-Pw').ajaxForm(options);
		$('#maga-list').ajaxForm(options);
		$('#maga-health').ajaxForm(options);
		$('#maga-report').ajaxForm(options);
		$('#cvrisk-list').ajaxForm(options);
		$('#medi-list').ajaxForm(options);
		$('#medi-edit-do').ajaxForm(options);
		$('#medi-delete').ajaxForm(options);
		$('#pre-list').ajaxForm(options);
		$('#pre-edit-do').ajaxForm(options);
		$('#pre-photo-add').ajaxForm(options);
		$('#pre-delete').ajaxForm(options);
		$('#myinfo').ajaxForm(options);
		$('#myinfo-drop-do').ajaxForm(options);
		$('#myinfo-phone-do').ajaxForm(options);
		$('#myinfo-pass-do').ajaxForm(options);
		$('#myinfo-med').ajaxForm(options);
		$('#myinfo-med-edit').ajaxForm(options);
		$('#service-list').ajaxForm(options);
		$('#service-detail').ajaxForm(options);
		$('#service-qna-detail').ajaxForm(options);
		$('#profile-photo-add').ajaxForm(options);
		$('#profile-photo-delete').ajaxForm(options);
		$('#myinfo-med').ajaxForm(options);
		$('#myinfo-med-edit').ajaxForm(options);
		$('#management').ajaxForm(options);
		$('#management-do').ajaxForm(options);
		$('#med-exam').ajaxForm(options);
		$('#med-exam-do').ajaxForm(options);
		$('#poindo').ajaxForm(options);
		$('#pointer-bloodinsert').ajaxForm(options);
		$('#pointer-pressinsert').ajaxForm(options);
		$('#pointer-colinsert').ajaxForm(options);
		$('#pointer-weinsert').ajaxForm(options);
		$('#pointer-hbinsert').ajaxForm(options);
		$('#pointer-result').ajaxForm(options);
		$('#period').ajaxForm(options);
		$('#period-into').ajaxForm(options);
		$('#period-edit').ajaxForm(options);
		$('#b-edit').ajaxForm(options);
		$('#p-edit').ajaxForm(options);
		$('#period-err').ajaxForm(options);
		$('#cvrisk').ajaxForm(options);
		$('#cvrisk-cal').ajaxForm(options);
		$('#cvrisk-insert').ajaxForm(options);
		$('#daycontents').ajaxForm(options);
		$('#weekcontents').ajaxForm(options);
		$('#week-intro').ajaxForm(options);
		$('#week-first').ajaxForm(options);
		$('#week-blooddetail').ajaxForm(options);
		$('#week-blood').ajaxForm(options);
		$('#week-press').ajaxForm(options);
		$('#week-weight').ajaxForm(options);
		$('#week-result').ajaxForm(options);
		$('#monthcontents').ajaxForm(options);
		$('#magareport').ajaxForm(options);
		$('#month-result').ajaxForm(options);
		$('#chart-blood').ajaxForm(options);
		$('#chart-pressure').ajaxForm(options);
		$('#chart-weight').ajaxForm(options);
		$('#chart-col').ajaxForm(options);
		$('#chart-hba').ajaxForm(options);
		$('#delete-blood').ajaxForm(options);
		$('#delete-pressure').ajaxForm(options);
		$('#delete-weight').ajaxForm(options);
		$('#delete-col').ajaxForm(options);
		$('#delete-hba').ajaxForm(options);
		$('#g-blood').ajaxForm(options);
		$('#g-pressure').ajaxForm(options);
		$('#g-weight').ajaxForm(options);
		$('#g-col').ajaxForm(options);
		$('#g-hba').ajaxForm(options);photoForm
		$('#photoForm').ajaxForm(options);
		$('#test').ajaxForm(options);
		$('#test2').ajaxForm(options);
		$('#test3').ajaxForm(options);
		$('#aimmed_check_user').ajaxForm(options);
		$('#aimmed_send_sms').ajaxForm(options);
		
		$('#medi_eat_check').ajaxForm(options);
		
		$('#save_data').ajaxForm(options);
		$('#chat_counselor').ajaxForm(options);
		$('#photo_upload').ajaxForm(options);
		$('#now_version').ajaxForm(options);
		$('#counsel-rev').ajaxForm(options);
		$('#service').ajaxForm(options);
		$('#send_push').ajaxForm(options);
		$('#send_push_test').ajaxForm(options);
		$('#clear_noticeBadge').ajaxForm(options);
		$('#chart_comment').ajaxForm(options);
		$('#chart_commnet_view').ajaxForm(options);
		$('#badge').ajaxForm(options);
		$('#badge_read').ajaxForm(options);

		$('#chat_start').ajaxForm(options);
		$('#chat_finish').ajaxForm(options);
		$('#medi-list2').ajaxForm(options);

		$('#server-time').ajaxForm(options);
		$('#report_history_test').ajaxForm(options);

		
	});
	
	function formCheck(formData, jqForm, options) {
		$("#"+resultDiv+"-result").html("");
		return true; 
	}
	function formSuccess(responseText, statusText, xhr, $form) {
		//alert('status: ' + statusText + '\n\nresponseText: \n' + responseText );
		var json = JSON.parse(responseText);
		try {
			$("#"+resultDiv+"-result").html(responseText);
		} catch (e) {
            alert(json.message); 
		}
	}

	var resultDiv;
	function formSubmit(div) {
		resultDiv = div;
	}

</script>

</head>
<body>

<h1 class="ad_title">DoctorTalk Server API</h1>

<div class="div-title">
	회원관리
</div>

<ol class="my-form">
	
	
	<div class="api">
		<form method="post" id="user-login" name="userLoginForm" action="/m/login.go">
			<li>모바일 로그인</li>
			<h1 class="page-title">/m/login.go</h1>
			<dl class="in">
				<dt>userId</dt> <dd><input type="text" name="userId" placeholder="이메일" class="bb"></dd>
				<dt>password</dt> <dd><input type="password" name="password" placeholder="password" class="bb"></dd>
				<dt>os_version</dt> <dd><input type="text" name="osVersion" placeholder="os_version" class="bb"></dd>
				<dt>os_type</dt> <dd><input type="text" name="osType" placeholder="osTyp" class="bb"></dd>
				<dt>device_name</dt> <dd><input type="text" name="deviceName" placeholder="deviceName" class="bb"></dd>
				<dt>device_id</dt> <dd><input type="text" name="deviceId" placeholder="deviceId" class="bb"></dd>
				<dt>pushKey</dt> <dd><input type="text" name="pushKey" placeholder="pushKey" class="bb"></dd>
				<dt>loginNaver</dt> <dd><input type="text" name="loginNaver" placeholder="1:네이버로 로그인, 0" class="bb"></dd>
				<dt>loginKakao</dt> <dd><input type="text" name="loginKakao" placeholder="1:카카오로 로그인, 0" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('user-login');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"로그인이 성공되었습니다.","result":true}<br>
					{"message":"해당 아이디는 존재하지 않습니다.","result":false}<br>
					{"message":"비밀번호가 일치하지 않습니다.","result":false}
				</div>
				<div id="user-login-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="photoForm" name="photoForm" action="/m/profile_photo.go">
			<li>이미지 업데이트</li>
			<h1 class="page-title">/m/profile_photo.go</h1>
			<dl class="in">
				<dt>userId</dt> <dd><input type="text" name="userId" placeholder="이메일" class="bb"></dd>
				<dt>fileName</dt> <dd><input type="text" name="fileName" placeholder="fileName" class="bb"></dd>
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('photoForm');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				</div>
				<div id="photoForm-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="aimmed-check" name="aimmedCheckForm" action="/m/aimmed_check.go">
			<li>리엑트, 리본 사용자 체크</li>
			<h1 class="page-title">/m/aimmed_check.go</h1>
			<dl class="in">
				<dt>userName</dt>	<dd><input type="text" name="userName" placeholder="userName" class="bb"></dd>
				<dt>phoneNumber</dt>	<dd><input type="text" name="phoneNumber" placeholder="phoneNumber" class="bb"></dd>
				<dt>birthday</dt>		<dd><input type="text" name="birthday" placeholder="birthday" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('aimmed-check');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"에임메드 회원입니다.","groupName":"홍길동","aimmedId":"redeastload","isAimmedUser":1,"groupCode":"123456"}<br>
					{"message":"에임메드 회원이 아닙니다.","groupName":"","aimmedId":"","isAimmedUser":0,"groupCode":""}
				</div>
				<div id="aimmed-check-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="user-join" name="userJoinForm" action="/m/join.go">
			<li>회원가입</li>
			<h1 class="page-title">/m/join.go</h1>
			<dl class="in">
				<dt>userId</dt>		<dd><input type="text" name="userId" placeholder="이메일" class="bb"></dd>
				<dt>password</dt>		<dd><input type="password" name="password" placeholder="password" class="bb"></dd>
				<dt>userName</dt>	<dd><input type="text" name="userName" placeholder="userName" class="bb"></dd>
				<dt>phoneNumber</dt>	<dd><input type="text" name="phoneNumber" placeholder="phoneNumber" class="bb"></dd>
				<dt>loginNaver</dt>	<dd><input type="text" name="loginNaver" placeholder="0:사용안함 1:사용" class="bb"></dd>
				<dt>loginKakao</dt>	<dd><input type="text" name="loginKakao" placeholder="0:사용안함 1:사용" class="bb"></dd>
				<dt>gender</dt>	<dd><dd><input type="text" name="gender" placeholder="1:남자 2:여자" class="bb"></dd>
				<dt>appVersion</dt>	<dd><input type="text" name="appVersion" placeholder="appVersion" class="bb"></dd>
				<dt>photo</dt>	<dd><input type="text" name="fileName"  class="bb"></dd>
				 <dt>osType</dt>	<dd><input type="text" name="osType" placeholder="osType" class="bb"></dd>
				<dt>osversion</dt>		<dd><input type="text" name="osversion" placeholder="osversion" class="bb"></dd>
				<dt>deviceName</dt>		<dd><input type="text" name="deviceName" placeholder="deviceName" class="bb"></dd>
				<dt>deviceId</dt>		<dd><input type="text" name="deviceId" placeholder="deviceId" class="bb"></dd>
				<dt>pushKey</dt>		<dd><input type="text" name="pushKey" placeholder="pushKey" class="bb"></dd>
				<dt>birthday</dt>		<dd><input type="text" name="birthday" placeholder="birthday" class="bb"></dd>
				<dt>usePushservice</dt><dd><input type="text" name="usePushservice" placeholder="0:사용안함 1:사용" class="bb"></dd>
				<dt>userType</dt>		<dd><input type="text" name="userType" placeholder="1: 최고관리자, 2:상담자, 3:일반회원" class="bb"></dd>
				<dt>aimmedId</dt>		<dd><input type="text" name="aimmedId" placeholder="리액트,리본계정" class="bb"></dd>
				<dt>groupCode</dt>		<dd><input type="text" name="groupCode" placeholder="에임메드 그룹코드" class="bb"></dd>
				<dt>groupName</dt>		<dd><input type="text" name="groupName" placeholder="에임메드 그룹이름" class="bb"></dd>
				<dt>&nbsp;</dt>		<dd><button type="submit" onclick="formSubmit('user-join');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"회원가입되었습니다.","result":true}<br>
					{"message":"존재하는 아이디 입니다.","result":false}
				</div>
				<div id="user-join-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="photo_upload" name="photo_upload" action="/m/photo_upload.go" enctype="multipart/form-data">
			<li>사진 업로드</li>
			<h1 class="page-title">/m/photo_upload.go</h1>
			<dl class="in">
				<dt>type</dt>				<dd><input type="text" name="type" placeholder="chat" class="bb"></dd>
				<dt>isThumb</dt>			<dd><input type="text" name="isThumb" placeholder="0:원본 1:썸네일" class="bb"></dd>
				<dt>addThumb(웹전용)</dt>	<dd><input type="text" name="addThumb" placeholder="0:저장안함 1:섬네일저장" class="bb"></dd>
				<dt>userId</dt>				<dd><input type="text" name="userId" placeholder="등록자ID" class="bb"></input></dd>
				<dt>file</dt>				<dd><input type="file" name="file" class="bb"></dd>
				<dt>&nbsp;</dt>				<dd><button type="submit" onclick="formSubmit('photo_upload');" class="bb round green" style="width:274px;">확인</button></dd>
				<dt>&nbsp;</dt>				<dd>type - 채팅:chat</dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"사진이 등록되었습니다.","result":true,"path":"/files/chat/201511/","orgFileName":"","photo":"3143420151116005447.jpg"}
					<br>
				</div>
				<div id="photo_upload-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="photo-add" name="photo-add" action="/m/photo_add.go" enctype="multipart/form-data">
			<li>프로필이미지</li>
			<h1 class="page-title">/m/photo_add.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId"  class="bb"></dd>
				<dt>photo</dt>	 <dd><input type="file" name="filename"  class="bb"></dd>
				<dt>path(프로필 -profile / 처방전-prescription)</dt>	 <dd><input type="text" name="path"  class="bb" ></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('profile-photo-add');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result",true"message", "사진이 등록되었습니다."}
					{"false", true"message", "사진 등록에 실패하였습니다.\n"+e.getMessage()"}
					{photo}
					<br>
				</div>
				<div id="profile-photo-add-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="profile-photo-delete" name="profile-photo-delete" action="/m/profile_photo_delete.go" >
			<li>프로필이미지삭제</li>
			<h1 class="page-title">/m/profile_photo_delete.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId"  class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('profile-photo-delete');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"프로필 이미지가 삭제되었습니다.","result":true}<br>
					{"message":"존재하지 않는 ID 입니다.","result":false}<br>
					{"message":"파일삭제실패.","result":false}
					<br>
				</div>
				<div id="profile-photo-delete-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	
	
	<div class="api">
		<form method="post" id="check-id" name="checkIdForm" action="/m/dup_check_id.go">
			<li>아이디중복체크</li>
			<h1 class="page-title">/m/dup_check_id.go</h1>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="이메일" class="bb"></dd>
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('check-id');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"중복된 이메일 주소입니다 다른주소로 입력해주세요.
					       지속적인 문제 발생시 (관리자이메일)로 이메일을 보내주세요","result":true}<br>
					{"message":"사용가능한 이메일입니다.","result":false}<br>
				</div>
				<div id="check-id-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="check-phone" name="checkphForm" action="/m/dup_check_phone.go">
			<li>전화번호중복체크</li>
			<h1 class="page-title">/m/dup_check_phone.go</h1>
			<dl class="in">
				<dt>phoneNumber</dt>	<dd><input type="text" name="phoneNumber" placeholder="phoneNumber" class="bb"></dd>
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('check-phone');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"사용중인 전화번호 입니다.","result":true}<br>
					{"message":"사용할 수 있는 전화번호입니다..","result":false}<br>
				</div>
				<div id="check-phone-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="search-Id" name="searchIdForm" action="/m/myid.go">
			<li>아이디 찾기</li>
			<h1 class="page-title">/m/myid.go</h1>
			<dl class="in">
				<dt>userName</dt>	<dd><input type="text" name="userName" placeholder="userName" class="bb"></dd>
				<dt>phoneNumber</dt><dd><input type="text" name="phoneNumber" placeholder="phoneNumber" class="bb"></dd>
				<dt>&nbsp;</dt>		<dd><button type="submit" onclick="formSubmit('search-Id');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{{userId},"result":true}<br>
					{"message":"회원님의 ID를 찾을 수 없습니다.아직 회원이 아니시면 아래 회원가입 버튼을 클릭해 주세요","result":false}<br>

				</div>
				<div id="search-Id-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="search-Pw" name="searchpwForm" action="/m/mypass.go">
			<li>비밀번호 찾기</li>
			<h1 class="page-title">/m/mypass.go</h1>
			<dl class="in">
				<dt>userId</dt>	    <dd><input type="text" name="userId" placeholder="이메일" class="bb"></dd>
				<dt>phoneNumber</dt><dd><input type="text" name="phoneNumber" placeholder="phoneNumber" class="bb"></dd>
				<dt>&nbsp;</dt>		<dd><button type="submit" onclick="formSubmit('search-Pw');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"비밀번호를 재설정해주세요."->비밀번호변경 페이지로,"result":true}<br>
					{"message":"회원님의 데이터를 찾을 수 없습니다.아직 회원이 아니시면 아래 회원가입 버튼을 클릭해 주세요","result":false}<br>

				</div>
				<div id="search-Pw-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
 	
	<div class="api">
		<form method="post" id="new-Pw" name="newpwForm" action="/m/new_mypass.go">
			<li>비밀번호 재설정</li>
			<h1 class="page-title">/m/new_mypass.go</h1>
			<dl class="in">
				<dt>userId</dt>	    <dd><input type="text" name="userId" placeholder="이메일" class="bb"></dd>
				<dt>password</dt> <dd><input type="password" name="password" placeholder="password" class="bb"></dd>
				<dt>&nbsp;</dt>		<dd><button type="submit" onclick="formSubmit('new-Pw');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"완료되었습니다.","result":true}<br>
					

				</div>
				<div id="new-Pw-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<br>
	<br>
	<br>
	<div class="div-title">
	  건강매거진
	</div>
	
	<div class="api">
		<form method="post" id="maga-list" name="maga-list" action="/m/maga_list.go">
			<li>건강매거진</li>
			<h1 class="page-title">/m/maga_list.go</h1>
			<dl class="in">
				<dt>userId</dt>	    <dd><input type="text" name="userId" placeholder="이메일" class="bb"></dd>
				<dt>&nbsp;</dt>		<dd><button type="submit" onclick="formSubmit('maga-list');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{{list},{joindate},"result":true}<br>
					

				</div>
				<div id="maga-list-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="maga-health" name="maga-health" action="/m/maga_health.go">
			<li>매거진</li>
			<h1 class="page-title">/m/maga_health.go</h1>
			<dl class="in">
				<dt>magaSeq</dt>	    <dd><input type="text" name="magaSeq" placeholder="magaSeq" class="bb"></dd>
				<dt>kind</dt>	    <dd><input type="text" name="kind" placeholder="0:상위 1:하위" class="bb"></dd>
				<dt>&nbsp;</dt>		<dd><button type="submit" onclick="formSubmit('maga-health');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{{topmagazine},{submagazine}}<br>
					

				</div>
				<div id="maga-health-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="maga-report" name="maga-reportForm" action="/m/maga_report.go">
			<li>수행내역리포트</li>
			<h1 class="page-title">/m/maga_report.go</h1>
			<dl class="in">
				<dt>userId</dt>	    <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>ym</dt>	    <dd><input type="text" name="month" placeholder="해당년월" class="bb"></dd>
				<dt>&nbsp;</dt>		<dd><button type="submit" onclick="formSubmit('maga-report');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{blood}
					{pressure}
					{weight}
					{goaldata}<br>
					

				</div>
				<div id="maga-report-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="cvrisk-list" name="cvrisk-list" action="/m/cvrisk_list.go">
			<li>심뇌혈관질환 위험도</li>
			<h1 class="page-title">/m/cvrisk_list.go</h1>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('cvrisk-list');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}
					<br>
				</div>
				<div id="cvrisk-list-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>

	<div class="div-title">
	  복약관리
	</div>
	
	<div class="api">
		<form method="post" id="medi-list" name="medi-list" action="/m/medi_list.go">
			<li>복약관리</li>
			<h1 class="page-title">/m/medi_list.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>page</dt>	 <dd><input type="text" name="page" placeholder="page" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('medi-list');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}
					<br>
				</div>
				<div id="medi-list-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="medi-list2" name="medi-list2" action="/m/medi_list2.go">
			<li>복약목록</li>
			<h1 class="page-title">/m/medi_list2.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('medi-list2');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}
					<br>
				</div>
				<div id="medi-list2-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="medi-edit-do" name="medi-edit-do" action="/m/medi_edit_do.go">
			<li>복약알림등록</li>
			<h1 class="page-title">/m/medi_edit_do.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>mediname</dt>	 <dd><input type="text" name="mediname" placeholder="mediname" class="bb"></dd>
				<dt>meditime</dt>	 <dd><input type="text" name="meditime" placeholder="meditime" class="bb"></dd>
				<dt>medialert</dt>	 <dd><input type="text" name="medialert" placeholder="(0:알림안함 1: 알림)" class="bb"></dd>
				<dt>mediweek1</dt>	 <dd><input type="text" name="mediweek1" placeholder="일-1:설정, 0:해제" class="bb"></dd>
				<dt>mediweek2</dt>	 <dd><input type="text" name="mediweek2" placeholder="월-1:설정, 0:해제" class="bb"></dd>
				<dt>mediweek3</dt>	 <dd><input type="text" name="mediweek3" placeholder="화-1:설정, 0:해제" class="bb"></dd>
				<dt>mediweek4</dt>	 <dd><input type="text" name="mediweek4" placeholder="수-1:설정, 0:해제" class="bb"></dd>
				<dt>mediweek5</dt>	 <dd><input type="text" name="mediweek5" placeholder="목-1:설정, 0:해제" class="bb"></dd>
				<dt>mediweek6</dt>	 <dd><input type="text" name="mediweek6" placeholder="금-1:설정, 0:해제" class="bb"></dd>
				<dt>mediweek7</dt>	 <dd><input type="text" name="mediweek7" placeholder="토-1:설정, 0:해제" class="bb"></dd>
				<dt>medihospital</dt>	 <dd><input type="text" name="medihospital" placeholder="병원" class="bb"></dd>
				<dt>mediSeq</dt>	 <dd><input type="text" name="mediSeq" placeholder="(0:신규등록 이하:수정)" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('medi-edit-do');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result", true"message", "완료되었습니다."}
					<br>
				</div>
				<div id="medi-edit-do-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="medi-delete" name="medi-delete" action="/m/medi_delete.go">
			<li>복약알림삭제</li>
			<h1 class="page-title">/m/medi_delete.go</h1>
			<dl class="in">
				<dt>medSeq</dt>	 <dd><input type="text" name="medSeq" placeholder="medSeq" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('medi-delete');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{result:true msg:삭제되었습니다}
					<br>
				</div>
				<div id="medi-delete-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="medi_eat_check" name="medi-delete" action="/m/medi_eat_check.go">
			<li>복약기록</li>
			<h1 class="page-title">/m/medi_eat_check.go</h1>
			<dl class="in">
				<dt>medSeq</dt>	 <dd><input type="text" name="medSeq" placeholder="medSeq" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('medi_eat_check');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{result:true msg:삭제되었습니다}
					<br>
				</div>
				<div id="medi_eat_check-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="div-title">
	  처방전
	</div>
	
	<div class="api">
		<form method="post" id="pre-list" name="pre-list" action="/m/pre_list.go">
			<li>처방전관리</li>
			<h1 class="page-title">/m/pre_list.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>page</dt>	 <dd><input type="text" name="page" placeholder="page" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('pre-list');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}
					<br>
				</div>
				<div id="pre-list-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="pre-edit-do" name="pre-edit-do" action="/m/pre_edit_do.go">
			<li>처방전등록</li>
			<h1 class="page-title">/m/pre_edit_do.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>preSeq</dt>	 <dd><input type="text" name="preSeq" placeholder="(0:신규등록 이하:수정)" class="bb"></dd>
				<dt>hosName</dt>	 <dd><input type="text" name="hosName" placeholder="hosName" class="bb"></dd>
				<dt>comment</dt>	 <dd><input type="text" name="comment" placeholder="comment" class="bb"></dd>
				<dt>fileName</dt>	 <dd><input type="text" name="fileName" placeholder="fileName" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('pre-edit-do');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result", true"message", "완료되었습니다."}
					<br>
				</div>
				<div id="pre-edit-do-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
<!-- 	
	<div class="api">
		<form method="post" id="pre-photo-add" name="pre-photo-add" action="/m/pre_photo_add.go" enctype="multipart/form-data">
			<li>처방전이미지</li>
			<h1 class="page-title">/m/pre_photo_add.go</h1>
			<dl class="in">
				
				<dt>photo</dt>	 <dd><input type="file" name="filename"  class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('pre-photo-add');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result",true"message", "사진이 등록되었습니다."}
					{"false", true"message", "사진 등록에 실패하였습니다.\n"+e.getMessage()"}
					<br>
				</div>
				<div id="pre-photo-add-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div> -->
	
	<div class="api">
		<form method="post" id="pre-delete" name="pre-delete" action="/m/pre_delete.go">
			<li>처방전삭제</li>
			<h1 class="page-title">/m/pre_delete.go</h1>
			<dl class="in">
			
				<dt>preSeq</dt>	 <dd><input type="text" name="preSeq" placeholder="preSeq" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('pre-delete');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{result:true msg:삭제되었습니다}
					<br>
				</div>
				<div id="pre-delete-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="div-title">
	  계정
	</div>
	
	<div class="api">
		<form method="post" id="myinfo" name="myinfo" action="/m/myinfo.go">
			<li>계정관리</li>
			<h1 class="page-title">/m/myinfo.go</h1>
			<dl class="in">
			
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('myinfo');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{data}
					<br>
				</div>
				<div id="myinfo-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="myinfo-drop-do" name="myinfo-drop-do" action="/m/myinfo_drop_do.go">
			<li>탈퇴</li>
			<h1 class="page-title">/m/myinfo_drop_do.go</h1>
			<dl class="in">
			
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>password</dt>	 <dd><input type="password" name="password" placeholder="password" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('myinfo-drop-do');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{result : false msg:비밀번호가 일치하지않습니다}
					{result : true msg:정상적으로 탈퇴 되었습니다.}
					<br>
				</div>
				<div id="myinfo-drop-do-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="myinfo-phone-do" name="myinfo-phone-do" action="/m/myinfo_phone_do.go">
			<li>휴대전화변경</li>
			<h1 class="page-title">/m/myinfo_phone_do.go</h1>
			<dl class="in">
			
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>phoneNumber</dt> <dd><input type="text" name="phoneNumber" placeholder="phoneNumber" class="bb"></dd>
				<dt>password</dt>	 <dd><input type="password" name="password" placeholder="password" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('myinfo-phone-do');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{result : false msg:비밀번호가 일치하지않습니다}
					{result : true msg:변경되었습니다.}
					<br>
				</div>
				<div id="myinfo-phone-do-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="myinfo-pass-do" name="myinfo-pass-do" action="/m/myinfo_pass_do.go">
			<li>비밀번호변경</li>
			<h1 class="page-title">/m/myinfo_pass_do.go</h1>
			<dl class="in">
			
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>password</dt>	 <dd><input type="password" name="password" placeholder="password" class="bb"></dd>
			    <dt>npassword</dt>	 <dd><input type="password" name="npassword" placeholder="npassword" class="bb"></dd> 
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('myinfo-pass-do');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{result : false msg:비밀번호가 일치하지않습니다}
					{result : true msg:변경되었습니다.}
					<br>
				</div>
				<div id="myinfo-pass-do-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="div-title">
	  문진정보
	</div>
	
	<div class="api">
		<form method="post" id="myinfo-med" name="myinfo-med" action="/m/myinfo_med.go">
			<li>문진정보</li>
			<h1 class="page-title">/m/myinfo_med.go</h1>
			<dl class="in">
			
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('myinfo-med');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{data}{result : true }
				
					<br>
				</div>
				<div id="myinfo-med-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="myinfo-med-edit" name="myinfo-med-edit" action="/m/med_exam_do.go">
			<li>문진정보수정</li>
			<h1 class="page-title">/m/med_exam_do.go</h1>
			<dl class="in">
			
				
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>height</dt>	 <dd><input type="text" name="height" placeholder="height" class="bb"></dd>
				<dt>weight</dt>	 <dd><input type="text" name="weight" placeholder="weight" class="bb"></dd>
				<dt>waist</dt>	 <dd><input type="text" name="waist" placeholder="waist" class="bb"></dd>
				<dt>smoke</dt>	 <dd><input type="text" name="smoke" placeholder="smoke" class="bb"></dd>
				<dt>blood</dt>	 <dd><input type="text" name="blood" placeholder="(빈칸 : 선택없음 blood:해당 ) " class="bb"></dd>
				<dt>press</dt>	 <dd><input type="text" name="press" placeholder="(빈칸 : 선택없음 press:해당 )" class="bb"></dd>
				<dt>col</dt>	 <dd><input type="text" name="col" placeholder="(빈칸 : 선택없음 col:해당 )"  class="bb"></dd>
				<dt>heiwieght</dt>	 <dd><input type="text" name="heiwieght" placeholder="(빈칸 : 선택없음 heiwieght:해당 )"  class="bb"></dd>
				<dt>bmi</dt>	 <dd><input type="text" name="bmi" placeholder="bmi"  class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('myinfo-med-edit');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{result : false msg:에러}
					{result : true msg:변경되었습니다.}
					<br>
				</div>
				<div id="myinfo-med-edit-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="div-title">
	  공지사항&도움말
	</div>

	
	<div class="api">
		<form method="post" id="service-list" name="service-list" action="/m/service_list.go">
			<li>공지사항(n)</li>
			<h1 class="page-title">/m/service_list.go</h1>
			<dl class="in">
				<dt>page</dt>	 <dd><input type="text" name="page" placeholder="page" class="bb"></dd>
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('service-list');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{data}{cnt}
					<br>
				</div>
				<div id="service-list-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	
	<div class="api">
		<form method="post" id="service-detail" name="service-detail" action="/m/service_detail.go">
			<li>공지사항 상세(n)</li>
			<h1 class="page-title">/m/service_detail.go</h1>
			<dl class="in">
				<dt>noticeSeq</dt>	 <dd><input type="text" name="noticeSeq" placeholder="noticeSeq" class="bb"></dd>
				
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('service-detail');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{data}
					<br>
				</div>
				<div id="service-detail-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	
	<div class="api">
		<form method="post" id="service-qna-detail" name="service-qna-detail" action="/m/service_qna_detail.go">
			<li>도움말(n)</li>
			<h1 class="page-title">/m/service_qna_detail.go</h1>
			<dl class="in">
				<dt>noticeSeq</dt>	 <dd><input type="text" name="noticeSeq" placeholder="noticeSeq" class="bb"></dd>
				
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('service-qna-detail');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{data}
					<br>
				</div>
				<div id="service-detail-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>		

	<div class="div-title">
	  문진
	</div>
	<div class="api">
		<form method="post" id="med-exam" name="med-exam" action="/m/med_exam.go">
			<li>문진</li>
			<h1 class="page-title">/m/med_exam.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>kind</dt>	 <dd><input type="text" name="kind" placeholder="(1:문진)" class="bb"></dd>
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('med-exam');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}
					<br>
				</div>
				<div id="med-exam-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
		

	<div class="api">
		<form method="post" id="med-exam-do" name="med-exam-do" action="/m/med_exam_do.go">
			<li>문진등록</li>
			<h1 class="page-title">/m/med_exam_do.go</h1>
			<dl class="in">
	   		<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>height</dt>	 <dd><input type="text" name="height" placeholder="height" class="bb"></dd>
				<dt>weight</dt>	 <dd><input type="text" name="weight" placeholder="weight" class="bb"></dd>
				<dt>waist</dt>	 <dd><input type="text" name="waist" placeholder="waist" class="bb"></dd>
				<dt>smoke</dt>	 <dd><input type="text" name="smoke" placeholder="0:비흡연 1:흡연" class="bb"></dd>
				<dt>gender</dt>	 <dd><input type="text" name="gender" placeholder="1:남자 2:여자" class="bb"></dd>
				<dt>blood</dt>	 <dd><input type="text" name="blood" placeholder="(빈칸 : 선택없음 blood:해당 ) " class="bb"></dd>
				<dt>press</dt>	 <dd><input type="text" name="press" placeholder="(빈칸 : 선택없음 press:해당 )" class="bb"></dd>
				<dt>col</dt>	 <dd><input type="text" name="col" placeholder="(빈칸 : 선택없음 col:해당 )"  class="bb"></dd>
				<dt>heiwieght</dt>	 <dd><input type="text" name="heiwieght" placeholder="(빈칸 : 선택없음 heiwieght:해당 )"  class="bb"></dd>
				<dt>bmi</dt>	 <dd><input type="text" name="bmi" placeholder="bmi"  class="bb"></dd>
				<dt>mediname</dt>	 <dd><input type="text" name="mediname" placeholder="mediname" class="bb"></dd>
				<dt>meditime</dt>	 <dd><input type="text" name="meditime" placeholder="meditime" class="bb"></dd>
				<dt>medialert</dt>	 <dd><input type="text" name="medialert" placeholder="(0:알림안함 1: 알림)" class="bb"></dd> 
				
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('med-exam-do');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					("result", true);
					("msg","등록완료");
					<br>
				</div>
				<div id="med-exam-do-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
 	<div class="div-title">
	  관리목표설정
	</div>
	
	<div class="api">
		<form method="post" id="management" name="management" action="/m/management.go">
			<li>관리목표설정안내(해당질병을 채워보내줘야함)</li>
			<h1 class="page-title">/m/management.go"</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>blood</dt>	 <dd><input type="text" name="blood" placeholder="(빈칸 : 선택없음 blood:해당 ) " class="bb"></dd>
				<dt>press</dt>	 <dd><input type="text" name="press" placeholder="(빈칸 : 선택없음 press:해당 )" class="bb"></dd>
				<dt>col</dt>	 <dd><input type="text" name="col" placeholder="(빈칸 : 선택없음 col:해당 )"  class="bb"></dd>
				<dt>weight</dt>	 <dd><input type="text" name="weight" placeholder="(빈칸 : 선택없음 weight:해당 )"  class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('management');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					("blood", ug)("press", ug)("col", ug)("weight", ug)
					<br>
				</div>
				<div id="management-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>		

	<!-- <div class="api">
		<form method="post" id="management-do" name="management-do" action="/m/management_do.go">
			<li>관리목표설정</li>
			<h1 class="page-title">/m/management_do.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>blood</dt>	 <dd><input type="text" name="blood" placeholder="(빈칸 : 선택없음 blood:해당 ) " class="bb"></dd>
				<dt>press</dt>	 <dd><input type="text" name="press" placeholder="(빈칸 : 선택없음 press:해당 )" class="bb"></dd>
				<dt>col</dt>	 <dd><input type="text" name="col" placeholder="(빈칸 : 선택없음 col:해당 )"  class="bb"></dd>
				<dt>heiwieght</dt>	 <dd><input type="text" name="heiwieght" placeholder="(빈칸 : 선택없음 heiwieght:해당 )"  class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('management-do');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				("result", true);
			 	("msg","등록완료");
				{data}
					<br>
				</div>
				<div id="management-do-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
 -->
	<div class="div-title">
	  지표관리
	</div>	
	<div class="api">
		<form method="post" id="poindo" name="poindo" action="/m/pointer_do.go">
			<li>지표관리설정(혈당:blood , 혈압:press, 콜레스테롤:col,당화혈색소:hba,체중:weight)</li>
			<h1 class="page-title">/m/pointer_do.go</h1>
			<dl class="in">
				<dt>diseaseId</dt>	 <dd><input type="text" name="diseaseId" placeholder="" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('poindo');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
					("result", true);
				 	{data};
					<br>
				</div>
				<div id="poindo-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
		
	<div class="api">
		<form method="post" id="pointer-bloodinsert" name="pointer-bloodinsert" action="/m/pointer_bloodinsert.go">
			<li>혈당등록</li>
			<h1 class="page-title">/m/pointer_bloodinsert.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>bloodTime</dt>	 <dd><input type="text" name="bloodTime" placeholder="측정시간 " class="bb"></dd>
				<dt>bloodKind</dt>	 <dd><input type="text" name="bloodKind" placeholder="종류" class="bb"></dd>
				<dt>bloodNum</dt>	 <dd><input type="text" name="bloodNum" placeholder="혈당숫자"  class="bb"></dd>
				<dt>regDate</dt>	 <dd><input type="text" name="regDate" placeholder="기기등록시 측정일시"  class="bb"></dd>
				<dt>equip</dt>	 <dd><input type="text" name="equip" placeholder="기기등록여부(0안함1함)" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('pointer-bloodinsert');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				("result", true);
			 	("msg","등록완료");
			
					<br>
				</div>
				<div id="pointer-bloodinsert-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
		<div class="api">
		<form method="post" id="pointer-pressinsert" name="pointer-pressinsert" action="/m/pointer_pressinsert.go">
			<li>혈압등록</li>
			<h1 class="page-title">/m/pointer_pressinsert.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>pulse</dt>	 <dd><input type="text" name="pulse" placeholder="맥박" class="bb"></dd>
				<dt>splessure</dt>	 <dd><input type="text" name="splessure" placeholder="수축기수치" class="bb"></dd>
				<dt>dplessure</dt>	 <dd><input type="text" name="dplessure" placeholder="이완기수치"  class="bb"></dd>
				<dt>equip</dt>	 <dd><input type="text" name="equip" placeholder="기기등록여부(0안함1함)" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('pointer-pressinsert');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				("result", true);
			 	("msg","등록완료");
			
					<br>
				</div>
				<div id="pointer-pressinsert-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="pointer-colinsert" name="pointer-colinsert" action="/m/pointer_colinsert.go">
			<li>콜레스테롤등록</li>
			<h1 class="page-title">/m/pointer_colinsert.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>col</dt>	 <dd><input type="text" name="col" placeholder="총콜레스테롤" class="bb"></dd>
				<dt>ldl</dt>	 <dd><input type="text" name="ldl" placeholder="저밀도" class="bb"></dd>
				<dt>tg</dt>	 <dd><input type="text" name="tg" placeholder="중성지방"  class="bb"></dd>
				<dt>hdl</dt>	 <dd><input type="text" name="hdl" placeholder="고밀도"  class="bb"></dd>
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('pointer-colinsert');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				("result", true);
			 	("msg","등록완료");
			
					<br>
				</div>
				<div id="pointer-colinsert" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
		
	<div class="api">
		<form method="post" id="pointer-weinsert" name="pointer-weinsert" action="/m/pointer_weinsert.go">
			<li>체중등록</li>
			<h1 class="page-title">/m/pointer_weinsert.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>weightNum</dt>	 <dd><input type="text" name="weightNum" placeholder="weightNum" class="bb"></dd>
				<dt>bmi</dt>	 <dd><input type="text" name="bmi" placeholder="bmi" class="bb"></dd>
				<dt>bbmi</dt>	 <dd><input type="text" name="bbmi" placeholder="체지방입니다!!!" class="bb"></dd>
				<dt>tbw</dt>	 <dd><input type="text" name="tbw" placeholder="체수분" class="bb"></dd>
				<dt>muscle</dt>	 <dd><input type="text" name="muscle" placeholder="근육" class="bb"></dd>
				<dt>bmd</dt>	 <dd><input type="text" name="bmd" placeholder="골량" class="bb"></dd>
				<dt>equip</dt>	 <dd><input type="text" name="equip" placeholder="기기등록여부(0안함1함)" class="bb"></dd>
						
				
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('pointer-weinsert');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				("result", true);
			 	("msg","등록완료");
			
					<br>
				</div>
				<div id="pointer-weinsert-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	

	<div class="api">
		<form method="post" id="pointer-hbinsert" name="pointer-hbinsert" action="/m/pointer_hbinsert.go">
			<li>당화혈색소등록</li>
			<h1 class="page-title">/m/pointer_hbinsert.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>hbaNum</dt>	 <dd><input type="text" name="hbaNum" placeholder="hbaNum" class="bb"></dd>
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('pointer-hbinsert');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				("result", true);
			 	("msg","등록완료");
			
					<br>
				</div>
				<div id="pointer-hbinsert-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	

	
 	<div class="api">
		<form method="post" id="pointer-result" name="pointer-result" action="/m/pointer_result.go">
			<li>지표결과</li>
			<h1 class="page-title">/m/pointer_result.go</h1>
			<dl class="in">
				<dt>commentCode</dt> <dd><input type="text" name="commentCode" placeholder="코멘트번호"  class="bb"></dd>
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('pointer-result');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{data};
					<br>
				</div>
				<div id="pointer-result-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div> 
		<div class="api">
		<form method="post" id="period" name="period" action="/m/period.go">
			<li>관리주기안내</li>
			<h1 class="page-title">/m/period.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>kind</dt> <dd><input type="text" name="kind" placeholder="kind 0:등록시 1:3개월 재안내"  class="bb"></dd>
				<dt>blood</dt> <dd><input type="text" name="blood" placeholder="blood(해당되면 안되면 null)"  class="bb"></dd>
				<dt>press</dt> <dd><input type="text" name="press" placeholder="press"  class="bb"></dd>
				<dt>col</dt> <dd><input type="text" name="col" placeholder="col"  class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('period');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list} {blood} {press} {col} {weight}
					<br>
				</div>
			
				<div id="period-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<!-- <div class="api">
		<form method="post" id="period-into" name="period-into" action="/m/period_into/first.go">
			<li>관리주기등록</li>
			<h1 class="page-title">/m/period_into/first.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>blood</dt>	 <dd><input type="text" name="blood" placeholder="(빈칸 : 선택없음 blood:해당 ) " class="bb"></dd>
				<dt>press</dt>	 <dd><input type="text" name="press" placeholder="(빈칸 : 선택없음 press:해당 )" class="bb"></dd>
				<dt>col</dt>	 <dd><input type="text" name="col" placeholder="(빈칸 : 선택없음 col:해당 )"  class="bb"></dd>
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('period-into');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				("result", true);
			 	("msg","등록완료");
					<br>
				</div>
				<div id="period-into-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	 -->
	<div class="api">
		<form method="post" id="period-edit" name="period-edit" action="/m/period_edit.go">
			<li>관리주기변경안내</li>
			<h1 class="page-title">/m/period_edit.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('period-edit');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					 기준치 달성시 result : true {list} <br>
					<br>-미 달성시 result : false , msg : 해당없음 
					<br>
				</div>
				<div id="period-edit-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	
	
<!-- 	<div class="api">
		<form method="post" id="b-edit" name="b-edit" action="/m/period_into/blood.go">
			<li>혈당변경</li>
			<h1 class="page-title">/m/period_into/blood.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('b-edit');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				("result", true);
			 	("msg","등록완료");
					<br>
				</div>
				<div id="b-edit-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="p-edit" name="p-edit" action="/m/period_into/press.go">
			<li>혈압변경</li>
			<h1 class="page-title">/m/period_into/press.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>press</dt>	 <dd><input type="text" name="press" placeholder="(빈칸 : 비질환 press:질환 )" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('p-edit');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				("result", true);
			 	("msg","등록완료");
					<br>
				</div>
				<div id="p-edit-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div> -->
	
	<div class="api">
		<form method="post" id="period-err" name="period-err" action="/m/period_err.go">
			<li>기간내미입력</li>
			<h1 class="page-title">/m/period_err.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('period-err');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}
					<br>
				</div>
				<div id="period-err-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>														
	<div class="div-title">
	  cvrisk 
	</div>
<!-- 	<div class="api">
		<form method="post" id="cvrisk" name="cvrisk" action="/m/cvrisk.go">
			<li>cvrisk 안내</li>
			<h1 class="page-title">/m/cvrisk.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('cvrisk');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					유저가 기존에 입력값이 있으면 ("user", uc); ("list", list);
					없으면 ("list", list);
					<br>
				</div>
				<div id="cvrisk-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	 -->
<!-- 		<div class="api">
		<form method="post" id="cvrisk-cal" name="cvrisk-cal" action="/m/cvrisk_cal.go">
			<li>cvrisk 계산</li>
			<h1 class="page-title">/m/cvrisk_cal.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>col</dt>	 <dd><input type="text" name="col" placeholder="col" class="bb"></dd>
			 	<dt>hdl</dt>	 <dd><input type="text" name="hdl" placeholder="hdl" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('cvrisk-cal');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					("tage", tage);
					("data", result);
					<br>
				</div>
				<div id="cvrisk-cal-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	 -->
	<div class="api">
		<form method="post" id="cvrisk-insert" name="cvrisk-insert" action="/m/cvrisk_insert.go">
			<li>cvrisk 등록</li>
			<h1 class="page-title">/m/cvrisk_insert.go</h1>
			<dl class="in">
			 	<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			 	<dt>col</dt>	 <dd><input type="text" name="col" placeholder="col" class="bb"></dd>
			 	<dt>hdl</dt>	 <dd><input type="text" name="hdl" placeholder="hdl" class="bb"></dd>
			 	<dt>userTage</dt>	 <dd><input type="text" name="userTage" placeholder="사용자 10년후 나이" class="bb"></dd>
			 	<dt>cvNum</dt>	 <dd><input type="text" name="cvNum" placeholder="cvNum" class="bb"></dd>
			 	<dt>splessure</dt>	 <dd><input type="text" name="splessure" placeholder="splessure" class="bb"></dd>
			 	<dt>dplessure</dt>	 <dd><input type="text" name="dplessure" placeholder="dplessure" class="bb"></dd>
			 	
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('cvrisk-insert');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					("result", true);
			 	    ("msg","등록완료");
					
					<br>
				</div>
				<div id="cvrisk-insert-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>				
	
	<div class="div-title">
	  컨텐츠
	</div>
	
	<div class="api">
		<form method="post" id="daycontents" name="daycontents" action="/m/daycontents.go">
			<li>1일컨텐츠</li>
			<h1 class="page-title">/m/daycontents.go</h1>
			<dl class="in">
			 	
			 	
			 	<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			 	
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('daycontents');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}
					<br>
				</div>
				<div id="daycontents-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<!-- <div class="api">
		<form method="post" id="weekcontents" name="weekcontents" action="/m/weekcontents.go">
			<li>1주컨텐츠</li>
			<h1 class="page-title">/m/weekcontents.go</h1>
			<dl class="in">
			 		<dt>weekgroup</dt>	 <dd><input type="text" name="weekgroup" placeholder="그룹" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('weekcontents');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}
					<br>
				</div>
				<div id="weekcontents-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="week-intro" name="week-intro" action="/m/week_intro.go">
			<li>1주지표처음</li>
			<h1 class="page-title">/m/week_intro.go</h1>
			<dl class="in">
			 	 <dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('week-intro');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					("result",true); -데이터가1개이상 있는경우
					("result",false); -없는경우
					<br>
				</div>
				<div id="week-intro-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	
	<div class="api">
		<form method="post" id="week-first" name="week-first" action="/m/week/first.go">
			<li>1주지표안내</li>
			<h1 class="page-title">/m/week/first.go</h1>
			<dl class="in">
			 	 	<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('week-first');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					("bloodcnt");
					("presscnt");
					("weightcnt");
					("ucnt");
					<br>
				</div>
				<div id="week-first-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
		<div class="api">
		<form method="post" id="week-blooddetail" name="week-blooddetail" action="/m/week/blooddetail.go">
			<li>1주지표(혈당두번째)</li>
			<h1 class="page-title">/m/week/blooddetail.go</h1>
			<dl class="in">
			  	<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			  
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('week-blooddetail');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
						{"gong":0,"sik":1,"sleep":1} 각 종류별 측정횟수
					<br>
				</div>
				<div id="week-blooddetail-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="week-blood" name="week-blood" action="/m/week/blood.go">
			<li>1주지표(혈당)</li>
			<h1 class="page-title">/m/week/blood.go</h1>
			<dl class="in">
			  	<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			  	<dt>kind</dt>	 <dd><input type="text" name="kind" placeholder="체크할 데이터(공복 1 식후 2 취침전 3)" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('week-blood');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
						{"result":true}<br>
						{"oavg":56,"myavg":88"} -다른사람평균앞에 o , 내평균 앞에 my<br>
						{"firstcomment":"평균이 타 사용자들에 비해 높네요.","secondcomment":"목표값보다 낮습니다."} 코멘트 두개<br>
						{"goal":140} 목표데이터 <br>
					<br>
				</div>
				<div id="week-blood-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="week-press" name="week-press" action="/m/week/press.go">
			<li>1주지표(혈압)</li>
			<h1 class="page-title">/m/week/press.go</h1>
			<dl class="in">
			  	<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('week-press');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
						{"result":true}<br>
						{"osavg":58,"odavg":73,"mysavg":55,"mydavg":120} 작은값 큰값 <br>
						{"firstcomment":"평균이 타 사용자들에 비해 높네요.","secondcomment":"목표값보다 낮습니다."}<br>
						{"goal":140}	<br>
					<br>
				</div>
				<div id="week-press-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="week-weight" name="week-weight" action="/m/week/weight.go">
			<li>1주지표(체중)</li>
			<h1 class="page-title">/m/week/weight.go</h1>
			<dl class="in">
			  	<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('week-weight');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
						{"result":true}<br>
						{"firstcomment":"평균이 타 사용자들에 비해 낮네요.","secondcomment":"목표값보다 낮습니다."}<br>
						{"oavg":200,"myavg":0}<br>
						{"goal":23}	<br>
					<br>
				</div>
				<div id="week-weight-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
 -->
<!-- 	<div class="api">
		<form method="post" id="monthcontents" name="monthcontents" action="/m/monthcontents.go">
			<li>1개월 컨텐츠</li>
			<h1 class="page-title">/m/monthcontents.go</h1>
			<dl class="in">
			 	<dt>년월</dt>	 <dd><input type="text" name="month" placeholder="2015-05" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('monthcontents');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}
					<br>
				</div>
				<div id="monthcontents-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	
	
	<div class="api">
		<form method="post" id="magareport" name="magareport" action="/m/maga_report.go">
			<li>1개월 지표안내</li>
			<h1 class="page-title">/m/maga_report.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			 	<dt>년월</dt>	 <dd><input type="text" name="month" placeholder="2015-05" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('magareport');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{blood}
					{pressure}
					{weight}
					{goaldata}<br>
					<br>
				</div>
				<div id="magareport-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="month-result" name="month-result" action="/m/result.go">
			<li>1개월지표(결과)</li>
			<h1 class="page-title">/m/result.go</h1>
			<dl class="in">
			     <dt>period</dt> <dd><input type="text" name="period" placeholder="2:1개월"  class="bb"></dd>
			     <dt>code</dt> <dd><input type="text" name="code" placeholder="code"  class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('month-result');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					 {data}		
					<br>
				</div>
				<div id="month-result-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div> -->
	<div class="div-title">
	  차트
	</div>
	<div class="api">
		<form method="post" id="chart-blood" name="chart-blood" action="/m/chartblood.go">
			<li>혈당차트</li>
			<h1 class="page-title">/m/chartblood.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>page</dt>	 <dd><input type="text" name="page" placeholder="page" class="bb"></dd>
			 	<dt>kind</dt>	 <dd><input type="text" name="kind" placeholder=" 0:전체 1:공복 2:식후 3:취침전" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chart-blood');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}<br>
					<br>
				</div>
				<div id="chart-blood-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="delete-blood" name="delete-blood" action="/m/delete/blood.go">
			<li>혈당차트삭제</li>
			<h1 class="page-title">/m/delete/blood.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			 	<dt>Seq</dt>	 <dd><input type="text" name="Seq" placeholder=" Seq" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('delete-blood');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
						("result", true, "msg","삭제되었습니다");<br>
					<br>
				</div>
				<div id="delete-blood-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="chart_comment" name="chart_comment" action="/m/chart_comment.go">
			<li>코멘트입력</li>
			<h1 class="page-title">/m/chart_comment.go</h1>
			<dl class="in">
				<dt>dId</dt>	 <dd><input type="text" name="dId" placeholder="dId" class="bb"></dd>
			 	<dt>Seq</dt>	 <dd><input type="text" name="Seq" placeholder=" Seq" class="bb"></dd>
			 	<dt>comment</dt>	 <dd><input type="text" name="comment" placeholder=" comment" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chart_comment');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					<br>
					<br>
				</div>
				<div id="chart_comment-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="chart_commnet_view" name="chart_commnet_view" action="/m/chart_commnet_view.go">
			<li>코멘트조회</li>
			<h1 class="page-title">/m/chart_commnet_view.go</h1>
			<dl class="in">
				<dt>dId</dt>	 <dd><input type="text" name="dId" placeholder="dId" class="bb"></dd>
			 	<dt>Seq</dt>	 <dd><input type="text" name="Seq" placeholder=" Seq" class="bb"></dd>
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chart_commnet_view');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					<br>
					<br>
				</div>
				<div id="chart_commnet_view-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="chart-pressure" name="chart-pressure" action="/m/chart/pressure.go">
			<li>혈압차트</li>
			<h1 class="page-title">/m/chart/pressure.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			 	<dt>page</dt>	 <dd><input type="text" name="page" placeholder="page" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chart-pressure');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}<br>
					<br>
				</div>
				<div id="chart-pressure-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="delete-pressure" name="delete-pressure" action="/m/delete/pressure.go">
			<li>혈압차트삭제</li>
			<h1 class="page-title">/m/delete/pressure.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			 	<dt>Seq</dt>	 <dd><input type="text" name="Seq" placeholder=" Seq" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('delete-pressure');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
						("result", true, "msg","삭제되었습니다");<br>
					<br>
				</div>
				<div id="delete-pressure-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="chart-weight" name="chart-weight" action="/m/chart/weight.go">
			<li>체중차트</li>
			<h1 class="page-title">/m/chart/weight.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			 	<dt>page</dt>	 <dd><input type="text" name="page" placeholder="page" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chart-weight');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}<br>
					<br>
				</div>
				<div id="chart-weight-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="delete-weight" name="delete-weight" action="/m/delete/weight.go">
			<li>체중차트삭제</li>
			<h1 class="page-title">/m/delete/weight.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			 	<dt>Seq</dt>	 <dd><input type="text" name="Seq" placeholder=" Seq" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('delete-weight');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
						("result", true, "msg","삭제되었습니다");<br>
					<br>
				</div>
				<div id="delete-weight-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	
	
	<div class="api">
		<form method="post" id="chart-col" name="chart-col" action="/m/chart/col.go">
			<li>콜레스테롤차트</li>
			<h1 class="page-title">/m/chart/col.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			 	<dt>page</dt>	 <dd><input type="text" name="page" placeholder="page" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chart-col');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}<br>
					<br>
				</div>
				<div id="chart-col-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="delete-col" name="delete-col" action="/m/delete/col.go">
			<li>콜레스테롤차트삭제</li>
			<h1 class="page-title">/m/delete/col.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			 	<dt>Seq</dt>	 <dd><input type="text" name="Seq" placeholder=" Seq" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('delete-col');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
						("result", true, "msg","삭제되었습니다");<br>
					<br>
				</div>
				<div id="delete-col-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	
	<div class="api">
		<form method="post" id="chart-hba" name="chart-hba" action="/m/chart/hba.go">
			<li>당화혈색소차트</li>
			<h1 class="page-title">/m/chart/hba.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			 	<dt>page</dt>	 <dd><input type="text" name="page" placeholder="page" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chart-hba');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}<br>
					<br>
				</div>
				<div id="chart-hba-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="delete-hba" name="delete-hba" action="/m/delete/hba.go">
			<li>당화혈색소차트삭제</li>
			<h1 class="page-title">/m/delete/hba.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			 	<dt>Seq</dt>	 <dd><input type="text" name="Seq" placeholder=" Seq" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('delete-hba');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
						("result", true, "msg","삭제되었습니다");<br>
					<br>
				</div>
				<div id="delete-hba-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="div-title">
	  그래프
	</div>			
	<div class="api">
		<form method="post" id="g-blood" name="g-blood" action="/m/g/blood.go">
			<li>혈당그래프</li>
			<h1 class="page-title">/m/g/blood.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>speriod</dt>	 <dd><input type="text" name="speriod" placeholder="YYYY-MM-DD" class="bb"></dd>
				<dt>eperiod</dt>	 <dd><input type="text" name="eperiod" placeholder="YYYY-MM-DD" class="bb"></dd>
			 
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('g-blood');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}{goal}<br>
					<br>
				</div>
				<div id="g-blood-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>												
	<div class="api">
		<form method="post" id="g-pressure" name="g-pressure" action="/m/g/pressure.go">
			<li>혈압그래프</li>
			<h1 class="page-title">/m/g/pressure.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>speriod</dt>	 <dd><input type="text" name="speriod" placeholder="YYYY-MM-DD" class="bb"></dd>
				<dt>eperiod</dt>	 <dd><input type="text" name="eperiod" placeholder="YYYY-MM-DD" class="bb"></dd>
			 
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('g-pressure');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}{goal}<br>
					<br>
				</div>
				<div id="g-pressure-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>		
	<div class="api">
		<form method="post" id="g-weight" name="g-weight" action="/m/g/weight.go">
			<li>체중그래프</li>
			<h1 class="page-title">/m/g/weight.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>speriod</dt>	 <dd><input type="text" name="speriod" placeholder="YYYY-MM-DD" class="bb"></dd>
				<dt>eperiod</dt>	 <dd><input type="text" name="eperiod" placeholder="YYYY-MM-DD" class="bb"></dd>
			 
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('g-weight');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}{goal}<br>
					<br>
				</div>
				<div id="g-weight-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>		
	<div class="api">
		<form method="post" id="g-col" name="g-col" action="/m/g/col.go">
			<li>콜레스테롤그래프</li>
			<h1 class="page-title">/m/g/col.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>speriod</dt>	 <dd><input type="text" name="speriod" placeholder="YYYY-MM-DD" class="bb"></dd>
				<dt>eperiod</dt>	 <dd><input type="text" name="eperiod" placeholder="YYYY-MM-DD" class="bb"></dd>
			 
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('g-col');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}{goal}<br>
					<br>
				</div>
				<div id="g-col-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="g-hba" name="g-hba" action="/m/g/hba.go">
			<li>당화혈색소그래프</li>
			<h1 class="page-title">/m/g/hba.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>speriod</dt>	 <dd><input type="text" name="speriod" placeholder="YYYY-MM-DD" class="bb"></dd>
				<dt>eperiod</dt>	 <dd><input type="text" name="eperiod" placeholder="YYYY-MM-DD" class="bb"></dd>
			 
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('g-hba');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{list}{goal}<br>
					<br>
				</div>
				<div id="g-hba-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>		

	<div class="api">
		<form method="post" id="save_data" name="save_data" action="/m/save_data.go">
			<li>데이터백업</li>
			<h1 class="page-title">/m/save_data.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>startDate</dt>	 <dd><input type="text" name="startDate" placeholder="YYYY-MM-DD" class="bb"></dd>
				<dt>endDate</dt>	 <dd><input type="text" name="endDate" placeholder="YYYY-MM-DD" class="bb"></dd>
				<dt>isBloodSugar</dt>	<dd><input type="text" name="isBloodSugar" placeholder="1:백업 0:안함" class="bb"></dd>
				<dt>isPressure</dt>	 	<dd><input type="text" name="isPressure" placeholder="1:백업 0:안함" class="bb"></dd>
				<dt>isCholesterol</dt>	<dd><input type="text" name="isCholesterol" placeholder="1:백업 0:안함" class="bb"></dd>
				<dt>isWeight</dt>	 	<dd><input type="text" name="isWeight" placeholder="1:백업 0:안함" class="bb"></dd>
				<dt>isHemoglobin</dt>	<dd><input type="text" name="isHemoglobin" placeholder="1:백업 0:안함" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('save_data');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="save_data-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>				

	<div class="api">
		<form method="post" id="now_version" name="now_version" action="/m/now_version.go">
			<li>앱버전</li>
			<h1 class="page-title">/m/now_version.go</h1>
			<dl class="in">
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('now_version');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="now_version-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>				

	<div class="api">
		<form method="post" id="chat_counselor" name="chat_counselor" action="/m/chat_counselor.go">
			<li>상담자 호출</li>
			<h1 class="page-title">/m/chat_counselor.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" value="bestist@naver.com" placeholder="userId" class="bb"></dd>
				<dt>chatType</dt>	 <dd><input type="text" name="chatType" placeholder="1:건강상담 2:일반상담" class="bb"></dd>
				<dt>flag</dt>	 <dd><input type="text" name="flag" placeholder="'test'로 등록하면 안내글 표시됨" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chat_counselor');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"상담 가능","result":true,"counselor":{"appVersion":"","birthday":"1980-01-01","deviceId":"ffffffff-c54a-43fb-e98b-8953171c3533","deviceName":"IM-A910K","fileName":"","gender":1,"genderText":"남자","lastLogindate":"2015-11-15 23:20:49.113","loginKakao":0,"loginNaver":0,"loginStatus":"1","osType":"ANDROID","osVersion":"19","password":"+/s4bv6mfoFvLdoKjJSpjrIDdXrrs/VfGDdVoZLURGc\u003d","phoneNumber":"01000000000","pushkey":"","regDate":"","status":0,"usePushservice":0,"userId":"counselor@test.com","userMed":1,"userName":"상담원1","userType":2,"userTypeText":""}}
				</div>
				<div id="chat_counselor-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>				

				
	<div class="api">
		<form method="post" id="aimmed_check_user" name="aimmed_check_user" action="/m/aimmed_check_user.go">
			<li>에임메드 사용자 체크</li>
			<h1 class="page-title">/m/aimmed_check_user.go</h1>
			<dl class="in">
				<dt>name</dt>	 <dd><input type="text" name="name" value="저스틴" placeholder="name" class="bb"></dd>
				<dt>mobile</dt>	 <dd><input type="text" name="mobile" value="01099990004" placeholder="mobile" class="bb"></dd>
				<dt>birth</dt>	 <dd><input type="text" name="birth" value="19800101" class="bb"></dd>
				<dt>secret</dt>	 <dd><input type="text" name="secret" value="AimmedRecover" class="bb"></dd>
				<dt>appVersion</dt>	 <dd><input type="text" name="appVersion" placeholder="appVersion" class="bb"></dd>
				<dt>os_version</dt>	 <dd><input type="text" name="os_version" placeholder="os_version" class="bb"></dd>
				<dt>os_type</dt>	 <dd><input type="text" name="os_type" placeholder="os_type" class="bb"></dd>
				<dt>device_name</dt> <dd><input type="text" name="device_name" placeholder="device_name" class="bb"></dd>
				<dt>device_id</dt>	 <dd><input type="text" name="device_id" placeholder="device_id" class="bb"></dd>
				<dt>pushKey</dt>	 <dd><input type="text" name="pushKey" placeholder="pushKey" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('aimmed_check_user');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result":true,"col":"","press":"","weight":0,"bmi":0.0,"waist":0,"pulse":0,"blood":"","bloodNum":0,"smoke":0,"userType":3,"message":"로그인이 성공되었습니다.","splessure":0,"height":0,"age":35,"userMed":0,"userName":"저스틴","dplessure":0,"weightNum":0}
				</div>
				<div id="aimmed_check_user-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>				

	
	<div class="api">
		<form method="post" id="aimmed_send_sms" name="aimmed_send_sms" action="/m/aimmed_send_sms.go">
			<li>에임메드 SMS 발송</li>
			<h1 class="page-title">/m/aimmed_send_sms.go</h1>
			<dl class="in">
				<dt>message</dt>	 <dd><input type="text" name="message" placeholder="message" class="bb"></dd>
				<dt>to</dt>	 <dd><input type="text" name="to" value="" placeholder="수신전화번호" class="bb"></dd>
				<dt>from</dt>	 <dd><input type="text" name="from" value="0230157554" class="bb"></dd>
				<dt>secret</dt>	 <dd><input type="text" name="secret" value="AimmedRecover" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('aimmed_send_sms');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"인증번호가 발송되었습니다.","result":true}
				</div>
				<div id="aimmed_send_sms-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	
	
		<div class="api">
		<form method="post" id="test" name="test" action="/m/test.go">
			<li>1주레포트 테스트용</li>
			<h1 class="page-title">/m/test.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('test');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="test-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>		
	
	<div class="api">
		<form method="post" id="test2" name="test2" action="/m/test2.go">
			<li>1개월레포트 테스트용</li>
			<h1 class="page-title">/m/test2.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('test2');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="test2-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="test3" name="test3" action="/m/test3.go">
			<li>1개월컨텐츠(매거진) 테스트용</li>
			<h1 class="page-title">/m/test3.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('test3');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="test3-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="counsel-rev" name="counsel-rev" action="/m/counsel_rev.go">
			<li>상담예약</li>
			<h1 class="page-title">/m/counsel_rev.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>counselDate</dt>	 <dd><input type="text" name="counselDate" placeholder="counselDate" class="bb"></dd>
				<dt>counselTime</dt>	 <dd><input type="text" name="counselTime" placeholder="counselTime" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('counsel-rev');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="counsel-rev-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>						
	
	<div class="api">
		<form method="post" id="send_push" name="send_push" action="/m/send_push.go">
			<li>푸시발송테스트</li>
			<h1 class="page-title">/m/send_push.go</h1>
			<dl class="in">
			<!-- 	<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>msg</dt>	 <dd><input type="text" name="msg" placeholder="메세지" class="bb"></dd>
				<dt>pushType</dt> <dd><input type="text" name="pushType" placeholder="1 :공지사항 2: 복약알림 3:기간내미입력 4:관리주기변경 5:cv-risk" class="bb"></dd> -->
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('send_push');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="send_push-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	


	<div class="api">
		<form method="post" id="send_push_test" name="send_push" action="/m/send_push_test.go">
			<li>푸시발송테스트</li>
			<h1 class="page-title">/m/send_push_test.go</h1>
			<dl class="in">
			 	<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>msg</dt>	 <dd><input type="text" name="msg" placeholder="메세지" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('send_push_test');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="send_push_test-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>	

		<div class="api">
		<form method="post" id="clear_noticeBadge" name="clear_noticeBadge" action="/m/clear_noticeBadge.go">
			<li>푸시클리어</li>
			<h1 class="page-title">/m/clear_noticeBadge.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('clear_noticeBadge');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="clear_noticeBadge-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>						
		<div class="api">
		<form method="post" id="service" name="service" action="/m/service.go">
			<li>공지사항 진입시점</li>
			<h1 class="page-title">/m/service.go</h1>
			<dl class="in">
				
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('service');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{cnt}
					<br>
				</div>
				<div id="service-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="badge" name="badge" action="/m/badge.go">
			<li>뱃지갯수</li>
			<h1 class="page-title">/m/badge.go</h1>
			<dl class="in">
				
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>type</dt>	 <dd><input type="text" name="type" placeholder="1:복약 2: 복약 이외" class="bb"></dd>
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('badge');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{cnt}
					<br>
				</div>
				<div id="badge-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="badge_read" name="badge_read" action="/m/badge_read.go">
			<li>뱃지읽음처리</li>
			<h1 class="page-title">/m/badge_read.go</h1>
			<dl class="in">
				
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>type</dt>	 <dd><input type="text" name="type" placeholder="1:복약 2: 복약 이외" class="bb"></dd>
			
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('badge_read');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{cnt}
					<br>
				</div>
				<div id="badge_read-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="grape" name="grape" action="/m/grape_do.go">
			<li>그래프</li>
			<h1 class="page-title">/m/grape_do.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>diseaseId</dt>	 <dd><input type="text" name="diseaseId" placeholder="diseaseId" class="bb"></dd>
				<dt>speriod</dt>	 <dd><input type="text" name="speriod" placeholder="speriod" class="bb"></dd>
				<dt>eperiod</dt>	 <dd><input type="text" name="eperiod" placeholder="eperiod" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('grape');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
					<br>
				</div>
				<div id="grape-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>

	<div class="api">
		<form method="post" id="chat_start" name="chat_start" action="/m/chat_start.go">
			<li>채팅시작</li>
			<h1 class="page-title">/m/chat_start.go</h1>
			<dl class="in">
				
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>chatRoomSeq</dt>	 <dd><input type="text" name="chatRoomSeq" placeholder="chatRoomSeq" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chat_start');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{cnt}
					<br>
				</div>
				<div id="chat_start-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="chat_finish" name="chat_finish" action="/m/chat_finish.go">
			<li>채팅종료</li>
			<h1 class="page-title">/m/chat_finish.go</h1>
			<dl class="in">
				
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>chatRoomSeq</dt>	 <dd><input type="text" name="chatRoomSeq" placeholder="chatRoomSeq" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('chat_finish');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{cnt}
					<br>
				</div>
				<div id="chat_finish-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="api">
		<form method="post" id="server-time" name="server-time" action="/m/sever_time.go">
			<li>서버 시간</li>
			<h1 class="page-title">/m/sever_time.go</h1>
			<dl class="in">
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('server-time');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result":true,"serverTime":"2016-02-03 18:23:29"}
					<br>
				</div>
				<div id="server-time-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
	
	<div class="api">
		<form method="post" id="report_history_test" name="report_history_test" action="/m/report_history_test.go">
			<li>수행내역 테스트</li>
			<h1 class="page-title">/m/report_history_test.go</h1>
			<dl class="in">
				<dt>userId</dt>	 <dd><input type="text" name="userId" placeholder="userId" class="bb"></dd>
				<dt>today</dt>	 <dd><input type="text" name="today" placeholder="today" class="bb"></dd>
				<dt>before</dt>	 <dd><input type="text" name="before" placeholder="before" class="bb"></dd>
				<dt>&nbsp;</dt>	 <dd><button type="submit" onclick="formSubmit('report_history_test');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"result":true,"serverTime":"2016-02-03 18:23:29"}
					<br>
				</div>
				<div id="report_history_test-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</div>
</ol>

</body>
</html>