package kr.nomad.mars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.nomad.mars.dao.BadgeDao;
import kr.nomad.mars.dao.PushDao;
import kr.nomad.mars.dao.ReportHistoryDao;
import kr.nomad.mars.dao.UCntDao;
import kr.nomad.mars.dao.UGoalDao;
import kr.nomad.mars.dao.UPressDao;
import kr.nomad.mars.dao.UWeightDao;
import kr.nomad.mars.dao.UbloodDao;
import kr.nomad.mars.dao.UserDao;
import kr.nomad.mars.dao.UserReportDao;
import kr.nomad.mars.dto.Analisys;
import kr.nomad.mars.dto.Badge;
import kr.nomad.mars.dto.Push;
import kr.nomad.mars.dto.Report;
import kr.nomad.mars.dto.ReportHistory;
import kr.nomad.mars.dto.User;
import kr.nomad.mars.dto.UserCnt;
import kr.nomad.mars.dto.UserGoal;
import kr.nomad.mars.dto.UserReport;
import kr.nomad.util.T;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ibm.icu.util.Calendar;

@Service
public class SchedulerController {

	@Autowired UserDao userdao;
	@Autowired PushDao pushDao;
	@Autowired UCntDao ucntDao;
	@Autowired BadgeDao badgeDao;
	@Autowired UCntDao ucntdao;

	@Autowired
	UbloodDao ublooddao;

	@Autowired
	UPressDao upressdao;

	@Autowired
	UWeightDao uweightdao;
	@Autowired UGoalDao ugoaldao;

	 @Autowired UserReportDao userReportDao;
	 @Autowired ReportHistoryDao reportHistoryDao;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	//@Scheduled(fixedDelay = 10000) // 5초마다 실행한다.
	@Scheduled(cron = "1 * * * * *") // 매분마다 실행한다. 초 분 시 일 월 주(년)
	public void eatMedicineScheduler() {
		
		// 매 분마다(매분 1초) 복약 알림 푸시를 발송한다.

		String time = T.getNowFomat2();
		String week = T.getWeekday();
		
		logger.info("time "+ time);
		logger.info("week "+ week);
		
		time = time.replace("오전", "AM").replace("오후", "PM");
	
		List list = userdao.getUserListEatMedicine(time,week);
		String message = "약 드실 시간입니다.";
		
		for (int i=0; i<list.size(); i++) {
			User user = (User) list.get(i);
			Badge bg = new Badge();
			bg.setBadgeStatus(1);
			bg.setBadgeType(1);
			bg.setUserId(user.getUserId());
			badgeDao.addBadge(bg);
			
			if (user.getPushkey().equals("") == false&&user.getUsePushservice()==1) {
			
				Push push = new Push();
				push.setBadge(1);
				push.setOs(user.getOsType());
				push.setPushKey(user.getPushkey());
				push.setMsgType(Push.MSG_TYPE_EAT_MED);
				push.setUserid(user.getUserId());
				push.setStatus(0);
				push.setServiceId("RECOVER");
				push.setPushType(1);							
				push.setMsg(message);
				push.setMsgKey("0");
				pushDao.addPush(push);
			}
			
		}
	}


	//@Scheduled(fixedDelay = 10000) // 5초마다 실행한다.
	@Scheduled(cron = "0 0 13 * * *") // 매분마다 실행한다. 초 분 시 일 월 주(년)
	public void noCheckedScheduler() {
		
	
		// 오후 1시에 기간내 미입력 대상자에게 푸시 발송
		String today = T.getToday(); // "2015-10-12"
		String bloodBefore =T.getDateAdd(today, -2); //혈당
		String pressBefore = T.getDateAdd(today, -6); //혈압
		String weigthBefore = T.getDateAdd(today, -30); //체중
		
		List list = userdao.getUserListEperiod(today,bloodBefore,pressBefore,weigthBefore);
		String message = "데이터를 입력해주세요.";
		
		for (int i=0; i<list.size(); i++) {
			User user = (User) list.get(i);
			
			Badge bg = new Badge();
			bg.setBadgeStatus(1);
			bg.setBadgeType(2);
			bg.setUserId(user.getUserId());
			badgeDao.addBadge(bg);
			
			if (user.getPushkey().equals("") == false&&user.getUsePushservice()==1) {
				//UserCnt uc = ucntDao.getUserCntList(user.getUserId());
				
				
					Push push = new Push();
					
					push.setBadge(1);
					push.setOs(user.getOsType());
					push.setPushKey(user.getPushkey());
					push.setMsgType(Push.MSG_TYPE_NOT_CHECKED);
					push.setUserid(user.getUserId());
					push.setStatus(0);
					push.setServiceId("RECOVER");
					push.setPushType(1);							
					push.setMsg(message);
					push.setMsgKey("0");
					pushDao.addPush(push);
					
			}
		}
	}

