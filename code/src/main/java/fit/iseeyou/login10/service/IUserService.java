package fit.iseeyou.login10.service;

import fit.iseeyou.login10.domain.UserDomain;

import java.util.List;

public interface IUserService {
    List<UserDomain> getList(UserDomain userDomain);

    UserDomain getUser(Integer id);

    UserDomain getUserByUsername(String username);
}
