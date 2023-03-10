package wrsungrestapi.service;

import wrsungrestapi.exception.NoSuchDataException;
import wrsungrestapi.mapper.PostMapper;
import wrsungrestapi.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;

    public List<PostVo> getPostList() {
        List<PostVo> list = postMapper.getPostList();
        if (list.isEmpty())
            throw new NoSuchDataException("No such data exists.");
        return list;
    }

    public List<PostVo> getPostListByUserId(Long userId) {
        List<PostVo> list = postMapper.getPostListByUserId(userId);
        if (list.isEmpty())
            throw new NoSuchDataException("No such data exists.");
        return list;
    }

    public PostVo getPostById(Long id) {
        PostVo postVo = postMapper.getPostById(id);
        if (postVo == null)
            throw new NoSuchDataException("No such data exists.");
        return postVo;
    }

    public void createPost(PostVo postVo) {
        postMapper.insertPost(postVo);
    }

    public void updatePost(PostVo postVo) {
        int result = postMapper.updatePost(postVo);
        if (result == 0)
            throw new NoSuchDataException("No such data exists.");
    }

    public void deletePost(Long id) {
        int result = postMapper.deletePost(id);
        if (result == 0)
            throw new NoSuchDataException("No such data exists.");
    }
}
