package wrsungrestapi.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import wrsungrestapi.consts.ResCode;
import wrsungrestapi.vo.UserVo;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUserListRespDto {
    private List<UserVo> userList;
    private int code = ResCode.SUCCESS.value();
    private String message;
}
