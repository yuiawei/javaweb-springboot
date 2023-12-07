package fit.iseeyou.login10.service.impl;

import fit.iseeyou.login10.domain.UserDomain;
import fit.iseeyou.login10.mapper.UserMapper;
import fit.iseeyou.login10.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDomain> getList(UserDomain userDomain) {
        return userMapper.getList(userDomain);
    }

    @Override
    public UserDomain getUser(Integer id) {
        return null;
    }

    @Override
    public UserDomain getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
