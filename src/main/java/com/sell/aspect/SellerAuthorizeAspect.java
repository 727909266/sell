package com.sell.aspect;

import com.sell.constant.CookieContstant;
import com.sell.constant.RedisConstant;
import com.sell.exception.SellerAuthorizeException;
import com.sell.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by huhaoran on 2019/1/6 0006.
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(public * com.sell.cotroller.Seller*.*(..))" +
    "&& !execution(public * com.sell.cotroller.SellerUserController.*(..))") //本身登入登出方法排除
    public void verify() {}

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieContstant.TOKEN);
        if(cookie == null) {
            log.warn("[登陆校验] Cookie中查询不到token");
            throw new SellerAuthorizeException();
        }
        //去redis里查询
        String tokenValue = stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)) {
            log.warn("[登陆校验] Redis中查询不到token");
            throw new SellerAuthorizeException();
        }
    }

}
