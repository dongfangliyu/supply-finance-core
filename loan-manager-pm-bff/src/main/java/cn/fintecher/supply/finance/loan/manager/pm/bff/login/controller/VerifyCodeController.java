package cn.fintecher.supply.finance.loan.manager.pm.bff.login.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;

import cn.fintecher.supply.finance.loan.manager.pm.bff.common.redis.IRedisService;
import com.github.bingoohuang.patchca.color.ColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.*;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * 生成验证码
 * 
 * @author fengqiang
 *
 */
@RestController
@Api(tags="验证码接口")
public class VerifyCodeController {
	@Autowired
	IRedisService redisService;

	private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
	private static Random random = new Random();

	static {
		cs.setColorFactory(new ColorFactory() {
			public Color getColor(int x) {
				int[] c = new int[3];
				int i = random.nextInt(c.length);
				for (int fi = 0; fi < c.length; fi++) {
					if (fi == i) {
						c[fi] = random.nextInt(71);
					} else {
						c[fi] = random.nextInt(256);
					}
				}
				return new Color(c[0], c[1], c[2]);
			}
		});
		RandomWordFactory wf = new RandomWordFactory();
		wf.setCharacters("123456789abcdefghigkmnpqrstuvwxyzABCDEFGHIGKLMNPQRSTUVWXYZ");
		wf.setMaxLength(4);
		wf.setMinLength(4);
		cs.setWordFactory(wf);
	}



	/**
	 *校验输入的验证码
	 **/
	@ApiOperation(value = "校验验证码", notes = "校验验证码")
	@RequestMapping(value = "/checkVerifycode", method = RequestMethod.GET)
	public Message checkVerifycode(@RequestParam("verifycode") String verifycode,@RequestParam("uuid")String uuid){
		if(verifycode!=null && !"".equals(verifycode)){
			if(uuid!=null && !"".equals(uuid)){
				String code = redisService.getString(uuid);
				if (code == null ||"".equals (code) || !code.equalsIgnoreCase(verifycode)) {
					return new Message(MessageType.MSG_ERROR,"login_bff","验证码输入错误");
				}else{
					return new Message(MessageType.MSG_SUCCESS,"login_bff",null);
				}
			}else{
				return  new Message(MessageType.MSG_ERROR,"login_bff","传参为空");
			}
		}else{
			return  new Message(MessageType.MSG_ERROR,"login_bff","请输入验证码");
		}
	}

	/**
	 *  随机生成验证码 
	 */
	@ApiOperation(value = "生成验证码", notes = "生成验证码", produces = "image/png")
	@RequestMapping(value = "/verifycode/{uuid}", method = RequestMethod.GET)
	public void createImage(@PathVariable("uuid")String uuid, HttpServletResponse resp, HttpServletRequest request)
			throws IOException {
		//uuid对应code码,没有uuid不生成校验码
		if(StringUtils.isEmpty(uuid)){
			return ;
		}
		
		//生成验证码
		switch (random.nextInt(5)) {
		case 0:
			cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
			break;
		case 1:
			cs.setFilterFactory(new MarbleRippleFilterFactory());
			break;
		case 2:
			cs.setFilterFactory(new DoubleRippleFilterFactory());
			break;
		case 3:
			cs.setFilterFactory(new WobbleRippleFilterFactory());
			break;
		case 4:
			cs.setFilterFactory(new DiffuseRippleFilterFactory());
			break;
		}
//		HttpSession session = request.getSession(false);
//		if (session == null) {
//			session = request.getSession();
//		}
		setResponseHeaders(resp);
		String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", resp.getOutputStream());

		// 存入用户对应的验证码
		redisService.setString(uuid, token, 600);
//		session.setAttribute(Constants.VERIFICATION_CODE_SESSION_KEY_F, token);
	}

	/**
	 * 设置http头
	 * 
	 * @param response
	 */
	protected void setResponseHeaders(HttpServletResponse response) {
		response.setContentType("image/png");
		response.setHeader("Cache-Control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		long time = System.currentTimeMillis();
		response.setDateHeader("Last-Modified", time);
		response.setDateHeader("Date", time);
		response.setDateHeader("Expires", time);
	}

}
