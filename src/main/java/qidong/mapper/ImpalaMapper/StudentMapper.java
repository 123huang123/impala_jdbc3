package qidong.mapper.ImpalaMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Map<String,Object>> selectAll();
}



