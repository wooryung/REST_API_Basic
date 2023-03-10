package wrsungrestapi.service;

import wrsungrestapi.exception.NoSuchDataException;
import wrsungrestapi.vo.UserVo;
import wrsungrestapi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<UserVo> getUserList() {
        List<UserVo> list = userMapper.getUserList();
        if (list.isEmpty())
            throw new NoSuchDataException("No such data exists.");
        return list;
    }

    public UserVo getUserById(Long id) {
        UserVo userVo = userMapper.getUserById(id);
        if (userVo == null)
            throw new NoSuchDataException("No such data exists.");
        return userVo;
    }

    public void createUser(UserVo userVo) {
        userMapper.insertUser(userVo);
    }

    public void updateUser(UserVo userVo) {
        int result = userMapper.updateUser(userVo);
        if (result == 0)
            throw new NoSuchDataException("No such data exists.");
    }

    public void deleteUser(Long id) {
        int result = userMapper.deleteUser(id);
        if (result == 0)
            throw new NoSuchDataException("No such data exists.");
    }
}
