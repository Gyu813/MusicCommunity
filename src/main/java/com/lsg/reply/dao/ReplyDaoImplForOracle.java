package com.lsg.reply.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.lsg.reply.vo.ReplyVO;

public class ReplyDaoImplForOracle extends SqlSessionDaoSupport implements ReplyDao {

	@Override
	public List<ReplyVO> selectAllReplies(int communityNo) {
		return getSqlSession().selectList("ReplyDao.selectAllReplies", communityNo);
	}

	@Override
	public ReplyVO selectOneReply(int replyNo) {
		return getSqlSession().selectOne("ReplyDao.selectOneReply", replyNo);
	}

	@Override
	public int insertReply(ReplyVO replyVO) {
		int sequence = getSqlSession().selectOne("ReplyDao.nextValue");
		replyVO.setNo(sequence);
		return getSqlSession().insert("ReplyDao.insertReply", replyVO);
	}

	@Override
	public int increaseLoveCount(int replyNo) {
		return getSqlSession().update("ReplyDao.increaseLoveCount", replyNo);
	}

	@Override
	public int increaseHateCount(int replyNo) {
		return getSqlSession().update("ReplyDao.increaseHateCount", replyNo);
	}

	@Override
	public int deleteReply(int replyNo) {
		return getSqlSession().delete("ReplyDao.deleteReply", replyNo);
	}

	@Override
	public int updateReplyLevelOneBody(int replyNo) {
		return getSqlSession().update("ReplyDao.updateReplyLevelOneBody", replyNo);
	}

	@Override
	public int deleteReplyByMemberNo(int memberNo) {
		return getSqlSession().delete("ReplyDao.deleteReplyByMemberNo", memberNo);
	}

	@Override
	public int deleteReplyByCommunityNo(int communityNo) {
		return getSqlSession().delete("ReplyDao.deleteReplyByCommunityNo", communityNo);
	}

}
