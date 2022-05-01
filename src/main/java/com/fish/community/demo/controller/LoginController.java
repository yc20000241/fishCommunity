package com.fish.community.demo.controller;

import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;
import com.fish.community.demo.model.RegisterVerification;
import com.fish.community.demo.req.UserReq;
import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.resp.LoginEmailResp;
import com.fish.community.demo.resp.LoginResp;
import com.fish.community.demo.service.LoginService;
import com.fish.community.demo.util.EmailLoginVerification;
import com.fish.community.demo.util.UserLoginTimesUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;


@Controller
@RequestMapping("/login")
@ResponseBody
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/byPassword")
	@ApiOperation("使用密码登录")
	public CommonResp loginWithPassword(@Validated @RequestBody UserReq userReq){
		LoginResp loginResp = loginService.loginWithPassword(userReq);
		CommonResp<LoginResp> loginRespCommonResp = new CommonResp<>();

		if(loginResp.isLoginSuccess()){
			loginRespCommonResp.setContent(loginResp);
			loginRespCommonResp.setSuccess(true);
			loginRespCommonResp.setMessage("登录成功");
		}
		else{
			loginRespCommonResp.setSuccess(false);
			loginRespCommonResp.setMessage("登录失败");
		}

		return loginRespCommonResp;
	}


	@PostMapping("/byEmail")
	@ApiOperation("使用邮箱验证码登录")
	public CommonResp loginWithEmail(@Validated @RequestBody UserReq userReq, HttpServletRequest request){
		String user_agent = request.getHeader("user-agent");
		if(user_agent == null || user_agent.isEmpty())
			throw new BusinessException(BusinessExceptionCode.REQUEST_HEADER_MESSAGE_NOT_ALL);
		EmailLoginVerification emailLoginVerification1 = UserLoginTimesUtil.get(user_agent);
		if(emailLoginVerification1 == null){
			EmailLoginVerification emailLoginVerification = new EmailLoginVerification();
			emailLoginVerification.setGmtTime(System.currentTimeMillis());
			emailLoginVerification.setTimes((long)5);
			UserLoginTimesUtil.push(user_agent, emailLoginVerification);
		}else{
			long l = System.currentTimeMillis() - emailLoginVerification1.getGmtTime();
			if(l < 600000){
				if(emailLoginVerification1.getTimes() <= 0)
					throw new BusinessException(BusinessExceptionCode.EMAIL_VERIFICATION_CEND_TOO_REPEATD);
				else{
					emailLoginVerification1.setTimes(emailLoginVerification1.getTimes()-1);
					emailLoginVerification1.setGmtTime(System.currentTimeMillis());
				}
			}
			if(emailLoginVerification1.getTimes() < 0){
				l = l/60000;
				Long times = (l>5 ? 5 : l);
				emailLoginVerification1.setTimes(times);
				emailLoginVerification1.setGmtTime(System.currentTimeMillis());
			}
		}

		LoginResp loginResp = loginService.loginWithEmail(userReq);
		CommonResp<LoginResp> loginRespCommonResp = new CommonResp<>();
		loginRespCommonResp.setContent(loginResp);
		if(loginResp.isLoginSuccess())
			loginRespCommonResp.setMessage("登录成功");
		else
			loginRespCommonResp.setMessage("登录失败");
		return loginRespCommonResp;
	}

	@GetMapping("/getVerification/{email}")
	@ApiOperation("发送登录验证码")
	public CommonResp getVerification(@PathVariable("email") String email){

		CommonResp commonResp = new CommonResp();

		boolean isMatch = Pattern.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", email);
		if(!isMatch)
			throw new BusinessException(BusinessExceptionCode.EMAIL_FORMAT_WRONG);

		RegisterVerification verification = loginService.getQQVerification(email);
		commonResp.setMessage("验证码发送成功");
		commonResp.setContent(new LoginEmailResp(verification.getEmailToken()));

		return commonResp;
	}
}
