package com.gaea.Controller;

import com.gaea.server.LFSAPI.HttpClient;
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
@RequestMapping("/test")
@Api(tags = "临时接口-各种临时需求")
public class TestController {

    @ApiOperation("向审核平台添加线上帖子、文章、图片")
    @GetMapping()
    @ApiImplicitParams({@ApiImplicitParam(name="id",value="填写增加数据的事件ID（事件管理-事件列表中可查到ID）",required=true)})
    public String hello(@RequestParam("id") String id) throws Exception {
        return HttpClient.addData(id);
    }
}
