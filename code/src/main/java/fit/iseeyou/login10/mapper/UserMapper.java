package fit.iseeyou.login10.mapper;

import fit.iseeyou.login10.domain.UserDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDomain> getList(@Param("userDomain") UserDomain userDomain);

    UserDomain getUser(@Param("id") Integer id);

    UserDomain getUserByUsername(@Param("username") String username);
}
