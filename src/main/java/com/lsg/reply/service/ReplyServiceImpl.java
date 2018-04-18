package com.lsg.reply.service;

import java.util.List;

import com.lsg.reply.dao.ReplyDao;
import com.lsg.reply.vo.ReplyVO;

public class ReplyServiceImpl implements ReplyService {
	
	private ReplyDao replyDao;

	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	@Override
	public List<ReplyVO> getAllReplies(int communityNo) {
		return replyDao.selectAllReplies(communityNo);
	}

	@Override
	public ReplyVO getOneReply(int replyNo) {
		return replyDao.selectOneReply(replyNo);
	}

	@Override
	public boolean createReply(ReplyVO replyVO) {
		return replyDao.insertReply(replyVO) > 0;
	}

	@Override
	public boolean increaseLoveCount(int replyNo) {
		return replyDao.increaseLoveCount(replyNo) > 0;
	}

	@Override
	public boolean increaseHateCount(int replyNo) {
		return replyDao.increaseHateCount(replyNo) > 0;
	}

	@Override
	public boolean removeReply(int replyNo) {
		return replyDao.deleteReply(replyNo) > 0;
	}

	@Override
	public boolean modifyReplyLevelOneBody(int replyNo) {
		return replyDao.updateReplyLevelOneBody(replyNo) > 0;
	}

}
