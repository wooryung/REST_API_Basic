package wrsungrestapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import wrsungrestapi.consts.ResCode;
import wrsungrestapi.dto.post.*;
import wrsungrestapi.exception.NoSuchDataException;
import wrsungrestapi.service.PostService;
import wrsungrestapi.vo.PostVo;

@Slf4j
@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public GetPostListRespDto getPostList(@RequestParam(value = "userId", required = false) Long userId) {
        GetPostListRespDto getPostListRespDto = new GetPostListRespDto();
        try {
            if (userId != null)
                getPostListRespDto.setPostList(postService.getPostListByUserId(userId));
            else
                getPostListRespDto.setPostList(postService.getPostList());
        } catch (NoSuchDataException e) {
            getPostListRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            getPostListRespDto.setMessage("No such post exists.");
        } catch (Exception e) {
            log.error("[PostController getPostList]", e);
            getPostListRespDto.setCode(ResCode.UNKNOWN.value());
            getPostListRespDto.setMessage(e.getLocalizedMessage());
        }
        return getPostListRespDto;
    }

    @GetMapping("/posts/{id}")
    public GetPostRespDto getPost(@PathVariable Long id) {
        GetPostRespDto getPostRespDto = new GetPostRespDto();
        try {
            getPostRespDto.setPost(postService.getPostById(id));
        } catch (NoSuchDataException e) {
            getPostRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            getPostRespDto.setMessage("No such post exists.");
        } catch (Exception e) {
            log.error("[PostController getPost]", e);
            getPostRespDto.setCode(ResCode.UNKNOWN.value());
            getPostRespDto.setMessage(e.getLocalizedMessage());
        }
        return getPostRespDto;
    }

    @PostMapping("/posts")
    public CreatePostRespDto createPost(@RequestBody CreatePostReqDto createPostReqDto) {
        CreatePostRespDto createPostRespDto = new CreatePostRespDto();
        try {
            PostVo postVo = new PostVo(0L, createPostReqDto.getUserId(), createPostReqDto.getTitle(), createPostReqDto.getBody());
            postService.createPost(postVo);
        } catch (DataIntegrityViolationException e) {
            createPostRespDto.setCode(ResCode.NULL_VALUE.value());
            createPostRespDto.setMessage("'userId', 'title', 'body' are required.");
        } catch (Exception e) {
            log.error("[PostController createPost]", e);
            createPostRespDto.setCode(ResCode.UNKNOWN.value());
            createPostRespDto.setMessage(e.getLocalizedMessage());
        }
        return createPostRespDto;
    }

    @PutMapping("/posts/{id}")
    public UpdatePostRespDto updatePost(@PathVariable Long id, @RequestBody UpdatePostReqDto updatePostReqDto) {
        UpdatePostRespDto updatePostRespDto = new UpdatePostRespDto();
        try {
            PostVo postVo = new PostVo(id, 0L, updatePostReqDto.getTitle(), updatePostReqDto.getBody());
            postService.updatePost(postVo);
        } catch (NoSuchDataException e) {
            updatePostRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            updatePostRespDto.setMessage("No such post exists.");
        } catch (Exception e) {
            log.error("[PostController updatePost]", e);
            updatePostRespDto.setCode(ResCode.UNKNOWN.value());
            updatePostRespDto.setMessage(e.getLocalizedMessage());
        }
        return updatePostRespDto;
    }

    @DeleteMapping("/posts/{id}")
    public DeletePostRespDto deletePost(@PathVariable Long id) {
        DeletePostRespDto deletePostRespDto = new DeletePostRespDto();
        try {
            postService.deletePost(id);
        } catch (NoSuchDataException e) {
            deletePostRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            deletePostRespDto.setMessage("No such post exists.");
        } catch (Exception e) {
            log.error("[PostController deletePost]", e);
            deletePostRespDto.setCode(ResCode.UNKNOWN.value());
            deletePostRespDto.setMessage(e.getLocalizedMessage());
        }
        return deletePostRespDto;
    }
}
