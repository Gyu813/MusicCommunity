package com.lsg.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lsg.member.constants.Member;
import com.lsg.member.service.MemberService;
import com.lsg.member.vo.MemberVO;

@Controller
public class MemberController {
	
	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String viewLoginPage(HttpSession session) {
		
		if ( session.getAttribute(Member.USER) != null ) {
			return "redirect:/";
		}
				
		return "member/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView doLoginAction(@ModelAttribute("loginForm") MemberVO memberVO, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		MemberVO loginMember = memberService.getOneMember(memberVO);
		
		if ( loginMember != null ) {
			session.setAttribute(Member.USER, loginMember);
			return new ModelAndView("redirect:/");
		}
		
		session.setAttribute("status", "fail");
		
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String viewJoinPage() {
		return "member/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ModelAndView doJoinAction(@ModelAttribute("joinForm") @Valid MemberVO memberVO, Errors errors) {
		
		if ( errors.hasErrors() ) {
			ModelAndView view = new ModelAndView();
			view.setViewName("member/join");
			view.addObject("member", memberVO);
			return view;
		}
		
		if ( memberService.createMember(memberVO) ) {
			return new ModelAndView("redirect:/login");
		}
		
		return new ModelAndView("member/join");
		
	}
	
	@RequestMapping("/logout")
	public String doLogoutAction(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
}
