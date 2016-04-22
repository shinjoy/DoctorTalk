var timer = {
	timerID : 1, // 타이머를 핸들링하기 위한 전역 변수
	time : 30, // 타이머 시작시의 시간

	/* 타이머를 시작하는 함수 */
	startTimer : function () { 
		timer.timerID = setInterval("timer.decrementTime()", 1000);
	},

	/* 남은 시간을 감소시키는 함수 */
	decrementTime : function() { 
		var x1 = document.getElementById("timer");
		x1.innerHTML = timer.toSec(timer.time);

		if (timer.time > 0) {
			timer.time--;
		} else { 
			// 시간이 0이 되었으므로 타이머를 중지함
			clearInterval(timer.timerID);

			// 시간이 만료되고 나서 할 작업을 여기에 작성
			refresh();
		}
	},

	/* 정수형 숫자(초 단위)를 "시:분:초" 형태로 표현하는 함수 */
	toHourMinSec : function(t) { 
		var hour;
		var min;
		var sec;

		// 정수로부터 남은 시, 분, 초 단위 계산
		hour = Math.floor(t / 3600);
		min = Math.floor( (t-(hour*3600)) / 60 );
		sec = t - (hour*3600) - (min*60);

		// hh:mm:ss 형태를 유지하기 위해 한자리 수일 때 0 추가
		if(hour < 10) hour = "0" + hour;
		if(min < 10) min = "0" + min;
		if(sec < 10) sec = "0" + sec;

		return(min + ":" + sec);
	},
	

	/* 정수형 숫자(초 단위)를 "시:분:초" 형태로 표현하는 함수 */
	toSec : function(t) { 
		var hour;
		var min;
		var sec;

		// 정수로부터 남은 시, 분, 초 단위 계산
		hour = Math.floor(t / 3600);
		min = Math.floor( (t-(hour*3600)) / 60 );
		sec = t - (hour*3600) - (min*60);

		// hh:mm:ss 형태를 유지하기 위해 한자리 수일 때 0 추가
		if(hour < 10) hour = "0" + hour;
		if(min < 10) min = "0" + min;
		if(sec < 10) sec = "0" + sec;

		return(sec);
	}
}
