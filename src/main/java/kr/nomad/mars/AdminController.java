package kr.nomad.mars;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

import encrypt.Sha256Util;
import kr.nomad.mars.dao.ChatCounselDao;
import kr.nomad.mars.dao.ChatMemberDao;
import kr.nomad.mars.dao.ChatMsgDao;
import kr.nomad.mars.dao.ChatRoomDao;
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
import kr.nomad.mars.dao.ReportHistoryDao;
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
import kr.nomad.mars.dto.ChatMsg;
import kr.nomad.mars.dto.ChatRoom;
import kr.nomad.mars.dto.Comment;
import kr.nomad.mars.dto.Config;
import kr.nomad.mars.dto.Cvrisk;
import kr.nomad.mars.dto.Day;
import kr.nomad.mars.dto.DayGroup;
import kr.nomad.mars.dto.DoctorPointer;
import kr.nomad.mars.dto.Eperiod;
import kr.nomad.mars.dto.EperiodGroup;
import kr.nomad.mars.dto.Magazine;
import kr.nomad.mars.dto.MagazinePage;
import kr.nomad.mars.dto.ManageIndex;
import kr.nomad.mars.dto.MedExam;
import kr.nomad.mars.dto.Month;
import kr.nomad.mars.dto.Notice;
import kr.nomad.mars.dto.Period;
import kr.nomad.mars.dto.Push;
import kr.nomad.mars.dto.Qna;
import kr.nomad.mars.dto.User;
import kr.nomad.mars.dto.UserBasic;
import kr.nomad.mars.dto.UserBlood;
import kr.nomad.mars.dto.UserCnt;
import kr.nomad.mars.dto.UserCol;
import kr.nomad.mars.dto.UserCvrisk;
import kr.nomad.mars.dto.UserGoal;
import kr.nomad.mars.dto.UserMedi;
import kr.nomad.mars.dto.UserPres;
import kr.nomad.mars.dto.UserPress;
import kr.nomad.mars.dto.UserWeight;
import kr.nomad.mars.dto.Userhb;
import kr.nomad.mars.dto.Week;
import kr.nomad.mars.dto.WeekGroup;
import kr.nomad.util.F;
import kr.nomad.util.ImageUtil;
import kr.nomad.util.Paging;
import kr.nomad.util.Response;
import kr.nomad.util.T;
import kr.nomad.util.XlsxWriter;
import kr.nomad.util.file.UniqFileRenamePolicy;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class AdminController {

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
	
	@Autowired
	ChatRoomDao chatRoomDao;
	@Autowired
	ChatMemberDao chatMemberDao;
	@Autowired
	ChatMsgDao chatMsgDao;
	@Autowired
	ReportHistoryDao reportHistoryDao;

	@Autowired
	ChatCounselDao chatCounselDao;

	

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

	// 관리자 메인
	@RequestMapping("/admin/main.go")
	public String adminMainController(HttpSession session, Model model) {

		int topCount = 8;

		// List<Notice> noticeList = noticeDao.getNoticeTopList(topCount);
		// List<Faq> faqList = faqDao.getFaqTopList(topCount);
		// List<Analysis> anaList = analysisDao.getAnalysisTopList(topCount);
		//
		// model.addAttribute("noticeList", noticeList);
		// model.addAttribute("faqList", faqList);
		// model.addAttribute("anaList", anaList);
		return "/admin/main";
	}

	// 회원 관리 > 상담원
	@RequestMapping("/admin/user/admin.go")
	public String userAdminController(HttpSession session, Model model) {

		return "/admin/user/admin";
	}

	// 회원 관리 > 상담원 리스트
	@RequestMapping("/admin/user/admin_list.go")
	public String adminListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<User> list = null;
		int count = 0;

		list = userDao.getCounselorList(keyword, gender, age, page,
				ITEM_COUNT_PER_PAGE);
		count = userDao.getCounselorCount(keyword, gender, age);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/user/admin_list";
	}

	// 상담원 보기
	@RequestMapping("/admin/user/admin_detail.go")
	public String adminDetailController(

			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpSession session, Model model) {

		User uu = userDao.getUser(userId);

		model.addAttribute("user", uu);
		return "admin/user/admin_detail";
	}

	// 상담원 수정등록 페이지
	@RequestMapping("/admin/user/admin_edit.go")
	public String admineditController(

			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpSession session, Model model) {
		int kind = 0;
		if (!userId.equals("")) {
			kind = 1;
		}
		User uu = userDao.getUser(userId);
		model.addAttribute("kind", kind);
		model.addAttribute("user", uu);
		return "admin/user/admin_edit";
	}

	// 상담원 수정등록
	@RequestMapping("/admin/user/admin_edit_do.go")
	public String admineditDoController(
			
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "userName", required = false, defaultValue = "") String userName,
			@RequestParam(value = "birthday", required = false, defaultValue = "") String birthday,
			@RequestParam(value = "userType", required = false, defaultValue = "0") int userType,
			@RequestParam(value = "password", required = false, defaultValue = "0") String password,
			@RequestParam(value = "kind", required = false, defaultValue = "0") int kind,
			HttpSession session, Model model, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		User uu = new User();
		uu.setUserId(userId);
		uu.setBirthday(birthday);
		uu.setUserType(userType);
		uu.setUserName(userName);
	
		try {
			String enPw = Sha256Util.encryptPassword(password);
			uu.setPassword(enPw);
			if (kind == 1) {// 수정
				userDao.updateadmin(uu);
				map.put("result", true);
				map.put("msg", "수정되었습니다.");
			} else {
				userDao.addUser(uu);
				map.put("result", true);
				map.put("msg", "등록되었습니다.");
			}

		} catch (Exception e) {
			map.put("result", false);
			map.put("msg", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 상담원 삭제
	@RequestMapping("/admin/user/admin_delete.go")
	public String adminDeleteController(

			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpSession session, Model model, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userDao.deleteUser(userId);
			;

			map.put("result", true);
			map.put("msg", "삭제되었습니다");
		} catch (Exception e) {
			map.put("result", false);
			map.put("msg", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 상담원 엑셀 다운로드
	@RequestMapping("/admin/user/counselor_list_excel.go")
	public ModelAndView CounselorListExcelController(Model model) {

		List list = userDao.getCounselorUser();

		SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "code_" + formatdate.format(new Date());
		XlsxWriter writer = new XlsxWriter(FILE_ROOT + "/files/excel/"
				+ fileName + ".xls");

		List title = new ArrayList();
		List contents = new ArrayList();

		title.add("이름");
		title.add("아이디");
		title.add("생년월일");
		// title.add("질환");
		// title.add("최근진단등록");
		// title.add("가입일");

		for (int i = 0; i < list.size(); i++) {
			User user = (User) list.get(i);

			List dataList = new ArrayList();
			dataList.add(user.getUserName());
			dataList.add(user.getUserId());
			dataList.add(user.getBirthday());
			// dataList.add("");
			// dataList.add("");
			// dataList.add(user.getRegDate());

			contents.add(dataList);
		}

		writer.writeFile(title, contents);

		File file = new File(FILE_ROOT + "/files/excel/" + fileName + ".xls");

		return new ModelAndView("fileDownloadView", "file", file);
	}

	// 회원 관리 > 일반회원
	@RequestMapping("/admin/user/user.go")
	public String usertController(HttpSession session, Model model) {

		return "/admin/user/user";
	}

	// 회원 관리 > 일반회원 리스트
	@RequestMapping("/admin/user/user_list.go")
	public String usertListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<User> list = null;
		int count = 0;

		list = userDao.getUserList(keyword, gender, age, page,ITEM_COUNT_PER_PAGE);
		count = userDao.getCount(keyword, gender, age);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/user/user_list";
	}

	// 탈퇴회원 삭제
	@RequestMapping("/admin/user/user_delete_do.go")
	public String companyDeleteDoController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			  User uu = userDao.getUser(userId); 
			  List<UserPres> upList = uPresDao.getUserPresList(userId);
			  uBasicDao.deleteUserBasic(userId);
			  ubloodDao.deleteUserBlood(userId); 
			  uCntDao.deleteUserCnt(userId);
			  uCvriskDao.deleteUserCvrisk(userId);
			  uMediDao.deleteUserMedi2(userId);
			  uPresDao.deleteUserPres2(userId);
			  uPressDao.deleteUserPress(userId);
			  userDao.deleteUser(userId);
			  uWeightDao.deleteUserWeight(userId);
			  uGoalDao.deleteUserGoal(userId); 
			  uColDao.deleteUserCol(userId);
			  uHbDao.deleteUserhb(userId); //프사지우기 
			  /*
			  if(uu.getFileName()!=null ||uu.getFileName()!=""){ 
				  filedelete(uu.getFileName());
			  
			  }
			  //처방전 이미지 지우기 
			  for(int i=0;i<upList.size();i++){ UserPres up =upList.get(i);
				  if(up.getFileName()!=null ||up.getFileName()!=""){ 
					  filedelete(up.getFileName());
				  }
			  } 
			 */
			map.put("message", "사용자가 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "사용자가 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 일반회원 엑셀 다운로드
	@RequestMapping("/admin/user/use_list_excel.go")
	public ModelAndView userListExcelController(Model model) {

		List list = userDao.getUser();

		SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "code_" + formatdate.format(new Date());
		XlsxWriter writer = new XlsxWriter(FILE_ROOT + "/files/excel/"+ fileName + ".xls");

		List title = new ArrayList();
		List contents = new ArrayList();

		title.add("이름");
		title.add("아이디");
		title.add("생년월일");
		title.add("혈당 질환 여부");
		title.add("혈압 질환 여부");
		title.add("콜레스테롤 질환 여부");
		title.add("비만 여부");
		title.add("가입일");

		for (int i = 0; i < list.size(); i++) {
			User user = (User) list.get(i);

			List dataList = new ArrayList();
			dataList.add(user.getUserName());
			dataList.add(user.getUserId());
			dataList.add(user.getBirthday());
			
			if(user.getBlood() == "blood"){
				dataList.add("당뇨");
			}

			if(user.getPress() == "press" ){
				dataList.add("고혈압");
			}
			
			if(user.getCol() =="col"){
				dataList.add("콜레스테롤");
			}
			
			if(user.getHeiwieght() =="heiwieght"){
				dataList.add("비만");
			}
			dataList.add(user.getRegDate());
			contents.add(dataList);
		}

		writer.writeFile(title, contents);

		File file = new File(FILE_ROOT + "/files/excel/" + fileName + ".xls");

		return new ModelAndView("fileDownloadView", "file", file);
	}

	// 일반회원 혈당 상세보기
	@RequestMapping("/admin/user/user_view.go")
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
		String ym = today.substring(0, 7);
		
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
		model.addAttribute("gongavg",ubloodDao.getUserBloodavg(userId, ym, 1)); // 공복월평균
		model.addAttribute("eatavg",ubloodDao.getUserBloodavg(userId, ym, 2)); // 식후 월 평균
		model.addAttribute("sleepavg",ubloodDao.getUserBloodavg(userId, ym, 3)); // 취침전 월평균
		model.addAttribute("UserBloodlist", UserBloodlist);
		model.addAttribute("ym", ym);
		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("userMedi", userMedi);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		model.addAttribute("today", today);
		model.addAttribute("before", before);
		
		return "admin/user/user_view";
	}
	
	
	// 그래프
	@RequestMapping("/admin/user/grape.go")
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

	
	//일반회원 혈당 추가하기
	@RequestMapping("/admin/user/user_blood.go")
	public String userViewController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "bloodNum", required = false, defaultValue = "1") int bloodNum,
			@RequestParam(value = "bloodTime", required = false, defaultValue = "1") int bloodTime,
			@RequestParam(value = "equip", required = false, defaultValue = "1") int equip,
			HttpServletResponse res, 
			HttpSession session, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

				UserBlood userBlood = new UserBlood();
				userBlood.setUserId(userId);
				userBlood.setBloodNum(bloodNum);
				userBlood.setBloodTime(bloodTime);
				userBlood.setEquip(equip);
				ubloodDao.addUserBlood(userBlood);
				result = true;
				message = "등록되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("userId", userId);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	
	
	
	
	
	// 일반회원 혈압 상세보기
	@RequestMapping("/admin/user/user_view_pressure.go")
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
		String ym = today.substring(0, 7);

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
				uPressDao.getUserPressavg(userId, ym, "dplessure")); // 이완기
		model.addAttribute("spress",
				uPressDao.getUserPressavg(userId, ym, "splessure")); // 수축기
		model.addAttribute("UserPresslist", UserPresslist);
		model.addAttribute("ym", ym);
		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("userMedi", userMedi);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		model.addAttribute("today", today);
		model.addAttribute("before", before);
		return "admin/user/user_view_pressure";
	}
	
	
	//일반회원 혈압 추가하기
	@RequestMapping("/admin/user/user_press.go")
	public String pressEditController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "splessure", required = false, defaultValue = "1") int splessure,
			@RequestParam(value = "dplessure", required = false, defaultValue = "1") int dplessure,
			@RequestParam(value = "equip", required = false, defaultValue = "1") int equip,
			HttpServletResponse res, 
			HttpSession session, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

				UserPress userPress = new UserPress();
				userPress.setUserId(userId);
				userPress.setSplessure(splessure);
				userPress.setDplessure(dplessure);
				userPress.setEquip(equip);
				uPressDao.addUserPress(userPress);
				result = true;
				message = "등록되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("userId", userId);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	
	// 일반회원 체중 상세보기
	@RequestMapping("/admin/user/user_view_weight.go")
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
		String ym = today.substring(0, 7);

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
				uWeightDao.getUseravg(userId, ym)); // 체중 월평균
		model.addAttribute("bmiavg",
				uWeightDao.getUserWeighavg(userId, ym)); // BMI 월평균
		model.addAttribute("UserWeightlist", UserWeightlist);
		model.addAttribute("ym", ym);
		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("userMedi", userMedi);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		model.addAttribute("today", today);
		model.addAttribute("before", before);
		return "admin/user/user_view_weight";
	}
	
	//일반회원 체중 추가하기
	@RequestMapping("/admin/user/user_weight.go")
	public String weightEditController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "height", required = false, defaultValue = "1") int height,
			@RequestParam(value = "weightNum", required = false, defaultValue = "1") int weightNum,
			@RequestParam(value = "bbmi", required = false, defaultValue = "1") int bbmi,
			@RequestParam(value = "tbw", required = false, defaultValue = "1") int tbw,
			@RequestParam(value = "muscle", required = false, defaultValue = "1") int muscle,
			@RequestParam(value = "bmd", required = false, defaultValue = "1") int bmd,
			@RequestParam(value = "equip", required = false, defaultValue = "1") int equip,
			HttpServletResponse res, 
			HttpSession session, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

				int bmi = weightNum/(height*height);
			
				UserWeight userWeight = new UserWeight();
				userWeight.setUserId(userId);
				userWeight.setWeightNum(weightNum);
				userWeight.setBmi(bmi);
				userWeight.setBbmi(bbmi);
				userWeight.setTbw(tbw);
				userWeight.setMuscle(muscle);
				userWeight.setBmd(bmd);
				userWeight.setEquip(equip);
				uWeightDao.addUserWeight(userWeight);
				result = true;
				message = "등록되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("userId", userId);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	
	// 일반회원 콜레스테롤 상세보기
	@RequestMapping("/admin/user/user_view_cholesterol.go")
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
		model.addAttribute("today", today);
		model.addAttribute("before", before);
		return "admin/user/user_view_cholesterol";
	}
	
	//일반회원 콜레스테롤 추가하기
	@RequestMapping("/admin/user/user_col.go")
	public String colEditController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "hdl", required = false, defaultValue = "1") int hdl,
			@RequestParam(value = "tg", required = false, defaultValue = "1") int tg,
			@RequestParam(value = "ldl", required = false, defaultValue = "1") int ldl,
			HttpServletResponse res, 
			HttpSession session, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			
				UserCol userCol = new UserCol();
				userCol.setUserId(userId);
				userCol.setHdl(hdl);
				userCol.setTg(tg);
				userCol.setLdl(ldl);
				uColDao.addUserCol(userCol);
				result = true;
				message = "등록되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("userId", userId);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	
	// 일반회원 당화혈색소 상세보기
	@RequestMapping("/admin/user/user_view_hemoglobin.go")
	public String userHemoglobinViewController(
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
		model.addAttribute("today", today);
		model.addAttribute("before", before);
		return "admin/user/user_view_hemoglobin";
	}
	
	
	//일반회원 당화혈색소 추가하기
	@RequestMapping("/admin/user/user_hemo.go")
	public String hemoEditController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "hbaNum", required = false, defaultValue = "1") int hbaNum,
			HttpServletResponse res, 
			HttpSession session, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			
				Userhb userhb = new Userhb();
				userhb.setUserId(userId);
				userhb.setHbaNum((double)hbaNum);
				uHbDao.addUserhb(userhb);
				result = true;
				message = "등록되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("userId", userId);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	
	// 일반회원 수정 삭제
	@RequestMapping("/admin/user/user_edit.go")
	public String userListController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpSession session, Model model) {

		User user = null;

		UserBasic userBasic = null;

		if (userId.equals("")) {
			userBasic = new UserBasic();
			user = new User();
		} else {
			userBasic = uBasicDao.getUserBasicEdit(userId);
			user = userDao.getUsers(userId);
		}

		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);

		return "/admin/user/user_edit";
	}

	// 일반회원 등록 수정 처리
	@RequestMapping("/admin/user/user_edit_do.go")
	public String userEditDoController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "haveHistory", required = false, defaultValue = "") String haveHistory,
			@RequestParam(value = "familyHistory", required = false, defaultValue = "") String familyHistory,
			@RequestParam(value = "drugHistory", required = false, defaultValue = "") String drugHistory,
			@RequestParam(value = "oralKind", required = false, defaultValue = "") String oralKind,
			@RequestParam(value = "oralAmount", required = false, defaultValue = "") String oralAmount,
			@RequestParam(value = "oralUse", required = false, defaultValue = "") String oralUse,
			@RequestParam(value = "insulinKind", required = false, defaultValue = "") String insulinKind,
			@RequestParam(value = "insulinAmount", required = false, defaultValue = "") String insulinAmount,
			@RequestParam(value = "insulinUse", required = false, defaultValue = "") String insulinUse,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			UserBasic userBasic = uBasicDao.getUserBasic(userId);
			int seq  = userBasic.getBasicSeq();
			if (seq == 0) {
				userBasic.setUserId(userId);
				userBasic.setHaveHistory(haveHistory);
				userBasic.setFamilyHistory(familyHistory);
				userBasic.setDrugHistory(drugHistory);
				userBasic.setOralKind(oralKind);
				userBasic.setOralAmount(oralAmount);
				userBasic.setOralUse(oralUse);
				userBasic.setInsulinKind(insulinKind);
				userBasic.setInsulinAmount(insulinAmount);
				userBasic.setInsulinUse(insulinUse);
				userBasic.setComment(comment);
				uBasicDao.addUserBasic(userBasic);
			} else {
				userBasic.setUserId(userId);
				userBasic.setHaveHistory(haveHistory);
				userBasic.setFamilyHistory(familyHistory);
				userBasic.setDrugHistory(drugHistory);
				userBasic.setOralKind(oralKind);
				userBasic.setOralAmount(oralAmount);
				userBasic.setOralUse(oralUse);
				userBasic.setInsulinKind(insulinKind);
				userBasic.setInsulinAmount(insulinAmount);
				userBasic.setInsulinUse(insulinUse);
				userBasic.setComment(comment);
				uBasicDao.updateUserBasic(userBasic);
			}

			// User user = userDao.getUsers(userId);

			result = true;
			message = "등록되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}

		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);


		return null;
	}

	// 일반회원 관리목표 상세보기
	@RequestMapping("/admin/user/user_view_goal.go")
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

		return "admin/user/user_view_goal";
	}
	

	//일반회원 관리목표 추가하기
	@RequestMapping("/admin/user/user_goal.go")
	public String goalEditController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "goalsMblood", required = false, defaultValue = "1") int goalsMblood,
			@RequestParam(value = "goalbMblood", required = false, defaultValue = "1") int goalbMblood,
			@RequestParam(value = "goalEblood", required = false, defaultValue = "1") int goalEblood,
			@RequestParam(value = "goalSblood", required = false, defaultValue = "1") int goalSblood,
			@RequestParam(value = "goalHba", required = false, defaultValue = "1") int goalHba,
			@RequestParam(value = "goalsPre", required = false, defaultValue = "1") int goalsPre,
			@RequestParam(value = "goalbPre", required = false, defaultValue = "1") int goalbPre,
			@RequestParam(value = "goalPul", required = false, defaultValue = "1") int goalPul,
			@RequestParam(value = "goalCol", required = false, defaultValue = "1") int goalCol,
			@RequestParam(value = "goalLdl", required = false, defaultValue = "1") int goalLdl,
			@RequestParam(value = "goalHdl", required = false, defaultValue = "1") int goalHdl,
			@RequestParam(value = "goalTg", required = false, defaultValue = "1") int goalTg,
			@RequestParam(value = "goalsBmi", required = false, defaultValue = "1") int goalsBmi,
			@RequestParam(value = "goalbBmi", required = false, defaultValue = "1") int goalbBmi,
			HttpServletResponse res, 
			HttpSession session, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			
				UserGoal userGoal = new UserGoal();
				userGoal.setUserId(userId);
				userGoal.setGoalsMblood(goalsMblood);
				userGoal.setGoalbMblood(goalbMblood);
				userGoal.setGoalEblood(goalEblood);
				userGoal.setGoalSblood(goalSblood);
				userGoal.setGoalHba(goalHba);
				userGoal.setGoalPul(goalPul);
				userGoal.setGoalsPre(goalsPre);
				userGoal.setGoalbPre(goalbPre);
				userGoal.setGoalCol(goalCol);
				userGoal.setGoalLdl(goalLdl);
				userGoal.setGoalHdl(goalHdl);
				userGoal.setGoalTg(goalTg);
				userGoal.setGoalsBmi(goalsBmi);
				userGoal.setGoalbBmi(goalbBmi);
				uGoalDao.addUserGoal(userGoal);
				result = true;
				message = "등록되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("userId", userId);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	
	
	// 일반회원 관리주기 상세보기
	@RequestMapping("/admin/user/user_view_week.go")
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

		return "admin/user/user_view_week";
	}
	
	//일반회원 관리주기 추가하기
	@RequestMapping("/admin/user/user_week.go")
	public String weekEditController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "pcnt", required = false, defaultValue = "1") int pcnt,
			@RequestParam(value = "wcnt", required = false, defaultValue = "1") int wcnt,
			@RequestParam(value = "bcnt", required = false, defaultValue = "1") int bcnt,
			HttpServletResponse res, 
			HttpSession session, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			
				UserCnt userCnt = new UserCnt();
				userCnt.setUserId(userId);
				userCnt.setPcnt(pcnt);
				userCnt.setBcnt(bcnt);
				userCnt.setWcnt(wcnt);
				uCntDao.addUserCnt(userCnt);
				result = true;
				message = "등록되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("userId", userId);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	
	
	
	
	
	
	

	// 일반회원 복약정보 상세보기
	@RequestMapping("/admin/user/user_view_medi.go")
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
		return "/admin/user/user_view_medi";
	}
	
	//일반회원 복약정보 추가하기
	@RequestMapping("/admin/user/user_medi.go")
	public String mediEditController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "mediweek1", required = false, defaultValue = "1") int mediweek1,
			@RequestParam(value = "mediweek2", required = false, defaultValue = "1") int mediweek2,
			@RequestParam(value = "mediweek3", required = false, defaultValue = "1") int mediweek3,
			@RequestParam(value = "mediweek4", required = false, defaultValue = "1") int mediweek4,
			@RequestParam(value = "mediweek5", required = false, defaultValue = "1") int mediweek5,
			@RequestParam(value = "mediweek6", required = false, defaultValue = "1") int mediweek6,
			@RequestParam(value = "mediweek7", required = false, defaultValue = "1") int mediweek7,
			@RequestParam(value = "mediname", required = false, defaultValue = "") String mediname,
			@RequestParam(value = "meditime", required = false, defaultValue = "") String meditime,
			@RequestParam(value = "medialert", required = false, defaultValue = "1") int medialert,
			HttpServletResponse res, 
			HttpSession session, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			
				UserMedi userMedi = new UserMedi();
				userMedi.setUserId(userId);
				userMedi.setMediweek1(mediweek1);
				userMedi.setMediweek2(mediweek2);
				userMedi.setMediweek3(mediweek3);
				userMedi.setMediweek4(mediweek4);
				userMedi.setMediweek5(mediweek5);
				userMedi.setMediweek6(mediweek6);
				userMedi.setMediweek7(mediweek7);
				userMedi.setMediname(mediname);
				userMedi.setMeditime(meditime);
				userMedi.setMedialert(medialert);
				uMediDao.addUserMedi(userMedi);
				result = true;
				message = "등록되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("userId", userId);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	
	// 일반회원 CV_RISK 상세보기
	@RequestMapping("/admin/user/user_cv_risk.go")
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
			//userMedi = uMediDao.getUserMediView(userId);
		}

		// 해당 유저 혈당 리스트
		List<UserCvrisk> UserCvrisklist = uCvriskDao.getUserCvriskAdmin(userId);
		count = uCvriskDao.getCount(userId);

		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,PAGE_COUNT_PER_PAGING);

		model.addAttribute("UserCvrisklist", UserCvrisklist);
		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("userMedi", userMedi);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		
		return "/admin/user/user_cv_risk";
	}
	
	
	
	
	// 일반회원 병원 목록  등록 수정 처리
	@RequestMapping("/admin/user/user_view_hospital.go")
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

		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,PAGE_COUNT_PER_PAGING);

		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		
		return "/admin/user/user_view_hospital";
	}
	

	
	// 수행내역
	@RequestMapping("/admin/user/user_view_done.go")
	public String userViewDoneController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpServletResponse res, Model model) {
		
		User user;
		UserBasic userBasic = null;

		if (userId.equals("")) {
			user = new User();
		} else {
			user = userDao.getUsers(userId);
			userBasic = uBasicDao.getUserBasicEdit(userId);
		}

		List list = reportHistoryDao.getReportHistoryList(userId, page, ITEM_COUNT_PER_PAGE);
		int count = reportHistoryDao.getCount(userId);
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,PAGE_COUNT_PER_PAGING);

		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		
		return "/admin/user/user_view_done";
	}
	
	// 수행내역
	@RequestMapping("/admin/user/user_view_etc.go")
	public String userViewEtcController(
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
			userBasic = uBasicDao.getUserBasicView(userId);
		}

		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,PAGE_COUNT_PER_PAGING);

		model.addAttribute("user", user);
		model.addAttribute("userBasic", userBasic);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		
		return "/admin/user/user_view_etc";
	}
	
	
	
	// 회원 관리 > 탈퇴회원
	@RequestMapping("/admin/user/user_drop.go")
	public String userController(HttpSession session, Model model) {

		return "/admin/user/user_drop";
	}

	// 회원 관리 > 탈퇴회원 리스트
	@RequestMapping("/admin/user/user_drop_list.go")
	public String userListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<User> list = null;
		int count = 0;

		list = userDao.getUserDropList(keyword, gender, age, page,
				ITEM_COUNT_PER_PAGE);
		count = userDao.getCountDrop(keyword, gender, age);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/user/user_drop_list";
	}

	// 공지사항
	@RequestMapping("/admin/notice/notice.go")
	public String noticeController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/notice/notice";
	}

	// 공지사항 리스트

	@RequestMapping("/admin/notice/notice_list.go")
	public String noticeListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Notice> list = null;
		int count = 0;
		int notiType = 0;

		if (keyword.equals("")) {

			list = noticeDao.getNoticeMainList(page, ITEM_COUNT_PER_PAGE);
			count = noticeDao.getNoticeMainCount();

		} else {

			list = noticeDao.getNoticeMainList(keyword, page,ITEM_COUNT_PER_PAGE);
			count = noticeDao.getNoticeMainCount(keyword);
		}

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/notice/notice_list";
	}

	// 공지사항 등록/수정
	@RequestMapping("/admin/notice/notice_edit.go")
	public String noticeEditController(
			@RequestParam(value = "noticeSeq", required = false, defaultValue = "0") int noticeSeq,
			Model model) {

		Notice notice = null;
		if (noticeSeq == 0) {
			notice = new Notice();
		} else {
			notice = noticeDao.getNotice(noticeSeq);
		}

		model.addAttribute("notice", notice);

		return "admin/notice/notice_edit";
	}

	// 공지사항 수정의 처리
	@RequestMapping("/admin/notice/notice_edit_do.go")
	public String noticeEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "noticeSeq", required = false, defaultValue = "0") int noticeSeq,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			@RequestParam(value = "notiType", required = false, defaultValue = "0") int notiType,
			@RequestParam(value = "sendPush", required = false, defaultValue = "0") int sendPush,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "ir1", required = false, defaultValue = "") String contentsHtml,
			@RequestParam(value = "ir1_text", required = false, defaultValue = "") String contentsText,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "새로운 공지사항이 등록 되었습니다.";

		try {
			if (noticeSeq == 0) {
				Notice notice = new Notice();
				notice.setUserId(userId);
				notice.setTitle(title);
				notice.setStartDate(startDate);
				notice.setEndDate(endDate);
				// notice.setNotiType(notiType);
				notice.setSendPush(sendPush);
				notice.setContentsHtml(contentsHtml);
				notice.setContentsText(contentsText);
				noticeDao.addNotice(notice);
				noticeSeq = noticeDao.getLastSeq();
				
				if(notice.getSendPush()==1){//푸시 발송 설정이면
					
					List<User>list = userDao.getUserPushList();
					for (int i=0; i<list.size(); i++) {
						User user = (User) list.get(i);
					
						if (user.getPushkey().equals("") == false) {
						
							Push push = new Push();
							push.setBadge(1);
							push.setOs(user.getOsType());
							push.setPushKey(user.getPushkey());
							push.setMsgType(Push.MSG_TYPE_NOTICE);
							push.setUserid(user.getUserId());
							push.setStatus(0);
							push.setServiceId("RECOVER");
							push.setPushType(1);							
							push.setMsg(message);
							push.setMsgKey("0");
							pushDao.addPush(push);
							userDao.updateBadge(user.getUserId(),user.getNoticeBadge()+1);
						}
					}
				}

				result = true;
				message = "등록되었습니다.";
			} else {
				Notice notice = noticeDao.getNotice(noticeSeq);

				notice.setUserId(userId);
				notice.setTitle(title);
				notice.setStartDate(startDate);
				notice.setEndDate(endDate);
				// notice.setNotiType(notiType);
				notice.setSendPush(sendPush);
				notice.setContentsHtml(contentsHtml);
				notice.setContentsText(contentsText);
				noticeDao.updateNotice(notice);
				result = true;
				message = "수정되었습니다.";
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("noticeSeq", noticeSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 공지사항 삭제
	@RequestMapping("/admin/notice/notice_delete_do.go")
	public String noticeDeleteDoController(@RequestParam int noticeSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 게시물 삭제
			noticeDao.deleteNotice(noticeSeq);

			map.put("message", "게시물이 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "게시물이 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// QNA
	@RequestMapping("/admin/notice/qna.go")
	public String qnaController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/notice/qna";
	}

	// QNA 리스트

	@RequestMapping("/admin/notice/qna_list.go")
	public String qnaListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Qna> list = null;
		int count = 0;
		int notiType = 0;

		if (keyword.equals("")) {

			list = qnaDao.getQnaListAdmin(page, ITEM_COUNT_PER_PAGE);
			count = qnaDao.getQnaCount();

		} else {

			list = qnaDao.getQnaListAdmin(keyword, page, ITEM_COUNT_PER_PAGE);
			count = qnaDao.getQnaCount(keyword);
		}

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);

		return "admin/notice/qna_list";
	}

	// QNA 등록/수정
	@RequestMapping("/admin/notice/qna_edit.go")
	public String qnaEditController(
			@RequestParam(value = "noticeSeq", required = false, defaultValue = "0") int noticeSeq,
			Model model) {

		Notice notice = null;
		if (noticeSeq == 0) {
			notice = new Notice();
		} else {
			notice = noticeDao.getNotice(noticeSeq);
		}

		model.addAttribute("notice", notice);

		return "admin/notice/qna_edit";
	}

	// QNA 수정의 처리
	@RequestMapping("/admin/notice/qna_edit_do.go")
	public String qnaEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "noticeSeq", required = false, defaultValue = "0") int noticeSeq,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			@RequestParam(value = "notiType", required = false, defaultValue = "0") int notiType,
			@RequestParam(value = "sendPush", required = false, defaultValue = "0") int sendPush,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "ir1", required = false, defaultValue = "") String contentsHtml,
			@RequestParam(value = "ir1_text", required = false, defaultValue = "") String contentsText,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			if (noticeSeq == 0) {
				Notice notice = new Notice();
				notice.setUserId(userId);
				notice.setTitle(title);
				notice.setStartDate(startDate);
				notice.setEndDate(endDate);
				// notice.setNotiType(notiType);
				notice.setSendPush(sendPush);
				notice.setContentsHtml(contentsHtml);
				notice.setContentsText(contentsText);
				noticeDao.addNotice(notice);
				noticeSeq = noticeDao.getLastSeq();

				result = true;
				message = "등록되었습니다.";
			} else {
				Notice notice = noticeDao.getNotice(noticeSeq);

				notice.setUserId(userId);
				notice.setTitle(title);
				notice.setStartDate(startDate);
				notice.setEndDate(endDate);
				// notice.setNotiType(notiType);
				notice.setSendPush(sendPush);
				notice.setContentsHtml(contentsHtml);
				notice.setContentsText(contentsText);
				noticeDao.updateNotice(notice);
				result = true;
				message = "수정되었습니다.";
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("noticeSeq", noticeSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// QNA 삭제
	@RequestMapping("/admin/notice/qna_delete_do.go")
	public String qnaDeleteDoController(@RequestParam int noticeSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 게시물 삭제
			noticeDao.deleteNotice(noticeSeq);

			map.put("message", "게시물이 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "게시물이 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 앱버전
	@RequestMapping("/admin/notice/app.go")
	public String appController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/notice/app";
	}

	// 앱버전
	@RequestMapping("/admin/notice/app_list.go")
	public String appListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Config> list = null;
		int count = 0;

		list = configDao.getConfigList(page, ITEM_COUNT_PER_PAGE);
		count = configDao.getCount();

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);

		return "/admin/notice/app_list";
	}

	// 앱버전 등록/수정
	@RequestMapping("/admin/notice/app_edit.go")
	public String appEditController(
			@RequestParam(value = "appSeq", required = false, defaultValue = "0") int appSeq,
			Model model) {

		Config config = null;
		if (appSeq == 0) {
			config = new Config();
		} else {
			config = configDao.getConfig(appSeq);
		}
		model.addAttribute("config", config);

		return "admin/notice/app_edit";
	}

	// 앱버전 수정의 처리
	@RequestMapping("/admin/notice/app_edit_do.go")
	public String appEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "appSeq", required = false, defaultValue = "0") int appSeq,
			@RequestParam(value = "appVersion", required = false, defaultValue = "") String appVersion,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			if (appSeq == 0) {
				Config config = new Config();
				config.setAppVersion(appVersion);
				config.setComment(comment);
				configDao.addConfig(config);
				result = true;
				message = "등록되었습니다.";
			} else {
				Config config = configDao.getConfig(appSeq);
				config.setAppVersion(appVersion);
				config.setComment(comment);
				configDao.updateConfig(config);
				result = true;
				message = "수정되었습니다.";
			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("appSeq", appSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 앱버전 삭제의 처리
	@RequestMapping("/admin/notice/app_delete_do.go")
	public String appDeleteDoController(
			HttpServletRequest req,
			@RequestParam(value = "appSeq", required = false, defaultValue = "0") int appSeq,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			configDao.deleteConfig(appSeq);
			result = true;
			message = "삭제되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("appSeq", appSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 관리지표 등록
	@RequestMapping("/admin/notice/manage_index.go")
	public String indexController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		ManageIndex goal = manageIndexDao.getManageIndexTop();

		model.addAttribute("goal", goal);

		return "/admin/notice/manage_index";
	}

	// 등록,수정의 처리
	@RequestMapping("/admin/notice/manage_index_edit_do.go")
	public String indexEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "indexSeq", required = false, defaultValue = "0") int indexSeq,
			@RequestParam(value = "goalSmblood", required = false, defaultValue = "0") int goalSmblood,
			@RequestParam(value = "goalBmblood", required = false, defaultValue = "0") int goalBmblood,
			@RequestParam(value = "goalEblood", required = false, defaultValue = "0") int goalEblood,
			@RequestParam(value = "goalEsblood", required = false, defaultValue = "0") int goalEsblood,
			@RequestParam(value = "goalSblood", required = false, defaultValue = "0") int goalSblood,
			@RequestParam(value = "goalSsblood", required = false, defaultValue = "0") int goalSsblood,
			@RequestParam(value = "goalHba", required = false, defaultValue = "0") float goalHba,
			@RequestParam(value = "goalSpre", required = false, defaultValue = "0") int goalSpre,
			@RequestParam(value = "goalSpreOld", required = false, defaultValue = "0") int goalSpreOld,
			@RequestParam(value = "goalBpre", required = false, defaultValue = "0") int goalBpre,
			@RequestParam(value = "goalBpreOld", required = false, defaultValue = "0") int goalBpreOld,
			@RequestParam(value = "goalPul", required = false, defaultValue = "0") int goalPul,
			@RequestParam(value = "goalCol", required = false, defaultValue = "0") int goalCol,
			@RequestParam(value = "goalLdl", required = false, defaultValue = "0") int goalLdl,
			@RequestParam(value = "goalHdl", required = false, defaultValue = "0") int goalHdl,
			@RequestParam(value = "goalTg", required = false, defaultValue = "0") int goalTg,
			@RequestParam(value = "goalSbmi", required = false, defaultValue = "0") float goalSbmi,
			@RequestParam(value = "goalBbmi", required = false, defaultValue = "0") float goalBbmi,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			ManageIndex manageIndex = manageIndexDao.getManageIndex(indexSeq);
			manageIndex.setGoalSmblood(goalSmblood);
			manageIndex.setGoalBmblood(goalBmblood);
			manageIndex.setGoalEblood(goalEblood);
			manageIndex.setGoalEsblood(goalEsblood);
			manageIndex.setGoalSblood(goalSblood);
			manageIndex.setGoalSsblood(goalSsblood);
			manageIndex.setGoalHba(goalHba);
			manageIndex.setGoalSpre(goalSpre);
			manageIndex.setGoalBpre(goalBpre);
			manageIndex.setGoalSpreOld(goalSpreOld);
			manageIndex.setGoalBpreOld(goalBpreOld);
			manageIndex.setGoalPul(goalPul);
			manageIndex.setGoalCol(goalCol);
			manageIndex.setGoalLdl(goalLdl);
			manageIndex.setGoalHdl(goalHdl);
			manageIndex.setGoalTg(goalTg);
			manageIndex.setGoalSbmi(goalSbmi);
			manageIndex.setGoalBbmi(goalBbmi);
			manageIndexDao.updateManageIndex(manageIndex);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 도움말
	@RequestMapping("/admin/notice/faq.go")
	public String faqController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/notice/faq";
	}

	// 도움말 리스트

	@RequestMapping("/admin/notice/faq_list.go")
	public String faqListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Qna> list = null;
		int count = 0;

		if (keyword.equals("")) {

			list = qnaDao.getQnaList(page, ITEM_COUNT_PER_PAGE);
			count = qnaDao.getCount();

		} else {

			list = qnaDao.getQnaList(keyword, page, ITEM_COUNT_PER_PAGE);
			count = qnaDao.getCount(keyword);
		}

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/notice/faq_list";
	}

	// 공지사항 등록/수정
	@RequestMapping("/admin/notice/faq_edit.go")
	public String faqEditController(
			@RequestParam(value = "qnaSeq", required = false, defaultValue = "0") int qnaSeq,
			Model model) {

		Qna qna = null;
		if (qnaSeq == 0) {
			qna = new Qna();
		} else {
			qna = qnaDao.getQna(qnaSeq);
		}

		model.addAttribute("qna", qna);

		return "admin/notice/faq_edit";
	}

	// 공지사항 수정의 처리
	@RequestMapping("/admin/notice/faq_edit_do.go")
	public String faqEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "qnaSeq", required = false, defaultValue = "0") int qnaSeq,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			// @RequestParam(value = "startDate", required = false, defaultValue
			// = "") String startDate,
			// @RequestParam(value = "endDate", required = false, defaultValue =
			// "") String endDate,
			@RequestParam(value = "cateKind", required = false, defaultValue = "0") int cateKind,
			// @RequestParam(value = "sendPush", required = false, defaultValue
			// = "0") int sendPush,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "ir1", required = false, defaultValue = "") String contentsHtml,
			@RequestParam(value = "ir1_text", required = false, defaultValue = "") String contentsText,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			if (qnaSeq == 0) {
				Qna qna = new Qna();
				qna.setUserId(userId);
				qna.setTitle(title);
				qna.setCateKind(cateKind);
				qna.setContentsHtml(contentsHtml);
				qna.setContentsText(contentsText);
				qnaDao.addQna(qna);

				result = true;
				message = "등록되었습니다.";
			} else {
				Qna qna = qnaDao.getQna(qnaSeq);
				qna.setUserId(userId);
				qna.setTitle(title);
				qna.setCateKind(cateKind);
				qna.setContentsHtml(contentsHtml);
				qna.setContentsText(contentsText);
				qnaDao.updateQna(qna);
				result = true;
				message = "수정되었습니다.";
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("qnaSeq", qnaSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 공지사항 삭제
	@RequestMapping("/admin/notice/faq_delete_do.go")
	public String faqDeleteDoController(@RequestParam int qnaSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 게시물 삭제
			qnaDao.deleteQna(qnaSeq);

			map.put("message", "게시물이 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "게시물이 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 문진
	@RequestMapping("/admin/medical/medical.go")
	public String medicalController(HttpSession session, Model model) {

		return "/admin/medical/medical";
	}

	// 문진 리스트

	@RequestMapping("/admin/medical/medical_list.go")
	public String medicalListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession session, Model model) {

		List<MedExam> list = null;
		int count = 0;
		list = medExamDao.getMedExamList();
		/*
		 * list = medExamDao.getMedExamList(page, ITEM_COUNT_PER_PAGE); count =
		 * medExamDao.getCount();
		 */

		// 페이징
		String paging = Paging.getPaging2(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);

		return "admin/medical/medical_list";
	}

	// 문진 등록/수정
	@RequestMapping("/admin/medical/medical_edit.go")
	public String medicalEditController(
			@RequestParam(value = "medSeq", required = false, defaultValue = "0") int medSeq,
			Model model) {

		MedExam medExam = null;
		if (medSeq == 0) {
			medExam = new MedExam();
		} else {
			medExam = medExamDao.getMedExam(medSeq);
		}

		model.addAttribute("medExam", medExam);

		return "admin/medical/medical_edit";
	}

	// 문진 등록의 처리
	@RequestMapping("/admin/medical/medical_edit_do.go")
	public String medicalEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "medSeq", required = false, defaultValue = "0") int medSeq,
			@RequestParam(value = "askind", required = false, defaultValue = "0") int askind,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			@RequestParam(value = "pseq", required = false, defaultValue = "0") int pseq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "move", required = false, defaultValue = "0") int move,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			if (medSeq == 0) {

				MedExam medExam = new MedExam();
				medExam.setAskind(askind);
				medExam.setAnsType(ansType);
				medExam.setPseq(pseq);
				medExam.setComment(comment);
				medExam.setMove(move);
				medExamDao.addMedExam(medExam);
				result = true;
				message = "등록되었습니다.";

			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 문진 삭제
	@RequestMapping("/admin/medical/medical_delete_do.go")
	public String medicalDeleteDoController(@RequestParam int medSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 문진 삭제
			medExamDao.deleteMedExam(medSeq);

			map.put("message", "삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 문진 수정의 처리
	@RequestMapping("/admin/medical/medical_edit_table_do.go")
	public String medicalTableEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "medSeq", required = false, defaultValue = "0") int medSeq,
			@RequestParam(value = "goseq", required = false, defaultValue = "0") int move,
			@RequestParam(value = "pseq", required = false, defaultValue = "0") int pseq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			// MedExam medExam = medExamDao.getMedExam(medSeq);
			MedExam medExam = new MedExam();
			medExam.setMedSeq(medSeq);
			medExam.setMove(move);
			medExam.setPseq(pseq);
			medExam.setAnsType(ansType);
			medExam.setComment(comment);
			medExamDao.updateMedExamTable(medExam);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 문진 sort 값 변경처리
	@RequestMapping("/admin/medical/sort_edit_do.go")
	public String sortEditController(
			@RequestParam(value = "sortList", required = false, defaultValue = "") String[] sortList,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			int sortCount = sortList.length;

			// logContents 리스트 갯수만큼 로그 저장

			if (sortList != null || !(sortList.equals(""))) {

				for (int i = 0; i < sortCount; i++) {

					int mSeq = Integer.parseInt(sortList[i]);

					// MedExam medExam = medExamDao.getMedExam(sort);
					// medExam.setSort(i+1);
					medExamDao.updateMedExam(i + 1, mSeq);
					result = true;
					message = "수정되었습니다.";
				}

			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		// map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 문진 마지막 질문 값 처리
	@RequestMapping("/admin/medical/medical_checkQuest_do.go")
	public String checkQuestEditController(
			@RequestParam(value = "medSeq", required = false, defaultValue = "") int medSeq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			medExamDao.updateMedExamIsLast(1, medSeq);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		// map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 분석 < 관리분석
	@RequestMapping("/admin/analysis/empty_morning.go")
	public String morningManageController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/analysis/empty_morning";
	}

	// 닥터톡 - 분석 < 관리분석 리스트

	@RequestMapping("/admin/analysis/empty_morning_list.go")
	public String morningManageListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Comment> list = null;
		int count = 0;

		list = commentDao.getCommentList(page, ITEM_COUNT_PER_PAGE);
		count = commentDao.getCount();

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/analysis/empty_morning_list";
	}

	// 닥터톡 - 분석 < 관리분석 등록의 처리
	@RequestMapping("/admin/analysis/empty_morning_edit_do.go")
	public String morningManageEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "commentSeq", required = false, defaultValue = "0") int commentSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			if (commentSeq == 0) {

				Comment commentAdd = new Comment();
				commentAdd.setCommnet(comment);
				commentDao.addComment(commentAdd);
				result = true;
				message = "등록되었습니다.";

			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("commentSeq", commentSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 분석 관리분석 수정의 처리
	@RequestMapping("/admin/analysis/empty_morning_edit_table_do.go")
	public String emptyMorningTableEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "commentSeq", required = false, defaultValue = "0") int commentSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "imgType", required = false, defaultValue = "") String imgType,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			commentDao.updateComment(imgType,comment, commentSeq);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("commentSeq", commentSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 분석 관리분석 삭제
	@RequestMapping("/admin/analysis/empty_morning_delete_do.go")
	public String emptyMorningDeleteDoController(@RequestParam int commentSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 닥터톡 - 분석 관리분석 삭제
			commentDao.deleteComment(commentSeq);

			map.put("message", "삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 닥터톡 - 지표입력 < 혈당
	@RequestMapping("/admin/doctor_index_input/blood.go")
	public String bloodController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/doctor_index_input/blood";
	}

	// 닥터톡 - 지표입력 < 혈당 리스트

	@RequestMapping("/admin/doctor_index_input/blood_list.go")
	public String bloodListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<DoctorPointer> list = null;
		int count = 0;
		list = doctorPointerDao.getBloodList();
		//list = doctorPointerDao.getBloodList(page, ITEM_COUNT_PER_PAGE);
		//count = doctorPointerDao.getBloodCount();

		// 페이징
		//String paging = Paging.getPaging2(page, count, ITEM_COUNT_PER_PAGE,
			//	PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		//model.addAttribute("paging", paging);
		//model.addAttribute("keyword", keyword);
		//model.addAttribute("currentPage", page);

		return "admin/doctor_index_input/blood_list";
	}

	// 지표입력 혈당 등록/수정
	@RequestMapping("/admin/doctor_index_input/blood_edit.go")
	public String bloodEditController(
			@RequestParam(value = "comSeq", required = false, defaultValue = "0") int comSeq,
			Model model) {

		DoctorPointer doctorPointer = null;

		if (comSeq == 0) {
			doctorPointer = new DoctorPointer();
		} else {
			doctorPointer = doctorPointerDao.getDoctorPointer(comSeq);
		}

		model.addAttribute("doctorPointer", doctorPointer);

		return "/admin/doctor_index_input/blood_edit";
	}

	// 지표입력 혈당 등록의 처리
	@RequestMapping("/admin/doctor_index_input/blood_edit_do.go")
	public String bloodEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "comSeq", required = false, defaultValue = "0") int comSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "askind", required = false, defaultValue = "0") int askind,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			@RequestParam(value = "move", required = false, defaultValue = "0") int move,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			if (comSeq == 0) {

				DoctorPointer doctorPointer = new DoctorPointer();
				doctorPointer.setDiseaseId("blood");
				doctorPointer.setAskind(askind);
				doctorPointer.setComment(comment);
				doctorPointer.setAnsType(ansType);
				doctorPointer.setMove(move);
				doctorPointerDao.addDoctorPointer(doctorPointer);
				result = true;
				message = "등록되었습니다.";

			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("comSeq", comSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 지표입력 혈당 삭제
	@RequestMapping("/admin/doctor_index_input/blood_delete_do.go")
	public String bloodDeleteDoController(@RequestParam int comSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 지표입력 혈당 삭제
			doctorPointerDao.deleteDoctorPointer(comSeq);

			map.put("message", "삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 지표입력 혈당 수정의 처리
	@RequestMapping("/admin/doctor_index_input/blood_edit_table_do.go")
	public String bloodTableEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "comSeq", required = false, defaultValue = "0") int comSeq,
			@RequestParam(value = "goseq", required = false, defaultValue = "0") int move,
			@RequestParam(value = "pseq", required = false, defaultValue = "0") int pseq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			// DoctorPointer doctorPointer =
			// doctorPointerDao.getDoctorPointer(comSeq);
			DoctorPointer doctorPointer = new DoctorPointer();
			doctorPointer.setComSeq(comSeq);
			doctorPointer.setMove(move);
			doctorPointer.setPseq(pseq);
			doctorPointer.setAnsType(ansType);
			doctorPointer.setComment(comment);
			doctorPointerDao.updateDoctorPointerTable(doctorPointer);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("comSeq", comSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 혈당 sort 값 변경처리
	@RequestMapping("/admin/blood/sort_edit_do.go")
	public String sortBloodEditController(
			@RequestParam(value = "sortList", required = false, defaultValue = "") String[] sortList,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			int sortCount = sortList.length;

			if (sortList != null || !(sortList.equals(""))) {

				for (int i = 0; i < sortCount; i++) {

					int cSeq = Integer.parseInt(sortList[i]);

					// MedExam medExam = medExamDao.getMedExam(sort);
					// medExam.setSort(i+1);
					doctorPointerDao.updateDoctorPointer(i + 1, cSeq);
					result = true;
					message = "수정되었습니다.";
				}

			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		// map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 지표입력 < 혈당 마지막 질문 값 처리
	@RequestMapping("/admin/blood/blood_checkQuest_do.go")
	public String bloodQuestEditController(
			@RequestParam(value = "comSeq", required = false, defaultValue = "") int comSeq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			doctorPointerDao.updateDoctorPointerIsLast(1, comSeq);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		// map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 지표입력 < 혈압
	@RequestMapping("/admin/doctor_index_input/pressure.go")
	public String pressureController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/doctor_index_input/pressure";
	}

	// 닥터톡 - 지표입력 < 혈압 리스트

	@RequestMapping("/admin/doctor_index_input/pressure_list.go")
	public String pressureListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<DoctorPointer> list = null;
		int count = 0;

		list = doctorPointerDao.getPressList();
		//count = doctorPointerDao.getPressCount();

		// 페이징
		//String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
		//		PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		//model.addAttribute("paging", paging);
		//model.addAttribute("keyword", keyword);
		//model.addAttribute("currentPage", page);
		return "admin/doctor_index_input/pressure_list";
	}

	// 지표입력 혈압 등록/수정
	@RequestMapping("/admin/doctor_index_input/pressure_edit.go")
	public String pressureEditController(
			@RequestParam(value = "comSeq", required = false, defaultValue = "0") int comSeq,
			Model model) {

		DoctorPointer doctorPointer = null;

		if (comSeq == 0) {
			doctorPointer = new DoctorPointer();
		} else {
			doctorPointer = doctorPointerDao.getDoctorPointer(comSeq);
		}

		model.addAttribute("doctorPointer", doctorPointer);

		return "/admin/doctor_index_input/pressure_edit";
	}

	// 지표입력 혈압 등록의 처리
	@RequestMapping("/admin/doctor_index_input/pressure_edit_do.go")
	public String pressureEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "comSeq", required = false, defaultValue = "0") int comSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "askind", required = false, defaultValue = "0") int askind,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			@RequestParam(value = "move", required = false, defaultValue = "0") int move,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			if (comSeq == 0) {

				DoctorPointer doctorPointer = new DoctorPointer();
				doctorPointer.setDiseaseId("press");
				doctorPointer.setAskind(askind);
				doctorPointer.setComment(comment);
				doctorPointer.setAnsType(ansType);
				doctorPointer.setMove(move);
				doctorPointerDao.addDoctorPointer(doctorPointer);
				result = true;
				message = "등록되었습니다.";

			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("comSeq", comSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 지표입력 혈압 삭제
	@RequestMapping("/admin/doctor_index_input/pressure_delete_do.go")
	public String pressureDeleteDoController(@RequestParam int comSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 지표입력 혈압 삭제
			doctorPointerDao.deleteDoctorPointer(comSeq);

			map.put("message", "삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 지표입력 혈압 수정의 처리
	@RequestMapping("/admin/doctor_index_input/pressure_edit_table_do.go")
	public String pressureTableEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "comSeq", required = false, defaultValue = "0") int comSeq,
			@RequestParam(value = "goseq", required = false, defaultValue = "0") int move,
			@RequestParam(value = "pseq", required = false, defaultValue = "0") int pseq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			// DoctorPointer doctorPointer =
			// doctorPointerDao.getDoctorPointer(comSeq);
			DoctorPointer doctorPointer = new DoctorPointer();
			doctorPointer.setComSeq(comSeq);
			doctorPointer.setMove(move);
			doctorPointer.setPseq(pseq);
			doctorPointer.setAnsType(ansType);
			doctorPointer.setComment(comment);
			doctorPointerDao.updateDoctorPointerTable(doctorPointer);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("comSeq", comSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 혈압 sort 값 변경처리
	@RequestMapping("/admin/pressure/sort_edit_do.go")
	public String sortPressureEditController(
			@RequestParam(value = "sortList", required = false, defaultValue = "") String[] sortList,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			int sortCount = sortList.length;

			if (sortList != null || !(sortList.equals(""))) {

				for (int i = 0; i < sortCount; i++) {

					int cSeq = Integer.parseInt(sortList[i]);

					// MedExam medExam = medExamDao.getMedExam(sort);
					// medExam.setSort(i+1);
					doctorPointerDao.updateDoctorPointer(i + 1, cSeq);
					result = true;
					message = "수정되었습니다.";
				}

			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		// map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 지표입력 < 혈압 마지막 질문 값 처리
	@RequestMapping("/admin/pressure/pressure_checkQuest_do.go")
	public String pressureQuestEditController(
			@RequestParam(value = "comSeq", required = false, defaultValue = "") int comSeq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			doctorPointerDao.updateDoctorPointerIsLast(1, comSeq);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		// map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 지표입력 < 콜레스테롤
	@RequestMapping("/admin/doctor_index_input/cholesterol.go")
	public String cholesterolController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/doctor_index_input/cholesterol";
	}

	// 닥터톡 - 지표입력 < 콜레스테롤 리스트

	@RequestMapping("/admin/doctor_index_input/cholesterol_list.go")
	public String cholesterolListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<DoctorPointer> list = null;
		int count = 0;

		list = doctorPointerDao.getColList(page, ITEM_COUNT_PER_PAGE);
		count = doctorPointerDao.getColCount();

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/doctor_index_input/cholesterol_list";
	}

	// 지표입력 콜레스테롤 등록/수정
	@RequestMapping("/admin/doctor_index_input/cholesterol_edit.go")
	public String cholesterolEditController(
			@RequestParam(value = "comSeq", required = false, defaultValue = "0") int comSeq,
			Model model) {

		DoctorPointer doctorPointer = null;

		if (comSeq == 0) {
			doctorPointer = new DoctorPointer();
		} else {
			doctorPointer = doctorPointerDao.getDoctorPointer(comSeq);
		}

		model.addAttribute("doctorPointer", doctorPointer);

		return "/admin/doctor_index_input/cholesterol_edit";
	}

	// 지표입력 콜레스테롤 등록의 처리
	@RequestMapping("/admin/doctor_index_input/cholesterol_edit_do.go")
	public String cholesterolEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "comSeq", required = false, defaultValue = "0") int comSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "askind", required = false, defaultValue = "0") int askind,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			@RequestParam(value = "move", required = false, defaultValue = "0") int move,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			if (comSeq == 0) {

				DoctorPointer doctorPointer = new DoctorPointer();
				doctorPointer.setDiseaseId("col");
				doctorPointer.setAskind(askind);
				doctorPointer.setComment(comment);
				doctorPointer.setAnsType(ansType);
				doctorPointer.setMove(move);
				doctorPointerDao.addDoctorPointer(doctorPointer);
				result = true;
				message = "등록되었습니다.";

			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("comSeq", comSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 지표입력 콜레스테롤 삭제
	@RequestMapping("/admin/doctor_index_input/cholesterol_delete_do.go")
	public String cholesterolDeleteDoController(@RequestParam int comSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 지표입력 콜레스테롤 삭제
			doctorPointerDao.deleteDoctorPointer(comSeq);

			map.put("message", "삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 지표입력 콜레스테롤 수정의 처리
	@RequestMapping("/admin/doctor_index_input/cholesterol_edit_table_do.go")
	public String cholesterolTableEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "comSeq", required = false, defaultValue = "0") int comSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "pseq", required = false, defaultValue = "0") int pseq,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			@RequestParam(value = "move", required = false, defaultValue = "0") int move,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			// DoctorPointer doctorPointer =
			// doctorPointerDao.getDoctorPointer(comSeq);
			DoctorPointer doctorPointer = new DoctorPointer();
			doctorPointer.setComSeq(comSeq);
			doctorPointer.setMove(move);
			doctorPointer.setPseq(pseq);
			doctorPointer.setAnsType(ansType);
			doctorPointer.setComment(comment);
			doctorPointerDao.updateDoctorPointerTable(doctorPointer);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("comSeq", comSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 콜레스테롤 sort 값 변경처리
	@RequestMapping("/admin/cholesterol/sort_edit_do.go")
	public String sortCholesterolEditController(
			@RequestParam(value = "sortList", required = false, defaultValue = "") String[] sortList,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			int sortCount = sortList.length;

			if (sortList != null || !(sortList.equals(""))) {

				for (int i = 0; i < sortCount; i++) {

					int cSeq = Integer.parseInt(sortList[i]);

					// MedExam medExam = medExamDao.getMedExam(sort);
					// medExam.setSort(i+1);
					doctorPointerDao.updateDoctorPointer(i + 1, cSeq);
					result = true;
					message = "수정되었습니다.";
				}

			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		// map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 지표입력 < 콜레스테롤 마지막 질문 값 처리
	@RequestMapping("/admin/cholesterol/cholesterol_checkQuest_do.go")
	public String cholesterolQuestEditController(
			@RequestParam(value = "comSeq", required = false, defaultValue = "") int comSeq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			doctorPointerDao.updateDoctorPointerIsLast(1, comSeq);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		// map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 지표입력 < 체중,당화혈색소
	@RequestMapping("/admin/doctor_index_input/weight.go")
	public String weightController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/doctor_index_input/weight";
	}

	// 닥터톡 - 지표입력 < 체중,당화혈색소 리스트

	@RequestMapping("/admin/doctor_index_input/weight_list.go")
	public String weightListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<DoctorPointer> list = null;
		int count = 0;

		list = doctorPointerDao.getWeightList(page, ITEM_COUNT_PER_PAGE);
		count = doctorPointerDao.getWeightCount();

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/doctor_index_input/weight_list";
	}

	// 지표입력 체중,당화혈색소 등록/수정
	@RequestMapping("/admin/doctor_index_input/weight_edit.go")
	public String weightEditController(
			@RequestParam(value = "comSeq", required = false, defaultValue = "0") int comSeq,
			Model model) {

		DoctorPointer doctorPointer = null;

		if (comSeq == 0) {
			doctorPointer = new DoctorPointer();
		} else {
			doctorPointer = doctorPointerDao.getDoctorPointer(comSeq);
		}

		model.addAttribute("doctorPointer", doctorPointer);

		return "/admin/doctor_index_input/weight_edit";
	}

	// 지표입력 체중,당화혈색소 등록의 처리
	@RequestMapping("/admin/doctor_index_input/weight_edit_do.go")
	public String weightEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "comSeq", required = false, defaultValue = "0") int comSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "diseaseId", required = false, defaultValue = "") String diseaseId,
			@RequestParam(value = "askind", required = false, defaultValue = "0") int askind,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			@RequestParam(value = "move", required = false, defaultValue = "0") int move,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			if (comSeq == 0) {

				DoctorPointer doctorPointer = new DoctorPointer();
				doctorPointer.setDiseaseId(diseaseId);
				doctorPointer.setAskind(askind);
				doctorPointer.setComment(comment);
				doctorPointer.setAnsType(ansType);
				doctorPointer.setMove(move);
				doctorPointerDao.addDoctorPointer(doctorPointer);
				result = true;
				message = "등록되었습니다.";

			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("comSeq", comSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 지표입력 체중,당화혈색소 삭제
	@RequestMapping("/admin/doctor_index_input/weight_delete_do.go")
	public String weightDeleteDoController(@RequestParam int comSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 지표입력 체중,당화혈색소 삭제
			doctorPointerDao.deleteDoctorPointer(comSeq);

			map.put("message", "삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 지표입력 체중,당화혈색소 수정의 처리
	@RequestMapping("/admin/doctor_index_input/weight_edit_table_do.go")
	public String weightTableEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "comSeq", required = false, defaultValue = "0") int comSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "pseq", required = false, defaultValue = "0") int pseq,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			@RequestParam(value = "move", required = false, defaultValue = "0") int move,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			// DoctorPointer doctorPointer =
			// doctorPointerDao.getDoctorPointer(comSeq);
			DoctorPointer doctorPointer = new DoctorPointer();
			doctorPointer.setComSeq(comSeq);
			doctorPointer.setComment(comment);
			doctorPointer.setPseq(pseq);
			doctorPointer.setAnsType(ansType);
			doctorPointer.setMove(move);
			;
			doctorPointerDao.updateDoctorPointerTable(doctorPointer);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("comSeq", comSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 체중,당화혈색소 sort 값 변경처리
	@RequestMapping("/admin/weight/sort_edit_do.go")
	public String sortWeightlEditController(
			@RequestParam(value = "sortList", required = false, defaultValue = "") String[] sortList,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			int sortCount = sortList.length;

			if (sortList != null || !(sortList.equals(""))) {

				for (int i = 0; i < sortCount; i++) {

					int cSeq = Integer.parseInt(sortList[i]);

					// MedExam medExam = medExamDao.getMedExam(sort);
					// medExam.setSort(i+1);
					doctorPointerDao.updateDoctorPointer(i + 1, cSeq);
					result = true;
					message = "수정되었습니다.";
				}

			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		// map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 지표입력 < 체중,당화혈색소 마지막 질문 값 처리
	@RequestMapping("/admin/weight/weight_checkQuest_do.go")
	public String weightQuestEditController(
			@RequestParam(value = "comSeq", required = false, defaultValue = "") int comSeq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			doctorPointerDao.updateDoctorPointerIsLast(1, comSeq);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		// map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - CV Risk > CV Risk
	@RequestMapping("/admin/doctor_cv_risk/cv_risk.go")
	public String cvRiskController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/doctor_cv_risk/cv_risk";
	}

	// 닥터톡 - CV Risk > CV Risk 리스트

	@RequestMapping("/admin/doctor_cv_risk/cv_risk_list.go")
	public String cvRiskListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "diseaseId", required = false, defaultValue = "1") int diseaseId,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Cvrisk> list = null;
		int count = 0;

		list = cvriskDao.getCvriskList2(diseaseId);
		//count = cvriskDao.getCount(diseaseId);

		// 페이징
		//String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
		//		PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		//model.addAttribute("paging", paging);
		model.addAttribute("diseaseId", diseaseId);
	//	model.addAttribute("keyword", keyword);
		//model.addAttribute("currentPage", page);
		return "/admin/doctor_cv_risk/cv_risk_list";
	}

	// 닥터톡 - CV Risk > CV Risk 등록/수정
	@RequestMapping("/admin/doctor_cv_risk/cv_risk_edit.go")
	public String cvRiskEditController(
			@RequestParam(value = "cvSeq", required = false, defaultValue = "0") int cvSeq,
			Model model) {

		Cvrisk cvrisk = null;
		if (cvSeq == 0) {
			cvrisk = new Cvrisk();
		} else {
			cvrisk = cvriskDao.getCvriskList(cvSeq);
		}

		model.addAttribute("cvrisk", cvrisk);

		return "/admin/doctor_cv_risk/cv_risk_edit";
	}

	// 닥터톡 - CV Risk > CV Risk 수정의 처리
	@RequestMapping("/admin/doctor_cv_risk/cv_risk_edit_do.go")
	public String cvRiskEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "cvSeq", required = false, defaultValue = "0") int cvSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "askind", required = false, defaultValue = "0") int askind,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			@RequestParam(value = "pseq", required = false, defaultValue = "0") int pseq,
			@RequestParam(value = "move", required = false, defaultValue = "0") int move,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			if (cvSeq == 0) {

				Cvrisk cvrisk = new Cvrisk();
				cvrisk.setAskind(askind);
				cvrisk.setComment(comment);
				cvrisk.setAnsType(ansType);
				cvrisk.setPseq(pseq);
				cvrisk.setMove(move);
				cvriskDao.addCvrisk(cvrisk);
				result = true;
				message = "등록되었습니다.";

			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("cvSeq", cvSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - CV Risk > CV Risk 삭제
	@RequestMapping("/admin/doctor_cv_risk/cv_risk_delete_do.go")
	public String cvRiskDeleteDoController(@RequestParam int cvSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 닥터톡 - CV Risk > CV Risk 삭제
			cvriskDao.deleteCvrisk(cvSeq);

			map.put("message", "삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 닥터톡 - CV Risk > CV Risk 수정의 처리
	@RequestMapping("/admin/doctor_cv_risk/cv_risk_edit_table_do.go")
	public String cvRiskTableEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "cvSeq", required = false, defaultValue = "0") int cvSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "pseq", required = false, defaultValue = "0") int pseq,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			@RequestParam(value = "move", required = false, defaultValue = "0") int move,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			Cvrisk cvrisk = cvriskDao.getCvriskList(cvSeq);
			cvrisk.setComment(comment);
			cvrisk.setPseq(pseq);
			cvrisk.setAnsType(ansType);
			cvrisk.setMove(move);
			cvriskDao.updateCvrisk(cvrisk);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("cvSeq", cvSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - CV Risk > CV Risk sort 값 변경처리
	@RequestMapping("/admin/cv_risk/sort_edit_do.go")
	public String cvRisksortEditController(
			@RequestParam(value = "sortList", required = false, defaultValue = "") String[] sortList,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			int sortCount = sortList.length;

			// logContents 리스트 갯수만큼 로그 저장

			if (sortList != null || !(sortList.equals(""))) {

				for (int i = 0; i < sortCount; i++) {

					int cSeq = Integer.parseInt(sortList[i]);

					// MedExam medExam = medExamDao.getMedExam(sort);
					// medExam.setSort(i+1);
					cvriskDao.updateCvrisk(i + 1, cSeq);
					result = true;
					message = "수정되었습니다.";
				}

			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		// map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - CV Risk > CV Risk 마지막 질문 값 처리
	@RequestMapping("/admin/cv_risk/cv_risk_checkQuest_do.go")
	public String cv_riskQuestEditController(
			@RequestParam(value = "cvSeq", required = false, defaultValue = "") int cvSeq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			cvriskDao.updateCvriskIsLast(1, cvSeq);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		// map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 관리목표/주기 > 관리주기 안내
	@RequestMapping("/admin/period_manage/period.go")
	public String periodController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/period_manage/period";
	}

	// 닥터톡 - 관리목표/주기 > 관리주기 안내 리스트

	@RequestMapping("/admin/period_manage/period_list.go")
	public String periodListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Period> list = null;
		int count = 0;

		list = periodDao.getPeriodList(page, ITEM_COUNT_PER_PAGE);
		count = periodDao.getCount();

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "/admin/period_manage/period_list";
	}

	// 닥터톡 - 관리목표/주기 > 관리주기 안내 등록/수정
	@RequestMapping("/admin/period_manage/period_edit.go")
	public String periodEditController(
			@RequestParam(value = "preSeq", required = false, defaultValue = "0") int preSeq,
			Model model) {

		Period period = null;
		if (preSeq == 0) {
			period = new Period();
		} else {
			period = periodDao.getPeriodSelect(preSeq);
		}
		model.addAttribute("period", period);

		return "/admin/period_manage/no_input_edit";
	}

	// 닥터톡 - 관리목표/주기 > 관리주기 안내 수정의 처리
	@RequestMapping("/admin/period_manage/period_edit_do.go")
	public String periodEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "preSeq", required = false, defaultValue = "0") int preSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			if (preSeq == 0) {

				Period period = new Period();
				//period.setAskind(askind);
				period.setComment(comment);
				// period.setAnsType(ansType);
				//period.setPseq(pseq);
				//period.setMove(move);
				periodDao.addPeriod(period);
				result = true;
				message = "등록되었습니다.";

			}

		} catch (Exception e) {
			message = e.getMessage();
		}

		map.put("preSeq", preSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 관리목표/주기 > 관리주기 안내 삭제
	@RequestMapping("/admin/period_manage/period_delete_do.go")
	public String periodDeleteDoController(@RequestParam int preSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 닥터톡 - 관리목표/주기 > 관리주기 안내 삭제
			periodDao.deletePeriod(preSeq);

			map.put("message", "삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 닥터톡 - 관리목표/주기 > 관리주기 테이블 내용 수정의 처리
	@RequestMapping("/admin/period_manage/period_edit_table_do.go")
	public String periodTableEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "preSeq", required = false, defaultValue = "0") int preSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			//@RequestParam(value = "diseaseId", required = false, defaultValue = "0") String diseaseId,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			// Period period = periodDao.getPeriodSelect(preSeq);
			Period period = new Period();
			period.setPreSeq(preSeq);
			//period.setDiseaseId(diseaseId);
			period.setComment(comment);
			//period.setPseq(pseq);
			// period.setAnsType(ansType);
			//period.setMove(move);
			periodDao.updatePeriod(period);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("preSeq", preSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 관리목표/주기 > 관리주기 안내 sort 값 변경처리
	@RequestMapping("/admin/period/sort_edit_do.go")
	public String periodsortEditController(
			@RequestParam(value = "sortList", required = false, defaultValue = "") String[] sortList,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			int sortCount = sortList.length;

			// logContents 리스트 갯수만큼 로그 저장

			if (sortList != null || !(sortList.equals(""))) {

				for (int i = 0; i < sortCount; i++) {

					int eSeq = Integer.parseInt(sortList[i]);

					// MedExam medExam = medExamDao.getMedExam(sort);
					// medExam.setSort(i+1);
					periodDao.updatePeriod(i + 1, eSeq);
					result = true;
					message = "수정되었습니다.";
				}

			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		// map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 관리목표/주기 > 관리주기 마지막 질문 값 처리
	@RequestMapping("/admin/period/period_checkQuest_do.go")
	public String periodQuestEditController(
			@RequestParam(value = "eperSeq", required = false, defaultValue = "") int eperSeq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			periodDao.updatePeriodIsLast(1, eperSeq);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		// map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	
	// 닥터톡 - 관리목표/주기 > 기간내 미입력

	@RequestMapping("/admin/period_manage/category.go")
	public String noInputCategoryController(HttpServletResponse res, Model model) {
		// int maxnum = daydao.getGroupCount();
		List list = eperiodDao.eperiodGroup();
		for (int i=0; i<list.size(); i++) {
			EperiodGroup group = (EperiodGroup)list.get(i);
			int kcase = group.getKcase();
			List contentsList = eperiodDao.getEperiodlistTop1(kcase); 
			group.setCaseList(contentsList);
			list.set(i, group);
		}
		model.addAttribute("list", list);
		return "/admin/period_manage/category";
	}
	
	@RequestMapping("/admin/period_manage/no_input.go")
	public String no_inputController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "group", required = false, defaultValue = "1") int group,
			HttpSession session, Model model) {
		
		
		List caseList = eperiodDao.getcaseList();
		
		model.addAttribute("caseList", caseList);
		model.addAttribute("group", group);
		return "/admin/period_manage/no_input";
	}

	// 닥터톡 - 관리목표/주기 > 기간내 미입력 리스트

	@RequestMapping("/admin/period_manage/no_input_list.go")
	public String no_inputListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "kcase", required = false, defaultValue = "1") int kcase,
			HttpSession session, Model model) {

		List<Eperiod> list = null;
		int count = 0;

		if(kcase == 0){
			list = eperiodDao.getEperiodList(1);
			//count = eperiodDao.getCount();
		}else{
			list = eperiodDao.getEperiodList(kcase);
			//count = eperiodDao.getCount(kcase);
		}
		

		// 페이징
		//String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		//model.addAttribute("paging", paging);
		model.addAttribute("kcase", kcase);
		model.addAttribute("keyword", keyword);
		//model.addAttribute("currentPage", page);
		return "/admin/period_manage/no_input_list";
	}

	// 닥터톡 - 관리목표/주기 > 기간내 미입력 등록/수정
	@RequestMapping("/admin/period_manage/no_input_edit.go")
	public String noInputEditController(
			@RequestParam(value = "cvSeq", required = false, defaultValue = "0") int eperSeq,
			Model model) {

		Eperiod eperiod = null;
		if (eperSeq == 0) {
			eperiod = new Eperiod();
		} else {
			eperiod = eperiodDao.getEperiodCheck(eperSeq);
		}
		model.addAttribute("eperiod", eperiod);

		return "/admin/period_manage/no_input_edit";
	}

	// 닥터톡 - 관리목표/주기 > 기간내 미입력 수정의 처리
	@RequestMapping("/admin/period_manage/no_input_edit_do.go")
	public String noInputEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "eperSeq", required = false, defaultValue = "0") int eperSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "askind", required = false, defaultValue = "0") int askind,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			@RequestParam(value = "pseq", required = false, defaultValue = "0") int pseq,
			@RequestParam(value = "move", required = false, defaultValue = "0") int move,
			@RequestParam(value = "kcase", required = false, defaultValue = "0") int kcase,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			if (eperSeq == 0) {

				Eperiod eperiod = new Eperiod();
				eperiod.setAskind(askind);
				eperiod.setComment(comment);
				eperiod.setAnsType(ansType);
				eperiod.setPseq(pseq);
				eperiod.setMove(move);
				eperiod.setKcase(kcase);
				eperiodDao.addEperiod(eperiod);
				result = true;
				message = "등록되었습니다.";

			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("eperSeq", eperSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 관리목표/주기 > 기간내 미입력 삭제
	@RequestMapping("/admin/period_manage/no_input_delete_do.go")
	public String noInputDeleteDoController(@RequestParam int eperSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 닥터톡 - 관리목표/주기 > 기간내 미입력 삭제
			eperiodDao.deleteEperiod(eperSeq);

			map.put("message", "삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 닥터톡 - 관리목표/주기 > 기간내 미입력 테이블 내용 수정의 처리
	@RequestMapping("/admin/period_manage/no_input_edit_table_do.go")
	public String noInputTableEditDoController(
			HttpServletRequest req,
			@RequestParam(value = "eperSeq", required = false, defaultValue = "0") int eperSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "pseq", required = false, defaultValue = "0") int pseq,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			@RequestParam(value = "move", required = false, defaultValue = "0") int move,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			// Eperiod eperiod = eperiodDao.getEperiodCheck(eperSeq);
			Eperiod eperiod = new Eperiod();
			eperiod.setEperSeq(eperSeq);
			eperiod.setComment(comment);
			eperiod.setPseq(pseq);
			eperiod.setAnsType(ansType);
			eperiod.setMove(move);
			eperiodDao.updateEperiodTable(eperiod);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("eperSeq", eperSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 관리목표/주기 > 기간내 미입력 sort 값 변경처리
	@RequestMapping("/admin/no_input/sort_edit_do.go")
	public String noInputsortEditController(
			@RequestParam(value = "sortList", required = false, defaultValue = "") String[] sortList,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			int sortCount = sortList.length;

			// logContents 리스트 갯수만큼 로그 저장

			if (sortList != null || !(sortList.equals(""))) {

				for (int i = 0; i < sortCount; i++) {

					int eSeq = Integer.parseInt(sortList[i]);

					// MedExam medExam = medExamDao.getMedExam(sort);
					// medExam.setSort(i+1);
					eperiodDao.updateEperiod(i + 1, eSeq);
					result = true;
					message = "수정되었습니다.";
				}

			}

		} catch (Exception e) {
			message = e.getMessage();
		}
		// map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 닥터톡 - 관리목표/주기 > 기간내 미입력 마지막 질문 값 처리
	@RequestMapping("/admin/no_input/no_input_checkQuest_do.go")
	public String no_inputQuestEditController(
			@RequestParam(value = "eperSeq", required = false, defaultValue = "") int eperSeq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			eperiodDao.updateEperiodIsLast(1, eperSeq);
			result = true;
			message = "수정되었습니다.";

		} catch (Exception e) {
			message = e.getMessage();
		}
		// map.put("medSeq", medSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 컨텐츠 관리 > 건강매거진
	@RequestMapping("/admin/contents/contents.go")
	public String contentsController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		return "/admin/contents/contents";
	}

	// 건강매거진 리스트
	@RequestMapping("/admin/contents/contents_list.go")
	public String contentsListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<Magazine> list = null;
		int count = 0;

		if (keyword.equals("")) {

			list = magazineDao.getMagazineList(page, ITEM_COUNT_PER_PAGE);
			count = magazineDao.getCount();

		} else {

			list = magazineDao.getMagazineList(keyword, page,ITEM_COUNT_PER_PAGE);
			count = magazineDao.getCount(keyword);
		}

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,
				PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "admin/contents/contents_list";
	}

	// 건강매거진 등록/수정
	@RequestMapping("/admin/contents/contents_edit.go")
	public String contentsEditController(
			@RequestParam(value = "mSeq", required = false, defaultValue = "0") int mSeq,
			Model model) {

		Magazine magazine = null;
		List<MagazinePage> pageList = new ArrayList<MagazinePage>();
		if (mSeq == 0) {
			magazine = new Magazine();
		} else {
			magazine = magazineDao.getMagazine(mSeq);
			pageList = magazinepageDao.getMagazinePageList(mSeq);

		}

		model.addAttribute("magazine", magazine);
		model.addAttribute("pageList", pageList);
		return "admin/contents/contents_edit";
	}

	// 건강매거진 수정의 처리
	@RequestMapping("/admin/contents/contents_edit_do.go")
	public String contentsEditDoController(HttpServletRequest req,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		int mSeq = 0;

		try {
			String FILE_PATH = "/files/magazine/";
			String FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;
			String photo = "";
			String path = "";
			String photoPre = "";
			int fileSize = FILE_MAX_SIZE * 1024 * 1024;
			String fileName = "";
			String thumbName = "";

			req.setCharacterEncoding("utf-8");

			File file = null;
			MultipartRequest multi = new MultipartRequest(req, FILE_LOCAL_PATH,
					fileSize, "UTF-8", new UniqFileRenamePolicy());

			mSeq = Integer
					.parseInt(F.nullCheck(multi.getParameter("mSeq"), "0"));
			String userId = F.nullCheck(multi.getParameter("userId"), "");
			String title = F.nullCheck(multi.getParameter("title"), "");
			String subTitle = F.nullCheck(multi.getParameter("subTitle"), "");
			String contents = F.nullCheck(multi.getParameter("contents"), "");
			String year = F.nullCheck(multi.getParameter("year"), "");
			String month = F.nullCheck(multi.getParameter("month"), "");

			Enumeration files = multi.getFileNames();
			while (files.hasMoreElements()) {
				String elementName = (String) files.nextElement();
				file = multi.getFile(elementName);
				if (file != null) {
					fileName = file.getName();
				}
			}

			if (fileName.equals("") == false) {
				// 축소이미지 저장
				File newFile = new File(FILE_LOCAL_PATH + fileName);
				File thumbFile = new File(FILE_LOCAL_PATH + "thumb/" + fileName);
				if (!thumbFile.exists()) {
					thumbFile.mkdirs();
				}
				try {
					ImageUtil.resize(newFile, thumbFile, 300, 0);
					result = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (mSeq == 0) {
				Magazine magazine = new Magazine();
				magazine.setContents(contents);
				magazine.setMonth(year + "-" + month);
				magazine.setTitle(title);
				magazine.setSubTitle(subTitle);
				magazine.setFileName(fileName);
				magazine.setThumFile(fileName);
				magazineDao.addMagazine(magazine);
				mSeq = magazineDao.getLastId();

				result = true;
				message = "등록되었습니다.";
			} else {
				Magazine magazine = magazineDao.getMagazine(mSeq);
				magazine.setContents(contents);
				magazine.setMonth(year + "-" + month);
				magazine.setTitle(title);
				magazine.setSubTitle(subTitle);
				if (fileName.equals("") == false) {
					magazine.setFileName(fileName);
					magazine.setThumFile(fileName);
				}
				magazineDao.updateMagazine(magazine);
				result = true;
				message = "수정되었습니다.";
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("mSeq", mSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 건강매거진 삭제
	@RequestMapping("/admin/contents/contents_delete_do.go ")
	public String contentsDeleteDoController(@RequestParam int mSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 게시물 삭제
		
			Magazine magazine = magazineDao.getMagazine(mSeq);
			String fileName = magazine.getFileName();

			String FILE_LOCAL_PATH = FILE_ROOT + "/files/magazine/";
			String filePath = FILE_LOCAL_PATH + fileName;
			String thumbPath = FILE_LOCAL_PATH + "thumb/" + fileName;

			File file = new File(filePath);
			file.delete();

			File thumb = new File(thumbPath);
			thumb.delete();

			// 게시물 삭제
			magazineDao.deleteMagazinePageMain(mSeq);

			
			map.put("message", "게시물이 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "게시물이 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 건강매거진 이미지 삭제
	@RequestMapping("/admin/contents/contents_file_delete.go")
	public String contentsFileDeleteController(
			@RequestParam(value = "mSeq", required = false, defaultValue = "0") int mSeq,
			@RequestParam(value = "fileName", required = false, defaultValue = "") String fileName,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			String FILE_LOCAL_PATH = FILE_ROOT + "/files/magazine/";
			String filePath = FILE_LOCAL_PATH + fileName;
			String thumbPath = FILE_LOCAL_PATH + "thumb/" + fileName;

			File file = new File(filePath);
			file.delete();

			File thumb = new File(thumbPath);
			thumb.delete();

			// 게시물 업데이트
			magazineDao.updateMagazineFile(mSeq, "");

			map.put("message", "파일이 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "파일이 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 서브 등록/수정
	@RequestMapping("/admin/contents/subcontents_edit.go")
	public String subcontentsEditController(
			@RequestParam(value = "pSeq", required = false, defaultValue = "0") int pSeq,
			@RequestParam(value = "mSeq", required = false, defaultValue = "0") int mSeq,
			Model model) {

		MagazinePage mp = null;

		if (pSeq == 0) {
			mp = new MagazinePage();
			int page = magazinepageDao.getMagazinePageCnt(mSeq);
			model.addAttribute("page", page + 1);
		} else {
			mp = magazinepageDao.getMagaginePage(pSeq);
			model.addAttribute("page", mp.getPage());
		}
		model.addAttribute("mSeq", mSeq);
		model.addAttribute("mp", mp);

		return "admin/contents/subcontents_edit";
	}

	// 건강매거진 서브 수정의 처리
	@RequestMapping("/admin/contents/subcontents_edit_do.go")
	public String subcontentsEditDoController(HttpServletRequest req,
			HttpServletResponse res, Model model) throws IllegalStateException,
			IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		int mSeq = 0;

		try {
			String FILE_PATH = "/files/submagazine/";
			String FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;
			String photo = "";
			String path = "";
			String photoPre = "";
			int fileSize = FILE_MAX_SIZE * 1024 * 1024;

			String fileName = "";
			String thumbName = "";

			req.setCharacterEncoding("utf-8");

			File file = null;
			MultipartRequest multi = new MultipartRequest(req, FILE_LOCAL_PATH,
					fileSize, "UTF-8", new UniqFileRenamePolicy());

			mSeq = Integer
					.parseInt(F.nullCheck(multi.getParameter("mSeq"), "0"));
			int pSeq = Integer.parseInt(F.nullCheck(multi.getParameter("pSeq"),
					"0"));
			String userId = F.nullCheck(multi.getParameter("userId"), "");
			String pageTitle = F.nullCheck(multi.getParameter("pageTitle"), "");
			String pageContents = F.nullCheck(
					multi.getParameter("pageContents"), "");
			int page = Integer.parseInt(F.nullCheck(multi.getParameter("page"),
					"0"));

			Enumeration files = multi.getFileNames();
			while (files.hasMoreElements()) {
				String elementName = (String) files.nextElement();
				file = multi.getFile(elementName);
				if (file != null) {
					fileName = file.getName();
				}
			}

			if (fileName.equals("") == false) {
				// 축소이미지 저장
				File newFile = new File(FILE_LOCAL_PATH + fileName);
				File thumbFile = new File(FILE_LOCAL_PATH + "thumb/" + fileName);
				if (!thumbFile.exists()) {
					thumbFile.mkdirs();
				}
				try {
					ImageUtil.resize(newFile, thumbFile, 100, 0);
					result = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (pSeq == 0) {
				MagazinePage mp = new MagazinePage();
				mp.setmSeq(mSeq);
				mp.setPageContents(pageContents);
				mp.setPageTitle(pageTitle);
				mp.setPage(page);
				mp.setPageFilename(fileName);
				mp.setPageThumname(fileName);
				magazinepageDao.addMagazinePage(mp);
				pSeq = magazinepageDao.getLastId();

				result = true;
				message = "등록되었습니다.";
			} else {
				MagazinePage mp = magazinepageDao.getMagaginePage(pSeq);
				mp.setmSeq(mSeq);
				mp.setPageContents(pageContents);
				mp.setPageTitle(pageTitle);
				mp.setPage(page);
				if (fileName.equals("") == false) {
					mp.setPageFilename(fileName);
					mp.setPageThumname(fileName);
				}
				magazinepageDao.updateMagazinePage(mp);
				result = true;
				message = "수정되었습니다.";
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
		map.put("mSeq", mSeq);
		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 건강매거진 서브 이미지 삭제
	@RequestMapping("/admin/contents/subcontents_file_delete.go")
	public String subcontentsFileDeleteController(
			@RequestParam(value = "pSeq", required = false, defaultValue = "0") int pSeq,
			@RequestParam(value = "fileName", required = false, defaultValue = "") String fileName,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			String FILE_LOCAL_PATH = FILE_ROOT + "/files/submagazine/";
			String filePath = FILE_LOCAL_PATH + fileName;
			String thumbPath = FILE_LOCAL_PATH + "thumb/" + fileName;

			File file = new File(filePath);
			file.delete();

			File thumb = new File(thumbPath);
			thumb.delete();

			// 게시물 업데이트
			magazinepageDao.updateFile("", pSeq);
			map.put("pSeq", pSeq);
			map.put("message", "파일이 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "파일이 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 서브페이지 삭제
	@RequestMapping("/admin/contents/subcontents_delete_do.go")
	public String subcontentsDeleteController(
			@RequestParam(value = "pSeq", required = false, defaultValue = "0") int pSeq,

			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			MagazinePage mp = magazinepageDao.getMagaginePage(pSeq);
			String fileName = mp.getPageFilename();

			String FILE_LOCAL_PATH = FILE_ROOT + "/files/submagazine/";
			String filePath = FILE_LOCAL_PATH + fileName;
			String thumbPath = FILE_LOCAL_PATH + "thumb/" + fileName;

			File file = new File(filePath);
			file.delete();

			File thumb = new File(thumbPath);
			thumb.delete();

			// 게시물 삭제
			magazinepageDao.deleteMagazinePage(pSeq);

			List<MagazinePage> list = magazinepageDao.getMagazinePageList(mp
					.getmSeq());
			for (int i = 1; i < list.size() + 1; i++) {
				int seq = (list.get(i - 1)).getpSeq();
				magazinepageDao.updatePage(seq, i);
			}

			map.put("message", "페이지가 삭제되었습니다.");
			map.put("result", true);
		} catch (Exception e) {

			map.put("message", "페이지가 삭제되지 않았습니다.\n" + e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	@RequestMapping("/admin/contents/subcontents_sort_do.go")
	public String subsortEditController(
			@RequestParam(value = "arrSeq", required = false, defaultValue = "") String[] arrSeq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			int Count = arrSeq.length;

			if (arrSeq != null || !(arrSeq.equals(""))) {

				for (int i = 0; i < Count; i++) {

					int pSeq = Integer.parseInt(arrSeq[i]);

					magazinepageDao.updatePage(pSeq, i + 1);

					result = true;
					message = "수정되었습니다.";
				}

			}

		} catch (Exception e) {
			message = e.getMessage();
		}

		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 1일주기 컨텐츠
	@RequestMapping("/admin/day/category.go")
	public String dayCategoryController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpServletResponse res, Model model
		) {

		List list = daydao.dayGroup(page, ITEM_COUNT_PER_PAGE);
		int count = daydao.dayGroupCount();

		for (int i=0; i<list.size(); i++) {
			DayGroup group = (DayGroup)list.get(i);
			int daygroup = group.getDaygroup();
			List contentsList = daydao.getDaylistTop1(daygroup); 
			group.setDayList(contentsList);
			list.set(i, group);
		}
		
		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE, PAGE_COUNT_PER_PAGING);
		
		int maxGroup = daydao.getMaxGroup();

		model.addAttribute("maxGroup", maxGroup);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		return "/admin/day/category";
	}
	
	@RequestMapping("/admin/day/contents.go")
	public String dayConController(
			@RequestParam(value = "group", required = false, defaultValue = "1") int group,
			HttpServletResponse res, Model model
		) {
		model.addAttribute("daygroup", daydao.daygroup());
		model.addAttribute("group", group);
		return "/admin/day/contents";
	}

	// 1일주기 컨텐츠

	@RequestMapping("/admin/day/contents_list.go")
	public String dayConListController(
			@RequestParam(value = "group", required = false, defaultValue = "1") int group,
			HttpServletResponse res, Model model) {

		try {
			List<Day> list = daydao.getDaylist(group);
			model.addAttribute("list", list);
			model.addAttribute("group", group);
			model.addAttribute("genderType", list.get(0).getGenderType());
		} catch (Exception e) {
			String message = e.getMessage();
			model.addAttribute("message", message);
		}

		return "/admin/day/contents_list";
	}

	// 정렬
	@RequestMapping("/admin/day/contents_sort_do.go")
	public String daysortEditController(
			@RequestParam(value = "arrSeq", required = false, defaultValue = "") String[] arrSeq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			int Count = arrSeq.length;

			if (arrSeq != null || !(arrSeq.equals(""))) {

				for (int i = 0; i < Count; i++) {

					int daySeq = Integer.parseInt(arrSeq[i]);

					daydao.updatesort(daySeq, i + 1);

					result = true;
					message = "수정되었습니다.";
				}

			}

		} catch (Exception e) {
			message = e.getMessage();
		}

		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 컨텐츠 수정
	@RequestMapping("/admin/day/contents_list_edit_do.go")
	public String dayeditdoConController(
			@RequestParam(value = "daySeq", required = false, defaultValue = "0") int daySeq,
			@RequestParam(value = "daygroup", required = false, defaultValue = "0") int daygroup,
			@RequestParam(value = "goseq", required = false, defaultValue = "0") int move,
			@RequestParam(value = "pseq", required = false, defaultValue = "0") int pseq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			Day dd = new Day();
			dd.setAnsType(ansType);
			dd.setComment(comment);
			dd.setMove(move);
			dd.setDaySeq(daySeq);
			dd.setPseq(pseq);
			daydao.updateday(dd);
			map.put("message", "수정되었습니다");
			map.put("result", true);

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 삭제
	@RequestMapping("/admin/day/contents_list_delete.go")
	public String daydeleteController(
			@RequestParam(value = "daySeq", required = false, defaultValue = "0") int daySeq,
			@RequestParam(value = "daygroup", required = false, defaultValue = "0") int daygroup,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			daydao.deleteday(daySeq);
			List<Day> list = daydao.getDaylist(daygroup);
			for (int i = 1; i < list.size() + 1; i++) {
				int seq = (list.get(i - 1)).getDaySeq();
				daydao.updatesort(seq, i);
			}
			map.put("message", "삭제되었습니다");
			map.put("result", true);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 삭제
	@RequestMapping("/admin/day/contents_group_delete.go")
	public String dayGroupdeleteController(
			@RequestParam(value = "daygroup", required = false, defaultValue = "0") int daygroup,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			daydao.deletedayGroup(daygroup);
			map.put("message", "삭제되었습니다");
			map.put("result", true);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	/*
	 * daygroup : frm.daygroup.value, askind : frm.askind.value, comment :
	 * frm.comment.value, ansType : frm.ansType.value, pseq : frm.pseq.value,
	 * move : frm.move.value, maxsort : frm.maxsort.value
	 */

	// 추가
	@RequestMapping("/admin/day/contents_add.go")
	public String daydeleteController(
			@RequestParam(value = "daygroup", required = false, defaultValue = "0") int daygroup,
			@RequestParam(value = "askind", required = false, defaultValue = "0") int askind,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			@RequestParam(value = "pseq", required = false, defaultValue = "0") int pseq,
			@RequestParam(value = "goseq", required = false, defaultValue = "0") int move,
			@RequestParam(value = "maxsort", required = false, defaultValue = "0") int maxsort,
			@RequestParam(value = "genderType", required = false, defaultValue = "0") int genderType,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			Day dd = new Day();

			dd.setMove(move);
			dd.setDaygroup(daygroup);
			dd.setComment(comment);
			dd.setAnsType(ansType);
			dd.setSort(maxsort + 1);
			dd.setAskind(askind);
			dd.setGenderType(genderType);
			dd.setPseq(pseq);

			daydao.addcontent(dd);
			map.put("message", "추가되었습니다.");
			map.put("result", true);

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 그룹생성
	@RequestMapping("/admin/day/addcontents.go")
	public String addgroupController(
			@RequestParam(value = "nextgroup", required = false, defaultValue = "0") int nextgroup,
			@RequestParam(value = "genderType", required = false, defaultValue = "0") int genderType,
			HttpServletResponse res, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			Day dd = new Day();
			dd.setGenderType(genderType);
			dd.setDaygroup(nextgroup + 1);
			dd.setAskind(1);
			daydao.addcontent(dd);
			int daygroup = daydao.getLastId();
			map.put("message", "추가되었습니다.");
			map.put("result", true);
			map.put("daygroup", daygroup);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// isLast
	@RequestMapping("/admin/day/contents_isLast.go")
	public String dayisLastController(
			@RequestParam(value = "daySeq", required = false, defaultValue = "0") int daySeq,
			@RequestParam(value = "arrSeq", required = false, defaultValue = "0") String[] arrSeq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (arrSeq != null || !(arrSeq.equals(""))) {

				for (int i = 0; i < arrSeq.length; i++) {

					int Seq = Integer.parseInt(arrSeq[i]);
					daydao.updatelast(Seq, 0);
					if (Seq == daySeq) {
						daydao.updatelast(Seq, 1);
					}
				}

			}

			map.put("message", "변경되었습니다.");
			map.put("result", true);

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// ////////////////////////1주주기

	@RequestMapping("/admin/week/category.go")
	public String weekCategoryController(
			@RequestParam(value = "diseaseId", required = false, defaultValue = "blood") String diseaseId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpServletResponse res, Model model
		) {
		// int maxnum = daydao.getGroupCount();
		List list = null;
		int count = 0;

		if (diseaseId.equals("")) {
			list = weekdao.weekGroup(page, ITEM_COUNT_PER_PAGE);
			count = weekdao.weekGroupCount();
		} else {
			list = weekdao.weekGroup(page, ITEM_COUNT_PER_PAGE, diseaseId);
			count = weekdao.weekGroupCount(diseaseId);
		}

		for (int i=0; i<list.size(); i++) {
			WeekGroup group = (WeekGroup)list.get(i);
			int weekgroup = group.getWeekgroup();
			String dId = group.getDiseaseId();
			List contentsList = weekdao.getWeeklistTop1(weekgroup, dId); 
			group.setWeekList(contentsList);
			list.set(i, group);
		}
		
		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE, PAGE_COUNT_PER_PAGING);

		int maxGroup = weekdao.getMaxGroup();

		model.addAttribute("maxGroup", maxGroup);
		model.addAttribute("diseaseId", diseaseId);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);
		return "/admin/week/category";
	}

	// 1주기 컨텐츠
	@RequestMapping("/admin/week/contents.go")
	public String weekConController(
			HttpServletResponse res,
			Model model,
			@RequestParam(value = "group", required = false, defaultValue = "1") int group,
			@RequestParam(value = "diseaseId", required = false, defaultValue = "") String diseaseId) {
		// int maxnum = daydao.getGroupCount();

		List<Week> list = weekdao.getGroupCount(diseaseId);
		model.addAttribute("diseaseId", diseaseId);
		model.addAttribute("weekgroup", list);
		model.addAttribute("group", group);
		model.addAttribute("diseaseId", diseaseId);
		return "/admin/week/contents";

	}

	@RequestMapping("/admin/week/contents_list.go")
	public String weekConController(
			@RequestParam(value = "weekgroup", required = false, defaultValue = "1") int weekgroup,
			@RequestParam(value = "diseaseId", required = false, defaultValue = "") String diseaseId,
			HttpServletResponse res, Model model) {

		try {
			List<Week> list = weekdao.getWeeklist2(weekgroup, diseaseId);
			List<Week> list2 = weekdao.getGroupCount(diseaseId);
			model.addAttribute("weekgroup", list2);
			model.addAttribute("diseaseId", diseaseId);
			model.addAttribute("list", list);
			model.addAttribute("group", weekgroup);
		} catch (Exception e) {

		}

		return "/admin/week/contents_list";
	}

	// 정렬
	@RequestMapping("/admin/week/contents_sort_do.go")
	public String weeksortEditController(
			@RequestParam(value = "arrSeq", required = false, defaultValue = "") String[] arrSeq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			int Count = arrSeq.length;

			if (arrSeq != null || !(arrSeq.equals(""))) {

				for (int i = 0; i < Count; i++) {

					int Seq = Integer.parseInt(arrSeq[i]);

					weekdao.updatesortWeek(Seq, i + 1);

					result = true;
					message = "수정되었습니다.";
				}

			}

		} catch (Exception e) {
			message = e.getMessage();
		}

		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 컨텐츠 수정
	@RequestMapping("/admin/week/contents_list_edit_do.go")
	public String weekeditdoConController(
			@RequestParam(value = "weekSeq", required = false, defaultValue = "0") int weekSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,

			@RequestParam(value = "goseq", required = false, defaultValue = "0") int move,
			@RequestParam(value = "pseq", required = false, defaultValue = "0") int pseq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			Week ww = new Week();
			ww.setMove(move);
			ww.setPseq(pseq);

			ww.setWeekSeq(weekSeq);
			ww.setComment(comment);
			ww.setAnsType(ansType);
			weekdao.updateWeek(ww);
			map.put("message", "수정되었습니다");
			map.put("result", true);

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 삭제
	@RequestMapping("/admin/week/contents_list_delete.go")
	public String weekdeleteController(
			@RequestParam(value = "weekSeq", required = false, defaultValue = "0") int weekSeq,
			@RequestParam(value = "weekgroup", required = false, defaultValue = "0") int weekgroup,
			@RequestParam(value = "diseaseId", required = false, defaultValue = "0") String diseaseId,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			weekdao.deleteWeek(weekSeq);
			List<Week> list = weekdao.getWeeklist2(weekgroup, diseaseId);
			for (int i = 1; i < list.size() + 1; i++) {
				int seq = (list.get(i - 1)).getWeekSeq();
				weekdao.updatesortWeek(seq, i);
			}
			map.put("message", "삭제되었습니다");
			map.put("result", true);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 삭제
	@RequestMapping("/admin/week/contents_group_delete.go")
	public String weekGroupdeleteController(
			@RequestParam(value = "weekgroup", required = false, defaultValue = "0") int weekgroup,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			weekdao.deleteWeekGroup(weekgroup);
			map.put("message", "삭제되었습니다");
			map.put("result", true);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 추가
	@RequestMapping("/admin/week/contents_add.go")
	public String weekaddController(
			@RequestParam(value = "weekgroup", required = false, defaultValue = "0") int weekgroup,
			@RequestParam(value = "askind", required = false, defaultValue = "0") int askind,
			@RequestParam(value = "diseaseId", required = false, defaultValue = "") String diseaseId,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			@RequestParam(value = "pseq", required = false, defaultValue = "0") int pseq,
			@RequestParam(value = "goseq", required = false, defaultValue = "0") int move,
			@RequestParam(value = "maxsort", required = false, defaultValue = "0") int maxsort,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			System.out.println("dd");
			Week ww = new Week();

			ww.setMove(move);
			ww.setWeekgroup(weekgroup);
			ww.setComment(comment);
			ww.setAnsType(ansType);
			ww.setSort(maxsort + 1);
			ww.setAskind(askind);
			ww.setDiseaseId(diseaseId);
			ww.setPseq(pseq);

			weekdao.addWeek(ww);
			map.put("message", "추가되었습니다.");
			map.put("result", true);

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 그룹생성
	@RequestMapping("/admin/week/addcontents.go")
	public String weekaddgroupController(
			@RequestParam(value = "nextgroup", required = false, defaultValue = "0") int nextgroup,
			@RequestParam(value = "diseaseId", required = false, defaultValue = "") String diseaseId,

			HttpServletResponse res, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(diseaseId);
		try {
			Week ww = new Week();
			ww.setDiseaseId(diseaseId);
			ww.setWeekgroup(nextgroup + 1);
			ww.setAskind(1);
			weekdao.addWeek(ww);
			int weekgroup = weekdao.getLastId();
			map.put("message", "추가되었습니다.");
			map.put("result", true);
			map.put("weekgroup", weekgroup);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// isLast
	@RequestMapping("/admin/week/contents_isLast.go")
	public String weekisLastController(
			@RequestParam(value = "weekSeq", required = false, defaultValue = "0") int weekSeq,
			@RequestParam(value = "arrSeq", required = false, defaultValue = "0") String[] arrSeq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (arrSeq != null || !(arrSeq.equals(""))) {

				for (int i = 0; i < arrSeq.length; i++) {

					int Seq = Integer.parseInt(arrSeq[i]);
					weekdao.updateislastWeek(Seq, 0);
					if (Seq == weekSeq) {
						weekdao.updateislastWeek(Seq, 1);
					}
				}

			}

			map.put("message", "변경되었습니다.");
			map.put("result", true);

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// ////////////////////////1개월주기

	// 1개월 컨텐츠

	@RequestMapping("/admin/month/contents.go")
	public String monthConController(
			HttpServletResponse res,
			Model model,
			@RequestParam(value = "month", required = false, defaultValue = "") String month) {

		if (month.equals("")) {
			month = T.getMonth();

		}
		List<Month> list = monthdao.getGroup();
		model.addAttribute("month", list);
		model.addAttribute("nowmonth", month);

		return "/admin/month/contents";

	}

	@RequestMapping("/admin/month/contents_list.go")
	public String monthConController(
			@RequestParam(value = "month", required = false, defaultValue = "") String month,

			HttpServletResponse res, Model model) {

		try {
			if (month.equals("")) {
				month = T.getMonth();

			}
			List<Month> list = monthdao.getGroup();
			List<Month> list2 = monthdao.getmonthlist2(month);
			model.addAttribute("month", list);
			model.addAttribute("nowmonth", month);
			model.addAttribute("list", list2);

		} catch (Exception e) {

		}

		return "/admin/month/contents_list";
	}

	// 정렬
	@RequestMapping("/admin/month/contents_sort_do.go")
	public String monthsortEditController(
			@RequestParam(value = "arrSeq", required = false, defaultValue = "") String[] arrSeq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			int Count = arrSeq.length;

			if (arrSeq != null || !(arrSeq.equals(""))) {

				for (int i = 0; i < Count; i++) {

					int Seq = Integer.parseInt(arrSeq[i]);

					monthdao.updatesort(Seq, i + 1);

					result = true;
					message = "수정되었습니다.";
				}

			}

		} catch (Exception e) {
			message = e.getMessage();
		}

		map.put("result", result);
		map.put("message", message);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 컨텐츠 수정
	@RequestMapping("/admin/month/contents_list_edit_do.go")
	public String montheditdoConController(
			@RequestParam(value = "monthSeq", required = false, defaultValue = "0") int monthSeq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			@RequestParam(value = "goseq", required = false, defaultValue = "0") int move,
			@RequestParam(value = "pseq", required = false, defaultValue = "0") int pseq,
			@RequestParam(value = "month", required = false, defaultValue = "0") String month,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			Month mm = new Month();
			mm.setMonth(month);
			mm.setMove(move);
			mm.setPseq(pseq);
			mm.setMonthSeq(monthSeq);
			mm.setComment(comment);
			mm.setAnsType(ansType);
			monthdao.updateMonthdata(mm);
			map.put("message", "수정되었습니다");
			map.put("result", true);

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 삭제
	@RequestMapping("/admin/month/contents_list_delete.go")
	public String monthdeleteController(
			@RequestParam(value = "monthSeq", required = false, defaultValue = "0") int monthSeq,
			@RequestParam(value = "month", required = false, defaultValue = "") String month,

			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			monthdao.deleteMonth(monthSeq);
			List<Month> list = monthdao.getmonthlist2(month);
			for (int i = 1; i < list.size() + 1; i++) {
				int seq = (list.get(i - 1)).getMonthSeq();
				monthdao.updatesort(seq, i);
			}
			map.put("message", "삭제되었습니다");
			map.put("result", true);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 추가
	@RequestMapping("/admin/month/contents_add.go")
	public String monthaddController(
			@RequestParam(value = "month", required = false, defaultValue = "") String month,
			@RequestParam(value = "askind", required = false, defaultValue = "0") int askind,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "ansType", required = false, defaultValue = "0") int ansType,
			@RequestParam(value = "pseq", required = false, defaultValue = "0") int pseq,
			@RequestParam(value = "goseq", required = false, defaultValue = "0") int move,
			@RequestParam(value = "maxsort", required = false, defaultValue = "0") int maxsort,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			Month mm = new Month();

			mm.setMove(move);
			mm.setMonth(month);
			mm.setComment(comment);
			mm.setAnsType(ansType);
			mm.setSort(maxsort + 1);
			mm.setAskind(askind);
			mm.setPseq(pseq);

			monthdao.addMonth(mm);
			map.put("message", "추가되었습니다.");
			map.put("result", true);

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 그룹생성
	@RequestMapping("/admin/month/addcontents.go")
	public String monthaddgroupController(
			@RequestParam(value = "newmonth", required = false, defaultValue = "") String newmonth,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(newmonth);
		try {
			Month mm = new Month();
			mm.setMonth(newmonth);
			mm.setAskind(1);
			monthdao.addMonth(mm);
			map.put("message", "추가되었습니다.");
			map.put("result", true);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// isLast
	@RequestMapping("/admin/month/contents_isLast.go")
	public String monthisLastController(
			@RequestParam(value = "monthSeq", required = false, defaultValue = "0") int monthSeq,
			@RequestParam(value = "arrSeq", required = false, defaultValue = "0") String[] arrSeq,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (arrSeq != null || !(arrSeq.equals(""))) {

				for (int i = 0; i < arrSeq.length; i++) {

					int Seq = Integer.parseInt(arrSeq[i]);
					monthdao.updateislast(Seq, 0);
					if (Seq == monthSeq) {
						monthdao.updateislast(Seq, 1);
					}
				}

			}

			map.put("message", "변경되었습니다.");
			map.put("result", true);

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	


	// isLast
	@RequestMapping("/admin/chat/chat.go")
	public String chatController(
			HttpServletResponse res, Model model) {
	
		return "/admin/chat/chat";
	}
	
	// isLast
	@RequestMapping("/admin/chat/chat_list.go")
	public String chatListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		List list = null;
		int count = 0;
		String paging = "";
				
		try {
			if (keyword.equals("")) {
				list = chatCounselDao.getChatCounselList(page, ITEM_COUNT_PER_PAGE);
				count = chatCounselDao.getCount();
			} else {
				list = chatCounselDao.getChatCounselList(page, ITEM_COUNT_PER_PAGE, keyword);
				count = chatCounselDao.getCount(keyword);
			}
			
			/*
			for (int i=0; i<list.size(); i++) {
				ChatRoom room = (ChatRoom)list.get(i);
				List memberList = chatMemberDao.getChatMemberList(room.getChatRoomSeq());
				room.setMemberList(memberList);
				
				List lastChat = chatMsgDao.getChatMsgLast3(room.getChatRoomSeq());
				room.setLastChat(lastChat);
				
				list.set(i, room);
				
			}
			*/
			// 페이징
			paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE, PAGE_COUNT_PER_PAGING);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);

		return "/admin/chat/chat_list";
	}

	// isLast
	@RequestMapping("/admin/chat/chat_view.go")
	public String chatViewController(
			@RequestParam(value = "chatRoomSeq", required = false, defaultValue = "1") int chatRoomSeq,
			HttpServletResponse res, Model model) {
	
		model.addAttribute("chatRoomSeq", chatRoomSeq);
		return "/admin/chat/chat_view";
	}
	// isLast
	@RequestMapping("/admin/chat/chat_view_list.go")
	public String chatViewListController(
			@RequestParam(value = "chatRoomSeq", required = false, defaultValue = "1") int chatRoomSeq,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		List list = null;
		int count = 0;
		String paging = "";
				
		try {
			list = chatMsgDao.getChatMsgList(chatRoomSeq, page, ITEM_COUNT_PER_PAGE);
			count = chatMsgDao.getCount(chatRoomSeq);
			
			for(int i=0; i<list.size(); i++) {
				ChatMsg msg = (ChatMsg)list.get(i);
				if (msg.getFileName().equals("")==false) {
					String fileName = msg.getFileName();
					
					JSONObject jo = JSONObject.fromObject(fileName);
					JSONArray ja = (JSONArray)jo.get("items");
					JSONObject j = (JSONObject)ja.get(0);
					String filePath = (String)j.get("thumb");
					msg.setContents("<img src=\""+filePath+"\" onclick=\"pop.zoom('"+filePath+"');\">");
				}
			}
			
			// 페이징
			paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE, PAGE_COUNT_PER_PAGING);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("currentPage", page);

		return "/admin/chat/chat_view_list";
	}
	

	
	public void filedelete(String fullName){
		
		
		
		File file = new File(FILE_ROOT+fullName);
		file.delete();
		//썸네일
		String  path = fullName.substring(0,fullName.lastIndexOf("/"));//경로까지
		String fileName = fullName.substring(fullName.lastIndexOf("/"));//파일명
		String thumbName = path+"/thumb/"+fileName;
		File thumb = new File(FILE_ROOT+thumbName);
		thumb.delete();
		
	}

}
