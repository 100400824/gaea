package com.gaea.Controller;

import com.gaea.server.LFSAPP.LFSandroid;
import com.gaea.server.LFSexamine.Examin;
import com.gaea.server.dachiyidun.WebDachiyidun;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/lfs")
@Api(tags = "旅法师营地_自动化测试")
public class LFSController {

    @ApiOperation("旅法师营地Android端自动化测试（包含鸿蒙）")
    @GetMapping(value = "android")
    public String hello() throws Exception {
        LFSandroid.doAppium();
        return "测试结束，日志保存在" + System.getProperty("user.dir");
    }

    @ApiOperation("监控审核平台")
    @GetMapping(value = "examin")
    @ApiImplicitParams({@ApiImplicitParam(name="url",value="线上登录地址：https://www.iyingdi.com/tz/login"),@ApiImplicitParam(name = "userName", value = "账号",required=true), @ApiImplicitParam(name = "passWord", value = "密码",required=true), @ApiImplicitParam(name = "minute", value = "监控频率N分钟",required=true), @ApiImplicitParam(name = "sendToEmal", value = "邮件接收人，例如：zhiqinag.he@gaea.com,peiwei.zhang@gaea.com",required=true)})
    public String doExamin(@RequestParam("url") String url, @RequestParam("userName") String userName, @RequestParam("passWord") String passWord, @RequestParam("minute") String minute, @RequestParam("sendToEmal") String sendToEmal) throws Exception {
        Examin.doExamin(url, userName, passWord, minute, sendToEmal);
        return "连续报警三次，程序停止运行。";
    }
}
