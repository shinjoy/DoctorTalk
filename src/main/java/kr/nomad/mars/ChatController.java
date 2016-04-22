package kr.nomad.mars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.nomad.mars.dao.ChatScriptDao;
import kr.nomad.mars.dao.CounselDao;
import kr.nomad.mars.dao.UBasicDao;
import kr.nomad.mars.dao.UserDao;
import kr.nomad.mars.dto.User;
import kr.nomad.mars.dto.UserBasic;
import kr.nomad.util.Response;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {

	@Autowired
	CounselDao counselDao;

	@Autowired
	UserDao userDao;

	@Autowired
	ChatScriptDao chatScriptDao;

	@Autowired
	UBasicDao userBasicDao;

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

	// 채팅
	@RequestMapping("/chat/chat.go")
	public String chatController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			HttpSession session, Model model) {

		return "/chat/chat";
	}

	// 사용자 정보
	@RequestMapping("/chat/user_info.go")
	public String chatUserInfoController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpSession session, Model model
		) {

		User user = userDao.getUser(userId);
		UserBasic basic = userBasicDao.getUserBasic(userId);
		
		model.addAttribute("user",user);
		model.addAttribute("basic",basic);
		return "/chat/user_info";
	}

	// 상담예약
	@RequestMapping("/chat/reserve_list.go")
	public String chatReserveListController(
			@RequestParam(value = "userId", required = false, defaultValue = "0") String userId,
			HttpSession session, Model model
		) {
		
		User user = userDao.getUser(userId);
		
		session.setAttribute("USER_ID", user.getUserId());
		session.setAttribute("USER_NAME", user.getUserName());
		session.setAttribute("USER_TYPE", user.getUserType());

		List list = counselDao.getcounselList();
		
		model.addAttribute("list",list);
		return "/chat/reserve_list";
	}

	// 상담자 목록
	@RequestMapping("/chat/counselor_list.go")
	public String chatCounselorListController(
			HttpSession session, Model model
		) {
		

		List list = userDao.getUserList(2);
		
		model.addAttribute("list",list);
		return "/chat/counselor_list";
	}

	// 스크립트 목록
	@RequestMapping("/chat/script.go")
	public String chatScriptController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model
		) {

		return "/chat/script";
	}

	// 스크립트 목록
	@RequestMapping("/chat/script_list.go")
	public String chatScriptListController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpServletResponse res, HttpSession session, Model model
		) {
		Map<String, Object> map = new HashMap<String, Object>();

		List list = new ArrayList();
		if (keyword.equals("")) {
			list = chatScriptDao.getScriptList();
		} else {
			list = chatScriptDao.getScriptList(keyword);
		}
		
		map.put("list", list);
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
}
