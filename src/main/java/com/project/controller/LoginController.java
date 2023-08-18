package com.project.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.dto.UserDTO;
import com.project.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	private UserService userService;

	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/")
	public String main() {
		return "login_html/login";
	}

	@RequestMapping("/loginInput")
	public String login(String userId, String passwd, HttpSession session) {

		UserDTO dto = userService.login(userId, passwd);
		if (dto != null) {
			session.setAttribute("user", dto);
			System.out.println(session.getAttribute("user"));
			return "login_html/manager_page";
		}
		return "redirect:/";
	}

	@RequestMapping("/membership")
	public String membership() {
		return "login_html/membership";
	}

	@RequestMapping("/join")
	public String joinView() {
		return "login_html/join";
	}

	@RequestMapping("/myPage")
	public String MyPage() {
		return "login_html/myPage";
	}

	@RequestMapping("/user/join/action")
	public String join(UserDTO dto) {
		MembershipService membershipService = new MembershipService();
		membershipService.signup(dto);
		System.out.println(dto.toString());
		int result = userService.insertUser(dto);
		return "login_html/join_Complete";
	}

	@RequestMapping("/user")
	public String user() {
		return "login_html/user";
	}

	@RequestMapping("/manager_page")
	public ModelAndView userList(ModelAndView view) {
		List<UserDTO> list = userService.viewAlluser();
		System.out.println(list);
		view.addObject("list", list);
		view.setViewName("login_html/manager_page");
		return view;
	}

	@RequestMapping("/user/search")
	public ResponseEntity<String> searchUser(String kind, String search) {
		List<UserDTO> dto = userService.searchUser(kind, search);
		System.out.println(dto);
		return new ResponseEntity(dto, HttpStatus.OK);
	}

	@RequestMapping("/join_Complete")
	public String Join_Complete() {
		return "login_html/login";
	}

	@RequestMapping("/idsearch")
	public String idsearch() {
		return "login_html/idsearch";
	}

	@RequestMapping("/idsearch_result")
	public String Idsearch_result() {
		return "login_html/idsearch_result";
	}

	@RequestMapping("/searchPasswd")
	public String SearchPasswd() {
		return "login_html/searchPasswd";
	}

	@RequestMapping("/searchPasswd_Result")
	public String SearchPasswd_Result() {
		return "login_html/searchPasswd_Result";
	}

	@RequestMapping("/update/passwd")
	public int checkPasswd(@RequestParam("userId") String userId, @RequestParam("nowPass") String nowPass,
			@RequestParam("newPass") String newPass) throws Exception {
		boolean userPassValid = userService.checkPasswd(userId, nowPass);
		if (!userPassValid) {
			return 0; // 현재 비밀번호가 일치하지 않음
		}
		userService.updatePasswd(userId, newPass);

		return 1; // 비밀번호 변경 성공

	}

	// 아이디 중복 검사
	@RequestMapping("join/checkid")
	@ResponseBody
	public int checkid(@RequestParam("id") String id, @RequestParam("type") String type) {
		return userService.UserCheckId(id, type);
	}

	// 회원번호 랜덤으로 생성하게 해주는 클래스 입니다. 보기 쉽게 하려고 controller에 넣었습니다.
	public class MembershipService {
		private static final int MEMBERSHIP_NUMBER_LENGTH = 8; // 회원번호 길이입니다.
		private static final String NUMBERS = "0123456789"; // 사용될 회원번호 숫자, 숫자로만 생성되는 회원번호 입니다.
		private Set<String> generatedMembershipNumbers = new HashSet<>();

		// 기존에 사용된 회원번호 목록을 설정
		public void setExistingMembershipNumbers(Set<String> membershipNumber) {
			generatedMembershipNumbers.addAll(membershipNumber);
		}

		public void signup(UserDTO dto) {
			// 회원 정보 검증 및 처리

			String membershipNumber = UniqueMembershipNumber();
			dto.setMembershipNumber(membershipNumber);

			// 회원 가입 로직 수행 및 응답 DTO 생성
		}

		private String UniqueMembershipNumber() {
			Random random = new Random();
			StringBuilder membershipNumber = new StringBuilder();

			while (true) {
				for (int i = 0; i < MEMBERSHIP_NUMBER_LENGTH; i++) {
					int index = random.nextInt(NUMBERS.length());
					char digit = NUMBERS.charAt(index);
					membershipNumber.append(digit);
				}

				String generatedNumber = membershipNumber.toString();
				if (!generatedMembershipNumbers.contains(generatedNumber)) {
					generatedMembershipNumbers.add(generatedNumber);
					return generatedNumber;
				}

				membershipNumber.setLength(0); // 생성할 때 마다 회원번호 초기화
			}
		}
	}

}
