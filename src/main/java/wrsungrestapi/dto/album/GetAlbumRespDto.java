package wrsungrestapi.dto.album;

import com.fasterxml.jackson.annotation.JsonInclude;
import wrsungrestapi.consts.ResCode;
import wrsungrestapi.vo.AlbumVo;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAlbumRespDto {
    private AlbumVo album;
    private int code = ResCode.SUCCESS.value();
    private String message;
}
