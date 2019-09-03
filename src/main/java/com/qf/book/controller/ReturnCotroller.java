package com.qf.book.controller;

import com.qf.book.vo.Message;
import com.qf.book.pojo.Record;
import com.qf.book.service.RecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 还书控制层
 */
@Controller
@RequestMapping("/returns")
public class ReturnCotroller {
	
	@Resource
	private RecordService recordService;
	
	@RequestMapping("/findByLike")
	public ModelAndView findByLike(String uName) {
		ModelAndView mav = new ModelAndView();
		List<Record> records = recordService.findByLike(uName);
		mav.setViewName("/return.jsp");
		mav.addObject("records",records);
		return mav;
	}
	
	@RequestMapping("/findAll")
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView();
		List<Record> records = recordService.findAll();
		mav.setViewName("/return.jsp");
		mav.addObject("records",records);
		return mav;
	}
	
	@RequestMapping("/returnBook")
	@ResponseBody
	public Message returnBook(Integer rId) {
		try {
			Message msg = recordService.updateRecord(rId);
			return msg;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Message msg =new Message();
			msg.setMsg("操作异常");
			return msg;
		}
	}
	

}
