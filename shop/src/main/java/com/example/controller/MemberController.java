package com.example.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.dto.MemberList;
import com.example.service.MemberListService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberListService mservice;

	@Autowired
	JavaMailSender mailSender;


	//회원가입
	@PostMapping("/register")
	public String create(MemberList memberList, Model model) throws Exception{
		MemberList member = new MemberList();
		member.setMem_id(memberList.getMem_id());
		member.setMem_name(memberList.getMem_name());
		member.setPwd(memberList.getPwd());
		member.setEmail(memberList.getEmail());
		member.setAddress(memberList.getAddress());
		member.setPhone(memberList.getPhone());
		try {
			mservice.register(member);
		}catch(Exception e) {
			e.printStackTrace();
		}
		 model.addAttribute("message", "회원가입이 완료되었습니다");
		return "redirect:/login";
	}
	
	private String hashPassword(String password) throws NoSuchAlgorithmException{
		MessageDigest md= MessageDigest.getInstance("SHA-256");
		 byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
		    StringBuilder sb = new StringBuilder();
		    for (byte b : hashedBytes) {
		        sb.append(String.format("%02x", b));
		    }
		    return sb.toString();
	}
	// 인증번호 발송
	@RequestMapping(value = "/pw_auth.me")
	public ModelAndView pw_auth(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String email = (String) request.getParameter("email");
		String id = (String) request.getParameter("id");

		MemberList vo = null;
		try {
			vo = mservice.selectMember(email);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (vo != null) {
			SecureRandom r = new SecureRandom();
			int num = r.nextInt(999999); // 랜덤난수설정

			if (vo.getMem_id().equals(id)) {
				session.setAttribute("email", vo.getEmail());

				String setfrom = "qkrgkektha@gmail.com";
				String tomail = email; // 받는사람
				String title = "[Barbie] 비밀번호변경 인증 이메일 입니다";
				String content = System.getProperty("line.separator") + "안녕하세요 회원님"
						+ System.getProperty("line.separator") + "DOIT BOOK 비밀번호찾기(변경) 인증번호는 " + num + " 입니다."
						+ System.getProperty("line.separator"); //

				try {
					MimeMessage message = mailSender.createMimeMessage();
					MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
					messageHelper.setFrom(setfrom);
					messageHelper.setTo(tomail);
					messageHelper.setSubject(title);
					messageHelper.setText(content);

					mailSender.send(message);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				ModelAndView mv = new ModelAndView();
				mv.setViewName("/pw_auth");
				mv.addObject("num", num);
				mv.addObject("email", email);
				return mv;
			} else {
				ModelAndView mv = new ModelAndView();
				mv.setViewName("/FindFd");
				return mv;
			}
		} else {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("/FindFd");
			return mv;
		}
	}

	//인증번호 동일한지 확인
	@RequestMapping(value = "/pw_set.me", method = RequestMethod.POST)
	public ModelAndView pw_set(@RequestParam(value = "email_injeung") String email_injeung,
			@RequestParam(value = "num") String num, @RequestParam(value="email") String email) throws IOException {
		System.out.println("일로넘오옴");
		System.out.println(email_injeung + num);
		ModelAndView mv = new ModelAndView();
		if (email_injeung.equals(num)) {
			mv.setViewName("/pw_new");
			mv.addObject("email", email);
			return mv;
		} else {
			mv.setViewName("/pw_auth");
			return mv;
		}
	} // 이메일 인증번호 확인


//DB비밀번호 업데이트	
@RequestMapping(value = "/pw_new.me", method = RequestMethod.POST)
public String pw_new(@RequestParam("pw")  String newPwd, @RequestParam("email") String email,  HttpSession session) throws Exception {
    System.out.println("pwd=" + newPwd + "email=" + email);
   
    MemberList  member = mservice.selectMember(email);
    String memberId = member.getMem_id();

  
    mservice.pwUpdate_M(memberId, newPwd, email);
    return "/login";
}

}