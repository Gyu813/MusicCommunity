package com.lsg.community.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lsg.community.service.CommunityService;
import com.lsg.community.vo.CommunityVO;
import com.lsg.member.constants.Member;
import com.lsg.member.vo.MemberVO;

@Controller
public class CommunityController {
	
	private CommunityService communityService;

	public void setCommunityService(CommunityService communityService) {
		this.communityService = communityService;
	}

	@RequestMapping("/")
	public ModelAndView viewMainPage() {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("community/main");
		
		List<CommunityVO> communityList = communityService.getAllCommunities();
		view.addObject("communityList", communityList);
		
		return view;
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String viewWritePage(HttpSession session) {
		
		if ( session.getAttribute(Member.USER) == null ) {
			return "redirect:/login";
		}
		
		return "community/write";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public ModelAndView doWriteAction(
			@ModelAttribute("writeForm")
			@Valid CommunityVO communityVO, Errors errors,
				   HttpServletRequest request) {
		
		if ( errors.hasErrors() ) {
			ModelAndView view = new ModelAndView();
			view.setViewName("community/write");
			view.addObject("community", communityVO);
			return view;
		}
		
		String requestIp = request.getRemoteAddr();
		communityVO.setRequestIp(requestIp);
		
		communityVO.save();
		
		boolean isSuccess = communityService.createCommunity(communityVO);
		
		if ( isSuccess ) {
			return new ModelAndView("redirect:/");
		}
		
		return new ModelAndView("redirect:/write");
	}
	
	@RequestMapping("/read/{no}")
	public String increaseViewCount(@PathVariable int no, HttpSession session) {
		
		if ( session.getAttribute(Member.USER ) == null ) {
			return "member/login";
		}
		
		boolean isSuccess = communityService.increaseViewCount(no);
		
		if ( isSuccess ) {
			return "redirect:/detail/" + no;
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/detail/{no}")
	public ModelAndView viewDetailPage(@PathVariable int no) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("community/detail");
		
		CommunityVO communityVO = communityService.getOneCommunity(no);
		view.addObject("community", communityVO);
		
		return view;
	}
	
	@RequestMapping("/recommend/{no}")
	public String increaseRecommendCount(@PathVariable int no) {
		
		boolean isSuccess = communityService.increaseRecommendCount(no);
		if ( isSuccess ) {
			return "redirect:/detail/" + no;
		}
		
		return "redirect:/";
	}
}
