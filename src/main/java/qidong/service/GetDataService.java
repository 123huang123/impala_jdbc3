package qidong.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import qidong.mapper.ImpalaMapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qidong.mapper.MysqlMapper.RuijiMapper;
import qidong.util.PageInfoUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class GetDataService {

    @Autowired
    protected SqlSessionFactory sqlSessionFactory;
    @Resource
//    @Qualifier("ImpalaDataSource")
    private StudentMapper studentMapper;

    @Resource
//    @Qualifier("MysqlDataSource")
    private RuijiMapper ruijiMapper;

    public List<Map<String,Object>> getdata() {
        List<Map<String,Object>> students = studentMapper.selectAll();
        return students;
    }

    public PageInfoUtils<Map<String,Object>> getpage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);//这行是重点，表示从pageNum页开始，每页pageSize条数据
        Page<Map<String,Object>> students = (Page<Map<String, Object>>) studentMapper.selectAll();
        PageInfoUtils<Map<String,Object>> pageInfo = new PageInfoUtils<>(students);
        return pageInfo;
    }


    public int putAll_foreach() {
        List<Map<String,Object>> students = studentMapper.selectAll();
        int rows=ruijiMapper.putAll_foreach(students);
        return rows;
    }

    public String putData_batch() {

            //  开启批量处理模式 BATCH 、关闭自动提交事务 false
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
        RuijiMapper mapper = sqlSession.getMapper(RuijiMapper.class);
        List<Map<String,Object>> students = studentMapper.selectAll();
//        long startTime = System.currentTimeMillis();
        for (int i = 0; i < students.size(); i++) {
            mapper.putAll_batch(students.get(i));
        }
        sqlSession.commit();
        sqlSession.close();
        return "插入成功";

    }
}
