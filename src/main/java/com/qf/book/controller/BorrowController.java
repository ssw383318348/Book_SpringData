package com.qf.book.controller;

import com.qf.book.vo.Message;
import com.qf.book.pojo.Record;
import com.qf.book.service.RecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/** 
 * 借书控制层
 */
@Controller
@RequestMapping("/borrows")
public class BorrowController {
	
	@Resource
	private RecordService recordService;

	//借出书籍
	@RequestMapping("/borrow")
	public ModelAndView borrow(Record record) {
		ModelAndView mav = new ModelAndView();
		try {
			Message msg = recordService.borrow(record);
			mav.setViewName("/borrow.jsp");
			mav.addObject("msg", msg);
			return mav;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		    Message msg = new Message();
		    msg.setMsg("操作异常!");
		    mav.setViewName("/borrow.jsp");
			mav.addObject("msg", msg);
			return mav;
		}
			
	}

}
