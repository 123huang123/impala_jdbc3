package qidong.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import qidong.mapper.MysqlMapper.RuijiMapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RuijiService {
    @Resource
//    @Qualifier("MysqlDataSource")
    private RuijiMapper ruijiMapper;

    public List<Map<String,Object>> getdata() {
        List<Map<String,Object>> users = ruijiMapper.selectAll();
        return users;
    }
}
