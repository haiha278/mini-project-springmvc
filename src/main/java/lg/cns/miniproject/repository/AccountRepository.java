package lg.cns.miniproject.repository;

import lg.cns.miniproject.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface AccountRepository {
    @Select("select username, password from account where username = #{username}")
    @ResultMap("AccountResultMap")
    Account findAccountByUsername(String username);
}
