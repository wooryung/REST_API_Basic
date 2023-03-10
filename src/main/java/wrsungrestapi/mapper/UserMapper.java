package wrsungrestapi.mapper;

import wrsungrestapi.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserVo> getUserList();
    UserVo getUserById(Long id);
    void insertUser(UserVo userVo);
    int updateUser(UserVo userVo);
    int deleteUser(Long id);
}
