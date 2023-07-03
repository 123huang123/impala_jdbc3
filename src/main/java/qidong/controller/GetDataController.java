package qidong.controller;//package qidong.controller;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import qidong.pojo.student_HDFS;
//import qidong.service.GetDataService;
//
//import javax.servlet.ServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import qidong.pojo.student_HDFS;
import qidong.service.GetDataService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class GetDataController {
    @Autowired
    private GetDataService getDataService;

    @RequestMapping("/getdata")
    public List<Map<String,Object>> getdata() throws IOException {
        List<Map<String,Object>> students = getDataService.getdata();
        System.out.println("students");
        System.out.println(students);
        String file_name = ".\\src\\main\\resources\\csvs\\test.csv";
        FileWriter fos = new FileWriter(file_name, true);
        fos.write("num,name,sex,age,dept");
//        for (student_HDFS i : students) {
//            fos.write("\n");
//            fos.write(i.getNum() + "," + i.getName() + "," + i.getSex() + "," + i.getAge() + "," + i.getDept());
//        }
        fos.flush();
        fos.close();
        return students;
    }


    @RequestMapping("/downdata")
    public void downdata(HttpServletResponse response) throws IOException {
        String file_name = ".\\src\\main\\resources\\csvs\\test.csv";


        File file = new File(file_name);
// 获取文件名
        String filename = file.getName();
// 将文件写入输入流
        InputStream fis = new BufferedInputStream(new FileInputStream(file_name));
//        InputStream fis = new FileInputStream(file_name);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
// 清空response
        response.reset();
// 设置response的Header
        response.setCharacterEncoding("UTF-8");
//Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
//attachment表示以附件方式下载 inline表示在线打开 "Content-Disposition: inline; filename=文件名.mp3"
// filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
// 告知浏览器文件的大小
//        response.addHeader("Content-Length", "" + file.length());
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
//        OutputStream outputStream = response.getOutputStream();
        response.setContentType("application/octet-stream");
        outputStream.write(buffer);
        outputStream.flush();
    }
}