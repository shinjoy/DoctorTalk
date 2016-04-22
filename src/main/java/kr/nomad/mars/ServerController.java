
package kr.nomad.mars;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.ibm.icu.util.Calendar;
import com.oreilly.servlet.MultipartRequest;

import kr.nomad.mars.dao.AnalisysDao;
import kr.nomad.mars.dao.BadgeDao;
import kr.nomad.mars.dao.ChatCounselDao;
import kr.nomad.mars.dao.ChatMemberDao;
import kr.nomad.mars.dao.ChatMsgDao;
import kr.nomad.mars.dao.ChatRoomDao;
import kr.nomad.mars.dao.CommentDao;
import kr.nomad.mars.dao.ConfigDao;
import kr.nomad.mars.dao.CounselDao;
import kr.nomad.mars.dao.CperiodDao;
import kr.nomad.mars.dao.CvriskDao;
import kr.nomad.mars.dao.DayDao;
import kr.nomad.mars.dao.EperiodDao;
import kr.nomad.mars.dao.MagazineDao;
import kr.nomad.mars.dao.ManageIndexDao;
import kr.nomad.mars.dao.MedExamDao;
import kr.nomad.mars.dao.MonthDao;
import kr.nomad.mars.dao.NoticeDao;
import kr.nomad.mars.dao.PeriodDao;
import kr.nomad.mars.dao.PointerDao;
import kr.nomad.mars.dao.PushDao;
import kr.nomad.mars.dao.ReportDao;
import kr.nomad.mars.dao.ReportHistoryDao;
import kr.nomad.mars.dao.UBasicDao;
import kr.nomad.mars.dao.UCntDao;
import kr.nomad.mars.dao.UColDao;
import kr.nomad.mars.dao.UCvriskDao;
import kr.nomad.mars.dao.UGoalDao;
import kr.nomad.mars.dao.UHbDao;
import kr.nomad.mars.dao.UMediDao;
import kr.nomad.mars.dao.UMediLogDao;
import kr.nomad.mars.dao.UPresDao;
import kr.nomad.mars.dao.UPressDao;
import kr.nomad.mars.dao.UWeightDao;
import kr.nomad.mars.dao.UbloodDao;
import kr.nomad.mars.dao.UserDao;
import kr.nomad.mars.dao.WeekDao;
import kr.nomad.mars.dto.Analisys;
import kr.nomad.mars.dto.ChatCounsel;
import kr.nomad.mars.dto.ChatMember;
import kr.nomad.mars.dto.ChatMsg;
import kr.nomad.mars.dto.ChatRoom;
import kr.nomad.mars.dto.Comment;
import kr.nomad.mars.dto.Config;
import kr.nomad.mars.dto.Counsel;
import kr.nomad.mars.dto.Cperiod;
import kr.nomad.mars.dto.Cvrisk;
import kr.nomad.mars.dto.Day;
import kr.nomad.mars.dto.Eperiod;
import kr.nomad.mars.dto.Magazine;
import kr.nomad.mars.dto.ManageIndex;
import kr.nomad.mars.dto.MedExam;
import kr.nomad.mars.dto.Month;
import kr.nomad.mars.dto.Notice;
import kr.nomad.mars.dto.Period;
import kr.nomad.mars.dto.Pointer;
import kr.nomad.mars.dto.Push;
import kr.nomad.mars.dto.Report;
import kr.nomad.mars.dto.ReportHistory;
import kr.nomad.mars.dto.User;
import kr.nomad.mars.dto.UserBasic;
import kr.nomad.mars.dto.UserBlood;
import kr.nomad.mars.dto.UserCnt;
import kr.nomad.mars.dto.UserCol;
import kr.nomad.mars.dto.UserCvrisk;
import kr.nomad.mars.dto.UserGoal;
import kr.nomad.mars.dto.UserMedi;
import kr.nomad.mars.dto.UserMediLog;
import kr.nomad.mars.dto.UserPres;
import kr.nomad.mars.dto.UserPress;
import kr.nomad.mars.dto.UserReport;
import kr.nomad.mars.dto.UserWeight;
import kr.nomad.mars.dto.Userhb;
import kr.nomad.mars.dto.Week;
import kr.nomad.mars.dto.WeekPointer;
import kr.nomad.util.F;
import kr.nomad.util.ImageUtil;
import kr.nomad.util.Response;
import kr.nomad.util.T;
import kr.nomad.util.XlsxWriter;
import kr.nomad.util.file.UniqFileRenamePolicy;
import net.sf.json.JSONObject;

