package com.mmall.controller;

import com.mmall.common.JsonData;
import com.mmall.exception.ParamException;
import com.mmall.param.TestVo;
import com.mmall.util.BeanValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello(){
        log.info("hello");
        throw new RuntimeException("text exception");
//        throw new PermissionException("text exception");
//        return JsonData.success("hello,permission");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo) throws ParamException{
        log.info("validate");
        BeanValidator.check(vo);
        return JsonData.success("text validate");
    }
}