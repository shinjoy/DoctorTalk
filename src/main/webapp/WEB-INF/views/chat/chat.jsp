<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header5.jsp"  %>

	<script>
		
		function filtering(str,link,cType) {
			if (link!="") {
				str = itemLinkFilter(str,link);
			}
			if (cType==0) {
				str = autolink(str);
			}
			return str;
		}
		function itemLinkFilter(str,json) {
			/* 테스트에서는 아래 코드 주석처리해야 함 
			var json = JSON.parse(decodeURIComponent(json));
			*/
			
			var re = new Array();
			
			for (var i=0; i<json.length; i++) {
				var realName = json[i].REAL_NAME;
				var name = json[i].NAME;
				var code = json[i].CODE;
				var strArr = str.split(name);
				str = strArr.join("<a href=\"javascript:onItemLink('"+code+"','&a"+i+";');\" class=\"item-link\">&a"+i+";</a>");
				//str = strArr.join("<a href=\"javascript:onItemLink('"+code+"','"+name+"');\" class=\"item-link\">"+name+"</a>");
				//str = str.replace(name, "<a href=\"javascript:onItemLink('"+code+"','"+name+"');\" alt=\""+realName+"\" class=\"item-link\">"+name+"</a>");
				re.push(name);
			}
			for (var i=0; i<re.length; i++) {
				var strArr = str.split("&a"+i+";");
				str = strArr.join(re[i]);
			}
			return str;
		}
		function autolink(str) {
			var regURL = new RegExp("(http|https|ftp|telnet|news|irc)://([-/.a-zA-Z0-9_~#%$?&=:200-377()]+)","gi");
			var regEmail = new RegExp("([xA1-xFEa-z0-9_-]+@[xA1-xFEa-z0-9-]+\.[a-z0-9-]+)","gi");
			return str.replace(regURL,"<span class='link'><a href='$1://$2' target='_blank'>$1://$2</a></span>").replace(regEmail,"<span class='link'><a href='mailto:$1'>$1</a></span>");
		}

		function contextMenu(obj) {
			/*
			if (document.addEventListener) {
				document.addEventListener('contextmenu', function(e) {
					//e.preventDefault();
				}, false);
				document.addEventListener('dblclick', function(e) {
					e.preventDefault();
				}, false);
				document.addEventListener('click', function(e) {
					if (showContext) {
						$("ul.context").css("display","none");
						msgKey = 0;
					}
				}, false);
			} else {
				document.attachEvent('oncontextmenu', function() {
					alert("You've tried to open context menu2");
					//showContextMenu();
					window.event.returnValue = false;
				});
			}
			*/
		}
		function onContentsMenu(obj) {
			/*
			showContextMenu();
			msgKey = $(obj).find(".msg-key").val();
			return false;
			*/
		}
		var showContext = false;
		var msgKey = 0;
		function showContextMenu() {
			var e = window.event;
		    var posX = e.clientX;
		    var posY = e.clientY;
		    if ($(window).width() < (posX+80)) {
		    	posX = posX - 80;
		    }
		    if ($(window).height() < (posY+55)) {
		    	posY = posY - 55;
		    }
			$("ul.context").css("display","block");
			$("ul.context").css("top",posY);
			$("ul.context").css("left",posX);
			showContext = true;
		}
		
		function contextCopy() {
			var text = $("#data"+msgKey).html();
			
			if (window.clipboardData) { // Internet Explorer
				window.clipboardData.setData("Text", text);
				alert("클립보드에 복사되었습니다.");
				$("ul.context").css("display","none");
			}
		}
		function contextDelete() {
			if (confirm("선택한 내용을 삭제하시겠습니까?")) {
				window.location = "jscall://messageDelete?"+msgKey;
				$("#msg"+msgKey).remove();
				$("ul.context").css("display","none");
			}
		}
		
		$(document).ready(function () {

			contextMenu();
			
			/*
			//-> 테스트 데이터
			var a1 = '{"list":[';
			a1 += '{"msgKey":21,"roomIdx":10,"mType":0,"cType":0,"sendID":"bestist@naver.com","contents":"안녕(헤헤)하 하나금융하나금융 세요.","fileName":"","option1": "","option2": "","option3": "","incoming": 1,"unread": 3,"senderType": "UT_ST","PhoneNumber": "010-1234-1234","sendName": "최승규","mdate": 100,"regDate": "2015-10-12 14:15:23:00", "fileType":0, "thumbUrl":"", "fileUrl":"", "profileUrl":"http://localhost/files/profile/user2.jpg", "itemLink":[{"LINKSO_SKIP_LINK":0,"@":"","#":"하나금융지주","REAL_NAME":"하나금융지주","NAME":"하나금융","CODE":"005930"}]},';
			//a1 += '{"msgKey":21,"roomIdx":10,"mType":0,"cType":0,"sendID":"user01","contents":"안녕(헤헤)하세요.","fileName":"","option1": "","option2": "","option3": "","incoming": 1,"unread": 3,"senderType": "UT_ST","PhoneNumber": "010-1234-1234","sendName": "이정근 차장","mdate": 100,"regDate": "2015-10-12 14:15:23:00", "fileType":0, "thumbUrl":"", "fileUrl":"", "profileUrl":"http://localhost/files/profile/user2.jpg"},';
			a1 += '{"msgKey":22,"roomIdx":10,"mType":0,"cType":0,"sendID":"bestist@naver.com","contents":"하나대투증권 떴어요!1 ","fileName":"","option1": "","option2": "","option3": "","incoming": 0,"unread": 3,"senderType": "UT_ST","PhoneNumber": "010-1234-1234","sendName": "최승규","mdate": 100,"regDate": "2015-10-11 10:15:23:00", "fileType":0, "thumbUrl":"", "fileUrl":"", "profileUrl":"http://localhost/files/profile/user2.jpg"},';
			a1 += '{"msgKey":23,"roomIdx":10,"mType":0,"cType":0,"sendID":"bestist@naver.com","contents":"(미안)처럼 자주 쓰이는 함수들이 있다. ","fileName":"","option1": "","option2": "","option3": "","incoming": 1,"unread": 3,"senderType": "UT_ST","PhoneNumber": "010-1234-1234","sendName": "최승규","mdate": 100,"regDate": "2015-10-11 09:15:23:00", "fileType":0, "thumbUrl":"http://localhost/files/bbs/201509/user7_7ACX120151010232019000.jpg", "fileUrl":"", "profileUrl":"http://localhost/files/profile/user1.jpg"},';
			a1 += '{"msgKey":24,"roomIdx":10,"mType":0,"cType":0,"sendID":"aimmed@aimmed.com","contents":" 엘리멘트/하위 엘리멘트로 (룰루)나뉘어져 있는 상태를 말한다. ","fileName":"","option1": "","option2": "","option3": "","incoming": 1,"unread": 3,"senderType": "UT_ST","PhoneNumber": "010-1234-1234","sendName": "리커버","mdate": 100,"regDate": "2015-10-10 14:17:23:00", "fileType":0, "thumbUrl":"", "fileUrl":"", "profileUrl":"http://localhost/files/profile/user1.jpg"},';
			a1 += '{"msgKey":25,"roomIdx":10,"mType":0,"cType":0,"sendID":"aimmed@aimmed.com","contents":"반대로 동등한 관계일땐 별도의 함수를 (헤벌쭉)사용한다.","fileName":"","option1": "","option2": "","option3": "","incoming": 0,"unread": 3,"senderType": "UT_ST","PhoneNumber": "010-1234-1234","sendName": "리커버","mdate": 100,"regDate": "2015-10-10 14:15:23:00", "fileType":0, "thumbUrl":"", "fileUrl":"", "profileUrl":"http://localhost/files/profile/user1.jpg"}';
			a1 += ']}';
			var b1 = encodeURIComponent(a1);
			console.log(b1);

			loadChatData(b1);

			var a2 = '{"msgKey":26,"roomIdx":10,"mType":0,"cType":0,"sendID":"aimmed@aimmed.com","contents":"하나대투증권 http://www.naver.com 떴어요  !! 2","fileName":"http://localhost/files/profile/user1.jpg","option1": "","option2": "","option3": "","incoming": 1,"unread": 2,"senderType": "UT_ST","PhoneNumber": "010-1234-1234","sendName": "리커버","mdate": 100,"regDate": "2015-10-13 14:15:23:00", "fileType":0, "thumbUrl":"", "fileUrl":"", "profileUrl":"http://localhost/files/profile/user1.jpg"}';
			var b2 = encodeURIComponent(a2);
			addItem(b2);

			var a21 = '{"msgKey":27,"roomIdx":10,"mType":0,"cType":0,"sendID":"bestist@naver.com","contents":"하나대투증권 떴어요!! 21","fileName":"http://localhost/files/profile/user1.jpg","option1": "","option2": "","option3": "","incoming": 1,"unread": 2,"senderType": "UT_ST","PhoneNumber": "010-1234-1234","sendName": "최승규","mdate": 100,"regDate": "2015-10-13 14:16:23:00", "fileType":0, "thumbUrl":"", "fileUrl":"", "profileUrl":"http://localhost/files/profile/user1.jpg"}';
			var b21 = encodeURIComponent(a21);
			addItem(b21);

			var a22 = '{"msgKey":28,"roomIdx":10,"mType":0,"cType":0,"sendID":"aimmed@aimmed.com","contents":"하나대투증권 (초롱)떴어요!! 22","fileName":"http://localhost/files/profile/user1.jpg","option1": "","option2": "","option3": "","incoming": 1,"unread": 2,"senderType": "UT_ST","PhoneNumber": "010-1234-1234","sendName": "리커버","mdate": 100,"regDate": "2015-10-13 14:17:23:00", "fileType":0, "thumbUrl":"", "fileUrl":"", "profileUrl":"http://localhost/files/profile/user1.jpg"}';
			var b22 = encodeURIComponent(a22);
			addItem(b22);

			var a23 = '{"msgKey":29,"roomIdx":10,"mType":0,"cType":0,"sendID":"bestist@naver.com","contents":"하나대투증권 떴어요!! 23","fileName":"http://localhost/files/profile/user1.jpg","option1": "","option2": "","option3": "","incoming": 1,"unread": 2,"senderType": "UT_ST","PhoneNumber": "010-1234-1234","sendName": "최승규","mdate": 100,"regDate": "2015-10-13 14:18:23:00", "fileType":0, "thumbUrl":"", "fileUrl":"", "profileUrl":"http://localhost/files/profile/user1.jpg"}';
			var b23 = encodeURIComponent(a23);
			addItem(b23);

			var a3 = '{"msgKey":30,"roomIdx":10,"mType":0,"status":-1,"sendID":"bestist@naver.com","contents":"하나대투증권 떴어요!! 3","fileName":"http://localhost/files/profile/user1.jpg","option1": "","option2": "","option3": "","incoming": 1,"unread": 2,"senderType": "UT_ST","PhoneNumber": "010-1234-1234","sendName": "최승규","mdate": 100,"regDate": "2015-10-13 14:19:23:00", "fileType":"img", "thumbUrl":"http://localhost/files/bbs/201509/user7_7ACX120151010232019000.jpg", "fileUrl":"", "profileUrl":"http://localhost/files/profile/user2.jpg"}';
			var b3 = encodeURIComponent(a3);
			addItem(b3);

			//var a4 = '{"msgKey":2,"roomIdx":10,"mType":0,"status":-1,"cType":1,"sendID":"user01","contents":"하나대투증권 떴어요!! 4","fileName":"http://localhost/files/profile/user1.jpg","option1": "","option2": "","option3": "","incoming": 1,"unread": 2,"senderType": "UT_ST","PhoneNumber": "010-1234-1234","sendName": "이정근 차장","mdate": 100,"regDate": "2015-10-13 14:10:23:00", "fileType":"mov", "thumbUrl":"", "fileUrl":"http://localhost/files/bbs/201509/fighting.mp4", "profileUrl":"http://localhost/files/profile/user2.jpg"}';
			var a4 = '{"msgKey":31,"roomIdx":10,"mType":0,"status":-1,"cType":0,"sendID":"aimmed@aimmed.com","contents":"우리나라도 어서 저런 문화가 도입 되야되는데..","fileName":"","option1": "","option2": "","option3": "","incoming": 0,"unread": 3,"senderType": "UT_ST","PhoneNumber": "010-1234-1234","sendName": "리커버","mdate": 100,"regDate": "2015-10-10 14:15:23:00", "fileType":0, "thumbUrl":"", "fileUrl":"", "profileUrl":"http://localhost/files/profile/user1.jpg"}';
			var b4 = encodeURIComponent(a4);
			addItem(b4);

			var a5 = '[{"unread":1,"thumbUrl":"","regDate":"2015-10-16 10:55:52:000","incoming":0,"mdate":"1444992952","mType":"0","sendID":"aimmed@aimmed.com","msgKey":"20151016105544397LNPQU","status":"-1","PhoneNumber":"","fileType":"","fileName":"","roomIdx":"21","senderType":"UT_ME","option3":"","option2":"","option1":"","contents":"ddd","cType":"0","fileUrl":"","sendName":"user4","profileUrl":""},{"unread":1,"thumbUrl":"","regDate":"2015-10-16 10:30:33:000","incoming":0,"mdate":"1444991433","mType":"0","sendID":"user4","msgKey":"2015101610303387634568","status":"1","PhoneNumber":"","fileType":"","fileName":"","roomIdx":"21","senderType":"UT_ME","option3":"","option2":"","option1":"","contents":"fffff","cType":"0","fileUrl":"","sendName":"user4","profileUrl":""}]';
			var b5 = encodeURIComponent(a5);
			//addItem(b5);
			//loadChatData(b5);
			
			
			addDateLine();
			
			// 실패처리
			onReceiveSendFail(31);
			
			return false;

			// <-- 테스트 데이터 
			*/
		});

		function addDateLine() {
			var d = "";
			var e = "";
			$(".line").each(function(i) {
				$(this).remove();
			});
			$(".regdate").each(function(i) {
				d = $(this).val().substring(0,10);
				if (e == "") {
					$(renderDate(d)).insertBefore($(this).parent());
				} else if (e!=d) {
					$(renderDate(d)).insertAfter($(this).parent());
				}
				e = d;
			});
		}
		
		function renderDialog(it) {
			var regDate = it.regDate;
			var hour = regDate.substring(11,13);
			var minute = regDate.substring(14,16);
			var time = "";
			if (hour <= 12) {
				time = "오전 "+parseInt(hour)+":"+minute;
			} else {
				time = "오후 "+(hour-12)+":"+minute;
			}
			var photo = "/images/user_default.png";
			if (it.profileUrl != "") {
				photo = it.profileUrl;
			}
			var contents = "";
			if (it.cType == 1) {
				if (it.fileType == "img") {
					contents = '<img src="'+it.thumbUrl+'" style="max-width:150px;" onclick="viewImg(\''+it.thumbUrl+'\');">';
				} else {
					contents = '<video width="300" height="220" controls src="'+it.thumbUrl+'>';
				}
			} else {
				contents = it.contents;
				var ca = contents.split("+");
				contents = ca.join(" ");
				var ca2 = contents.split("\n");
				contents = ca2.join("<br>");
			}
			var unread = "";
			if (it.unread > 0) {
				unread = it.unread;
			}
			var itemLink = "";
			if (!(it.itemLink == "" || it.itemLink == null || it.itemLink == undefined)) {
				itemLink = it.itemLink;
			}
			
			var str = '';
			if (it.incoming == 1) {	// 수신
				str += '	<li class="cmt bubble-comment" id="msg'+it.msgKey+'" oncontextmenu="return onContentsMenu(this);">';
				str += '	<input type="hidden" name="msgKey" class="msg-key" value="'+it.msgKey+'">';
				str += '	<input type="hidden" name="status" class="status" value="">';
				str += '	<input type="hidden" name="regDate" class="regdate" value="'+it.regDate+'">';
				str += '		<div class="buddy-photo">';
				str += '			<div class="photo" onclick="openUserInfo(\''+it.sendID+'\')" style="background-image:url(\''+photo+'\');"></div>';
				str += '		</div>';
				str += '		<div class="name-bubble">';
				str += '			<div class="buddy-name">'+it.sendName+'</div>';
				str += '			<div class="buddy-bubble">';
				str += '				<div class="arrow-left"><img src="/images/bubble_left.png"></div>';
				str += '				<div class="chat-comment">'+filtering(contents,itemLink,it.cType)+'</div>';
				str += '				<div class="chat-comment-hidden" id="data'+it.msgKey+'">'+contents+'</div>';
				str += '			</div>';
				str += '		</div>';
				str += '		<div class="reg-date">';
				str += '			<span class="no-read">'+unread+'</span>';
				if (memberType == "MT_ADMIN") {
					str += '			<span class="star" onclick="callAddFavMsg('+it.msgKey+');">★</span>';
				}
				str += '			<br><span class="time">'+ time +'</span>';
				str += '		</div>';
				str += '	</li>';
			} else if (it.incoming == 0) { // 송신
				if (it.status == -1) {
					str += '	<li class="cmt my-comment" id="msg'+it.msgKey+'" oncontextmenu="return onContentsMenu(this);">';
					str += '	<input type="hidden" name="msgKey" class="msg-key" value="'+it.msgKey+'">';
					str += '	<input type="hidden" name="status" class="status" value="">';
					str += '	<input type="hidden" name="regDate" class="regdate" value="'+it.regDate+'">';
					str += '		<div class="fail-btn">';
					str += '			<a href="javascript:cancelMessage('+it.msgKey+');"><img src="/images/btn_chat_cancel.png"></a><a href="javascript:resend('+it.msgKey+');"><img src="/images/btn_chat_resend.png"></a>';
					str += '		</div>';
					str += '		<div class="reg-date">';
					str += '			<span class="no-read">'+unread+'</span>';
					if (memberType == "MT_ADMIN") {
						str += '			<span class="star" onclick="callAddFavMsg('+it.msgKey+');">★</span>';
					}
					str += '			<br><span class="time">'+ time +'</span>';
					str += '		</div>';
					str += '		<div class="name-bubble">';
					str += '			<div class="buddy-bubble">';
					str += '				<div class="chat-comment"><img src="/images/ing.gif"></div>';
					str += '				<div class="chat-comment-hidden" id="data'+it.msgKey+'">'+contents+'</div>';
					str += '				<div class="arrow-left"><img src="/images/bubble_right.png"></div>';
					str += '			</div>';
					str += '		</div>';
					str += '	</li>';
				} else {
					str += '	<li class="cmt my-comment" id="msg'+it.msgKey+'" oncontextmenu="return onContentsMenu(this);">';
					str += '	<input type="hidden" name="msgKey" class="msg-key" value="'+it.msgKey+'">';
					str += '	<input type="hidden" name="status" class="status" value="">';
					str += '	<input type="hidden" name="regDate" class="regdate" value="'+it.regDate+'">';
					str += '		<div class="fail-btn">';
					str += '			<a href="javascript:cancelMessage('+it.msgKey+');"><img src="/images/btn_chat_cancel.png"></a><a href="javascript:resend('+it.msgKey+');"><img src="/images/btn_chat_resend.png"></a>';
					str += '		</div>';
					str += '		<div class="reg-date">';
					str += '			<span class="no-read">'+unread+'</span>';
					if (memberType == "MT_ADMIN") {
						str += '			<span class="star" onclick="callAddFavMsg('+it.msgKey+');">★</span>';
					}
					str += '			<br><span class="time">'+ time +'</span>';
					str += '		</div>';
					str += '		<div class="name-bubble">';
					str += '			<div class="buddy-bubble">';
					str += '				<div class="chat-comment">'+filtering(contents,itemLink,it.cType)+'</div>';
					str += '				<div class="chat-comment-hidden" id="data'+it.msgKey+'">'+contents+'</div>';
					str += '				<div class="arrow-left"><img src="/images/bubble_right.png"></div>';
					str += '			</div>';
					str += '		</div>';
					str += '	</li>';
				}
			}
			
			//str = JSONtoString(it);

			return str;
		}
		
		function JSONtoString(object) {
		    var results = [];
		    for (var property in object) {
		        var value = object[property];
		        if (value)
		            results.push(property.toString() + ': ' + value);
	        }
	        return '{' + results.join(', ') + '}';
		}
		
		function renderDate(date) {
			var year = date.substring(0,4);
			var month = date.substring(5,7);
			var day = date.substring(8,10);
			var d = new Date(date);
			var week = new Array('일','월','화','수','목','금','토');
			var str = '<li style="clear:both; height:15px;"></li>';
			str += '<li class="line"><span class="date-info">'+year+'년 '+month+'월 '+day+'일 '+week[d.getDay()]+'요일</span></li>';
			return str; //$(".chat").prepend(str);
		}


		// 실시간 수신
		var scrollTimer;
		function addItem(jsonObject) {
			var json = JSON.parse(decodeURIComponent(jsonObject));
			var str = renderDialog(json);
			$(".chat").append(str);
			scrollTimer = setTimeout(scrollDown, 100);
			
		}
		function scrollDown() {
			window.scrollTo(0,document.body.scrollHeight);
			clearTimeout(scrollTimer);
		}

		var memberType = "";
		// 멤버타입 설정
		function setMemberType(type) {
			localStorage.setItem("memberType",type);
			memberType = type;
		}
		
		// 관심 등록 요청
		function callAddFavMsg(msgKey) {
			window.location = "jscall://callAddFavMsg?"+msgKey;
		}

		//데이터 로드.
		function loadChatData(jsonArray) {
			var txt = decodeURIComponent(jsonArray);
			var json = JSON.parse(txt);
			var list = json.list;
			var str = "";
			for (var i=0; i<list.length; i++) {
				$(".chat").prepend(renderDialog(list[i]));
			}
			window.scrollTo(0,document.body.scrollHeight);
		}

		//더보기 데이터 로드
		function loadMoreData(jsonArray) {
			var json = JSON.parse(decodeURIComponent(jsonArray));
			var list = json.list;
			var str = "";
			for (var i=0; i<list.length; i++) {
				$(".chat").prepend(renderDialog(list[i]));
			}
			window.scrollTo(0,document.body.scrollHeight);
		}

		//읽음 상태 변경
		function onReceiveMsgReadStatus(msgKey, count) {
			if (count>0) {
				$("#msg"+msgKey).find(".no-read").html(count);
			} else {
				$("#msg"+msgKey).find(".no-read").html("");
			}
		}

		// 프로필 이미지 클릭했을때
		function onPhoto(userId) {
			window.location = "jscall://callUserPicture?"+userId;
		}
		
		// 첨부파일 이미지 클릭
		function viewImg(img) {
			window.location = "jscall://callPicture?"+img;
		}
		
		// 재전송 요청
		function resend(msgKey) {
			var status = $("#msg"+msgKey+" .status").val();
			window.location = "jscall://resend?"+msgKey+"&"+status;
		}
		
		// 실패 삭제
		function cancelMessage(msgKey) {
			if (confirm("메세지를 삭제하시겠습니까?")) {
				$("#msg"+msgKey).remove();
				window.location = "jscall://resend?"+msgKey+"&"+status;
			}
		}
		
		// 종목링크
		function onItemLink(code,name) {
			window.location = "jscall://callItemlink?"+code+"&"+name;
		}
		
		
		// 폰트크기 설정
		function updateFont(size) {
			if (size=="1") {
				$("body").addClass("small");
				$("body").removeClass("big");
				$("body").removeClass("huge");
			} else if (size=="2") {
				$("body").removeClass("small");
				$("body").removeClass("big");
				$("body").removeClass("huge");
			} else if (size=="3") {
				$("body").removeClass("small");
				$("body").addClass("big");
				$("body").removeClass("huge");
			} else if (size=="4") {
				$("body").removeClass("small");
				$("body").removeClass("big");
				$("body").addClass("huge");
			}			
		}
		
		function onReceiveSendSuccess(msgKey, str) {
			var json = JSON.parse(decodeURIComponent(str));
			var contents = '<img src="'+json.thumb+'" style="max-width:150px;" onclick="viewImg(\''+json.file+'\');">';
			$("#msg"+msgKey).find(".chat-comment").html(contents);
			$("#msg"+msgKey+" .status").val(0);
			scrollTimer = setTimeout(scrollDown, 100);
		}

		// 더 찾기
		function searchMore() {
			var minMsgKey = chatForm.msgKey[0].value;
			window.location = "jscall://callMoreData?"+minMsgKey+",1000,"+searchForm.keyword,value;
		}

		function onReceiveMsgSendFail(msgKey) {
			$("#msg"+msgKey+" .fail-btn").css("display","table-cell");
			$("#msg"+msgKey+" .status").val(0);
			//alert("메세지 전송이 실패했습니다.");
		}
		function onReceiveSendFail(msgKey) {
			$("#msg"+msgKey+" .fail-btn").css("display","table-cell");
			$("#msg"+msgKey+" .status").val(-1);
			//alert("파일 전송이 실패했습니다.");
		}

		function onReceiveMsgSend(msgKey, redDate) {
			var hour = regDate.substring(11,13);
			var minute = regDate.substring(14,16);
			var time = "";
			if (hour <= 12) {
				time = "오전 "+parseInt(hour)+":"+minute;
			} else {
				time = "오후 "+(hour-12)+":"+minute;
			}
			$("#msg"+msgKey+" time").html(time);
		}

		function openUserInfo(userId) {
			windowOpen("/chat/user_info.go?userId="+userId, "doctor_profile", 250, 280, "no", "no");
			//pop.openPage("/chat/user_info.go?userId="+userId);
		}
		
		
		// PC -> WEB 상용구 오픈 요청
		function openScript() {
			windowOpen("/chat/script.go", "doctor_script", 300, 300, "no", "no");
			//pop.openPage("/chat/script.go");
		}
		
		// WEB -> PC 상용구 전송요청.
		function callScript(txt) {
			window.location = "jscall://callScript?"+txt;
		}
		
		// PC -> WEB 초대 화면 오픈 요청.
		function openInvitation() {
			windowOpen("/chat/counselor_list.go", "doctor_counselor", 250, 280, "no", "no");
			//pop.openPage("/chat/counselor_list.go");
		}
		
		// 스크립트 적용
		function callScript(txt) {
			window.location = "jscall://callScript?"+txt;
		}
		
		// 상담원 초대 : WEB -> PC 초대회원 선택.
		function callInvite(id) {
			window.location = "jscall://callInvite?"+id;
		}

		/*** 대화내용 검색 ***/
		var searchIdx = -1;
		var searchCount = 0;
		function onKeyword() {
			searchIdx = -1;
			initSearch();
			searchChat(searchForm);
		}

		function onSearch(frm) {
			//searchIdx = -1;
			//initSearch();
			var result = searchChat(frm);
			if (result) {
				searchPrev('prev');
			}
			return false;
		}

		// 검색 결과 초기화
		function initSearch() {
			$(".searched").removeClass("searched");
			$(".search").removeClass("search");
			for (var i=0; i<searchCount; i++) {
				$(".sr"+i).removeClass("sr"+i);
			}
			searchCount = 0;
			searchIdx = -1;
		}

		function searchChat(frm) {
			var keyword = frm.keyword.value;
			if (keyword == "") {
				pop.openAlert("","검색어를 입력해주세요.");
				return false; 
			} else {
				$(".searched").removeClass("searched");
				for (var i=0; i<chatForm.msgKey.length ; i++) {
					var msg = $(chatForm.msgKey[i]).parent().find(".chat-comment-hidden").html();
					if (msg != undefined) {
						if (msg.indexOf(keyword) > -1) {
							$(chatForm.msgKey[i]).parent().addClass("searched");
							$(chatForm.msgKey[i]).parent().addClass("sr"+searchCount);
							searchCount++;
						}
					}
				}
				return true;
			}
		}

		function searchPrev(dir) {
			$(".search").removeClass("search");
			if (dir=="next") {
				if (searchIdx < $(".searched").length - 1) {
					searchIdx++;
				} else {
					searchIdx = 0;
				}
			} else {
				if (searchIdx == -1) {
					searchIdx = searchCount-1;
				} else if (searchIdx > 0) {
					searchIdx--;
				}
			}
			$(".sr"+searchIdx).addClass("search");

			if ($(".sr"+searchIdx).offset() != undefined) {
				var offSet = $(".sr"+searchIdx).offset().top - 50;
				$(document).scrollTo(offSet);
			} else {
				pop.openAlert("","검색한 내용이 없습니다.");
			}


			/*
			//$(".s"+searchIdx).parent().find(".searched").addClass("bold");
			$("#search"+searchIdx).addClass("bold");
			//$(window).scrollTo("#search0");
			//$(".s"+searchIdx).parent().find(".searched.bold")
			//$("#search0").focus();
			//document.location.href = "#sr"+searchIdx;
			*/
			return false;
		}
	</script>


