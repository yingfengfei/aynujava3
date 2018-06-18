package com.bookStore.admin.notice.service;

import java.util.List;

import com.bookStore.commons.beans.Notice;
import com.bookStore.utils.PageModel;

public interface IAdminNoticeService {

	List<Notice> findNotice(PageModel pageModel);

	int findNoticeCount();

	void addNotice(Notice notice);

	Notice findNoticeById(Integer n_id);

	void modifyNotice(Notice notice);

	void removeNotice(Integer id);

}
