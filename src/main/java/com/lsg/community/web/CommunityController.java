package com.lsg.community.web;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
import org.springframework.web.multipart.support.RequestPartServletServerHttpRequest;
import org.springframework.web.servlet.ModelAndView;

import com.lsg.community.service.CommunityService;
import com.lsg.community.vo.CommunityVO;
import com.lsg.member.constants.Member;
import com.lsg.member.vo.MemberVO;
import com.lsg.util.DownloadUtil;

@Controller
public class CommunityController {
	
	private CommunityService communityService;

	public void setCommunityService(CommunityService communityService) {
		this.communityService = communityService;
	}

	@RequestMapping("/")
	public ModelAndView viewMainPage(HttpSession session) {
		
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
	public ModelAndView doWriteAction(@ModelAttribute("writeForm")
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
		
		CommunityVO community = communityService.getOneCommunity(no);
		ModelAndView view = new ModelAndView();
		
		view.setViewName("community/detail");
		view.addObject("community", community);
		
		return view;
	}
	
	@RequestMapping("/play/{no}")
	public void playVideoAction(@PathVariable int no,
								HttpServletRequest request,
								HttpServletResponse response) {
		CommunityVO community = communityService.getOneCommunity(no);
		String filename = community.getDisplayFilename();
		DownloadUtil downloadUtil = new DownloadUtil("D:/uploadFiles/" + filename);
		
		try {
			downloadUtil.download(request, response, filename);
		} catch (UnsupportedEncodingException uee) {
			throw new RuntimeException(uee.getMessage(), uee);
		}
	}
	
	@RequestMapping("/recommend/{no}")
	public String increaseRecommendCount(@PathVariable int no) {
		
		boolean isSuccess = communityService.increaseRecommendCount(no);
		if ( isSuccess ) {
			return "redirect:/detail/" + no;
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.GET)
	public ModelAndView viewModifyPage(@PathVariable int no, HttpSession session) {
	
		CommunityVO community = communityService.getOneCommunity(no);
		MemberVO loginMember = (MemberVO) session.getAttribute(Member.USER);
		
		if ( community.getMemberNo() != loginMember.getNo() ) {
			return new ModelAndView("WEB-INF/view/error/404");
		}
		
		ModelAndView view = new ModelAndView();
		view.setViewName("community/write");
		view.addObject("community", community);
		view.addObject("mode", "modify");
		
		return view;
	}
	
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.POST)
	public String doModifyAction(@PathVariable int no,
								 HttpSession session, HttpServletRequest request,
								 @ModelAttribute("writeForm")
								 @Valid CommunityVO communityVO, Errors errors) {
		
		CommunityVO originCommunity = communityService.getOneCommunity(no);
		MemberVO loginMember = (MemberVO) session.getAttribute(Member.USER);
		
		if ( originCommunity.getMemberNo() != loginMember.getNo() ) {
			return "error/404";
		}
		
		if ( errors.hasErrors() ) {
			return "redirect:/modify/" + no;
		}
		
		CommunityVO updateCommunity = new CommunityVO();
		updateCommunity.setNo(originCommunity.getNo());
		updateCommunity.setMemberNo(loginMember.getNo());
		
		boolean isModify = false;
		
		String ip = request.getRemoteAddr();
		if ( !originCommunity.getRequestIp().equals(ip) ) {
			updateCommunity.setRequestIp(ip);
			isModify = true;
		}
		
		if ( !originCommunity.getTitle().equals(communityVO.getTitle()) ) {
			updateCommunity.setTitle(communityVO.getTitle());
			isModify = true;
		}
		
		if ( !originCommunity.getBody().equals(communityVO.getBody()) ) {
			updateCommunity.setBody(communityVO.getBody());
			isModify = true;
		}
		
		communityVO.save();
		
		if ( communityVO.getDisplayFilename().length() > 0 ) {
			// 첨부파일을 바꾸었을 때,
			File file = new File("D:/uploadFiles/" + originCommunity.getDisplayFilename());
			file.delete();
		} else {
			// 첨부파일을 바꾸지 않았을 때,
			communityVO.setDisplayFilename(originCommunity.getDisplayFilename());
		}
		
		if ( !originCommunity.getDisplayFilename().equals(communityVO.getDisplayFilename()) ) {
			updateCommunity.setDisplayFilename(communityVO.getDisplayFilename());
			isModify = true;
		}
		
		if ( isModify ) {
			communityService.modifyCommunity(updateCommunity);
		}
		
		return "redirect:/detail/" + no;
	}
	
	@RequestMapping("/remove/{no}")
	public String doDeleteAction(@PathVariable int no, HttpSession session) {
		
		MemberVO loginMember = (MemberVO) session.getAttribute(Member.USER);
		CommunityVO thisCommunity = communityService.getOneCommunity(no);
		
		boolean isMyCommunity = loginMember.getNo() == thisCommunity.getMemberNo();
		
		if ( isMyCommunity && communityService.removeCommunity(no) ) {
			if ( thisCommunity.getDisplayFilename() != "" ) {
				File file = new File("D:/uploadFiles/" + thisCommunity.getDisplayFilename());
				file.delete();
			}
			return "redirect:/";
		}	
		
		return "error/404";
	}
	
}
