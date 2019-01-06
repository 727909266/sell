package com.sell.controller;

import com.sell.config.ProjectUrlConfig;
import com.sell.constant.CookieContstant;
import com.sell.constant.RedisConstant;
import com.sell.enums.ResultEnum;
import com.sell.model.SellerInfo;
import com.sell.service.SellerInfoService;
import com.sell.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by huhaoran on 2019/1/6 0006.
 */
@Controller
@RequestMapping("/seller")
public class SellUserController {
    @Autowired
    private SellerInfoService sellerInfoService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) {
        //1、openid去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerInfoService.findByOpenId(openid);
        if(sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error");
        }

        //2、设置token到redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);

        //3、设置token到cookie
        CookieUtil.set(response, CookieContstant.TOKEN, token, expire);

        //跳转尽量使用完整的http地址，最好不要使用相对路径的跳转
        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list");
    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String, Object> map) {
        //1、从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieContstant.TOKEN);
        if(cookie != null) {
            //2、清除redis
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            //3、清除cookie
            CookieUtil.set(response, CookieContstant.TOKEN, null, 0);
        }
        map.put("mas", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}
