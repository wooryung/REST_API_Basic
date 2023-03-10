package wrsungrestapi.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import wrsungrestapi.consts.ResCode;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteUserRespDto {
    private int code = ResCode.SUCCESS.value();
    private String message;
}