</head>
<body class="chat-body">

	<!-- 
	<header class="toolbar shadow">
		<form method="post" name="searchForm" onsubmit="return onSearch(this); return false;">
			<div class="tool-table">
				<div class="header-left">
					<div class="search-tool">
						<div class="search-box bbs">
							<img class="search-icon" src="/images/icon_search.png">
							<input type="text" name="keyword" value="" placeholder="대화 내용 검색" onchange="onKeyword();">
						</div>
						<div class="search-btn">
							<button type="submit"><img src="/image/btn_next_down.png"></button>
							<button type="button" onclick="searchPrev('next');"><img src="/image/btn_next_down.png"></button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</header>
	 -->
	
	<form name="chatForm">
		<ul class="chat">

			<!--
			<li class="line"><span class="date-info">2015년 10월 16일 금요일</span></li>

			<li class="bubble-comment">
			<input type="hidden" name="msgKey" value="">
				<div class="buddy-photo">
					<div class="photo" style="background-image:url('/images/profile_sm.png');"></div>
				</div>
				<div class="name-bubble">
					<div class="buddy-name">홍길동</div>
					<div class="buddy-bubble">
						<div class="arrow-left"><img src="/images/bubble_left.png"></div>
						<div class="chat-comment">안녕하세요.<br>하나대투증권 떴어요!! <br> 안녕하세요.<br>하나대투증권 떴어요!! 하나대투증권 떴어요!! 하나대투증권 떴어요!! 하나대투증권 떴어요!!</div>
					</div>
				</div>
				<div class="reg-date">오후 3:23 <span class="no-read">2</span></div>
			</li>

			<li class="my-comment">
			<input type="hidden" name="msgKey" value="">
				<div class="fail-btn">
					<a href="javascript:cancel();"><img src="/images/btn_chat_cancel.png"></a><a href="javascript:resend();"><img src="/images/btn_chat_resend.png"></a>
				</div>
				<div class="reg-date"><span class="no-read">2</span> 오후 3:23</div>
				<div class="name-bubble">
					<div class="buddy-bubble">
						<div class="chat-comment">안녕하세요.<br>하나대투증권 떴어요!! <br> 안녕하세요.<br>하나대투증권 떴어요!! 하나대투증권 떴어요!! 하나대투증권 떴어요!! 하나대투증권 떴어요!!</div>
						<div class="arrow-left"><img src="/images/bubble_right.png"></div>
					</div>
				</div>
			</li>

			<li class="bubble-comment">
			<input type="hidden" name="msgKey" value="">
				<div class="buddy-photo">
					<div class="photo" style="background-image:url('/images/profile_sm.png');"></div>
				</div>
				<div class="name-bubble">
					<div class="buddy-name">홍길동</div>
					<div class="buddy-bubble">
						<div class="arrow-left"><img src="/images/bubble_left.png"></div>
						<div class="chat-comment"><img src="/files/bbs/201509/user1_184c9cc61522b2084fca07127d3fe193.jpg"></div>
					</div>
				</div>
				<div class="reg-date">오후 3:23 <span class="no-read">2</span></div>
			</li>
			-->

		</ul>
	</form>


	<ul class="context">
		<li onclick="contextCopy()">복사하기</li>
		<li onclick="contextDelete()">삭제하기</li>
	</ul>

</body>
</html>