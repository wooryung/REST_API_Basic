package wrsungrestapi.dto.photo;

import com.fasterxml.jackson.annotation.JsonInclude;
import wrsungrestapi.consts.ResCode;
import wrsungrestapi.vo.PhotoVo;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetPhotoListRespDto {
    private List<PhotoVo> photoList;
    private int code = ResCode.SUCCESS.value();
    private String message;
}
