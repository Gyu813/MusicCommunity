package com.lsg.reply.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsg.member.constants.Member;
import com.lsg.member.vo.MemberVO;
import com.lsg.reply.service.ReplyService;
import com.lsg.reply.vo.ReplyVO;

@Controller
public class ReplyController {
	
	private ReplyService replyService;
	
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	@RequestMapping(value = "/api/reply/{communityNo}", method = RequestMethod.GET)
	@ResponseBody
	public List<ReplyVO> getAllRepliesAction(@PathVariable int communityNo) {
		return replyService.getAllReplies(communityNo);
	}
	
	@RequestMapping(value = "/api/reply/{communityNo}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createReplyAction(@PathVariable int communityNo,
												 HttpSession session, ReplyVO replyVO) {
		MemberVO loginMember = (MemberVO) session.getAttribute(Member.USER);
		replyVO.setMemberNo(loginMember.getNo());
		replyVO.setCommunityNo(communityNo);
		
		boolean isSuccess = replyService.createReply(replyVO);
		
		ReplyVO newReply = null;
		if ( isSuccess ) {
			newReply = replyService.getOneReply(replyVO.getNo());
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", isSuccess);
		result.put("reply", newReply);
		
		return result;
		
	}
	
	@RequestMapping(value = "/api/love/{replyNo}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> increaseLoveCountAction(@PathVariable int replyNo) {
		boolean isSuccess = replyService.increaseLoveCount(replyNo);
		ReplyVO replyVO = replyService.getOneReply(replyNo);
		
		int loveCount = replyVO.getLoveCount();
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("response", isSuccess);
		response.put("loveCount", loveCount);
		
		return response;
	}
	
	@RequestMapping(value = "/api/hate/{replyNo}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> increaseHateCountAction(@PathVariable int replyNo) {
		boolean isSuccess = replyService.increaseHateCount(replyNo);
		ReplyVO replyVO = replyService.getOneReply(replyNo);
		
		int hateCount = replyVO.getHateCount();
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("response", isSuccess);
		response.put("hateCount", hateCount);
		
		return response;
	}
	
	@RequestMapping(value = "/api/remove/{replyNo}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> deleteReplyAction(@PathVariable int replyNo) {
		boolean isSuccess = replyService.removeReply(replyNo);
		
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("response", isSuccess);
		
		return response;
	}
	
	@RequestMapping(value = "/api/modifyBody/{replyNo}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> modifyReplyLevelOneBodyAction(@PathVariable int replyNo) {
		boolean isSuccess = replyService.modifyReplyLevelOneBody(replyNo);
		
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("response", isSuccess);
		
		return response;
	}
	
	

}