	//@Scheduled(fixedDelay = 10000) // 5초마다 실행한다.
	@Scheduled(cron = "0 0 10 * * *") // 매분마다 실행한다. 초 분 시 일 월 주(년)
	public void noCvScheduler() {
		
		// 오전 10시에 가입한지 1일, 1년 된 사용자에게 CV-Risk 알림 발송
		
		String today = T.getToday();
		List list =userdao.getUserListcvrisk(today);
		String message = "CV-RISK 를 등록해 주세요";
		for (int i=0; i<list.size(); i++) {
			User user = (User) list.get(i);
			Badge bg = new Badge();
			bg.setBadgeStatus(1);
			bg.setBadgeType(2);
			bg.setUserId(user.getUserId());
			badgeDao.addBadge(bg);
			
			if (user.getPushkey().equals("") == false&&user.getUsePushservice()==1) {
				
				Push push = new Push();
				
				push.setBadge(1);
				push.setOs(user.getOsType());
				push.setPushKey(user.getPushkey());
				push.setMsgType(Push.MSG_TYPE_CV_RISK);
				push.setUserid(user.getUserId());
				push.setStatus(0);
				push.setServiceId("RECOVER");
				push.setPushType(1);							
				push.setMsg(message);
				push.setMsgKey("0");
				pushDao.addPush(push);
			}
			
		}
		
	}

	

	//@Scheduled(fixedDelay = 10000) // 5초마다 실행한다.
	@Scheduled(cron = "0 0 15 * * *") // 매분마다 실행한다. 초 분 시 일 월 주(년)
	public void noPeriodScheduler() {
		
		// 오후 3시에 관리주기 변경 대상자에게 알림
		String today = T.getToday();
		String bloodBefore =T.getDateAdd(today, -2); //혈당
		
		String before4 = T.getDateAdd(today, -6); //혈압
		String after3 = T.getDateAdd(before4, -1); 
		String before3 = T.getDateAdd(after3, -6);
		String after2 = T.getDateAdd(before3, -1);
		String before2 = T.getDateAdd(after2, -6);
		String after = T.getDateAdd(before2, -1);
		String before = T.getDateAdd(after, -6);
		
		//혈압 푸시 
		List list = userdao.getUserpressListCperiod(today,before,after,before2,after2,before3,after3,before4);
	
		for (int i=0; i<list.size(); i++) {
			User user = (User) list.get(i);
			String message = "관리주기 변경입니다.";
			UserCnt uc = ucntDao.getUserCntList(user.getUserId());
			
			if(uc!=null){
				if(uc.getPressStatus()==0){//변경 안한사람이면 푸시보내기
					
					Badge bg = new Badge();
					bg.setBadgeStatus(1);
					bg.setBadgeType(2);
					bg.setUserId(user.getUserId());
					badgeDao.addBadge(bg);
					
					if (user.getPushkey().equals("") == false&&user.getUsePushservice()==1) {
					
						
							Push push = new Push();
							push.setBadge(1);
							push.setOs(user.getOsType());
							push.setPushKey(user.getPushkey());
							push.setMsgType(Push.MSG_TYPE_CHANGE_PERIOD);
							push.setUserid(user.getUserId());
							push.setStatus(0);
							push.setServiceId("RECOVER");
							push.setPushType(1);							
							push.setMsg(message);
							push.setMsgKey("0");
							pushDao.addPush(push);
					}
			  }
		   }
			
		}
		
		//혈당 푸시 
		List bloodlist = userdao.getUserbloodListCperiod(today,bloodBefore);
	
		for (int i=0; i<bloodlist.size(); i++) {
			User user = (User) bloodlist.get(i);
			String message = "관리주기 변경입니다.";
			UserCnt uc = ucntDao.getUserCntList(user.getUserId());
			if(uc!=null){
				if(uc.getBloodStatus()==0){
					Badge bg = new Badge();
					bg.setBadgeStatus(1);
					bg.setBadgeType(2);
					bg.setUserId(user.getUserId());
					badgeDao.addBadge(bg);
					
					
					if (user.getPushkey().equals("") == false&&user.getUsePushservice()==1) {
			
			
						Push push = new Push();
						
						push.setBadge(1);
						push.setOs(user.getOsType());
						push.setPushKey(user.getPushkey());
						push.setMsgType(Push.MSG_TYPE_CHANGE_PERIOD);
						push.setUserid(user.getUserId());
						push.setStatus(0);
						push.setServiceId("RECOVER");
						push.setPushType(1);							
						push.setMsg(message);
						push.setMsgKey("0");
						pushDao.addPush(push);
					}
				}
			}
			
		}
	}
	

