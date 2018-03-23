package com.lsg.community.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lsg.community.service.CommunityService;
import com.lsg.community.vo.CommunityVO;
import com.lsg.member.constants.Member;

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
	public ModelAndView doWriteAction(@ModelAttribute("writeForm") CommunityVO communityVO, HttpServletRequest request) {
		
		String requestIp = request.getRemoteAddr();
		communityVO.setRequestIp(requestIp);
		
		ModelAndView view = new ModelAndView();
		
		boolean isSuccess = communityService.createCommunity(communityVO);
		
		if ( isSuccess ) {
			return new ModelAndView("redirect:/");
		}
		
		return new ModelAndView("redirect:/write");
	}
}
