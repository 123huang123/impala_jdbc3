package qidong.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import qidong.pojo.student_HDFS;
import qidong.service.GetDataService;
import qidong.util.PageInfoUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@Slf4j
public class GetDataPageController {
    @Resource
    private GetDataService getDataService;

    @RequestMapping("/getpage")
    public PageInfoUtils<Map<String,Object>> getPageData(Integer pageNum, Integer pageSize) {
        PageInfoUtils<Map<String,Object>> students = getDataService.getpage(pageNum, pageSize);
        System.out.println("students");
        return students;
    }
    @RequestMapping("/getall")
    public List<Map<String,Object>> getPageData() {
        List<Map<String,Object>> students = getDataService.getdata();
        System.out.println(students);
        return students;
    }

    @RequestMapping("/putData_foreach")
    public void putData_foreach() {
        int rows = getDataService.putAll_foreach();
        System.out.println("impala添加了："+rows+"行新数据到mysql");
    }

    @RequestMapping("/putData_batch")
    public void putData() {
        String rows = getDataService.putData_batch();
        System.out.println(rows);
    }
}