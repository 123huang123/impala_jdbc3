package qidong.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import qidong.pojo.student_HDFS;
import qidong.service.GetDataService;
import qidong.service.RuijiService;
import qidong.util.PageInfoUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class RuijiController {
    @Resource
    private RuijiService ruijiService;

    @RequestMapping("/getMysqlData")
    public List<Map<String,Object>> getData() {
        List<Map<String,Object>> users = ruijiService.getdata();
        System.out.println(users);
        return users;
    }
}