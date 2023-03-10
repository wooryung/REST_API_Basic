package wrsungrestapi.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import wrsungrestapi.consts.ResCode;
import wrsungrestapi.vo.PostVo;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetPostListRespDto {
    private List<PostVo> postList;
    private int code = ResCode.SUCCESS.value();
    private String message;
}
