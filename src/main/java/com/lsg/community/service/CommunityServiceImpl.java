package com.lsg.community.service;

import com.lsg.community.dao.CommunityDao;
import com.lsg.community.vo.CommunityPagerVO;
import com.lsg.community.vo.CommunityVO;
import com.lsg.reply.dao.ReplyDao;

import io.github.seccoding.web.pager.Pager;
import io.github.seccoding.web.pager.PagerFactory;
import io.github.seccoding.web.pager.explorer.ClassicPageExplorer;
import io.github.seccoding.web.pager.explorer.PageExplorer;

public class CommunityServiceImpl implements CommunityService {

	private CommunityDao communityDao;
	private ReplyDao replyDao;

	public void setCommunityDao(CommunityDao communityDao) {
		this.communityDao = communityDao;
	}

	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	@Override
	public PageExplorer getAllCommunities(CommunityPagerVO communityPagerVO) {
		
		Pager pager = PagerFactory.getPager(Pager.ORACLE,
											communityPagerVO.getPageNo() + "",
											communityDao.selectAllCommunityCount(communityPagerVO));
		PageExplorer pageExplorer = pager.makePageExplorer(ClassicPageExplorer.class, communityPagerVO);
		pageExplorer.setList(communityDao.selectAllCommunities(communityPagerVO));
		
		return pageExplorer;
	}

	@Override
	public CommunityVO getOneCommunity(int no) {
		return communityDao.selectOneCommunity(no);
	}

	@Override
	public boolean createCommunity(CommunityVO communityVO) {
		
		String body = communityVO.getBody();

		body = body.replace("\n", "<br/>");
		communityVO.setBody(body);
		
		return communityDao.insertCommunity(communityVO) > 0;
	}

	@Override
	public boolean increaseViewCount(int no) {
		return communityDao.increaseViewCount(no) > 0;
	}

	@Override
	public boolean increaseRecommendCount(int no) {
		return communityDao.increaseRecommendCount(no) > 0;
	}

	@Override
	public boolean modifyCommunity(CommunityVO communityVO) {
		return communityDao.updateCommunity(communityVO) > 0;
	}

	@Override
	public boolean removeCommunity(int no) {
		replyDao.deleteReplyByCommunityNo(no);
		return communityDao.deleteCommunity(no) > 0;
	}
	
}
