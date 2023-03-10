package wrsungrestapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import wrsungrestapi.consts.ResCode;
import wrsungrestapi.dto.comment.*;
import wrsungrestapi.exception.NoSuchDataException;
import wrsungrestapi.service.CommentService;
import wrsungrestapi.vo.CommentVo;

@Slf4j
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    public GetCommentListRespDto getCommentList(@RequestParam(value = "postId", required = false) Long postId) {
        GetCommentListRespDto getCommentListRespDto = new GetCommentListRespDto();
        try {
            if (postId != null)
                getCommentListRespDto.setCommentList(commentService.getCommentListByPostId(postId));
            else
                getCommentListRespDto.setCommentList(commentService.getCommentList());
        } catch (NoSuchDataException e) {
            getCommentListRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            getCommentListRespDto.setMessage("No such comment exists.");
        } catch (Exception e) {
            log.error("[CommentController getCommentList]", e);
            getCommentListRespDto.setCode(ResCode.UNKNOWN.value());
            getCommentListRespDto.setMessage(e.getLocalizedMessage());
        }
        return getCommentListRespDto;
    }

    @GetMapping("/comments/{id}")
    public GetCommentRespDto getComment(@PathVariable Long id) {
        GetCommentRespDto getCommentRespDto = new GetCommentRespDto();
        try {
            getCommentRespDto.setComment(commentService.getCommentById(id));
        } catch (NoSuchDataException e) {
            getCommentRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            getCommentRespDto.setMessage("No such comment exists.");
        } catch (Exception e) {
            log.error("[CommentController getComment]", e);
            getCommentRespDto.setCode(ResCode.UNKNOWN.value());
            getCommentRespDto.setMessage(e.getLocalizedMessage());
        }
        return getCommentRespDto;
    }

    @PostMapping("/comments")
    public CreateCommentRespDto createComment(@RequestBody CreateCommentReqDto createCommentReqDto) {
        CreateCommentRespDto createCommentRespDto = new CreateCommentRespDto();
        try {
            CommentVo commentVo = new CommentVo(0L, createCommentReqDto.getPostId(), createCommentReqDto.getName(), createCommentReqDto.getEmail(), createCommentReqDto.getBody());
            commentService.createComment(commentVo);
        } catch (DataIntegrityViolationException e) {
            createCommentRespDto.setCode(ResCode.NULL_VALUE.value());
            createCommentRespDto.setMessage("'postId', 'name', 'email', 'body' are required.");
        } catch (Exception e) {
            log.error("[CommentController createComment]", e);
            createCommentRespDto.setCode(ResCode.UNKNOWN.value());
            createCommentRespDto.setMessage(e.getLocalizedMessage());
        }
        return createCommentRespDto;
    }

    @PutMapping("/comments/{id}")
    public UpdateCommentRespDto updateComment(@PathVariable Long id, @RequestBody UpdateCommentReqDto updateCommentReqDto) {
        UpdateCommentRespDto updateCommentRespDto = new UpdateCommentRespDto();
        try {
            CommentVo commentVo = new CommentVo(id, 0L, updateCommentReqDto.getName(), updateCommentReqDto.getEmail(), updateCommentReqDto.getBody());
            commentService.updateComment(commentVo);
        } catch (NoSuchDataException e) {
            updateCommentRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            updateCommentRespDto.setMessage("No such comment exists.");
        } catch (Exception e) {
            log.error("[CommentController updateComment]", e);
            updateCommentRespDto.setCode(ResCode.UNKNOWN.value());
            updateCommentRespDto.setMessage(e.getLocalizedMessage());
        }
        return updateCommentRespDto;
    }

    @DeleteMapping("/comments/{id}")
    public DeleteCommentRespDto deleteComment(@PathVariable Long id) {
        DeleteCommentRespDto deleteCommentRespDto = new DeleteCommentRespDto();
        try {
            commentService.deleteComment(id);
        } catch (NoSuchDataException e) {
            deleteCommentRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            deleteCommentRespDto.setMessage("No such comment exists.");
        } catch (Exception e) {
            log.error("[CommentController deleteComment]", e);
            deleteCommentRespDto.setCode(ResCode.UNKNOWN.value());
            deleteCommentRespDto.setMessage(e.getLocalizedMessage());
        }
        return deleteCommentRespDto;
    }
}
