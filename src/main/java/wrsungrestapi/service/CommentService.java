package wrsungrestapi.service;

import wrsungrestapi.exception.NoSuchDataException;
import wrsungrestapi.vo.CommentVo;
import wrsungrestapi.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public List<CommentVo> getCommentList() {
        List<CommentVo> list = commentMapper.getCommentList();
        if (list.isEmpty())
            throw new NoSuchDataException("No such data exists.");
        return list;
    }

    public List<CommentVo> getCommentListByPostId(Long postId) {
        List<CommentVo> list = commentMapper.getCommentListByPostId(postId);
        if (list.isEmpty())
            throw new NoSuchDataException("No such data exists.");
        return list;
    }

    public CommentVo getCommentById(Long id) {
        CommentVo commentVo = commentMapper.getCommentById(id);
        if (commentVo == null)
            throw new NoSuchDataException("No such data exists.");
        return commentVo;
    }

    public void createComment(CommentVo commentVo) {
        commentMapper.insertComment(commentVo);
    }

    public void updateComment(CommentVo commentVo) {
        int result = commentMapper.updateComment(commentVo);
        if (result == 0)
            throw new NoSuchDataException("No such data exists.");
    }

    public void deleteComment(Long id) {
        int result = commentMapper.deleteComment(id);
        if (result == 0)
            throw new NoSuchDataException("No such data exists.");
    }
}
