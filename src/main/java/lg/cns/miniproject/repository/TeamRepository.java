package lg.cns.miniproject.repository;

import lg.cns.miniproject.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TeamRepository {
    @Select("select * from team")
    @ResultMap("TeamResultMap")
    List<Team> findAll();
}
