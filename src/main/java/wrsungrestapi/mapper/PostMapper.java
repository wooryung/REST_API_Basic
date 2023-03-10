package wrsungrestapi.mapper;

import wrsungrestapi.vo.PostVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostVo> getPostList();
    List<PostVo> getPostListByUserId(Long userId);
    PostVo getPostById(Long id);
    void insertPost(PostVo postVo);
    int updatePost(PostVo postVo);
    int deletePost(Long id);
}
