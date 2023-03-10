package wrsungrestapi.mapper;

import wrsungrestapi.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentVo> getCommentList();
    List<CommentVo> getCommentListByPostId(Long postId);
    CommentVo getCommentById(Long id);
    void insertComment(CommentVo commentVo);
    int updateComment(CommentVo commentVo);
    int deleteComment(Long id);
}
