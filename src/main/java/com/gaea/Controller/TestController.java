package com.gaea.Controller;

import com.gaea.server.LFSAPI.HttpClient;
import com.gaea.utls.publicTool.Upload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

    @ApiOperation("文件上传接口")
    @PostMapping("/upload")
    public Object addFile(MultipartFile file) throws IOException {
        return Upload.upload(file);
    }
}
