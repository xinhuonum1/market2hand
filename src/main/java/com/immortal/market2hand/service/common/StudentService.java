package com.immortal.market2hand.service.common;
/**
 * 学生信息操作service
 */
import com.immortal.market2hand.bean.CodeMsg;
import com.immortal.market2hand.bean.PageBean;
import com.immortal.market2hand.bean.Result;
import com.immortal.market2hand.dao.common.StudentDao;
import com.immortal.market2hand.entity.common.Student;
import com.immortal.market2hand.util.MD5SecretUtil;
import com.immortal.market2hand.util.MailUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;


@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;

	
	/**
	 * 根据学号查询
	 * @param sn
	 * @return
	 */
	public Student findBySn(String sn){
		return studentDao.findBySn(sn);
	}
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public Student findById(Long id){
		return studentDao.find(id);
	}
	
	/**
	 * 学生修改/编辑，当传入id时则为编辑，若id为空则为添加
	 * @param student
	 * @return
	 */
	public Student save(Student student){
		return studentDao.save(student);
	}
	
	/**
	 * 搜索学生列表
	 * @param pageBean
	 * @param student
	 * @return
	 */
	public PageBean<Student> findlist(PageBean<Student> pageBean, Student student){
		ExampleMatcher exampleMatcher = ExampleMatcher.matching();
		exampleMatcher = exampleMatcher.withMatcher("sn", GenericPropertyMatchers.contains());
		exampleMatcher = exampleMatcher.withIgnorePaths("status");
		Example<Student> example = Example.of(student, exampleMatcher);
		Sort sort = Sort.by(Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<Student> findAll = studentDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void delete(Long id){
		studentDao.deleteById(id);
	}
	
	/**
	 * 获取学生总数
	 * @return
	 */
	public long total(){
		return studentDao.count();
	}

	/***
	*忘记密码
	 * @param cpacha
	*@return:{@link com.immortal.market2hand.bean.Result<com.immortal.market2hand.entity.common.Student>}
	*@author:immor
	*@date:2021/3/27
	*/
	public Result<Student> forgetPwd(HttpServletRequest request,String stuemail, String cpacha) throws MessagingException {
		//表示实体信息合法，开始验证验证码是否为空
		if(StringUtils.isEmpty(cpacha)){
			return Result.error(CodeMsg.CPACHA_EMPTY);
		}
		//说明验证码不为空，从session里获取验证码
		Object attribute = request.getSession().getAttribute("admin_login");
		if(attribute == null){
			return Result.error(CodeMsg.SESSION_EXPIRED);
		}
		//表示session未失效，进一步判断用户填写的验证码是否正确
		if(!cpacha.equalsIgnoreCase(attribute.toString())){
			return Result.error(CodeMsg.CPACHA_ERROR);
		}
		//查询数据库是否存在该邮箱地址
		Student byEmail = studentDao.findByEmail(stuemail);
		if(null != byEmail){
			MailUtils.sendMail(stuemail);
			return Result.success(CodeMsg.HOME_STUDENT_SENDMAIL);
		}
		//返回结果
		return Result.error(CodeMsg.HOME_STUDENT_REGISTER_EMAIL_EXIST);
	}

	public Result updatePasswordByStuemail(HttpServletRequest request,String stuemail, String password, String cpata) {
		//表示实体信息合法，开始验证验证码是否为空
		if(StringUtils.isEmpty(cpata)){
			return Result.error(CodeMsg.CPACHA_EMPTY);
		}
		//说明验证码不为空，从session里获取验证码
		Object attribute = request.getSession().getAttribute("admin_login");
		if(attribute == null){
			return Result.error(CodeMsg.SESSION_EXPIRED);
		}
		if(cpata.equalsIgnoreCase(attribute.toString())){
			String newPassword = MD5SecretUtil.md5(password);
			studentDao.updatePasswordByEmail(stuemail,newPassword);
			return Result.success(CodeMsg.HOME_STUDENT_PASSWORD_UPDATE_success);
		}
		return Result.error(CodeMsg.HOME_STUDENT_PASSWORD_UPDATE_ERROR);
	}

	public Student findByNameAndEmail(String account) {
		//根据昵称和邮箱查找数据库是否存在该用户
		Student student = null;
		if(null != account){
		student =	studentDao.findByNameAndEmail(account);
		}
		return student;
	}

	public Student findByEmail(String stuemail) {
		Student byEmail = studentDao.findByEmail(stuemail);
		return byEmail;
	}
}
