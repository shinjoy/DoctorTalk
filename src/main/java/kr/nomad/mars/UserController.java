package kr.nomad.mars;

import java.util.List;

import javax.servlet.http.HttpSession;

import kr.nomad.mars.dto.User;
import kr.nomad.util.Paging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
	
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
	
	
	
	// 상담원 메인
	@RequestMapping("/talk/main.go")
	public String MainController(HttpSession session, Model model) {

		int topCount = 8;

		// List<Notice> noticeList = noticeDao.getNoticeTopList(topCount);
		// List<Faq> faqList = faqDao.getFaqTopList(topCount);
		// List<Analysis> anaList = analysisDao.getAnalysisTopList(topCount);
		//
		// model.addAttribute("noticeList", noticeList);
		// model.addAttribute("faqList", faqList);
		// model.addAttribute("anaList", anaList);
		return "/talk/main";
	}
	
	// 회원 관리 > 일반회원
	@RequestMapping("/talk/talk.go")
	public String userController(HttpSession session, Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		
		int count = 0;
		
		
		return "/talk/talk";
	}

	// 회원 관리 > 일반회원 리스트
	@RequestMapping("/talk/talk_list.go")
	public String userListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<User> list = null;
		int count = 0;

//		list = userDao.getUserList(gender,age,keyword,page, ITEM_COUNT_PER_PAGE);
//		count = userDao.getCount(gender,age,keyword);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "/talk/talk_list";
	}
	
	
	// 회원 관리 > 일반회원
	@RequestMapping("/talk/advice.go")
	public String adviceController(HttpSession session, Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		
		int count = 0;
		
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,PAGE_COUNT_PER_PAGING);
		
		model.addAttribute("paging", paging);
		
		return "/talk/advice";
	}

	// 회원 관리 > 일반회원 리스트
	@RequestMapping("/talk/advice_list.go")
	public String adviceListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			HttpSession session, Model model) {

		List<User> list = null;
		int count = 0;

//		list = userDao.getUserList(gender,age,keyword,page, ITEM_COUNT_PER_PAGE);
//		count = userDao.getCount(gender,age,keyword);

		// 페이징
		String paging = Paging.getPaging(page, count, ITEM_COUNT_PER_PAGE,PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "/talk/advice_list";
	}
	
	
	
	
}
