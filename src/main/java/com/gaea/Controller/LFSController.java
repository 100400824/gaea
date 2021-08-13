package com.gaea.Controller;

import com.gaea.server.lfsAPP.LFSandroid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
