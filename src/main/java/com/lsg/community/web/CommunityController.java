package com.lsg.community.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lsg.member.constants.Member;

@Controller
public class CommunityController {

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
}
