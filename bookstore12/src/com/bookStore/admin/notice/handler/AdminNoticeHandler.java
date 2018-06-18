package com.bookStore.admin.notice.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookStore.admin.notice.service.IAdminNoticeService;
import com.bookStore.commons.beans.Notice;
import com.bookStore.utils.PageModel;

@Controller
@RequestMapping("/admin/notice")
public class AdminNoticeHandler {
	
	@Autowired
	private IAdminNoticeService adminNoticeService;

	@RequestMapping("/listNotice.do")
	public String listNotice(@RequestParam(defaultValue="1")Integer pageIndex,Model model){
		
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		int count = adminNoticeService.findNoticeCount();
		pageModel.setRecordCount(count);
		
		List<Notice> notices = adminNoticeService.findNotice(pageModel);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("notices", notices);
		return "/admin/notices/list.jsp";
	}
	
	@RequestMapping("/addNotice.do")
	public String addNotice(Notice notice){
		adminNoticeService.addNotice(notice);
		return "redirect:/admin/notice/listNotice.do";
	}
	
	@RequestMapping("/modifyNotice.do")
	public String modifyNotice(Notice notice,String flag,Model model){
		if("1".equals(flag)){
			Notice notice1 = adminNoticeService.findNoticeById(notice.getN_id());
			model.addAttribute("n", notice1);
			
			return "/admin/notices/edit.jsp";
		}else{
			adminNoticeService.modifyNotice(notice);
			return "redirect:/admin/notice/listNotice.do";
		}
	}
	
	@RequestMapping("/deleteNotice.do")
	public String deleteNotice(Integer id){
		adminNoticeService.removeNotice(id);
		return "redirect:/admin/notice/listNotice.do";
	}
	
	
	
}
