package wrsungrestapi.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoVo {
    private Long id;
    private Long albumId;
    private String title;
    private String url;
    private String thumbnailUrl;
}
