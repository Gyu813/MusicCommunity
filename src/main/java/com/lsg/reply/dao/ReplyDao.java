package com.lsg.reply.dao;

import java.util.List;

import com.lsg.reply.vo.ReplyVO;

public interface ReplyDao {
	
	public List<ReplyVO> selectAllReplies(int communityNo);
	
	public ReplyVO selectOneReply(int replyNo);
	
	public int insertReply(ReplyVO replyVO);
	
	public int increaseLoveCount(int replyNo);
	
	public int increaseHateCount(int replyNo);
	
	public int deleteReply(int replyNo);

	public int updateReplyLevelOneBody(int replyNo);
	
	public int deleteReplyByMemberNo(int memberNo);
	
	public int deleteReplyByCommunityNo(int communityNo);

}
