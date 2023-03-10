package wrsungrestapi.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import wrsungrestapi.consts.ResCode;
import wrsungrestapi.vo.CommentVo;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetCommentListRespDto {
    private List<CommentVo> commentList;
    private int code = ResCode.SUCCESS.value();
    private String message;
}
