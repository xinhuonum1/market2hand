package com.immortal.market2hand.controller.home;

import com.immortal.market2hand.bean.CodeMsg;
import com.immortal.market2hand.bean.PageBean;
import com.immortal.market2hand.bean.Result;
import com.immortal.market2hand.config.AppConfig;
import com.immortal.market2hand.constant.SessionConstant;
import com.immortal.market2hand.entity.common.Goods;
import com.immortal.market2hand.entity.common.News;
import com.immortal.market2hand.entity.common.Student;
import com.immortal.market2hand.service.common.GoodsCategoryService;
import com.immortal.market2hand.service.common.GoodsService;
import com.immortal.market2hand.service.common.NewsService;
import com.immortal.market2hand.service.common.StudentService;
import com.immortal.market2hand.util.MD5SecretUtil;
import com.immortal.market2hand.util.SessionUtil;
import com.immortal.market2hand.util.ValidateEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;


/**
 * 前台首页控制器
 * @author Administrator
 *
 */
@RequestMapping("/home/index")
@Controller
public class IndexController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsCategoryService goodsCategoryService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private NewsService newsService;
	/**
	 * 前台首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,Model model, PageBean<Goods> pageBean, Goods goods,
						@RequestParam(name = "order",defaultValue = "desc") String order){
		pageBean.setPageSize(12);
		goods.setStatus(Goods.GOODS_STATUS_UP);
		model.addAttribute("pageBean", goodsService.findlist(pageBean, goods,order));
		model.addAttribute("name",goods.getName());
		model.addAttribute("newsList",newsService.findList(3));
		model.addAttribute(SessionConstant.SESSION_USER_AUTH_KEY, AppConfig.ORDER_AUTH);
		request.getSession().setAttribute("order",order);
		return "home/index/index";
	}
	
	/**
	 * 新闻详情页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/news_detail")
	public String index(Model model,@RequestParam(name="id",required=true)Long id){
		News news = newsService.find(id);
		model.addAttribute("news",news);
		news.setViewNumber(news.getViewNumber()+1);
		newsService.save(news);
		return "home/index/news_detail";
	}
	
	/**
	 * 用户登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model){
		return "home/index/login";
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(){
		SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY, null);
		return "redirect:login";
	}
	
	/**
	 * 检查学号是否存在
	 * @param sn
	 * @return
	 */
	@RequestMapping(value="/check_sn",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> checkSn(@RequestParam(name="sn",required=true)String sn){
		Student student = studentService.findBySn(sn);
		return Result.success(student == null);
	}

	/**
	 * 通过昵称和邮箱检查用户是否存在
	 * @return
	 */
	@RequestMapping(value="/check_nameAndEmail",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> checkNameAndEmail(@RequestParam(name="account",required=true)String account){
		Student student = studentService.findByNameAndEmail(account);
		return Result.success(student == null);
	}

	
	/**
	 * 用户注册表单提交
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> register(Student student){
		CodeMsg validate = ValidateEntityUtil.validate(student);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//基本验证通过
		if(studentService.findByEmail(student.getStuemail()) != null){
			return Result.error(CodeMsg.HOME_STUDENT_REGISTER_SN_EXIST);
		}
		//用md5加密算法对用户密码加密
		String md5pw = MD5SecretUtil.md5(student.getPassword());
		student.setPassword(md5pw);
		//存储用户
		student = studentService.save(student);
		if(student == null){
			return Result.error(CodeMsg.HOME_STUDENT_REGISTER_ERROR);
		}
		//表示注册成功，此时将用户信息放入session
		SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY, student);
		return Result.success(true);
	}

	/**
	*忘记密码
	*@param: * @param forgetPwd
	*@return:
	*@date:2021/3/27
	*/
	@RequestMapping(value="/forgetPwd",method=RequestMethod.POST)
	@ResponseBody
	public Result forgetPwd(HttpServletRequest request, String[] forgetPwd) throws MessagingException {
		Result<Student> result = null;
		if(null != forgetPwd){
			String stuemail = forgetPwd[0];
			String cpacha = forgetPwd[1];
			result = studentService.forgetPwd(request,stuemail,cpacha);
		}
		return result;
	}

	@RequestMapping(value = "/updatePassword")
	public String forgetPassword(){
		return "home/index/forgetPassword";
	}

	@RequestMapping("/updateUserPassword")
	@ResponseBody
	public Result updateUserPassword(String stuemail,String password,String truePassword,String cpata,HttpServletRequest request){

		if(null != password && password.equals(truePassword)){
	  Result result = studentService.updatePasswordByStuemail(request,stuemail,password,cpata);
	  return result;
		}

		return Result.success("更新密码成功");
	}
	
	/**
	 * 用户登录表单提交
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> login(@RequestParam(name="account",required=true)String account,
			@RequestParam(name="password",required=true)String password){
		Student student = studentService.findByNameAndEmail(account);
		if(student == null){
			return Result.error(CodeMsg.HOME_STUDENT_REGISTER_SN_EXIST);
		}
		//student = studentService.save(student);
		if(student == null){
			return Result.error(CodeMsg.HOME_STUDENT_SN_NO_EXIST);
		}
		//表示学号存在，验证密码是否正确
		if(!student.getPassword().equals(MD5SecretUtil.md5(password))){
			return Result.error(CodeMsg.HOME_STUDENT_PASSWORD_ERROR);
		}
		//验证用户状态是否被冻结
		if(student.getStatus() != Student.STUDENT_STATUS_ENABLE){
			return Result.error(CodeMsg.HOME_STUDENT_UNABLE);
		}
		//表示一切都符合，此时将用户信息放入session
		SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY, student);
		return Result.success(true);
	}
}
