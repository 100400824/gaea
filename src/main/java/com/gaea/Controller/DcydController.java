package com.gaea.Controller;

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
@RequestMapping("/dcyd")
@Api(tags = "大吃一墩_自动化测试")
public class DcydController {

    @ApiOperation("大吃一墩，UI自动出牌，计算每回合预算、吃顿、巫毒、宝藏分数是否正确，必填参数userID(用户ID)，runNum(运行次数)，例如：14000034")
    @GetMapping()
    @ApiImplicitParams({@ApiImplicitParam(name="url",value="测试URL：https://activity-test.iyingdi.com/gameTest/demo.html"),@ApiImplicitParam(name = "userID", value = "用户ID,例如：14000034",required=true), @ApiImplicitParam(name = "runNum", value = "运行次数,例如：1",required=true)})
    public String hello(@RequestParam("url") String url,@RequestParam("userID") String userID, @RequestParam("runNum") String runNum) throws Exception {
        WebDachiyidun.doDCYD(url,userID, runNum);
        return "测试结束，日志保存在" + System.getProperty("user.dir");
    }
}