	//@Scheduled(fixedDelay = 10000) // 5초마다 실행한다.
	@Scheduled(cron = "0 0 1 * * *") // 매분마다 실행한다. 초 분 시 일 월 주(년)
	public void MonthReportScheduler() {
		
		// 오전 1시 기록. 월간레포트
		
		String todaymonet = T.getMonth();
		
		List <User>list =userdao.getUserMonthReportList(todaymonet+"-");
		
		for(int i=0;i<list.size();i++){
			User user =list.get(i);
			String userId = user.getUserId();
			System.out.println(user.getUserId());
			
			String before="";
			Calendar date = Calendar.getInstance();
			UserCnt uc = new UserCnt();
			uc = ucntdao.getUserCntList(userId);
			

			before = T.getBeforeYearMonthByYM(1);
			String b = before +"-01";
			String [] arr =before.split("-");
			int lastday = T.getLastMonthday(before);
			
			String tafter = before+"-"+lastday;
		
			int presscnt = upressdao.getmonthCount(userId, b,tafter); // 혈압갯수
			int bloodcnt = ublooddao.getmonthCount(userId, b,tafter);// 혈당갯수
			int weightcnt = uweightdao.getmonthCount(userId, b,tafter);// 체중갯수

			UserReport ur = new UserReport();
			ur.setTotalBloodCnt(bloodcnt);
			ur.setTotalPressureCnt(presscnt);
			ur.setTotalWeightCnt(weightcnt);
			ur.setGoalBloodCnt(uc.getBcnt());
			ur.setGoalPressureCnt(uc.getPcnt());
			ur.setGoalWeightCnt(uc.getWcnt());
			ur.setUserId(userId);
			ur.setType(1);
			userReportDao.addUserReport(ur);
			
			ReportHistory rh = new ReportHistory();
			rh.setUserId(userId);
			rh.setBloodCount(bloodcnt);
			rh.setBloodTargetCount(uc.getBcnt());
			rh.setBloodPercent((bloodcnt/uc.getBcnt())*100);
			rh.setPressureCount(presscnt);
			rh.setPressureTargetCount(uc.getPcnt());
			rh.setPressurePercent((presscnt/uc.getPcnt())*100);
			rh.setWeightCount(weightcnt);
			rh.setWeightTargetCount(uc.getWcnt());
			rh.setWeightPercent((weightcnt/uc.getWcnt())*100);
			
			reportHistoryDao.addReportHistory(rh);
		}
		
	}
	
