package com.bookStore.admin.notice.dao;

import java.util.List;

import com.bookStore.commons.beans.Notice;
import com.bookStore.utils.PageModel;

public interface IAdminNoticeDao {

	List<Notice> selectNotice(PageModel pageModel);

	int selectNoticCount();

	void insertNotice(Notice notice);

	Notice selectNoticeById(Integer n_id);

	void updateNotice(Notice notice);

	void deleteNotice(Integer id);

}
