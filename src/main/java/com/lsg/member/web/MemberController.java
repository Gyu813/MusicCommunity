package com.lsg.member.web;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lsg.community.service.CommunityService;
import com.lsg.member.constants.Member;
import com.lsg.member.service.MemberService;
import com.lsg.member.vo.MemberVO;
import com.lsg.util.DownloadUtil;

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
	public ModelAndView doLoginAction(@ModelAttribute("loginForm")
									  MemberVO memberVO, HttpServletRequest request) {
		
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
	public String doJoinAction(@ModelAttribute("joinForm")
							   @Valid MemberVO memberVO, Errors errors) {
		
		if ( errors.hasErrors() ) {
			return "member/join";
		}

		memberVO.save();
		
		boolean isSuccess = memberService.createMember(memberVO);
		
		if ( isSuccess ) {
			return "redirect:/login";
		}
		
		return "member/join";
	}
	
	@RequestMapping("/logout")
	public String doLogoutAction(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("/profile/{memberNo}")
	public void viewProfileAction(@PathVariable int memberNo,
								  HttpServletRequest request,
								  HttpServletResponse response) {
		MemberVO loginMember = memberService.getOneMemberByNo(memberNo);
		String profileFilename = loginMember.getProfileFilename();
		DownloadUtil downloadUtil = new DownloadUtil("D:/uploadProfiles/" + profileFilename);
		
		try {
			downloadUtil.download(request, response, profileFilename);
		} catch (UnsupportedEncodingException uee) {
			throw new RuntimeException(uee.getMessage(), uee);
		}
		
	}
	
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public ModelAndView viewMyPage(HttpSession session) {

		MemberVO loginMember = (MemberVO) session.getAttribute(Member.USER); 
		
		ModelAndView view = new ModelAndView();
		view.addObject("member", loginMember);
		view.setViewName("member/mypage");
		
		return view;
	}
	
	@RequestMapping(value = "/mypage", method = RequestMethod.POST)
	public String doModifyMyInfo(@ModelAttribute("modifyForm")
								 @Valid MemberVO memberVO, Errors errors,
								 HttpSession session) {
		
		MemberVO loginMember = (MemberVO) session.getAttribute(Member.USER);
		MemberVO updateMember = new MemberVO();
		
		updateMember.setNo(loginMember.getNo());
		
		boolean isModify = false;
		
		if ( !loginMember.getNickname().equals(memberVO.getNickname()) ) {
			updateMember.setNickname(memberVO.getNickname());
			isModify = true;
		}
		
		if ( !loginMember.getSelfIntroduction().equals(memberVO.getSelfIntroduction()) ) {
			updateMember.setSelfIntroduction(memberVO.getSelfIntroduction());
			isModify = true;
		}
		
		memberVO.save();
		
		if ( memberVO.getProfileFilename().length() > 0 ) {
			// 프로필을 바꾸었을 때,
			if ( !loginMember.getProfileFilename().equals("default.png") ) {
				File file = new File("D:/uploadProfiles/" + loginMember.getProfileFilename());
				file.delete();
			}
		} else {
			// 첨부파일을 바꾸지 않았을 때,
			updateMember.setProfileFilename(loginMember.getProfileFilename());
		}
		
		if ( !loginMember.getProfileFilename().equals(memberVO.getProfileFilename()) ) {
			updateMember.setProfileFilename(memberVO.getProfileFilename());
			isModify = true;
		}
		
		if ( isModify ) {
			memberService.modifyMyInfo(updateMember);
			loginMember = memberService.getOneMemberByNo(loginMember.getNo());
			session.setAttribute(Member.USER, loginMember);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/drop/{no}")
	public String doDropMember(@PathVariable int no, HttpSession session) {
		
		boolean isSuccess = memberService.dropMember(no);
		
		if ( isSuccess ) {
			session.invalidate();
			return "member/drop/drop";
		}
		
		return "member/mypage";
	}
	
	@RequestMapping("/api/exists/id")
	@ResponseBody
	public Map<String, Boolean> apiIsExistsId(@RequestParam String id) {
		
		boolean isExists = memberService.readCountMemberId(id);
		
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("response", isExists);
		
		return response;
	}
	
	@RequestMapping("drop/step1")
	public String viewVerifyPage() {
		return "member/drop/step1";
	}
	
	@RequestMapping("drop/step2")
	public ModelAndView viewRemoveMyCommunitiesPage(
			@RequestParam(required = false, defaultValue = "") String password,
			HttpSession session) {
		
		if ( password.length() == 0 ) {
			return new ModelAndView("error/404");
		}
		MemberVO member = (MemberVO) session.getAttribute(Member.USER);
		member.setPassword(password);
		
		MemberVO verifyMember = memberService.getOneMember(member);
		if ( verifyMember == null ) { // 비밀번호가 다를 경우
			return new ModelAndView("redirect:/drop/step1");
		}
		
		ModelAndView view = new ModelAndView();
		view.setViewName("member/drop/step2");
		view.addObject("member", verifyMember);
		
		return view;
	}
	
}