	@Scheduled(cron = "1 1 1 * * *") // 매분마다 실행한다. 초 분 시 일 월 주(년)
	public void weekReportScheduler() {
		
		// 오전 1시 기록. 주간레포트
		
		String todaymonet = T.getMonth();
		System.out.println("ddddd");
		List <User>list =userdao.getUserWeekReportList();
		
		for(int i=0;i<list.size();i++){
			User user =list.get(i);
			String userId = user.getUserId();
			System.out.println(userId);
			String today= T.getToday();
			
			String before = T.getDateAdd(today, -6);

			int weekbloodcnt = ublooddao.getUserweekBloodcnt(userId, today, before);
			int weekpresscnt = upressdao.getUserPresscnt(userId, today, before);
			int weekweightcnt = uweightdao.getUserWeightcnt(userId, today, before);

			
			int gong = ublooddao.getUserBloodcnt(userId, today, before, 1); // 공복
																				// 측정횟수
			int sik = ublooddao.getUserBloodcnt(userId, today, before, 2);// 식후
																				// 측정횟수
			int sleep = ublooddao.getUserBloodcnt(userId, today, before, 3);// 취침전
																				// 측정횟수
			UserGoal ug = ugoaldao.getuserGoal(userId);
			UserCnt uc = ucntdao.getUserCntList(userId);
			int gongavg = ublooddao.getUserBloodavg(userId, today, before, 1);// 내평균
			int sikavg = ublooddao.getUserBloodavg(userId, today, before, 2);// 내평균
			int sleepavg = ublooddao.getUserBloodavg(userId, today, before, 3);// 내평균
			int mydavg = upressdao.getUserPressavg(userId, today, before, "dplessure");// 이완
			int mysavg = upressdao.getUserPressavg(userId, today, before, "splessure");// 수축
			double mybmiavg = uweightdao.getUserWeighavg(userId, today, before); // 평균
			
			UserReport ur= new UserReport();
			ur.setUserId(userId);
			ur.setType(2);
			
			ur.setBeforeEatBloodCnt(gong); //공복 횟수
			ur.setAfterEatBloodCnt(sik); //식후 횟수
			ur.setBeforeSleepBloodCnt(sleep); //취침전횟수
			ur.setTotalBloodCnt(gong+sik+sleep); //총횟수
			ur.setGoalBloodCnt(uc.getBcnt()); //혈당 측정 목표횟수
			ur.setBeforeEatBlood(gongavg); //공복 평균
			ur.setAfterEatBlood(sikavg);  //식후평균
			ur.setBeforeSleepBlood(sleepavg); //취침전평균
			ur.setGoalBeforeEatSblood(ug.getGoalsMblood()); //공복목표(작은값)
			ur.setGoalBeforeEatBblood(ug.getGoalbMblood()); //공복목표(큰값)
			ur.setGoalAfterEatBblood(ug.getGoalEblood()); //식후 목표
			ur.setGoalBeforeSleepBblood(ug.getGoalSblood()); //취침전목표
			
			
			ur.setTotalPressureCnt(weekpresscnt); //혈압측정횟수
			ur.setSpressure(mysavg);//수축평균
			ur.setBpressure(mydavg);//이완평균
			ur.setGoalSpressure(ug.getGoalbPre()); //수축목표
			ur.setGoalBpressure(ug.getGoalsPre());//이완목표
			ur.setGoalPressureCnt(uc.getPcnt()); //목표측정횟수
			
			ur.setTotalWeightCnt(weekweightcnt); //체중측정횟수
			ur.setBmi(mybmiavg);//bmi평균
			ur.setGoalSbmi(ug.getGoalsBmi());//bmi작은값
			ur.setGoalBbmi(ug.getGoalbBmi());//bmi 큰값
			ur.setGoalWeightCnt(uc.getWcnt()); //목표측정횟수
			
			userReportDao.addUserReport(ur);
			
			ReportHistory rh = new ReportHistory();
			rh.setUserId(userId);
			rh.setBloodCount(weekbloodcnt);
			rh.setBloodTargetCount(uc.getBcnt());
			rh.setBloodPercent((weekbloodcnt/uc.getBcnt())*100);
			rh.setPressureCount(weekpresscnt);
			rh.setPressureTargetCount(uc.getPcnt());
			rh.setPressurePercent((weekpresscnt/uc.getPcnt())*100);
			rh.setWeightCount(weekweightcnt);
			rh.setWeightTargetCount(uc.getWcnt());
			rh.setWeightPercent((weekweightcnt/uc.getWcnt())*100);
			
			reportHistoryDao.addReportHistory(rh);
			
			

		}
	}
	
	
		@Scheduled(cron = "0 0 11 * * *") // 매분마다 실행한다. 초 분 시 일 월 주(년)
		public void MonthReportPushScheduler() {
			
			// 오전 11시 발송. 월간레포트
			
			String todaymonet = T.getMonth();
			
			List <User>list =userdao.getUserMonthReportList(todaymonet+"-");
			
			for(int i=0;i<list.size();i++){
				User user =list.get(i);
				String userId = user.getUserId();
				if (user.getPushkey().equals("") == false&&user.getUsePushservice()==1) {
					
					
					Push push = new Push();
					
					push.setBadge(1);
					push.setOs(user.getOsType());
					push.setPushKey(user.getPushkey());
					push.setMsgType(Push.MSG_TYPE_MONTHREPORT);
					push.setUserid(user.getUserId());
					push.setStatus(0);
					push.setServiceId("RECOVER");
					push.setPushType(1);							
					push.setMsg("월간 레포트가 발송 되었습니다.");
					push.setMsgKey("0");
					pushDao.addPush(push);
				}
			}
			
		}
		
		@Scheduled(cron = "1 1 11 * * *") // 매분마다 실행한다. 초 분 시 일 월 주(년)
		public void weekReportPushScheduler() {
			
			// 오전 11시 발송. 주간레포트
			
			String todaymonet = T.getMonth();
			System.out.println("ddddd");
			List <User>list =userdao.getUserWeekReportList();
			
			for(int i=0;i<list.size();i++){
				User user =list.get(i);
				String userId = user.getUserId();
					if (user.getPushkey().equals("") == false&&user.getUsePushservice()==1) {
					
					
					Push push = new Push();
					
					push.setBadge(1);
					push.setOs(user.getOsType());
					push.setPushKey(user.getPushkey());
					push.setMsgType(Push.MSG_TYPE_WEEKREPORT);
					push.setUserid(user.getUserId());
					push.setStatus(0);
					push.setServiceId("RECOVER");
					push.setPushType(1);							
					push.setMsg("주간 레포트가 발송 되었습니다.");
					push.setMsgKey("0");
					pushDao.addPush(push);
				}
				
				

			}
		}
}
