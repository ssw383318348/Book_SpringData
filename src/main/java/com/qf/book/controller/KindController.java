package com.qf.book.controller;

import com.qf.book.pojo.Kind;
import com.qf.book.vo.Message;
import com.qf.book.service.KindService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 图书种类控制层
 * 
 */
@Controller
@RequestMapping("/kinds")
public class KindController {

	@Resource
	private KindService kindService;

	@RequestMapping("/findAll")
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView();
		List<Kind> kinds = kindService.findAll();
		mav.addObject("kinds", kinds);
		mav.setViewName("/bookKind.jsp");
		return mav;
	}

	@RequestMapping("/addKind")
	@ResponseBody
	public Message addKind(Kind kind) {
		try {
			Message msg = kindService.addKind(kind);
			return msg;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Message msg = new Message();
			msg.setMsg("操作异常!");
			return msg;
		}
	}

	@RequestMapping("/updateKind")
	@ResponseBody
	public Message updateKind(Kind kind) {

		try {
			Message msg = kindService.updateKind(kind);
			return msg;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Message msg = new Message();
			msg.setMsg("操作异常!");
			return msg;
		}

	}

	@RequestMapping("/deleteKind")
	@ResponseBody
	public Message deleteKind(Integer kId) {
		try {
			Message msg = kindService.deleteById(kId);
			return msg;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Message msg = new Message();
			msg.setMsg("操作异常!");
			return msg;
		}
	}

}
