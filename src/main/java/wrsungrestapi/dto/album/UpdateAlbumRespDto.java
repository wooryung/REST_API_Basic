package wrsungrestapi.dto.album;

import com.fasterxml.jackson.annotation.JsonInclude;
import wrsungrestapi.consts.ResCode;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateAlbumRespDto {
    private int code = ResCode.SUCCESS.value();
    private String message;
}
