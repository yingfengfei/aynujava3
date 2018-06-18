package com.bookStore.admin.notice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.admin.notice.dao.IAdminNoticeDao;
import com.bookStore.admin.notice.service.IAdminNoticeService;
import com.bookStore.commons.beans.Notice;
import com.bookStore.utils.PageModel;
@Service
public class AdminNoticeServiceImpl implements IAdminNoticeService {

	@Autowired
	private IAdminNoticeDao adminNoticeDao;
	
	@Override
	public List<Notice> findNotice(PageModel pageModel) {
		return adminNoticeDao.selectNotice(pageModel);
	}

	@Override
	public int findNoticeCount() {
		return adminNoticeDao.selectNoticCount();
	}

	@Override
	public void addNotice(Notice notice) {
		adminNoticeDao.insertNotice(notice);
	}

	@Override
	public Notice findNoticeById(Integer n_id) {
		return adminNoticeDao.selectNoticeById(n_id);
	}

	@Override
	public void modifyNotice(Notice notice) {
		adminNoticeDao.updateNotice(notice);
	}

	@Override
	public void removeNotice(Integer id) {
		adminNoticeDao.deleteNotice(id);
	}

}
