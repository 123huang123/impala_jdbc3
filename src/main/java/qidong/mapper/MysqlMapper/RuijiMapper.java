package qidong.mapper.MysqlMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface RuijiMapper {


    List<Map<String, Object>> selectAll();

    int putAll_foreach(List<Map<String, Object>> students);

    int putAll_batch(Map<String, Object> student);
}
