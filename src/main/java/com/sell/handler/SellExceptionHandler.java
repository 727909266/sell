package com.sell.handler;

import com.sell.config.ProjectUrlConfig;
import com.sell.exception.ResourceExampleException;
import com.sell.exception.SellException;
import com.sell.exception.SellerAuthorizeException;
import com.sell.util.ResultVOUtil;
import com.sell.viewobject.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by huhaoran on 2019/1/6 0006.
 */
@ControllerAdvice //拦截异常并统一处理
public class SellExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    //拦截登陆异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login")
        );
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException e) {
        return ResultVOUtil.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = ResourceExampleException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handlerResourceException(ResourceExampleException e) {
    }

}
