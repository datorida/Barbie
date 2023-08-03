package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.dto.MemberList;
import com.example.service.MemberListService;

@Controller
public class MainController {
	@Autowired
	private MemberListService memService;

	@RequestMapping(value = { "/", "/index" })
	public String index(Model model) {
		// 모델에 필요한 데이터를 추가하는 로직을 여기에 작성합니다.
		// 예를 들어, model.addAttribute("message", "Hello, World!");

		return "index"; // index.html 파일의 이름을 반환합니다.
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("top", "login");
		return "login";
	}

	// 회원가입페이지
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("top", "signup");
		return "signup";
	}

	@GetMapping("/about")
	public String about(Model model) {
		return "about";
	}

	@PostMapping("/loginimpl")
	public ResponseEntity<String> loginSubmit(@RequestParam("userId") String mem_id,
			@RequestParam("password") String pwd, HttpSession session) {
		try {
			boolean isAuthenticated = memService.authenticate(mem_id, pwd);

			if (isAuthenticated) {
				MemberList member = memService.get(mem_id);
				session.setAttribute("member", member);
				return ResponseEntity.ok("success");// 로그인 성공 시 200 상태 코드 반환
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 올바르지 않습니다."); // 인증 실패 시 401 상태
																										// 코드 반환
			}
		} catch (Exception e) {
			// 예외 처리 로직
			// 예외 발생 시 처리할 내용을 작성합니다.
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류가 발생했습니다."); // 예외 발생 시 500 상태 코드 반환
		}
	}

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, Model model) {
		// 예외처리 로직
		model.addAttribute("error", e.getMessage());
		return "error";
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
		session.invalidate();
		return "redirect:/";
	}

	// 비밀번호 찾기 페이지 이동
	@RequestMapping("/FindFd")
	public String FindFd() {
		return "FindFd";
	}

	@RequestMapping("/FindId")
	public String FindId() {
		return "FindId";
	}

}