@Controller
public class ServerController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	UserDao userdao;

	@Autowired
	UbloodDao ublooddao;

	@Autowired
	UPressDao upressdao;

	@Autowired
	UWeightDao uweightdao;

	@Autowired
	UCntDao ucntdao;

	@Autowired
	UCvriskDao ucvriskdao;

	@Autowired
	UMediDao umedidao;

	@Autowired
	UPresDao upresdao;

	@Autowired
	UGoalDao ugoaldao;

	@Autowired
	UBasicDao ubasicdao;

	@Autowired
	NoticeDao noticedao;

	@Autowired
	MedExamDao medexamdao;

	@Autowired
	AnalisysDao anlisysdao;

	@Autowired
	PointerDao pointerdao;

	@Autowired
	UColDao ucoldao;

	@Autowired
	UHbDao uhbdao;

	@Autowired
	PeriodDao perioddao;

	@Autowired
	CperiodDao cperioddao;

	@Autowired
	EperiodDao eperioddao;

	@Autowired
	CvriskDao cvriskdao;

	@Autowired
	DayDao daydao;

	@Autowired
	WeekDao weekdao;

	@Autowired
	CommentDao commentdao;

	@Autowired
	WeekPointer wkpointer;

	@Autowired
	UMediLogDao uMediLogDao;

	@Autowired
	MagazineDao magaDao;

	@Autowired
	MonthDao monthdao;

	@Autowired
	ConfigDao configdao;

	@Autowired
	ReportDao reportdao;

	@Autowired
	CounselDao counseldao;

	@Autowired
	ManageIndexDao mindexdao;

	@Autowired
	PushDao pushDao;
	@Autowired
	BadgeDao badgeDao;

	@Autowired
	ChatCounselDao chatCounselDao;

	@Autowired
	ChatRoomDao chatRoomDao;

	@Autowired
	ChatMsgDao chatMsgDao;

	@Autowired
	ChatMemberDao chatMemberDao;
	
	@Autowired ReportHistoryDao reportHistoryDao;


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

	public String SERVER_DOMAIN = "http://mint.aimmed.com:8070";
	public String GOOGLE_MAIL_ID = "aimmednote@gmail.com";
	public String GOOGLE_MAIL_PW = "Tkdfkdlxm*";
	
	public String DEFAULT_COUNSELOR_ID = "counselor@test.com";


	private int category1;

	@RequestMapping("/api_view.go")
	public String wUserMenuController(Model model) {

		return "/api_view";
	}

	/**
	 * 회원가입
	 * 
	 * @param loginId
	 * @param loginPw
	 * @param res
	 * @return
	 */
	@RequestMapping("/m/aimmed_check.go")
	public String aimmedCheckController(
		@RequestParam(value = "userName", required = false, defaultValue = "") String userName,
		@RequestParam(value = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
		@RequestParam(value = "birthday", required = false, defaultValue = "") String birthday,
		HttpServletRequest req, HttpServletResponse res
	) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		int isAimmedUser = 0;
		String userMobile = "";
		String userBirth = "";
		String groupCode = "";
		String groupName = "";
		String aimmedId = "";

		try {

			// 끌어모았어 get 요청 URL 문자열 사용 URLEncoder.encode 대한 특별한 및 안 보이는 문자 인코딩 진행하다
			String getURL = "http://api.aimmed.co.kr/recovery/check_user.asp";
			String secret = "AimmedRecover";
			getURL += "?name="+ URLEncoder.encode(userName, "UTF-8");
			getURL += "&mobile="+ phoneNumber;
			getURL += "&birth="+ birthday;
			getURL += "&secret="+ secret;
			
			//String getURL = GET_URL + "?name=" + URLEncoder.encode("zhangshan", "utf-8");
			URL getUrl = new URL(getURL);
			// 끌어모았어 URL을 열 따라 연결할 URL 형식은 따라 할 URL.openConnection 함수, 
			// 다시 다른 URLConnection 하위 클래스 대상, 여기 URL 한 http 때문에 실제 복귀 것은 HttpURLConnection
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			// 을 연결 하지만 실제로는 get request 반드시 다음 구의 connection.getInputStream () 함수 중 비로소 진정한 발 까지 서버
			connection.connect();
			// 확실한 입력 및 사용 Reader 읽기
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 인코딩 설정 을 함께 했 다., 그렇지 않으면, 중국어
			System.out.println("=============================");
			System.out.println("Contents of get request");
			System.out.println("=============================");
			String lines;
			String message = "";
			while ((lines = reader.readLine()) != null) {
				String str = new String(lines.getBytes());
				message += URLDecoder.decode(str, "utf-8");
				System.out.println(lines);
			}
			reader.close();
			// 연결 끊기
			connection.disconnect();
			
			JSONObject jsonObject = JSONObject.fromObject(message);
			String statusCode = jsonObject.getString("STATUS_CODE");
			
			if (statusCode.equals("200")) {
				
				//userName = jsonObject.getString("name");
				//userMobile = jsonObject.getString("mobile");
				//userBirth = jsonObject.getString("birth");
				groupCode = jsonObject.getString("group_code");
				groupName = jsonObject.getString("group_name");
				aimmedId = jsonObject.getString("mem_id");
				
				isAimmedUser = 1;
				
				map.put("aimmedId", aimmedId);
				map.put("groupCode", groupCode);
				map.put("groupName", groupName);
				map.put("message", "에임메드 회원입니다.");
				map.put("isAimmedUser", isAimmedUser);
			} else {
				map.put("aimmedId", aimmedId);
				map.put("groupCode", groupCode);
				map.put("groupName", groupName);
				map.put("message", "에임메드 회원이 아닙니다.");
				map.put("isAimmedUser", isAimmedUser);
			}


		} catch (Exception e) {
			map.put("aimmedId", aimmedId);
			map.put("groupCode", groupCode);
			map.put("groupName", groupName);
			map.put("message", e.getMessage());
			map.put("isAimmedUser", isAimmedUser);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;

	}


	
	/**
	 * 회원가입
	 * 
	 * @param loginId
	 * @param loginPw
	 * @param res
	 * @return
	 */
	@RequestMapping("/m/join.go")
	public String joinController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "password", required = false, defaultValue = "") String password,
			@RequestParam(value = "userType", required = false, defaultValue = "0") int userType,
			@RequestParam(value = "userName", required = false, defaultValue = "") String userName,
			@RequestParam(value = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
			@RequestParam(value = "birthday", required = false, defaultValue = "") String birthday,
			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			@RequestParam(value = "loginNaver", required = false, defaultValue = "") int loginNaver,
			@RequestParam(value = "loginKakao", required = false, defaultValue = "") int loginKakao,
			@RequestParam(value = "osType", required = false, defaultValue = "") String osType,
			@RequestParam(value = "osVersion", required = false, defaultValue = "") String osVersion,
			@RequestParam(value = "appVersion", required = false, defaultValue = "") String appVersion,
			@RequestParam(value = "deviceName", required = false, defaultValue = "") String deviceName,
			@RequestParam(value = "deviceId", required = false, defaultValue = "") String deviceId,
			@RequestParam(value = "pushkey", required = false, defaultValue = "") String pushkey,
			@RequestParam(value = "usePushservice", required = false, defaultValue = "") int usePushservice,
			@RequestParam(value = "fileName", required = false, defaultValue = "") String fileName,
			@RequestParam(value = "aimmedId", required = false, defaultValue = "") String aimmedId,
			@RequestParam(value = "groupCode", required = false, defaultValue = "") String groupCode,
			@RequestParam(value = "groupName", required = false, defaultValue = "") String groupName,

	HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			boolean userCheck = userdao.correctId(userId);
			if (userCheck) {
				map.put("result", false);
				map.put("message", "중복된 이메일 주소입니다. 다른 주소로 입력해 주십시오. 지속적인 문제 발생 시 (관리자이메일)로 이메일을 보내 주세요.");
			} else {
				
				
				User uu = new User();
				uu.setAppVersion(appVersion);
				uu.setBirthday(birthday);
				uu.setDeviceId(deviceId);
				uu.setDeviceName(deviceName);
				uu.setFileName(fileName);
				uu.setGender(gender);
				uu.setLoginKakao(loginKakao);
				uu.setLoginNaver(loginNaver);
				uu.setOsType(osType);
				uu.setOsVersion(osVersion);
				uu.setPhoneNumber(phoneNumber);
				uu.setPushkey(pushkey);
				uu.setStatus(1);
				uu.setUsePushservice(usePushservice);
				uu.setUserId(userId);
				uu.setUserName(userName);
				uu.setUserType(userType);
				uu.setUserMed(0);
				uu.setAimmedId(aimmedId);
				uu.setGroupCode(groupCode);
				uu.setGroupName(groupName);

				// String enPw = Sha256Util.encryptPassword(password);

				uu.setPassword(password);
				userdao.addUser(uu);
				
				UserGoal usergoal = new UserGoal();
				usergoal.setUserId(userId);
				ManageIndex index = mindexdao.getManageIndex();

				usergoal.setGoalsMblood(index.getGoalSmblood());
				usergoal.setGoalbMblood(index.getGoalBmblood());
				usergoal.setGoalEblood(index.getGoalEblood());
				usergoal.setGoalSblood(index.getGoalSblood());
				usergoal.setGoalHba(index.getGoalHba());
				usergoal.setGoalsPre(index.getGoalSpre());// 수축기
				usergoal.setGoalbPre(index.getGoalBpre());// 이완기
				usergoal.setGoalPul(index.getGoalPul());
				usergoal.setGoalCol(index.getGoalCol());
				usergoal.setGoalLdl(index.getGoalLdl());
				usergoal.setGoalHdl(index.getGoalHdl());
				usergoal.setGoalTg(index.getGoalTg());
				usergoal.setGoalbBmi(index.getGoalBbmi());
				usergoal.setGoalsBmi(index.getGoalSbmi());

				ugoaldao.addUserGoal(usergoal);
				UserCnt uc = new UserCnt();
				uc.setUserId(userId);
				ucntdao.addUserCnt(uc);

				map.put("result", true);
				map.put("message", "회원가입이 완료되었습니다.");
			}
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;

	}

	@RequestMapping("/m/profile_photo.go")
	public String proUploadController(HttpServletRequest req, HttpServletResponse res, Model model,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "fileName", required = false, defaultValue = "") String fileName) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userdao.updateProfileImgAdd(userId, fileName);
			map.put("result", true);
		} catch (Exception e) {
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 프로필 이미지등록
	@RequestMapping("/m/photo_add.go")
	public String proUploadController(HttpServletRequest req, HttpServletResponse res, Model model

	) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = false;

		String FILE_PATH = "/files/temp/";
		String FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;
		String photo = "";
		String fileName = "";
		String path = "";
		String photoPre = "";
		String userId = "";
		int fileSize = FILE_MAX_SIZE * 1024 * 1024;

		try {
			req.setCharacterEncoding("utf-8");

			File file = null;

			MultipartRequest multi = new MultipartRequest(req, FILE_LOCAL_PATH, fileSize, "UTF-8",
					new UniqFileRenamePolicy());

			path = F.nullCheck(multi.getParameter("path"), "");
			Enumeration files = multi.getFileNames();
			while (files.hasMoreElements()) {
				String elementName = (String) files.nextElement();
				file = multi.getFile(elementName);
				if (file != null) {
					photo = file.getName();
				}

			}

			String yearMonth = T.getTodayYear() + T.getTodayMonth();

			// fileName = userId +"_"+ photo;
			fileName = photo;
			photoPre = yearMonth + "/";

			File copyFolder = new File(FILE_ROOT + "/files/" + path + "/" + photoPre);
			if (!copyFolder.exists()) {
				copyFolder.mkdirs();
			}
			// 파일 복사
			FileInputStream fis = new FileInputStream(FILE_LOCAL_PATH + photo);
			FileOutputStream fos = new FileOutputStream(FILE_ROOT + "/files/" + path + "/" + photoPre + fileName);
			int data = 0;
			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
			fis.close();
			fos.close();

			// 축소이미지 저장
			File newFile = new File(FILE_LOCAL_PATH + photo);
			File thumbFile = new File(FILE_ROOT + "/files/" + path + "/" + photoPre + "thumb/" + fileName);
			if (!thumbFile.exists()) {
				thumbFile.mkdirs();
			}
			try {
				ImageUtil.resize(newFile, thumbFile, 200, 0);
				result = true;
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 복사한뒤 원본파일을 삭제함
			file.delete();
			if (path.equals("profile")) {
				userId = F.nullCheck(multi.getParameter("userId"), "");
				userdao.updateProfileImgAdd(userId, "/files/" + path + "/" + photoPre + fileName);
			}

			map.put("result", true);
			map.put("message", "사진이 등록되었습니다.");
			map.put("photo", "/files/" + path + "/" + photoPre + fileName);

		} catch (Exception e) {
			map.put("result", false);
			map.put("message", "사진 등록에 실패하였습니다.\n" + e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);

		Gson gson = new Gson();
		String outputstr = gson.toJson(jsonObject);
		Response.responseWrite(res, outputstr);

		return null;

	}

	// 사진 업로드
	@RequestMapping("/m/photo_upload.go")
	public String photoUploadController(HttpServletRequest req, HttpServletResponse res, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = false;

		String FILE_PATH = "/files/temp/";
		String FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;
		String userId = "";
		String photo = "";
		String fileName = "";
		String photoPre = "";
		String type = "";
		int isThumb = 0;
		int addThumb = 0;
		String orgFileName = "";

		int fileSize = FILE_MAX_SIZE * 1024 * 1024;

		try {
			req.setCharacterEncoding("utf-8");

			File file = null;
			try {
				MultipartRequest multi = new MultipartRequest(req, FILE_LOCAL_PATH, fileSize, "UTF-8",
						new UniqFileRenamePolicy());

				// 폼에서 입력한 값을 가져옴
				userId = F.nullCheck(multi.getParameter("userId"), "");
				type = F.nullCheck(multi.getParameter("type"), "");
				isThumb = Integer.parseInt(F.nullCheck(multi.getParameter("isThumb"), "0"));
				addThumb = Integer.parseInt(F.nullCheck(multi.getParameter("addThumb"), "0"));
				orgFileName = F.nullCheck(multi.getParameter("fileName"), "");

				Enumeration files = multi.getFileNames();
				while (files.hasMoreElements()) {
					String elementName = (String) files.nextElement();
					file = multi.getFile(elementName);
					if (file != null) {
						photo = file.getName();
					}
				}

				if (type.equals("profile")) {
					fileName = userId + "." + photo.substring(photo.lastIndexOf(".") + 1, photo.length());
				} else {
					String yearMonth = T.getTodayYear() + T.getTodayMonth();

					fileName = photo;
					photoPre = yearMonth + "/";
				}
				if (isThumb == 1) {
					photoPre += "thumb/";
				}

				File copyFolder = new File(FILE_ROOT + "/files/" + type + "/" + photoPre);
				if (!copyFolder.exists()) {
					copyFolder.mkdirs();
				}

				// 파일 복사
				FileInputStream fis = new FileInputStream(FILE_LOCAL_PATH + photo);
				FileOutputStream fos = new FileOutputStream(FILE_ROOT + "/files/" + type + "/" + photoPre + fileName);
				int data = 0;
				while ((data = fis.read()) != -1) {
					fos.write(data);
				}
				fis.close();
				fos.close();

				// 필요시에 썸네일 저장
				if (addThumb == 1) {
					// 축소이미지 저장
					File newFile = new File(FILE_LOCAL_PATH + photo);
					File thumbFile = new File(FILE_ROOT + "/files/" + type + "/" + photoPre + "thumb/" + fileName);
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

				// 원본 이미지는 최소이미지도 함께 저장
				if (isThumb == 0) {
					// 축소이미지 저장
					File newFile = new File(FILE_LOCAL_PATH + photo);
					File thumbFile = new File(FILE_ROOT + "/files/" + type + "/" + photoPre + "tiny/" + fileName);
					if (!thumbFile.exists()) {
						thumbFile.mkdirs();
					}
					try {
						ImageUtil.resize(newFile, thumbFile, 50, 0);
						result = true;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				// 복사한뒤 원본파일을 삭제함
				file.delete();
			} catch (Exception e) {
				e.getMessage();
			}

			map.put("orgFileName", orgFileName);
			map.put("photo", fileName);
			map.put("path", "/files/" + type + "/" + photoPre);
			// map.put("thumbPath", "/files/"+ type +"/thumb/"+photoPre);
			map.put("result", true);
			map.put("message", "사진이 등록되었습니다.");

		} catch (Exception e) {
			map.put("result", false);
			map.put("message", "사진 등록에 실패하였습니다.\n" + e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);

		Gson gson = new Gson();
		String outputstr = gson.toJson(jsonObject);
		Response.responseWrite(res, outputstr);

		return null;
	}

	/* 프로필삭제 */

	@RequestMapping("/m/profile_photo_delete.go")
	public String prodeleteController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId, HttpServletRequest req,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			User user = userdao.getUsers(userId);
			map.put("result", true);
			map.put("message", "삭제되었습니다.");

			if (user != null) {
				// 파일삭제

				if (user.getFileName() != "") {
					
					
					filedelete(user.getFileName());
				}
				userdao.updateProfileImgDel(userId);

				map.put("message", "프로필 이미지가 삭제되었습니다.");
				map.put("result", true);
			} else {
				map.put("message", "존재하지 않는 ID 입니다.");
				map.put("result", false);
			}

		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	/**
	 * 로그인
	 * 
	 * @param loginId
	 * @param loginPw
	 * @param res
	 * @return
	 */
	@RequestMapping("/m/login.go")
	public String loginController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "password", required = false, defaultValue = "") String password,
			@RequestParam(value = "os_version", required = false, defaultValue = "") String osVersion,
			@RequestParam(value = "os_type", required = false, defaultValue = "") String osType,
			@RequestParam(value = "device_name", required = false, defaultValue = "") String deviceName,
			@RequestParam(value = "device_id", required = false, defaultValue = "") String deviceId,
			@RequestParam(value = "pushKey", required = false, defaultValue = "") String pushKey,
			@RequestParam(value = "loginNaver", required = false, defaultValue = "") int loginNaver,
			@RequestParam(value = "loginKakao", required = false, defaultValue = "") int loginKakao,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		String enPw = "";
		try {

			boolean userCheck = userdao.correctId(userId);
			if (userCheck == true) {

				boolean loginCheck = false;

				if (loginNaver == 1) {
					loginCheck = userdao.correctNaver(userId);
				} else if (loginKakao == 1) {
					loginCheck = userdao.correctKakao(userId);
				} else {
					// enPw = Sha256Util.encryptPassword(user.getPassword());
					loginCheck = userdao.correctPw(userId, password);
				}

				if (loginCheck == true) {
					
					User userdata = userdao.getUsers(userId);
					if(userdata.getStatus()!=4){
						
						UserBasic userBasic = ubasicdao.getUserBasic(userId);
						HttpSession session = req.getSession();
						session.setAttribute("USER_ID", userdata.getUserId());
						session.setAttribute("USER_NAME", userdata.getUserName());
	
						User uu = new User();
						uu.setUserId(userId);
						uu.setPassword(password);
						uu.setOsType(osType);
						uu.setOsVersion(osVersion);
						uu.setDeviceName(deviceName);
						uu.setDeviceId(deviceId);
						uu.setPushkey(pushKey);
	
						userdao.updateUserData(uu);
	
						String birth = userdata.getBirthday();
						int birthyear = Integer.parseInt(birth.substring(0, 4));
						int nowyear = Integer.parseInt(T.getTodayYear());
						int age = nowyear - birthyear;
	
						map.put("message", "로그인이 성공되었습니다.");
						map.put("result", true);
						map.put("userName", userdata.getUserName());
						map.put("userType", userdata.getUserType());
						map.put("photo", userdata.getFileName());
						map.put("userMed", userdata.getUserMed());
						map.put("age", age);
						map.put("height", userBasic.getHeight());
						map.put("weight", userBasic.getWeight());
						map.put("waist", userBasic.getWaist());
						map.put("smoke", userBasic.getSmoke());
						map.put("blood", userBasic.getBlood());
						map.put("press", userBasic.getPress());
						map.put("col", userBasic.getCol());
						map.put("bloodNum", userBasic.getBloodNum());
						map.put("pulse", userBasic.getPulse());
						map.put("splessure", userBasic.getSplessure());
						map.put("dplessure", userBasic.getDplessure());
						map.put("weightNum", userBasic.getWeightNum());
						map.put("bmi", userBasic.getBmi());
						map.put("gender", userdata.getGender());
						map.put("hdl", userBasic.getHdl());
						map.put("colNum", userBasic.getColNum());
						map.put("ldl", userBasic.getLdl());
						map.put("tg", userBasic.getTg());
						map.put("loginNaver", loginNaver);
						map.put("loginKakao", loginKakao);
						map.put("loginEimmed", 0);
						map.put("gender", userdata.getGender());
						map.put("hdl", userBasic.getHdl());
						map.put("colNum", userBasic.getColNum());
						map.put("ldl", userBasic.getLdl());
						map.put("tg", userBasic.getTg());
						map.put("heiwieght", userBasic.getHeiwieght());
						
					}else{
						map.put("message", "탈퇴한 사용자 입니다.");
						map.put("result", false);
					}

				} else {
					map.put("message", "비밀번호가 일치하지 않습니다.");
					map.put("result", false);
					map.put("userType", 3);
				}

			} else {
				map.put("message", "해당 아이디는 존재하지 않습니다.");
				map.put("result", false);
				map.put("userType", 3);
			}
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
			map.put("userType", 3);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	/**
	 * 아이디중복체크
	 * 
	 * @return
	 */

	@RequestMapping("/m/dup_check_id.go")
	public String checkIdController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			boolean userCheck = userdao.correctId(userId);
			if (userCheck == true) {

				map.put("message", "중복된 이메일 주소입니다 다른주소로 입력해주세요. " + "지속적인 문제 발생시 (관리자이메일)로 이메일을 보내주세요");
				map.put("result", true);

			} else {

				map.put("message", "사용가능한 이메일입니다.");
				map.put("result", false);
			}
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	/**
	 * 전화번호중복체크
	 * 
	 * @return
	 */

	@RequestMapping("/m/dup_check_phone.go")
	public String checkPhoneController(
			@RequestParam(value = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			boolean phoneCheck = userdao.correctPhone(phoneNumber);
			if (phoneCheck == true) {

				map.put("message", "사용중인 전화번호 입니다.");
				map.put("result", false);

			} else {

				map.put("message", "사용할 수 있는 전화번호입니다.");
				map.put("result", true);
			}
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	/**
	 * 아이디찾기
	 * 
	 * @return
	 */
	@RequestMapping("/m/myid.go")
	public String searchIdController(
			@RequestParam(value = "userName", required = false, defaultValue = "") String userName,
			@RequestParam(value = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			User user = userdao.findId(phoneNumber, userName);

			if (user != null) {
				String userId = user.getUserId();
				map.put("message", userId);
				map.put("result", true);

			} else {
				map.put("message", "회원님의 ID를 찾을 수 없습니다." + "아직 회원이 아니시면 아래 회원가입 버튼을 클릭해 주세요");
				map.put("result", false);

			}
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", "error : " + e.getMessage());

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	/**
	 * 비밀번호찾기
	 * 
	 * @return
	 */

	@RequestMapping("/m/mypass.go")
	public String searchPwController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		boolean userCheck = userdao.findPw(userId, phoneNumber);
		if (userCheck == false) {
			map.put("result", false);
			map.put("message", "회원님의 데이터를 찾을 수 없습니다." + "아직 회원이 아니시면 아래 회원가입 버튼을 클릭해 주세요");
		} else {
			try {

				map.put("result", true);
				map.put("message", "비밀번호를 재설정해주세요.");
			} catch (Exception e) {
				map.put("result", false);
				map.put("message", "error : " + e.getMessage());
			}
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	/*
	 * //건강매거진
	 * 
	 * @RequestMapping("/m/maga_list.go") public String magaListController(
	 * 
	 * @RequestParam(value="userId", required=false, defaultValue="") String
	 * userId, HttpServletRequest req, HttpServletResponse res ) {
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>();
	 * 
	 * User user =userdao.getUsers(userId); String joindate = user.getRegDate();
	 * 
	 * List<Magazine> list =magaginedao.getDoctorMagagineList();
	 * 
	 * map.put("result", true); map.put("joindate", joindate);//가입날짜
	 * map.put("list", list);
	 * 
	 * 
	 * JSONObject jsonObject = JSONObject.fromObject(map);
	 * Response.responseWrite(res, jsonObject); return null; }
	 */

	/*
	 * //매거진
	 * 
	 * @RequestMapping("/m/maga_health.go") public String magaHealthController(
	 * 
	 * @RequestParam(value="magaSeq", required=false, defaultValue="") int
	 * magaSeq,
	 * 
	 * @RequestParam(value="kind", required=false, defaultValue="") int kind,
	 * HttpServletRequest req, HttpServletResponse res ) {
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>();
	 * 
	 * if(kind == 0){ Magazine tdm =magaginedao.getTopMagagine(magaSeq); //상위
	 * 컨텐츠 List<Magazine> sublist =magaginedao.getSubMagagine(magaSeq); //하위 컨텐츠
	 * map.put("topmagazine", tdm); map.put("submagazine", sublist);
	 * 
	 * } else{ Magazine sdm =magaginedao.getTopMagagine(magaSeq);
	 * map.put("submagazine", sdm); }
	 * 
	 * JSONObject jsonObject = JSONObject.fromObject(map);
	 * Response.responseWrite(res, jsonObject); return null; }
	 */

	/*
	 * * 수행내역리스트 ////// 1개월 주기 지표
	 * 
	 * @return
	 **/

	/*
	 * @RequestMapping("/m/maga_report.go") public String
	 * magaReportController(@RequestParam(value = "userId", required = false,
	 * defaultValue = "") String id,
	 * 
	 * @RequestParam(value = "month", required = false, defaultValue = "")
	 * String ym, HttpServletRequest req, HttpServletResponse res) {
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>();
	 * 
	 * int bcnt = ublooddao.getUserBloodList(id, ym); // 혈당갯수 int pcnt =
	 * upressdao.getUserPressList(id, ym); // 혈압갯수 int wcnt =
	 * uweightdao.getUserWeightList(id, ym);// 체중측정갯수 UserCnt ucnt =
	 * ucntdao.getUserCntList(id, ym); // 해당년월에 목표데이터
	 * 
	 * map.put("blood", bcnt); map.put("pressure", pcnt); map.put("weight",
	 * wcnt); map.put("goaldata", ucnt); map.put("result", true);
	 * 
	 * JSONObject jsonObject = JSONObject.fromObject(map);
	 * Response.responseWrite(res, jsonObject); return null; }
	 */

	/* 뇌심혈관질환 위험도 */

	@RequestMapping("/m/cvrisk_list.go")
	public String cvriskController(@RequestParam(value = "userId", required = false, defaultValue = "") String id,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		List<UserCvrisk> list = ucvriskdao.getUserCvriskList(id);

		map.put("result", true);
		map.put("list", list);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	/* 복약관리 */

	@RequestMapping("/m/medi_list.go")
	public String mediListController(@RequestParam(value = "userId", required = false, defaultValue = "") String id,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, HttpServletRequest req,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		String today = T.getToday();
		int week = T.getWeekdayNum();

		List<UserMedi> list = umedidao.getUserMediList(id,page, ITEM_COUNT_PER_PAGE);
		// List<UserMedi> list =
		// umedidao.getUserMediList(id,page,ITEM_COUNT_PER_PAGE);
		map.put("result", true);
		map.put("list", list);
		map.put("cnt", umedidao.getUserMedicnt(id));

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	//복약관리 오늘것만.
	@RequestMapping("/m/medi_list2.go")
	public String mediList2Controller(
			@RequestParam(value = "userId", required = false, defaultValue = "") String id,
			HttpServletRequest req,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		String today = T.getToday();
		int week = T.getWeekdayNum();
		
		List<UserMedi> list = umedidao.getUserMediList(id,week,today);
		// List<UserMedi> list =
		// umedidao.getUserMediList(id,page,ITEM_COUNT_PER_PAGE);
		map.put("result", true);
		map.put("list", list);
	//	map.put("cnt", umedidao.getUserMedicnt(id,week));

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	/* 복약체크 */
	@RequestMapping("/m/medi_eat_check.go")
	public String mediEatCheckController(
			@RequestParam(value = "medSeq", required = false , defaultValue = "0") int medSeq, HttpServletRequest req,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			String today = T.getToday();
			int count = uMediLogDao.getUMediLogCount(medSeq, today);

			if (count == 0) {
				UserMediLog uml = new UserMediLog();
				uml.setMedSeq(medSeq);
				uMediLogDao.addUMediLog(uml);
				map.put("eatLog", 1);
				map.put("result", true);
				map.put("message", "약을 먹었습니다.");
			} else {
				uMediLogDao.deleteUMediLog(medSeq, today);
				map.put("eatLog", 0);
				map.put("result", true);
				map.put("message", "약을 먹지 않았습니다.");
			}
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	/* 복약등록 */

	@RequestMapping("/m/medi_edit_do.go")
	public String mediEditdoController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "mediname", required = false, defaultValue = "") String mediname,
			@RequestParam(value = "meditime", required = false, defaultValue = "") String meditime,
			@RequestParam(value = "medialert", required = false, defaultValue = "1") int medialert,
			@RequestParam(value = "medihospital", required = false, defaultValue = "") String medihospital,
			@RequestParam(value = "mediSeq", required = false, defaultValue = "0") int mediSeq,
			@RequestParam(value = "mediweek1", required = false, defaultValue = "0") int mediweek1,
			@RequestParam(value = "mediweek2", required = false, defaultValue = "0") int mediweek2,
			@RequestParam(value = "mediweek3", required = false, defaultValue = "0") int mediweek3,
			@RequestParam(value = "mediweek4", required = false, defaultValue = "0") int mediweek4,
			@RequestParam(value = "mediweek5", required = false, defaultValue = "0") int mediweek5,
			@RequestParam(value = "mediweek6", required = false, defaultValue = "0") int mediweek6,
			@RequestParam(value = "mediweek7", required = false, defaultValue = "0") int mediweek7,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		UserMedi um = new UserMedi();

		um.setMedialert(medialert);
		um.setMedihospital(medihospital);
		um.setMediname(mediname);
		um.setMeditime(meditime);
		um.setUserId(userId);
		um.setMediSeq(mediSeq);
		um.setMediweek1(mediweek1);
		um.setMediweek2(mediweek2);
		um.setMediweek3(mediweek3);
		um.setMediweek4(mediweek4);
		um.setMediweek5(mediweek5);
		um.setMediweek6(mediweek6);
		um.setMediweek7(mediweek7);

		try {
			if (mediSeq == 0) {// 신규등록

				umedidao.addUserMedi(um);
				map.put("message", "알림이 등록되었습니다.");

			} else { // 수정

				umedidao.updateUserMedi(um);
				if (medialert == 0) {
					map.put("message", "알림이 해제되었습니다.");
				} else {
					map.put("message", "알림이 설정되었습니다.");
				}

			}

			map.put("result", true);

		} catch (Exception e) {

			map.put("result", false);
			map.put("message", e.getMessage());

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	/* 복약삭제 */

	@RequestMapping("/m/medi_delete.go")
	public String medideleteController(

	@RequestParam(value = "medSeq", required = false, defaultValue = "") int medSeq, HttpServletRequest req,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			umedidao.deleteUserMedi(medSeq);
			map.put("result", true);
			map.put("message", "삭제되었습니다.");

		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	/* 처방전관리 */

	@RequestMapping("/m/pre_list.go")
	public String preController(@RequestParam(value = "userId", required = false, defaultValue = "") String id,
			@RequestParam(value = "page", required = false, defaultValue = "") int page, HttpServletRequest req,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		List<UserPres> list = upresdao.getUserPresList(id, page, ITEM_COUNT_PER_PAGE);

		map.put("list", list);
		map.put("result", true);
		map.put("cnt", upresdao.getUserPresCnt(id));
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	/* 처방전등록 */

	@RequestMapping("/m/pre_edit_do.go")
	public String preEditdoController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "hosName", required = false, defaultValue = "") String hosName,
			@RequestParam(value = "fileName", required = false, defaultValue = "") String fileName,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			@RequestParam(value = "preSeq", required = false, defaultValue = "0") int preSeq, HttpServletRequest req,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		UserPres up = new UserPres();
		up.setUserId(userId);
		up.setHosName(hosName);
		up.setFileName(fileName);
		up.setComment(comment);
		up.setPreSeq(preSeq);
		try {
			if (preSeq == 0) {// 신규등록

				upresdao.addUserPres(up);

			} else { // 수정

				upresdao.updateUserPres(up);

			}

			map.put("result", true);
			map.put("message", "완료되었습니다.");

		} catch (Exception e) {

			map.put("result", false);
			map.put("message", e.getMessage());

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	/* 처방전삭제 */

	@RequestMapping("/m/pre_delete.go")
	public String predeleteController(@RequestParam(value = "preSeq", required = false, defaultValue = "") int preSeq,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			map.put("result", true);
			map.put("message", "삭제되었습니다.");

			UserPres userpres = upresdao.UserPresOne(preSeq);

			if (userpres.getFileName() != "") {
				filedelete(userpres.getFileName());
				
			}

			upresdao.deleteUserPres(preSeq);

		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	/* 계정관리 */

	@RequestMapping("/m/myinfo.go")
	public String myinfoController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			User user = userdao.getUsers(userId);
			map.put("data", user);
			map.put("result", true);

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	/* 탈퇴 */

	@RequestMapping("/m/myinfo_drop_do.go")
	public String myinfoDropController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "password", required = false, defaultValue = "") String password,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		String enPw = "";

		try {

			// enPw = Sha256Util.encryptPassword(password);
			boolean loginCheck = userdao.correctPw(userId, password);

			if (loginCheck) {
				/*User uu = userdao.getUser(userId);
				List<UserPres> upList = upresdao.getUserPresList(userId);
				
				ubasicdao.deleteUserBasic(userId);
				ublooddao.deleteUserBlood(userId);
				ucntdao.deleteUserCnt(userId);
				ucvriskdao.deleteUserCvrisk(userId);
				umedidao.deleteUserMedi2(userId);
				upresdao.deleteUserPres2(userId);
				upressdao.deleteUserPress(userId);*/
				userdao.updatedrop(userId); ///4번 탈퇴자 
			/*	uweightdao.deleteUserWeight(userId);
				ugoaldao.deleteUserGoal(userId);
				ucoldao.deleteUserCol(userId);
				uhbdao.deleteUserhb(userId);
				//프사지우기
				if(uu.getFileName()!=null || uu.getFileName()!=""){
					filedelete(uu.getFileName());
					
				}
				//처방전 이미지 지우기
				for(int i=0;i<upList.size();i++){
					UserPres up = upList.get(i);
					if(up.getFileName()!=null || up.getFileName()!=""){
						filedelete(up.getFileName());
						
					}
				}*/
				
				map.put("message", "정상적으로 탈퇴되었습니다.");
				map.put("result", true);

			} else {
				map.put("message", "비밀번호가 일치하지않습니다");
				map.put("result", false);

			}

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 휴대전화변경
	@RequestMapping("/m/myinfo_phone_do.go")
	public String myphoneController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "password", required = false, defaultValue = "") String password,
			@RequestParam(value = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		String enPw = "";

		try {
			// enPw = Sha256Util.encryptPassword(password);
			boolean loginCheck = userdao.correctPw(userId, password);
			if (loginCheck) {

				userdao.editPhone(userId, phoneNumber);
				map.put("message", "변경되었습니다");
				map.put("result", true);

			} else {
				map.put("message", "비밀번호가 일치하지않습니다");
				map.put("result", false);

			}

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 비밀번호재설정
	@RequestMapping("/m/new_mypass.go")
	public String mynpassController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "password", required = false, defaultValue = "") String password,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		String enPw = "";

		try {
			// enPw = Sha256Util.encryptPassword(password);

			userdao.updatePw(userId, password);
			map.put("message", "변경되었습니다");
			map.put("result", true);

		} catch (Exception e) {
			map.put("message", "변경에 실패하였습니다.");
			map.put("result", false);

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 비밀번호변경
	@RequestMapping("/m/myinfo_pass_do.go")
	public String mypassController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "password", required = false, defaultValue = "") String password,
			@RequestParam(value = "npassword", required = false, defaultValue = "") String npassword,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		String enPw = "";

		try {
			// enPw = Sha256Util.encryptPassword(password);
			boolean loginCheck = userdao.correctPw(userId, password);
			if (loginCheck) {
				// enPw = Sha256Util.encryptPassword(npassword);
				userdao.updatePw(userId, npassword);
				map.put("message", "변경되었습니다");
				map.put("result", true);

			} else {
				map.put("message", "비밀번호가 일치하지않습니다");
				map.put("result", false);

			}

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 문진정보

	@RequestMapping("/m/myinfo_med.go")
	public String medController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,

	HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			UserBasic ub = ubasicdao.getUserBasic(userId);
			map.put("data", ub);
			map.put("result", true);

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}
	
	//월 컨텐츠 테스트 
	@RequestMapping("/m/test3.go")
	public String test3Controller(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "month");
		String today = T.getToday();
		List list = monthdao.getmonthlist(today.substring(0, 7));
		for (int i = 0; i < list.size(); i++) {
			Month mm = (Month) list.get(i);
			if (mm.getAnsType() == 1) {
				List<Month> answerList = monthdao.getmonthAnswerList(mm.getMonthSeq(), today.substring(0, 7));
				mm.setAnswerList(answerList);
				list.set(i, mm);
			}
	
		}
	
		Magazine mz = magaDao.getTopMagazine(today.substring(0, 7));
		Month mm = new Month();
		mm.setAnsType(5);
		mm.setComment(mz.getSubTitle());
		mm.setPrimarySeq(100);
		mm.setMonthSeq(100);
		mm.setUrl(SERVER_DOMAIN+"/m/maga_view.go?mSeq=" + Integer.toString(mz.getmSeq()));
		mm.setThumFile("/files/magazine/"+mz.getThumFile());
		mm.setIsLast(1);
		list.add(mm);
		map.put("result", true);
		map.put("list", list);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
		
	}
	
	/*//// 테스트 월레포트
	@RequestMapping("/m/test2.go")
	public String test2Controller(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletRequest req, HttpServletResponse res) {
		
		Calendar date = Calendar.getInstance();
		UserCnt uc = new UserCnt();
		uc = ucntdao.getUserCntList(userId); // 목표갯수
		User user = userdao.getUsers(userId);
		String userName = user.getUserName();
		String regday = user.getRegDate().substring(0, 10);
		String regdate = regday.substring(8, 10);
		String todaymonet = T.getMonth();
		String nextregday = T.getDateAdd(regday, 1);
		String maga = T.getDateAdd(todaymonet + "-" + regdate, -10);
		String repo = T.getDateAdd(todaymonet + "-" + regdate, -1);
		int birthWeek = T.getWeekDay(regday);
		int todayWeek = T.getWeekDay(T.getToday());

		int regyo = T.daytype(regday);
		int todayyo = T.daytype(T.getToday());
		int nextregyo = T.daytype(nextregday);
		String today = T.getToday(); // "2015-10-12"
		String before = T.getDateAdd(today, -6);

		// int presscnt = upressdao.getUserPresscnt(userId, today, before); //
		// 혈압갯수
		before = T.getDateAdd(today, -2);
		// int bloodcnt = ublooddao.getUserBloodcnt(userId, today, before);
		before = T.getDateAdd(today, -30);
		// int weightcnt = uweightdao.getUserWeightcnt(userId, today, before);
		int num = T.getDateminus(today, (user.getRegDate().substring(0, 10)));

		List list = new ArrayList();
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		List<Report> monthreportlist = reportdao.getReportList(2);

		before = T.getBeforeYearMonthByYM(1);
		int presscnt = upressdao.getmonthCount(userId, before); // 혈압갯수
		int bloodcnt = ublooddao.getmonthCount(userId, before);// 혈당갯수
		int weightcnt = uweightdao.getmonthCount(userId, before);// 체중갯수

		int ptype = wkpointer.type(presscnt, uc.getPcnt());
		int btype = wkpointer.type(bloodcnt, uc.getBcnt());
		int wtype = wkpointer.type(weightcnt, uc.getWcnt());

		List lastList = new ArrayList();

		String pstr = (anlisysdao.getAnalisys(ptype)).getComment();
		String bstr = (anlisysdao.getAnalisys(btype)).getComment();
		String wstr = (anlisysdao.getAnalisys(wtype)).getComment();
		String setstr = "";
		String setstr2 = "";
		List commentList = new ArrayList();

		if (ptype == btype && btype == wtype) { // 셋다 같을때
			setstr = "모두 " + pstr;
			commentList.add(setstr);

		} else if (ptype <= btype && ptype <= wtype) { // 혈압이 제일 우선순위
			if (ptype == btype) {// 같으면
				setstr = "혈압,혈당 횟수는" + pstr;
				commentList.add(setstr);
			} else if (ptype == wtype) {
				setstr = "혈압,체중 횟수는" + pstr;
				commentList.add(setstr);
			} else {
				setstr = "혈압은 " + pstr;
				commentList.add(setstr);
				if (btype == wtype) {
					setstr = "혈당,체중 횟수는" + bstr;
					commentList.add(setstr);
				} else if (btype < wtype) {
					setstr = "혈당은 " + bstr;
					commentList.add(bstr);
					setstr = "체중은 " + wstr;
					commentList.add(wstr);
				} else {
					setstr = "체중은 " + wstr;
					commentList.add(wstr);
					setstr = "혈당은 " + bstr;
					commentList.add(bstr);
				}
			}
		} else if (btype <= ptype && btype <= wtype) {// 혈당이 우선순위
			if (btype == ptype) {// 같으면
				setstr = "혈압,혈당 횟수" + bstr;
			} else if (btype == wtype) {
				setstr = "혈당,체중 횟수" + bstr;
			} else {
				setstr = "혈당은 " + bstr;
				commentList.add(setstr);
				if (wtype == ptype) {
					setstr = "혈압,체중 횟수" + pstr;
					commentList.add(setstr);
				} else if (ptype < wtype) {
					setstr = "혈압은 " + pstr;
					commentList.add(pstr);
					setstr = "체중은 " + wstr;
					commentList.add(wstr);
				} else {
					setstr = "체중은 " + wstr;
					commentList.add(wstr);
					setstr = "혈압은 " + pstr;
					commentList.add(pstr);
				}
			}
		} else if (wtype <= ptype && wtype <= btype) {// 체중이 우선순위
			if (wtype == ptype) {// 같으면
				setstr = "혈압,체중 횟수" + wstr;
			} else if (wtype == btype) {
				setstr = "혈당,체중 횟수" + wstr;
			} else {
				setstr = "체중은 " + wstr;
				commentList.add(setstr);
				if (ptype == btype) {
					setstr = "혈압,혈당 횟수" + pstr;
					commentList.add(setstr);
				} else if (ptype < btype) {
					setstr = "혈압은 " + pstr;
					commentList.add(pstr);
					setstr = "혈당은 " + bstr;
					commentList.add(bstr);
				} else {
					setstr = "혈당은 " + bstr;
					commentList.add(bstr);
					setstr = "혈압은 " + pstr;
					commentList.add(pstr);
				}
			}
		}

		for (int i = 0; i < monthreportlist.size(); i++) {
			Report rp = monthreportlist.get(i);
			String comment = rp.getComment();
			rp.setComment(comment.replace("(사용자명)", userName));

			if (comment.equals("setcnt")) {// 처음차트
				int lastday = T.getLastMonthday(before);
				comment = Integer.toString(uc.getBcnt()) + "," + Double.toString(uc.getPcnt()) + ","
						+ Integer.toString(uc.getWcnt()) + "," + Integer.toString(bloodcnt) + ","
						+ Integer.toString(presscnt) + "," + Integer.toString(weightcnt)+","+
						before+"-1 ~ "+before+"-"+lastday;
				rp.setComment(comment);

			}
			if (rp.getAnsType() == 1) {
				List<Report> answerList = reportdao.getReportanswerList(2, rp.getReportSeq());
				rp.setAnswerList(answerList);

			}
			if (comment.equals("comment1")) {

				String com = (String) commentList.get(0);
				if (commentList.size() == 1) {// 모두같을때
					rp.setIsLast(1);// 마지막값주고
					rp.setMove(0);// 무브 0
				}
				rp.setComment(com);

			} else if (comment.equals("comment2")) {
				if (commentList.size() > 1) {
					String com = (String) commentList.get(1);
					if (commentList.size() == 2) {// 모두같을때
						rp.setIsLast(1);// 마지막값주고
						rp.setMove(0);// 무브 0

					}
					rp.setComment(com);
				}

			} else if (comment.equals("comment3")) {
				if (commentList.size() > 2) {
					String com = (String) commentList.get(2);

					rp.setComment(com);
				}

			}
			list.add(rp);

		}
		map.put("type","monthreport");
		map.put("list", list);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;

	}*/

	//////////////////////////// 테스트용 주레포트

	@RequestMapping("/m/test.go")
	public String testController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletRequest req, HttpServletResponse res) {

		User user = userdao.getUsers(userId);
		String userName = user.getUserName();
		String regday = user.getRegDate().substring(0, 10);
		String regdate = regday.substring(8, 10);
		String todaymonet = T.getMonth();
		String nextregday = T.getDateAdd(regday, 1);
		String maga = T.getDateAdd(todaymonet + "-" + regdate, -10);
		String repo = T.getDateAdd(todaymonet + "-" + regdate, -1);
		int birthWeek = T.getWeekDay(regday);
		int todayWeek = T.getWeekDay(T.getToday());

		int regyo = T.daytype(regday);
		int todayyo = T.daytype(T.getToday());
		int nextregyo = T.daytype(nextregday);
		String today = T.getToday(); // "2015-10-12"
		String before = T.getDateAdd(today, -6);

		int presscnt = upressdao.getUserPresscnt(userId, today, before); // 혈압갯수
		before = T.getDateAdd(today, -2);
		int bloodcnt = ublooddao.getUserBloodcnt(userId, today, before);
		before = T.getDateAdd(today, -30);
		int weightcnt = uweightdao.getUserWeightcnt(userId, today, before);
		int num = T.getDateminus(today, (user.getRegDate().substring(0, 10)));

		List list = new ArrayList();
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		map.put("type", "weekreport");
		int weekbloodcnt = ublooddao.getUserBloodcnt(userId, today, before);
		int weekpresscnt = upressdao.getUserPresscnt(userId, today, before);
		int weekweightcnt = uweightdao.getUserWeightcnt(userId, today, before);

		if (weekbloodcnt > 0 || weekpresscnt > 0 || weekweightcnt > 0) {// 한개라도
																		// 데이터가잇음
			int code = 0;
			int code2 = 0;
			String commentType = "";
			String maxtype = "";// 제일 높은값 담을 변수
			List<Report> breportlist = reportdao.getReportList(1, "blood");// 질문을
																			// 가져온다
			List<Report> preportlist = reportdao.getReportList(1, "press");// 질문을
																			// 가져온다
			List<Report> wreportlist = reportdao.getReportList(1, "weight");// 질문을
																			// 가져온다
			List<Report> lastlist = reportdao.lastReportList();
			int gong = ublooddao.getUserBloodcnt(userId, today, before, 1); // 공복
																			// 측정횟수
			int sik = ublooddao.getUserBloodcnt(userId, today, before, 2);// 식후
																			// 측정횟수
			int sleep = ublooddao.getUserBloodcnt(userId, today, before, 3);// 취침전
																			// 측정횟수
			UserGoal ug = ugoaldao.getuserGoal(userId);
			UserCnt uc = ucntdao.getUserCntList(userId);

			if (gong >= sik && gong >= sleep) {// 공복이 제일큼
				maxtype = "공복혈당";

			} else if (sik >= gong && sik >= sleep) {// 식후가 제일큼
				maxtype = "식후혈당";

			} else if (sleep >= gong && sleep >= sik) {// 취침전혈당이 제일큼
				maxtype = "취침전혈당";
			}
			for (int i = 0; i < 2; i++) {
				Report rp = breportlist.get(i);
				String comment = rp.getComment(); // 코멘트를 담아서
				String cmt = comment.replace("(사용자명)", userName);
				rp.setComment(cmt);
				if (comment.equals("bloodfirst")) {// 처음차트
					comment = Integer.toString(weekbloodcnt) + "," + Integer.toString(weekpresscnt) + ","
							+ Integer.toString(weekweightcnt) + "," + Integer.toString(uc.getBcnt()) + ","
							+ Double.toString(uc.getPcnt()) + "," + Integer.toString(uc.getWcnt());
					rp.setComment(comment);

				}
				list.add(rp);
			}
			if (weekbloodcnt > 0) {
				for (int i = 2; i < breportlist.size(); i++) {
					Report rp = breportlist.get(i);
					String comment = rp.getComment(); // 코멘트를 담아서
					String cmt = comment.replace("(가장 높은 구분값명)", " " + maxtype);
					rp.setComment(cmt);
					if (rp.getAnsType() == 1) {
						List answerList = reportdao.getReportanswerList(1, rp.getReportSeq());
						rp.setAnswerList(answerList);

					} else if (comment.equals("blooduser")) {// 두번째 차트

						comment = Integer.toString(gong) + "," + Integer.toString(sik) + "," + Integer.toString(sleep);
						rp.setComment(comment);

					} else if (comment.equals("blooddetail")) {// 마지막차트

						if (maxtype.equals("공복혈당")) {

							int gongavg = ublooddao.getUserBloodavg(userId, today, before, 1);// 내평균
							int ogongavg = ublooddao.getotherBloodavg(today, before, 1);// 타인평균
							comment = Integer.toString(ug.getGoalbMblood()) + "," + // 목표
									Integer.toString(gongavg) + "," + // 내평균
									Integer.toString(ogongavg);// 타인평균
							rp.setComment(comment);
							code = wkpointer.code1(gongavg, ogongavg);
							code2 = wkpointer.code2(ug.getGoalbMblood(), gongavg);

						} else if (maxtype.equals("식후혈당")) {

							int sikavg = ublooddao.getUserBloodavg(userId, today, before, 2);// 내평균
							int osikavg = ublooddao.getotherBloodavg(today, before, 2);// 타인평균
							comment = Integer.toString(ug.getGoalEblood()) + "," + // 목표
									Integer.toString(sikavg) + "," + // 내평균
									Integer.toString(osikavg);// 타인평균
							rp.setComment(comment);
							code = wkpointer.code1(sikavg, osikavg);
							code2 = wkpointer.code2(ug.getGoalEblood(), sikavg);

						} else if (maxtype.equals("취침전혈당")) {

							int sleepavg = ublooddao.getUserBloodavg(userId, today, before, 3);// 내평균
							int osleepavg = ublooddao.getotherBloodavg(today, before, 3);// 타인평균
							comment = "goal:" + Integer.toString(ug.getGoalSblood()) + "," // 목표
									+ "myavg:" + Integer.toString(sleepavg) + "," // 내평균
									+ "otheravg:" + Integer.toString(osleepavg);// 타인평균
							rp.setComment(comment);
							code = wkpointer.code1(sleepavg, osleepavg);
							code2 = wkpointer.code2(ug.getGoalSblood(), sleepavg);
						}
					}
					if (comment.equals("bloodcomment")) {// 처음분석 코멘트
						Analisys an = anlisysdao.getAnalisys(code);
						commentType = "혈당의 " + an.getComment();
						rp.setComment(commentType);

					} else if (comment.equals("bloodcomment2")) {// 두번째분석
																	// 코멘트
						Analisys an = anlisysdao.getAnalisys(code2);
						String comment2 = an.getComment();
						String commentType2 = "";
						if ((commentType.contains("높") && comment2.contains("높"))
								|| (commentType.contains("비슷한") && comment2.contains("안정적"))
								|| (commentType.contains("낮") && comment2.contains("낮"))) {// 둘다
																							// 같으면
							commentType2 = "수치도";
						} else {
							commentType2 = "수치가";
						}
						rp.setComment(commentType2 + " " + an.getComment());

					}
					rp.setIsLast(0);
					list.add(rp);

					if (weekpresscnt == 0 && weekweightcnt == 0 && rp.getPrimarySeq() == 9) { // 더이상
																								// 측정이없으면
						if (rp.getPrimarySeq() == 9) {
							rp.setMove(20);
							rp.setAnsType(3);
						}
						for (int j = 0; j < lastlist.size(); j++) {

							Report lastrp = lastlist.get(j);

							lastrp.setComment(lastrp.getComment().replace("(파이팅스티커)", SERVER_DOMAIN+"/images/sticker_fighting.png"));
							list.add(lastrp);
						}

					}

				} // 리스트 for문 닫기
			} // 혈당 측정 있으면
			if (weekpresscnt > 0) {// 혈압
				for (int i = 0; i < preportlist.size(); i++) {
					Report rp = preportlist.get(i);
					String comment = rp.getComment(); // 코멘트를 담아서

					if (rp.getAnsType() == 1) {
						List answerList = reportdao.getReportanswerList(1, rp.getReportSeq());
						rp.setAnswerList(answerList);

					}
					if (comment.equals("pressfirst")) {// 처음차트
						int mydavg = upressdao.getUserPressavg(userId, today, before, "dplessure");// 이완
						int mysavg = upressdao.getUserPressavg(userId, today, before, "splessure");// 수축
						int odavg = upressdao.getotherPressavg(today, before, "dplessure");
						int osavg = upressdao.getotherPressavg(today, before, "splessure");

						comment = Integer.toString(mydavg) + "," + Integer.toString(mysavg) + ","
								+ Integer.toString(odavg) + "," + Integer.toString(osavg) + "," + ug.getGoalbPre() + ","
								+ ug.getGoalsPre();
						rp.setComment(comment);
						code = wkpointer.code1(mysavg, osavg);
						code2 = wkpointer.code2(ug.getGoalbPre(), mysavg);

					}
					if (comment.equals("presscomment")) {// 처음분석 코멘트
						Analisys an = anlisysdao.getAnalisys(code);
						commentType = "혈압의 " + an.getComment();
						rp.setComment(commentType);

					} else if (comment.equals("presscomment2")) {// 두번째분석
																	// 코멘트
						Analisys an = anlisysdao.getAnalisys(code2);
						String comment2 = an.getComment();
						String commentType2 = "";
						if ((commentType.contains("높") && comment2.contains("높"))
								|| (commentType.contains("비슷한") && comment2.contains("안정적"))
								|| (commentType.contains("낮") && comment2.contains("낮"))) {// 둘다
																							// 같으면
							commentType2 = "수치도";
						} else {
							commentType2 = "수치가";
						}
						rp.setComment(commentType2 + " " + an.getComment());

					}
					rp.setIsLast(0);
					list.add(rp);

					if (weekweightcnt == 0 && rp.getPrimarySeq() == 14) { // 더이상
																			// 측정이없으면
						if (rp.getPrimarySeq() == 14) {
							rp.setMove(20);
							rp.setAnsType(3);

						}
						for (int j = 0; j < lastlist.size(); j++) {
							Report lastrp = lastlist.get(j);

							lastrp.setComment(lastrp.getComment().replace("(파이팅스티커)", SERVER_DOMAIN+"/images/sticker_fighting.png"));

							list.add(lastrp);
						}

					}

				} // 혈압 for문 닫고

			} // 혈압 끝

			if (weekweightcnt > 0) {// 체중
				for (int i = 0; i < wreportlist.size(); i++) {
					Report rp = wreportlist.get(i);
					String comment = rp.getComment(); // 코멘트를 담아서

					if (rp.getAnsType() == 1) {
						List answerList = reportdao.getReportanswerList(1, rp.getReportSeq());
						rp.setAnswerList(answerList);

					}
					if (comment.equals("bmifirst")) {// 처음차트
						double mybmiavg = uweightdao.getUserWeighavg(userId, today, before); // 평균
						double obmiavg = uweightdao.getotherWeighavg(today, before); // 평균//타인

						code = code3(mybmiavg, obmiavg);
						code2 = code4(ug.getGoalbBmi(), mybmiavg);

						comment = Double.toString(mybmiavg) + "," + Double.toString(obmiavg) + ","
								+ Double.toString(ug.getGoalbBmi());
						rp.setComment(comment);

					}

					if (comment.equals("bmicomment")) {// 처음분석 코멘트
						Analisys an = anlisysdao.getAnalisys(code2);
						commentType = "수치가 " + an.getComment();
						rp.setComment(commentType);

					}
					list.add(rp);

				} // 체중 for문 닫고

				for (int j = 0; j < lastlist.size(); j++) {
					Report lastrp = lastlist.get(j);

					lastrp.setComment(lastrp.getComment().replace("(파이팅스티커)",
							SERVER_DOMAIN+"/images/sticker_fighting.png"));

					list.add(lastrp);
				}

			}
		}
		map.put("list", list);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}
	
	//공지사항 진입 시점 
	@RequestMapping("/m/service.go")
	public String noticeController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			User uu = userdao.getUsers(userId);
			
			map.put("cnt", uu.getNoticeBadge());//내 뱃지갯수
			
			map.put("result", true);

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 공지사항

	@RequestMapping("/m/service_list.go")
	public String noticeController(
			@RequestParam(value = "page", required = false, defaultValue = "") int page,
		
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {
			
			List<Notice> list = noticedao.getNoticeMainList(page, ITEM_COUNT_PER_PAGE);
			int cnt = noticedao.getNoticeMainCount();
		
			map.put("data", list);
			map.put("cnt", cnt);
			map.put("result", true);

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 공지사항상세

	@RequestMapping("/m/service_detail.go")
	public String noticeDController(
			@RequestParam(value = "noticeSeq", required = false, defaultValue = "") int noticeSeq,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		try {

			Notice notice = noticedao.getNotice(noticeSeq);
			map.put("data", notice);
			map.put("result", true);

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 문진

	@RequestMapping("/m/med_exam.go")
	public String medExamController(@RequestParam(value = "kind", required = false, defaultValue = "") int kind,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		List<MedExam> list = medexamdao.getMedExamList(kind);
		for (int i = 0; i < list.size(); i++) {
			MedExam me = (MedExam) list.get(i);
			if (me.getAnsType() == 1 || me.getAnsType() == 4) {
				List<MedExam> answerList = medexamdao.getMedExamAnswerList(me.getMedSeq(), kind);
				me.setAnswerList(answerList);
				list.set(i, me);
			}
		}
		map.put("list", list);
		map.put("result", true);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 문진등록

	@RequestMapping("/m/med_exam_do.go")
	public String medExamdoController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "height", required = false, defaultValue = "0") int height,
			@RequestParam(value = "weight", required = false, defaultValue = "0") int weight,
			@RequestParam(value = "waist", required = false, defaultValue = "0") int waist,
			@RequestParam(value = "smoke", required = false, defaultValue = "0") int smoke,
			@RequestParam(value = "blood", required = false, defaultValue = "") String blood,
			@RequestParam(value = "press", required = false, defaultValue = "") String press,
			@RequestParam(value = "col", required = false, defaultValue = "") String col,
			@RequestParam(value = "heiwieght", required = false, defaultValue = "") String heiwieght,
			@RequestParam(value = "bmi", required = false, defaultValue = "") double bmi, HttpServletRequest req,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		int count = ubasicdao.getCount(userId);
		int gender = (userdao.getUser(userId)).getGender();

		if (count == 0) {
			UserBasic ub = new UserBasic();
			ub.setUserId(userId);
			ub.setGender(gender);
			ub.setHeight(height);
			ub.setWeight(weight);
			ub.setWaist(waist);
			ub.setSmoke(smoke);
			ub.setBlood(blood);
			ub.setPress(press);
			ub.setCol(col);
			ub.setHeiwieght(heiwieght);
			ubasicdao.addUserBasic(ub);
			UserWeight uw = new UserWeight();
			uw.setBmi(bmi);
			uw.setUserId(userId);
			uw.setWeightNum(weight);
			uweightdao.addUserWeight(uw);

			map.put("result", true);
			map.put("msg", "등록완료");
		} else {
			

			UserBasic ub = ubasicdao.getUserBasic(userId);
			ub.setGender(gender);
			ub.setHeight(height);
			ub.setWeight(weight);
			ub.setWaist(waist);
			ub.setSmoke(smoke);
			ub.setBlood(blood);
			ub.setPress(press);
			ub.setCol(col);
			ub.setHeiwieght(heiwieght);
			ubasicdao.updateUserBasic1(ub);
			UserWeight uw = new UserWeight();
			uw.setBmi(bmi);
			uw.setUserId(userId);
			uw.setWeightNum(weight);
			uweightdao.updateUWeight(uw);
			
			
			ManageIndex mi = mindexdao.getManageIndexTop();

			UserGoal goal = ugoaldao.getuserGoal(userId);
			if (goal.getGoalSeq() == 0) {
				if (blood.equals("blood")) {
					goal.setGoalsMblood(mi.getGoalSmblood());
					goal.setGoalEblood(mi.getGoalEblood()); 
					goal.setGoalSblood(mi.getGoalSblood()); 
					goal.setGoalHba(mi.getGoalHba()); 
				}
				if (press.equals("press")) {
					goal.setGoalsPre(mi.getGoalSpre()); 
					goal.setGoalbPre(mi.getGoalBpre()); 
				}
				//goal.setGoalPul(mi.getGoalPul()); 
				if (col.equals("col")) {
					goal.setGoalCol(mi.getGoalCol()); 
					goal.setGoalLdl(mi.getGoalLdl()); 
					goal.setGoalHdl(mi.getGoalHdl()); 
					goal.setGoalTg(mi.getGoalTg()); 
				}
				if (heiwieght.equals("heiwieght")) {
					goal.setGoalsBmi(mi.getGoalSbmi()); 
					goal.setGoalbBmi(mi.getGoalBbmi()); 
				}
				ugoaldao.addUserGoal(goal);
				
				
			} else {
				if (blood.equals("blood")) {
					goal.setGoalsMblood(mi.getGoalSmblood());
					goal.setGoalEblood(mi.getGoalEblood()); 
					goal.setGoalSblood(mi.getGoalSblood()); 
					goal.setGoalHba(mi.getGoalHba()); 
				}
				if (press.equals("press")) {
					goal.setGoalsPre(mi.getGoalSpre()); 
					goal.setGoalbPre(mi.getGoalBpre()); 
				}
				//goal.setGoalPul(mi.getGoalPul()); 
				if (col.equals("col")) {
					goal.setGoalCol(mi.getGoalCol()); 
					goal.setGoalLdl(mi.getGoalLdl()); 
					goal.setGoalHdl(mi.getGoalHdl()); 
					goal.setGoalTg(mi.getGoalTg()); 
				}
				if (heiwieght.equals("heiwieght")) {
					goal.setGoalsBmi(mi.getGoalSbmi()); 
					goal.setGoalbBmi(mi.getGoalBbmi()); 
				}
				ugoaldao.updateUserGoal(goal);
			}
			
			

			
			map.put("result", true);
			map.put("msg", "수정완료");
		}
		userdao.updateUser(userId);// 문진등록완료
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 관리목표 안내로 관리자가 등록한 값을 넘겨준다.

	@RequestMapping("/m/management.go")
	public String managementController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "blood", required = false, defaultValue = "") String blood,
			@RequestParam(value = "press", required = false, defaultValue = "") String press,
			@RequestParam(value = "col", required = false, defaultValue = "") String col,
			@RequestParam(value = "weight", required = false, defaultValue = "") String weight, HttpServletRequest req,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<UserGoal> list = new ArrayList();
		UserGoal usergoal = new UserGoal();
		usergoal.setUserId(userId);
		ManageIndex index = mindexdao.getManageIndex();
		if (blood != "") {

			map.put("blood", index);

		}
		if (press != "") {
		
			map.put("press", index);

		}
		if (col != "") {
		
			map.put("col", index);

		
		}
		if (weight != "") {
		
			map.put("heiwieght", index);


		}
	
		map.put("result", true);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	/*
	 * //관리목표설정
	 * 
	 * @RequestMapping("/m/management_do.go") public String medExamdoController(
	 * 
	 * @RequestParam(value="userId", required=false, defaultValue="") String
	 * userId,
	 * 
	 * @RequestParam(value="blood", required=false, defaultValue="") String
	 * blood,
	 * 
	 * @RequestParam(value="press", required=false, defaultValue="") String
	 * press,
	 * 
	 * @RequestParam(value="col", required=false, defaultValue="") String col,
	 * HttpServletRequest req, HttpServletResponse res ) {
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>(); boolean result =
	 * true; String message = "";
	 * 
	 * 
	 * if(blood!=""){//혈당
	 * 
	 * 
	 * 
	 * }
	 * 
	 * if(press!=""){//혈압
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * if(col!=""){//콜레스테롤 고지혈증
	 * 
	 * 
	 * 
	 * }
	 * 
	 * map.put("result", true); map.put("msg","등록완료"); map.put("data",
	 * ugoaldao.getuserGoal(userId));
	 * 
	 * 
	 * JSONObject jsonObject = JSONObject.fromObject(map);
	 * Response.responseWrite(res, jsonObject);
	 * 
	 * return null; }
	 */

	// 지표관리설정(질문 답)

	@RequestMapping("/m/pointer_do.go")
	public String pointerController(

	@RequestParam(value = "diseaseId", required = false, defaultValue = "") String diseaseId,

	HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		List<Pointer> list = pointerdao.getPointer(diseaseId);
		for (int i = 0; i < list.size(); i++) {
			Pointer pointer = (Pointer) list.get(i);
			if (pointer.getAnsType() == 1) {
				List<Pointer> answerList = pointerdao.getPointerAnswerList(pointer.getComSeq());
				pointer.setAnswerList(answerList);
				list.set(i, pointer);
			}
		}
		map.put("data", list);

		/*
		 * //혈압리스트 List <Pointer>list2 = pointerdao.getPointer(press); for (int
		 * i=0; i<list2.size(); i++) { Pointer pointer = (Pointer)list2.get(i);
		 * if (pointer.getAnsType() == 1) { List <Pointer> answerList =
		 * pointerdao.getPointerAnswerList(pointer.getComSeq());
		 * pointer.setAnswerList(answerList); list2.set(i, pointer); } }
		 * map.put("press", list2);
		 * 
		 * //당화혈색소추가 (당뇨병환자만)
		 * 
		 * if(blood!=""){
		 * 
		 * List <Pointer>list3 = pointerdao.getPointer("hba"); for (int i=0;
		 * i<list3.size(); i++) { Pointer pointer = (Pointer)list3.get(i); if
		 * (pointer.getAnsType() == 1) { List <Pointer> answerList =
		 * pointerdao.getPointerAnswerList(pointer.getComSeq());
		 * pointer.setAnswerList(answerList); list3.set(i, pointer); } }
		 * map.put("hba", list3);
		 * 
		 * }
		 * 
		 * //체중리스트
		 * 
		 * List <Pointer>list4 = pointerdao.getPointer("weight"); for (int i=0;
		 * i<list4.size(); i++) { Pointer pointer = (Pointer)list4.get(i); if
		 * (pointer.getAnsType() == 1) { List <Pointer> answerList =
		 * pointerdao.getPointerAnswerList(pointer.getComSeq());
		 * pointer.setAnswerList(answerList); list4.set(i, pointer); } }
		 * map.put("weight", list4);
		 * 
		 * 
		 * 
		 * 
		 * if(col!=""){//콜레스테롤 고지혈증 (해당 환자만 측정)
		 * 
		 * List <Pointer>list3 = pointerdao.getPointer(col); for (int i=0;
		 * i<list3.size(); i++) { Pointer pointer = (Pointer)list3.get(i); if
		 * (pointer.getAnsType() == 1) { List <Pointer> answerList =
		 * pointerdao.getPointerAnswerList(pointer.getComSeq());
		 * pointer.setAnswerList(answerList); list3.set(i, pointer); } }
		 * map.put("col", list3);
		 * 
		 * }
		 */

		map.put("result", true);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 혈당등록
	@RequestMapping("/m/pointer_bloodinsert.go")
	public String pbloodController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "bloodTime", required = false, defaultValue = "0") int bloodTime,
			@RequestParam(value = "bloodKind", required = false, defaultValue = "0") int bloodKind,
			@RequestParam(value = "bloodNum", required = false, defaultValue = "0") int bloodNum,
			@RequestParam(value = "regDate", required = false, defaultValue = "") String regDate,
			@RequestParam(value = "equip", required = false, defaultValue = "0") int equip, // 기기사용여부
																							// 0
																							// 사용안함
																							// 1사용
			HttpServletRequest req, HttpServletResponse res) {

		String today = T.getToday();

		Map<String, Object> map = new HashMap<String, Object>();

		UserBlood ub = new UserBlood();
		ub.setUserId(userId);
		ub.setBloodTime(bloodTime);
		ub.setBloodKind(bloodKind);
		ub.setBloodNum(bloodNum);
		ub.setEquip(equip);
		if (regDate.equals("")) {
			ublooddao.addUserBlood(ub);
		} else {
			ub.setRegDate(regDate);
			ublooddao.addUserBloodWithDate(ub);
		}

		map.put("result", true);
		map.put("msg", "등록완료");

		/**
		 * 당일 데이터 덮어쓰기 int count = ublooddao.getCount(userId, today, bloodKind);
		 * if (count == 0) { UserBlood ub = new UserBlood();
		 * ub.setUserId(userId); ub.setBloodTime(bloodTime);
		 * ub.setBloodKind(bloodKind); ub.setBloodNum(bloodNum);
		 * ublooddao.addUserBlood(ub);
		 * 
		 * map.put("result", true); map.put("msg","등록완료"); } else { UserBlood ub
		 * = ublooddao.getUserBlood(userId, today, bloodKind);
		 * ub.setBloodTime(bloodTime); ub.setBloodKind(bloodKind);
		 * ub.setBloodNum(bloodNum); ublooddao.updateUserBlood(ub);
		 * 
		 * map.put("result", true); map.put("msg","수정완료"); }
		 */

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 혈압등록
	@RequestMapping("/m/pointer_pressinsert.go")
	public String ppressController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "pulse", required = false, defaultValue = "0") int pulse,
			@RequestParam(value = "splessure", required = false, defaultValue = "0") int splessure,
			@RequestParam(value = "dplessure", required = false, defaultValue = "0") int dplessure,
			@RequestParam(value = "equip", required = false, defaultValue = "0") int equip, // 기기사용여부
																							// 0
																							// 사용안함
																							// 1사용
			HttpServletRequest req, HttpServletResponse res) {

		String today = T.getToday();

		Map<String, Object> map = new HashMap<String, Object>();

		UserPress up = new UserPress();
		up.setUserId(userId);
		up.setPulse(pulse);
		up.setSplessure(splessure);
		up.setDplessure(dplessure);
		up.setEquip(equip);
		upressdao.addUserPress(up);
		map.put("result", true);
		map.put("msg", "등록완료");

		/**
		 * 당일 데이터 덮어쓰기 int count = upressdao.getCount(userId, today); if (count
		 * == 0) { UserPress up = new UserPress(); up.setUserId(userId);
		 * up.setPulse(pulse); up.setSplessure(splessure);
		 * up.setDplessure(dplessure); upressdao.addUserPress(up);
		 * map.put("result", true); map.put("msg","등록완료"); } else { UserPress up
		 * = upressdao.getUserPress(userId, today); up.setPulse(pulse);
		 * up.setSplessure(splessure); up.setDplessure(dplessure);
		 * upressdao.updateUserPress(up); map.put("result", true);
		 * map.put("msg","수정완료"); }
		 */

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;

	}

	// 콜레스테롤
	@RequestMapping("/m/pointer_colinsert.go")
	public String pcolController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "col", required = false, defaultValue = "0") int col,
			@RequestParam(value = "ldl", required = false, defaultValue = "0") int ldl,
			@RequestParam(value = "tg", required = false, defaultValue = "0") int tg,
			@RequestParam(value = "hdl", required = false, defaultValue = "0") int hdl, HttpServletRequest req,
			HttpServletResponse res) {

		String today = T.getToday();

		Map<String, Object> map = new HashMap<String, Object>();

		UserCol uc = new UserCol();
		uc.setCol(col);
		uc.setHdl(hdl);
		uc.setLdl(ldl);
		uc.setTg(tg);
		uc.setUserId(userId);
		ucoldao.addUserCol(uc);
		map.put("result", true);
		map.put("msg", "등록완료");

		/**
		 * 당일 데이터 덮어쓰기 int count = ucoldao.getCount(userId, today); if (count ==
		 * 0) { UserCol uc = new UserCol(); uc.setCol(col); uc.setHdl(hdl);
		 * uc.setLdl(ldl); uc.setTg(tg); uc.setUserId(userId);
		 * ucoldao.addUserCol(uc); map.put("result", true);
		 * map.put("msg","등록완료"); } else { UserCol uc =
		 * ucoldao.getUserCol(userId, today); uc.setCol(col); uc.setHdl(hdl);
		 * uc.setLdl(ldl); uc.setTg(tg); ucoldao.updateUserCol(uc);
		 * map.put("result", true); map.put("msg","수정완료"); }
		 */
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;

	}

	// 체중등록
	@RequestMapping("/m/pointer_weinsert.go")
	public String pweiController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "weightNum", required = false, defaultValue = "0") int weightNum, // 체중
			@RequestParam(value = "bmi", required = false, defaultValue = "0") double bmi, // 체지방
			@RequestParam(value = "bbmi", required = false, defaultValue = "0") double bbmi, // 체지방
			@RequestParam(value = "tbw", required = false, defaultValue = "0") double tbw, // 체수분
			@RequestParam(value = "muscle", required = false, defaultValue = "0") double muscle, // 근육
			@RequestParam(value = "bmd", required = false, defaultValue = "0") double bmd, // 골량
			@RequestParam(value = "equip", required = false, defaultValue = "0") int equip, // 기기사용여부
																							// 0
																							// 사용안함
																							// 1사용
			HttpServletRequest req, HttpServletResponse res) {
		String today = T.getToday();

		Map<String, Object> map = new HashMap<String, Object>();

		UserWeight uw = new UserWeight();
		uw.setWeightNum(weightNum);
		uw.setBmi(bmi);
		uw.setBmd(bmd);
		uw.setEquip(equip);
		uw.setMuscle(muscle);
		uw.setTbw(tbw);
		uw.setBbmi(bbmi);
		uw.setUserId(userId);
		uweightdao.addUserWeight(uw);
		UserBasic ub = ubasicdao.getUserBasic(userId);
		if (!ub.getUserId().equals("")) {
			ubasicdao.updateUserWeight(weightNum, userId);
		}

		map.put("result", true);
		map.put("msg", "등록완료");

		/**
		 * 당일 데이터 덮어쓰기 int count = uweightdao.getCount(userId, today); if (count
		 * == 0) { UserWeight uw = new UserWeight(); uw.setWeightNum(weightNum);
		 * uw.setBmi(bmi); uw.setBmd(bmd); uw.setEquip(equip);
		 * uw.setMuscle(muscle); uw.setTbw(tbw); uw.setBbmi(bbmi);
		 * uw.setUserId(userId); uweightdao.addUserWeight(uw); map.put("result",
		 * true); map.put("msg","등록완료"); } else { UserWeight uw =
		 * uweightdao.getUserWeight(userId, today); uw.setWeightNum(weightNum);
		 * uw.setBmi(bmi); uw.setUserId(userId); uw.setEquip(equip);
		 * if(equip==0){//기기사용안함
		 * 
		 * uweightdao.updateUserWeight(uw); }else{ //함 uw.setBmd(bmd);
		 * uw.setBbmi(bbmi); uw.setMuscle(muscle); uw.setTbw(tbw);
		 * uweightdao.updateUserWeight(uw); }
		 * 
		 * map.put("result", true); map.put("msg","수정완료"); }
		 */

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 당화혈색소등록
	@RequestMapping("/m/pointer_hbinsert.go")
	public String phController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "hbaNum", required = false, defaultValue = "0") Double hbaNum, HttpServletRequest req,
			HttpServletResponse res) {

		String today = T.getToday();

		Map<String, Object> map = new HashMap<String, Object>();

		Userhb hb = new Userhb();
		hb.setHbaNum(hbaNum);
		hb.setUserId(userId);
		uhbdao.addUserhb(hb);
		map.put("result", true);
		map.put("msg", "등록완료");

		/**
		 * 당일 데이터 덮어쓰기 int count = uhbdao.getCount(userId, today); if (count ==
		 * 0) { Userhb hb = new Userhb(); hb.setHbaNum(hbaNum);
		 * hb.setUserId(userId); uhbdao.addUserhb(hb); map.put("result", true);
		 * map.put("msg","등록완료"); } else { Userhb hb = uhbdao.getUserhb(userId,
		 * today); hb.setHbaNum(hbaNum); hb.setUserId(userId);
		 * uhbdao.updateUserhb(hb); map.put("result", true);
		 * map.put("msg","수정완료"); }
		 */

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;

	}

	// 지표결과 전송
	@RequestMapping("/m/pointer_result.go")
	public String phController(
			@RequestParam(value = "commentCode", required = false, defaultValue = "") int commentCode,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		Comment cc = commentdao.getComment(commentCode);
		String str =cc.getImgType().replace("green", SERVER_DOMAIN+"/images/sticker_good.png" )
		.replace("yellow", SERVER_DOMAIN+"/images/sticker_caution.png")
		.replace("red", SERVER_DOMAIN+"/images/sticker_danger.png")
		.replace("Ered", SERVER_DOMAIN+"/images/st_emergency.png");
		
		cc.setImgType(str);
		
		map.put("data", cc);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;

	}

	// 관리주기안내
	@RequestMapping("/m/period.go")
	public String periodController(@RequestParam(value = "kind", required = false, defaultValue = "") int kind,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "blood", required = false, defaultValue = "") String blood,
			@RequestParam(value = "press", required = false, defaultValue = "") String press,
			@RequestParam(value = "col", required = false, defaultValue = "") String col,

	HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		// 질문
		List<Period> list = perioddao.getPeriod(kind);
		for (int i = 0; i < list.size(); i++) {
			Period period = (Period) list.get(i);

			List<Period> answerList = perioddao.getPeriodAnswerList(period.getPreSeq());
			period.setAnswerList(answerList);
			list.set(i, period);

		}
		// 질문답변담음
		map.put("list", list);

		map.put("blood", perioddao.getPeriodAnswer(blood)); // 결과값
		map.put("press", perioddao.getPeriodAnswer(press));
		map.put("col", perioddao.getPeriodAnswer(col));
		map.put("weight", perioddao.getPeriodAnswer("weight"));

		

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;

	}

	/*
	 * //관리주기 등록/변경
	 * 
	 * @RequestMapping("/m/period_into/{diseaseId}.go") public String
	 * phController(
	 * 
	 * @PathVariable("diseaseId") String dId,
	 * 
	 * @RequestParam(value="userId", required=false, defaultValue="") String
	 * userId,
	 * 
	 * @RequestParam(value="press", required=false, defaultValue="") String
	 * press,
	 * 
	 * HttpServletRequest req, HttpServletResponse res ) {
	 * 
	 * 
	 * if(dId.equals("blood")){//관리주기변경
	 * 
	 * ucntdao.upUserbCnt(userId,4); //1주 } else if(dId.equals("press")){
	 * if(press!="")//질환자이면 { ucntdao.upUserpCnt(userId,2); //2개월에한번 } else{
	 * ucntdao.upUserpCnt(userId,1);//1개월에 한번 } }
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>();
	 * map.put("result", true); map.put("msg","등록완료");
	 * 
	 * JSONObject jsonObject = JSONObject.fromObject(map);
	 * Response.responseWrite(res, jsonObject);
	 * 
	 * return null;
	 * 
	 * }
	 */

	// 관리주기변경안내
	@RequestMapping("/m/period_edit.go")
	public String periodCController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,

	HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		String today = T.getToday(); // "2015-10-12"

		UserBasic userbasic = ubasicdao.getUserBasic(userId);
		String before = T.getDateAdd(today, -2);
		// 사용자가 데이터를 3일 이상 연속 입력했는지확인
		int cnt = ublooddao.getUserBloodcnt(userId, today, before, 3);
		User uu = userdao.getUser(userId);
		if (cnt > 2) { // 혈당 관리주기변경임
			List<Cperiod> list = cperioddao.getCperiod("blood");
			for (int i = 0; i < list.size(); i++) {
				Cperiod cp = (Cperiod) list.get(i);
				cp.setComment(cp.getComment().replace("(사용자명)",uu.getUserName()));
				List<Cperiod> answerList = cperioddao.getCperiodAnswerList(cp.getCperSeq());
				cp.setAnswerList(answerList);
				list.set(i, cp);

			}
			map.put("list", list);
			map.put("result", true);
			ucntdao.upUserbCnt(userId, 4); // 1주

		} else { // 아니면 혈압이겠지

			if (!userbasic.getPress().equals("")) // 질환자이면
			{
			
				List<Cperiod> list2 = cperioddao.getCperiod("ispress");

				for (int i = 0; i < list2.size(); i++) {
					Cperiod cp = (Cperiod) list2.get(i);
					cp.setComment(cp.getComment().replace("(사용자명)",uu.getUserName()));
					List<Cperiod> answerList = cperioddao.getCperiodAnswerList(cp.getCperSeq());
					cp.setAnswerList(answerList);
					list2.set(i, cp);

				}
				map.put("list", list2);
				ucntdao.upUserpCnt(userId, 2); // 1개월 두번
			} else {

				List<Cperiod> list2 = cperioddao.getCperiod("press");

				for (int i = 0; i < list2.size(); i++) {
					Cperiod cp = (Cperiod) list2.get(i);
					cp.setComment(cp.getComment().replace("(사용자명)",uu.getUserName()));
					List<Cperiod> answerList = cperioddao.getCperiodAnswerList(cp.getCperSeq());
					cp.setAnswerList(answerList);
					list2.set(i, cp);

				}
				map.put("list", list2);
				ucntdao.upUserpCnt(userId, 1);// 1개월에 한번
			}
			map.put("result", true);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;

	}

	// 기간내 미입력
	@RequestMapping("/m/period_err.go")
	public String periodEController(@RequestParam(value = "userId", required = false, defaultValue = "0") String userId,
			HttpServletRequest req, HttpServletResponse res) {
		
		
		User user = userdao.getUsers(userId);
		String today = T.getToday(); // "2015-10-12"
		String before = T.getDateAdd(today, -6);
		int presscnt = upressdao.getUserPresscnt(userId, today, before); // 혈압갯수
		before = T.getDateAdd(today, -2);
		int bloodcnt = ublooddao.getUserBloodcnt(userId, today, before);
		before = T.getDateAdd(today, -30);
		int weightcnt = uweightdao.getUserWeightcnt(userId, today, before);
		//int num = T.getDateminus(today, (user.getRegDate().substring(0, 10)));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "eperiod");
		List<Eperiod> kcaselist = eperioddao.getkcase();
		List list = new ArrayList();
		int kcaseCount = eperioddao.getkcaseCount();
		int kcase = ((int) (Math.random() * kcaseCount) + 1);
		boolean chk = true;
		if (kcaselist.size() > 0) { // 갯수가 크면
			int index = (int) ((Math.random() * kcaselist.size()) + 1); // 숫자만큼
																		// 랜덤

			while (chk) {

				index = (int) ((Math.random() * kcaselist.size()) + 1);
				list = eperioddao.getEperiod(index);
				String str = "";
				if (list.size() > 0) {
					for(int i=0;i<list.size();i++){
						//String str = "";
						Eperiod ep = (Eperiod)list.get(i);
						if(bloodcnt==0){
							str = "혈당";
						}else if(presscnt == 0){
							str = "혈압";
						}else if(weightcnt == 0){
							str = "체중";
						}
						ep.setComment(ep.getComment().replace("(사용자명)", user.getUserName()).replace("(질환명)",str));
						list.set(i, ep);
					}
					break;

				}
			}
		}

		// list = eperioddao.getEperiod(kcase) ;
		for (int i = 0; i < list.size(); i++) {
			Eperiod ep = (Eperiod) list.get(i);
			if (ep.getAnsType() == 1) {
				List<Eperiod> answerList = eperioddao.getEperiodAnswerList(ep.getEperSeq());
				ep.setAnswerList(answerList);
				list.set(i, ep);
			}

		}
		map.put("result", true);
		
		map.put("list", list);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;

	}

	// cvrisk 안내
	@RequestMapping("/m/cvrisk.go")
	public String cvriskintController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId, HttpServletRequest req,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		int col = ucoldao.datacntCol(userId);
		// 혈압
		int press = upressdao.getCount(userId);

		boolean kind = false;
		int kindd = 0;
		if (col > 0 && press == 0) {// 콜 있고 혈압 없고
			kind = true;
			kindd = 3;
		} else if (press > 0 && col == 0) {// 콜없고 혈압있고
			kindd = 2;
		} else if (press > 0 && col > 0) {// 둘다있고
			kindd = 1;
		} else {// 둘다 없으면
			kindd = 0;
		}
		List list = new ArrayList();
		list = cvriskdao.getCvrisk(kindd);
		for (int i = 0; i < list.size(); i++) {
			Cvrisk cv = (Cvrisk) list.get(i);
			if (cv.getAnsType() == 1) {
				List<Cvrisk> answerList = cvriskdao.CvriskAnswerList(cv.getCvSeq(), kindd);
				cv.setAnswerList(answerList);
				list.set(i, cv);
			}
		}
		map.put("result", true);
		map.put("kind", kindd);
		map.put("list", list);
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;

	}
	/*
	 * //cvrisk 계산
	 * 
	 * @RequestMapping("/m/cvrisk_cal.go") public String cvriskcalController(
	 * 
	 * @RequestParam(value="userId", required=false, defaultValue="") String
	 * userId,
	 * 
	 * @RequestParam(value="col", required=false, defaultValue="") int col,
	 * 
	 * @RequestParam(value="hdl", required=false, defaultValue="") int hdl,
	 * HttpServletRequest req, HttpServletResponse res ) {
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>();
	 * 
	 * String message = ""; int result=0; int tage=0; ///계산식 String birth =
	 * userdao.getUsers(userId).getBirthday(); int birthyear =
	 * Integer.parseInt(birth.substring(0, 4)); int nowyear =
	 * Integer.parseInt(T.getTodayYear()); tage = (nowyear-birthyear)+10;
	 * 
	 * 
	 * map.put("tage", tage); map.put("data", result); map.put("result", true);
	 * JSONObject jsonObject = JSONObject.fromObject(map);
	 * Response.responseWrite(res, jsonObject); return null;
	 * 
	 * }
	 */

	// cvrisk 등록
	@RequestMapping("/m/cvrisk_insert.go")
	public String cvriskController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "cvNum", required = false, defaultValue = "0") int cvNum,
			@RequestParam(value = "userTage", required = false, defaultValue = "0") int userTage,
			@RequestParam(value = "col", required = false, defaultValue = "0") int col,
			@RequestParam(value = "hdl", required = false, defaultValue = "0") int hdl, 
			@RequestParam(value = "splessure", required = false, defaultValue = "0") int splessure, 
			@RequestParam(value = "dplessure", required = false, defaultValue = "0") int dplessure, 
			
			HttpServletRequest req,HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		UserCvrisk uc = ucvriskdao.getUserCvrisk(userId);//일단가져오고요
		UserBasic ub = ubasicdao.getUserBasic(userId);
		User uu = userdao.getUsers(userId);
		int birthyear = Integer.parseInt((uu.getBirthday()).substring(0, 4));
		int nowyear = Integer.parseInt(T.getTodayYear());
		int age = nowyear - birthyear;
		
		if(uc!=null){
			
			
			if(uc.getCol()==col && uc.getCvNum() == cvNum && uc.getHdl()==hdl && uc.getSplessure()==splessure && uc.getDplessure()==dplessure
					&&uc.getSmoke()==ub.getSmoke() && uc.getUserAge()==age		
				){ //데이터가 모두 같으면
				map.put("result", false);
				map.put("msg", "등록된 값과 같습니다.");
				
			}else{
				uc = new UserCvrisk();
				uc.setCol(col);
				uc.setHdl(hdl);
				uc.setCvNum(cvNum);
				uc.setUserId(userId);
				uc.setUserTage(userTage);
				uc.setUserAge(age);
				uc.setSmoke(ub.getSmoke());
				uc.setDplessure(dplessure);
				uc.setSplessure(splessure);
				ucvriskdao.addUserCvrisk(uc);
				map.put("result", true);
				map.put("msg", "등록완료");
				
			}
			
			
		}else{
			
			uc = new UserCvrisk();
			uc.setCol(col);
			uc.setHdl(hdl);
			uc.setCvNum(cvNum);
			uc.setUserId(userId);
			uc.setUserTage(userTage);
			uc.setUserAge(age);
			uc.setSmoke(ub.getSmoke());
			uc.setDplessure(dplessure);
			uc.setSplessure(splessure);
			ucvriskdao.addUserCvrisk(uc);
			map.put("result", true);
			map.put("msg", "등록완료");
			
		}
		
		
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 자동대화
	@RequestMapping("/m/daycontents.go")
	public String dayController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		/*
		 * 1. CV-Risk a. 가입후 24시간 지났는데 등록된 데이터가 없을 때 b. 가입후 1년이 지났는데 등록된 데이터가 없을
		 * 때 2. 기간내 미등록 안내 3. 1주 컨텐츠 : 생년월일의 요일과 동일한 요일일 때 4. 1개월 컨텐츠 : 매월 첫번째
		 * 1주 컨텐츠일 때 5. 1일 컨텐츠 이외에
		 */

		User user = userdao.getUsers(userId);
		String userName = user.getUserName();
		String regday = user.getRegDate().substring(0, 10);
		String regdate = regday.substring(8, 10);
		String todaymonet = T.getMonth();
		String nextregday = T.getDateAdd(regday, 1);
		String beforeregday = T.getDateAdd(regday, -1);
		String maga = T.getDateAdd(todaymonet + "-" + regdate, -10);
		String repo = T.getDateAdd(todaymonet + "-" + regdate, -3);
		System.out.println(repo);
		
		int birthWeek = T.getWeekDay(regday);
		int todayWeek = T.getWeekDay(T.getToday());

		int regyo = T.daytype(regday);
		int beforeregyo = T.daytype(beforeregday);
		int todayyo = T.daytype(T.getToday());
		int nextregyo = T.daytype(nextregday);
		String today = T.getToday(); // "2015-10-12"
		String before = T.getDateAdd(today, -6);
		System.out.println(todayyo);
		System.out.println(beforeregyo);
		int presscnt = upressdao.getUserPresscnt(userId, today, before); // 혈압갯수
		before = T.getDateAdd(today, -2);
		int bloodcnt = ublooddao.getUserBloodcnt(userId, today, before);
		before = T.getDateAdd(today, -30);
		int weightcnt = uweightdao.getUserWeightcnt(userId, today, before);
		int num = T.getDateminus(today, (user.getRegDate().substring(0, 10)));

		if(todayyo==beforeregyo){
			System.out.println("dd");
		}
		List list = new ArrayList();

		int cvriskCount = ucvriskdao.getUserCvcount(userId, today);

		if (bloodcnt == 0 || presscnt == 0 || weightcnt == 0) { // 기간내 미등록
			map.put("type", "eperiod");
			List<Eperiod> kcaselist = eperioddao.getkcase();
			
			int kcaseCount = eperioddao.getkcaseCount();
			int kcase = ((int) (Math.random() * kcaseCount) + 1);
			boolean chk = true;
			if (kcaselist.size() > 0) { // 갯수가 크면
				int index = (int) ((Math.random() * kcaselist.size()) + 1); // 숫자만큼
																			// 랜덤

				while (chk) {

					index = (int) ((Math.random() * kcaselist.size()) + 1);
					list = eperioddao.getEperiod(index);
					String str = "";
					if (list.size() > 0) {
						for(int i=0;i<list.size();i++){
							//String str = "";
							Eperiod ep = (Eperiod)list.get(i);
							if(bloodcnt==0){
								str = "혈당";
							}else if(presscnt == 0){
								str = "혈압";
							}else if(weightcnt == 0){
								str = "체중";
							}
							ep.setComment(ep.getComment().replace("(사용자명)", user.getUserName()).replace("(질환명)",str));
							list.set(i, ep);
						}
						break;

					}
				}
			}

			// list = eperioddao.getEperiod(kcase) ;
			for (int i = 0; i < list.size(); i++) {
				Eperiod ep = (Eperiod) list.get(i);
				if (ep.getAnsType() == 1) {
					List<Eperiod> answerList = eperioddao.getEperiodAnswerList(ep.getEperSeq());
					ep.setAnswerList(answerList);
					list.set(i, ep);
				}

			}
			map.put("result", true);
			
			map.put("list", list);
			

		} else if (num == 1 && cvriskCount < 1) {// cv 리스크 1일에 뜸
			map.put("type", "cvrisk");

			map.put("result", true);

		} else if (num != 0 && num % 365 == 0 && cvriskCount < 1) {// 1년 뒤 뜸
			map.put("type", "cvrisk");

			map.put("result", true);

		} else if (maga.equals(today)) { // 1개월 컨텐츠
			map.put("type", "month");
		
			list = monthdao.getmonthlist(today.substring(0, 7));
			for (int i = 0; i < list.size(); i++) {
				Month mm = (Month) list.get(i);
				if (mm.getAnsType() == 1) {
					List<Month> answerList = monthdao.getmonthAnswerList(mm.getMonthSeq(), today.substring(0, 7));
					mm.setAnswerList(answerList);
					list.set(i, mm);
				}

			}

			Magazine mz = magaDao.getTopMagazine(today.substring(0, 7));
			Month mm = new Month();
			mm.setAnsType(5);
			mm.setComment(mz.getSubTitle());
			mm.setPrimarySeq(100);
			mm.setMonthSeq(100);
			mm.setUrl(SERVER_DOMAIN+"/m/maga_view.go?mSeq=" + Integer.toString(mz.getmSeq()));
			mm.setThumFile("/files/magazine/"+mz.getThumFile());
			mm.setIsLast(1);
			list.add(mm);
			map.put("result", true);
			map.put("list", list);

		} else if (today.equals(repo)) { // 1개월 레포트
			System.out.println("dd");
			map.put("type", "monthreport");
			Calendar date = Calendar.getInstance();
			UserCnt uc = new UserCnt();
			uc = ucntdao.getUserCntList(userId);
			List<Report> monthreportlist = reportdao.getReportList(2);

			before = T.getBeforeYearMonthByYM(1);
			String b = before +"-01";
			String [] arr =before.split("-");
			int lastday = T.getLastMonthday(before);
			
			String tafter = before+"-"+lastday;
		
			presscnt = upressdao.getmonthCount(userId, b,tafter); // 혈압갯수
			bloodcnt = ublooddao.getmonthCount(userId, b,tafter);// 혈당갯수
			weightcnt = uweightdao.getmonthCount(userId, b,tafter);// 체중갯수

			int ptype = wkpointer.type(presscnt, uc.getPcnt());
			int btype = wkpointer.type(bloodcnt, uc.getBcnt());
			int wtype = wkpointer.type(weightcnt, uc.getWcnt());

			List lastList = new ArrayList();

			String pstr = (anlisysdao.getAnalisys(ptype)).getComment();
			String bstr = (anlisysdao.getAnalisys(btype)).getComment();
			String wstr = (anlisysdao.getAnalisys(wtype)).getComment();
			String setstr = "";
			String setstr2 = "";
			List commentList = new ArrayList();

			if (ptype == btype && btype == wtype) { // 셋다 같을때
				setstr = "모두 " + pstr;
				commentList.add(setstr);

			} else if (ptype <= btype && ptype <= wtype) { // 혈압이 제일 우선순위
				if (ptype == btype) {// 같으면
					setstr = "혈압,혈당 횟수는" + pstr;
					commentList.add(setstr);
				} else if (ptype == wtype) {
					setstr = "혈압,체중 횟수는" + pstr;
					commentList.add(setstr);
				} else {
					setstr = "혈압은 " + pstr;
					commentList.add(setstr);
					if (btype == wtype) {
						setstr = "혈당,체중 횟수는" + bstr;
						commentList.add(setstr);
					} else if (btype < wtype) {
						setstr = "혈당은 " + bstr;
						commentList.add(setstr);
						setstr = "체중은 " + wstr;
						commentList.add(setstr);
					} else {
						setstr = "체중은 " + wstr;
						commentList.add(setstr);
						setstr = "혈당은 " + bstr;
						commentList.add(setstr);
					}
				}
			} else if (btype <= ptype && btype <= wtype) {// 혈당이 우선순위
				if (btype == ptype) {// 같으면
					setstr = "혈압,혈당 횟수는" + bstr;
				} else if (btype == wtype) {
					setstr = "혈당,체중 횟수는" + bstr;
				} else {
					setstr = "혈당은 " + bstr;
					commentList.add(setstr);
					if (wtype == ptype) {
						setstr = "혈압,체중 횟수는" + pstr;
						commentList.add(setstr);
					} else if (ptype < wtype) {
						setstr = "혈압은 " + pstr;
						commentList.add(setstr);
						setstr = "체중은 " + wstr;
						commentList.add(setstr);
					} else {
						setstr = "체중은 " + wstr;
						commentList.add(setstr);
						setstr = "혈압은 " + pstr;
						commentList.add(setstr);
					}
				}
			} else if (wtype <= ptype && wtype <= btype) {// 체중이 우선순위
				if (wtype == ptype) {// 같으면
					setstr = "혈압,체중 횟수" + wstr;
				} else if (wtype == btype) {
					setstr = "혈당,체중 횟수" + wstr;
				} else {
					setstr = "체중은 " + wstr;
					commentList.add(setstr);
					if (ptype == btype) {
						setstr = "혈압,혈당 횟수" + pstr;
						commentList.add(setstr);
					} else if (ptype < btype) {
						setstr = "혈압은 " + pstr;
						commentList.add(setstr);
						setstr = "혈당은 " + bstr;
						commentList.add(setstr);
					} else {
						//System.out.println("ddd");
						setstr = "혈당은 " + bstr;
						commentList.add(setstr);
						setstr = "혈압은 " + pstr;
						commentList.add(setstr);
					}
				}
			}

			for (int i = 0; i < monthreportlist.size(); i++) {
				Report rp = monthreportlist.get(i);
				String comment = rp.getComment();
				rp.setComment(comment.replace("(사용자명)", userName));

				if (comment.equals("setcnt")) {// 처음차트
					
					comment = Integer.toString(bloodcnt) + "," + Integer.toString(presscnt) + ","
							+ Integer.toString(weightcnt) + "," + Integer.toString(uc.getBcnt()) + ","
							+ Integer.toString(uc.getPcnt()) + "," + Integer.toString(uc.getWcnt())+","+
							before+"-1 ~ "+tafter;
					
					rp.setComment(comment);

				}
				if (rp.getAnsType() == 1) {
					List<Report> answerList = reportdao.getReportanswerList(2, rp.getReportSeq());
					rp.setAnswerList(answerList);

				}
				if (comment.equals("comment1")) {

					String com = (String) commentList.get(0);
					if (commentList.size() == 1) {// 모두같을때
						rp.setIsLast(1);// 마지막값주고
						rp.setMove(0);// 무브 0
					}
					rp.setComment(com);

				} else if (comment.equals("comment2")) {
					if (commentList.size() > 1) {
						String com = (String) commentList.get(1);
						if (commentList.size() == 2) {// 모두같을때
							rp.setIsLast(1);// 마지막값주고
							rp.setMove(0);// 무브 0

						}
						rp.setComment(com);
					}

				} else if (comment.equals("comment3")) {
					if (commentList.size() > 2) {
						String com = (String) commentList.get(2);

						rp.setComment(com);
					}

				}
				list.add(rp);

			}	
			map.put("list", list);
			map.put("result", true);
		
		
			
		} else if (todayyo == beforeregyo) { // 1주컨텐츠
			map.put("type", "week");
			
			UserBasic ub = ubasicdao.getUserBasic(userId);
			String isblood = ub.getBlood();
			String ispress = ub.getPress();
			String iscol = ub.getCol();
			String isweight = ub.getHeiwieght();
			boolean chk = true;

			List pressList = wkpointer.setting("press", userName);

			List bloodList = wkpointer.setting("blood", userName);

			List colList = wkpointer.setting("col", userName);

			List weightList = wkpointer.setting("weight", userName);

			if (!ispress.equals("")) {
				for (int i = 0; i < pressList.size(); i++) {
					Week week = (Week) pressList.get(i);
					if ((isblood.equals("") && iscol.equals("")) == false) {
						week.setIsLast(0);
					}
					list.add(week);
				}
			}
			if (!isblood.equals("")) {
				for (int i = 0; i < bloodList.size(); i++) {
					Week week = (Week) bloodList.get(i);
					if (!iscol.equals("")) {
						week.setIsLast(0);
					}
					list.add(week);
				}
			}
			if (!iscol.equals("")) {
				for (int i = 0; i < colList.size(); i++) {
					Week week = (Week) colList.get(i);
					if (!isweight.equals("")) {
						week.setIsLast(0);
					}
					list.add(week);
				}
			}
			if (!isweight.equals("")) {
				for (int i = 0; i < weightList.size(); i++) {
					Week week = (Week) weightList.get(i);
					list.add(week);
				}
			}
			if(list.size()==0){
				int index = (int)((Math.random() * 4)+1);
				if(index==1){
					list = pressList;
				}else if(index==2){
					list =bloodList;
					
				}else if(index==3){
					list =colList;
					
				}else{
					list=weightList;
				}
			
			}
			map.put("result", true);
			map.put("list", list);
		} else if (todayyo == nextregyo) { // 1주레포트
			map.put("type", "weekreport");

			before = T.getDateAdd(today, -6);

			int weekbloodcnt = ublooddao.getUserweekBloodcnt(userId, today, before);
			int weekpresscnt = upressdao.getUserPresscnt(userId, today, before);
			int weekweightcnt = uweightdao.getUserWeightcnt(userId, today, before);

			if (weekbloodcnt > 0 || weekpresscnt > 0 || weekweightcnt > 0) {// 한개라도
																			// 데이터가잇음
				int code = 0;
				int code2 = 0;
				String commentType = "";
				String maxtype = "";// 제일 높은값 담을 변수
				List<Report> breportlist = reportdao.getReportList(1, "blood");// 질문을
																				// 가져온다
				List<Report> preportlist = reportdao.getReportList(1, "press");// 질문을
																				// 가져온다
				List<Report> wreportlist = reportdao.getReportList(1, "weight");// 질문을
																				// 가져온다
				List<Report> lastlist = reportdao.lastReportList();
				int gong = ublooddao.getUserBloodcnt(userId, today, before, 1); // 공복
																				// 측정횟수
				int sik = ublooddao.getUserBloodcnt(userId, today, before, 2);// 식후
																				// 측정횟수
				int sleep = ublooddao.getUserBloodcnt(userId, today, before, 3);// 취침전
																				// 측정횟수
				UserGoal ug = ugoaldao.getuserGoal(userId);
				UserCnt uc = ucntdao.getUserCntList(userId);

				if (gong >= sik && gong >= sleep) {// 공복이 제일큼
					maxtype = "공복혈당";

				} else if (sik >= gong && sik >= sleep) {// 식후가 제일큼
					maxtype = "식후혈당";

				} else if (sleep >= gong && sleep >= sik) {// 취침전혈당이 제일큼
					maxtype = "취침전혈당";
				}
				for (int i = 0; i < 2; i++) {
					Report rp = breportlist.get(i);
					String comment = rp.getComment(); // 코멘트를 담아서
					String cmt = comment.replace("(사용자명)", userName);
					rp.setComment(cmt);
					if (comment.equals("bloodfirst")) {// 처음차트
						comment = Integer.toString(weekbloodcnt) + "," + Integer.toString(weekpresscnt) + ","
								+ Integer.toString(weekweightcnt) + "," + Integer.toString(uc.getBcnt()) + ","
								+ Integer.toString(uc.getPcnt()) + "," + Integer.toString(uc.getWcnt());
						rp.setComment(comment);
						if(weekbloodcnt==0&&weekpresscnt!=0&&weekweightcnt==0){
							rp.setMove(10);
						}else if(weekbloodcnt==0&&weekpresscnt==0&&weekweightcnt!=0){
							rp.setMove(15);
						}else if(weekbloodcnt==0&&weekpresscnt!=0&&weekweightcnt!=0){
							rp.setMove(10);
						}
						

					}
					list.add(rp);
				}
				if (weekbloodcnt > 0) {
					for (int i = 2; i < breportlist.size(); i++) {
						Report rp = breportlist.get(i);
						String comment = rp.getComment(); // 코멘트를 담아서
						String cmt = comment.replace("(가장 높은 구분값명)", " " + maxtype);
						rp.setComment(cmt);
						if (rp.getAnsType() == 1) {
							List answerList = reportdao.getReportanswerList(1, rp.getReportSeq());
							rp.setAnswerList(answerList);

						} else if (comment.equals("blooduser")) {// 두번째 차트

							comment = Integer.toString(gong) + "," + Integer.toString(sik) + "," + Integer.toString(sleep);
							rp.setComment(comment);

						} else if (comment.equals("blooddetail")) {// 마지막차트

							if (maxtype.equals("공복혈당")) {

								int gongavg = ublooddao.getUserBloodavg(userId, today, before, 1);// 내평균
								int ogongavg = ublooddao.getotherBloodavg(today, before, 1);// 타인평균
								comment = Integer.toString(ug.getGoalbMblood()) + "," + // 목표
										Integer.toString(gongavg) + "," + // 내평균
										Integer.toString(ogongavg);// 타인평균
								rp.setComment(comment);
								code = wkpointer.code1(gongavg, ogongavg);
								code2 = wkpointer.code2(ug.getGoalbMblood(), gongavg);

							} else if (maxtype.equals("식후혈당")) {

								int sikavg = ublooddao.getUserBloodavg(userId, today, before, 2);// 내평균
								int osikavg = ublooddao.getotherBloodavg(today, before, 2);// 타인평균
								comment = Integer.toString(ug.getGoalEblood()) + "," + // 목표
										Integer.toString(sikavg) + "," + // 내평균
										Integer.toString(osikavg);// 타인평균
								rp.setComment(comment);
								code = wkpointer.code1(sikavg, osikavg);
								code2 = wkpointer.code2(ug.getGoalEblood(), sikavg);

							} else if (maxtype.equals("취침전혈당")) {

								int sleepavg = ublooddao.getUserBloodavg(userId, today, before, 3);// 내평균
								int osleepavg = ublooddao.getotherBloodavg(today, before, 3);// 타인평균
								comment = "goal:" + Integer.toString(ug.getGoalSblood()) + "," // 목표
										+ "myavg:" + Integer.toString(sleepavg) + "," // 내평균
										+ "otheravg:" + Integer.toString(osleepavg);// 타인평균
								rp.setComment(comment);
								code = wkpointer.code1(sleepavg, osleepavg);
								code2 = wkpointer.code2(ug.getGoalSblood(), sleepavg);
							}
						}
					
						if (comment.equals("bloodcomment")) {// 처음분석 코멘트
							Analisys an = anlisysdao.getAnalisys(code);
							commentType = "혈당의 " + an.getComment();
							rp.setComment(commentType);

						} else if (comment.equals("bloodcomment2")) {// 두번째분석
																		// 코멘트
							Analisys an = anlisysdao.getAnalisys(code2);
							String comment2 = an.getComment();
							String commentType2 = "";
							if ((commentType.contains("높") && comment2.contains("높"))
									|| (commentType.contains("비슷한") && comment2.contains("안정적"))
									|| (commentType.contains("낮") && comment2.contains("낮"))) {// 둘다
																								// 같으면
								commentType2 = "수치도";
							} else {
								commentType2 = "수치가";
							}
							rp.setComment(commentType2 + " " + an.getComment());
							if(weekpresscnt == 0 && weekweightcnt != 0 ){
								rp.setMove(15);
							}

						}
						rp.setIsLast(0);
						list.add(rp);

						if (weekpresscnt == 0 && weekweightcnt == 0 && rp.getPrimarySeq() == 9) { // 더이상
																									// 측정이없으면
							if (rp.getPrimarySeq() == 9) {
								rp.setMove(20);
								rp.setAnsType(3);
							}
							for (int j = 0; j < lastlist.size(); j++) {

								Report lastrp = lastlist.get(j);

								lastrp.setComment(lastrp.getComment().replace("(파이팅스티커)",
										SERVER_DOMAIN+"/images/sticker_fighting.png"));
								list.add(lastrp);
							}

						}

					} // 리스트 for문 닫기
				} // 혈당 측정 있으면
				if (weekpresscnt > 0) {// 혈압
					for (int i = 0; i < preportlist.size(); i++) {
						Report rp = preportlist.get(i);
						String comment = rp.getComment(); // 코멘트를 담아서

						if (rp.getAnsType() == 1) {
							List answerList = reportdao.getReportanswerList(1, rp.getReportSeq());
							rp.setAnswerList(answerList);

						}
						if (comment.equals("pressfirst")) {// 처음차트
							int mydavg = upressdao.getUserPressavg(userId, today, before, "dplessure");// 이완
							int mysavg = upressdao.getUserPressavg(userId, today, before, "splessure");// 수축
							int odavg = upressdao.getotherPressavg(today, before, "dplessure");
							int osavg = upressdao.getotherPressavg(today, before, "splessure");

							comment = Integer.toString(mydavg) + "," + Integer.toString(mysavg) + ","
									+ Integer.toString(odavg) + "," + Integer.toString(osavg) + "," + ug.getGoalbPre() + ","
									+ ug.getGoalsPre();
							rp.setComment(comment);
							code = wkpointer.code1(mysavg, osavg);
							code2 = wkpointer.code2(ug.getGoalbPre(), mysavg);

						}
						if (comment.equals("presscomment")) {// 처음분석 코멘트
							Analisys an = anlisysdao.getAnalisys(code);
							commentType = "혈압의 " + an.getComment();
							rp.setComment(commentType);

						} else if (comment.equals("presscomment2")) {// 두번째분석
																		// 코멘트
							Analisys an = anlisysdao.getAnalisys(code2);
							String comment2 = an.getComment();
							String commentType2 = "";
							if ((commentType.contains("높") && comment2.contains("높"))
									|| (commentType.contains("비슷한") && comment2.contains("안정적"))
									|| (commentType.contains("낮") && comment2.contains("낮"))) {// 둘다
																								// 같으면
								commentType2 = "수치도";
							} else {
								commentType2 = "수치가";
							}
							rp.setComment(commentType2 + " " + an.getComment());

						}
						rp.setIsLast(0);
						list.add(rp);

						if (weekweightcnt == 0 && rp.getPrimarySeq() == 14) { // 더이상
																				// 측정이없으면
							if (rp.getPrimarySeq() == 14) {
								rp.setMove(20);
								rp.setAnsType(3);

							}
							for (int j = 0; j < lastlist.size(); j++) {
								Report lastrp = lastlist.get(j);

								lastrp.setComment(lastrp.getComment().replace("(파이팅스티커)",
										SERVER_DOMAIN+"/images/sticker_fighting.png"));

								list.add(lastrp);
							}

						}

					} // 혈압 for문 닫고

				} // 혈압 끝

				if (weekweightcnt > 0) {// 체중
					for (int i = 0; i < wreportlist.size(); i++) {
						Report rp = wreportlist.get(i);
						String comment = rp.getComment(); // 코멘트를 담아서

						if (rp.getAnsType() == 1) {
							List answerList = reportdao.getReportanswerList(1, rp.getReportSeq());
							rp.setAnswerList(answerList);

						}
						if (comment.equals("bmifirst")) {// 처음차트
							double mybmiavg = uweightdao.getUserWeighavg(userId, today, before); // 평균
							double obmiavg = uweightdao.getotherWeighavg(today, before); // 평균//타인
							System.out.println(today+":"+before);
							code = code3(mybmiavg, obmiavg);
							code2 = code4(ug.getGoalbBmi(), mybmiavg);

							comment = Double.toString(mybmiavg) + "," + Double.toString(obmiavg) + ","
									+ Double.toString(ug.getGoalbBmi());
							rp.setComment(comment);

						}

						if (comment.equals("bmicomment")) {// 처음분석 코멘트
							Analisys an = anlisysdao.getAnalisys(code2);
							commentType = "수치가 " + an.getComment();
							rp.setComment(commentType);

						}
						list.add(rp);

					} // 체중 for문 닫고

					for (int j = 0; j < lastlist.size(); j++) {
						Report lastrp = lastlist.get(j);

						lastrp.setComment(lastrp.getComment().replace("(파이팅스티커)",
								SERVER_DOMAIN+"/images/sticker_fighting.png"));

						list.add(lastrp);
					}
				}
			}
			map.put("list", list);
			map.put("result", true);

		} else { // 1일 데이터
			map.put("type", "day");
			List<Day> groupList = daydao.daygroup();

			int daygroup = 0;
			boolean chk = true;
			List<Day> list1 = null;

			while (chk) {

				daygroup = (int) ((Math.random() * groupList.size()) + 1);
				list1 = daydao.getDaylist(daygroup);
				if (list1.size() > 0) {
					break;

				}
			}

			for (int i = 0; i < list1.size(); i++) {

				Day day = list1.get(i);
				day.setComment(day.getComment().replace("(사용자명)", userName));
				list1.set(i, day);

			}
			Day day = (Day) list1.get(0);
			if (user.getGender() == 0) {// 남자일때
				if (day.getGenderType() == 1) { // 여자만 질문이나오면
					while (chk) {
						daygroup = ((int) (Math.random() * groupList.size()) + 1);
						list1 = daydao.getDaylist(daygroup);
						day = (Day) list1.get(0);
						if (day.getGenderType() == 0) {
							chk = false;
						}
					}

				}
			}

			for (int i = 0; i < list1.size(); i++) {
				Day dd = (Day) list1.get(i);
				if (dd.getAnsType() == 1) {
					List<Day> answerList = daydao.getDayAnswerList(dd.getDaySeq(), daygroup);
					dd.setAnswerList(answerList);
					list1.set(i, dd);
				}
			}
			map.put("list", list1);
			map.put("result", true);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;

	}

	/*
	 * //1일컨텐츠
	 * 
	 * @RequestMapping("/m/daycontents_test.go") public String
	 * dayContentsTestController(
	 * 
	 * @RequestParam(value="userId", required=false, defaultValue="") String
	 * userId,
	 * 
	 * @RequestParam(value="typeParam", required=false, defaultValue="") String
	 * typeParam, HttpServletRequest req, HttpServletResponse res ) {
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>(); boolean result =
	 * true; String message = "";
	 * 
	 * 
	 * 
	 * 1. CV-Risk a. 가입후 24시간 지났는데 등록된 데이터가 없을 때 b. 가입후 1년이 지났는데 등록된 데이터가 없을 때
	 * 2. 기간내 미등록 안내 3. 1주 컨텐츠 : 생년월일의 요일과 동일한 요일일 때 4. 1개월 컨텐츠 : 매월 첫번째 1주 컨텐츠일
	 * 때 5. 1일 컨텐츠 이외에
	 * 
	 * 
	 * User user = userdao.getUsers(userId); String userName =
	 * user.getUserName(); String birthDay = user.getBirthday(); int birthWeek =
	 * T.getWeekDay(birthDay); int todayWeek = T.getWeekDay(T.getToday()); int
	 * birthyo =T.daytype(birthDay); int todayyo = T.daytype(T.getToday());
	 * String today = T.getToday(); // "2015-10-12" String before
	 * =T.getDateAdd(today, -6);
	 * 
	 * int presscnt = upressdao.getUserPresscnt(userId, today, before); //혈압갯수
	 * before =T.getDateAdd(today, -2); int bloodcnt =
	 * ublooddao.getUserBloodcnt(userId, today, before); before
	 * =T.getDateAdd(today, -30); int weightcnt =
	 * uweightdao.getUserWeightcnt(userId, today, before); int num =
	 * T.getDateminus(today,(user.getRegDate().substring(0, 10)));
	 * 
	 * List list = new ArrayList();
	 * 
	 * int cvriskCount = ucvriskdao.getUserCvcount(userId, today);
	 * 
	 * 
	 * 
	 * if(bloodcnt==0||presscnt==0||weightcnt==0){ //기간내 미등록 map.put("type",
	 * "eperiod"); List<Eperiod>kcaselist = eperioddao.getkcase(); int
	 * kcaseCount = eperioddao.getkcaseCount(); int kcase= ((int) (Math.random()
	 * * kcaseCount) + 1); boolean chk = true; if(kcaselist.size()>0){ //갯수가 크면
	 * int index = (int)((Math.random() * kcaselist.size())+1); //숫자만큼 랜덤
	 * 
	 * while(chk){
	 * 
	 * index = (int)((Math.random() * kcaselist.size())+1); list =
	 * eperioddao.getEperiod(index) ; if(list.size()>0){ break;
	 * 
	 * } } }
	 * 
	 * 
	 * //list = eperioddao.getEperiod(kcase) ; for (int i=0; i<list.size(); i++)
	 * { Eperiod ep = (Eperiod)list.get(i); if(ep.getAnsType()==1){ List
	 * <Eperiod> answerList = eperioddao.getEperiodAnswerList(ep.getEperSeq()) ;
	 * ep.setAnswerList(answerList); list.set(i, ep); }
	 * 
	 * } map.put("result", true); map.put("bloodcnt", bloodcnt);
	 * map.put("presscnt", presscnt); map.put("weightcnt", weightcnt);
	 * map.put("list", list);
	 * 
	 * 
	 * }else if(num==1 && cvriskCount<1 ){//cv 리스크 1일에 뜸 map.put("type",
	 * "cvrisk");
	 * 
	 * map.put("result", true);
	 * 
	 * 
	 * }else if(num!=0 && num%365==0 && cvriskCount<1){//1년 뒤 뜸 map.put("type",
	 * "cvrisk");
	 * 
	 * map.put("result", true);
	 * 
	 * 
	 * }else if (todayWeek == birthWeek && birthyo==todayyo) { //1개월 컨텐츠
	 * 
	 * map.put("type", "month");
	 * 
	 * list = monthdao.getmonthlist(today.substring(0,7)) ; for (int i=0;
	 * i<list.size(); i++) { Month mm = (Month)list.get(i);
	 * if(mm.getAnsType()==1){ List <Month> answerList =
	 * monthdao.getmonthAnswerList(mm.getMonthSeq(),today.substring(0,7));
	 * mm.setAnswerList(answerList); list.set(i, mm); }
	 * 
	 * }
	 * 
	 * Magazine mz =magaDao.getTopMagazine(today.substring(0,7));
	 * map.put("result", true); map.put("contents", mz); map.put("list", list);
	 * 
	 * } else if (todayWeek == birthWeek) { //1주컨텐츠
	 * 
	 * map.put("type", "week");
	 * 
	 * UserBasic ub = ubasicdao.getUserBasic(userId); String isblood =
	 * ub.getBlood(); String ispress = ub.getPress(); String iscol =
	 * ub.getCol(); String isweight = ub.getHeiwieght(); boolean chk = true;
	 * 
	 * 
	 * List pressList = wkpointer.setting("press",userName);
	 * 
	 * List bloodList = wkpointer.setting("blood",userName);
	 * 
	 * List colList = wkpointer.setting("col",userName);
	 * 
	 * List weightList = wkpointer.setting("weight",userName);
	 * 
	 * 
	 * 
	 * if(!ispress.equals("")){ for (int i=0; i<pressList.size(); i++) { Week
	 * week = (Week) pressList.get(i); if ((isblood.equals("") &&
	 * iscol.equals("")) == false) { week.setIsLast(0); } list.add(week); } }
	 * if(!isblood.equals("")){ for (int i=0; i<bloodList.size(); i++) { Week
	 * week = (Week) bloodList.get(i); if (!iscol.equals("")) {
	 * week.setIsLast(0); } list.add(week); } } if(!iscol.equals("")){ for (int
	 * i=0; i<colList.size(); i++) { Week week = (Week) colList.get(i); if
	 * (!isweight.equals("")) { week.setIsLast(0); } list.add(week); } }
	 * if(!isweight.equals("")){ for (int i=0; i<weightList.size(); i++) { Week
	 * week = (Week) weightList.get(i); list.add(week); } } map.put("list",
	 * list);
	 * 
	 * }else { //1일 데이터
	 * 
	 * map.put("type", "day"); int groupCount = daydao.getGroupCount(); int
	 * daygroup = ((int)(Math.random() * groupCount) + 1); boolean chk = true;
	 * List<Day >list1 = daydao.getDaylist(daygroup) ;
	 * 
	 * 
	 * for(int i=0;i<list1.size();i++){
	 * 
	 * Day day = list1.get(i); day.setComment(day.getComment().replace("(사용자명)",
	 * userName)); list1.set(i, day);
	 * 
	 * } Day day = (Day)list1.get(0); if(user.getGender()==0){//남자일때
	 * if(day.getGenderType()==1){ //여자만 질문이나오면 while(chk){ daygroup =
	 * ((int)(Math.random() * groupCount) + 1); list1 =
	 * daydao.getDaylist(daygroup) ; day = (Day)list1.get(0);
	 * if(day.getGenderType()==0){ chk = false; } }
	 * 
	 * } }
	 * 
	 * for (int i=0; i<list1.size(); i++) { Day dd = (Day)list1.get(i);
	 * if(dd.getAnsType()==1){ List <Day> answerList =
	 * daydao.getDayAnswerList(dd.getDaySeq(),daygroup);
	 * dd.setAnswerList(answerList); list1.set(i, dd); } } map.put("list",
	 * list1); map.put("result", true); }
	 * 
	 * 
	 * JSONObject jsonObject = JSONObject.fromObject(map);
	 * Response.responseWrite(res, jsonObject); return null; }
	 */

	/*
	 * // 1주컨텐츠
	 * 
	 * @RequestMapping("/m/weekcontents.go") public String
	 * weekController(@RequestParam(value = "weekgroup", required = false,
	 * defaultValue = "0") int weekgroup, HttpServletRequest req,
	 * HttpServletResponse res) {
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>(); boolean result =
	 * true; String message = "";
	 * 
	 * List<Week> list = weekdao.getWeeklist(weekgroup); for (int i = 0; i <
	 * list.size(); i++) { Week ww = (Week) list.get(i); if (ww.getAnsType() ==
	 * 1) { List<Week> answerList = weekdao.getWeekAnswerList(ww.getWeekSeq(),
	 * weekgroup); ww.setAnswerList(answerList); list.set(i, ww); } }
	 * map.put("list", list); JSONObject jsonObject =
	 * JSONObject.fromObject(map); Response.responseWrite(res, jsonObject);
	 * return null;
	 * 
	 * }
	 */

	/*
	 * // 1주 레포트 들어옴
	 * 
	 * @RequestMapping("/m/week_intro.go") public String weekintroController(
	 * 
	 * @RequestParam(value = "userId", required = false, defaultValue = "")
	 * String userId, HttpServletRequest req, HttpServletResponse res) {
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>(); String today =
	 * T.getToday(); // "2015-10-12" String before = T.getDateAdd(today, -6);
	 * int bloodcnt = ublooddao.getUserBloodcnt(userId, today, before); int
	 * presscnt = upressdao.getUserPresscnt(userId, today, before); int
	 * weightcnt = uweightdao.getUserWeightcnt(userId, today, before); boolean
	 * result = false; if (bloodcnt > 0 || presscnt > 0 || weightcnt > 0) {
	 * result = true; }
	 * 
	 * map.put("result", result);
	 * 
	 * JSONObject jsonObject = JSONObject.fromObject(map);
	 * Response.responseWrite(res, jsonObject);
	 * 
	 * return null;
	 * 
	 * }
	 */

	/*
	 * // 1주레포트 분석
	 * 
	 * @RequestMapping("/m/week/{dId}.go") public String
	 * weekmaController(@PathVariable("dId") String dId,
	 * 
	 * @RequestParam(value = "userId", required = false, defaultValue = "")
	 * String userId,
	 * 
	 * @RequestParam(value = "kind", required = false, defaultValue = "0") int
	 * kind, HttpServletRequest req, HttpServletResponse res) {
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>(); boolean result =
	 * true; String message = ""; String today = T.getToday(); // "2015-10-12"
	 * String before = T.getDateAdd(today, -7); try { if (dId.equals("first")) {
	 * 
	 * int bloodcnt = ublooddao.getUserBloodcnt(userId, today, before);// 총 //
	 * 측정 // 횟수 int presscnt = upressdao.getUserPresscnt(userId, today, before);
	 * int weightcnt = uweightdao.getUserWeightcnt(userId, today, before);
	 * UserCnt ucnt = ucntdao.getUserCntList(userId); map.put("bloodcnt",
	 * bloodcnt); map.put("presscnt", presscnt); map.put("weightcnt",
	 * weightcnt); if (ucnt == null) { map.put("ucnt", 0);// 목표측정횟수가없으면 } else {
	 * map.put("ucnt", ucnt); }
	 * 
	 * } else if (dId.equals("blooddetail")) { int gong =
	 * ublooddao.getUserBloodcnt(userId, today, before, 1); // 공복 // 측정횟수
	 * map.put("gong", gong); int sik = ublooddao.getUserBloodcnt(userId, today,
	 * before, 2);// 식후 // 측정횟수 map.put("sik", sik); int sleep =
	 * ublooddao.getUserBloodcnt(userId, today, before, 3);// 취침전 // 측정횟수
	 * map.put("sleep", sleep);
	 * 
	 * } else { int code = 0; int code2 = 0; UserGoal ug =
	 * ugoaldao.getuserGoal(userId); // map.put("goal", ug); int goaldata = 0;
	 * if (dId.equals("blood")) { // 공복 if (kind == 1) { int gongavg =
	 * ublooddao.getUserBloodavg(userId, today, before, 1); map.put("myavg",
	 * gongavg);
	 * 
	 * // 타인 int ogongavg = ublooddao.getotherBloodavg(today, before, 1);
	 * map.put("oavg", ogongavg); goaldata = ug.getGoalbMblood();
	 * 
	 * /// 타사용자대비 code = wkpointer.code1(gongavg, ogongavg); code2 =
	 * wkpointer.code2(goaldata, gongavg);
	 * 
	 * // 식후 } else if (kind == 2) {
	 * 
	 * int sikavg = ublooddao.getUserBloodavg(userId, today, before, 2);
	 * map.put("myavg", sikavg); // 타인 int osikavg =
	 * ublooddao.getotherBloodavg(today, before, 2); map.put("oavg", osikavg);
	 * 
	 * goaldata = ug.getGoalEblood(); code = wkpointer.code1(sikavg, osikavg);
	 * code2 = wkpointer.code2(goaldata, sikavg);
	 * 
	 * // 취침전 } else {
	 * 
	 * int sleepavg = ublooddao.getUserBloodavg(userId, today, before, 3);
	 * map.put("myavg", sleepavg);
	 * 
	 * // 타인취침전리스트 int osleepavg = ublooddao.getotherBloodavg(today, before, 3);
	 * map.put("oavg", osleepavg);
	 * 
	 * goaldata = ug.getGoalSblood(); code = wkpointer.code1(sleepavg,
	 * osleepavg); code2 = wkpointer.code2(goaldata, sleepavg);
	 * 
	 * } } if (dId.equals("press")) { // 내꺼
	 * 
	 * int mydavg = upressdao.getUserPressavg(userId, today, before,
	 * "dplessure"); // 이완기평균 int mysavg = upressdao.getUserPressavg(userId,
	 * today, before, "splessure"); // 수축기평균 map.put("mydavg", mydavg);
	 * map.put("mysavg", mysavg);
	 * 
	 * // 타인 int odavg = upressdao.getotherPressavg(today, before, "dplessure");
	 * // 이완기평균 int osavg = upressdao.getotherPressavg(today, before,
	 * "splessure"); // 수축기평균 map.put("odavg", odavg); map.put("osavg", osavg);
	 * 
	 * goaldata = ug.getGoalbPre();
	 * 
	 * code = wkpointer.code1(mydavg, odavg); code2 = wkpointer.code2(goaldata,
	 * mydavg);
	 * 
	 * } if (dId.equals("weight")) {
	 * 
	 * // 내꺼 double mybmiavg = uweightdao.getUserWeighavg(userId, today,
	 * before); // 평균 map.put("myavg", mybmiavg); // 타인 double obmiavg =
	 * uweightdao.getotherWeighavg(today, before); // 평균 map.put("oavg",
	 * obmiavg);
	 * 
	 * goaldata = ug.getGoalbBmi();
	 * 
	 * code = code3(mybmiavg, obmiavg); code2 = code4(goaldata, mybmiavg);
	 * 
	 * } Analisys an = anlisysdao.getAnalisys(code); String comment =
	 * an.getComment(); map.put("firstcomment", comment); Analisys an2 =
	 * anlisysdao.getAnalisys(code2); String comment2 = an2.getComment();
	 * map.put("secondcomment", comment2); map.put("result", true);
	 * map.put("goal", goaldata); } } catch (Exception e) { map.put("result",
	 * false); map.put("msg", "데이터가 없어 비교할수 없습니다.");
	 * 
	 * } JSONObject jsonObject = JSONObject.fromObject(map);
	 * Response.responseWrite(res, jsonObject);
	 * 
	 * return null;
	 * 
	 * }
	 */

	// 혈당차트
	@RequestMapping("/m/chartblood.go")
	public String chartbController(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "kind", required = false, defaultValue = "0") int kind,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, HttpServletRequest req,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		String today = T.getToday(); // "2015-10-12"
		String before = ""; // 기간세팅
		before = T.getDateAdd(today, -30);
		String ym = today.substring(0, 7);

		map.put("gongavg", ublooddao.getUserBloodavg(userId, ym, 1));
		map.put("eatavg", ublooddao.getUserBloodavg(userId, ym, 2));
		map.put("sleepavg", ublooddao.getUserBloodavg(userId, ym, 3));

		List<UserBlood> list = new ArrayList();

		list = ublooddao.getUserBlood(userId, kind, page, ITEM_COUNT_PER_PAGE);
		map.put("list", list);
		map.put("cnt", ublooddao.getBloodcnt(userId, kind));

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;

	}

	// 차트
	@RequestMapping("/m/chart/{dId}.go")
	public String chartController(@PathVariable("dId") String dId,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, HttpServletRequest req,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		String today = T.getToday(); // "2015-10-12"
		String before = ""; // 기간세팅
		String ym = today.substring(0, 7);
		before = T.getDateAdd(today, -30);

		if (dId.equals("pressure")) {
			map.put("dpress", upressdao.getUserPressavg(userId, ym, "dplessure"));// 이완기
			map.put("spress", upressdao.getUserPressavg(userId, ym, "splessure"));// 수축기
			List<UserPress> list = new ArrayList();
			list = upressdao.getUserPress(userId, page, ITEM_COUNT_PER_PAGE);
			map.put("cnt", upressdao.getUserPresscnt(userId));
			map.put("list", list);
		} else if (dId.equals("weight")) {
			map.put("weightavg", uweightdao.getUseravg(userId, ym));
			map.put("bmiavg", uweightdao.getUserWeighavg(userId, ym));
			List<UserWeight> list = new ArrayList();
			list = uweightdao.getUserWeight(userId, page, ITEM_COUNT_PER_PAGE);
			map.put("list", list);
			map.put("cnt", uweightdao.getUserWeightcnt(userId));
		} else if (dId.equals("col")) {
			UserCol uc= ucoldao.getUserdesc(userId);
			map.put("avgCol", uc.getCol());
			map.put("avgLdl", uc.getLdl());
			map.put("avgTg", uc.getTg());
			map.put("avgHdl", uc.getHdl());
			List<UserCol> list = new ArrayList();
			list = ucoldao.getUserCol(userId, page, ITEM_COUNT_PER_PAGE);
			map.put("list", list);
			map.put("cnt", ucoldao.getUserColcnt(userId));
		} else if (dId.equals("hba")) {
			Double avg = (Double) uhbdao.getUserdesc(userId);
			map.put("hbaavg", avg);
			List<Userhb> list = new ArrayList();
			list = uhbdao.getUserHba(userId, page, ITEM_COUNT_PER_PAGE);
			map.put("list", list);
			map.put("cnt", uhbdao.getUserHbacnt(userId));
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;

	}

	// 차트삭제
	@RequestMapping("/m/delete/{dId}.go")
	public String chartdelController(@PathVariable("dId") String dId,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "Seq", required = false, defaultValue = "") int Seq, HttpServletRequest req,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		if (dId.equals("blood")) {
			ublooddao.deleteUserBlood(Seq);
		} else if (dId.equals("pressure")) {
			upressdao.deleteUserPress(Seq);

		} else if (dId.equals("weight")) {
			uweightdao.deleteUserWeight(Seq);

		} else if (dId.equals("col")) {
			ucoldao.deleteUserCol(Seq);

		} else if (dId.equals("hba")) {
			uhbdao.deleteUserhb(Seq);
		}

		map.put("result", true);
		map.put("msg", "삭제되었습니다");
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;

	}

	// 그래프
	@RequestMapping("/m/g/{dId}.go")
	public String graController(@PathVariable("dId") String dId,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "speriod", required = false, defaultValue = "") String speriod, // 낮은숫자
			@RequestParam(value = "eperiod", required = false, defaultValue = "") String eperiod, // 높은숫자
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";
		ManageIndex id = mindexdao.getManageIndex();
		// UserGoal ug = ugoaldao.getuserGoal(userId);
		int period = T.getDateminus(eperiod, speriod);
		ArrayList list2 = new ArrayList();

		if (period < 31) {// 일짜별

			List dates = new ArrayList();
			for (int i = 0; i <= period; i++) {
				dates.add(T.getDateAdd(speriod, i));
			}

			if (dId.equals("blood")) {
				List<UserBlood> listEmpty = ublooddao.getUserBlood(userId, eperiod, speriod, 1);
				List<UserBlood> listEmpty2 = new ArrayList();
				for (int i = 0; i < dates.size(); i++) {
					boolean hasData = false;
					for (int j = 0; j < listEmpty.size(); j++) {
						UserBlood ub = (UserBlood) listEmpty.get(j);
						String date = ub.getRegDate().substring(0, 10);
						if (date.equals((String) dates.get(i))) {
							hasData = true;
							listEmpty2.add(i, ub);
							break;
						}
					}
					if (hasData == false) {
						UserBlood nd = new UserBlood();
						nd.setRegDate((String) dates.get(i));
						listEmpty2.add(i, nd);
					}
				}
				List<UserBlood> listAfterEat = ublooddao.getUserBlood(userId, eperiod, speriod, 2);
				List<UserBlood> listAfterEat2 = new ArrayList();
				for (int i = 0; i < dates.size(); i++) {
					boolean hasData = false;
					for (int j = 0; j < listAfterEat.size(); j++) {
						UserBlood ub = (UserBlood) listAfterEat.get(j);
						String date = ub.getRegDate().substring(0, 10);
						if (date.equals((String) dates.get(i))) {
							hasData = true;
							listAfterEat2.add(i, ub);
							break;
						}
					}
					if (hasData == false) {
						UserBlood nd = new UserBlood();
						nd.setRegDate((String) dates.get(i));
						listAfterEat2.add(i, nd);
					}
				}
				List<UserBlood> listBeforeSleep = ublooddao.getUserBlood(userId, eperiod, speriod, 3);
				List<UserBlood> listBeforeSleep2 = new ArrayList();
				for (int i = 0; i < dates.size(); i++) {
					boolean hasData = false;
					for (int j = 0; j < listBeforeSleep.size(); j++) {
						UserBlood ub = (UserBlood) listBeforeSleep.get(j);
						String date = ub.getRegDate().substring(0, 10);
						if (date.equals((String) dates.get(i))) {
							hasData = true;
							listBeforeSleep2.add(i, ub);
							break;
						}
					}
					if (hasData == false) {
						UserBlood nd = new UserBlood();
						nd.setRegDate((String) dates.get(i));
						listBeforeSleep2.add(i, nd);
					}
				}
				map.put("listEmpty", listEmpty2);
				map.put("listAfterEat", listAfterEat2);
				map.put("listBeforeSleep", listBeforeSleep2);

			} else if (dId.equals("pressure")) {
				List<UserPress> list = upressdao.getUserPress(userId, eperiod, speriod);
				List<UserPress> list1 = new ArrayList();
				for (int i = 0; i < dates.size(); i++) {
					boolean hasData = false;
					for (int j = 0; j < list.size(); j++) {
						UserPress ub = (UserPress) list.get(j);
						String date = ub.getRegDate().substring(0, 10);
						if (date.equals((String) dates.get(i))) {
							hasData = true;
							list1.add(i, ub);
							break;
						}
					}
					if (hasData == false) {
						UserPress nd = new UserPress();
						nd.setRegDate((String) dates.get(i));
						list1.add(i, nd);
					}
				}
				map.put("list", list1);

			} else if (dId.equals("weight")) {
				List<UserWeight> list = uweightdao.getUserWeight2(userId, eperiod, speriod);
				List<UserWeight> list1 = new ArrayList();
				for (int i = 0; i < dates.size(); i++) {
					boolean hasData = false;
					for (int j = 0; j < list.size(); j++) {
						UserWeight ub = (UserWeight) list.get(j);
						String date = ub.getRegDate().substring(0, 10);
						if (date.equals((String) dates.get(i))) {
							hasData = true;
							list1.add(i, ub);
							break;
						}
					}
					if (hasData == false) {
						UserWeight nd = new UserWeight();
						nd.setRegDate((String) dates.get(i));
						list1.add(i, nd);
					}
				}
				map.put("list", list1);

			} else if (dId.equals("col")) {
				List<UserCol> list = ucoldao.getUserCol2(userId, eperiod, speriod);
				List<UserCol> list1 = new ArrayList();
				for (int i = 0; i < dates.size(); i++) {
					boolean hasData = false;
					for (int j = 0; j < list.size(); j++) {
						UserCol ub = (UserCol) list.get(j);
						String date = ub.getRegDate().substring(0, 10);
						if (date.equals((String) dates.get(i))) {
							hasData = true;
							list1.add(i, ub);
							break;
						}
					}
					if (hasData == false) {
						UserCol nd = new UserCol();
						nd.setRegDate((String) dates.get(i));
						list1.add(i, nd);
					}
				}
				map.put("list", list1);

			} else if (dId.equals("hba")) {
				List<Userhb> list = uhbdao.getUserHba2(userId, eperiod, speriod);
				List<Userhb> list1 = new ArrayList();
				for (int i = 0; i < dates.size(); i++) {
					boolean hasData = false;
					for (int j = 0; j < list.size(); j++) {
						Userhb ub = (Userhb) list.get(j);
						String date = ub.getRegDate().substring(0, 10);
						if (date.equals((String) dates.get(i))) {
							hasData = true;
							list1.add(i, ub);
							break;
						}
					}
					if (hasData == false) {
						Userhb nd = new Userhb();
						nd.setRegDate((String) dates.get(i));
						list1.add(i, nd);
					}
				}
				map.put("list", list1);
			}

		} else if (period >= 31 && period < 180) {// 주별

			int startday = T.daytype(speriod); // 시작 날짜의 요일
			String weeknum = T.getWeekInMonth(speriod); // 시작날짜 몇주차
			String[] arr = speriod.split("-");
			String year = arr[0];
			String month = arr[1];
			String before = ""; // 시작날짜 전 일요일
			String after = ""; // 그주 토요일

			before = T.getDateAdd(speriod, -(startday - 1)); // 시작날짜 전 일요일
			after = T.getDateAdd(speriod, 6 - (startday - 1)); // 그주 토요일

			if (dId.equals("blood")) {
				List<UserBlood> listEmpty = new ArrayList();
				List<UserBlood> listAfterEat = new ArrayList();
				List<UserBlood> listBeforeSleep = new ArrayList();

				boolean notFinished = true;
				while (notFinished) {

					int avg = ublooddao.getUserBloodavg(userId, after, before, 1);// 1주차
																					// 아침공복
					int avg2 = ublooddao.getUserBloodavg(userId, after, before, 2);
					int avg3 = ublooddao.getUserBloodavg(userId, after, before, 3);

					UserBlood ub = new UserBlood();
					ub.setBloodNum(avg);
					ub.setRegDate(year + "-" + month + "-" + weeknum + "주");
					listEmpty.add(ub);

					UserBlood ub2 = new UserBlood();
					ub2.setBloodNum(avg2);
					ub2.setRegDate(year + "-" + month + "-" + weeknum + "주");
					listAfterEat.add(ub2);

					UserBlood ub3 = new UserBlood();
					ub3.setBloodNum(avg3);
					ub3.setRegDate(year + "-" + month + "-" + weeknum + "주");
					listBeforeSleep.add(ub3);

					map.put("listEmpty", listEmpty);
					map.put("listAfterEat", listAfterEat);
					map.put("listBeforeSleep", listBeforeSleep);

					before = T.getDateAdd(after, 1); // 그 다음주 일요일
					after = T.getDateAdd(after, 7);
					arr = before.split("-");
					year = arr[0];
					month = arr[1];
					weeknum = T.getWeekInMonth(before);

					if (T.getDateminus(eperiod, T.getDateAdd(after, -6)) < 0) {
						notFinished = false;
					}
				}

			} else if (dId.equals("pressure")) {
				boolean notFinished = true;
				while (notFinished) {
					HashMap hm = new HashMap();
					int avg = upressdao.getUserPressavg(userId, after, before, "dplessure");// 1주차
					int avg2 = upressdao.getUserPressavg(userId, after, before, "splessure");// 1주차
					hm.put("date", year + "-" + month + "-" + weeknum + "주");
					hm.put("dplessure", avg);
					hm.put("splessure", avg);

					list2.add(hm);
					before = T.getDateAdd(after, 1); // 그 다음주 일요일
					after = T.getDateAdd(after, 7);
					arr = before.split("-");
					year = arr[0];
					month = arr[1];
					weeknum = T.getWeekInMonth(before);

					if (T.getDateminus(eperiod, T.getDateAdd(after, -6)) < 0) {
						notFinished = false;
					}
				}

				map.put("list", list2);

			} else if (dId.equals("weight")) {

				boolean notFinished = true;
				while (notFinished) {
					HashMap hm = new HashMap();
					double avg = uweightdao.getUserWeighavg(userId, after, before);// 1주차
					int avg2 = uweightdao.getUseravg(userId, after, before);
					hm.put("date", year + "-" + month + "-" + weeknum + "주");
					hm.put("bmi", avg);
					hm.put("weightNum", avg2);
					list2.add(hm);
					before = T.getDateAdd(after, 1); // 그 다음주 일요일
					after = T.getDateAdd(after, 7);
					arr = before.split("-");
					year = arr[0];
					month = arr[1];
					weeknum = T.getWeekInMonth(before);

					if (T.getDateminus(eperiod, T.getDateAdd(after, -6)) < 0) {
						notFinished = false;
					}
				}

				map.put("list", list2);

			} else if (dId.equals("col")) {
				boolean notFinished = true;
				while (notFinished) {
					HashMap hm = new HashMap();
					int avg = ucoldao.getUserColavg(userId, after, before, "col");
					int avg2 = ucoldao.getUserColavg(userId, after, before, "hdl");
					int avg3 = ucoldao.getUserColavg(userId, after, before, "ldl");
					int avg4 = ucoldao.getUserColavg(userId, after, before, "tg");
					hm.put("date", year + "-" + month + "-" + weeknum + "주");
					hm.put("col", avg);
					hm.put("hdl", avg2);
					hm.put("ldl", avg3);
					hm.put("tg", avg4);
					list2.add(hm);
					before = T.getDateAdd(after, 1); // 그 다음주 일요일
					after = T.getDateAdd(after, 7);
					arr = before.split("-");
					year = arr[0];
					month = arr[1];
					weeknum = T.getWeekInMonth(before);

					if (T.getDateminus(eperiod, T.getDateAdd(after, -6)) < 0) {
						notFinished = false;
					}
				}

				map.put("list", list2);

			} else if (dId.equals("hba")) {
				boolean notFinished = true;
				while (notFinished) {
					HashMap hm = new HashMap();
					int avg = uhbdao.getUserHbaavg(userId, after, before);

					hm.put("date", year + "-" + month + "-" + weeknum + "주");
					hm.put("hbaNum", avg);

					list2.add(hm);
					before = T.getDateAdd(after, 1); // 그 다음주 일요일
					after = T.getDateAdd(after, 7);
					arr = before.split("-");
					year = arr[0];
					month = arr[1];
					weeknum = T.getWeekInMonth(before);

					if (T.getDateminus(eperiod, T.getDateAdd(after, -6)) < 0) {
						notFinished = false;
					}
				}

				map.put("list", list2);
			}

		} else {// 월별

			String[] arr = T.getFirstAndFinishDayOfMonth(speriod);
			String startday = arr[0];
			String endday = arr[1];
			String[] arr2 = speriod.split("-");

			if (dId.equals("blood")) {
				boolean notFinished = true;
				List<UserBlood> listEmpty = new ArrayList();
				List<UserBlood> listAfterEat = new ArrayList();
				List<UserBlood> listBeforeSleep = new ArrayList();
				while (notFinished) {
					// HashMap hm = new HashMap();

					int avg = ublooddao.getUserBloodavg(userId, endday, startday, 1);// 1개월차
																						// 아침공복
					int avg2 = ublooddao.getUserBloodavg(userId, endday, startday, 2);
					int avg3 = ublooddao.getUserBloodavg(userId, endday, startday, 3);

					UserBlood ub = new UserBlood();
					ub.setBloodNum(avg);

					ub.setRegDate(arr2[0] + "-" + arr2[1]);
					listEmpty.add(ub);

					UserBlood ub2 = new UserBlood();
					ub2.setBloodNum(avg2);
					ub2.setRegDate(arr2[0] + "-" + arr2[1]);
					listAfterEat.add(ub2);

					UserBlood ub3 = new UserBlood();
					ub3.setBloodNum(avg3);
					ub3.setRegDate(arr2[0] + "-" + arr2[1]);
					listBeforeSleep.add(ub3);

					map.put("listEmpty", listEmpty);
					map.put("listAfterEat", listAfterEat);
					map.put("listBeforeSleep", listBeforeSleep);

					startday = T.getDateAdd(endday, 1); // 그 다음달 시작일
					arr = T.getFirstAndFinishDayOfMonth(startday);
					endday = arr[1];
					arr2 = startday.split("-");

					String[] arr3 = T.getFirstAndFinishDayOfMonth(eperiod);
					String finishday = arr3[1];
					if (T.getDateminus(T.getDateAdd(finishday, 1), endday) < 0) {
						notFinished = false;
					}
				}

			} else if (dId.equals("pressure")) {
				boolean notFinished = true;
				while (notFinished) {
					HashMap hm = new HashMap();

					int avg = upressdao.getUserPressavg(userId, endday, startday, "dplessure");// 1주차
					int avg2 = upressdao.getUserPressavg(userId, endday, startday, "splessure");// 1주차
					hm.put("date", arr2[0] + "-" + arr2[1]);

					hm.put("dplessure", avg);
					hm.put("splessure", avg);
					list2.add(hm);

					startday = T.getDateAdd(endday, 1); // 그 다음달 시작일
					arr = T.getFirstAndFinishDayOfMonth(startday);
					endday = arr[1];
					arr2 = startday.split("-");

					String[] arr3 = T.getFirstAndFinishDayOfMonth(eperiod);
					String finishday = arr3[1];
					if (T.getDateminus(T.getDateAdd(finishday, 1), endday) < 0) {
						notFinished = false;
					}
				}

				map.put("list", list2);
			} else if (dId.equals("col")) {
				boolean notFinished = true;
				while (notFinished) {
					HashMap hm = new HashMap();
					int avg = ucoldao.getUserColavg(userId, endday, startday, "col");
					int avg2 = ucoldao.getUserColavg(userId, endday, startday, "hdl");
					int avg3 = ucoldao.getUserColavg(userId, endday, startday, "ldl");
					int avg4 = ucoldao.getUserColavg(userId, endday, startday, "tg");

					hm.put("col", avg);
					hm.put("hdl", avg2);
					hm.put("ldl", avg3);
					hm.put("tg", avg4);
					hm.put("date", arr2[0] + "-" + arr2[1]);
					list2.add(hm);

					startday = T.getDateAdd(endday, 1); // 그 다음달 시작일
					arr = T.getFirstAndFinishDayOfMonth(startday);
					endday = arr[1];
					arr2 = startday.split("-");

					String[] arr3 = T.getFirstAndFinishDayOfMonth(eperiod);
					String finishday = arr3[1];
					if (T.getDateminus(T.getDateAdd(finishday, 1), endday) < 0) {
						notFinished = false;
					}
				}

				map.put("list", list2);
			} else if (dId.equals("weight")) {
				boolean notFinished = true;
				while (notFinished) {
					HashMap hm = new HashMap();
					double avg = uweightdao.getUserWeighavg(userId, endday, startday);// 1주차
					int avg2 = uweightdao.getUseravg(userId, endday, startday);

					hm.put("bmi", avg);
					hm.put("weightNum", avg2);
					hm.put("date", arr2[0] + "-" + arr2[1]);
					list2.add(hm);

					startday = T.getDateAdd(endday, 1); // 그 다음달 시작일
					arr = T.getFirstAndFinishDayOfMonth(startday);
					endday = arr[1];
					arr2 = startday.split("-");

					String[] arr3 = T.getFirstAndFinishDayOfMonth(eperiod);
					String finishday = arr3[1];
					if (T.getDateminus(T.getDateAdd(finishday, 1), endday) < 0) {
						notFinished = false;
					}
				}

				map.put("list", list2);
			} else if (dId.equals("hba")) {
				boolean notFinished = true;
				while (notFinished) {
					HashMap hm = new HashMap();
					int avg = uhbdao.getUserHbaavg(userId, endday, startday);

					hm.put("date", arr2[0] + "-" + arr2[1]);
					hm.put("hbaNum", avg);

					list2.add(hm);
					startday = T.getDateAdd(endday, 1); // 그 다음달 시작일
					arr = T.getFirstAndFinishDayOfMonth(startday);
					endday = arr[1];
					arr2 = startday.split("-");

					String[] arr3 = T.getFirstAndFinishDayOfMonth(eperiod);
					String finishday = arr3[1];
					if (T.getDateminus(T.getDateAdd(finishday, 1), endday) < 0) {
						notFinished = false;
					}
				}

				map.put("list", list2);
			}
		}

		if (dId.equals("blood")) {

			map.put("goalEmpty", id.getGoalSmblood() + "-" + id.getGoalBmblood());
			map.put("goalEat", id.getGoalEblood());
			map.put("goalSleep", id.getGoalSblood());

		} else if (dId.equals("pressure")) {
			map.put("goalsplessure", id.getGoalBpre());
			map.put("goaldplessure", id.getGoalSpre());

		} else if (dId.equals("col")) {
			map.put("goalcol", id.getGoalCol());
			map.put("goalldl", id.getGoalLdl());
			map.put("goalhdl", id.getGoalHdl());
			map.put("goaltg", id.getGoalTg());
		} else if (dId.equals("hba")) {
			map.put("goalhba", id.getGoalHba());

		} else if (dId.equals("weight")) {
			map.put("goalBmi", id.getGoalSbmi() + "-" + id.getGoalBbmi());
		}

		map.put("result", true);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;

	}

	// 데이터 백업
	@RequestMapping("/m/save_data.go")
	public String mSaveDataController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "isBloodSugar", required = false, defaultValue = "") int isBloodSugar,
			@RequestParam(value = "isPressure", required = false, defaultValue = "") int isPressure,
			@RequestParam(value = "isCholesterol", required = false, defaultValue = "") int isCholesterol,
			@RequestParam(value = "isWeight", required = false, defaultValue = "") int isWeight,
			@RequestParam(value = "isHemoglobin", required = false, defaultValue = "") int isHemoglobin,
			@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
			@RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		File bloodSugarExcel = null;
		File pressureExcel = null;
		File cholesterolExcel = null;
		File weightExcel = null;
		File hemoglobinExcel = null;

		String bloodSugarFile = "";
		String pressureFile = "";
		String cholesterolFile = "";
		String weightFile = "";
		String hemoglobinFile = "";

		String bloodSugarFileName = "";
		String pressureFileName = "";
		String cholesterolFileName = "";
		String weightFileName = "";
		String hemoglobinFileName = "";
		
		User user = userdao.getUser(userId);

		// 혈당 데이터
		if (isBloodSugar == 1) {
			List bsList = ublooddao.getUserBlood(userId, endDate, startDate);

			SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileName = formatdate.format(new Date()) + "_" + userId + "_bloodsugar";
			XlsxWriter writer = new XlsxWriter(FILE_ROOT + "/files/excel/" + fileName + ".xls");

			List title = new ArrayList();
			List contents = new ArrayList();

			title.add("이름");
			title.add("복약시간");
			title.add("복약구분");
			title.add("혈당수치");
			title.add("기록일시");

			for (int i = 0; i < bsList.size(); i++) {
				UserBlood ub = (UserBlood) bsList.get(i);
				String bloodTime = "";
				String bloodKind = "";
				if (ub.getBloodTime() == 1) {
					bloodTime = "아침";
				} else if (ub.getBloodTime() == 2) {
					bloodTime = "점심";
				} else if (ub.getBloodTime() == 3) {
					bloodTime = "저녁";
				}
				if (ub.getBloodKind() == 1) {
					bloodKind = "공복";
				} else if (ub.getBloodKind() == 2) {
					bloodKind = "식후";
				} else if (ub.getBloodKind() == 3) {
					bloodKind = "취침전";
				}

				List dataList = new ArrayList();
				dataList.add(user.getUserName());
				dataList.add(bloodTime);
				dataList.add(bloodKind);
				dataList.add(ub.getBloodNum());
				dataList.add(ub.getRegDate());

				contents.add(dataList);
			}

			writer.writeFile(title, contents);
			bloodSugarExcel = new File(FILE_ROOT + "/files/excel/" + fileName + ".xls");

			bloodSugarFile = FILE_ROOT + "/files/excel/" + fileName + ".xls";
			bloodSugarFileName = fileName;
			message += "혈당 데이터";
		}

		// 혈압 데이터
		if (isPressure == 1) {
			List bpList = upressdao.getUserPress(userId, endDate, startDate);

			SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileName = formatdate.format(new Date()) + "_" + userId + "_bloodpressure";
			XlsxWriter writer = new XlsxWriter(FILE_ROOT + "/files/excel/" + fileName + ".xls");

			List title = new ArrayList();
			List contents = new ArrayList();

			title.add("이름");
			title.add("수축기");
			title.add("이완기");
			title.add("맥박");
			title.add("기록일시");

			for (int i = 0; i < bpList.size(); i++) {
				UserPress up = (UserPress) bpList.get(i);

				List dataList = new ArrayList();
				dataList.add(user.getUserName());
				dataList.add(up.getPulse());
				dataList.add(up.getSplessure());
				dataList.add(up.getDplessure());
				dataList.add(up.getRegDate());
				contents.add(dataList);
			}

			writer.writeFile(title, contents);
			pressureExcel = new File(FILE_ROOT + "/files/excel/" + fileName + ".xls");

			pressureFile = FILE_ROOT + "/files/excel/" + fileName + ".xls";
			pressureFileName = fileName;

			message += " 혈압 데이터";
		}

		// 콜레스테롤
		if (isCholesterol == 1) {
			List bcList = ucoldao.getUserCol(userId, endDate, startDate);

			SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileName = formatdate.format(new Date()) + "_" + userId + "_cholesterol";
			XlsxWriter writer = new XlsxWriter(FILE_ROOT + "/files/excel/" + fileName + ".xls");

			List title = new ArrayList();
			List contents = new ArrayList();

			title.add("이름");
			title.add("총 콜레스테롤");
			title.add("저밀도 콜레스테롤");
			title.add("중성지방");
			title.add("고밀도 콜레스테롤");
			title.add("기록일시");

			for (int i = 0; i < bcList.size(); i++) {
				UserCol uc = (UserCol) bcList.get(i);

				List dataList = new ArrayList();
				dataList.add(user.getUserName());
				dataList.add(uc.getCol());
				dataList.add(uc.getLdl());
				dataList.add(uc.getTg());
				dataList.add(uc.getHdl());
				dataList.add(uc.getRegDate());

				contents.add(dataList);
			}

			writer.writeFile(title, contents);
			cholesterolExcel = new File(FILE_ROOT + "/files/excel/" + fileName + ".xls");

			cholesterolFile = FILE_ROOT + "/files/excel/" + fileName + ".xls";
			cholesterolFileName = fileName;

			message += "콜레스테롤 데이터";
		}

		// 체중
		if (isWeight == 1) {
			List uwList = uweightdao.getUserWeight(userId, endDate, startDate);

			SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileName = formatdate.format(new Date()) + "_" + userId + "_weight";
			XlsxWriter writer = new XlsxWriter(FILE_ROOT + "/files/excel/" + fileName + ".xls");

			List title = new ArrayList();
			List contents = new ArrayList();

			title.add("이름");
			title.add("체중");
			title.add("BMI");
			title.add("체지방");
			title.add("체수분");
			title.add("근육");
			title.add("골량");
			title.add("기록일시");

			for (int i = 0; i < uwList.size(); i++) {
				UserWeight uw = (UserWeight) uwList.get(i);

				List dataList = new ArrayList();
				dataList.add(user.getUserName());
				dataList.add(uw.getWeightNum());
				dataList.add(uw.getBmi());
				dataList.add(uw.getBbmi());
				dataList.add(uw.getTbw());
				dataList.add(uw.getMuscle());
				dataList.add(uw.getBmd());
				dataList.add(uw.getRegDate());

				contents.add(dataList);
			}

			writer.writeFile(title, contents);
			weightExcel = new File(FILE_ROOT + "/files/excel/" + fileName + ".xls");

			weightFile = FILE_ROOT + "/files/excel/" + fileName + ".xls";
			weightFileName = fileName;

			message += "체중 데이터";
		}

		// 당화혈색소
		if (isHemoglobin == 1) {
			List hbList = uhbdao.getUserHba(userId, endDate, startDate);

			SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileName = formatdate.format(new Date()) + "_" + userId + "_hemoglobin";
			XlsxWriter writer = new XlsxWriter(FILE_ROOT + "/files/excel/" + fileName + ".xls");

			List title = new ArrayList();
			List contents = new ArrayList();

			title.add("이름");
			title.add("수치");
			title.add("기록일시");

			for (int i = 0; i < hbList.size(); i++) {
				Userhb hb = (Userhb) hbList.get(i);

				List dataList = new ArrayList();
				dataList.add(user.getUserName());
				dataList.add(hb.getHbaNum());
				dataList.add(hb.getRegDate());

				contents.add(dataList);
			}

			writer.writeFile(title, contents);
			hemoglobinExcel = new File(FILE_ROOT + "/files/excel/" + fileName + ".xls");

			hemoglobinFile = FILE_ROOT + "/files/excel/" + fileName + ".xls";
			hemoglobinFileName = fileName;

			message += "당화혈색소 데이터";
		}

		// 메일 관련
		try {

			// 메일 관련 정보
			String host = "smtp.gmail.com";
			String username = this.GOOGLE_MAIL_ID; // "지메일아이디@gmail.com";
			String password = this.GOOGLE_MAIL_PW; // "비밀번호";

			// 메일 내용
			String recipient = userId; // "수신자 메일주소";

			// 2. 파일을 첨부한다.
			/*
			 * bloodSugarFile; pressureFile; cholesterolFile; weightFile;
			 * hemoglobinFile;
			 */
			boolean hasFile = false;
			String subject = "Recover 데이터 백업"; // "지메일을 사용한 발송 테스트입니다.";
			String body = "" + "안녕하세요. Recover 입니다.<br>" + "" + userId + "님의 측정 지수 데이터입니다.<br>첨부된 파일을 확인하세요.";

			// properties 설정
			Properties props = new Properties();
			props.put("mail.smtps.auth", "true");
			// 메일 세션
			Session session = Session.getDefaultInstance(props);
			MimeMessage msg = new MimeMessage(session);

			msg.setSubject(subject);
			msg.setText(body);
			msg.setFrom(new InternetAddress(username));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

			// 파일 첨부시에는 BodyPart를 사용
			// msg.setText(body);

			// 파일첨부를 위한 Multipart
			Multipart multipart = new MimeMultipart();
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setText(body);
			multipart.addBodyPart(bodyPart);

			if (bloodSugarFile.equals("") == false) {
				// BodyPart를 생성
				BodyPart bodyPartAdd = new MimeBodyPart();
				//bodyPart.setText(body);
				String filename = bloodSugarFile;
				DataSource source = (DataSource) new FileDataSource(filename);
				bodyPartAdd.setDataHandler(new DataHandler((javax.activation.DataSource) source));
				bodyPartAdd.setFileName(bloodSugarFileName+".xls");
				multipart.addBodyPart(bodyPartAdd);
				hasFile = true;
			}
			if (pressureFile.equals("") == false) {
				// BodyPart를 생성
				BodyPart bodyPartAdd = new MimeBodyPart();
				//bodyPart.setText(body);
				String filename = pressureFile;
				DataSource source = (DataSource) new FileDataSource(filename);
				bodyPartAdd.setDataHandler(new DataHandler((javax.activation.DataSource) source));
				bodyPartAdd.setFileName(pressureFileName+".xls");
				multipart.addBodyPart(bodyPartAdd);
				hasFile = true;
			}
			if (cholesterolFile.equals("") == false) {
				// BodyPart를 생성
				BodyPart bodyPartAdd = new MimeBodyPart();
				//bodyPart.setText(body);
				String filename = cholesterolFile;
				DataSource source = (DataSource) new FileDataSource(filename);
				bodyPartAdd.setDataHandler(new DataHandler((javax.activation.DataSource) source));
				bodyPartAdd.setFileName(cholesterolFileName+".xls");
				multipart.addBodyPart(bodyPartAdd);
				hasFile = true;
			}
			if (weightFile.equals("") == false) {
				// BodyPart를 생성
				BodyPart bodyPartAdd = new MimeBodyPart();
				//bodyPart.setText(body);
				String filename = weightFile;
				DataSource source = (DataSource) new FileDataSource(filename);
				bodyPartAdd.setDataHandler(new DataHandler((javax.activation.DataSource) source));
				bodyPartAdd.setFileName(weightFileName+".xls");
				multipart.addBodyPart(bodyPartAdd);
				hasFile = true;
			}
			if (hemoglobinFile.equals("") == false) {
				// BodyPart를 생성
				BodyPart bodyPartAdd = new MimeBodyPart();
				//bodyPart.setText(body);
				String filename = hemoglobinFile;
				DataSource source = (DataSource) new FileDataSource(filename);
				bodyPartAdd.setDataHandler(new DataHandler((javax.activation.DataSource) source));
				bodyPartAdd.setFileName(hemoglobinFileName+".xls");
				multipart.addBodyPart(bodyPartAdd);
				hasFile = true;

			}
			// 이메일 메시지의 내용에 Multipart를 붙인다.
			msg.setContent(multipart);

			// 발송 처리
			Transport transport = session.getTransport("smtps");
			transport.connect(host, username, password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();

		} catch (MessagingException e) {
			map.put("result", false);
			map.put("message", "mail send error : " + e.getMessage());
		}

		/*
		 * String contents = "" + "<p>안녕하세요. Recover 입니다.<br>" + ""+userId+
		 * "님의 측정 지수 자료입니다.</p>";
		 * 
		 * String host = "smtp.gmail.com"; String subject = "측정 지수 자료 안내";
		 * String from = this.GOOGLE_MAIL_ID; String fromPassword =
		 * this.GOOGLE_MAIL_PW; String fromName = "Recover 관리자"; //List userList
		 * = userDao.getUserList(userId);
		 * 
		 * try{ InternetAddress[] address = new InternetAddress[1]; //String to
		 * = "minsuk818@naver.com"; address[0]=new InternetAddress(userId);
		 * 
		 * //프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정) Properties props = new
		 * Properties(); //G-Mail SMTP 사용시
		 * props.put("mail.smtp.starttls.enable","true");
		 * props.put("mail.transport.protocol","smtp");
		 * props.put("mail.smtp.host", host);
		 * //props.setProperty("mail.smtp.socketFactory.class",
		 * "javax.net.ssl.SSLSocktFactory"); //암호화된 메일 보낼때 사용(지원하는곳만사용가능)
		 * props.put("mail.smtp.port","25"); props.put("mail.smtp.user", from);
		 * props.put("mail.smtp.auth","true"); //MyAuthenticator auth = new
		 * MyAuthenticator("cms82818@gmail.com",""); MyAuthenticator auth = new
		 * MyAuthenticator(from,fromPassword); Session mailSession =
		 * Session.getDefaultInstance(props,auth);
		 * 
		 * Message msg = new MimeMessage(mailSession); msg.setFrom(new
		 * InternetAddress(from, MimeUtility.encodeText(fromName,"UTF-8","B")));
		 * //보내는 사람 설정
		 * 
		 * msg.setRecipients(Message.RecipientType.TO, address); //받는 사람설정
		 * msg.setSubject(subject); //제목설정 msg.setSentDate(new
		 * java.util.Date()); //보내는 날짜 설정
		 * 
		 * msg.setContent(contents,"text/html; charset=EUC-KR"); //내용 설정(MIME
		 * 지정-HTML 형식)
		 * 
		 * Transport.send(msg); //메일 보내기 map.put("result", true);
		 * map.put("message", "측정 지수 데이터 발송을 완료하였습니다."); } catch (Exception e1)
		 * { map.put("result", false); map.put("message", "mail send error : "
		 * +e1.getMessage()); }
		 */

		map.put("result", true);
		map.put("msg", message + "가 발송되었습니다.");
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;

	}

	/* 버전확인 */
	@RequestMapping("/m/now_version.go")
	public String nowVersionController(HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			Config config = configdao.getConfig();
			String version = config.getAppVersion();
			map.put("version", version);
			map.put("result", true);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	@RequestMapping("/m/send_push.go")
	public String mSendPushController(
			/*@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "msg", required = false, defaultValue = "") String msg, 
			@RequestParam(value = "pushType", required = false, defaultValue = "") String pushType,*/
			HttpServletRequest req,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			
			//User user = userdao.getUser(userId);
			
			String today = T.getToday();
			List list =userdao.getUserListcvrisk(today);
			String message = "CV-RISK 를 등록해 주세요";
			for (int i=0; i<list.size(); i++) {
				User user = (User) list.get(i);
				
				if (user.getPushkey().equals("") == false) {
					System.out.println(user.getUserId());
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
/*
			if (user.getPushkey().equals("") == false) {

				Push push = new Push();
				push.setBadge(1);
				push.setOs(user.getOsType());
				push.setPushKey(user.getPushkey());
				push.setMsgType(pushType);
				push.setUserid(user.getUserId());
				push.setStatus(0);
				push.setServiceId("RECOVER");
				push.setPushType(1);
				push.setMsg(msg);
				push.setMsgKey("0");
				pushDao.addPush(push);

				map.put("message", "푸시발송되었습니다. 수신 여부를 체트하세요.");
				map.put("userId", userId);
				map.put("pushKey", user.getPushkey());
				map.put("result", true);
			} else {
				map.put("message", "푸시정보가 없는 사용자입니다.");
				map.put("userId", userId);
				map.put("pushKey", user.getPushkey());
				map.put("result", false);
			}*/

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	@RequestMapping("/m/send_push_test.go")
	public String mSendPushTestController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "msg", required = false, defaultValue = "") String msg, 
			HttpServletRequest req,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			
			User user = userdao.getUser(userId);
			
			
			if (user.getPushkey().equals("") == false) {
				System.out.println(user.getUserId());
				Push push = new Push();
				push.setBadge(1);
				push.setOs(user.getOsType());
				push.setPushKey(user.getPushkey());
				push.setMsgType(Push.MSG_TYPE_EAT_MED);
				push.setUserid(user.getUserId());
				push.setStatus(0);
				push.setServiceId("RECOVER");
				push.setPushType(1);							
				push.setMsg(msg);
				push.setMsgKey("0");
				pushDao.addPush(push);
				
			}

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	/* 푸시 클리어 */
	@RequestMapping("/m/clear_noticeBadge.go")
	public String noticeBadgeController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			
			userdao.updateBadge(userId, 0);
			
			map.put("result", true);
			
		} catch (Exception e) {
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	
	
	/* 그래프 */
	@RequestMapping("/m/grape_do.go")
	public String grapedoController(
			@RequestParam(value = "diseaseId", required = false, defaultValue = "") String dId,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "speriod", required = false, defaultValue = "") String speriod, // 낮은숫자
			@RequestParam(value = "eperiod", required = false, defaultValue = "") String eperiod, // 높은숫자
			
			HttpServletRequest req, HttpServletResponse res,Model model) {

		
		String message = "";
		ManageIndex id = mindexdao.getManageIndex();
		// UserGoal ug = ugoaldao.getuserGoal(userId);
		int period = T.getDateminus(eperiod, speriod);
		ArrayList list2 = new ArrayList();

		if (period < 31) {// 일짜별

			List dates = new ArrayList();
			for (int i = 0; i <= period; i++) {
				dates.add(T.getDateAdd(speriod, i));
			}

			if (dId.equals("blood")) {
				List<UserBlood> listEmpty = ublooddao.getUserBlood(userId, eperiod, speriod, 1);
				List<UserBlood> listEmpty2 = new ArrayList();
				
				for (int i = 0; i < dates.size(); i++) {
					boolean hasData = false;
					for (int j = 0; j < listEmpty.size(); j++) {
						UserBlood ub = (UserBlood) listEmpty.get(j);
						if (ub == null || ub.getRegDate().equals("")) {
							break;
						} else {
							String date = "";
							if (ub.getRegDate().length() >= 10) {
								date = ub.getRegDate().substring(0, 10);
							}
							if (date.equals((String) dates.get(i))) {
								hasData = true;
								
								listEmpty2.add(i, ub);
								break;
							}
						}
					}
					if (hasData == false) {
						UserBlood nd = new UserBlood();
						nd.setRegDate((String) dates.get(i));
						listEmpty2.add(i, nd);
					}
				}
				
				List<UserBlood> listAfterEat = ublooddao.getUserBlood(userId, eperiod, speriod, 2);
				List<UserBlood> listAfterEat2 = new ArrayList();
				for (int i = 0; i < dates.size(); i++) {
					boolean hasData = false;
					for (int j = 0; j < listAfterEat.size(); j++) {
						UserBlood ub = (UserBlood) listAfterEat.get(j);
						String date = ub.getRegDate().substring(0, 10);
						if (date.equals((String) dates.get(i))) {
							hasData = true;
							listAfterEat2.add(i, ub);
							break;
						}
					}
					if (hasData == false) {
						UserBlood nd = new UserBlood();
						nd.setRegDate((String) dates.get(i));
						listAfterEat2.add(i, nd);
					}
				}
				List<UserBlood> listBeforeSleep = ublooddao.getUserBlood(userId, eperiod, speriod, 3);
				List<UserBlood> listBeforeSleep2 = new ArrayList();
				for (int i = 0; i < dates.size(); i++) {
					boolean hasData = false;
					for (int j = 0; j < listBeforeSleep.size(); j++) {
						UserBlood ub = (UserBlood) listBeforeSleep.get(j);
						if (ub == null || ub.getRegDate().equals("")) {
							break;
						} else {
							String date = "";
							if (ub.getRegDate().length() >= 10) {
								date = ub.getRegDate().substring(0, 10);
							}
							if (date.equals((String) dates.get(i))) {
								hasData = true;
								listBeforeSleep2.add(i, ub);
								break;
							}
						}
					}
					if (hasData == false) {
						UserBlood nd = new UserBlood();
						nd.setRegDate((String) dates.get(i));
						listBeforeSleep2.add(i, nd);
					}
				}
				
			
				model.addAttribute("listEmpty", listEmpty2);
				model.addAttribute("listAfterEat", listAfterEat2);
				model.addAttribute("listBeforeSleep", listBeforeSleep2);

			} else if (dId.equals("pressure")) {
				List<UserPress> list = upressdao.getUserPress(userId, eperiod, speriod);
				List<UserPress> list1 = new ArrayList();
				for (int i = 0; i < dates.size(); i++) {
					boolean hasData = false;
					for (int j = 0; j < list.size(); j++) {
						UserPress ub = (UserPress) list.get(j);
						if (ub == null || ub.getRegDate().equals("")) {
							break;
						} else {
							String date = "";
							if (ub.getRegDate().length() >= 10) {
								date = ub.getRegDate().substring(0, 10);
							}
							if (date.equals((String) dates.get(i))) {
								hasData = true;
								list1.add(i, ub);
								break;
							}
						}
					}
					if (hasData == false) {
						UserPress nd = new UserPress();
						nd.setRegDate((String) dates.get(i));
						list1.add(i, nd);
					}
				}
				model.addAttribute("list", list1);
			
			} else if (dId.equals("weight")) {
				List<UserWeight> list = uweightdao.getUserWeight2(userId, eperiod, speriod);
				List<UserWeight> list1 = new ArrayList();
				for (int i = 0; i < dates.size(); i++) {
					boolean hasData = false;
					for (int j = 0; j < list.size(); j++) {
						UserWeight ub = (UserWeight) list.get(j);
						if (ub == null || ub.getRegDate().equals("")) {
							break;
						} else {
							String date = "";
							if (ub.getRegDate().length() >= 10) {
								date = ub.getRegDate().substring(0, 10);
							}
							if (date.equals((String) dates.get(i))) {
								hasData = true;
								list1.add(i, ub);
								break;
							}
						}
					}
					if (hasData == false) {
						UserWeight nd = new UserWeight();
						nd.setRegDate((String) dates.get(i));
						list1.add(i, nd);
					}
				}
				model.addAttribute("list", list1);
			
			} else if (dId.equals("col")) {
				List<UserCol> list = ucoldao.getUserCol2(userId, eperiod, speriod);
				List<UserCol> list1 = new ArrayList();
				for (int i = 0; i < dates.size(); i++) {
					boolean hasData = false;
					for (int j = 0; j < list.size(); j++) {
						UserCol ub = (UserCol) list.get(j);
						if (ub == null || ub.getRegDate().equals("")) {
							break;
						} else {
							String date = "";
							if (ub.getRegDate().length() >= 10) {
								date = ub.getRegDate().substring(0, 10);
							}
							if (date.equals((String) dates.get(i))) {
								hasData = true;
								list1.add(i, ub);
								break;
							}
						}
					}
					if (hasData == false) {
						UserCol nd = new UserCol();
						nd.setRegDate((String) dates.get(i));
						list1.add(i, nd);
					}
				}
				model.addAttribute("list", list1);
			

			} else if (dId.equals("hba")) {
				List<Userhb> list = uhbdao.getUserHba2(userId, eperiod, speriod);
				List<Userhb> list1 = new ArrayList();
				for (int i = 0; i < dates.size(); i++) {
					boolean hasData = false;
					for (int j = 0; j < list.size(); j++) {
						Userhb ub = (Userhb) list.get(j);
						if (ub == null || ub.getRegDate().equals("")) {
							break;
						} else {
							String date = "";
							if (ub.getRegDate().length() >= 10) {
								date = ub.getRegDate().substring(0, 10);
							}
							if (date.equals((String) dates.get(i))) {
								hasData = true;
								list1.add(i, ub);
								break;
							}
						}
					}
					if (hasData == false) {
						Userhb nd = new Userhb();
						nd.setRegDate((String) dates.get(i));
						list1.add(i, nd);
					}
				}
				model.addAttribute("list", list1);
			
			}

		} else if (period >= 31 && period < 180) {// 주별

			int startday = T.daytype(speriod); // 시작 날짜의 요일
			String weeknum = T.getWeekInMonth(speriod); // 시작날짜 몇주차
			String[] arr = speriod.split("-");
			String year = arr[0];
			String month = arr[1];
			String before = ""; // 시작날짜 전 일요일
			String after = ""; // 그주 토요일

			before = T.getDateAdd(speriod, -(startday - 1)); // 시작날짜 전 일요일
			after = T.getDateAdd(speriod, 6 - (startday - 1)); // 그주 토요일

			if (dId.equals("blood")) {
				List<UserBlood> listEmpty = new ArrayList();
				List<UserBlood> listAfterEat = new ArrayList();
				List<UserBlood> listBeforeSleep = new ArrayList();

				boolean notFinished = true;
				while (notFinished) {

					int avg = ublooddao.getUserBloodavg(userId, after, before, 1);// 1주차
																					// 아침공복
					int avg2 = ublooddao.getUserBloodavg(userId, after, before, 2);
					int avg3 = ublooddao.getUserBloodavg(userId, after, before, 3);

					UserBlood ub = new UserBlood();
					ub.setBloodNum(avg);
					ub.setRegDate(year + "-" + month + "-" + weeknum + "주");
					listEmpty.add(ub);

					UserBlood ub2 = new UserBlood();
					ub2.setBloodNum(avg2);
					ub2.setRegDate(year + "-" + month + "-" + weeknum + "주");
					listAfterEat.add(ub2);

					UserBlood ub3 = new UserBlood();
					ub3.setBloodNum(avg3);
					ub3.setRegDate(year + "-" + month + "-" + weeknum + "주");
					listBeforeSleep.add(ub3);

					model.addAttribute("listEmpty", listEmpty);
					model.addAttribute("listAfterEat", listAfterEat);
					model.addAttribute("listBeforeSleep", listBeforeSleep);
					

					before = T.getDateAdd(after, 1); // 그 다음주 일요일
					after = T.getDateAdd(after, 7);
					arr = before.split("-");
					year = arr[0];
					month = arr[1];
					weeknum = T.getWeekInMonth(before);

					if (T.getDateminus(eperiod, T.getDateAdd(after, -6)) < 0) {
						notFinished = false;
					}
				}
				
			

			} else if (dId.equals("pressure")) {
				boolean notFinished = true;
				while (notFinished) {
					HashMap hm = new HashMap();
					int avg = upressdao.getUserPressavg(userId, after, before, "dplessure");// 1주차
					int avg2 = upressdao.getUserPressavg(userId, after, before, "splessure");// 1주차
					hm.put("date", year + "-" + month + "-" + weeknum + "주");
					hm.put("dplessure", avg);
					hm.put("splessure", avg);

					list2.add(hm);
					before = T.getDateAdd(after, 1); // 그 다음주 일요일
					after = T.getDateAdd(after, 7);
					arr = before.split("-");
					year = arr[0];
					month = arr[1];
					weeknum = T.getWeekInMonth(before);

					if (T.getDateminus(eperiod, T.getDateAdd(after, -6)) < 0) {
						notFinished = false;
					}
				}
				model.addAttribute("list", list2);
				

			} else if (dId.equals("weight")) {

				boolean notFinished = true;
				while (notFinished) {
					HashMap hm = new HashMap();
					double avg = uweightdao.getUserWeighavg(userId, after, before);// 1주차
					int avg2 = uweightdao.getUseravg(userId, after, before);
					hm.put("date", year + "-" + month + "-" + weeknum + "주");
					hm.put("bmione", avg);
					System.out.println(avg);
					hm.put("weightNum", avg2);
					list2.add(hm);
					before = T.getDateAdd(after, 1); // 그 다음주 일요일
					after = T.getDateAdd(after, 7);
					arr = before.split("-");
					year = arr[0];
					month = arr[1];
					weeknum = T.getWeekInMonth(before);

					if (T.getDateminus(eperiod, T.getDateAdd(after, -6)) < 0) {
						notFinished = false;
					}
				}

				model.addAttribute("list", list2);
				

			} else if (dId.equals("col")) {
				boolean notFinished = true;
				while (notFinished) {
					HashMap hm = new HashMap();
					int avg = ucoldao.getUserColavg(userId, after, before, "col");
					int avg2 = ucoldao.getUserColavg(userId, after, before, "hdl");
					int avg3 = ucoldao.getUserColavg(userId, after, before, "ldl");
					int avg4 = ucoldao.getUserColavg(userId, after, before, "tg");
					hm.put("date", year + "-" + month + "-" + weeknum + "주");
					hm.put("col", avg);
					hm.put("hdl", avg2);
					hm.put("ldl", avg3);
					hm.put("tg", avg4);
					list2.add(hm);
					before = T.getDateAdd(after, 1); // 그 다음주 일요일
					after = T.getDateAdd(after, 7);
					arr = before.split("-");
					year = arr[0];
					month = arr[1];
					weeknum = T.getWeekInMonth(before);

					if (T.getDateminus(eperiod, T.getDateAdd(after, -6)) < 0) {
						notFinished = false;
					}
				}

				model.addAttribute("list", list2);
				
				
			} else if (dId.equals("hba")) {
				boolean notFinished = true;
				while (notFinished) {
					HashMap hm = new HashMap();
					int avg = uhbdao.getUserHbaavg(userId, after, before);

					hm.put("date", year + "-" + month + "-" + weeknum + "주");
					hm.put("hbaNumone", avg);
					System.out.println(avg);
					list2.add(hm);
					before = T.getDateAdd(after, 1); // 그 다음주 일요일
					after = T.getDateAdd(after, 7);
					arr = before.split("-");
					year = arr[0];
					month = arr[1];
					weeknum = T.getWeekInMonth(before);

					if (T.getDateminus(eperiod, T.getDateAdd(after, -6)) < 0) {
						notFinished = false;
					}
				}

				model.addAttribute("list", list2);
				
			}

		} else {// 월별

			String[] arr = T.getFirstAndFinishDayOfMonth(speriod);
			String startday = arr[0];
			String endday = arr[1];
			String[] arr2 = speriod.split("-");

			if (dId.equals("blood")) {
				boolean notFinished = true;
				List<UserBlood> listEmpty = new ArrayList();
				List<UserBlood> listAfterEat = new ArrayList();
				List<UserBlood> listBeforeSleep = new ArrayList();
				while (notFinished) {
					// HashMap hm = new HashMap();

					int avg = ublooddao.getUserBloodavg(userId, endday, startday, 1);// 1개월차
																						// 아침공복
					int avg2 = ublooddao.getUserBloodavg(userId, endday, startday, 2);
					int avg3 = ublooddao.getUserBloodavg(userId, endday, startday, 3);

					UserBlood ub = new UserBlood();
					ub.setBloodNum(avg);

					ub.setRegDate(arr2[0] + "-" + arr2[1]);
					listEmpty.add(ub);

					UserBlood ub2 = new UserBlood();
					ub2.setBloodNum(avg2);
					ub2.setRegDate(arr2[0] + "-" + arr2[1]);
					listAfterEat.add(ub2);

					UserBlood ub3 = new UserBlood();
					ub3.setBloodNum(avg3);
					ub3.setRegDate(arr2[0] + "-" + arr2[1]);
					listBeforeSleep.add(ub3);
					
					model.addAttribute("listEmpty", listEmpty);
					model.addAttribute("listAfterEat", listAfterEat);
					model.addAttribute("listBeforeSleep", listBeforeSleep);
					
					startday = T.getDateAdd(endday, 1); // 그 다음달 시작일
					arr = T.getFirstAndFinishDayOfMonth(startday);
					endday = arr[1];
					arr2 = startday.split("-");

					String[] arr3 = T.getFirstAndFinishDayOfMonth(eperiod);
					String finishday = arr3[1];
					if (T.getDateminus(T.getDateAdd(finishday, 1), endday) < 0) {
						notFinished = false;
					}
				}
			

			} else if (dId.equals("pressure")) {
				boolean notFinished = true;
				while (notFinished) {
					HashMap hm = new HashMap();

					int avg = upressdao.getUserPressavg(userId, endday, startday, "dplessure");// 1주차
					int avg2 = upressdao.getUserPressavg(userId, endday, startday, "splessure");// 1주차
					hm.put("date", arr2[0] + "-" + arr2[1]);

					hm.put("dplessure", avg);
					hm.put("splessure", avg);
					list2.add(hm);

					startday = T.getDateAdd(endday, 1); // 그 다음달 시작일
					arr = T.getFirstAndFinishDayOfMonth(startday);
					endday = arr[1];
					arr2 = startday.split("-");

					String[] arr3 = T.getFirstAndFinishDayOfMonth(eperiod);
					String finishday = arr3[1];
					if (T.getDateminus(T.getDateAdd(finishday, 1), endday) < 0) {
						notFinished = false;
					}
				}

				model.addAttribute("list", list2);
			
			} else if (dId.equals("col")) {
				boolean notFinished = true;
				while (notFinished) {
					HashMap hm = new HashMap();
					int avg = ucoldao.getUserColavg(userId, endday, startday, "col");
					int avg2 = ucoldao.getUserColavg(userId, endday, startday, "hdl");
					int avg3 = ucoldao.getUserColavg(userId, endday, startday, "ldl");
					int avg4 = ucoldao.getUserColavg(userId, endday, startday, "tg");

					hm.put("col", avg);
					hm.put("hdl", avg2);
					hm.put("ldl", avg3);
					hm.put("tg", avg4);
					hm.put("date", arr2[0] + "-" + arr2[1]);
					list2.add(hm);

					startday = T.getDateAdd(endday, 1); // 그 다음달 시작일
					arr = T.getFirstAndFinishDayOfMonth(startday);
					endday = arr[1];
					arr2 = startday.split("-");

					String[] arr3 = T.getFirstAndFinishDayOfMonth(eperiod);
					String finishday = arr3[1];
					if (T.getDateminus(T.getDateAdd(finishday, 1), endday) < 0) {
						notFinished = false;
					}
				}

				model.addAttribute("list", list2);
				
			} else if (dId.equals("weight")) {
				boolean notFinished = true;
				while (notFinished) {
					HashMap hm = new HashMap();
					double avg = uweightdao.getUserWeighavg(userId, endday, startday);// 1주차
					int avg2 = uweightdao.getUseravg(userId, endday, startday);

					hm.put("bmione", avg);
					hm.put("weightNum", avg2);
					hm.put("date", arr2[0] + "-" + arr2[1]);
					list2.add(hm);

					startday = T.getDateAdd(endday, 1); // 그 다음달 시작일
					arr = T.getFirstAndFinishDayOfMonth(startday);
					endday = arr[1];
					arr2 = startday.split("-");

					String[] arr3 = T.getFirstAndFinishDayOfMonth(eperiod);
					String finishday = arr3[1];
					if (T.getDateminus(T.getDateAdd(finishday, 1), endday) < 0) {
						notFinished = false;
					}
				}

				model.addAttribute("list", list2);
				
				
			} else if (dId.equals("hba")) {
				boolean notFinished = true;
				while (notFinished) {
					HashMap hm = new HashMap();
					int avg = uhbdao.getUserHbaavg(userId, endday, startday);

					hm.put("date", arr2[0] + "-" + arr2[1]);
					hm.put("hbaNumone", avg);

					list2.add(hm);
					startday = T.getDateAdd(endday, 1); // 그 다음달 시작일
					arr = T.getFirstAndFinishDayOfMonth(startday);
					endday = arr[1];
					arr2 = startday.split("-");

					String[] arr3 = T.getFirstAndFinishDayOfMonth(eperiod);
					String finishday = arr3[1];
					if (T.getDateminus(T.getDateAdd(finishday, 1), endday) < 0) {
						notFinished = false;
					}
				}

				model.addAttribute("list", list2);
			}
		}

		if (dId.equals("blood")) {

			model.addAttribute("goalEmpty", id.getGoalSmblood() + " - " + id.getGoalBmblood());
			model.addAttribute("goalEat", id.getGoalEblood());
			model.addAttribute("goalSleep", id.getGoalSblood());
			message ="/m/grape";
		} else if (dId.equals("pressure")) {
			model.addAttribute("goalsplessure", id.getGoalBpre());
			model.addAttribute("goaldplessure", id.getGoalSpre());
			message ="/m/pressgrape";
		} else if (dId.equals("col")) {
			model.addAttribute("goalcol", id.getGoalCol());
			model.addAttribute("goalldl", id.getGoalLdl());
			model.addAttribute("goalhdl", id.getGoalHdl());
			model.addAttribute("goaltg", id.getGoalTg());
			message ="/m/colgrape";
		} else if (dId.equals("hba")) {
			model.addAttribute("goalhba", id.getGoalHba());
			message ="/m/hbagrape";

		} else if (dId.equals("weight")) {
			model.addAttribute("goalBmi", id.getGoalSbmi() + " - " + id.getGoalBbmi());
			message ="/m/weightgrape";
		}

		
		
		return message;
	}
	

	/* 차트 메모입력 */
	@RequestMapping("/m/chart_comment.go")
	public String chartCommentController(
			@RequestParam(value = "dId", required = false, defaultValue = "") String dId,
		
			@RequestParam(value = "Seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "comment", required = false, defaultValue = "") String comment,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			System.out.println(seq);
			if(dId.equals("blood")){
				ublooddao.updateUserComment(seq,comment);
			}else if(dId.equals("press")){
				upressdao.updateUserComment(seq,comment);
			}else if(dId.equals("weight")){
				uweightdao.updateUserComment(seq,comment);
			}else if(dId.equals("col")){
				ucoldao.updateUserComment(seq,comment);
			}else if(dId.equals("hba")){
				uhbdao.updateUserComment(seq,comment);
			}
			map.put("result", true);
			map.put("message", "완료되었습니다.");
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	/* 차트 코멘트조회 */
	@RequestMapping("/m/chart_commnet_view.go")
	public String chartCommentViewController(
		@RequestParam(value = "dId", required = false, defaultValue = "") String dId,
		@RequestParam(value = "Seq", required = false, defaultValue = "0") int seq,
			
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			String str = "";
			if(dId.equals("blood")){
				
				str = ublooddao.getUserComment(seq).getComment();
			}else if(dId.equals("press")){
				str =upressdao.getUserComment(seq).getComment();
			}else if(dId.equals("weight")){
				str =uweightdao.getUserComment(seq).getComment();
			}else if(dId.equals("col")){
				str =ucoldao.getUserComment(seq).getComment();
			}else if(dId.equals("hba")){
				str =uhbdao.getUserComment(seq).getComment();
			}
			map.put("result", true);
			map.put("str", str);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	/* 복약 알림 뱃지 */
	@RequestMapping("/m/badge.go")
	public String mediBageController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "type", required = false, defaultValue = "0") int type,
		HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			String str = "";
			
			int cnt = badgeDao.getBadgeCount(userId,type);
			map.put("result", true);
			map.put("cnt", cnt);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	/* 복약 알림 뱃지읽음처리 */
	@RequestMapping("/m/badge_read.go")
	public String mediBageReadController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "type", required = false, defaultValue = "0") int type,
		HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			String str = "";
			badgeDao.updateBadgeRead(userId, type);
		
			map.put("result", true);
			map.put("message", "처리되었습니다.");
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	

	/* 로그아웃 */
	@RequestMapping("/m/logout.go")
	public String LogoutController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
	
		HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
		
			userdao.updateout(userId);
			map.put("result", true);
			map.put("message", "로그아웃 되었습니다.");
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 상담자 호출
	@RequestMapping("/m/chat_counselor.go")
	public String mChatCounselorController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "chatType", required = false, defaultValue = "") int chatType,
			@RequestParam(value = "flag", required = false, defaultValue = "") String flag, HttpServletRequest req,
			HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		String message = "";

		if (flag.equals("test")) {
			map.put("result", false);
			map.put("phone", "02-3015-7500");
			map.put("message",
					"본 건강상담 서비스의 이용가능 시간은 평일 AM09:00~PM18:00입니다. 원활한 상담을 위해 서비스 이용가능 시간에 다시 접속해주시기 바랍니다. 감사합니다.");
		} else {
			String week = T.getWeekday();
			if (week.equals("일")) {
				map.put("result", false);
				map.put("phone", "02-3015-7500");
				map.put("message",
						"본 건강상담 서비스의 이용가능 시간은 평일 AM09:00~PM18:00입니다. 원활한 상담을 위해 서비스 이용가능 시간에 다시 접속해주시기 바랍니다. 감사합니다.");
			} else {
				try {

					int chatRoomSeq = 0;
					User counselor = new User();
					int roomCount = chatRoomDao.getChatRoomCount(userId);
					// 방이 없으면 방 만들기
					if (roomCount == 0) {
						chatMemberDao.deleteChatMemberById(userId);
						// 디폴트 상담자 연결
						counselor = userdao.getUser(DEFAULT_COUNSELOR_ID);

						// 즉시 상담 예약
						int count = counseldao.getcounselrev(userId);
						if (count > 0) {
							counseldao.deletecounsel(userId);
						}
						// 상담대기에 기록
						Counsel cs = new Counsel();
						cs.setUserId(userId);
						counseldao.addcounselNow(cs);

						// 채팅방 생성
						ChatRoom room = new ChatRoom();
						room.setOwnerId(userId);
						chatRoomDao.addChatRoom(room);
						chatRoomSeq = chatRoomDao.getLastId();
						
						// 채팅방에 본인 참여
						ChatMember member1 = new ChatMember();
						member1.setChatRoomSeq(chatRoomSeq);
						member1.setUserId(userId);
						chatMemberDao.addChatMember(member1);

						// 채팅방에 디폴트 상담원 참여
						ChatMember member2 = new ChatMember();
						member2.setChatRoomSeq(chatRoomSeq);
						member2.setUserId(DEFAULT_COUNSELOR_ID);
						chatMemberDao.addChatMember(member2);

						map.put("counselor", counselor);
						map.put("chatRoomSeq", chatRoomSeq);
					} else {
						ChatRoom room = chatRoomDao.getChatRoom(userId);
						chatRoomSeq = room.getChatRoomSeq();

						ChatMember counselorMember = chatMemberDao.getChatNormalCounselor(chatRoomSeq, DEFAULT_COUNSELOR_ID);
						if (counselorMember == null) {
							// 기존 참여자 제거
							chatMemberDao.deleteChatMember(chatRoomSeq);
							chatMemberDao.deleteChatMemberById(userId);
							
							// 채팅방에 본인 참여
							ChatMember member1 = new ChatMember();
							member1.setChatRoomSeq(chatRoomSeq);
							member1.setUserId(userId);
							chatMemberDao.addChatMember(member1);

							// 디폴트 상담원 참여
							ChatMember member2 = new ChatMember();
							member2.setChatRoomSeq(room.getChatRoomSeq());
							member2.setUserId(DEFAULT_COUNSELOR_ID);
							chatMemberDao.addChatMember(member2);
							counselor = userdao.getUser(DEFAULT_COUNSELOR_ID);

							// 상담 대기자 명단에서는 제거
							counseldao.deletecounsel(room.getOwnerId());

							Counsel cs = new Counsel();
							cs.setUserId(userId);
							counseldao.addcounselNow(cs);
						} else {
							counselor = userdao.getUser(counselorMember.getUserId());
						}

						map.put("counselor", counselor);
						map.put("chatRoomSeq", room.getChatRoomSeq());
					}

					map.put("result", true);
					map.put("message", "상담 가능");

				} catch (Exception e) {
					map.put("message", e.getMessage());
					map.put("result", false);
				}
			}

			/*
			 * 최근 상담 건수가 가장 적은 상담원 연결 구조
			User counselor = userdao.getCounselor(userId);
			if (counselor == null) {
				counselor = userdao.getCounselorOne();
				if (counselor == null) {
					map.put("result", false);
					map.put("phone", "02-3015-7500");
					map.put("message", "상담 가능한 상담원이 없습니다.");
				} else {
					map.put("counselor", counselor);
					map.put("result", true);
					map.put("message", "상담 가능");
				}
			} else {
				map.put("counselor", counselor);
				map.put("result", true);
				map.put("message", "상담 가능");
			}
			*/
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	/* 상담예약 */
	@RequestMapping("/m/counsel_rev.go")
	public String counselRevController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "counselDate", required = false, defaultValue = "") String counselDate,
			@RequestParam(value = "counselTime", required = false, defaultValue = "") String counselTime,
			HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		String today = T.getToday();
		String week = T.getWeekday();
		String before = "";
		int chk = 0;
		/*
		 * if(week.equals("일")){//일요일이면 //토요일부터 일요일 현재까지 예약된게 있는지 판별 ManageIndex
		 * index = mindexdao.getrevTime(3); before = T.getDateAdd(today, -1)+" "
		 * +index.getEndTime();//토요일 끝나는 시간부터
		 * 
		 * }else if(week.equals("토")){ ManageIndex index =
		 * mindexdao.getrevTime(3); before = today+" "+index.getEndTime();//토요일
		 * 끝나는 시간부터
		 * 
		 * }else{ ManageIndex index = mindexdao.getrevTime(2); before = today+
		 * " "+index.getEndTime();//
		 * 
		 * }
		 */

		try {
			// 디폴트 상담자 연결
			User counselor = userdao.getUser(DEFAULT_COUNSELOR_ID);

			// 즉시 상담 예약
			int count = counseldao.getcounselrev(userId);
			if (count > 0) {
				counseldao.deletecounsel(userId);
			}
			Counsel cs = new Counsel();
			cs.setCounselDate(counselDate);
			cs.setCounselTime(counselTime);
			cs.setUserId(userId);
			counseldao.addcounsel(cs);
			
			// 방이 없으면 방 만들기
			int roomCount = chatRoomDao.getChatRoomCount(userId);
			if (roomCount == 0) {
				ChatRoom room = new ChatRoom();
				room.setOwnerId(userId);
				chatRoomDao.addChatRoom(room);
				int chatRoomSeq = chatRoomDao.getLastId();
				
				ChatMember member1 = new ChatMember();
				member1.setChatRoomSeq(chatRoomSeq);
				member1.setUserId(userId);
				chatMemberDao.addChatMember(member1);

				ChatMember member2 = new ChatMember();
				member2.setChatRoomSeq(chatRoomSeq);
				member2.setUserId(DEFAULT_COUNSELOR_ID);
				chatMemberDao.addChatMember(member2);
			}


			map.put("counselor", counselor);
			map.put("result", true);
			map.put("message", "예약되었습니다.");
	
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	/* 채팅 시작 */
	@RequestMapping("/m/chat_start.go")
	public String chatStartController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "chatRoomSeq", required = false, defaultValue = "") int chatRoomSeq,
	
		HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			//1. 채팅방이 진행중인지 확인
			ChatCounsel last = chatCounselDao.getRecentChatCounsel(chatRoomSeq);
			int status = last.getStatus();
			
			//2. 진행중이면 아무런 작동을 하지 않는다.
			
			//3. 대기중이거나 상담이 종료도었으면 새로운 상담을 기록한다.
			if (status == 0 || status == 2) {
				ChatRoom room = chatRoomDao.getChatRoom(chatRoomSeq);
				ChatMember member = chatMemberDao.getChatCounselor(room.getChatRoomSeq());

				ChatCounsel counsel = new ChatCounsel();
				counsel.setChatRoomSeq(chatRoomSeq);
				counsel.setUserId(room.getOwnerId());
				counsel.setAgentId(userId);
				counsel.setStatus(1);
				chatCounselDao.addChatCounsel(counsel);

				// 상담 대기자 명단에서는 제거
				counseldao.deletecounsel(room.getOwnerId());
			}
			
			map.put("result", true);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	/* 채팅 종료 */
	@RequestMapping("/m/chat_finish.go")
	public String chatFinishController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "chatRoomSeq", required = false, defaultValue = "") int chatRoomSeq,
	
		HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			//1. 채팅방이 진행중인지 확인한다.
			ChatCounsel last = chatCounselDao.getRecentChatCounsel(chatRoomSeq);
			int status = last.getStatus();
			
			//2. 진행중이면 종료 기록한다.
			if (status == 1) {
				ChatMsg msg = chatMsgDao.getChatMsgLast1(chatRoomSeq);
				last.setStatus(2);
				last.setDialog(msg.getContents());
				chatCounselDao.finishChatCounsel(last);
			}
			
			// 마지막 멤버가 나갈 경우 방을 폐쇄한다.
			int count = chatMemberDao.getCount(chatRoomSeq);
					
			if (count == 1) {
				chatRoomDao.deleteChatRoom(chatRoomSeq);
				chatMemberDao.deleteChatMember(chatRoomSeq);
			}
			map.put("result", true);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	
	/* 채팅 종료 */
	@RequestMapping("/m/chat_out.go")
	public String chatOutController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "chatRoomSeq", required = false, defaultValue = "") int chatRoomSeq,
	
		HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			//1. 채팅방이 진행중인지 확인한다.
			ChatCounsel last = chatCounselDao.getRecentChatCounsel(chatRoomSeq);
			int status = last.getStatus();
			
			//2. 진행중이면 종료 기록한다.
			if (status == 1) {
				ChatMsg msg = chatMsgDao.getChatMsgLast1(chatRoomSeq);
				last.setStatus(2);
				last.setDialog(msg.getContents());
				chatCounselDao.finishChatCounsel(last);
			}
			map.put("result", true);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	

	/* 서버시간 */
	@RequestMapping("/m/sever_time.go")
	public String serverTimeController(
		HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			
			String serverTime = T.getNowFomat();
			
			map.put("serverTime", serverTime);
			map.put("result", true);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	

	/* 수행내역 테스트 */
	@RequestMapping("/m/report_history_test.go")
	public String reportHistoryTestController(
		/*@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		@RequestParam(value = "today", required = false, defaultValue = "") String today,
		@RequestParam(value = "before", required = false, defaultValue = "") String before,*/
	
		HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			
			
			List <User>list =userdao.getUserWeekReportList();
			
			for(int i=0;i<list.size();i++){
				User user =list.get(i);
				String userId = user.getUserId();
				System.out.println(userId);
			}
			/*int weekbloodcnt = ublooddao.getUserweekBloodcnt(userId, today, before);
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
			
			reportHistoryDao.addReportHistory(rh);*/
			
			
			map.put("result", true);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", false);
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
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

	public int code3(double my, double other) {

		int code = 0;
		double num = other * 0.05;
		if (other + num < my) {// 5%이상
			code = 1;

		} else if (my < other + num && my > other - num) {// 사이
			code = 2;

		} else {
			code = 3;
		}

		return code;
	}

	public int code4(double goal, double my) {

		int code2 = 0;
		double num = goal * 0.05;

		if (goal + num < my) {// 5%이상
			code2 = 4;

		} else if (my < num + goal && goal - num < my) {// 사이
			code2 = 5;

		} else {
			code2 = 6;
		}
		return code2;
	}
	
	

}
