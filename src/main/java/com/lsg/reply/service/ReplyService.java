package com.lsg.reply.service;

import java.util.List;

import com.lsg.reply.vo.ReplyVO;

public interface ReplyService {
	
	public List<ReplyVO> getAllReplies(int communityNo);
	
	public ReplyVO getOneReply(int replyNo);
	
	public boolean createReply(ReplyVO replyVO);
	
	public boolean increaseLoveCount(int replyNo);
	
	public boolean increaseHateCount(int replyNo);
	
	public boolean removeReply(int replyNo);
	
	public boolean modifyReplyLevelOneBody(int replyNo);

}
