package lg.cns.miniproject.repository;

import lg.cns.miniproject.entity.Account;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface AccountRepository {
    @Select("select username, password from account where username = #{username}")
    @ResultMap("AccountResultMap")
    Account findAccountByUsername(String username);

    @Insert("INSERT INTO account (account_id, username, password, created_by, updated_by) " +
            "VALUES (#{id}, #{username}, #{password}, #{createBy}, #{updateBy})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createAccount(Account account);

    @Update("UPDATE account SET created_by = #{createBy}, updated_by = #{updateBy} WHERE account_id = #{id}")
    void updateAccount(Account account);
}
