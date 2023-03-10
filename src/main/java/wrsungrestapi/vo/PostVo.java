package wrsungrestapi.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostVo {
    private Long id;
    private Long userId;
    private String title;
    private String body;
}
