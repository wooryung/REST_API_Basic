package wrsungrestapi.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import wrsungrestapi.consts.ResCode;
import wrsungrestapi.vo.CommentVo;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetCommentRespDto {
    private CommentVo comment;
    private int code = ResCode.SUCCESS.value();
    private String message;
}
