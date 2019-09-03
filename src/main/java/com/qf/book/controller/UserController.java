package com.qf.book.controller;

import com.qf.book.vo.Message;
import com.qf.book.pojo.User;
import com.qf.book.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户控制层
 * 
 */
@Controller
@RequestMapping("/users")
public class UserController {

	@Resource
	private UserService userService;
	//登陆
	@RequestMapping("/login")
	public ModelAndView login(User user, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Message msg = userService.login(user);
		if (msg.getMsg().equals("success")) {
			msg.setMsg("");
			request.getSession().setAttribute("msg1", msg);
			mav.setViewName("redirect:/index.jsp");
			return mav;
		} else {
			mav.addObject("msg1", msg);
			mav.setViewName("redirect:/login.jsp");
			return mav;
		}
	}
	
	/**
	    *   查找所有的用户
	 * @return
	 */
	@RequestMapping("/findAll")
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView();
		List<User> users = userService.findAll();
		mav.setViewName("/userList.jsp");
		mav.addObject("users", users);
		return mav;
	}
	
	
	/**
	 * 模糊查找用户
	 * @param uName
	 * @return
	 */
	@RequestMapping("/findByLike")
	public ModelAndView findByLike(String uName) {
		ModelAndView mav = new ModelAndView();
		List<User> users = userService.findByLike(uName);
		mav.setViewName("/userList.jsp");
		mav.addObject("users", users);
		return mav;
	}

	@RequestMapping("/addUser")
	@ResponseBody
	public Message addUser(User user) {
		try {
			Message msg = userService.addUser(user);
			return msg;
		} catch (Exception e) {

			e.printStackTrace();
			Message msg = new Message();
			msg.setMsg("操作异常");
			return msg;
		}
	}

	@RequestMapping("/deleteUser")
	@ResponseBody
	public Message deleteUser(Integer uId) {
		try {
			Message msg = userService.deleteUser(uId);
			return msg;
		} catch (Exception e) {

			e.printStackTrace();
			Message msg = new Message();
			msg.setMsg("操作异常");
			return msg;
		}
	}

	@RequestMapping("/updateUser")
	@ResponseBody
	public Message updateUser(User user) {
		try {
			Message msg = userService.updateUser(user);
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			Message msg = new Message();
			msg.setMsg("操作异常");
			return msg;
		}
	}

	@RequestMapping("/poi")
	@ResponseBody
	public Message poi(MultipartFile file, HttpServletRequest request) {
		Message msg = new Message();
		// 声明book模板 存放excel文件
		try {
			Workbook book = null;
			if (file.getSize() == 0) {
				msg.setMsg("请选则上传文件!");
				return msg;
			}
			// 判断excel是否是最新版本 .xlsx为新版本
			if (file.getOriginalFilename().endsWith(".xlsx")) {
				book = new XSSFWorkbook(file.getInputStream());
			} else {
				book = new HSSFWorkbook(file.getInputStream());
			}
			// 获取我们excel文件中的第一张表
			
			Sheet sheet = book.getSheetAt(0);
			if (sheet.getLastRowNum() < 1) {
				msg.setMsg("请确认上传文件的内容是否有值");
				return msg;
			}
			List<User> users = new ArrayList<>();
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				String uName = row.getCell(0).getStringCellValue();
				String name = row.getCell(1).getStringCellValue();
				String email = row.getCell(3).getStringCellValue();
				String phone = row.getCell(2).getStringCellValue();
				User user = new User();
				user.setuName(uName);
				user.setName(name);
				user.setPhone(phone);
				user.setEmail(email);
				users.add(user);
			}
			book.close();
			userService.addUsers(users);
			msg.setMsg("添加成功");
			return msg;
		} catch (Exception e) {

			e.printStackTrace();
			msg.setMsg("异常");
			return msg;
		}

	}
	
	@RequestMapping("/old")
	@ResponseBody
	public Message upPwd(String pwd, HttpServletRequest request) {
		Message msg = new Message();
		Message m = (Message) request.getSession().getAttribute("msg1");
		Md5Hash md5 = new Md5Hash(pwd);
		if (m.getUser().getuPwd().equals(md5.toString())) {
			msg.setMsg("success");
			return msg;
		} else {
			msg.setMsg("旧密码错误");
			return msg;
		}
	}
	
	
	@RequestMapping("/updatePwd")
	@ResponseBody
	public Message updatePwd(String newpwd, HttpServletRequest request) {
		Message msg = new Message();
		try {
			 userService.updatePwd(newpwd,request);
			 msg.setMsg("success");
			 return msg;
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("异常");
			return msg;
		}
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public Message test() {
		Message msg = new Message();
		msg.setMsg("error");
		return msg;
	}

}
