package kr.nomad.mars;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.nomad.mars.dao.CommentDao;
import kr.nomad.mars.dao.ConfigDao;
import kr.nomad.mars.dao.CvriskDao;
import kr.nomad.mars.dao.DayDao;
import kr.nomad.mars.dao.DoctorPointerDao;
import kr.nomad.mars.dao.EperiodDao;
import kr.nomad.mars.dao.MagazineDao;
import kr.nomad.mars.dao.MagazinePageDao;
import kr.nomad.mars.dao.ManageIndexDao;
import kr.nomad.mars.dao.MedExamDao;
import kr.nomad.mars.dao.MonthDao;
import kr.nomad.mars.dao.NoticeDao;
import kr.nomad.mars.dao.PeriodDao;
import kr.nomad.mars.dao.PushDao;
import kr.nomad.mars.dao.QnaDao;
import kr.nomad.mars.dao.UBasicDao;
import kr.nomad.mars.dao.UCntDao;
import kr.nomad.mars.dao.UColDao;
import kr.nomad.mars.dao.UCvriskDao;
import kr.nomad.mars.dao.UGoalDao;
import kr.nomad.mars.dao.UHbDao;
import kr.nomad.mars.dao.UMediDao;
import kr.nomad.mars.dao.UPresDao;
import kr.nomad.mars.dao.UPressDao;
import kr.nomad.mars.dao.UWeightDao;
import kr.nomad.mars.dao.UbloodDao;
import kr.nomad.mars.dao.UserDao;
import kr.nomad.mars.dao.WeekDao;
import kr.nomad.mars.dto.User;
import kr.nomad.mars.dto.UserBasic;
import kr.nomad.mars.dto.UserBlood;
import kr.nomad.mars.dto.UserCnt;
import kr.nomad.mars.dto.UserCol;
import kr.nomad.mars.dto.UserCvrisk;
import kr.nomad.mars.dto.UserGoal;
import kr.nomad.mars.dto.UserMedi;
import kr.nomad.mars.dto.UserPress;
import kr.nomad.mars.dto.UserWeight;
import kr.nomad.mars.dto.Userhb;
import kr.nomad.util.Paging;
import kr.nomad.util.T;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PcController {

	@Autowired
	NoticeDao noticeDao;

	@Autowired
	UserDao userDao;

	@Autowired
	MedExamDao medExamDao;

	@Autowired
	DoctorPointerDao doctorPointerDao;

	@Autowired
	QnaDao qnaDao;

	@Autowired
	UBasicDao uBasicDao;

	@Autowired
	UMediDao uMediDao;

	@Autowired
	UbloodDao ubloodDao;

	@Autowired
	UPressDao uPressDao;

	@Autowired
	UWeightDao uWeightDao;

	@Autowired
	UColDao uColDao;

	@Autowired
	UHbDao uHbDao;

	@Autowired
	CvriskDao cvriskDao;

	@Autowired
	MagazineDao magazineDao;
	@Autowired
	MagazinePageDao magazinepageDao;

	@Autowired
	DayDao daydao;
	@Autowired
	WeekDao weekdao;
	@Autowired
	MonthDao monthdao;
	@Autowired
	PeriodDao periodDao;
	@Autowired
	EperiodDao eperiodDao;
	@Autowired
	CommentDao commentDao;
	@Autowired
	UGoalDao uGoalDao;
	@Autowired
	UCntDao uCntDao;
	@Autowired
	ManageIndexDao manageIndexDao;
	@Autowired
	ConfigDao configDao;
	@Autowired
	UPresDao uPresDao;
	@Autowired
	UCvriskDao uCvriskDao;
	@Autowired
	PushDao pushDao;

	// 페이지당 아이템 갯수
	@Value("#{config['page.itemCountPerPage']}")
	int ITEM_COUNT_PER_PAGE;

	// 페이징당 페이지 갯수
	@Value("#{config['page.pageCountPerPaging']}")
	int PAGE_COUNT_PER_PAGING;

	// 파일 루트
	@Value("#{config['file.root']}")
	String FILE_ROOT;

	String FILE_PATH = "";
	String FILE_LOCAL_PATH = "";

	// 파일 최대크기(Mb)
	@Value("#{config['file.maxSize']}")
	int FILE_MAX_SIZE;

	// 일반회원 혈당 상세보기
	@RequestMapping("/pc/blood_view.go")
	public String bloodEditController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession session, Model model) {

		int count = 0;

		User user;
		UserBasic userBasic = null;
		UserMedi userMedi = null;

		String today = T.getToday(); // 오늘 날짜
		String before = ""; // 기간세팅
		before = T.getDateAdd(today, -30);

		if (userId.equals("")) {
			user = new User();
		} else {
			user = userDao.getUsers(userId);
			userBasic = uBasicDao.getUserBasicView(userId);
			userMedi = uMediDao.getUserMediView(userId);
		}

		// 해당 유저 혈당 리스트
		List<UserBlood> UserBloodlist = ubloodDao.getUserBlood(userId);
		count = ubloodDao.getCount(userId);

		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,PAGE_COUNT_PER_PAGING);
		model.addAttribute("gongavg",ubloodDao.getUserBloodavg(userId, today, before, 1)); // 공복월평균
		model.addAttribute("eatavg",ubloodDao.getUserBloodavg(userId, today, before, 2)); // 식후 월 평균
		model.addAttribute("sleepavg",ubloodDao.getUserBloodavg(userId, today, before, 3)); // 취침전 월평균
		model.addAttribute("UserBloodlist", UserBloodlist);
		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("userMedi", userMedi);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);

		return "/pc/blood_view";
	}

	// 일반회원 혈압 상세보기
	@RequestMapping("/pc/pressure_view.go")
	public String userPressureViewController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession session, Model model) {

		int count = 0;

		User user;
		UserBasic userBasic = null;
		UserMedi userMedi = null;

		String today = T.getToday(); // 오늘 날짜
		String before = ""; // 기간세팅
		before = T.getDateAdd(today, -30);

		if (userId.equals("")) {
			user = new User();
		} else {
			user = userDao.getUsers(userId);
			userBasic = uBasicDao.getUserBasicView(userId);
			userMedi = uMediDao.getUserMediView(userId);
		}

		// 해당 유저 혈압 리스트
		List<UserPress> UserPresslist = uPressDao.getUserPress(userId);
		count = uPressDao.getCount(userId);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("dpress",
				uPressDao.getUserPressavg(userId, today, before, "dplessure")); // 이완기
		model.addAttribute("spress",
				uPressDao.getUserPressavg(userId, today, before, "dplessure")); // 수축기
		model.addAttribute("UserPresslist", UserPresslist);
		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("userMedi", userMedi);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		return "pc/pressure_view";
	}

	// 일반회원 체중 상세보기
	@RequestMapping("/pc/weight_view.go")
	public String userWeightViewController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession session, Model model) {

		int count = 0;

		User user;
		UserBasic userBasic = null;
		UserMedi userMedi = null;

		String today = T.getToday(); // 오늘 날짜
		String before = ""; // 기간세팅
		before = T.getDateAdd(today, -30);

		if (userId.equals("")) {
			user = new User();
		} else {
			user = userDao.getUsers(userId);
			userBasic = uBasicDao.getUserBasicView(userId);
			userMedi = uMediDao.getUserMediView(userId);
		}

		// 해당 유저 체중 리스트
		List<UserWeight> UserWeightlist = uWeightDao.getUserWeight(userId);
		count = uWeightDao.getCount(userId);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("weightavg",
				uWeightDao.getUseravg(userId, today, before)); // 체중 월평균
		model.addAttribute("bmiavg",
				uWeightDao.getUserWeighavg(userId, today, before)); // BMI 월평균
		model.addAttribute("UserWeightlist", UserWeightlist);
		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("userMedi", userMedi);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		return "/pc/weight_view";
	}

	// 일반회원 콜레스테롤 상세보기
	@RequestMapping("/pc/cholesterol_view.go")
	public String userCholesterolViewController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession session, Model model) {

		int count = 0;

		User user;
		UserBasic userBasic = null;
		UserMedi userMedi = null;

		String today = T.getToday(); // 오늘 날짜
		String before = ""; // 기간세팅
		before = T.getDateAdd(today, -30);

		if (userId.equals("")) {
			user = new User();
		} else {
			user = userDao.getUsers(userId);
			userBasic = uBasicDao.getUserBasicView(userId);
			userMedi = uMediDao.getUserMediView(userId);
		}

		// 해당유저 콜레스테롤 리스트
		List<UserCol> UserCollist = uColDao.getUserCol(userId);
		count = uColDao.getUserColcnt(userId);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		// 해당유저 콜레스테롤 최근값 하나만 가지고 오기
		UserCol userCol = uColDao.UserCol(userId);

		model.addAttribute("userCol", userCol);
		model.addAttribute("UserCollist", UserCollist);
		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("userMedi", userMedi);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		return "/pc/cholesterol_view";
	}

	// 일반회원 당화혈색소 상세보기
	@RequestMapping("/pc/hemoglobin_view.go")
	public String userHemoglobinViewController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession session, Model model) {

		int count = 0;

		User user;
		UserBasic userBasic = null;
		UserMedi userMedi = null;

		if (userId.equals("")) {
			user = new User();
		} else {
			user = userDao.getUsers(userId);
			userBasic = uBasicDao.getUserBasicView(userId);
			userMedi = uMediDao.getUserMediView(userId);
		}

		// 해당유저 당화혈색소 리스트
		List<Userhb> Userhblist = uHbDao.getUserhb(userId);
		count = uHbDao.getUserHbacnt(userId);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		// 해당유저 당화혈색소 최근값 하나만 가지고 오기
		Userhb userhb = uHbDao.getUserhbRecent(userId);

		model.addAttribute("userhb", userhb);
		model.addAttribute("Userhblist", Userhblist);
		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("userMedi", userMedi);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		return "/pc/hemoglobin_view";
	}

	// 일반회원 관리목표 상세보기
	@RequestMapping("/pc/goal_view.go")
	public String userGoalViewController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession session, Model model) {

		int count = 0;

		User user;
		UserBasic userBasic = null;
		UserMedi userMedi = null;

		if (userId.equals("")) {
			user = new User();
		} else {
			user = userDao.getUsers(userId);
			userBasic = uBasicDao.getUserBasicView(userId);
			userMedi = uMediDao.getUserMediView(userId);
		}

		// 해당유저 관리목표 리스트
		List<UserGoal> UserGoallist = uGoalDao.getUserGoal(userId);
		count = uGoalDao.getCount(userId);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		// 해당유저 관리목표 최근값 하나만 가지고 오기
		UserGoal Usergoal = uGoalDao.UserGoal(userId);

		model.addAttribute("Usergoal", Usergoal);
		model.addAttribute("UserGoallist", UserGoallist);
		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("userMedi", userMedi);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);

		return "/pc/goal_view";
	}

	// 일반회원 관리주기 상세보기
	@RequestMapping("/pc/week_view.go")
	public String userWeekViewController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession session, Model model) {

		int count = 0;

		User user;
		UserBasic userBasic = null;
		UserMedi userMedi = null;

		if (userId.equals("")) {
			user = new User();
		} else {
			user = userDao.getUsers(userId);
			userBasic = uBasicDao.getUserBasicView(userId);
			userMedi = uMediDao.getUserMediView(userId);
		}

		// 해당유저 관리주기 리스트
		List<UserCnt> UserCntlist = uCntDao.getUserCnt(userId);
		count = uCntDao.getCount(userId);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		// 해당유저 관리주기 최근값 하나만 가지고 오기
		UserCnt Usercnt = uCntDao.UserCnt(userId);

		model.addAttribute("Usercnt", Usercnt);
		model.addAttribute("UserCntlist", UserCntlist);
		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("userMedi", userMedi);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);

		return "/pc/week_view";
	}

	// 일반회원 복약정보 상세보기
	@RequestMapping("/pc/medi_view.go")
	public String userMediController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession session, Model model) {

		int count = 0;

		User user;
		UserBasic userBasic = null;
		UserMedi userMedi = null;

		String today = T.getToday(); // 오늘 날짜
		String before = ""; // 기간세팅
		before = T.getDateAdd(today, -30);

		if (userId.equals("")) {
			user = new User();
		} else {
			user = userDao.getUsers(userId);
			userBasic = uBasicDao.getUserBasicView(userId);
			userMedi = uMediDao.getUserMediView(userId);
		}

		// 해당 유저 혈당 리스트
		List<UserMedi> UserMedilist = uMediDao.getUserMediAdmin(userId);
		count = uMediDao.getCount(userId);

		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("gongavg",
				ubloodDao.getUserBloodavg(userId, today, before, 1)); // 공복월평균
		model.addAttribute("eatavg",
				ubloodDao.getUserBloodavg(userId, today, before, 2)); // 식후 월 평균
		model.addAttribute("sleepavg",
				ubloodDao.getUserBloodavg(userId, today, before, 3)); // 취침전 월
																		// 평균
		model.addAttribute("UserMedilist", UserMedilist);
		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("userMedi", userMedi);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		return "/pc/medi_view";
	}

	// 일반회원 CV_RISK 상세보기
	@RequestMapping("/pc/cv_risk_view.go")
	public String userCVController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession session, Model model) {

		int count = 0;

		User user;
		UserBasic userBasic = null;
		UserMedi userMedi = null;

		String today = T.getToday(); // 오늘 날짜
		String before = ""; // 기간세팅
		before = T.getDateAdd(today, -30);

		if (userId.equals("")) {
			user = new User();
		} else {
			user = userDao.getUsers(userId);
			userBasic = uBasicDao.getUserBasicView(userId);
			// userMedi = uMediDao.getUserMediView(userId);
		}

		// 해당 유저 혈당 리스트
		List<UserCvrisk> UserCvrisklist = uCvriskDao.getUserCvriskAdmin(userId);
		count = uCvriskDao.getCount(userId);

		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("UserCvrisklist", UserCvrisklist);
		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("userMedi", userMedi);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);

		return "/pc/cv_risk_view";
	}

	// 일반회원 병원 목록 등록 수정 처리
	@RequestMapping("/pc/hospital_view.go")
	public String userHopitalDoController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpServletResponse res, Model model) {

		int count = 0;

		User user;
		UserBasic userBasic = null;
		UserMedi userMedi = null;

		String today = T.getToday(); // 오늘 날짜
		String before = ""; // 기간세팅
		before = T.getDateAdd(today, -30);

		if (userId.equals("")) {
			user = new User();
		} else {
			user = userDao.getUsers(userId);
			userBasic = uBasicDao.getUserBasicEdit(userId);
		}

		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);

		return "/pc/hospital_view";
	}
	
	
	// 그래프
	@RequestMapping("/pc/grape.go")
	public String adminAnalystEditController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "diseaseId", required = false, defaultValue = "") String diseaseId,
			HttpSession session, Model model) {

		String startDate = "";
		String endDate = T.getToday();

		startDate = T.getDateAdd(endDate, -7);

		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("diseaseId", diseaseId);
		model.addAttribute("userId", userId);

		return "/m/admin_grape";

	}

}
